package br.com.unesp.Thales_Hotel.controllers;

import br.com.unesp.Thales_Hotel.domain.User;
import br.com.unesp.Thales_Hotel.services.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("public/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> returnAll(){
        return this.userService.returnAll();
    }

    @GetMapping("/{mail}")
    public User findByMail(@PathVariable("mail") String mail){
        return this.userService.findByMail(mail);
    }

    @PostMapping
    public String createUser(@RequestBody User user){
        return this.userService.createOrUpdateUser(user) ? "Created" : "An error has occurred when saving a new user " + user.getName();
    }
}
