package com.ken207.openbank.domain;

import lombok.Builder;
import lombok.Getter;
import org.springframework.util.StringUtils;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@Builder
public class CodeGenerator {
    @Id @GeneratedValue
    private Long id;

    public String createAccountNumber(String prefixCode) {
        StringBuilder numberBuilder = new StringBuilder();
        numberBuilder.append(prefixCode).append(100000+this.getId());
        return numberBuilder.toString();
    }

}
