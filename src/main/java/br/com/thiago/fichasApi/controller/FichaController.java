package br.com.thiago.fichasApi.controller;

import br.com.thiago.fichasApi.domain.ficha.AtualizarFichaDTO;
import br.com.thiago.fichasApi.domain.ficha.CadastrarFichaDTO;
import br.com.thiago.fichasApi.domain.ficha.Ficha;
import br.com.thiago.fichasApi.domain.maquina.Maquina;
import br.com.thiago.fichasApi.domain.usuario.Usuario;
import br.com.thiago.fichasApi.service.FichaService;
import br.com.thiago.fichasApi.service.MaquinaService;
import br.com.thiago.fichasApi.service.UsuarioService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("ficha")
@SecurityRequirement(name="bearer-key")
public class FichaController {

    @Autowired
    private FichaService fichaService;

    @Autowired
    private MaquinaService maquinaService;

    @Autowired
    private UsuarioService usuarioService;


    @PostMapping()
    public ResponseEntity<Ficha> cadastrarFicha(@RequestBody @Valid CadastrarFichaDTO data, UriComponentsBuilder uriBuilder){
        var newFicha = new Ficha(data);
        Usuario usuario = usuarioService.getById(data.autor().id());
        Maquina maquina = maquinaService.getById(data.autor().id());

        newFicha.setAutor(usuario);
        newFicha.setMaquina(maquina);

        Ficha savedFicha = fichaService.save(newFicha);
        var uri = uriBuilder.path("/ficha/{id}").buildAndExpand(savedFicha.getId()).toUri();
        return ResponseEntity.created(uri).body(savedFicha);
    }

    @GetMapping("{id}")
    public ResponseEntity<Ficha> getFichaById(@PathVariable Long id){
        var foundFicha = fichaService.getFichaById(id);
        if (foundFicha != null){
            return ResponseEntity.ok(foundFicha);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping()
    public ResponseEntity<List<Ficha>> getAllFichas(){
        List<Ficha> fichas = fichaService.getAll();
        return ResponseEntity.ok(fichas);
    }

    @PutMapping("{id}")
    public ResponseEntity<Ficha> updateFicha(@RequestBody AtualizarFichaDTO data, @PathVariable Long id){
        var fichaAutualizada = new Ficha(data);
        return ResponseEntity.ok(fichaService.update(id, fichaAutualizada));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Ficha> delete(@PathVariable Long id){
        return ResponseEntity.ok(fichaService.delete(id));
    }




}