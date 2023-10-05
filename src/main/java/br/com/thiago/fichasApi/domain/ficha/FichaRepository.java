package br.com.thiago.fichasApi.domain.ficha;

import br.com.thiago.fichasApi.domain.usuario.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FichaRepository extends JpaRepository<Ficha, Long> {
    Optional<Ficha> findByIdAndStatusTrue(Long id);

    List<Ficha> findAllByStatusTrue();

    List<Ficha> findAllByAutor(Usuario usuario);

    Optional<Ficha> getReferenceByIdAndStatusTrue(Long id);
}
