package com.ken207.openbank.domain;

import com.ken207.openbank.common.OBDateUtils;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.StringUtils;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@Builder
@NoArgsConstructor
public class CodeGenerator {
    @Id @GeneratedValue
    private Long id;

    public String createAccountNumber(String prefixCode) {
        StringBuilder numberBuilder = new StringBuilder();
        numberBuilder.append(prefixCode).append(100000+this.getId());
        return numberBuilder.toString();
    }

    public String createTradeUniqueNumber() {
        StringBuilder numberBuilder = new StringBuilder();
        numberBuilder.append(OBDateUtils.getToday()).append(1000000+this.getId());
        return numberBuilder.toString();
    }
}
