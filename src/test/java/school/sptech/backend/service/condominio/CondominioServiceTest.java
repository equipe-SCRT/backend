package school.sptech.backend.service.condominio;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import school.sptech.backend.domain.condominio.Condominio;
import school.sptech.backend.domain.condominio.repository.CondominioRepository;
import school.sptech.backend.domain.endereco.Endereco;
import school.sptech.backend.exception.NaoEncontradoException;
import school.sptech.backend.service.endereco.EnderecoService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class CondominioServiceTest {

    @InjectMocks
    private CondominioService service;

    @Mock
    private CondominioRepository repository;

    @Mock
    private EnderecoService enderecoService;

    @Test
    @DisplayName("Caso houver uma lista com 3 condomínios, retorne os 3 condomínios")
    void cenarioListaComCondominios() {
        List<Condominio> lista = List.of(
                new Condominio(),
                new Condominio(),
                new Condominio()
        );

        Mockito.when(repository.findAll()).thenReturn(lista);

        List<Condominio> condominios = service.listar();

        assertFalse(condominios.isEmpty());
        assertEquals(condominios.get(0).getId(), lista.get(0).getId());
        assertEquals(condominios.get(0).getNome(), lista.get(0).getNome());
        assertEquals(condominios.get(0).getEndereco(), lista.get(0).getEndereco());

        Mockito.verify(repository, Mockito.times(1)).findAll();
    }

    @Test
    @DisplayName("Caso não houver nada no banco, retorne uma lista vazia")
    void cenarioListaVazia() {
        List<Condominio> lista = new ArrayList<>();

        Mockito.when(repository.findAll()).thenReturn(lista);

        List<Condominio> condominios = service.listar();

        assertTrue(condominios.isEmpty());
    }

    @Test
    @DisplayName("Deve retornar o objeto salvo")
    void testSalvaProduto() {
        Endereco endereco = new Endereco(1, "Rua", "Teste", "250", "09981145");
        Condominio condominio = new Condominio(1, "teste", endereco);
        Condominio novoCondominio = new Condominio(null, "teste", endereco);

        Mockito.when(enderecoService.porId(endereco.getId())).thenReturn(endereco);
        Mockito.when(service.criar(novoCondominio)).thenReturn(condominio);

        Condominio condominioSalvo = service.criar(novoCondominio);

        assertEquals(condominio.getId(), condominioSalvo.getId());
        Mockito.verify(repository, Mockito.times(1)).save(novoCondominio);

    }

    @Test
    @DisplayName("Caso houver o condomínio pelo id, retorne o mesmo")
    void cenarioCondominioExistente() {
        Optional<Condominio> condominio = Optional.of(new Condominio());
        condominio.get().setId(1);
        Integer id = 1;

        Mockito.when(repository.findById(id)).thenReturn(condominio);

        Condominio condominioEncontrado = service.porId(id);

        assertEquals(condominio.get().getNome(), condominioEncontrado.getNome());

        Mockito.verify(repository, Mockito.times(1)).findById(id);
    }

    @Test
    @DisplayName("Caso não houver o condomínio pelo id, lance uma exception")
    void cenarioCondominioInexistente() {
        Integer id = 10;

        Mockito.when(repository.findById(id)).thenReturn(Optional.empty());

        NaoEncontradoException exception = assertThrows(
                NaoEncontradoException.class,
                () -> service.porId(id)
        );

        assertEquals("Condomínio não encontrado(a)", exception.getLocalizedMessage());
    }

    @Test
    @DisplayName("Ao buscar nome ignorando case contendo, retornar o produto")
    void cenarioBuscaPorNomeContendoIgnoreCase() {
        Condominio resposta = new Condominio();

        Mockito.when(repository.findByNomeIgnoreCase("a")).thenReturn(Optional.of(resposta));

        Condominio condominio = service.porNome("a");

        assertNotNull(condominio);
    }

    @Test
    @DisplayName("Ao buscar nome ignorando case não contendo, retornar nulo")
    void cenarioBuscaPorNomeNaoContendoIgnoreCase() {
        Mockito.when(repository.findByNomeIgnoreCase("w")).thenReturn(Optional.empty());

        assertThrows(NaoEncontradoException.class, () -> service.porNome("w"));

        Mockito.verify(repository, Mockito.times(1)).findByNomeIgnoreCase("w");
    }

    @Test
    @DisplayName("Dado que tenho o id no banco e passei o objeto, atualiza com sucesso")
    void cenarioAtualizacaoCorreta() {
        Condominio condominioAtualizacao = new Condominio();
        condominioAtualizacao.setId(null);
        Integer idInformado = 1;
        Condominio condominioRetorno = new Condominio();
        condominioRetorno.setId(idInformado);

        Mockito.when(repository.save(condominioAtualizacao)).thenReturn(condominioRetorno);
        Mockito.when(repository.existsById(idInformado)).thenReturn(Boolean.TRUE);

        Condominio resposta = service.atualizar(idInformado, condominioAtualizacao);

        assertEquals(idInformado, resposta.getId());
        assertEquals(condominioAtualizacao.getNome(), resposta.getNome());

        Mockito.verify(repository, Mockito.times(1)).existsById(idInformado);
        Mockito.verify(repository, Mockito.times(1)).save(Mockito.any());
    }

    @Test
    @DisplayName("Dado que tenho um id que não existe no banco")
    void cenarioIdNaoExistenteNoAtualizar() {
        Mockito.when(repository.existsById(Mockito.any())).thenReturn(Boolean.FALSE);

        assertThrows(NaoEncontradoException.class,
                () -> service.atualizar(1, Mockito.any()));
    }

    @Test
    @DisplayName("Quando deletar errado devolva erro")
    void deletarErrado(){
        Mockito.when(repository.existsById(1)).thenReturn(false);

        assertThrows(NaoEncontradoException.class, () -> {
            service.deletar(1);
        });

        Mockito.verify(repository, Mockito.times(1)).existsById(1);
    }
}