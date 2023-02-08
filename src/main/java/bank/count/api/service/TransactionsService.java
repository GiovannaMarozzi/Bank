package bank.count.api.service;

//import bank.count.api.transactions.Transactions;
//import bank.count.api.transactions.TransactionsRepository;
import bank.count.api.transactions.Transactions;
import bank.count.api.transactions.TransactionsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TransactionsService {

    @Autowired
    private TransactionsRepository repository;

    @Transactional
    public Transactions deposit(Transactions informations){
        return repository.save(informations);
    }
}
