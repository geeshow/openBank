package com.ken207.openbank.dto.request.dto.response;

import com.ken207.openbank.domain.BaseEntity;

public interface BaseResponse {
    public Long getId();

    public static BaseEntity<?> transform(BaseEntity<?> baseEntity) {
        return null;
    }
}
