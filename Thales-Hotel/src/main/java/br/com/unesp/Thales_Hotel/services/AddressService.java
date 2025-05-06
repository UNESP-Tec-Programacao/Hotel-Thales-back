package br.com.unesp.Thales_Hotel.services;

import br.com.unesp.Thales_Hotel.domain.Address;
import br.com.unesp.Thales_Hotel.exceptions.AddressException;
import br.com.unesp.Thales_Hotel.repositories.AddressJPA;
import org.springframework.stereotype.Service;

@Service
public class AddressService {

    private final AddressJPA addressJPA;

    public AddressService(AddressJPA addressJPA) {
        this.addressJPA = addressJPA;
    }

    public Address findByCep(String cep){
        return this.addressJPA
                .findByCep(cep)
                .orElseThrow(() -> new AddressException("There was an error locating the address by cep"));
    }

    public Boolean verifyIfAddressExists(Address address){
        return this.addressJPA
                .findByCep(address.getCep())
                .isPresent();
    }

    public void createOrUpdateAddress(Address address){
        this.addressJPA.save(address);
    }

    public void createIfNotExists(Address address){
        if(!verifyIfAddressExists(address)){
            createOrUpdateAddress(address);
        }
    }




}
