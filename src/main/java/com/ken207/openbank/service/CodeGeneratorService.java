package com.ken207.openbank.service;

import com.ken207.openbank.domain.CodeGenerator;
import com.ken207.openbank.repository.CodeGeneratorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CodeGeneratorService {

    private final CodeGeneratorRepository codeGeneratorRepository;

    @Transactional
    public String createAccountNumber(String prefixCode) {
        CodeGenerator code = CodeGenerator.builder()
                .build();
        CodeGenerator newAcnoCode = codeGeneratorRepository.save(code);

        return newAcnoCode.createAccountNumber(prefixCode);
    }

}
