package bank.count.api.controller;


import bank.count.api.accounts.Account;
import bank.count.api.service.TransactionsService;
import bank.count.api.transactions.Transactions;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    @Autowired
    public TransactionsService service;

    @PostMapping
    public Transactions deposit(@RequestBody @Valid Transactions informations){
        return service.deposit(informations);
    }

    @GetMapping("/document={cpf}")
    public Account transferById(@PathVariable Long cpf){
        return null;
    }

}
