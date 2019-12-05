package com.ken207.openbank.dto.request;

import com.ken207.openbank.common.ErrorsResource;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;

public class RequestValidator {

    /**
     * 고객 정보 생성 요청값 검증
     * @param customerCreateRequest
     * @param errors
     * @return
     */
    public static ResponseEntity validate(CustomerCreateRequest customerCreateRequest, Errors errors) {
        if ( errors.hasErrors()) {
            return badRequest(errors);
        }

        if ( customerCreateRequest.getNation().length() < 3 ) {
            errors.rejectValue("nation", "wrongValue", "Nation is too short.");
        }

        return badRequest(errors);
    }

    /**
     * 오류 응답 REST API
     * @param errors
     * @return
     */
    public static ResponseEntity badRequest(Errors errors) {
        return ResponseEntity.badRequest().body(new ErrorsResource(errors) );
    }
}