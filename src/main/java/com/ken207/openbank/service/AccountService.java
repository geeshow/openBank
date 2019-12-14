package com.ken207.openbank.service;

import com.ken207.openbank.domain.account.AccountEntity;
import com.ken207.openbank.domain.enums.SubjectCode;
import com.ken207.openbank.dto.AccountDto;
import com.ken207.openbank.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;
    private final CodeGeneratorService codeGeneratorService;

    @Transactional
    public String openRegularAccount(AccountDto.Request accountRequest) {
        String accountNum = codeGeneratorService.createAcno(SubjectCode.REGULAR.getSubjectCode());

        AccountEntity accountEntity = AccountEntity.openAccount(accountNum, SubjectCode.REGULAR, accountRequest.getRegDate(), accountRequest.getTaxationCode());
        AccountEntity saveAccount = accountRepository.save(accountEntity);
        return saveAccount.getAccountNum();
    }

    @Transactional
    public void setPassword(String accountNum, String newPassword) {
        AccountEntity account = accountRepository.findByAccountNum(accountNum);
        account.setPassword(newPassword);
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
        AccountEntity account = accountRepository.findByAccountNum(acno);
        if (account == null) {
            throw new EntityNotFoundException("존재하지 않는 계좌번호 입니다.");
        }
        return account;
    }

}
