package bank.count.api.service;

import bank.count.api.accounts.*;
import bank.count.api.transactions.TransactionsRepository;
import bank.count.api.user.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

@Service
public class AccountService {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private TransactionsRepository transactionsRepository;

    @Transactional
    public List<ListAccounts> listAccounts(){
        return usersRepository.findAll().stream().map(ListAccounts::new).toList();
    }

    public List<ListAccounts> listById(Long number){
        return usersRepository.findByNumber(number).stream().map(ListAccounts::new).toList();
    }

    @Transactional
    public void deposit(Float value, Long number){
        var account = usersRepository.getReferenceById(number);
        var balance = account.getSaldo();
        account.deposit(value, balance);
    }

    @Transactional
    public void withdraw(Float value, Long number){
        var account = usersRepository.getReferenceById(number);
        var balance = account.getSaldo();
        account.withdraw(value, balance);
    }

    @Transactional
    public void transfer(Float value, Long number){
        var account = usersRepository.getReferenceById(number);
        var balanceAccount = account.getSaldo();
        account.transfer(value, balanceAccount);
    }
    @Transactional
    public void transferAccount(Float value, Long accountSecond){
        var accountForTransfer = usersRepository.getReferenceById(accountSecond);
        var balanceAccountForTransfer = accountForTransfer.getSaldo();
        accountForTransfer.transferAccount(value, balanceAccountForTransfer);
    }

    public List<Extract> extract(Long document) {
        return transactionsRepository.findByNumber(document).stream().map(Extract::new).toList();

    }

    public void block(Long document) {
        var accountBlocked = usersRepository.getReferenceById(document);
        accountBlocked.block();
    }
}
