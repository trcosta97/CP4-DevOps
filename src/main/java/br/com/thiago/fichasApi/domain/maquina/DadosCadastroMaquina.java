package br.com.thiago.fichasApi.domain.maquina;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosCadastroMaquina(
        @NotBlank
        String nome,
        @NotNull
        Setor setor) {
}
