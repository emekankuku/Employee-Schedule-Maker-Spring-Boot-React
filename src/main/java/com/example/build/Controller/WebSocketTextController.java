package com.example.build.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.build.dto.NotificationDto;

@RestController
public class WebSocketTextController {

    @Autowired
    SimpMessagingTemplate template;

    // @PostMapping("/send")
    // public ResponseEntity<Void> sendMessage(@RequestBody NotificationDto notificationDto) {
    //     template.convertAndSend("/topic/message", notificationDto);
    //     return new ResponseEntity<>(HttpStatus.OK);
    // }

    // @MessageMapping("/sendMessage")
    // public void receiveMessage(@Payload NotificationDto notificationDto) {
    //     // receive message from client
    // }

    @MessageMapping("/application")
    @SendTo("/all/message")
    public Message send(final Message message) throws Exception{
        return message;
    }
}