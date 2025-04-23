package br.com.unesp.Thales_Hotel.repositories;

import br.com.unesp.Thales_Hotel.domain.Address;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AddressJPA extends JpaRepository<Address, Long> {
    Optional<Address> findByCep(String cep);
}
