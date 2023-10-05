package br.com.thiago.fichasApi.domain.maquina;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity(name = "Maquina")
@Table(name = "maquina")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@EqualsAndHashCode(of = "id")
public class Maquina {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "maquina_seq")
    @SequenceGenerator(name = "maquina_seq", sequenceName = "maquina_seq", allocationSize = 1)
    private Long id;
    @Column(name="nome",nullable = false)
    private String nome;
    @Column(name="setor",nullable = false)
    @Enumerated(EnumType.STRING)
    private Setor setor;
    @Column(name = "status",  columnDefinition = "BOOLEAN DEFAULT TRUE")
    private Boolean status;
    @Column(name = "data_cadastro")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime cadastro;

    @PrePersist
    public void prePersist(){
        this.cadastro = LocalDateTime.now();
        this.status = true;
    }

    public Maquina(DadosCadastroMaquina data) {
        this.setor = data.setor();
        this.nome = data.nome();
    }

    public Maquina(DadosMaquinaCadastroFicha data){
        this.id = data.id();
    }
}
