package br.com.thiago.fichasApi.domain.maquina;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MaquinaRepository extends JpaRepository<Maquina, Long> {
    Optional<Maquina> findByIdAndStatusTrue(Long id);

    List<Maquina> getAllByStatusTrue();
}
