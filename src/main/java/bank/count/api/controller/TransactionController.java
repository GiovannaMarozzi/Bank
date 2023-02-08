package bank.count.api.controller;


import bank.count.api.service.TransactionsService;
import bank.count.api.transactions.Transactions;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    @Autowired
    public TransactionsService service;

    @PostMapping
    public Transactions deposit(@RequestBody @Valid Transactions informations){
        return service.deposit(informations);
    }

}
