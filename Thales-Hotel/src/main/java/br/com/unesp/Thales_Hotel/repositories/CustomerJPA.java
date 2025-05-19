package br.com.unesp.Thales_Hotel.repositories;

import br.com.unesp.Thales_Hotel.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerJPA extends JpaRepository<Customer,Long> {
    Optional<Customer> findByIdentify(String identify);
}
