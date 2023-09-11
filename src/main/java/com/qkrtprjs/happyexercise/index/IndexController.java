package com.qkrtprjs.happyexercise.index;

import com.qkrtprjs.happyexercise.config.auth.LoginMember;
import com.qkrtprjs.happyexercise.config.auth.dto.SessionMember;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;
import java.security.Principal;

@Controller
public class IndexController {

    @GetMapping("/")
    private String index(Model model,@LoginMember SessionMember loginMember) {
        model.addAttribute("loginMember", loginMember);
        return "index";
    }

    @GetMapping("/auth/login")
    private String login() {
        return "login";
    }

    @GetMapping("/member/detail")
    private String detail(Model model, @LoginMember SessionMember loginMember) {
        model.addAttribute("loginMember", loginMember);
        return "member/detail";
    }
}
