package br.com.thiago.fichasApi.domain.usuario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByIdAndStatusTrue(Long id);

    List<Usuario> findAllByStatusTrue();

    UserDetails findByLogin(String login);
}
