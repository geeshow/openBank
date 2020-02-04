package com.ken207.openbank.service;

import com.ken207.openbank.common.OBDateUtils;
import com.ken207.openbank.domain.*;
import com.ken207.openbank.domain.enums.PeriodType;
import com.ken207.openbank.domain.enums.SubjectCode;
import com.ken207.openbank.dto.AccountDto;
import com.ken207.openbank.dto.TradeDto;
import com.ken207.openbank.exception.BizRuntimeException;
import com.ken207.openbank.repository.AccountRepository;
import com.ken207.openbank.repository.ProductRepository;
import com.ken207.openbank.repository.TradeRepository;
import com.ken207.openbank.repository.query.TradeQueryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Map;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AccountService {

    private final ProductRepository productRepository;
    private final AccountRepository accountRepository;
    private final TradeRepository tradeRepository;
    private final TradeQueryRepository tradeQueryRepository;
    private final CodeGeneratorService codeGeneratorService;

    @Transactional
    public Long openRegularAccount(AccountDto.RequestOpen accountRequestOpen) {

        Product product = productRepository.findByProductCode(accountRequestOpen.getProductCode());

        if ( product == null ) {
            throw new BizRuntimeException("존재하지 않는 상품 코드 입니다. 상품코드:"+accountRequestOpen.getProductCode());
        }

        String accountNum = codeGeneratorService.createAccountNumber(SubjectCode.REGULAR.getSubjectCode());

        Account account = Account.openAccount(product, accountNum, accountRequestOpen.getRegDate(), accountRequestOpen.getTaxationCode());

        Account saveAccount = accountRepository.save(account);

        return saveAccount.getId();
    }

    @Transactional
    public void setPassword(String accountNum, String newPassword) {
        Account account = accountRepository.findByAccountNum(accountNum);
        account.setPassword(newPassword);
    }

    @Transactional
    public Trade deposit(String accountNum, TradeDto.RequestDeposit requestDeposit) {
        Account account = getAccountEntity(accountNum);

        if ( OBDateUtils.compareDate(account.getLastTradeDate(), requestDeposit.getTradeDate()) > 0 ) {
            throw new BizRuntimeException("지정일 이 후 거래가 존재. 기산일 거래를 요청해야 함.");
        }

        account.setReckonDt(requestDeposit.getTradeDate());
        Trade deposit = account.deposit(requestDeposit.getAmount());

        String tradeUniqueNumber = codeGeneratorService.createTradeUniqueNumber();

        return tradeRepository.save(deposit);
    }

    @Transactional
    public Trade withdraw(String accountNum, TradeDto.RequestDeposit requestWithdraw) {
        Account account = getAccountEntity(accountNum);


        account.setReckonDt(requestWithdraw.getTradeDate());
        Trade withdraw = account.withdraw(requestWithdraw.getAmount());
        return tradeRepository.save(withdraw);
    }

    private Account getAccountEntity(String accountNum) {
        Account account = accountRepository.findByAccountNum(accountNum);
        if (account == null) {
            throw new EntityNotFoundException("존재하지 않는 계좌번호 입니다.");
        }
        return account;
    }

    public Page<Account> getAccountList(Pageable pageable) {
        Page<Account> page = this.accountRepository.findAll(pageable);
        page.stream().forEach(o -> o.getProduct().getName());
        return page;
    }

    @Transactional(readOnly = true)
    public Interest getInterest(String accountNum, String untilDate) {

        //원장조회
        Account account = accountRepository.findByAccountNum(accountNum);

        //이자계산용 거래내역 조회
        //최종이자계산일 기준으로 이후 거래내역을 모두 조회
        //원가시 거래내역을 항상 insert함 0원 포함.
        List<Trade> tradeListForInterest = tradeRepository.findByAccountIdAndTradeDateGreaterThanOrderBySrnoDesc(account.getId(), account.getLastIntsDt());

        //InterestEntity 생성, AccountEntity 연관관계설정
        Interest interest = Interest.createInterest(account);

        //이자계산 대상 거래내역 등록
        interest.setTradeListForInterest(tradeListForInterest);

        //이자계산 기간, 방법 설정 -> 일수로 이자계산.
        interest.setPeriod(OBDateUtils.addDays(account.getLastIntsDt(), 1), untilDate, PeriodType.DAILY);

        //거래를 역순으로 정렬
        interest.sortedTradeList();

        // 거래내역 필터
        interest.remainLastTradeOfDays();

        // 거래내역을 이자계산내역으로 변경
        interest.makeInterestDetail();

        // 이자계산내역으로 이자계산 실행.
        interest.calculate();

        return interest;
    }

    @Transactional
    public Trade payInterest(String accountNum, String untilDate, String reckonDate) {

        Interest interest = this.getInterest(accountNum, untilDate);
        Trade trade = interest.payInterest(reckonDate);
        accountRepository.save(trade.getAccount());
        return trade;
    }

    @Transactional
    public Trade closeAccount(String accountNum, String reckonDate) {

        Account account = accountRepository.findByAccountNum(accountNum);
        Map<String, Long> dailyBalance = tradeQueryRepository.getDailyBalanceFrom(account.getId(), account.getLastIntsDt());

        Interest interest = Interest.builder()
                .account(account)
                .basicRate(account.getBasicRate().getRate())
                .reckonDate(reckonDate)
                .fromDate(account.getLastIntsDt())
                .build();
        return account.closeAccount(interest);
    }
}
