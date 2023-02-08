package bank.count.api.controller;


import bank.count.api.accounts.Account;
import jakarta.validation.Valid;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/account")
public class AccountsController {

    @PostMapping
    @Transactional
    public void cadAccount(@RequestBody @Valid Account informations){
        System.out.println("Chegou aqui");
        System.out.println(informations);
    }

}
