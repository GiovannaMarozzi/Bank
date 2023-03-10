package bank.count.api.controller;


import bank.count.api.accounts.ListAccounts;
import bank.count.api.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/account")
public class AccountsController {

    @Autowired
    private AccountService service;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ListAccounts> listAccounts(){
        return service.listAccounts();
    }

    @GetMapping("/document={number}")
    @ResponseStatus(HttpStatus.OK)
    public List<ListAccounts> listById(@PathVariable Long number){
        return service.listById(number);
    }
}
