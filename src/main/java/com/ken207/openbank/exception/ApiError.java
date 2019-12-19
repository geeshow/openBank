package com.ken207.openbank.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter
public class ApiError implements Serializable {
    private String message;
    private HttpStatus status;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private final List<Detail> details = new ArrayList<>();

    public void addDetail(String target, String message) {
        details.add(new Detail(target, message));
    }

    @Getter
    @Setter
    @AllArgsConstructor
    public class Detail implements Serializable {

        private static final long serialVersionUID = -8119817744873562082L;
        private String target;
        private String message;

    }
}
