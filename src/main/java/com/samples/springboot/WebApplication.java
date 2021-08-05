package com.samples.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class WebApplication {
    public static void main(String[] args) {
        SpringApplication.run(WebApplication.class, args);
    }

    @RequestMapping("/")
    public @ResponseBody String root() {
        return "<body>" +
                "<p><a href=\"send\">/send?message=***</a>: Send message ***</p>" +
                "<p><a href=\"get\">/get</a>: Get all messages from queue</p>" +
                "</body>";
    }
}