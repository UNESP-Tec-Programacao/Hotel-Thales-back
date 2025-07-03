package br.com.unesp.Thales_Hotel.services;

import br.com.unesp.Thales_Hotel.domain.Reserve;
import br.com.unesp.Thales_Hotel.repositories.ReserveJPA;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;

@Service
public class ReserveService {

    private final ReserveJPA reserveJPA;

    public ReserveService(ReserveJPA reserveJPA) {
        this.reserveJPA = reserveJPA;
    }

    public List<Reserve> returnAll() {
        return this.reserveJPA.findAll();
    }
    public Reserve findById(Long id) {
        return reserveJPA.findById(id).orElse(null);
    }


    @Transactional
    public Boolean createOrUpdate(Reserve room) {
        return this.reserveJPA.save(room).getId() != null;
    }

    @Transactional
    public Boolean delete(Long id) {
        if (!reserveJPA.existsById(id)) {
            return false;
        }
        try {
            reserveJPA.deleteById(id);
            return true;
        } catch (Exception exception) {
            return false;
        }
    }


    public List<Reserve> reserveds(){
        return reserveJPA.findReservesWithEndsBeforeToday(
                LocalDate.now()
                .atStartOfDay(ZoneId.systemDefault()) // vira ZonedDateTime com hora 00:00
                .toInstant());
    }

    public int countRoomsToBeFreedToday() {
        // Define início do dia e fim do dia no fuso local
        LocalDate today = LocalDate.now(ZoneId.systemDefault());
        Instant startOfDay = today.atStartOfDay(ZoneId.systemDefault()).toInstant();
        Instant endOfDay = today.plusDays(1).atStartOfDay(ZoneId.systemDefault()).toInstant().minusMillis(1);

        List<Long> roomIds = reserveJPA.findRoomIdsWithReservationsEndingBetween(startOfDay, endOfDay);
        // Retorna o número de quartos liberados hoje (sem repetir)
        return (int) roomIds.stream().distinct().count();
    }
}
