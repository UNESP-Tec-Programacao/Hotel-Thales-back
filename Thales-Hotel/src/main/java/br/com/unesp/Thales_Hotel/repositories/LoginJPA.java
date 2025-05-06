package br.com.unesp.Thales_Hotel.repositories;

import br.com.unesp.Thales_Hotel.domain.Login;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;


public interface LoginJPA extends JpaRepository<Login, Long> {
    Optional<Login> findByLoggedUser(UUID uuid);
}
