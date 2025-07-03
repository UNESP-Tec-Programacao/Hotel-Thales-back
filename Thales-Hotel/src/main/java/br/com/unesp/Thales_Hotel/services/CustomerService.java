package br.com.unesp.Thales_Hotel.services;

import br.com.unesp.Thales_Hotel.domain.Customer;
import br.com.unesp.Thales_Hotel.repositories.CustomerJPA;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    private final CustomerJPA customerJPA;

    public CustomerService(CustomerJPA customerJPA) {
        this.customerJPA = customerJPA;
    }

    public List<Customer> returnAll() {
        return this.customerJPA.findAll();
    }

    @Transactional
    public Boolean createOrUpdate(Customer customer) {
        return this.customerJPA.save(customer).getId() != null;
    }

    @Transactional
    public Boolean Customer(Customer customer) {
        return this.customerJPA.save(customer).getId() != null;
    }

    public Customer findById(Long id) {
        return customerJPA.findById(id).orElse(null);
    }

    public Customer findByIdentify(String identify) {
        return this.customerJPA.findByIdentify(identify).orElse(null);
    }

    public Boolean delete(Long id) {
        if (!customerJPA.existsById(id)) {
            return false;
        }
        try {
            customerJPA.deleteById(id);
            return true;
        } catch (Exception exception) {
            return false;
        }
    }
}
