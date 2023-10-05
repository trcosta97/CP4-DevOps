package br.com.thiago.fichasApi.domain.usuario;

import jakarta.validation.constraints.NotBlank;

public record CadastrarUsuarioDTO(
        @NotBlank
        String nome,
        @NotBlank
        String login,
        @NotBlank
        String senha) {
}
