package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.domain.user.AuthenticationDTO;
import med.voll.api.domain.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authManager;

    @PostMapping
    public ResponseEntity login(@RequestBody @Valid AuthenticationDTO data){
        var token = new UsernamePasswordAuthenticationToken(data.login(), data.password());
        var authentication = authManager.authenticate(token);

        return ResponseEntity.ok().build();
    }
}