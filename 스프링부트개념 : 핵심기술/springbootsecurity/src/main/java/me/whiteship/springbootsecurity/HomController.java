package me.whiteship.springbootsecurity;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomController {

    @GetMapping("/hello")
    public String hello() {
        return "hello";

    }

    @GetMapping("/my")
    public String my() {
        return "my";
    }
}
