package org.am061.spring.spring_boot_webservices;

import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;

import static java.time.LocalDateTime.now;

@Controller
public class WebSocketController {

    @Resource
    private SimpMessagingTemplate template;

    @MessageMapping("/send/message")
    public void onReceiveMessage(String message) {
        this.template.convertAndSend("/chat", now() + ": " + message);
    }

    @MessageMapping("/send/message/private/{roomId}")
    private void onReceiveMessageFromPrivateRoom(String message, @DestinationVariable String roomId) {
        this.template.convertAndSend("/chat", roomId + ":" + now() + ": " + message);
    }
}
