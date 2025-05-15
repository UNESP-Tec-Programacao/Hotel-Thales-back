package br.com.unesp.Thales_Hotel.controllers;

import br.com.unesp.Thales_Hotel.domain.Customer;
import br.com.unesp.Thales_Hotel.services.CustomerService;
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
    public List<Customer> returnAll() {
        return this.customerService.returnAll();
    }

    @GetMapping("/{id}")
    public Customer findById(@PathVariable Long id) {
        return this.customerService.findById(id);
    }

    @PostMapping("/create")
    public String create(@RequestBody Customer customer) {
        return this.customerService.createOrUpdate(customer)
                ? "Created"
                : "An error has occurred when saving a new customer";
    }

    @PutMapping("/{id}")
    public String update(@PathVariable Long id, @RequestBody Customer customer) {
        customer.setId(id);
        return this.customerService.createOrUpdate(customer)
                ? "Updated"
                : "Error updating customer";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        return this.customerService.delete(id)
                ? "Deleted"
                : "Customer not found or couldn't be deleted";
    }
}
