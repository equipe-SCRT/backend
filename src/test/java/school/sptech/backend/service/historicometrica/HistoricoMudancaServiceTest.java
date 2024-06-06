package school.sptech.backend.service.historicometrica;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.hibernate.type.descriptor.java.LocalDateJavaType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import school.sptech.backend.domain.historicomudanca.HistoricoMudanca;
import school.sptech.backend.domain.historicomudanca.repository.HistoricoMudancaRepository;
import school.sptech.backend.domain.usuario.entity.Usuario;
import school.sptech.backend.exception.NaoEncontradoException;
import school.sptech.backend.service.historicomudanca.HistoricoMudancaService;

@ExtendWith(MockitoExtension.class)
class HistoricoMudancaServiceTest {

    @InjectMocks
    private HistoricoMudancaService service;

    @Mock
    private HistoricoMudancaRepository repository;

    @Test
    @DisplayName("Caso nao houver nada no banco retorna uma lista vazia")
    void cenarioListaVazia() {

        List<HistoricoMudanca> lista = new ArrayList<>();

        Mockito.when(service.listar()).thenReturn(lista);

        List<HistoricoMudanca> historicos = service.listar();

        assertTrue(historicos.isEmpty());

        Mockito.verify(repository, Mockito.times(1)).findAll();
    }

    @Test
    @DisplayName("Deve retornar lista com 3 registros, caso a lista tenha 3 registros")
    void deveRetornarLista() {

        Usuario usuario = new Usuario();
        usuario.setEmail("gabriel");
        usuario.setSenha("gabriel");
        usuario.setTipoUsuario(1);

        LocalDate data = LocalDate.of(2024, 05, 29);

        List<HistoricoMudanca> HistoricoMudancas = List.of(
                new HistoricoMudanca(1, data, usuario),
                new HistoricoMudanca(2, data, usuario),
                new HistoricoMudanca(3, data, usuario));

        Mockito.when(service.listar()).thenReturn(HistoricoMudancas);

        List<HistoricoMudanca> HistoricoMudancasRetorno = service.listar();

        assertEquals(HistoricoMudancas.size(), HistoricoMudancasRetorno.size());
        assertEquals(3, HistoricoMudancasRetorno.size());
        assertFalse(HistoricoMudancasRetorno.isEmpty());
        assertEquals(HistoricoMudancas.get(0).getDataHora(), HistoricoMudancasRetorno.get(0).getDataHora());

        Mockito.verify(repository, Mockito.times(1)).findAll();
    }

    @Test
    @DisplayName("Caso houver o produto pelo id, retorne o mesmo")
    void cenarioHistoricoExistente() {
        // GIVEN
        Usuario usuario = new Usuario();
        usuario.setEmail("gabriel");
        usuario.setSenha("gabriel");
        usuario.setTipoUsuario(1);

        LocalDate data = LocalDate.of(2024, 05, 29);

        Optional<HistoricoMudanca> historico = Optional.of(
                new HistoricoMudanca(1, data, usuario));
        Integer id = 1;

        // WHEN
        Mockito.when(repository.findById(id)).thenReturn(historico);

        // THEN
        HistoricoMudanca historicoEncontrado = service.porId(id);

        // ASSERT
        assertEquals(historicoEncontrado.getIdHistoricoMudanca(), historicoEncontrado.getIdHistoricoMudanca());

        Mockito.verify(repository, Mockito.times(1)).findById(id);
    }

    @Test
    @DisplayName("Deve retornar NaoEncontradoException caso não exista o id")
    void retornaNaoEncontradoException() {

        Integer id = 100;

        Mockito.when(repository.findById(id)).thenReturn(Optional.empty());

        NaoEncontradoException exception = assertThrows(NaoEncontradoException.class, () -> service.porId(id));

        assertEquals("Historico Mudança não encontrado(a)", exception.getLocalizedMessage());
        Mockito.verify(repository, Mockito.times(1)).findById(id);

    }

}
