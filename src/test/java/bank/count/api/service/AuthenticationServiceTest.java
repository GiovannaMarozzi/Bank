package bank.count.api.service;

import bank.count.api.user.Users;
import bank.count.api.user.UsersRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;


@RunWith(SpringRunner.class)
@SpringBootTest
public class AuthenticationServiceTest {

    @Autowired
    private UsersRepository repository;

    @Test
    public void loginByUsername(){
        String username = "testeByName@gmail.com";

        Users user = new Users();
        user.setNome("Teste");
        user.setLogin("testeByName@gmail.com");
        user.setPassword("$2a$12$lyqIlgbTtf5lKuLeKrnkme/NxILTj9DYmypjNTGBVBytezBdRCytO");
        user.setCpf_or_cnpj("12345678955");
        user.setType_document("cpf");
        user.setRg("536079766");
        user.setCel("11 00000-0000");
        user.setSaldo(0.00F);

        repository.save(user);
        var login = repository.findByLogin(username);
        int result =0;

        if(login != null){
            result = 1;
        }

        assertEquals(1, result);
    }

    @Test
    public void returnNullIfLoginByNullOrWrongUsername(){
        String username = "invalid@gmail.com";

        Users user = new Users();
        user.setNome("Teste");
        user.setLogin("teste@gmail.com");
        user.setPassword("$2a$12$lyqIlgbTtf5lKuLeKrnkme/NxILTj9DYmypjNTGBVBytezBdRCytO");
        user.setCpf_or_cnpj("12345678955");
        user.setType_document("cpf");
        user.setRg("536079766");
        user.setCel("11 00000-0000");
        user.setSaldo(0.00F);

        repository.save(user);

        var result = repository.findByLogin(username);

        assertNull(result);
    }


    @Test
    public void saveIfNotExistInTableUsers(){
        String newLogin = "newEmail@gmail.com";
        String status;

        Users user = new Users();
        user.setNome("Teste");
        user.setLogin("teste@gmail.com");
        user.setPassword("$2a$12$lyqIlgbTtf5lKuLeKrnkme/NxILTj9DYmypjNTGBVBytezBdRCytO");
        user.setCpf_or_cnpj("12345678955");
        user.setType_document("cpf");
        user.setRg("536079766");
        user.setCel("11 00000-0000");
        user.setSaldo(0.00F);
        repository.save(user);

        var exist = repository.findByLogin(newLogin);

        if(exist == null){
            status = "A new user can be added";
        }else{
            status = "A user with that login already exists";
        }

        assertEquals("A new user can be added", status);
    }

}
