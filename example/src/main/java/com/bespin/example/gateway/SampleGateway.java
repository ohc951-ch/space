package com.bespin.example.gateway;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;

/**
 * Project : group
 * Class : com.bespin.example.gateway.gateway
 * Version :
 * Created by josihyeon on 2019-06-21.
 */
@MessagingGateway
public interface SampleGateway {

    @Gateway(requestChannel = ProducerChannels.BROADCAST)
    void broadcastGreet(String msg);

    @Gateway(requestChannel = ProducerChannels.DIRECT)
    void directGreet(String msg);
}