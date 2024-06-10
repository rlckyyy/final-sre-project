package postingapp.finalsreproject.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/main")
public class MainController {
    @GetMapping("/hello")
    String sayHello(){
        return "Hello World!";
    }
    @GetMapping("/bye")
    String sayBy(){
        return "Bye Bye!";
    }
}
