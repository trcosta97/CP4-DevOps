package br.com.thiago.fichasApi.controller;

import br.com.thiago.fichasApi.domain.usuario.DadosAutenticacao;
import br.com.thiago.fichasApi.domain.usuario.Usuario;

import br.com.thiago.fichasApi.infra.security.DadosTokenJWT;
import br.com.thiago.fichasApi.infra.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
public class AutenticacaoController {
    @Autowired
    private AuthenticationManager manager;
    @Autowired
    private TokenService tokenService;
    @CrossOrigin(origins="*")
    @PostMapping
    public ResponseEntity efetuarLogin(@RequestBody @Valid DadosAutenticacao dados) {
        var authenticationToken = new UsernamePasswordAuthenticationToken(dados.login(), dados.senha());
        var authentication  = manager.authenticate(authenticationToken);

        var tokenJWT = tokenService.gerarToken((Usuario) authentication.getPrincipal());

        return ResponseEntity.ok( new DadosTokenJWT(tokenJWT));
    }
}

