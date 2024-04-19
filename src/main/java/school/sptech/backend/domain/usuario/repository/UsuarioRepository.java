package school.sptech.backend.domain.usuario.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import school.sptech.backend.domain.usuario.entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
}
