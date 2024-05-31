package school.sptech.backend.service.rota;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import school.sptech.backend.domain.rota.Rota;
import school.sptech.backend.domain.rota.repository.RotaRepository;
import school.sptech.backend.exception.NaoEncontradoException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class RotaServiceTest {

    @InjectMocks
    private RotaService service;

    @Mock
    private RotaRepository repository;

    @Test
    @DisplayName("Caso houver uma lista com 3 rotas, retorne os 3 rotas")
    void cenarioListaComRotas() {
        List<Rota> lista = List.of(
                new Rota(),
                new Rota(),
                new Rota()
        );

        Mockito.when(repository.findAll()).thenReturn(lista);

        List<Rota> rotas = service.listar();

        assertFalse(rotas.isEmpty());
        assertEquals(rotas.get(0).getId(), lista.get(0).getId());
        assertEquals(rotas.get(0).getNome(), lista.get(0).getNome());
        assertEquals(rotas.get(0).getData_historico(), lista.get(0).getData_historico());

        Mockito.verify(repository, Mockito.times(1)).findAll();
    }

    @Test
    @DisplayName("Caso não houver nada no banco, retorne uma lista vazia")
    void cenarioListaVazia() {
        List<Rota> lista = new ArrayList<>();

        Mockito.when(repository.findAll()).thenReturn(lista);

        List<Rota> rotas = service.listar();

        assertTrue(rotas.isEmpty());
    }

    @Test
    @DisplayName("Deve retornar o objeto salvo")
    void testSalvaRota() {
        Rota rota = new Rota();
        rota.setId(1);
        Rota novoCondominio = new Rota();
        novoCondominio.setId(null);

        Mockito.when(service.criar(novoCondominio)).thenReturn(rota);

        Rota condominioSalvo = service.criar(novoCondominio);

        assertEquals(rota.getId(), condominioSalvo.getId());
        Mockito.verify(repository, Mockito.times(1)).save(novoCondominio);
    }

    @Test
    @DisplayName("Caso houver a rota pelo id, retorne a mesma")
    void cenarioRotaExistente() {
        Optional<Rota> rota = Optional.of(new Rota());
        rota.get().setId(1);
        Integer id = 1;

        Mockito.when(repository.findById(id)).thenReturn(rota);

        Rota rotaEncontrada = service.porId(id);

        assertEquals(rota.get().getNome(), rotaEncontrada.getNome());

        Mockito.verify(repository, Mockito.times(1)).findById(id);
    }

    @Test
    @DisplayName("Caso não houver a rota pelo id, lance uma exception")
    void cenarioRotaInexistente() {
        Integer id = 10;

        Mockito.when(repository.findById(id)).thenReturn(Optional.empty());

        NaoEncontradoException exception = assertThrows(
                NaoEncontradoException.class,
                () -> service.porId(id)
        );

        assertEquals("Rota não encontrado(a)", exception.getLocalizedMessage());
    }

    @Test
    @DisplayName("Ao buscar nome ignorando case contendo, retornar o produto")
    void cenarioBuscaPorNomeContendoIgnoreCase() {
        Rota resposta = new Rota();

        Mockito.when(repository.findByNomeIgnoreCase("a")).thenReturn(Optional.of(resposta));

        Rota rota = service.porNome("a");

        assertNotNull(rota);
    }

//    @Test
//    @DisplayName("Ao buscar nome ignorando case não contendo, retornar nulo")
//    void cenarioBuscaPorNomeNaoContendoIgnoreCase() {
//        Rota rota = new Rota();
//
//        Mockito.when(repository.findByNomeIgnoreCase("w")).thenReturn(Optional.of(rota));
//
//        Rota rotaResposta = service.porNome("w");
//
//        assertNull(rotaResposta);
//        Mockito.verify(repository, Mockito.times(1)).findByNomeIgnoreCase("w");
//    }

    @Test
    @DisplayName("Dado que tenho o id no banco e passei o objeto, atualiza com sucesso")
    void cenarioAtualizacaoCorreta() {
        Rota rotaAtualizacao = new Rota();
        rotaAtualizacao.setId(null);
        Integer idInformado = 1;
        Rota rotaRetorno = new Rota();
        rotaRetorno.setId(idInformado);

        Mockito.when(repository.save(rotaAtualizacao)).thenReturn(rotaRetorno);
        Mockito.when(repository.existsById(idInformado)).thenReturn(Boolean.TRUE);

        Rota resposta = service.atualizar(rotaAtualizacao, idInformado);

        assertEquals(idInformado, resposta.getId());
        assertEquals(rotaAtualizacao.getNome(), resposta.getNome());

        Mockito.verify(repository, Mockito.times(1)).existsById(idInformado);
        Mockito.verify(repository, Mockito.times(1)).save(Mockito.any());
    }

    @Test
    @DisplayName("Dado que tenho um id que não existe no banco")
    void cenarioIdNaoExistenteNoAtualizar() {
        Mockito.when(repository.existsById(Mockito.any())).thenReturn(Boolean.FALSE);

        assertThrows(NaoEncontradoException.class,
                () -> service.atualizar(Mockito.any(), 1));
    }
}