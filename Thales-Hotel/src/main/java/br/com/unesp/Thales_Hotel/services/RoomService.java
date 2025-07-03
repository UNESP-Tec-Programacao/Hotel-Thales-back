package br.com.unesp.Thales_Hotel.services;

import br.com.unesp.Thales_Hotel.domain.Room;
import br.com.unesp.Thales_Hotel.exceptions.UserException;
import br.com.unesp.Thales_Hotel.repositories.RoomJPA;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
public class RoomService {

    private final RoomJPA roomJPA;

    public RoomService(RoomJPA roomJPA) {
        this.roomJPA = roomJPA;
    }

    public List<Room> returnAll() {
        return this.roomJPA.findAll();
    }

    public Room findById(Long id) {
        Optional<Room> roomOptional = roomJPA.findById(id);
        if (roomOptional.isEmpty()) {
            throw new UserException("Room with ID " + id + " not found.");
        }
        return roomOptional.get();
    }

    @Transactional
    public Boolean createOrUpdate(Room room) {
        return this.roomJPA.save(room).getId() != null;
    }

    @Transactional
    public Boolean delete(Long id) {
        if (!roomJPA.existsById(id)) {
            return false;
        }
        try {
            roomJPA.deleteById(id);
            return true;
        } catch (Exception exception) {
            return false;
        }
    }

    public List<Room> findAvailableRooms(Instant now) {
        return roomJPA.findAvailableRooms(now);
    }
}
