package br.com.thiago.fichasApi.service;

import br.com.thiago.fichasApi.domain.usuario.Usuario;
import br.com.thiago.fichasApi.domain.usuario.UsuarioRepository;
import br.com.thiago.fichasApi.infra.security.SecurityConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {
    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public Usuario save(Usuario usuario){
        usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
        return usuarioRepository.save(usuario);
    }

    public Usuario getById(Long id){
        Optional<Usuario> optionalUsuario = usuarioRepository.findByIdAndStatusTrue(id);
        return optionalUsuario.orElse(null);
    }

    public List<Usuario> getAll(){
        return usuarioRepository.findAllByStatusTrue();
    }

    public Usuario update(Long id, Usuario data){
        Optional<Usuario> optionalUsuario = usuarioRepository.findByIdAndStatusTrue(id);
        if (optionalUsuario.isPresent()){
            Usuario updatedUsuario = optionalUsuario.get();
            if (data.getLogin() != null){
                updatedUsuario.setLogin(data.getLogin());
            }
            if(data.getNome() != null){
                updatedUsuario.setNome(data.getNome());
            }
            if(data.getSenha() != null){
                updatedUsuario.setSenha(data.getSenha());
            }
            return usuarioRepository.save(updatedUsuario);

        }
        return null;
    }

    public Usuario update(Usuario data, Long id){
        Optional<Usuario> optionalUsuario = usuarioRepository.findByIdAndStatusTrue(id);
        if (optionalUsuario.isPresent()){
            Usuario updatedUsuario = optionalUsuario.get();
            if(data.getNome() != null){
                updatedUsuario.setNome(data.getNome());
            }
            if(data.getLogin() != null){
                updatedUsuario.setLogin(data.getLogin());
            }
            if(data.getSenha() != null){
                updatedUsuario.setSenha(data.getSenha());
            }
            return usuarioRepository.save(updatedUsuario);
        }
        return null;
    }

    public Usuario delete(Long id){
        Optional<Usuario> optionalUsuario = usuarioRepository.findByIdAndStatusTrue(id);
        if (optionalUsuario.isPresent()){
            Usuario usuario = optionalUsuario.get();
            usuario.setStatus(false);
            usuarioRepository.save(usuario);
            return usuario;
        }
        return null;
    }


}
