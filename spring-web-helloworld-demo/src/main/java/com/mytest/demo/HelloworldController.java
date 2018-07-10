package com.mytest.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloworldController {

    @GetMapping(value = "/hello")
    public String hello() {
        return "Hello world!";
    }

    @GetMapping(value = "/hello/{name}")
    public String hello(@PathVariable String name) {
        return "Hello " + name + "!";
    }
}
