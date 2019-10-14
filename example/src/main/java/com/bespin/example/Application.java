package com.bespin.example;

import com.bespin.example.gateway.ProducerChannels;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.integration.annotation.IntegrationComponentScan;

/**
 * Project : group.example
 * Class : com.bespin.example.Application
 * Version : 0.0.1
 * Created by josihyeon on 2019-05-23.
 */
@EnableJpaRepositories
@EnableJpaAuditing
@SpringBootApplication
@EnableBinding(ProducerChannels.class)
@IntegrationComponentScan
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
