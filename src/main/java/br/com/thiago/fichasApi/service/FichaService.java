package br.com.thiago.fichasApi.service;

import br.com.thiago.fichasApi.domain.ficha.Ficha;
import br.com.thiago.fichasApi.domain.ficha.FichaRepository;
import br.com.thiago.fichasApi.domain.usuario.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FichaService {

    @Autowired
    FichaRepository fichaRepository;

    public Ficha save(Ficha ficha){

        return fichaRepository.save(ficha);
    }

    public Ficha getFichaById(Long id){
        Optional<Ficha> optionalFicha = fichaRepository.findByIdAndStatusTrue(id);
        return optionalFicha.orElse(null);
    }

    public List<Ficha> getFichaByAutor(Usuario usuario){
        return fichaRepository.findAllByAutor(usuario);
    }

    public List<Ficha> getAll(){
        return fichaRepository.findAllByStatusTrue();
    }

    public Ficha update(Long id, Ficha ficha){
        Optional<Ficha> optionalFicha = fichaRepository.getReferenceByIdAndStatusTrue(id);
        if(optionalFicha.isPresent()){
            Ficha updatedFicha = optionalFicha.get();
            if(ficha.getComentarios() != null){
                updatedFicha.setComentarios(ficha.getComentarios());
            }
            fichaRepository.save(updatedFicha);
            return updatedFicha;
        }
        return null;
    }

    public Ficha delete(Long id){
        Optional<Ficha> optionalFicha = fichaRepository.findByIdAndStatusTrue(id);
        if (optionalFicha.isPresent()){
            Ficha ficha = optionalFicha.get();
            ficha.setStatus(false);
            fichaRepository.save(ficha);
            return ficha;
        }
        return null;

    }
}
