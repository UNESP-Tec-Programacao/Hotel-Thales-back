package br.com.unesp.Thales_Hotel.services;

import br.com.unesp.Thales_Hotel.domain.Reserve;
import br.com.unesp.Thales_Hotel.repositories.ReserveJPA;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

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
}
