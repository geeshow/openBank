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
    private String prefixCode; //접두코드
    private String suffixCode; //접미코드

    public String getAcno() {
        StringBuilder acnoBuilder = new StringBuilder();
        acnoBuilder.append(prefixCode).append(100000+this.getId());
        return acnoBuilder.toString();
    }
}
