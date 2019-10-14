package com.bespin.example.api.service;

import com.bespin.example.api.persistence.repository.UserRepository;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Project : group.example
 * Class : com.bespin.example.api.service.SampleServiceTest
 * Version : 0.0.1
 * Created by josihyeon on 2019-05-23.
 */
public class SampleServiceTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Autowired
    private SampleService sampleService;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void getSample() {
    }

    @Test
    public void findAll() {
    }

    @Test
    public void findByUserId() {
    }

    @Test
    public void add() {
    }

    @Test
    public void delete() {
    }

    @Test
    public void update() {
    }
}