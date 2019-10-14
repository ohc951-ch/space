package com.bespin.example.api.advice;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

/**
 * Project : group.example
 * Class : com.bespin.example.api.advice.ErrorMessage
 * Version : 0.0.1
 * Created by josihyeon on 2019-05-23.
 */
@ToString
@Getter
public class ErrorMessage {
    private String field;
    private String message;

    @Builder
    public ErrorMessage(String field, String message) {

        this.field = field;
        this.message = message;
    }
}
