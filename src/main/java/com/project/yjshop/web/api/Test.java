package com.project.yjshop.web.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Test {

    @GetMapping("abc")
    public String abc() {
        return "abc";
    }
}
