package com.ken207.openbank.customer;

import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;

@Controller
public class CustomerValidator {

    public void validate(CustomerCreateRequest customerCreateRequest, Errors errors) {
        if ( customerCreateRequest.getNation().length() < 3 ) {
            errors.rejectValue("nation", "wrongValue", "Nation is too short.");
        }
    }
}
