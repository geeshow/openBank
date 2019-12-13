package com.ken207.openbank.service;

import com.ken207.openbank.common.OBDateUtils;
import com.ken207.openbank.domain.account.AccountEntity;
import com.ken207.openbank.repository.AccountRepository;
import com.ken207.openbank.repository.CodeGeneratorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;
    private final CodeGeneratorService codeGeneratorService;

    @Transactional
    public String openAccount(String passwd) {
        String subjCd = "13";
        String acno = codeGeneratorService.createAcno(subjCd);

        AccountEntity accountEntity = AccountEntity.openAccount(acno, subjCd, OBDateUtils.getToday());
        accountEntity.setPassword(passwd);
        AccountEntity saveAccount = accountRepository.save(accountEntity);
        return saveAccount.getAcno();
    }

    @Transactional
    public String openAccount() {
        return openAccount("");
    }

    @Transactional
    public Long inAmount(String acno, long amount) {
        AccountEntity account = getAccountEntity(acno);
        account.inAmount(amount);
        return account.getAccoBlnc();
    }

    @Transactional
    public Long outAmount(String acno, long amount) {
        AccountEntity account = getAccountEntity(acno);
        account.outAmount(amount);
        return account.getAccoBlnc();
    }

    private AccountEntity getAccountEntity(String acno) {
        AccountEntity account = accountRepository.findByAcno(acno);
        if (account == null) {
            throw new EntityNotFoundException("존재하지 않는 계좌번호 입니다.");
        }
        return account;
    }

}
