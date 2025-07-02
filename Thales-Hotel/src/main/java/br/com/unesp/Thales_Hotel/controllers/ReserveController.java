package br.com.unesp.Thales_Hotel.controllers;

import br.com.unesp.Thales_Hotel.domain.Reserve;
import br.com.unesp.Thales_Hotel.responses.ApiResponse;
import br.com.unesp.Thales_Hotel.services.ReserveService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("public/reserve")
public class ReserveController {
    private final ReserveService reserveService;

    public ReserveController(ReserveService reserveService) {
        this.reserveService = reserveService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<Reserve>>> returnAll() {
        List<Reserve> reserves = reserveService.returnAll();
        return ResponseEntity.ok(new ApiResponse<>(200, "List of reserves", reserves));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Reserve>> findById(@PathVariable Long id) {
        Reserve reserve = reserveService.findById(id);
        if (reserve != null) {
            return ResponseEntity.ok(new ApiResponse<>(200, "Reserve found", reserve));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse<>(404, "Reserve not found"));
        }
    }

    @PostMapping("/create")
    public ResponseEntity<ApiResponse<Void>> create(@RequestBody Reserve reserve) {
        boolean created = reserveService.createOrUpdate(reserve);
        if (created) {
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new ApiResponse<>(201, "Reserve created successfully"));
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse<>(500, "Error creating reserve"));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> update(@PathVariable Long id, @RequestBody Reserve reserve) {
        reserve.setId(id);
        boolean updated = reserveService.createOrUpdate(reserve);
        if (updated) {
            return ResponseEntity.ok(new ApiResponse<>(200, "Reserve updated successfully"));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse<>(404, "Reserve not found or update failed"));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Long id) {
        boolean deleted = reserveService.delete(id);
        if (deleted) {
            return ResponseEntity.ok(new ApiResponse<>(200, "Reserve deleted successfully"));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse<>(404, "Reserve not found or deletion failed"));
        }
    }

    @GetMapping("/rooms-freed-today")
    public ResponseEntity<ApiResponse<Integer>> getRoomsFreedToday() {
        int count = reserveService.countRoomsToBeFreedToday();
        return ResponseEntity.ok(new ApiResponse<>(200, "Number of rooms freed today", count));
    }
}
