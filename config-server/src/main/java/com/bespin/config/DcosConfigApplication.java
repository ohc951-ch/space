package com.bespin.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * Project : group.config
 * Class : com.bespin.config.DcosConfigApplication
 * Version : 0.0.1
 * Created by josihyeon on 2019-05-23.
 */
@EnableConfigServer
@SpringBootApplication
public class DcosConfigApplication {

    public static void main(String[] args) {
        SpringApplication.run(DcosConfigApplication.class, args);
    }

}
