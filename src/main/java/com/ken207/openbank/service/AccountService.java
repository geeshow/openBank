package com.ken207.openbank.service;

import com.ken207.openbank.common.OBDateUtils;
import com.ken207.openbank.domain.AccountEntity;
import com.ken207.openbank.domain.TradeEntity;
import com.ken207.openbank.domain.enums.SubjectCode;
import com.ken207.openbank.dto.AccountDto;
import com.ken207.openbank.dto.TradeDto;
import com.ken207.openbank.exception.BizRuntimeException;
import com.ken207.openbank.repository.AccountRepository;
import com.ken207.openbank.repository.TradeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;
    private final TradeRepository tradeRepository;
    private final CodeGeneratorService codeGeneratorService;

    @Transactional
    public String openRegularAccount(AccountDto.RequestOpen accountRequestOpen) {
        String accountNum = codeGeneratorService.createAcno(SubjectCode.REGULAR.getSubjectCode());

        AccountEntity accountEntity = AccountEntity.openAccount(accountNum, SubjectCode.REGULAR, accountRequestOpen.getRegDate(), accountRequestOpen.getTaxationCode());
        AccountEntity saveAccount = accountRepository.save(accountEntity);
        return saveAccount.getAccountNum();
    }

    @Transactional
    public void setPassword(String accountNum, String newPassword) {
        AccountEntity account = accountRepository.findByAccountNum(accountNum);
        account.setPassword(newPassword);
    }

    @Transactional
    public TradeEntity deposit(String accountNum, TradeDto.RequestDeposit requestDeposit) {
        AccountEntity account = getAccountEntity(accountNum);

        if ( OBDateUtils.compareDate(account.getLastTradeDate(), requestDeposit.getTradeDate()) > 0 ) {
            throw new BizRuntimeException("지정일 이 후 거래가 존재. 기산일 거래를 요청해야 함.");
        }

        account.setReckonDt(requestDeposit.getTradeDate());
        TradeEntity deposit = account.deposit(requestDeposit.getAmount());
        return tradeRepository.save(deposit);
    }

    @Transactional
    public TradeEntity withdraw(String accountNum, TradeDto.RequestDeposit requestWithdraw) {
        AccountEntity account = getAccountEntity(accountNum);


        account.setReckonDt(requestWithdraw.getTradeDate());
        TradeEntity withdraw = account.withdraw(requestWithdraw.getAmount());
        return tradeRepository.save(withdraw);
    }

    private AccountEntity getAccountEntity(String acno) {
        AccountEntity account = accountRepository.findByAccountNum(acno);
        if (account == null) {
            throw new EntityNotFoundException("존재하지 않는 계좌번호 입니다.");
        }
        return account;
    }

}
