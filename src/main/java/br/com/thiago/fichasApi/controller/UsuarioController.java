package br.com.thiago.fichasApi.controller;

import br.com.thiago.fichasApi.domain.usuario.AtualizarUsuarioDTO;
import br.com.thiago.fichasApi.domain.usuario.CadastrarUsuarioDTO;
import br.com.thiago.fichasApi.domain.usuario.Usuario;
import br.com.thiago.fichasApi.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("usuario")
public class UsuarioController {

    @Autowired
    public UsuarioService usuarioService;

    @CrossOrigin(origins="*")
    @PostMapping
    public ResponseEntity<Usuario> cadastrarUsuario(@RequestBody @Valid CadastrarUsuarioDTO data, UriComponentsBuilder uriBuilder){
        var newUsuario = new Usuario(data);
        Usuario savedUsuario = usuarioService.save(newUsuario);
        var uri = uriBuilder.path("/player/{id}").buildAndExpand(savedUsuario.getId()).toUri();
        return ResponseEntity.created(uri).body(savedUsuario);
    }

    @GetMapping("{id}")
    public ResponseEntity<Usuario> getUsuarioById(@PathVariable Long id){
        var foundUsuario = usuarioService.getById(id);
        return ResponseEntity.ok(foundUsuario);

    }

    @GetMapping
    @CrossOrigin(origins="*")
    public ResponseEntity<List<Usuario>> getAllUsuarios(){
        List<Usuario> usuarios = usuarioService.getAll();
        return ResponseEntity.ok(usuarios);
    }

    @PutMapping("{id}")
    public ResponseEntity<Usuario> updateUsuario(@RequestBody @Valid AtualizarUsuarioDTO data, @PathVariable Long id){
        var dadosAtualizados = new Usuario(data);
        var usuarioAtualizado = usuarioService.update(id, dadosAtualizados);
        return ResponseEntity.ok(usuarioAtualizado);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Usuario> deleteUsuario(@PathVariable Long id){
        var usuarioApagado = usuarioService.delete(id);
        return ResponseEntity.ok(usuarioApagado);

    }
}
