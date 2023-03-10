package bank.count.api.controller;

import bank.count.api.accounts.ListAccounts;
import bank.count.api.security.AuthenticationJWT;
import bank.count.api.service.TokenService;
import bank.count.api.service.AutenticationService;
import bank.count.api.user.Autentication;
import bank.count.api.user.Users;
import bank.count.api.user.UsersRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/autentication")
public class AutenticationController {


    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private AutenticationService service;
    @Autowired
    private UsersRepository usersRepository;

    @PostMapping("/cad")
    public ResponseEntity cadUsers(@RequestBody @Valid Users informations, UriComponentsBuilder uriBuilder){
        service.save(informations);
        var uri = uriBuilder.path("/autentication/cad/{id}").buildAndExpand(informations.getNumber());
        return ResponseEntity.created(uri.toUri()).body(new ListAccounts(informations));
    }

    @PostMapping("/auth")
    public ResponseEntity autentication(@RequestBody @Valid Autentication autentication){
        var authenticationToken = new UsernamePasswordAuthenticationToken(autentication.login(), autentication.password());
        var authenticate = manager.authenticate(authenticationToken);

        var token = tokenService.generationToken((Users) authenticate.getPrincipal());

        return ResponseEntity.ok(new AuthenticationJWT(token));
    }

    @PutMapping("/block={document}")
    public Object block(@PathVariable Long document){
        var exist = usersRepository.findByNumber(document);

        if(exist.size() == 0){
            System.out.println("Conta inexistente ou incorreta");
            return ResponseEntity.notFound();
        }else{
            service.block(document);
            return ResponseEntity.ok("Conta bloqueada com sucesso");
        }
    }
}
