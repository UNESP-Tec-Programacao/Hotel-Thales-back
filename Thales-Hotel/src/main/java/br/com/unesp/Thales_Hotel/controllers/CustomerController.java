package br.com.unesp.Thales_Hotel.controllers;

import br.com.unesp.Thales_Hotel.domain.Customer;
import br.com.unesp.Thales_Hotel.responses.ApiResponse;
import br.com.unesp.Thales_Hotel.services.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("public/customer")
public class CustomerController {
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<Customer>>> returnAll() {
        List<Customer> customers = this.customerService.returnAll();
        return ResponseEntity.ok(new ApiResponse<>(200, "List of customers", customers));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Customer>> findById(@PathVariable Long id) {
        Customer customer = this.customerService.findById(id);
        if (customer != null) {
            return ResponseEntity.ok(new ApiResponse<>(200, "Customer found", customer));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse<>(404, "Customer not found"));
        }
    }

    @PostMapping("/create")
    public ResponseEntity<ApiResponse<Void>> create(@RequestBody Customer customer) {
        boolean created = this.customerService.createOrUpdate(customer);
        if (created) {
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new ApiResponse<>(201, "Customer created successfully"));
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse<>(500, "Error creating customer"));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> update(@PathVariable Long id, @RequestBody Customer customer) {
        customer.setId(id);
        boolean updated = this.customerService.createOrUpdate(customer);
        if (updated) {
            return ResponseEntity.ok(new ApiResponse<>(200, "Customer updated successfully"));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse<>(404, "Customer not found or update failed"));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Long id) {
        boolean deleted = this.customerService.delete(id);
        if (deleted) {
            return ResponseEntity.ok(new ApiResponse<>(200, "Customer deleted successfully"));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse<>(404, "Customer not found or deletion failed"));
        }
    }
}
