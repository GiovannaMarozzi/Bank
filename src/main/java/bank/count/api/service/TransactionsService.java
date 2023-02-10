package bank.count.api.service;

import bank.count.api.transactions.Transactions;
import bank.count.api.transactions.TransactionsRepository;
import bank.count.api.user.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TransactionsService {

    @Autowired
    private TransactionsRepository repository;

    @Autowired
    private UsersRepository accountRepository;

    @Autowired
    private AccountService accountService;

    @Transactional
    public Transactions deposit(Transactions informations){
        accountService.deposit(informations.getValue(), informations.getNumber());
        return repository.save(informations);
    }

    @Transactional
    public Object withdraw(Transactions informations){
        var account = accountRepository.getReferenceById(informations.getNumber());
        var balance = account.getSaldo();

        if(Float.compare(informations.getValue(), balance) > 0){
            System.out.println("O valor que deseja sacar é maior do que o seu saldo");
            return new ResponseEntity(null, HttpStatus.FORBIDDEN);
        }else{
            accountService.withdraw(informations.getValue(), informations.getNumber());
            return repository.save(informations);
        }
    }

    @Transactional
    public Object transfer(Transactions informations){
//        var account = accountRepository.getReferenceById(informations.getNumber());
//        var balanceAccount = account.getSaldo();
//
        accountService.transfer(informations.getValue(), informations.getNumber());
        accountService.transferAccount(informations.getValue(), informations.getAccountTransfer());
        return repository.save(informations);

    }
}
