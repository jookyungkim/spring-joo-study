package me.whiteship.demospringhateos;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SampleController {

    @GetMapping("/hello")
    public Hello hollo() {
        Hello hello = new Hello();
        hello.setPrefix("Hey,");
        hello.setName("keesun");

        return hello;
    }
}
