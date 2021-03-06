package com.ken207.openbank.dto.request;

import com.ken207.openbank.domain.Member;
import com.ken207.openbank.dto.AccountDto;
import com.ken207.openbank.dto.ProductDto;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;

public class RequestValidator {

    /**
     * 고객 정보 생성 요청값 검증
     * @param customerRequest
     * @param errors
     * @return
     */
    public static HttpStatus createCustomer(CustomerRequest customerRequest, Errors errors) {
        if ( errors.hasErrors()) {
            return HttpStatus.BAD_REQUEST;
        }

        if ( customerRequest.getNation().length() < 3 ) {
            errors.rejectValue("nation", "wrongValue", "Nation is too short.");
        }

        return HttpStatus.BAD_REQUEST;
    }

    /**
     * 고객 정보 생성 요청값 검증
     * @param branchRequest
     * @param errors
     * @param currentMember
     * @return
     */
    public static HttpStatus createBranch(BranchRequest branchRequest, Errors errors, Member currentMember) {
        if ( errors.hasErrors()) {
            return HttpStatus.BAD_REQUEST;
        }

        if ( branchRequest.getName().length() < 3 ) {
            errors.rejectValue("name", "wrongValue", "name of branch is too short.");
        }

        return HttpStatus.BAD_REQUEST;
    }

    /**
     * 계좌 생성 요청값 검증
     * @param accountRequestOpen
     * @param errors
     * @param currentMember
     * @return
     */
    public static HttpStatus createAccount(AccountDto.RequestOpen accountRequestOpen, Errors errors, Member currentMember) {
        //check data validation
        if (StringUtils.isEmpty(accountRequestOpen.getRegDate())) {
            errors.rejectValue("regDate", "wrongValue", "regDate must not be empty.");
        }

        if (errors.hasErrors()) {
            return HttpStatus.BAD_REQUEST;
        }

        return HttpStatus.CREATED;
    }

    public static HttpStatus createProduct(ProductDto.Create createProductDto, Errors errors, Member currentMember) {

        //check data validation
        if (errors.hasErrors()) {
            return HttpStatus.BAD_REQUEST;
        }

        return HttpStatus.CREATED;
    }
}
