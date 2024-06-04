package school.sptech.backend.service.produtocesta;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import school.sptech.backend.domain.produto.Produto;
import school.sptech.backend.domain.produto.repository.ProdutoRepository;
import school.sptech.backend.domain.produtocesta.entity.ProdutoCesta;
import school.sptech.backend.domain.produtocesta.repository.ProdutoCestaRepository;
import school.sptech.backend.service.produto.ProdutoService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ProdutoCestaServiceTest {
    @InjectMocks
    private ProdutoCestaService produtoService;

    @Mock
    private ProdutoCestaRepository produtoRepository;


    @Test
    @DisplayName("Quando pesquisado por id deve retornar")
    void pesquisaPorIdCerta(){
        ProdutoCesta produtoCesta = new ProdutoCesta();
        produtoCesta.setIdProdutoCesta(1);

        Mockito.when(produtoRepository.findById(1)).thenReturn(Optional.of(produtoCesta));

        ProdutoCesta teste = produtoService.get(1);

        assertEquals(1, teste.getIdProdutoCesta());

        Mockito.verify(produtoRepository, Mockito.times(1)).findById(1);
    }
    @Test
    @DisplayName("Quando pesquisado por id errado deve arremessar erro")
    void pesquisaPorIdErrada(){
        Mockito.when(produtoRepository.findById(1)).thenReturn(Optional.empty());


        assertThrows(HttpServerErrorException.class, () -> {
            produtoService.get(1);
        });

        Mockito.verify(produtoRepository, Mockito.times(1)).findById(1);
    }

    @Test
    @DisplayName("Quando inserido certo deve retornar certo")
    void saveErrada(){

        ProdutoCesta produtoCesta = new ProdutoCesta();
        Produto produto = new Produto();
        produto.setNome("Nome");
        produtoCesta.setProduto(produto);

        ProdutoCesta produtoCestaRet = new ProdutoCesta();
        produtoCestaRet.setIdProdutoCesta(1);
        produtoCestaRet.setProduto(produto);

        Mockito.when(produtoRepository.save(produtoCesta)).thenReturn(produtoCestaRet);

        ProdutoCesta teste = produtoService.adicionar(produtoCesta);

        assertEquals(1, teste.getIdProdutoCesta());
        assertEquals("Nome", teste.getProduto().getNome());

        Mockito.verify(produtoRepository, Mockito.times(1)).save(produtoCesta);
    }

    @Test
    @DisplayName("Quando listado certo deve retornar certo")
    void listadoCerto(){
        ProdutoCesta produtoCesta = new ProdutoCesta();
        Produto produto = new Produto();
        produto.setNome("Nome");
        produtoCesta.setProduto(produto);

        ProdutoCesta produtoCestaRet = new ProdutoCesta();
        produtoCestaRet.setIdProdutoCesta(1);
        produtoCestaRet.setProduto(produto);
        List<ProdutoCesta> produtoCestas = new ArrayList<>();
        produtoCestas.add(produtoCesta);
        produtoCestas.add(produtoCestaRet);

        Mockito.when(produtoRepository.findAll()).thenReturn(produtoCestas);

        List<ProdutoCesta> teste = produtoService.get();

        assertEquals(2, teste.size());
        assertEquals("Nome", teste.get(0).getProduto().getNome());

        Mockito.verify(produtoRepository, Mockito.times(1)).findAll();
    }

    @Test
    @DisplayName("Quando listado vazio deve retornar certo")
    void listadoCertoVazio(){

        List<ProdutoCesta> produtoCestas = Collections.emptyList();


        Mockito.when(produtoRepository.findAll()).thenReturn(produtoCestas);

        List<ProdutoCesta> teste = produtoService.get();

        assertEquals(0, teste.size());

        Mockito.verify(produtoRepository, Mockito.times(1)).findAll();
    }

    @Test
    @DisplayName("Quando deletar errado devolva erro")
    void deletarErrado(){
        Mockito.when(produtoRepository.existsById(1)).thenReturn(false);

        assertThrows(HttpServerErrorException.class, () -> {
           produtoService.deletar(1);
        });


        Mockito.verify(produtoRepository, Mockito.times(1)).existsById(1);
    }


    @Test
    @DisplayName("Quando update certo devolva novo ProdutoCesta")
    void updateCerto(){
        ProdutoCesta produtoCesta = new ProdutoCesta();
        Produto produto = new Produto();
        produto.setNome("Nome");
        produtoCesta.setProduto(produto);

        Mockito.when(produtoRepository.existsById(1)).thenReturn(true);
        Mockito.when(produtoRepository.save(produtoCesta)).thenReturn(produtoCesta);

        ProdutoCesta teste = produtoService.update(1, produtoCesta);


        assertEquals(1, teste.getIdProdutoCesta());
        assertEquals("Nome", teste.getProduto().getNome());


        Mockito.verify(produtoRepository, Mockito.times(1)).existsById(1);
    }

    @Test
    @DisplayName("Quando update errado devolva novo ProdutoCesta")
    void updateErrado(){


        Mockito.when(produtoRepository.existsById(1)).thenReturn(false);



        assertThrows(HttpServerErrorException.class, () -> {
            produtoService.update(1, new ProdutoCesta());
        });


        Mockito.verify(produtoRepository, Mockito.times(1)).existsById(1);
    }



}