package br.com.unesp.Thales_Hotel.services;

import br.com.unesp.Thales_Hotel.domain.Login;
import br.com.unesp.Thales_Hotel.domain.User;
import br.com.unesp.Thales_Hotel.repositories.LoginJPA;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Optional;
import java.util.UUID;

@Service
public class LoginService {

    private final UserService userService;
    private final LoginJPA loginJPA;
    private User userLogando = new User();

    public LoginService(UserService userService, LoginJPA loginJPA) {
        this.userService = userService;
        this.loginJPA = loginJPA;
    }

    public User getUser(String cpf){
        return userService.findByCpf(cpf);
    }

    public boolean verifyIfUserIsLoggedByUuid(String uuidStr) {
        UUID uuid = UUID.fromString(uuidStr);
        return loginJPA.findTopByLoggedUserOrderByInitialDateDesc(uuid)
                .filter(login -> login.getExpirationDate().isAfter(Instant.now()))
                .isPresent();
    }

    public boolean verifyIfUserIsLogged(String cpf) {
        return loginJPA.findTopByLoggedUserOrderByInitialDateDesc(userLogando.getId())
                .map(login -> !login.getExpirationDate().isBefore(Instant.now()))
                .orElse(false);
    }

    public UUID login(String cpf){
        userLogando = getUser(cpf);
        if (!verifyIfUserIsLogged(cpf)){
            loginJPA.save(new Login(userLogando.getId(), Instant.now(), Instant.now().plus(5, ChronoUnit.HOURS)));
        }
        return userLogando.getId();
    }



}
