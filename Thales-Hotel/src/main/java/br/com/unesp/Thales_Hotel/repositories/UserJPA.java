package br.com.unesp.Thales_Hotel.repositories;

import br.com.unesp.Thales_Hotel.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserJPA extends JpaRepository<User, UUID> {
    Optional<User> findByMail(String mail);
    Optional<List<User>> findByCpf(String cpf);
}
