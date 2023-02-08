package bank.count.api.service;

import bank.count.api.accounts.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

@Service
public class AccountService {

    @Autowired
    private AccountRepository repository;

    @Transactional
    public Account cadAccount(Account informations){
        return repository.save(informations);
    }

    @Transactional
    public List<ListAccounts> listAccounts(){
        return repository.findAll().stream().map(ListAccounts::new).toList();
    }

    public List<ListAccounts> listById(Long number){
        return repository.findByNumber(number).stream().map(ListAccounts::new).toList();
    }

    @Transactional
    public void deposit(Float value, Long number){
        var account = repository.getReferenceById(number);
        account.deposit(value);
    }
}
