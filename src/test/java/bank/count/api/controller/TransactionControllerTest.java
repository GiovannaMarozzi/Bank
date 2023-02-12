package bank.count.api.controller;

import bank.count.api.service.AccountService;
import bank.count.api.service.TokenService;
import bank.count.api.transactions.Transactions;
import bank.count.api.transactions.TransactionsRepository;
import bank.count.api.user.Users;
import bank.count.api.user.UsersRepository;
import org.apache.tomcat.util.http.parser.Authorization;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class TransactionControllerTest {

    //Obs.: Tentei realizar os testes unitários trazendo os resultados "200 OK", porém em nenhum teste consegui obter esse resultado devido as autenticações

    @Autowired
    private AccountService service;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private TransactionsRepository repository;

    @Autowired
    private UsersRepository usersRepository;

    @Test
    public void testApifForDepositIsAutorizathionNull() throws Exception {
        Transactions transactions = new Transactions();
        transactions.setNome("Teste");
        transactions.setNumber(48410501L);
        transactions.setCpf("12345678974");
        transactions.setRg("258852147");
        transactions.setDate_transaction("08/02/2023");
        transactions.setValue(200.00F);
        transactions.setType("Deposito");
        repository.save(transactions);


        mockMvc.perform(post("/transactions/deposit")
                        .content(String.valueOf(transactions))
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(status().isForbidden());

    }

    @Test
    public void testApifForWithdrawTokenInvalid() throws Exception {
        Transactions transactions = new Transactions();
        transactions.setNome("Teste");
        transactions.setNumber(48410501L);
        transactions.setCpf("12345678974");
        transactions.setRg("258852147");
        transactions.setDate_transaction("08/02/2023");
        transactions.setValue(200.00F);
        transactions.setType("Saque");
        repository.save(transactions);

        Users user = new Users();
        user.setNome("Teste");
        user.setNumber(1478523998);
        user.setLogin("testeByName@gmail.com");
        user.setPassword("$2a$12$lyqIlgbTtf5lKuLeKrnkme/NxILTj9DYmypjNTGBVBytezBdRCytO");
        user.setCpf("12345678955");
        user.setRg("536079766");
        user.setCel("11 00000-0000");
        user.setSaldo(0.00F);

        usersRepository.save(user);

        var token = tokenService.generationToken(user);

        mockMvc.perform(post("/transactions/withdraw")
                        .content(String.valueOf(transactions))
                        .header(HttpHeaders.PROXY_AUTHORIZATION, token)
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(status().isForbidden());

    }

    @Test
    public void apiExtract() throws Exception {
        Users user = new Users();
        user.setNome("Teste");
        user.setNumber(1478523998);
        user.setLogin("testeByName@gmail.com");
        user.setPassword("$2a$12$lyqIlgbTtf5lKuLeKrnkme/NxILTj9DYmypjNTGBVBytezBdRCytO");
        user.setCpf("12345678955");
        user.setRg("536079766");
        user.setCel("11 00000-0000");
        user.setSaldo(0.00F);

        usersRepository.save(user);

        var token = tokenService.generationToken(user);

        mockMvc.perform(get("/extract/document=1478523998")
                .header(HttpHeaders.PROXY_AUTHENTICATE, token)
                        .contentType(HttpHeaders.CONTENT_TYPE)).andExpect(status().isForbidden());

    }
}
