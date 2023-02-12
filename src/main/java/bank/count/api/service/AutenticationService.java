package bank.count.api.service;

import bank.count.api.user.Users;
import bank.count.api.user.UsersRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMessage;
import org.springframework.http.HttpMethod;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.net.http.HttpRequest;

@Service
public class AutenticationService implements UserDetailsService {

    @Autowired
    private UsersRepository repository;

    @Autowired
    private AccountService accountService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findByLogin(username);
    }

    public Users save(@Valid Users informations) {
        var exist = repository.findByLogin(informations.getLogin());
        if(exist == null){
            return repository.save(informations);
        }else{
            return null;
        }
    }

    @Transactional
    public void block(Long document) {
        accountService.block(document);
    }
}
