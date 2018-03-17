package org.am061.spring.spring_boot_webservices;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.time.LocalDate;

@Controller
public class WebSocketController {

    @Resource
    private SimpMessagingTemplate template;

    @MessageMapping("/send/message")
    public void onReceiveMessage(String message) {
        this.template.convertAndSend("/chat", LocalDate.now() + ": " + message);
    }
}
