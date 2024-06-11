package school.sptech.backend.service.metrica;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import school.sptech.backend.domain.metrica.Metrica;
import school.sptech.backend.domain.metrica.repository.MetricaRepository;
import school.sptech.backend.domain.usuario.entity.Usuario;
import school.sptech.backend.exception.NaoEncontradoException;

@ExtendWith(MockitoExtension.class)
public class MetricaServiceTest {

    @InjectMocks
    private MetricaService service;

    @Mock
    private MetricaRepository repository;

    @Test
    @DisplayName("Caso nao houver nada no banco retorna uma lista vazia")
    void cenarioListaProdutoVazia() {

        List<Metrica> lista = new ArrayList<>();

        Mockito.when(service.listar()).thenReturn(lista);

        List<Metrica> historicos = service.listar();

        assertTrue(historicos.isEmpty());

        Mockito.verify(repository, Mockito.times(1)).findAll();
    }

    @Test
    @DisplayName("Deve retornar lista com 3 produtos, caso houver 3 produtos")
    void deveRetornarListaProduto() {

        Usuario usuario = new Usuario();
        usuario.setEmail("gabriel");
        usuario.setSenha("gabriel");
        usuario.setTipoUsuario(1);

        LocalDate data = LocalDate.now();

        List<Metrica> metricas = List.of(
                new Metrica(1, data, usuario),
                new Metrica(2, data, usuario),
                new Metrica(3, data, usuario));

        Mockito.when(service.listar()).thenReturn(metricas);

        List<Metrica> metricaRetorno = service.listar();

        assertEquals(metricas.size(), metricaRetorno.size());
        assertEquals(3, metricaRetorno.size());
        assertFalse(metricaRetorno.isEmpty());
        assertEquals(metricaRetorno.get(0).getAlteracao(), metricaRetorno.get(0).getAlteracao());

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
        Integer id = 5;

        Optional<Metrica> metrica = Optional.of(
            new Metrica(5, data, usuario)
        );
        

        // WHEN
        Mockito.when(repository.findById(id)).thenReturn(metrica);

        // THEN
        Metrica metricaEncontrado = service.porId(id);

        // ASSERT
        assertEquals(metrica.get().getIdMetrica(), metricaEncontrado.getIdMetrica());

        Mockito.verify(repository, Mockito.times(1)).findById(id);
    }

    @Test
    @DisplayName("Deve retornar NaoEncontradoException caso não exista o id")
    void retornaNaoEncontradoException() {

        Integer id = 100;

        Mockito.when(repository.findById(id)).thenReturn(Optional.empty());

        NaoEncontradoException exception = assertThrows(NaoEncontradoException.class, () -> service.porId(id));

        assertEquals("Metrica não encontrado(a)", exception.getLocalizedMessage());

        Mockito.verify(repository, Mockito.times(1)).findById(id);
    }

}
