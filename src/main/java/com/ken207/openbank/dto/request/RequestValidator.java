package com.ken207.openbank.dto.request;

import com.ken207.openbank.common.ErrorsResource;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;

public class RequestValidator {

    /**
     * 고객 정보 생성 요청값 검증
     * @param customerRequest
     * @param errors
     * @return
     */
    public static ResponseEntity validate(CustomerRequest customerRequest, Errors errors) {
        if ( errors.hasErrors()) {
            return badRequest(errors);
        }

        if ( customerRequest.getNation().length() < 3 ) {
            errors.rejectValue("nation", "wrongValue", "Nation is too short.");
        }

        return badRequest(errors);
    }

    /**
     * 지점 수정 요청값 검증
     * @param BranchRequest
     * @param errors
     * @return
     */
    public static ResponseEntity validate(BranchRequest BranchRequest, Errors errors) {
        if ( errors.hasErrors()) {
            return badRequest(errors);
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
