package school.sptech.backend.service.produto;

import lombok.RequiredArgsConstructor;
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
import school.sptech.backend.domain.tipoproduto.repository.TipoProdutoRepository;
import school.sptech.backend.exception.NaoEncontradoException;
import school.sptech.backend.service.tipoproduto.TipoProdutoService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ProdutoServiceTest {

    @InjectMocks
    private ProdutoService produtoService;

    @Mock
    private TipoProdutoService tipoProdutoService;

    @Mock
    private ProdutoRepository produtoRepository;

    @Test
    @DisplayName("Caso nao houver nada no banco retorna uma lista vazia")
    void retornaListaVazia() {

        List<Produto> lista = Collections.emptyList();

        Mockito.when(produtoService.listar()).thenReturn(lista);

        List<Produto> produtos = produtoService.listar();

        assertTrue(produtos.isEmpty());
        Mockito.verify(produtoRepository, Mockito.times(1)).findAll();
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
        Optional<Produto> produto = Optional.of(new Produto(1, "Produto 1", new TipoProduto()));
        Integer id = 1;

        Mockito.when(produtoRepository.findById(id)).thenReturn(produto);

        Produto produtoEncontrado = produtoService.porId(id);

        assertEquals(produto.get().getNome(), produtoEncontrado.getNome());

        Mockito.verify(produtoRepository, Mockito.times(1)).findById(id);
    }

    @Test
    @DisplayName("Caso não houver produto por id, lançe uma exception")
    void cenarioProdutoInexistente(){
        Integer id = 10;

        Mockito.when(produtoRepository.findById(id)).thenReturn(Optional.empty());

        NaoEncontradoException exception = assertThrows(NaoEncontradoException.class, () -> produtoService.porId(id));

        assertEquals("Produto não encontrado(a)", exception.getLocalizedMessage());

    }

    @Test
    @DisplayName("Deve retornar o produto criado")
    void testCriaProduto() {

        TipoProduto tipoProduto = new TipoProduto(1, "Perecível");
        Produto produto = new Produto(1, "p1", tipoProduto);
        Produto novoProduto = new Produto(null, "p1", tipoProduto);

        Mockito.when(tipoProdutoService.porId(tipoProduto.getId())).thenReturn(tipoProduto);
        Mockito.when(produtoRepository.save(novoProduto)).thenReturn(produto);

        Produto produtoSalvo = produtoService.criar(novoProduto, novoProduto.getTipoProduto().getId());

        assertEquals(produto.getId(), produtoSalvo.getId());
        Mockito.verify(produtoRepository, Mockito.times(1)).save(novoProduto);

    }

    @Test
    @DisplayName("Deve retornar o produto pelo id")
    void retonaProdutoPeloId() {
        TipoProduto tipoProduto = new TipoProduto(1, "Perecível");
        Produto produto = new Produto(1, "Arroz", tipoProduto);
        Integer idBusca = 1;

        Mockito.when(produtoRepository.findById(idBusca)).thenReturn(Optional.of(produto));

        Produto produtoEncontrado = produtoService.porId(idBusca);

        assertEquals(produtoEncontrado.getId(), idBusca);

        Mockito.verify(produtoRepository, Mockito.times(1)).findById(idBusca);

    }

    @Test
    @DisplayName("Deve retornar ResponseStatusException caso não exista o id")
    void retornaExceptionCasoNaoExiste() {

        Integer idBusca = 100;

        Mockito.when(produtoRepository.findById(idBusca)).thenReturn(Optional.empty());

        NaoEncontradoException exception = assertThrows(NaoEncontradoException.class, () -> produtoService.porId(idBusca));

        assertEquals("Produto não encontrado(a)", exception.getLocalizedMessage());

        Mockito.verify(produtoRepository, Mockito.times(1)).findById(idBusca);
    }

    @Test
    @DisplayName("Dado que tenho 3 produtos no banco, me retone somente os produtos que contém a letra a")
    void retornaProdutoComLetraA(){
        TipoProduto t1 = new TipoProduto(1, "Perecível");
        TipoProduto t2 = new TipoProduto(2, "Não perecível");

        List<Produto> produtosFiltrados = List.of(
                new Produto(1, "Bolacha", t1),
                new Produto(2, "Chocolate", t2)
        );

        Mockito.when(produtoRepository.findByNomeContainingIgnoreCase("a")).thenReturn(produtosFiltrados);

        List<Produto> produtosEncontrados = produtoService.buscaPorParteDoNome("a");

        assertFalse(produtosEncontrados.isEmpty());

        for (int i = 0; i < produtosEncontrados.size(); i++) {

            assertEquals(produtosFiltrados.get(0).getId(), produtosEncontrados.get(0).getId());
            assertEquals(produtosFiltrados.get(0).getNome(), produtosEncontrados.get(0).getNome());
            assertEquals(produtosFiltrados.get(0).getTipoProduto(), produtosEncontrados.get(0).getTipoProduto());

        }


        Mockito.verify(produtoRepository, Mockito.times(1)).findByNomeContainingIgnoreCase("a");
    }

    @Test
    @DisplayName("Dado que não tenho registros com a letra w, retorna lista vazia")
    void retornarNenhumProdutoQuandoBuscarContendoIgnorandoCase(){

        Mockito.when(produtoRepository.findByNomeContainingIgnoreCase("w")).thenReturn(Collections.emptyList());

        List<Produto> produtosEncontrados = produtoService.buscaPorParteDoNome("w");

        assertTrue(produtosEncontrados.isEmpty());

        Mockito.verify(produtoRepository, Mockito.times(1)).findByNomeContainingIgnoreCase("w");
        Mockito.verify(produtoRepository, Mockito.times(0)).findAll();
    }

    @Test
    @DisplayName("Dado que tenho o id no banco e passei o objeto, atualiza com sucesso")
    void dadoQueAtualizacaoCorreta(){
        TipoProduto t1 = new TipoProduto(1, "Perecível");
        Produto produtoAtualizacao = new Produto(null, "Bolacha 2", t1);
        Integer idInformado = 1;
        Produto produtoRetorno = new Produto(idInformado, "Bolacha 2", t1);

        Mockito.when(produtoRepository.save(produtoAtualizacao)).thenReturn(produtoRetorno);
        Mockito.when(produtoRepository.existsById(idInformado)).thenReturn(Boolean.TRUE);

        Produto produtoResposta = produtoService.atualizar(produtoAtualizacao, idInformado);

        assertEquals(idInformado, produtoResposta.getId());
        assertEquals(produtoAtualizacao.getNome(), produtoResposta.getNome());

        Mockito.verify(produtoRepository, Mockito.times(1)).existsById(idInformado);
        Mockito.verify(produtoRepository, Mockito.times(1)).save(Mockito.any());
    }

    @Test
    @DisplayName("Dado que tenho um id que não existe no banco")
    void dadoQueIdInexistenteNoAtualizar(){
        Mockito.when(produtoRepository.existsById(Mockito.any())).thenReturn(Boolean.FALSE);

        assertThrows(NaoEncontradoException.class, () -> produtoService.atualizar(Mockito.any(), 1));
    }

    @Test
    @DisplayName("Quando deletar errado devolva erro")
    void deletarErrado(){
        Mockito.when(produtoRepository.existsById(1)).thenReturn(Boolean.FALSE);

        assertThrows(NaoEncontradoException.class, () -> {
            produtoService.deletar(1);
        });

        Mockito.verify(produtoRepository, Mockito.times(1)).existsById(1);
    }
}