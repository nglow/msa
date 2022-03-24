package com.example.msa.firstservice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/first-service")
@Slf4j
public class HomeController {

    private final Environment environment;

    public HomeController(Environment environment) {
        this.environment = environment;
    }

    @GetMapping("/hello")
    public String hello() {
        return "Hello first service";
    }

    @GetMapping("/message")
    public String message(@RequestHeader("first-request") String header) {
        log.info("header: {}", header);
        return "Hello world in first service";
    }

    @GetMapping("/check")
    public String check(HttpServletRequest request) {
        log.info("Server port={}", request.getServerPort());
        return String.format("Hi, there. This is a message from first service on port %s", environment.getProperty("local.server.port"));
    }
}
