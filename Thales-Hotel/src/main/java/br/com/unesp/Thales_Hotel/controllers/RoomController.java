package br.com.unesp.Thales_Hotel.controllers;

import br.com.unesp.Thales_Hotel.domain.Room;
import br.com.unesp.Thales_Hotel.responses.ApiResponse;
import br.com.unesp.Thales_Hotel.services.ReserveService;
import br.com.unesp.Thales_Hotel.services.RoomService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;

@RestController
@RequestMapping("/room")
public class RoomController {
    private final RoomService roomService;
    private final ReserveService reserveService;

    public RoomController(RoomService roomService, ReserveService reserveService) {
        this.roomService = roomService;
        this.reserveService = reserveService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<Room>>> returnAll() {
        List<Room> rooms = roomService.returnAll();
        return ResponseEntity.ok(new ApiResponse<>(200, "List of rooms", rooms));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Room>> findById(@PathVariable Long id) {
        Room room = roomService.findById(id);
        if (room != null) {
            return ResponseEntity.ok(new ApiResponse<>(200, "Room found", room));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse<>(404, "Room not found"));
        }
    }

    @GetMapping("/{id}/guests")
    public ResponseEntity<ApiResponse<Integer>> getGuests(@PathVariable Long id) {
        Room room = roomService.findById(id);
        if (room != null) {
            return ResponseEntity.ok(new ApiResponse<>(200, "Room found", room.getGuestNumber()));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse<>(404, "Room not found"));
        }
    }

    @PostMapping("/create")
    public ResponseEntity<ApiResponse<Void>> create(@RequestBody Room room) {
        boolean created = roomService.createOrUpdate(room);
        if (created) {
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new ApiResponse<>(201, "Room created successfully"));
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse<>(500, "Error creating room"));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> update(@PathVariable Long id, @RequestBody Room room) {
        room.setId(id);
        boolean updated = roomService.createOrUpdate(room);
        if (updated) {
            return ResponseEntity.ok(new ApiResponse<>(200, "Room updated successfully"));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse<>(404, "Room not found or update failed"));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Long id) {
        boolean deleted = roomService.delete(id);
        if (deleted) {
            return ResponseEntity.ok(new ApiResponse<>(200, "Room deleted successfully"));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse<>(404, "Room not found or deletion failed"));
        }
    }
    @GetMapping("/available-today")
    public ResponseEntity<ApiResponse<List<Room>>> getAvailableRoomsToday() {
        List<Room> availableRooms = roomService.findAvailableRooms(Instant.now());
        return ResponseEntity.ok(new ApiResponse<>(200, "Available rooms today", availableRooms));
    }

    @GetMapping("/freed-today")
    public ResponseEntity<ApiResponse<Integer>> getRoomsFreedToday() {
        int count = reserveService.countRoomsToBeFreedToday();
        return ResponseEntity.ok(new ApiResponse<>(200, "Number of rooms freed today", count));
    }
}
