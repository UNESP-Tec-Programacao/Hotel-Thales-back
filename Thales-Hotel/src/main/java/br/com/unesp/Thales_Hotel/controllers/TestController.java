package br.com.unesp.Thales_Hotel.controllers;

import br.com.unesp.Thales_Hotel.domain.User;
import br.com.unesp.Thales_Hotel.services.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/test")
public class TestController {

    private final UserService userService;

    public TestController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> list(){
        return this.userService.returnAll();
    }

}
