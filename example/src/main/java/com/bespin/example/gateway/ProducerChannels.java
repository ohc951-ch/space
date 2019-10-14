package com.bespin.example.gateway;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface ProducerChannels {

  // MessageChannel 메소드 자체가 스트림의 이름이 된다.
 //인터페이스에 문자열상수를 아래와같이 지정하면 스트림 바인딩을 위한 참조가 된다.
 String DIRECT = "directGreetings";

 String BROADCAST = "broadcastGreetings";

 @Output(DIRECT)
 // 점대점
 //스트림은 output input의 두가지 어노테이션 제공
 //output은 다른 서비스로 메시지를 보낼 채널을 스프링 클라우드 스트림에 명시한다.
 MessageChannel directGreetings();

 //전체
 @Output(BROADCAST)
 MessageChannel broadcastGreetings();
}
