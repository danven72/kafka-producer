package it.proactivity.training.kafkaproducer.controller;

import it.proactivity.training.kafkaproducer.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/kafka")
public class KafkaProducer {

    @Autowired
    private KafkaTemplate<String, String> stringKafkaTemplate;

    @Autowired
    private KafkaTemplate<String, User> userKafkaTemplate;


    @GetMapping("/publish/{message}")
    public String publishMessage(@PathVariable String message) {
        stringKafkaTemplate.send("springboot-kafka", message);

        return "Message [" + message + "] published successfully on Kafka";
    }

    //curl -v -H "Content-type: application/json" -d '{"name":"Mario", "surname": "Rossi", "age": 40}' http://localhost:8081/kafka/publish/user/
    @PostMapping("/publish/user")
    public User publishUser(@RequestBody User user) {
        userKafkaTemplate.send("springboot-kafka-json2", user);
        System.out.println("User " + user + " published successfully on Kafka");
        return user;
    }

}
