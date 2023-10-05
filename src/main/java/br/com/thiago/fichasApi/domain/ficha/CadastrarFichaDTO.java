package br.com.thiago.fichasApi.domain.ficha;

import br.com.thiago.fichasApi.domain.maquina.DadosMaquinaCadastroFicha;
import br.com.thiago.fichasApi.domain.usuario.DadosAutorCadastrarFicha;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CadastrarFichaDTO(
        @NotNull
        @Valid
        DadosAutorCadastrarFicha autor,
        @NotNull
        @Valid
        DadosMaquinaCadastroFicha maquina,
        @NotNull
        boolean aprovado,
        @NotBlank
        String comentarios
) {
}
