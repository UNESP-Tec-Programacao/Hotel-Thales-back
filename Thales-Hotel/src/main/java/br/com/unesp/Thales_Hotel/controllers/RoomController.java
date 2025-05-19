package br.com.unesp.Thales_Hotel.controllers;

import br.com.unesp.Thales_Hotel.domain.Room;
import br.com.unesp.Thales_Hotel.responses.ApiResponse;
import br.com.unesp.Thales_Hotel.services.RoomService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
}
