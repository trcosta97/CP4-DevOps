package br.com.thiago.fichasApi.domain.usuario;

import jakarta.persistence.*;


import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Entity(name = "Usuario")
@Table(name = "usuario")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class Usuario implements UserDetails {
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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        return login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
