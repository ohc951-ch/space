package com.bespin.example.api.controller.document;

import com.bespin.example.response.ApiResponseCode;
import com.bespin.example.response.ApiResponseDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Project : group.example
 * Class : com.bespin.example.api.controller.document.EnumViewController
 * Version : 0.0.1
 * Created by josihyeon on 2019-05-23.
 */
@RestController
public class EnumViewController {

    @GetMapping("/docs")
    public ApiResponseDto<Docs> findAll() {

        Map<String, String> apiResponseCodes = Arrays.stream(ApiResponseCode.values())
                .collect(Collectors.toMap(ApiResponseCode::name, ApiResponseCode::getText));

        return ApiResponseDto.createOK(
                Docs.builder().apiResponseCodes(apiResponseCodes)
                        .build()
        );
    }
}
