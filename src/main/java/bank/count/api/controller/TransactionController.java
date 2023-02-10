package bank.count.api.controller;


import bank.count.api.service.TransactionsService;
import bank.count.api.transactions.Transactions;
import bank.count.api.user.UsersRepository;
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

    @Autowired
    private UsersRepository repository;

    @PostMapping("/deposit")
    public Object deposit(@RequestBody @Valid Transactions informations){
        var exist = repository.getReferenceById(informations.getNumber());

        if(exist == null){
            System.out.println("Essa conta é inexistente");
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }else{
            service.deposit(informations);
            return new ResponseEntity<>(null, HttpStatus.OK);
        }
    }

    @PostMapping("/withdraw")
    public Object withdraw(@RequestBody @Valid Transactions informations){
        var exist = repository.findByNumber(informations.getNumber());

        if(exist == null){
            System.out.println("Essa conta é inexistente");
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }else{
            service.withdraw(informations);
            return new ResponseEntity<>(null, HttpStatus.OK);
        }
    }

    @PutMapping("/transfer")
    public ResponseEntity transfer(@RequestBody @Valid Transactions informations){
        var account = repository.getReferenceById(informations.getNumber());
        var balanceAccount = account.getSaldo();

        if(Objects.equals(informations.getAccountTransfer(), informations.getNumber()) == true){
            System.out.println("Não é possível realizar transferencias para a mesma conta");
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);

        }else if(Float.compare(informations.getValue(),balanceAccount) > 0){
            System.out.println("O valor que deseja transferir é maior do que o seu saldo");
            return new ResponseEntity(null, HttpStatus.FORBIDDEN);

        }else{
            service.transfer(informations);
            return new ResponseEntity <>(null, HttpStatus.ACCEPTED);
        }
    }



}
