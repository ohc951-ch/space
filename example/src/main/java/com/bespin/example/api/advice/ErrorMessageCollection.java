package com.bespin.example.api.advice;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import lombok.Getter;
import lombok.ToString;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

/**
 * Project : group.example
 * Class : com.bespin.example.api.advice.ErrorMessageCollection
 * Version : 0.0.1
 * Created by josihyeon on 2019-05-23.
 */
@ToString
@Getter
public class ErrorMessageCollection {

    private List<ErrorMessage> errors = new ArrayList<>();

    ErrorMessageCollection(List<FieldError> fieldErrors, List<ObjectError> globalErrors) {
        errors.addAll(getFieldErrors(fieldErrors));
        errors.addAll(getObjectErrors(globalErrors));
    }

    public ErrorMessageCollection(List<ErrorMessage> errors) {
        this.errors = errors;
    }

    private List<ErrorMessage> getFieldErrors(List<FieldError> fieldErrors) {
        return fieldErrors.stream()
                .map(f -> ErrorMessage.builder()
                        .field(f.getField())
                        .message(f.getDefaultMessage())
                        .build())
                .collect(Collectors.toList());
    }

    private List<ErrorMessage> getObjectErrors(List<ObjectError> globalErrors) {
        return globalErrors.stream()
                .map(o -> ErrorMessage.builder()
                        .field(o.getObjectName())
                        .message(o.getDefaultMessage())
                        .build())
                .collect(Collectors.toList());
    }
}
