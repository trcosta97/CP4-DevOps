package br.com.thiago.fichasApi.service;

import br.com.thiago.fichasApi.domain.ficha.Ficha;
import br.com.thiago.fichasApi.domain.maquina.Maquina;
import br.com.thiago.fichasApi.domain.maquina.MaquinaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MaquinaService {

    @Autowired
    MaquinaRepository maquinaRepository;

    public Maquina save(Maquina maquina){
        return maquinaRepository.save(maquina);
    }

    public Maquina getById(Long id){
        Optional<Maquina> optionalMaquina = maquinaRepository.findByIdAndStatusTrue(id);
        return optionalMaquina.orElse(null);
    }

    public List<Maquina> getAll(){
        return maquinaRepository.getAllByStatusTrue();
    }

    public Maquina updade(Long id, Maquina data){
        Optional<Maquina> optionalMaquina = maquinaRepository.findById(id);
        if(optionalMaquina.isPresent()){
            Maquina updatedMaquina = optionalMaquina.get();
            if(data.getSetor() != null){
                updatedMaquina.setSetor(data.getSetor());
            }
            return maquinaRepository.save(updatedMaquina);
        }
        return null;
    }

    public void delete(Long id){
        Optional<Maquina> optionalMaquina = maquinaRepository.findByIdAndStatusTrue(id);
        if (optionalMaquina.isPresent()){
            Maquina maquina = optionalMaquina.get();
            maquina.setStatus(false);
            maquinaRepository.save(maquina);
        }
    }


}
