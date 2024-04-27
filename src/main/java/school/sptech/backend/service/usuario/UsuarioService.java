package school.sptech.backend.service.usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import school.sptech.backend.api.configuration.security.jwt.GerenciadorTokenJwt;
import school.sptech.backend.service.usuario.autenticacao.dto.UsuarioLoginDto;
import school.sptech.backend.service.usuario.autenticacao.dto.UsuarioTokenDto;
import school.sptech.backend.service.usuario.dto.UsuarioConsultaDto;
import school.sptech.backend.service.usuario.dto.UsuarioCriacaoDto;
import school.sptech.backend.domain.usuario.entity.Usuario;
import school.sptech.backend.domain.usuario.mapper.UsuarioMapper;
import school.sptech.backend.domain.usuario.repository.UsuarioRepository;

import java.util.List;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private GerenciadorTokenJwt gerenciadorTokenJwt;

    @Autowired
    private AuthenticationManager authenticationManager;

    public void criar(UsuarioCriacaoDto usuarioCriacaoDto){

        final Usuario novoUsuario = UsuarioMapper.of(usuarioCriacaoDto);
        String senhaCriptografada = passwordEncoder.encode(novoUsuario.getSenha());
        novoUsuario.setSenha(senhaCriptografada);

        this.usuarioRepository.save(novoUsuario);

    }

    public UsuarioTokenDto autenticar(UsuarioLoginDto usuarioLoginDto) {

        final UsernamePasswordAuthenticationToken credentials = new UsernamePasswordAuthenticationToken(
                usuarioLoginDto.getEmail(), usuarioLoginDto.getSenha());

        final Authentication authentication = this.authenticationManager.authenticate(credentials);

        Usuario usuarioAutenticado =
                usuarioRepository.findByEmail(usuarioLoginDto.getEmail())
                        .orElseThrow(
                                () -> new ResponseStatusException(404, "Email do usuario nao cadastrado", null)
                        );
        SecurityContextHolder.getContext().setAuthentication(authentication);

        final String token = gerenciadorTokenJwt.generateToken(authentication);

        return UsuarioMapper.of(usuarioAutenticado, token);
    }

//    public UsuarioConsultaDto adicionarUsuario(UsuarioCriacaoDto usuarioCriacaoDto){
//        Usuario usuario = UsuarioMapper.toEntity(usuarioCriacaoDto);
//
//        usuarioRepository.save(usuario);
//
//        UsuarioConsultaDto usuarioConsultaDto = UsuarioMapper.toDto(usuario);
//
//        return usuarioConsultaDto;
//    }

    public List<UsuarioConsultaDto> getUsuarios() {
        List<Usuario> all = usuarioRepository.findAll();

        List<UsuarioConsultaDto> lista = UsuarioMapper.toDto(all);

        return lista;
    }
}
