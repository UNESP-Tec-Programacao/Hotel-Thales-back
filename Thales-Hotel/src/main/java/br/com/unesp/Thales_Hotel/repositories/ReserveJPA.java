package br.com.unesp.Thales_Hotel.repositories;

import br.com.unesp.Thales_Hotel.domain.Reserve;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReserveJPA extends JpaRepository<Reserve, Long> {
}
