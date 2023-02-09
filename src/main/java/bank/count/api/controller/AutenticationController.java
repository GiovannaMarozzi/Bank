package bank.count.api.controller;

import bank.count.api.security.AuthenticationJWT;
import bank.count.api.service.TokenService;
import bank.count.api.service.AutenticationService;
import bank.count.api.user.Autentication;
import bank.count.api.user.Users;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/autentication")
public class AutenticationController {


    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private AutenticationService service;

    @PostMapping("/cad")
    public Users cadUsers(@RequestBody @Valid Users informations){
        return service.save(informations);
    }

    @PostMapping
    public ResponseEntity autentication(@RequestBody @Valid Autentication autentication){
        var authenticationToken = new UsernamePasswordAuthenticationToken(autentication.login(), autentication.password());
        var authenticate = manager.authenticate(authenticationToken);

        var token = tokenService.generationToken((Users) authenticate.getPrincipal());

        return ResponseEntity.ok(new AuthenticationJWT(token));

    }
}
