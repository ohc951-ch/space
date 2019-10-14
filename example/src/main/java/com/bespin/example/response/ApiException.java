package com.bespin.example.response;

import lombok.Getter;

/**
 * Project : group.example
 * Class : com.bespin.example.response.ApiException
 * Version : 0.0.1
 * Created by josihyeon on 2019-05-23.
 */
@Getter
public class ApiException extends RuntimeException {

    private ApiResponseCode status;
    private String message;

    public ApiException(ApiResponseCode status, Exception e) {
        super(e);
        this.status = status;
        this.message = status.getMessage();
    }

    public ApiException(ApiResponseCode status, String message, Exception e) {
        super(e);
        this.status = status;
        this.message = message;
    }

    public ApiException(ApiResponseCode status) {
        this.status = status;
        this.message = status.getMessage();
    }

    public ApiException(ApiResponseCode status, String message) {
        this.status = status;
        this.message = message;
    }
}
