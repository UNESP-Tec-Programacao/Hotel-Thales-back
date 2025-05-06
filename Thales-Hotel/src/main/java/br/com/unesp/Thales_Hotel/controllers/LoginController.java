package br.com.unesp.Thales_Hotel.controllers;

import br.com.unesp.Thales_Hotel.domain.User;
import br.com.unesp.Thales_Hotel.services.LoginService;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("public/login")
public class LoginController {

    private final LoginService loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @GetMapping("{cpf}")
    public UUID login(@PathVariable("cpf") String cpf){
        return loginService.login(cpf);
    }

    @GetMapping("/verify/{uuid}")
    public boolean userLogged(@PathVariable("uuid") String uuid){
        return loginService.verifyIfUserIsLoggedByUuid(uuid);
    }
}
