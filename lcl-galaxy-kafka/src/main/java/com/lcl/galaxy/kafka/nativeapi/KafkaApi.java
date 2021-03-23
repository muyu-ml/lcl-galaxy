package com.lcl.galaxy.kafka.nativeapi;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/kafka")
public class KafkaApi {

    @Autowired
    private KafkaTemplate<String, String> template;

    @Value("${kafka.topic}")
    private String topic;

    @GetMapping("/send")
    public String sendMsg(String message){
        template.send(topic, message);
        return "Send Ok!!!";
    }
}
