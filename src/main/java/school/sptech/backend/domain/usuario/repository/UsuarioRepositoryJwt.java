package school.sptech.backend.domain.usuario.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import school.sptech.backend.domain.usuario.entity.UsuarioJwt;

import java.util.Optional;

@Repository
public interface UsuarioRepositoryJwt extends JpaRepository<UsuarioJwt, Long> {

    Optional<UsuarioJwt> findByEmail(String email);
}
