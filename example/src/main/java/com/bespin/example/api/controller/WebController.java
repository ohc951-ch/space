package com.bespin.example.api.controller;

import com.bespin.example.api.service.SampleService;
import com.bespin.example.api.service.dto.UserDto;
import com.bespin.example.gateway.SampleGateway;
import com.bespin.example.response.ApiResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;
import java.util.Map;

/**
 * Project : group.example
 * Class : com.bespin.example.api.controller.WebController
 * Version : 0.0.1
 * Created by josihyeon on 2019-05-23.
 */
@RestController
@RefreshScope
@Slf4j
public class WebController {

    @Autowired
    SampleService sampleService;

    /*@Value("${test}")
    private String test;*/

    private HttpServletRequest request;
    private Map<String, Object> params;
    private final SampleGateway gateway;

    @Autowired
    WebController(SampleGateway gateway) {
        this.gateway = gateway;
    }

   /* @RequestMapping(value = "/")
    public ResponseEntity<?> reactRedirect() throws Exception {
        log.debug("test");
        return new ResponseEntity(test, HttpStatus.OK);
    }*/

    @RequestMapping(value = "/test")
    public ResponseEntity<?> getCompanyDetail(HttpServletRequest request, @RequestParam Map<String, Object> params) throws IOException {
        this.request = request;
        this.params = params;

        Object result = sampleService.getSample(params);

        this.gateway.directGreet("Direct: ");
        this.gateway.broadcastGreet("Broadcast: ");

        return new ResponseEntity(result, HttpStatus.OK);
    }

    @GetMapping(value = "/user")
    public ApiResponseDto<UserDto.ResponseList> user(HttpServletRequest request, @RequestParam Map<String, Object> params) {
        return ApiResponseDto.createOK(new UserDto.ResponseList(sampleService.findAll()));
    }

    @GetMapping(value = "/user/{userId}")
    public ApiResponseDto<UserDto.ResponseOne> findById(HttpServletRequest request, @PathVariable("userId") String userId) {
        return ApiResponseDto.createOK(new UserDto.ResponseOne(sampleService.findByUserId(userId)));
    }

    @PostMapping(value = "/user")
    public ApiResponseDto<UserDto.ResponseOne> add(@RequestBody @Valid UserDto.Create create) {
        return ApiResponseDto.createOK(new UserDto.ResponseOne(sampleService.add(create)));
    }

    @DeleteMapping(value = "/user/{id}")
    public ResponseEntity<?> delete_by_id(@RequestParam Map<String, Object> params) {
        return new ResponseEntity("", HttpStatus.OK);
    }

    @PutMapping(value = "/user/{userId}")
    public ApiResponseDto<UserDto.ResponseOne> update(@PathVariable("userId") String
                                                              userId, @Valid @RequestBody UserDto.Update update) {
        return ApiResponseDto.createOK(new UserDto.ResponseOne(sampleService.update(userId, update)));
    }
}
