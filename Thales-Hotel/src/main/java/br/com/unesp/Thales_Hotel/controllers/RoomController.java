package br.com.unesp.Thales_Hotel.controllers;

import br.com.unesp.Thales_Hotel.domain.Room;
import br.com.unesp.Thales_Hotel.services.RoomService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("public/room")
public class RoomController {
    private final RoomService roomService;

    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @GetMapping
    public List<Room> returnAll() {
        return this.roomService.returnAll();
    }

    @GetMapping("/{id}")
    public Room findById(@PathVariable Long id) {
        return this.roomService.findById(id);
    }

    @PostMapping("/create")
    public String create(@RequestBody Room room) {
        return this.roomService.createOrUpdate(room)
                ? "Created"
                : "An error has occurred when saving a new room";
    }

    @PutMapping("/{id}")
    public String update(@PathVariable Long id, @RequestBody Room room) {
        room.setId(id);
        return this.roomService.createOrUpdate(room)
                ? "Updated"
                : "Error updating room";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        return this.roomService.delete(id)
                ? "Deleted"
                : "Room not found or couldn't be deleted";
    }
}
