package com.bespin.example.exception;

/**
 * Project : group.example
 * Class : com.bespin.example.exception.NotFoundException
 * Version : 0.0.1
 * Created by josihyeon on 2019-05-23.
 */
public class NotFoundException extends RuntimeException {
    public NotFoundException() {
        super("리소스를 찾지 못했습니다.");
    }
}
