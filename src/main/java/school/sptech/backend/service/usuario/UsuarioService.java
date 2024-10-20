package school.sptech.backend.service.usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import school.sptech.backend.configuration.security.jwt.GerenciadorTokenJwt;
import school.sptech.backend.exception.NaoEncontradoException;
import school.sptech.backend.service.usuario.autenticacao.dto.UsuarioLoginDto;
import school.sptech.backend.service.usuario.autenticacao.dto.UsuarioTokenDto;
import school.sptech.backend.service.usuario.dto.UsuarioConsultaDtoJwt;
import school.sptech.backend.service.usuario.dto.UsuarioCriacaoDtoJwt;
import school.sptech.backend.domain.usuario.Usuario;
import school.sptech.backend.service.usuario.dto.UsuarioMapper;
import school.sptech.backend.domain.usuario.repository.UsuarioRepository;

import java.util.List;
import java.util.Optional;

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

    public void criar(UsuarioCriacaoDtoJwt usuarioCriacaoDto){

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

    public List<Usuario> getUsuarios() {
        List<Usuario> all = usuarioRepository.findAll();

        return all;
    }

    public Usuario porId(Integer id) {
        return usuarioRepository.findById(id).orElseThrow(() -> new NaoEncontradoException("Usuario"));
    }

    public Usuario atualizarUsuario(UsuarioConsultaDtoJwt usuarioConsultaDtoJwt){
       Optional<Usuario> usuario = usuarioRepository.findById(usuarioConsultaDtoJwt.getId());
       usuario.get().setTipoUsuario(usuarioConsultaDtoJwt.getTipoUsuario());
       usuario.get().setNome(usuarioConsultaDtoJwt.getNome());
       usuario.get().setEmail(usuarioConsultaDtoJwt.getEmail());
       usuarioRepository.save(usuario.get());

       return usuario.get();
    }

    public boolean deleteUsuario(int idUsuario){
        if(usuarioRepository.existsById(idUsuario)){
            usuarioRepository.deleteById(idUsuario);
            return true;
        }
        throw new NaoEncontradoException("Usuario");
    }
}
