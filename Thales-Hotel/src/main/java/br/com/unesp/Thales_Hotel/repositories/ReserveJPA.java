package br.com.unesp.Thales_Hotel.repositories;

import br.com.unesp.Thales_Hotel.domain.Reserve;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.time.Instant;

public interface ReserveJPA extends JpaRepository<Reserve, Long> {


    @Query("SELECT r.room.id FROM Reserve r WHERE r.endsAt BETWEEN :startOfDay AND :endOfDay")
    List<Long> findRoomIdsWithReservationsEndingBetween(
            @Param("startOfDay") Instant startOfDay,
            @Param("endOfDay") Instant endOfDay
    );

    @Query("SELECT r FROM Reserve r WHERE r.endsAt > :today")
    List<Reserve> findReservesWithEndsBeforeToday(
            @Param("today")Instant today
            );
}
