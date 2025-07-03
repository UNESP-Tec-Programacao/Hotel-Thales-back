package br.com.unesp.Thales_Hotel.repositories;

import br.com.unesp.Thales_Hotel.domain.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.Instant;
import java.util.List;

public interface RoomJPA extends JpaRepository<Room, Long> {

    @Query("""
    SELECT r FROM Room r
    LEFT JOIN Reserve res ON res.room = r AND :now BETWEEN res.startsAt AND res.endsAt
    WHERE res.id IS NULL
""")
    public List<Room> findAvailableRooms(@Param("now") Instant now);
}
