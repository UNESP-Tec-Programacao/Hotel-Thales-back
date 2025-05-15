package br.com.unesp.Thales_Hotel.repositories;

import br.com.unesp.Thales_Hotel.domain.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomJPA extends JpaRepository<Room, Long> {
}
