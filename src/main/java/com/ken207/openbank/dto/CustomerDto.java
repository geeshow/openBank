package com.ken207.openbank.dto;

import com.ken207.openbank.dto.response.BaseResponse;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

public class CustomerDto {

    @Builder
    @Getter @Setter
    public static class CustomerRequest {

        private Long customerId;
        private String name;
        @Email
        private String email;
        private String nation;
    }

    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @Getter @Setter
    public static class Response {

        @NotEmpty
        private Long id;
        @NotEmpty
        private String name;
        @NotEmpty
        @Email
        private String email;
        @NotEmpty
        private String nation;
        @NotEmpty
        private String regBranchName;
        @NotEmpty
        private String mngBranchName;
        @NotEmpty
        private String regEmployeeName;
        @NotEmpty
        private LocalDateTime regDateTime;
    }
}
