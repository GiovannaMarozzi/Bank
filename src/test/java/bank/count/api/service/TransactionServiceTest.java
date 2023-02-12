package bank.count.api.service;

import bank.count.api.accounts.Extract;
import bank.count.api.transactions.Transactions;
import bank.count.api.transactions.TransactionsRepository;
import jakarta.validation.constraints.AssertTrue;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TransactionServiceTest {

    @Autowired
    private TransactionsRepository repository;

    @Autowired
    private AccountService accountService;

    @Test
    public void performDeposit(){
        Transactions transactions = new Transactions();
        transactions.setNome("Teste");
        transactions.setNumber(48410501L);
        transactions.setCpf("12345678974");
        transactions.setRg("258852147");
        transactions.setDate_transaction("08/02/2023");
        transactions.setValue(200.00F);
        transactions.setType("Deposito");
        repository.save(transactions);
    }

    @Test
    public void performWithdraw(){
        Transactions transactions = new Transactions();
        transactions.setNome("Teste");
        transactions.setNumber(48410501L);
        transactions.setCpf("12345678974");
        transactions.setRg("258852147");
        transactions.setDate_transaction("08/02/2023");
        transactions.setValue(200.00F);
        transactions.setType("Saque");
        repository.save(transactions);
    }

    @Test
    public void performTransfer(){
        Transactions transactions = new Transactions();
        transactions.setNome("Teste");
        transactions.setNumber(48410501L);
        transactions.setAccountTransfer(13254782L);
        transactions.setCpf("12345678974");
        transactions.setRg("258852147");
        transactions.setDate_transaction("08/02/2023");
        transactions.setValue(200.00F);
        transactions.setType("Transferencia");
        repository.save(transactions);
    }

    @Test
    public void extractByNumberAccount(){
        Long number = 12036578520L;
        accountService.extract(number);
    }

}
