package com.ken207.openbank.service;

import com.ken207.openbank.common.TestDescription;
import com.ken207.openbank.domain.account.AccountEntity;
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
    AccountRepository accountRepository;

    @Test
    public void openAccount() throws Exception {
        //given
        String password = "1234";

        //when
        AccountEntity accountEntity = accountService.openAccount(password);
        //Optional<AccountEntity> byId = accountRepository.findById(accountId);
        //AccountEntity accountEntity = byId.get();

        //then
        assertEquals(String.valueOf(13100000 + accountEntity.getId().intValue()), accountEntity.getAcno());
        assertEquals(LocalDate.now().toString(), accountEntity.getNewDt());
        assertEquals(LocalDate.now().toString(), accountEntity.getLastIntsDt());
        assertEquals(LocalDate.now().toString(), accountEntity.getLastTrnDt());
        assertEquals(password, accountEntity.getPassword());
        assertEquals(0, accountEntity.getAccoBlnc());
    }
}