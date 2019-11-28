package com.ken207.openbank.customer;

import com.ken207.openbank.domain.Branch;
import com.ken207.openbank.domain.Employee;
import com.ken207.openbank.domain.account.Account;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.LAZY;

@Data
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CustomerDto {

    private String name;
    private String nation;

    public CustomerDto(String name, String nation) {
        this.name = name;
        this.nation = nation;
    }
}
