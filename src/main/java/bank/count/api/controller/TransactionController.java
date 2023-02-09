package bank.count.api.controller;


import bank.count.api.service.TransactionsService;
import bank.count.api.transactions.Transactions;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    @Autowired
    public TransactionsService service;
    @PostMapping("/deposit")
    public Transactions deposit(@RequestBody @Valid Transactions informations){
        return service.deposit(informations);
    }

    @PostMapping("/withdraw")
    public Transactions withdraw(@RequestBody @Valid Transactions informations){
        return service.withdraw(informations);
    }

    @PutMapping("/transfer")
    public Object transfer(@RequestBody @Valid Transactions informations){
        if(Objects.equals(informations.getAccountTransfer(), informations.getNumber()) == true){
            System.out.println("Não é possível fazer transferencias para a mesma conta");
            return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
        }else{
            return service.transfer(informations);
        }
    }



}
