package bank.count.api.service;

import bank.count.api.accounts.Extract;
import bank.count.api.transactions.Transactions;
import bank.count.api.transactions.TransactionsRepository;
import bank.count.api.user.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TransactionsService {

    @Autowired
    private TransactionsRepository repository;

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private AccountService accountService;

    @Transactional
    public Transactions deposit(Transactions informations){
        accountService.deposit(informations.getValue(), informations.getNumber());
        return repository.save(informations);
    }

    @Transactional
    public Object withdraw(Transactions informations){
        var account = usersRepository.getReferenceById(informations.getNumber());
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
        accountService.transfer(informations.getValue(), informations.getNumber());
        accountService.transferAccount(informations.getValue(), informations.getAccountTransfer());
        return repository.save(informations);

    }

    @Transactional
    public List<Extract> extract(Long document) {
        return accountService.extract(document);
    }
}
