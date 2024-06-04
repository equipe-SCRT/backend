package school.sptech.backend.service.origem;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.server.ResponseStatusException;
import school.sptech.backend.domain.origem.Origem;
import school.sptech.backend.domain.origem.repository.OrigemRepository;
import school.sptech.backend.domain.produto.Produto;
import school.sptech.backend.domain.produtocesta.entity.ProdutoCesta;
import school.sptech.backend.domain.produtocesta.repository.ProdutoCestaRepository;
import school.sptech.backend.exception.NaoEncontradoException;
import school.sptech.backend.service.produtocesta.ProdutoCestaService;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class OrigemServiceTest {

    @InjectMocks
    private OrigemService origemService;

    @Mock
    private OrigemRepository origemRepository;

    @Test
    @DisplayName("Quando pesquisado por id deve retornar")
    void pesquisaPorIdOrigem(){
        Origem origem = new Origem();
        origem.setId(1);

        Mockito.when(origemRepository.findById(1)).thenReturn(Optional.of(origem));

        Origem teste = origemService.porId(1);

        assertEquals(1, teste.getId());

        Mockito.verify(origemRepository, Mockito.times(1)).findById(1);
    }

    @Test
    @DisplayName("Quando pesquisado por id errado deve arremessar erro")
    void pesquisaPorIdErrada(){
        Mockito.when(origemRepository.findById(1)).thenReturn(Optional.empty());


            assertThrows(NaoEncontradoException.class, () -> {
            origemService.porId(1);
        });

        Mockito.verify(origemRepository, Mockito.times(1)).findById(1);
    }

    @Test
    @DisplayName("Quando inserido certo deve retornar certo")
    void saveErrada(){
        Origem origem = new Origem();
        origem.setId(1);
        Origem novaOrigem = new Origem();
        novaOrigem.setId(null);

        Mockito.when(origemService.criar(novaOrigem)).thenReturn(origem);

        Origem origemSalva = origemService.criar(novaOrigem);

        assertEquals(origem.getId(), origemSalva.getId());
        Mockito.verify(origemRepository, Mockito.times(1)).save(novaOrigem);
    }

    @Test
    @DisplayName("Caso houver a rota pelo id, retorne a mesma")
    void cenarioOrigemExistente() {
        Optional<Origem> origem = Optional.of(new Origem());
        origem.get().setId(1);
        Integer id = 1;

        Mockito.when(origemRepository.findById(id)).thenReturn(origem);

        Origem origemEncontrada = origemService.porId(id);

        assertEquals(origem.get().getItapora(), origemEncontrada.getItapora());

        Mockito.verify(origemRepository, Mockito.times(1)).findById(id);
    }

    @Test
    @DisplayName("Dado que tenho o id no banco e passei o objeto, atualiza com sucesso")
    void cenarioAtualizacaoCorreta() {
        Origem origemAtualizacao = new Origem();
        origemAtualizacao.setId(null); // Outros campos podem ser setados conforme necessário

        // ID informado para atualização
        Integer idInformado = 1;

        Origem origemRetorno = new Origem();
        origemRetorno.setId(idInformado);
        origemRetorno.setItapora(origemAtualizacao.getItapora());

        Mockito.when(origemRepository.findById(idInformado)).thenReturn(Optional.of(new Origem()));
        Mockito.when(origemRepository.save(Mockito.any(Origem.class))).thenReturn(origemRetorno);

        Origem resposta = origemService.atualizar(origemAtualizacao, idInformado);

        assertEquals(idInformado, resposta.getId());
        assertEquals(origemAtualizacao.getItapora(), resposta.getItapora());

        Mockito.verify(origemRepository, Mockito.times(1)).findById(idInformado);
        Mockito.verify(origemRepository, Mockito.times(1)).save(Mockito.any(Origem.class));

    }

    @Test
    @DisplayName("Dado que tenho um id que não existe no banco, lança exceção")
    void cenarioIdNaoExistenteNoAtualizar() {
        Mockito.when(origemRepository.findById(Mockito.anyInt())).thenReturn(Optional.empty());

        assertThrows(NaoEncontradoException.class, () -> {
            origemService.atualizar(new Origem(), 1);
        });

        Mockito.verify(origemRepository, Mockito.times(1)).findById(Mockito.anyInt());
    }

    @Test
    @DisplayName("Quando deletar um ID inexistente, deve lançar exceção")
    void deletarErrado() {
        Mockito.when(origemRepository.existsById(1)).thenReturn(false);

        assertThrows(NaoEncontradoException.class, () -> {
            origemService.deletar(1);
        });

        Mockito.verify(origemRepository, Mockito.times(1)).existsById(1);
    }
}