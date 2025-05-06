package br.com.unesp.Thales_Hotel.services;

import br.com.unesp.Thales_Hotel.domain.User;
import br.com.unesp.Thales_Hotel.exceptions.UserException;
import br.com.unesp.Thales_Hotel.repositories.AddressJPA;
import br.com.unesp.Thales_Hotel.repositories.UserJPA;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.net.URL;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserJPA userJPA;
    private final AddressService addressService;
    private final ViaCepService viaCepService;

    public UserService(UserJPA userJPA, AddressService addressService, ViaCepService viaCepService) {
        this.userJPA = userJPA;
        this.addressService = addressService;
        this.viaCepService = viaCepService;
    }

    public List<User> returnAll(){
        return this.userJPA.findAll();
    }

    public User findByMail(String mail){
        return this.userJPA.findByMail(mail).orElse(null);
    }

    public User findByCpf(String cpf){
        return this.userJPA.findByCpf(cpf).orElse(null);
    }

    @Transactional
    public Boolean createOrUpdateUser(User user){
        try{
            addressService.createIfNotExists(viaCepService.returnAddress(user.getCep()));
            user.getUserAddress().setAddress(addressService.findByCep(user.getCep()));
            return this.userJPA.save(user).getMail() != null;
        }catch (Exception exception){
            throw new UserException("An error has occurred when creating or saving a new user: " + exception.getMessage());
        }
    }



    public Boolean verifyIfUserExists(User user){
        return this.userJPA.findByMail(user.getMail()).isPresent();
    }
}
