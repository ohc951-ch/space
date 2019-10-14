package com.bespin.example.api.service;

import com.bespin.example.api.persistence.domain.User;
import com.bespin.example.api.persistence.repository.UserRepository;
import com.bespin.example.api.service.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Project : group.example
 * Class : com.bespin.example.api.service.SampleService
 * Version : 0.0.1
 * Created by josihyeon on 2019-05-23.
 */
@Service
@RequiredArgsConstructor
public class SampleService {

    public Object getSample(Map<String, Object> params) throws IOException {
        List<User> resultObject = userRepository.findAll();
        return resultObject;
    }

    private final UserRepository userRepository;

    public List<UserDto.Response> findAll() {

        return userRepository.findAll().stream()
                .map(UserDto.Response::of)
                .collect(Collectors.toList());
    }

    public UserDto.Response findByUserId(String id) {
        return UserDto.Response.of(userRepository.findByUserId(id));
    }

    public UserDto.Response add(UserDto.Create create) {

        User target = create.toEntity();
        User created = userRepository.save(target);

        return UserDto.Response.of(created);
    }

    public void delete(String id) {
        userRepository.delete(userRepository.findByUserId(id));
    }

    @Transactional
    public UserDto.Response update(String id, UserDto.Update update) {
        return UserDto.Response.of(update.apply(userRepository.findByUserId(id)));
    }

}
