package bank.count.api.controller;

import bank.count.api.accounts.ListAccounts;
import bank.count.api.service.AccountService;
import bank.count.api.service.TokenService;
import bank.count.api.user.Users;
import bank.count.api.user.UsersRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


import java.util.Collections;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class AccountsControllerTest {

    @Autowired
    private AccountService service;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TokenService tokenService;

    @MockBean
    private UsersRepository repository;

    @Test
    public void listAccountsNull(){
        var result = service.listAccounts();
        assertEquals(0, result.size());
    }

    @Test
    @WithMockUser
    public void returnList() throws Exception {
        Users user = new Users();
        user.setNome("Teste");
        user.setLogin("testeByName@gmail.com");
        user.setPassword("$2a$12$lyqIlgbTtf5lKuLeKrnkme/NxILTj9DYmypjNTGBVBytezBdRCytO");
        user.setCpf_or_cnpj("12345678955");
        user.setRg("536079766");
        user.setCel("11 00000-0000");
        user.setSaldo(0.00F);

        repository.save(user);

        var token = tokenService.generationToken(user);

        Mockito.when(repository.findAll()).thenReturn(List.of(user));
        this.mockMvc.perform(get("/account"))
                .andDo(print())
                .andExpect(status().isOk());
        }

    @Test
    @WithMockUser
    public void returnListByLogin() throws Exception {
        Users user = new Users();
        user.setNome("Teste");
        user.setNumber(1478523998);
        user.setLogin("testeByName@gmail.com");
        user.setPassword("$2a$12$lyqIlgbTtf5lKuLeKrnkme/NxILTj9DYmypjNTGBVBytezBdRCytO");
        user.setCpf_or_cnpj("12345678955");
        user.setRg("536079766");
        user.setCel("11 00000-0000");
        user.setSaldo(0.00F);

        repository.save(user);


        var token = tokenService.generationToken(user);
        this.mockMvc.perform(get("/account/document=478523998")
                        .header(HttpHeaders.PROXY_AUTHENTICATE, token))
                .andDo(print())
                .andExpect(status().isOk());

    }

}

