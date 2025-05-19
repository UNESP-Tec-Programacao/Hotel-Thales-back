package br.com.unesp.Thales_Hotel.controllers;

import br.com.unesp.Thales_Hotel.domain.User;
import br.com.unesp.Thales_Hotel.responses.ApiResponse;
import br.com.unesp.Thales_Hotel.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<ApiResponse<List<User>>> returnAll() {
        List<User> users = userService.returnAll();
        return ResponseEntity.ok(new ApiResponse<>(200, "List of users", users));
    }

    @GetMapping("/{mail}")
    public ResponseEntity<ApiResponse<User>> findByMail(@PathVariable("mail") String mail) {
        User user = userService.findByMail(mail);
        if (user != null) {
            return ResponseEntity.ok(new ApiResponse<>(200, "User found", user));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse<>(404, "User not found"));
        }
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Void>> createUser(@RequestBody User user) {
        boolean created = userService.createOrUpdateUser(user);
        if (created) {
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new ApiResponse<>(201, "User created successfully"));
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse<>(500, "Error creating user: " + user.getName()));
        }
    }
}
