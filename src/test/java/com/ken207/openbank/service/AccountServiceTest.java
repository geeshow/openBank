package com.ken207.openbank.service;

import com.ken207.openbank.common.TestDescription;
import com.ken207.openbank.domain.account.AccountEntity;
import com.ken207.openbank.domain.enums.TradeCd;
import com.ken207.openbank.repository.AccountRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Optional;

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
    @TestDescription("계좌 정상 신규 테스트")
    public void openAccount() throws Exception {
        //given
        String password = "1234";

        //when
        String acno = accountService.openAccount(password);
        AccountEntity accountEntity = accountRepository.findByAcno(acno);

        //then
        assertEquals(String.valueOf(13100000 + accountEntity.getId().intValue()), accountEntity.getAcno());
        assertEquals(LocalDate.now().toString(), accountEntity.getNewDt());
        assertEquals(LocalDate.now().toString(), accountEntity.getLastIntsDt());
        assertEquals(LocalDate.now().toString(), accountEntity.getLastTrnDt());
        assertEquals(password, accountEntity.getPassword());
        assertEquals(0, accountEntity.getAccoBlnc());
    }

    @Test
    @TestDescription("비밀번호 없이 계좌 정상 신규 테스트")
    public void openAccountWithoutPassword() throws Exception {
        //given

        //when
        String acno = accountService.openAccount();
        AccountEntity accountEntity = accountRepository.findByAcno(acno);

        //then
        assertEquals(String.valueOf(13100000 + accountEntity.getId().intValue()), accountEntity.getAcno());
        assertEquals(LocalDate.now().toString(), accountEntity.getNewDt());
        assertEquals(LocalDate.now().toString(), accountEntity.getLastIntsDt());
        assertEquals(LocalDate.now().toString(), accountEntity.getLastTrnDt());
        assertEquals("", accountEntity.getPassword());
        assertEquals(0, accountEntity.getAccoBlnc());
    }


    @Test
    @TestDescription("정상 입금 테스트")
    public void inAccount() throws Exception {
        //given
        String acno = accountService.openAccount();
        long trnAmt1 = 30000;
        long trnAmt2 = 200000;
        long trnAmt3 = 1000000;

        //when
        long result1 = accountService.inAmount(acno, trnAmt1);
        long result2 = accountService.inAmount(acno, trnAmt2);
        long result3 = accountService.inAmount(acno, trnAmt3);
        AccountEntity accountEntity = accountRepository.findByAcno(acno);

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
}