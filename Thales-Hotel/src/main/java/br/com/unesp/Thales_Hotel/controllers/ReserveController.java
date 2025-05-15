package br.com.unesp.Thales_Hotel.controllers;

import br.com.unesp.Thales_Hotel.domain.Reserve;
import br.com.unesp.Thales_Hotel.services.ReserveService;
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
    public List<Reserve> returnAll() {
        return this.reserveService.returnAll();
    }

    @GetMapping("/{id}")
    public Reserve findById(@PathVariable Long id) {
        return this.reserveService.findById(id);
    }

    @PostMapping("/create")
    public String create(@RequestBody Reserve room) {
        return this.reserveService.createOrUpdate(room)
                ? "Created"
                : "An error has occurred when saving a new room";
    }

    @PutMapping("/{id}")
    public String update(@PathVariable Long id, @RequestBody Reserve room) {
        room.setId(id);
        return this.reserveService.createOrUpdate(room)
                ? "Updated"
                : "Error updating room";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        return this.reserveService.delete(id)
                ? "Deleted"
                : "Reserve not found or couldn't be deleted";
    }
}
