package bank.count.api.service;


import bank.count.api.accounts.Extract;
import bank.count.api.accounts.ListAccounts;
import bank.count.api.transactions.Transactions;
import bank.count.api.transactions.TransactionsRepository;
import bank.count.api.user.Users;
import bank.count.api.user.UsersRepository;
import jakarta.validation.constraints.AssertTrue;
import org.h2.engine.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;


import java.util.Arrays;
import java.util.List;
import java.util.function.BooleanSupplier;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AccountServiceTest {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private TransactionsRepository transactionsRepository;

    @Test
    public void implementNewAccountInList(){
        Users user = new Users();
        user.setNome("Teste");
        user.setLogin("teste@gmail.com");
        user.setPassword("$2a$12$lyqIlgbTtf5lKuLeKrnkme/NxILTj9DYmypjNTGBVBytezBdRCytO");
        user.setCpf("12345678955");
        user.setRg("536079766");
        user.setCel("11 00000-0000");
        user.setSaldo(0.00F);

        var result = usersRepository.save(user);
        assertNotNull(result);

    }

    @Test
    public void returnIfListAccountsIsNotEmpty(){
        implementNewAccountInList();
        var list = usersRepository.findAll().stream().map(ListAccounts::new).toList();

        assertNotNull(list);
    }

    @Test
    public void returnNullIAccountIsIncorrect(){
        implementNewAccountInList(); //Informações que foram implementadas na lista
        long account_number = 123456789L;

        List<ListAccounts> result = usersRepository.findByNumber(account_number).stream().map(ListAccounts::new).toList();
        assertEquals(0, result.size());
    }

    @Test
    public void returnTrueIfCalculateDeposit(){
        float valueDeposit = 10.00F;
        float balance = 100.00F;

        Users user = new Users();

        float result = user.deposit(valueDeposit, balance);
        assertEquals(110.00F, result);
    }

    @Test
    public void returnTrueIfCalculateWithdrawAndTransfer(){
        float valueDeposit = 10.00F;
        float balance = 100.00F;

        Users user = new Users();

        float resultWithdraw = user.withdraw(valueDeposit, balance);
        assertEquals(90.00F, resultWithdraw);
    }

    @Test
    public void returnTrueIfCalculateTransfer(){
        float valueDeposit = 10.00F;
        float balance = 100.00F;

        Users user = new Users();
        float resultTransfer = user.transfer(valueDeposit, balance);

        assertEquals(90.00F, resultTransfer);
    }

    @Test
    public void returnListOfExtract(){
        Transactions transaction = new Transactions();
        transaction.setNome("Teste");
        transaction.setNumber(123456799L);
        transaction.setAccountTransfer(147852398L);
        transaction.setCpf("14785236985");
        transaction.setRg("852144756");
        transaction.setDate_transaction("11/02/2023");
        transaction.setValue(1000.00F);
        transaction.setType("Transferência");

        var saveTansaction = transactionsRepository.save(transaction);
        var listTransaction = transactionsRepository.findAll().stream().map(Extract::new).toList();

        assertNotNull(listTransaction);
    }

    @Test
    public void returnListOfExtractByNumberAccount(){
        Transactions transaction = new Transactions();
        transaction.setNome("Teste");
        transaction.setNumber(123456799L);
        transaction.setAccountTransfer(147852398L);
        transaction.setCpf("14785236985");
        transaction.setRg("852144756");
        transaction.setDate_transaction("11/02/2023");
        transaction.setValue(1000.00F);
        transaction.setType("Transferência");

        Transactions transaction2 = new Transactions();
        transaction2.setNome("Teste2");
        transaction2.setNumber(2589632581L);
        transaction2.setCpf("85741258769");
        transaction2.setRg("852367589");
        transaction2.setDate_transaction("11/02/2023");
        transaction2.setValue(1000.00F);
        transaction2.setType("Saque");

        var saveTansaction = transactionsRepository.save(transaction);
        var saveTansaction2 = transactionsRepository.save(transaction2);
        var result = transactionsRepository.findByNumber(transaction2.getNumber()).stream().map(Extract::new).toList();

        assertEquals(1, result.size());



    }
}
