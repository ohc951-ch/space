package com.bespin.example.api.controller.document;

import lombok.Builder;
import lombok.Getter;

import java.util.Map;

/**
 * Project : group.example
 * Class : com.bespin.example.api.controller.document.Docs
 * Version : 0.0.1
 * Created by josihyeon on 2019-05-23.
 */
@Getter
class Docs {

    Map<String, String> apiResponseCodes;

    @Builder
    private Docs(Map<String, String> apiResponseCodes) {
        this.apiResponseCodes = apiResponseCodes;
    }
}
