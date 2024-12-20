package school.sptech.backend.service.usuario.autenticacao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import school.sptech.backend.domain.usuario.Usuario;
import school.sptech.backend.domain.usuario.repository.UsuarioRepository;
import school.sptech.backend.service.usuario.autenticacao.dto.UsuarioDetalhesDto;

import java.util.Optional;

@Service
public class AutenticacaoService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<Usuario> usuarioOpt = usuarioRepository.findByNome(username);

        if (usuarioOpt.isEmpty()) {
            throw new UsernameNotFoundException(String.format("usuario: %s nao encontrado", username));
        }

        return new UsuarioDetalhesDto(usuarioOpt.get());
    }

    public UserDetails loadByUserEmail(String email) throws UsernameNotFoundException {

        Optional<Usuario> usuarioOpt = usuarioRepository.findByEmail(email);

        if (usuarioOpt.isEmpty()) {
            throw new UsernameNotFoundException(String.format("usuario: %s nao encontrado", email));
        }

        return new UsuarioDetalhesDto(usuarioOpt.get());
    }
}