package school.sptech.backend.service.usuario.autenticacao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import school.sptech.backend.domain.usuario.entity.UsuarioJwt;
import school.sptech.backend.domain.usuario.repository.UsuarioRepositoryJwt;
import school.sptech.backend.service.usuario.autenticacao.dto.UsuarioDetalhesDto;

import java.util.Optional;

@Service
public class AutenticacaoService implements UserDetailsService {

    @Autowired
    private UsuarioRepositoryJwt usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<UsuarioJwt> usuarioOpt = usuarioRepository.findByEmail(username);

        if (usuarioOpt.isEmpty()) {
            throw new UsernameNotFoundException(String.format("usuario: %s nao encontrado", username));
        }

        return new UsuarioDetalhesDto(usuarioOpt.get());
    }
}