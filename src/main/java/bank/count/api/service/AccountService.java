package bank.count.api.service;

import bank.count.api.accounts.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.Collections;
import java.util.List;

@Service
public class AccountService {

    @Autowired
    private AccountRepository repository;

    @Autowired
    private AccountRepositoryById repositoryById;

    @Transactional
    public Account cadAccount(Account informations){
        return repository.save(informations);
    }

    @Transactional
    public List<ListAccounts> listAccounts(){
        return repository.findAll().stream().map(ListAccounts::new).toList();
    }

    @Transactional
    public List<ListAccountsById> transferById(Long number){
        return repositoryById.findAllById(Collections.singleton(number)).stream().map(ListAccountsById::new).toList();
    }

}
