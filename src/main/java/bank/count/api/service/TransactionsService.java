package bank.count.api.service;

//import bank.count.api.transactions.Transactions;
//import bank.count.api.transactions.TransactionsRepository;
import bank.count.api.accounts.Account;
import bank.count.api.accounts.ListAccounts;
import bank.count.api.transactions.Transactions;
import bank.count.api.transactions.TransactionsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;

@Service
public class TransactionsService {

    @Autowired
    private TransactionsRepository repository;

    @Autowired
    private AccountService accountService;

    @Transactional
    public Transactions deposit(Transactions informations){
        accountService.deposit(informations.getValue(), informations.getNumber());
        return repository.save(informations);
    }

    @Transactional
    public Transactions withdraw(Transactions informations){
        accountService.withdraw(informations.getValue(), informations.getNumber());
        return repository.save(informations);
    }
}
