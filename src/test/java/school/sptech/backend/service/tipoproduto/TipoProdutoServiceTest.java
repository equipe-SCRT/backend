package school.sptech.backend.service.tipoproduto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import school.sptech.backend.domain.tipoproduto.TipoProduto;
import school.sptech.backend.domain.tipoproduto.repository.TipoProdutoRepository;
import school.sptech.backend.exception.NaoEncontradoException;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class TipoProdutoServiceTest {

    @InjectMocks
    private TipoProdutoService tipoProdutoService;

    @Mock
    private TipoProdutoRepository tipoProdutoRepository;


    @Test
    @DisplayName("Caso nao houver nada no banco retorna uma lista vazia")
    void retornaListaVazia() {

        List<TipoProduto> lista = Collections.emptyList();

        Mockito.when(tipoProdutoService.listar()).thenReturn(lista);

        List<TipoProduto> tipoProdutos = tipoProdutoService.listar();

        assertTrue(tipoProdutos.isEmpty());
        Mockito.verify(tipoProdutoRepository, Mockito.times(1)).findAll();
    }


    @Test
    @DisplayName("Caso houver uma lista com 2 tipo produtos, retorne os 2 tipo produtos")
    void cenarioRetornaDoisProdutos(){

        List<TipoProduto> lista = List.of(
                new TipoProduto(1, "Perecível"),
                new TipoProduto(2, "Não Perecível")
        );

        Mockito.when(tipoProdutoService.listar()).thenReturn(lista);

        List<TipoProduto> tipoProdutos = tipoProdutoService.listar();

        assertFalse(tipoProdutos.isEmpty());
        assertEquals(2, tipoProdutos.size());

        assertEquals(lista.get(0).getId(), tipoProdutos.get(0).getId());
        assertEquals(lista.get(0).getNome(), tipoProdutos.get(0).getNome());
    }

    @Test
    @DisplayName("Caso houver o tipo produto pelo id, retorne o mesmo")
    void cenarioProdutoExistente(){
        Optional<TipoProduto> tipoProduto = Optional.of(new TipoProduto(1, "Perecível"));
        Integer id = 1;

        Mockito.when(tipoProdutoRepository.findById(id)).thenReturn(tipoProduto);

        TipoProduto tipoProdutoEncontrado = tipoProdutoService.porId(id);

        assertEquals(tipoProduto.get().getNome(), tipoProdutoEncontrado.getNome());

        Mockito.verify(tipoProdutoRepository, Mockito.times(1)).findById(id);
    }

    @Test
    @DisplayName("Caso não houver tipo produto por id, lançe uma exception")
    void cenarioProdutoInexistente(){
        Integer id = 10;

        Mockito.when(tipoProdutoRepository.findById(id)).thenReturn(Optional.empty());

        NaoEncontradoException exception = assertThrows(NaoEncontradoException.class, () -> tipoProdutoService.porId(id));

        assertEquals("Tipo Produto não encontrado(a)", exception.getLocalizedMessage());

    }

    @Test
    @DisplayName("Deve retornar o tipo produto criado")
    void testCriaProduto() {

        TipoProduto tipoProduto = new TipoProduto(1, "Perecível");
        TipoProduto novoTipoProduto = new TipoProduto(null, "Perecível");

        Mockito.when(tipoProdutoRepository.save(novoTipoProduto)).thenReturn(tipoProduto);

        TipoProduto tipoProdutoSalvo = tipoProdutoService.criar(novoTipoProduto);

        assertEquals(tipoProduto.getId(), tipoProdutoSalvo.getId());
        Mockito.verify(tipoProdutoRepository, Mockito.times(1)).save(novoTipoProduto);

    }

    @Test
    @DisplayName("Deve retornar o tipo produto pelo id")
    void retonaProdutoPeloId() {
        TipoProduto tipoProduto = new TipoProduto(1, "Perecível");
        Integer idBusca = 1;

        Mockito.when(tipoProdutoRepository.findById(idBusca)).thenReturn(Optional.of(tipoProduto));

        TipoProduto tipoProdutoEncontrado = tipoProdutoService.porId(idBusca);

        assertEquals(tipoProdutoEncontrado.getId(), idBusca);

        Mockito.verify(tipoProdutoRepository, Mockito.times(1)).findById(idBusca);

    }

    @Test
    @DisplayName("Deve retornar ResponseStatusException caso não exista o id")
    void retornaExceptionCasoNaoExiste() {

        Integer idBusca = 100;

        Mockito.when(tipoProdutoRepository.findById(idBusca)).thenReturn(Optional.empty());

        NaoEncontradoException exception = assertThrows(NaoEncontradoException.class, () -> tipoProdutoService.porId(idBusca));

        assertEquals("Tipo Produto não encontrado(a)", exception.getLocalizedMessage());

        Mockito.verify(tipoProdutoRepository, Mockito.times(1)).findById(idBusca);
    }

    @Test
    @DisplayName("Dado que tenho o id no banco e passei o objeto, atualiza com sucesso")
    void dadoQueAtualizacaoCorreta(){
        TipoProduto tipoProdutoAtualizacao = new TipoProduto(null, "Perecível");
        Integer idInformado = 1;
        TipoProduto tipoProdutoRetorno = new TipoProduto(idInformado, "Perecível");

        Mockito.when(tipoProdutoRepository.save(tipoProdutoAtualizacao)).thenReturn(tipoProdutoRetorno);
        Mockito.when(tipoProdutoRepository.existsById(idInformado)).thenReturn(Boolean.TRUE);

        TipoProduto tipoProdutoResposta = tipoProdutoService.atualizar(tipoProdutoAtualizacao, idInformado);

        assertEquals(idInformado, tipoProdutoResposta.getId());
        assertEquals(tipoProdutoAtualizacao.getNome(), tipoProdutoResposta.getNome());

        Mockito.verify(tipoProdutoRepository, Mockito.times(1)).existsById(idInformado);
        Mockito.verify(tipoProdutoRepository, Mockito.times(1)).save(Mockito.any());
    }

    @Test
    @DisplayName("Dado que tenho um id que não existe no banco")
    void dadoQueIdInexistenteNoAtualizar(){
        Mockito.when(tipoProdutoRepository.existsById(Mockito.any())).thenReturn(Boolean.FALSE);

        assertThrows(NaoEncontradoException.class, () -> tipoProdutoService.atualizar(Mockito.any(), 1));
    }

    @Test
    @DisplayName("Quando deletar por id que não existe no banco devolve erro")
    void deletarComIdInexistente(){
        Mockito.when(tipoProdutoRepository.existsById(1)).thenReturn(Boolean.FALSE);

        assertThrows(NaoEncontradoException.class, () -> {
            tipoProdutoService.deletar(1);
        });

        Mockito.verify(tipoProdutoRepository, Mockito.times(1)).existsById(1);
    }

    @Test
    @DisplayName("Quando deletar por id que existe no banco deleta tipo produto")
    void deletarComIdExistente(){
        Integer idInformado = 1;

        Mockito.when(tipoProdutoRepository.existsById(idInformado)).thenReturn(Boolean.TRUE);

        tipoProdutoService.deletar(idInformado);

        Mockito.verify(tipoProdutoRepository, Mockito.times(1)).existsById(idInformado);
        Mockito.verify(tipoProdutoRepository, Mockito.times(1)).deleteById(idInformado);
    }


}