package school.sptech.backend.service.campanha;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import school.sptech.backend.domain.campanha.Campanha;
import school.sptech.backend.domain.campanha.repository.CampanhaRepository;
import school.sptech.backend.domain.origem.Origem;
import school.sptech.backend.domain.origem.repository.OrigemRepository;
import school.sptech.backend.exception.NaoEncontradoException;
import school.sptech.backend.service.campanha.dto.CampanhaMapper;
import school.sptech.backend.service.origem.OrigemService;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class CampanhaServiceTest {

    @InjectMocks
    private CampanhaService campanhaService;

    @Mock
    private CampanhaRepository campanhaRepository;

    @Test
    @DisplayName("Quando pesquisado por id deve retornar")
    void pesquisaPorIdCampanha(){
        Campanha campanha = new Campanha();
        campanha.setId(1);

        Mockito.when(campanhaRepository.findById(1)).thenReturn(Optional.of(campanha));

        Campanha teste = campanhaService.porId(1);

        assertEquals(1, teste.getId());

        Mockito.verify(campanhaRepository, Mockito.times(1)).findById(1);
    }

    @Test
    @DisplayName("Quando pesquisado por id errado deve arremessar erro")
    void pesquisaPorIdErrada(){
        Mockito.when(campanhaRepository.findById(1)).thenReturn(Optional.empty());


        assertThrows(NaoEncontradoException.class, () -> {
            campanhaService.porId(1);
        });

        Mockito.verify(campanhaRepository, Mockito.times(1)).findById(1);
    }

    @Test
    @DisplayName("Quando inserido certo deve retornar certo")
    void saveErrada(){
        Campanha campanha = new Campanha();
        campanha.setId(1);
        Campanha novaCampanha = new Campanha();
        novaCampanha.setId(null);

        Mockito.when(campanhaService.criar(novaCampanha)).thenReturn(campanha);

        Campanha campanhaSalva = campanhaService.criar(novaCampanha);

        assertEquals(campanha.getId(), campanhaSalva.getId());
        Mockito.verify(campanhaRepository, Mockito.times(1)).save(novaCampanha);
    }

    @Test
    @DisplayName("Caso houver a rota pelo id, retorne a mesma")
    void cenarioCampanhaExistente() {
        Optional<Campanha> campanha = Optional.of(new Campanha());
        campanha.get().setId(1);
        Integer id = 1;

        Mockito.when(campanhaRepository.findById(id)).thenReturn(campanha);

        Campanha campanhaEncontrada = campanhaService.porId(id);

        assertEquals(campanha.get().getNome(), campanhaEncontrada.getNome());

        Mockito.verify(campanhaRepository, Mockito.times(1)).findById(id);
    }

    @Test
    @DisplayName("Dado que tenho o id no banco e passei o objeto, atualiza com sucesso")
    void cenarioAtualizacaoCorreta() {
        Campanha campanhaAtualizacao = new Campanha();
        campanhaAtualizacao.setId(null); // Outros campos podem ser setados conforme necessário

        // ID informado para atualização
        Integer idInformado = 1;

        Campanha campanhaRetorno = new Campanha();
        campanhaRetorno.setId(idInformado);
        campanhaRetorno.setNome(campanhaAtualizacao.getNome());

        Mockito.when(campanhaRepository.findById(idInformado)).thenReturn(Optional.of(new Campanha()));
        Mockito.when(campanhaRepository.save(Mockito.any(Campanha.class))).thenReturn(campanhaRetorno);

        Campanha resposta = campanhaService.atualizar(campanhaAtualizacao, idInformado);

        assertEquals(idInformado, resposta.getId());
        assertEquals(campanhaAtualizacao.getNome(), resposta.getNome());

        Mockito.verify(campanhaRepository, Mockito.times(1)).findById(idInformado);
        Mockito.verify(campanhaRepository, Mockito.times(1)).save(Mockito.any(Campanha.class));

    }

    @Test
    @DisplayName("Dado que tenho um id que não existe no banco, lança exceção")
    void cenarioIdNaoExistenteNoAtualizar() {
        Mockito.when(campanhaRepository.findById(Mockito.anyInt())).thenReturn(Optional.empty());

        assertThrows(NaoEncontradoException.class, () -> {
            campanhaService.atualizar(new Campanha(), 1);
        });

        Mockito.verify(campanhaRepository, Mockito.times(1)).findById(Mockito.anyInt());
    }

    @Test
    @DisplayName("Quando deletar um ID inexistente, deve lançar exceção")
    void deletarErrado() {
        Mockito.when(campanhaRepository.existsById(1)).thenReturn(false);

        assertThrows(NaoEncontradoException.class, () -> {
            campanhaService.deletar(1);
        });

        Mockito.verify(campanhaRepository, Mockito.times(1)).existsById(1);
    }
}