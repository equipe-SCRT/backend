package school.sptech.backend.service.produtounitario;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import school.sptech.backend.domain.campanha.Campanha;
import school.sptech.backend.domain.cesta.Cesta;
import school.sptech.backend.domain.condominio.Condominio;
import school.sptech.backend.domain.metrica.Metrica;
import school.sptech.backend.domain.origem.Origem;
import school.sptech.backend.domain.produto.Produto;
import school.sptech.backend.domain.produtounitario.ProdutoUnitario;
import school.sptech.backend.domain.produtounitario.repository.ProdutoUnitarioRepository;
import school.sptech.backend.domain.rota.Rota;
import school.sptech.backend.domain.tipocesta.TipoCesta;
import school.sptech.backend.domain.tipoproduto.TipoProduto;
import school.sptech.backend.domain.unidademedida.UnidadeMedida;
import school.sptech.backend.domain.usuario.entity.Usuario;
import school.sptech.backend.exception.NaoEncontradoException;
import school.sptech.backend.service.cesta.CestaService;
import school.sptech.backend.service.metrica.MetricaService;
import school.sptech.backend.service.origem.OrigemService;
import school.sptech.backend.service.produto.ProdutoService;
import school.sptech.backend.service.rota.RotaService;
import school.sptech.backend.service.unidademedida.UnidadeMedidaService;

import java.sql.Time;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ProdutoUnitarioServiceTest {
    @InjectMocks
    private ProdutoUnitarioService produtoUnitarioService;

    @Mock
    private OrigemService origemService;

    @Mock
    private UnidadeMedidaService unidadeMedidaService;

    @Mock
    private CestaService cestaService;

    @Mock
    private ProdutoService produtoService;

    @Mock
    private RotaService rotaService;

    @Mock
    private MetricaService metricaService;

    @Mock
    private ProdutoUnitarioRepository produtoUnitarioRepository;

    @Test
    @DisplayName("Caso nao houver nada no banco retorna uma lista vazia")
    void retornaListaVazia() {

        List<ProdutoUnitario> lista = Collections.emptyList();

        Mockito.when(produtoUnitarioService.listar()).thenReturn(lista);

        List<ProdutoUnitario> produtosUnitarios = produtoUnitarioService.listar();

        assertTrue(produtosUnitarios.isEmpty());
        Mockito.verify(produtoUnitarioRepository, Mockito.times(1)).findAll();
    }

    @Test
    @DisplayName("Caso houver uma lista com 3 produtos, retorne os 3 produtos")
    void cenarioListaTresProdutos() {

        List<ProdutoUnitario> lista = List.of(
                new ProdutoUnitario(1, "Produto 1", LocalDate.now(), 20.0, true, new Origem(), new UnidadeMedida(), new Cesta(), new Produto(), new Rota(), new Metrica()),
                new ProdutoUnitario(2, "Produto 2", LocalDate.now(), 10.0, true, new Origem(), new UnidadeMedida(), new Cesta(), new Produto(), new Rota(), new Metrica()),
                new ProdutoUnitario(3, "Produto 3", LocalDate.now(), 5.0, true, new Origem(), new UnidadeMedida(), new Cesta(), new Produto(), new Rota(), new Metrica())
        );

        Mockito.when(produtoUnitarioService.listar()).thenReturn(lista);

        List<ProdutoUnitario> produtos = produtoUnitarioService.listar();

        assertFalse(produtos.isEmpty());
        assertEquals(3, produtos.size());


        for (int i = 0; i < produtos.size(); i++) {

            assertEquals(lista.get(0).getId(), produtos.get(0).getId());
            assertEquals(lista.get(0).getNome(), produtos.get(0).getNome());
            assertEquals(lista.get(0).getDataValidade(), produtos.get(0).getDataValidade());
            assertEquals(lista.get(0).getPeso(), produtos.get(0).getPeso());
            assertEquals(lista.get(0).isAtivo(), produtos.get(0).isAtivo());
            assertEquals(lista.get(0).getOrigem(), produtos.get(0).getOrigem());
            assertEquals(lista.get(0).getUnidadeMedida(), produtos.get(0).getUnidadeMedida());
            assertEquals(lista.get(0).getCesta(), produtos.get(0).getCesta());
            assertEquals(lista.get(0).getProduto(), produtos.get(0).getProduto());
            assertEquals(lista.get(0).getRota(), produtos.get(0).getRota());
            assertEquals(lista.get(0).getMetrica(), produtos.get(0).getMetrica());

        }

    }

    @Test
    @DisplayName("Caso houver o produto pelo id, retorne o mesmo")
    void cenarioProdutoExistente(){
        Optional<ProdutoUnitario> produto = Optional.of( new ProdutoUnitario(1, "Produto 1", LocalDate.now(), 20.0, true, new Origem(), new UnidadeMedida(), new Cesta(), new Produto(), new Rota(), new Metrica()));
        Integer id = 1;

        Mockito.when(produtoUnitarioRepository.findById(id)).thenReturn(produto);

        ProdutoUnitario produtoEncontrado = produtoUnitarioService.porId(id);

        assertEquals(produto.get().getNome(), produtoEncontrado.getNome());

        Mockito.verify(produtoUnitarioRepository, Mockito.times(1)).findById(id);
    }

    @Test
    @DisplayName("Caso não houver produto por id, lançe uma exception")
    void cenarioProdutoInexistente(){
        Integer id = 10;

        Mockito.when(produtoUnitarioRepository.findById(id)).thenReturn(Optional.empty());

        NaoEncontradoException exception = assertThrows(NaoEncontradoException.class, () -> produtoUnitarioService.porId(id));

        assertEquals("Produto Unitário não encontrado(a)", exception.getLocalizedMessage());

    }

    @Test
    @DisplayName("Deve retornar o produto criado")
    void testCriaProduto() {
        Origem origem = new Origem(1, 1, 1, new Condominio(), new Campanha());
        UnidadeMedida unidadeMedida = new UnidadeMedida(1, "Quilos", "KG");
        Cesta cesta = new Cesta(1, "Lote 1", LocalDate.now(), new TipoCesta());
        Produto p1 = new Produto(1, "Produto 1", new TipoProduto());
        Rota rota = new Rota(1, "Rota 1", "km rodados", 5, LocalDate.now(), new Time(12,0,0), new Time(17, 0, 0));
        Metrica metrica = new Metrica(1, LocalDate.now(), new Usuario());

        ProdutoUnitario produto =  new ProdutoUnitario(1, "Produto 1", LocalDate.now(), 20.0, true, origem, unidadeMedida, cesta, p1, rota, metrica);
        ProdutoUnitario novoProduto =  new ProdutoUnitario(null, "Produto 1", LocalDate.now(), 20.0, true, origem, unidadeMedida, cesta, p1, rota, metrica);

        Mockito.when(origemService.porId(origem.getId())).thenReturn(origem);
        Mockito.when(unidadeMedidaService.porId(unidadeMedida.getId())).thenReturn(unidadeMedida);
        Mockito.when(cestaService.porId(cesta.getId())).thenReturn(cesta);
        Mockito.when(produtoService.porId(p1.getId())).thenReturn(p1);
        Mockito.when(rotaService.porId(rota.getId())).thenReturn(rota);
        Mockito.when(metricaService.porId(metrica.getIdMetrica())).thenReturn(metrica);

        Mockito.when(produtoUnitarioRepository.save(novoProduto)).thenReturn(produto);

        ProdutoUnitario produtoSalvo = produtoUnitarioService.criar(novoProduto, novoProduto.getOrigem().getId(), novoProduto.getUnidadeMedida().getId(), novoProduto.getCesta().getId(), novoProduto.getProduto().getId(), novoProduto.getRota().getId(), novoProduto.getMetrica().getIdMetrica());

        assertEquals(produto.getId(), produtoSalvo.getId());
        Mockito.verify(produtoUnitarioRepository, Mockito.times(1)).save(novoProduto);

    }

    @Test
    @DisplayName("Deve retornar o produto pelo id")
    void retonaProdutoPeloId() {
        ProdutoUnitario produto =  new ProdutoUnitario(1, "Produto 1", LocalDate.now(), 20.0, true, new Origem(), new UnidadeMedida(), new Cesta(), new Produto(), new Rota(), new Metrica());
        Integer idBusca = 1;

        Mockito.when(produtoUnitarioRepository.findById(idBusca)).thenReturn(Optional.of(produto));

        ProdutoUnitario produtoEncontrado = produtoUnitarioService.porId(idBusca);

        assertEquals(produtoEncontrado.getId(), idBusca);

        Mockito.verify(produtoUnitarioRepository, Mockito.times(1)).findById(idBusca);

    }

    @Test
    @DisplayName("Deve retornar ResponseStatusException caso não exista o id")
    void retornaExceptionCasoNaoExiste() {

        Integer idBusca = 100;

        Mockito.when(produtoUnitarioRepository.findById(idBusca)).thenReturn(Optional.empty());

        NaoEncontradoException exception = assertThrows(NaoEncontradoException.class, () -> produtoUnitarioService.porId(idBusca));

        assertEquals("Produto Unitário não encontrado(a)", exception.getLocalizedMessage());

        Mockito.verify(produtoUnitarioRepository, Mockito.times(1)).findById(idBusca);
    }

    @Test
    @DisplayName("Dado que tenho 3 produtos no banco, me retone somente os produtos que contém a letra a")
    void retornaProdutoComLetraA(){

        List<ProdutoUnitario> produtosFiltrados = List.of(
                new ProdutoUnitario(1, "Bolacha", LocalDate.now(), 20.0, true, new Origem(), new UnidadeMedida(), new Cesta(), new Produto(), new Rota(), new Metrica()),
                new ProdutoUnitario(2, "Chocolate", LocalDate.now(), 20.0, true, new Origem(), new UnidadeMedida(), new Cesta(), new Produto(), new Rota(), new Metrica())
        );

        Mockito.when(produtoUnitarioRepository.findByNomeContainingIgnoreCase("a")).thenReturn(produtosFiltrados);

        List<ProdutoUnitario> produtosEncontrados = produtoUnitarioService.buscaPorParteDoNome("a");

        assertFalse(produtosEncontrados.isEmpty());

        for (int i = 0; i < produtosEncontrados.size(); i++) {

            assertEquals(produtosFiltrados.get(0).getId(), produtosEncontrados.get(0).getId());
            assertEquals(produtosFiltrados.get(0).getNome(), produtosEncontrados.get(0).getNome());
            assertEquals(produtosFiltrados.get(0).getDataValidade(), produtosEncontrados.get(0).getDataValidade());
            assertEquals(produtosFiltrados.get(0).getPeso(), produtosEncontrados.get(0).getPeso());
            assertEquals(produtosFiltrados.get(0).isAtivo(), produtosEncontrados.get(0).isAtivo());
            assertEquals(produtosFiltrados.get(0).getOrigem(), produtosEncontrados.get(0).getOrigem());
            assertEquals(produtosFiltrados.get(0).getUnidadeMedida(), produtosEncontrados.get(0).getUnidadeMedida());
            assertEquals(produtosFiltrados.get(0).getCesta(), produtosEncontrados.get(0).getCesta());
            assertEquals(produtosFiltrados.get(0).getProduto(), produtosEncontrados.get(0).getProduto());
            assertEquals(produtosFiltrados.get(0).getRota(), produtosEncontrados.get(0).getRota());
            assertEquals(produtosFiltrados.get(0).getMetrica(), produtosEncontrados.get(0).getMetrica());

        }


        Mockito.verify(produtoUnitarioRepository, Mockito.times(1)).findByNomeContainingIgnoreCase("a");
    }

    @Test
    @DisplayName("Dado que não tenho registros com a letra w, retorna lista vazia")
    void retornarNenhumProdutoQuandoBuscarContendoIgnorandoCase(){

        Mockito.when(produtoUnitarioRepository.findByNomeContainingIgnoreCase("w")).thenReturn(Collections.emptyList());

        List<ProdutoUnitario> produtosEncontrados = produtoUnitarioService.buscaPorParteDoNome("w");

        assertTrue(produtosEncontrados.isEmpty());

        Mockito.verify(produtoUnitarioRepository, Mockito.times(1)).findByNomeContainingIgnoreCase("w");
        Mockito.verify(produtoUnitarioRepository, Mockito.times(0)).findAll();
    }

    @Test
    @DisplayName("Dado que tenho o id no banco e passei o objeto, atualiza com sucesso")
    void dadoQueAtualizacaoCorreta(){
        ProdutoUnitario produtoAtualizacao = new ProdutoUnitario(null, "Bolacha 2", LocalDate.now(), 20.0, true, new Origem(), new UnidadeMedida(), new Cesta(), new Produto(), new Rota(), new Metrica());
        Integer idInformado = 1;
        ProdutoUnitario produtoRetorno = new ProdutoUnitario(idInformado, "Bolacha 2", LocalDate.now(), 20.0, true, new Origem(), new UnidadeMedida(), new Cesta(), new Produto(), new Rota(), new Metrica());

        Mockito.when(produtoUnitarioRepository.save(produtoAtualizacao)).thenReturn(produtoRetorno);
        Mockito.when(produtoUnitarioRepository.existsById(idInformado)).thenReturn(Boolean.TRUE);

        ProdutoUnitario produtoResposta = produtoUnitarioService.atualizar(produtoAtualizacao, idInformado);

        assertEquals(idInformado, produtoResposta.getId());
        assertEquals(produtoAtualizacao.getNome(), produtoResposta.getNome());

        Mockito.verify(produtoUnitarioRepository, Mockito.times(1)).existsById(idInformado);
        Mockito.verify(produtoUnitarioRepository, Mockito.times(1)).save(Mockito.any());
    }

    @Test
    @DisplayName("Dado que tenho um id que não existe no banco")
    void dadoQueIdInexistenteNoAtualizar(){
        Mockito.when(produtoUnitarioRepository.existsById(Mockito.any())).thenReturn(Boolean.FALSE);

        assertThrows(NaoEncontradoException.class, () -> produtoUnitarioService.atualizar(Mockito.any(), 1));
    }

    @Test
    @DisplayName("Quando deletar por id que não existe no banco devolve erro")
    void deletarComIdInexistente(){
        Mockito.when(produtoUnitarioRepository.existsById(1)).thenReturn(Boolean.FALSE);

        assertThrows(NaoEncontradoException.class, () -> {
            produtoUnitarioService.deletar(1);
        });

        Mockito.verify(produtoUnitarioRepository, Mockito.times(1)).existsById(1);
    }

    @Test
    @DisplayName("Quando deletar por id que existe no banco deleta produto unitario")
    void deletarComIdExistente(){
        Integer idInformado = 1;

        Mockito.when(produtoUnitarioRepository.existsById(idInformado)).thenReturn(Boolean.TRUE);

        produtoUnitarioService.deletar(idInformado);

        Mockito.verify(produtoUnitarioRepository, Mockito.times(1)).existsById(idInformado);
        Mockito.verify(produtoUnitarioRepository, Mockito.times(1)).deleteById(idInformado);
    }

}