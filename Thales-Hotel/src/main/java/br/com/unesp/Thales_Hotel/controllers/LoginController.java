package br.com.unesp.Thales_Hotel.controllers;

import br.com.unesp.Thales_Hotel.responses.ApiResponse;
import br.com.unesp.Thales_Hotel.services.LoginService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("public/login")
public class LoginController {

    private final LoginService loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @GetMapping("/{cpf}")
    public ResponseEntity<ApiResponse<UUID>> login(@PathVariable("cpf") String cpf) {
        UUID uuid = loginService.login(cpf);
        if (uuid != null) {
            return ResponseEntity.ok(new ApiResponse<>(200, "Login successful", uuid));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new ApiResponse<>(401, "Login failed: user not found or invalid CPF"));
        }
    }

    @GetMapping("/verify/{uuid}")
    public ResponseEntity<ApiResponse<Boolean>> userLogged(@PathVariable("uuid") String uuid) {
        boolean isLogged = loginService.verifyIfUserIsLoggedByUuid(uuid);
        return ResponseEntity.ok(new ApiResponse<>(200, "Login verification result", isLogged));
    }
}
