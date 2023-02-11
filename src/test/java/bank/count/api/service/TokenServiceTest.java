package bank.count.api.service;

import bank.count.api.user.Users;
import bank.count.api.user.UsersRepository;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.h2.command.Token;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;


import java.time.LocalDateTime;
import java.time.ZoneOffset;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TokenServiceTest {

    @Autowired
    private UsersRepository repository;

    @Value("${api.security.token.secret}")
    private String secret;

    @Autowired
    private TokenService tokenService;

    @Test
    public void generateToken(){
        Users user = new Users();
        user.setNome("Teste");
        user.setLogin("teste@gmail.com");
        user.setPassword("$2a$12$lyqIlgbTtf5lKuLeKrnkme/NxILTj9DYmypjNTGBVBytezBdRCytO");
        user.setCpf("12345678955");
        user.setRg("536079766");
        user.setCel("11 00000-0000");
        user.setSaldo(0.00F);
        repository.save(user);

        var token = tokenService.generationToken(user);
        assertNotNull(token);
    }

    @Test
    public void getSubjectLogin(){
        Users user = new Users();
        user.setNome("Teste");
        user.setLogin("teste@gmail.com");
        user.setPassword("$2a$12$lyqIlgbTtf5lKuLeKrnkme/NxILTj9DYmypjNTGBVBytezBdRCytO");
        user.setCpf("12345678955");
        user.setRg("536079766");
        user.setCel("11 00000-0000");
        user.setSaldo(0.00F);
        repository.save(user);

        var token = tokenService.generationToken(user);
        var result = tokenService.getSubject(token);
        assertEquals("teste@gmail.com", result);
    }

    
}
