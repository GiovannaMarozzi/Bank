package bank.count.api.service;

import bank.count.api.accounts.Account;
import bank.count.api.accounts.AccountRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class AccountService {

    @Autowired
    private AccountRepository repository;

    @Transactional
    public Account cadAccount(Account informations){
        return repository.save(informations);
    }
}
