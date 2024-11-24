package school.sptech.backend.service.unidademedida;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import school.sptech.backend.domain.unidademedida.UnidadeMedida;
import school.sptech.backend.domain.unidademedida.repository.UnidadeMedidaRepository;
import school.sptech.backend.exception.NaoEncontradoException;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class UnidadeMedidaServiceTest {

    @InjectMocks
    private UnidadeMedidaService unidadeMedidaService;

    @Mock
    private UnidadeMedidaRepository unidadeMedidaRepository;

    @Test
    @DisplayName("Caso nao houver nada no banco retorna uma lista vazia")
    void retornaListaVazia() {

        List<UnidadeMedida> lista = Collections.emptyList();

        Mockito.when(unidadeMedidaService.listar()).thenReturn(lista);

        List<UnidadeMedida> unidadeMedidas = unidadeMedidaService.listar();

        assertTrue(unidadeMedidas.isEmpty());
        Mockito.verify(unidadeMedidaRepository, Mockito.times(1)).findAll();
    }

    @Test
    @DisplayName("Caso houver uma lista com 2 unidades de medida, retorne as 2 unidades de medida")
    void cenarioRetornaDuasUnidadesDeMedida(){

        List<UnidadeMedida> lista = List.of(
                new UnidadeMedida(1, "Quilos", "kg"),
                new UnidadeMedida(2, "Litros", "L")
        );

        Mockito.when(unidadeMedidaService.listar()).thenReturn(lista);

        List<UnidadeMedida> unidadeMedidas = unidadeMedidaService.listar();

        assertFalse(unidadeMedidas.isEmpty());
        assertEquals(2, unidadeMedidas.size());

        assertEquals(lista.get(0).getId(), unidadeMedidas.get(0).getId());
        assertEquals(lista.get(0).getNome(), unidadeMedidas.get(0).getNome());
    }

    @Test
    @DisplayName("Caso houver a unidade de medida pelo id, retorne o mesmo")
    void cenarioProdutoExistente(){
        Optional<UnidadeMedida> unidadeMedida = Optional.of(new UnidadeMedida(1, "Quilos", "kg"));
        Integer id = 1;

        Mockito.when(unidadeMedidaRepository.findById(id)).thenReturn(unidadeMedida);

        UnidadeMedida unidadeMedidaEncontrado = unidadeMedidaService.porId(id);

        assertEquals(unidadeMedida.get().getNome(), unidadeMedidaEncontrado.getNome());

        Mockito.verify(unidadeMedidaRepository, Mockito.times(1)).findById(id);
    }

    @Test
    @DisplayName("Caso não houver unidade de medida por id, lançe uma exception")
    void cenarioProdutoInexistente(){
        Integer id = 10;

        Mockito.when(unidadeMedidaRepository.findById(id)).thenReturn(Optional.empty());

        NaoEncontradoException exception = assertThrows(NaoEncontradoException.class, () -> unidadeMedidaService.porId(id));

        assertEquals("Unidade de Medida não encontrado(a)", exception.getLocalizedMessage());
    }

    @Test
    @DisplayName("Deve retornar a unidade de medida criado")
    void testCriaProduto() {

        UnidadeMedida unidadeMedida = new UnidadeMedida(1, "Quilos", "kg");
        UnidadeMedida novaUnidadeMedida = new UnidadeMedida(null, "Quilos", "kg");

        Mockito.when(unidadeMedidaRepository.save(novaUnidadeMedida)).thenReturn(unidadeMedida);

        UnidadeMedida unidadeMedidaSalvo = unidadeMedidaService.criar(novaUnidadeMedida);

        assertEquals(unidadeMedida.getId(), unidadeMedidaSalvo.getId());
        Mockito.verify(unidadeMedidaRepository, Mockito.times(1)).save(novaUnidadeMedida);
    }

    @Test
    @DisplayName("Deve retornar a unidade de medida pelo id")
    void retonaProdutoPeloId() {
        UnidadeMedida unidadeMedida = new UnidadeMedida(1, "Litros", "L");
        Integer idBusca = 1;

        Mockito.when(unidadeMedidaRepository.findById(idBusca)).thenReturn(Optional.of(unidadeMedida));

        UnidadeMedida unidadeMedidaEncontrado = unidadeMedidaService.porId(idBusca);

        assertEquals(unidadeMedidaEncontrado.getId(), idBusca);

        Mockito.verify(unidadeMedidaRepository, Mockito.times(1)).findById(idBusca);
    }

    @Test
    @DisplayName("Deve retornar ResponseStatusException caso não exista o id")
    void retornaExceptionCasoNaoExiste() {

        Integer idBusca = 100;

        Mockito.when(unidadeMedidaRepository.findById(idBusca)).thenReturn(Optional.empty());

        NaoEncontradoException exception = assertThrows(NaoEncontradoException.class, () -> unidadeMedidaService.porId(idBusca));

        assertEquals("Unidade de Medida não encontrado(a)", exception.getLocalizedMessage());

        Mockito.verify(unidadeMedidaRepository, Mockito.times(1)).findById(idBusca);
    }

    @Test
    @DisplayName("Dado que tenho o id no banco e passei o objeto, atualiza com sucesso")
    void dadoQueAtualizacaoCorreta(){
        UnidadeMedida unidadeMedidaAtualizacao = new UnidadeMedida(null, "Quilos", "kg");
        Integer idInformado = 1;
        UnidadeMedida unidadeMedidaRetorno = new UnidadeMedida(idInformado, "Quilos", "kg");

        Mockito.when(unidadeMedidaRepository.save(unidadeMedidaAtualizacao)).thenReturn(unidadeMedidaRetorno);
        Mockito.when(unidadeMedidaRepository.existsById(idInformado)).thenReturn(Boolean.TRUE);

        UnidadeMedida unidadeMedidaResposta = unidadeMedidaService.atualizar(idInformado, unidadeMedidaAtualizacao);

        assertEquals(idInformado, unidadeMedidaResposta.getId());
        assertEquals(unidadeMedidaAtualizacao.getNome(), unidadeMedidaResposta.getNome());

        Mockito.verify(unidadeMedidaRepository, Mockito.times(1)).existsById(idInformado);
        Mockito.verify(unidadeMedidaRepository, Mockito.times(1)).save(Mockito.any());
    }

    @Test
    @DisplayName("Dado que tenho um id que não existe no banco")
    void dadoQueIdInexistenteNoAtualizar(){
        Mockito.when(unidadeMedidaRepository.existsById(Mockito.any())).thenReturn(Boolean.FALSE);

        assertThrows(NaoEncontradoException.class, () -> unidadeMedidaService.atualizar(1, Mockito.any()));
    }

    @Test
    @DisplayName("Quando deletar por id que não existe no banco devolve erro")
    void deletarComIdInexistente(){
        Integer idInformado = 1;

        Mockito.when(unidadeMedidaRepository.existsById(idInformado)).thenReturn(Boolean.FALSE);

        assertThrows(NaoEncontradoException.class, () -> {
            unidadeMedidaService.deletar(idInformado);
        });

        Mockito.verify(unidadeMedidaRepository, Mockito.times(1)).existsById(idInformado);
    }

    @Test
    @DisplayName("Quando deletar por id que existe no banco deleta unidade de medida")
    void deletarComIdExistente(){
        Integer idInformado = 1;

        Mockito.when(unidadeMedidaRepository.existsById(idInformado)).thenReturn(Boolean.TRUE);

        unidadeMedidaService.deletar(idInformado);

        Mockito.verify(unidadeMedidaRepository, Mockito.times(1)).existsById(idInformado);
        Mockito.verify(unidadeMedidaRepository, Mockito.times(1)).deleteById(idInformado);
    }

}