package school.sptech.backend.service.endereco;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import school.sptech.backend.domain.endereco.Endereco;
import school.sptech.backend.domain.endereco.repository.EnderecoRepository;
import school.sptech.backend.exception.NaoEncontradoException;
import school.sptech.backend.service.BaseService;

import java.util.List;
@Service
@RequiredArgsConstructor
public class EnderecoService implements BaseService<Endereco, Integer> {
    private final EnderecoRepository repository;

    public Endereco porId(Integer id){
        return repository.findById(id).orElseThrow(
                ()-> new NaoEncontradoException("Endereco")
        );
    }
    public List<Endereco> listar(){
        return repository.findAll();
    }

    public Endereco criar(Endereco endereco){
        return repository.save(endereco);
    }
    public Endereco atualizar(Integer id, Endereco endereco){
        porId(id);
        endereco.setId(id);

        return repository.save(endereco);
    }

    public Void deletar(Integer id){
        repository.delete(porId(id));
        return null;
    }

}
