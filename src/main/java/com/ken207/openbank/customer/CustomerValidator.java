package com.ken207.openbank.customer;

import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;

@Controller
public class CustomerValidator {

    public void validate(CustomerDto customerDto, Errors errors) {
        if ( !"KOR".equals(customerDto.getNation()) ) {
            errors.rejectValue("nation", "wrongValue", "Nation is not KOR.");
        }
    }
}
