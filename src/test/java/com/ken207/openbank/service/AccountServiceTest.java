package com.ken207.openbank.service;

import com.ken207.openbank.common.OBDateUtils;
import com.ken207.openbank.common.TestDescription;
import com.ken207.openbank.domain.account.AccountEntity;
import com.ken207.openbank.domain.enums.TaxationCode;
import com.ken207.openbank.domain.enums.TradeCd;
import com.ken207.openbank.dto.AccountDto;
import com.ken207.openbank.exception.BizRuntimeException;
import com.ken207.openbank.repository.AccountRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class AccountServiceTest {

    @Autowired
    AccountService accountService;

    @Autowired
    AccountRepository accountRepository;

    @Test
    @TestDescription("보통예금 계좌 정상 신규 테스트")
    public void openAccount() throws Exception {
        //given
        String regDate = OBDateUtils.getToday();
        AccountDto.Request accountRequest = AccountDto.Request.builder()
                .regDate(regDate)
                .taxationCode(TaxationCode.REGULAR)
                .build();

        //when
        String acno = accountService.openRegularAccount(accountRequest);
        AccountEntity accountEntity = accountRepository.findByAccountNum(acno);

        //then
        assertThat(accountEntity.getAccountNum().contains("1310000"));
        assertEquals(OBDateUtils.getToday(), accountEntity.getRegDate());
        assertEquals(OBDateUtils.getToday(), accountEntity.getLastIntsDt());
        assertEquals(OBDateUtils.getToday(), accountEntity.getReckonDt());
        assertEquals(TaxationCode.REGULAR, accountEntity.getTaxationCode());
        assertEquals(0, accountEntity.getAccoBlnc());
    }


    @Test
    @TestDescription("보통예금 계좌 기산일 신규 테스트")
    public void openAccountWithReckonDt() throws Exception {
        //given
        String regDate = "20101010";
        AccountDto.Request accountRequest = AccountDto.Request.builder()
                .regDate(regDate)
                .taxationCode(TaxationCode.REGULAR)
                .build();

        //when
        String acno = accountService.openRegularAccount(accountRequest);
        AccountEntity accountEntity = accountRepository.findByAccountNum(acno);

        //then
        assertThat(accountEntity.getAccountNum().contains("1310000"));
        assertEquals(regDate, accountEntity.getRegDate());
        assertEquals(regDate, accountEntity.getLastIntsDt());
        assertEquals(regDate, accountEntity.getReckonDt());
        assertEquals(TaxationCode.REGULAR, accountEntity.getTaxationCode());
        assertEquals(0, accountEntity.getAccoBlnc());
    }

    @Test
    @TestDescription("비밀번호 변경 정상 테스트")
    public void changePassword() throws Exception {
        //given
        AccountDto.Request accountRequest = AccountDto.Request.builder()
                .regDate(OBDateUtils.getToday())
                .taxationCode(TaxationCode.REGULAR)
                .build();
        String acno = accountService.openRegularAccount(accountRequest);

        //when
        String newPassword = "1234";
        accountService.setPassword(acno, newPassword);
        AccountEntity accountEntity = accountRepository.findByAccountNum(acno);

        //then
        assertEquals(OBDateUtils.getToday(), accountEntity.getRegDate());
        assertEquals(OBDateUtils.getToday(), accountEntity.getLastIntsDt());
        assertEquals(OBDateUtils.getToday(), accountEntity.getReckonDt());
        assertEquals(newPassword, accountEntity.getPassword());
        assertEquals(0, accountEntity.getAccoBlnc());
    }


    @Test
    @TestDescription("정상 입금 테스트")
    public void inAccount() throws Exception {
        //given
        AccountDto.Request accountRequest = AccountDto.Request.builder()
                .regDate(OBDateUtils.getToday())
                .taxationCode(TaxationCode.REGULAR)
                .build();
        String acno = accountService.openRegularAccount(accountRequest);
        long trnAmt1 = 30000;
        long trnAmt2 = 200000;
        long trnAmt3 = 1000000;

        //when
        long result1 = accountService.inAmount(acno, trnAmt1);
        long result2 = accountService.inAmount(acno, trnAmt2);
        long result3 = accountService.inAmount(acno, trnAmt3);
        AccountEntity accountEntity = accountRepository.findByAccountNum(acno);

        //then
        assertEquals(trnAmt1, result1);
        assertEquals(trnAmt1+trnAmt2, result2);
        assertEquals(trnAmt1+trnAmt2+trnAmt3, result3);
        assertEquals(trnAmt1+trnAmt2+trnAmt3, accountEntity.getAccoBlnc());
        assertEquals(4, accountEntity.getTradeLogs().size());

        assertEquals(0, accountEntity.getTradeLogs().get(1).getBlncBefore());
        assertEquals(trnAmt1, accountEntity.getTradeLogs().get(1).getBlncAfter());
        assertEquals(trnAmt1, accountEntity.getTradeLogs().get(1).getAmount());
        assertEquals(TradeCd.IN, accountEntity.getTradeLogs().get(1).getTradeCd());

        assertEquals(trnAmt1, accountEntity.getTradeLogs().get(2).getBlncBefore());
        assertEquals(trnAmt1+trnAmt2, accountEntity.getTradeLogs().get(2).getBlncAfter());
        assertEquals(trnAmt2, accountEntity.getTradeLogs().get(2).getAmount());

        assertEquals(trnAmt1+trnAmt2, accountEntity.getTradeLogs().get(3).getBlncBefore());
        assertEquals(trnAmt1+trnAmt2+trnAmt3, accountEntity.getTradeLogs().get(3).getBlncAfter());
        assertEquals(trnAmt3, accountEntity.getTradeLogs().get(3).getAmount());

    }


    @Test
    @TestDescription("정상 출금 테스트")
    public void outAccount() throws Exception {
        //given
        AccountDto.Request accountRequest = AccountDto.Request.builder()
                .regDate(OBDateUtils.getToday())
                .taxationCode(TaxationCode.REGULAR)
                .build();
        String acno = accountService.openRegularAccount(accountRequest);
        long trnAmt1 = 1000000;
        long trnAmt2 = 30000;
        long trnAmt3 = trnAmt1 - trnAmt2;

        //when
        long result1 = accountService.inAmount(acno, trnAmt1);
        long result2 = accountService.outAmount(acno, trnAmt2);
        long result3 = accountService.outAmount(acno, trnAmt3);
        AccountEntity accountEntity = accountRepository.findByAccountNum(acno);

        //then
        assertEquals(trnAmt1, result1);
        assertEquals(trnAmt1-trnAmt2, result2);
        assertEquals(trnAmt1-trnAmt2-trnAmt3, result3);
        assertEquals(trnAmt1-trnAmt2-trnAmt3, accountEntity.getAccoBlnc());
        assertEquals(4, accountEntity.getTradeLogs().size());

        assertEquals(0, accountEntity.getTradeLogs().get(1).getBlncBefore());
        assertEquals(trnAmt1, accountEntity.getTradeLogs().get(1).getBlncAfter());
        assertEquals(trnAmt1, accountEntity.getTradeLogs().get(1).getAmount());
        assertEquals(TradeCd.IN, accountEntity.getTradeLogs().get(1).getTradeCd());

        assertEquals(trnAmt1, accountEntity.getTradeLogs().get(2).getBlncBefore());
        assertEquals(trnAmt1-trnAmt2, accountEntity.getTradeLogs().get(2).getBlncAfter());
        assertEquals(trnAmt2, accountEntity.getTradeLogs().get(2).getAmount());

        assertEquals(trnAmt1-trnAmt2, accountEntity.getTradeLogs().get(3).getBlncBefore());
        assertEquals(trnAmt1-trnAmt2-trnAmt3, accountEntity.getTradeLogs().get(3).getBlncAfter());
        assertEquals(trnAmt3, accountEntity.getTradeLogs().get(3).getAmount());
    }

    @Test(expected = BizRuntimeException.class)
    @TestDescription("잔액 초과 출금 테스트")
    public void outAccount_BizRuntimeException() throws Exception {
        //given
        AccountDto.Request accountRequest = AccountDto.Request.builder()
                .regDate(OBDateUtils.getToday())
                .taxationCode(TaxationCode.REGULAR)
                .build();
        String acno = accountService.openRegularAccount(accountRequest);
        long trnAmt1 = 1000000;
        long trnAmt2 = 1000001;

        //when
        long result1 = accountService.inAmount(acno, trnAmt1);
        long result2 = accountService.outAmount(acno, trnAmt2);

        //then
    }
}