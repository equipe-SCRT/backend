package school.sptech.backend.service.usuario.autenticacao.dto;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import school.sptech.backend.domain.usuario.entity.Usuario;

import java.util.Collection;

public class UsuarioDetalhesDto implements UserDetails {

    private final String nome;
    private final String email;
    private final String senha;
    private final Integer tipoUsuario;

    public UsuarioDetalhesDto(Usuario usuario) {
        this.nome =  usuario.getNome();
        this.email = usuario.getEmail();
        this.senha = usuario.getSenha();
        this.tipoUsuario = usuario.getTipoUsuario();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
