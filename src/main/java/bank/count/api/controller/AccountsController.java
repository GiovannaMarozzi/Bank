package bank.count.api.controller;


import bank.count.api.accounts.Account;
import bank.count.api.accounts.ListAccounts;
import bank.count.api.accounts.ListAccountsById;
import bank.count.api.service.AccountService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/account")
public class AccountsController {

    @Autowired
    private AccountService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Account cadAccount(@RequestBody @Valid Account informations){
        return service.cadAccount(informations);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ListAccounts> listAccounts(){
        return service.listAccounts();
    }

    @GetMapping("/document={number}")
    @ResponseStatus(HttpStatus.OK)
    public List<ListAccountsById> transferById(@PathVariable Long number){
        return service.transferById(number);
    }
}
