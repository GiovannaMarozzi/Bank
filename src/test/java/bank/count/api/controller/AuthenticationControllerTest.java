package bank.count.api.controller;

import bank.count.api.service.AutenticationService;
import bank.count.api.user.Users;
import bank.count.api.user.UsersRepository;
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
import org.springframework.test.web.servlet.ResultMatcher;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;


import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class AuthenticationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private AutenticationService service;

    @MockBean
    private UsersRepository repository;

    @Test
    public void cadNewUsers() throws Exception {

        JSONObject json = new JSONObject();
        json.put("nome", "Teste");
        json.put("login", "testeByName@gmail.com");
        json.put("Password", "$2a$12$lyqIlgbTtf5lKuLeKrnkme/NxILTj9DYmypjNTGBVBytezBdRCytO");
        json.put("cpf_or_cnpj", "12345678955");
        json.put("type_document", "cpf");
        json.put("rg", "536079766");
        json.put("cel", "11 00000-0000");
        json.put("saldo", 0.00);

        mockMvc.perform(post("/autentication/cad")
                .content(String.valueOf(json))
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    public void authenticationPasswordIncorrect() throws Exception {
        Users user = new Users();
        user.setNome("Teste");
        user.setLogin("testeByName@gmail.com");
        user.setPassword("$2a$12$lyqIlgbTtf5lKuLeKrnkme/NxILTj9DYmypjNTGBVBytezBdRCytO");
        user.setCpf_or_cnpj("12345678955");
        user.setRg("536079766");
        user.setCel("11 00000-0000");
        user.setSaldo(0.00F);

        repository.save(user);

        JSONObject json = new JSONObject();
        json.put("login", "testeByName@gmail.com");
        json.put("password", "12345678"); //Password corrective = 123456

        mockMvc.perform(post("/autentication/auth")
                .content(String.valueOf(json))
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(status().isForbidden());

    }

    @Test
    public void apiBlock() throws Exception {
        Users user = new Users();
        user.setNome("Teste");
        user.setLogin("testeByName@gmail.com");
        user.setPassword("$2a$12$lyqIlgbTtf5lKuLeKrnkme/NxILTj9DYmypjNTGBVBytezBdRCytO");
        user.setCpf_or_cnpj("12345678955");
        user.setRg("536079766");
        user.setCel("11 00000-0000");
        user.setSaldo(0.00F);

        repository.save(user);

        mockMvc.perform(put("/autentication/block={document}", 12345678955L)
                        .content(HttpHeaders.CONTENT_TYPE))
                        .andExpect(status().isNotAcceptable());

    }
}
