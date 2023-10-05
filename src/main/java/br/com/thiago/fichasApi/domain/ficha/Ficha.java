package br.com.thiago.fichasApi.domain.ficha;

import br.com.thiago.fichasApi.domain.maquina.DadosMaquinaCadastroFicha;
import br.com.thiago.fichasApi.domain.maquina.Maquina;
import br.com.thiago.fichasApi.domain.usuario.DadosAutorCadastrarFicha;
import br.com.thiago.fichasApi.domain.usuario.Usuario;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity(name = "Ficha")
@Table(name = "ficha")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class Ficha {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ficha_seq")
    @SequenceGenerator(name = "ficha_seq", sequenceName = "ficha_seq", allocationSize = 1)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "autor_id", referencedColumnName = "id", nullable = false)
    private Usuario autor;
    @ManyToOne
    @JoinColumn(name = "maquina_id", referencedColumnName = "id", nullable = false)
    private Maquina maquina;
    @Column(name = "data_criacao",nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime data;
    @Lob
    @Column(name="comentarios",columnDefinition = "TEXT")
    private String comentarios;
    @Column(name = "aprovado",nullable = false, columnDefinition = "BOOLEAN DEFAULT TRUE")
    private Boolean aprovado;
    @Column(name = "ativo", nullable = false, columnDefinition = "BOOLEAN DEFAULT TRUE")
    private Boolean status;

    public Ficha(CadastrarFichaDTO data) {
        this.autor = new Usuario(data.autor());
        this.comentarios = data.comentarios();
        this.aprovado = data.aprovado();
        this.maquina = new Maquina(data.maquina());
    }

    public Ficha(AtualizarFichaDTO data) {
        this.comentarios = data.comenatarios();
    }


    @PrePersist
    public void prePersist(){
        this.data = LocalDateTime.now();
        this.status = true;
    }
}
