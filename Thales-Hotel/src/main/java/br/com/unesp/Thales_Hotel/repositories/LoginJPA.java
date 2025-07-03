package br.com.unesp.Thales_Hotel.repositories;

import br.com.unesp.Thales_Hotel.domain.Login;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.swing.text.html.Option;
import java.util.Optional;
import java.util.UUID;


public interface LoginJPA extends JpaRepository<Login, Long> {
    Optional<Login> findTopByLoggedUserOrderByInitialDateDesc(UUID uuid);
}
