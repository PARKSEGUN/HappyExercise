package com.qkrtprjs.happyexercise.index;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @GetMapping("/")
    private String index() {
        return "index";
    }
    @GetMapping("/auth/login")
    private String login() {
        return "login";
    }
}
