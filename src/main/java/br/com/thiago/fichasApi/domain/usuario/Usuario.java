package br.com.thiago.fichasApi.domain.usuario;

import jakarta.persistence.*;


import lombok.*;


import java.time.LocalDateTime;

@Entity(name = "Usuario")
@Table(name = "usuario")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class Usuario{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "usuario_seq")
    @SequenceGenerator(name = "usuario_seq", sequenceName = "usuario_seq", allocationSize = 1)
    private Long id;
    @Column(name="nome",nullable = false)
    private String nome;
    @Column(name="login",nullable = false, unique = true)
    private String login;
    @Column(name="senha",nullable = false)
    private String senha;
    @Column(name = "data_cadastro", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime dataCadastro;
    @Column(name = "status",  columnDefinition = "BOOLEAN DEFAULT TRUE")
    private Boolean status;


    public Usuario(CadastrarUsuarioDTO data) {
        this.nome = data.nome();
        this.login = data.login();
        this.senha = data.senha();
    }

    public Usuario(DadosAutorCadastrarFicha data){
        this.id = data.id();
    }

    public Usuario(AtualizarUsuarioDTO data) {
        this.nome = data.nome();
        this.senha = data.senha();
        this.login = data.login();

    }

    @PrePersist
    public void prePersist(){
        this.dataCadastro = LocalDateTime.now();
        this.status = true;
    }

}
