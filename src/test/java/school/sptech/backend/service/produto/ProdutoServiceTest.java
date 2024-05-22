package school.sptech.backend.service.produto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import school.sptech.backend.domain.produto.Produto;
import school.sptech.backend.domain.produto.repository.ProdutoRepository;
import school.sptech.backend.domain.tipoproduto.TipoProduto;
import school.sptech.backend.exception.NaoEncontradoException;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ProdutoServiceTest {
    @InjectMocks
    private ProdutoService produtoService;

    @Mock
    ProdutoRepository produtoRepository;

    @Test
    @DisplayName("Caso nao houver nada no banco retorna uma lista vazia")
    void cenarioListaProdutoVazia() {

        List<Produto> lista = Collections.emptyList();

        Mockito.when(produtoService.listar()).thenReturn(lista);

        List<Produto> produtos = produtoService.listar();

        assertTrue(produtos.isEmpty());
    }

    @Test
    @DisplayName("Caso houver uma lista com 3 produtos, retorne os 3 produtos")
    void cenarioListaTresProdutos() {

        List<Produto> lista = List.of(
                new Produto(1, "Produto 1", new TipoProduto()),
                new Produto(2, "Produto 2", new TipoProduto()),
                new Produto(3, "Produto 3", new TipoProduto())
        );

        Mockito.when(produtoService.listar()).thenReturn(lista);

        List<Produto> produtos = produtoService.listar();

        assertFalse(produtos.isEmpty());
        assertEquals(3, produtos.size());

        assertEquals(lista.get(0).getId(), produtos.get(0).getId());
        assertEquals(lista.get(0).getNome(), produtos.get(0).getNome());
        assertEquals(lista.get(0).getTipoProduto(), produtos.get(0).getTipoProduto());
    }

    @Test
    @DisplayName("Caso houver o produto pelo id, retorne o mesmo")
    void cenarioProdutoExistente(){
        // GIVEN
        Optional<Produto> produto = Optional.of(new Produto(1, "Produto 1", new TipoProduto()));
        Integer id = 1;

        // WHEN
        Mockito.when(produtoRepository.findById(id)).thenReturn(produto);

        // THEN
        Produto produtoEncontrado = produtoService.porId(id);

        // ASSERT
        assertEquals(produto.get().getNome(), produtoEncontrado.getNome());

        Mockito.verify(produtoRepository, Mockito.times(1)).findById(id);
    }

    @Test
    @DisplayName("Caso não houver produto por id, lançe uma exception")
    void cenarioProdutoInexistente(){
        // GIVEN
        Integer id = 10;

        // WHEN
        Mockito.when(produtoRepository.findById(id)).thenReturn(Optional.empty());

        // THEN/ASSERT
        NaoEncontradoException exception = assertThrows(NaoEncontradoException.class, () -> produtoService.porId(id));

        assertEquals("Produto não encontrado(a)", exception.getLocalizedMessage());

    }
}