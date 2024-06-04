package school.sptech.backend.service.tipocesta;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import school.sptech.backend.domain.tipocesta.TipoCesta;
import school.sptech.backend.domain.tipocesta.repository.TipoCestaRepository;
import school.sptech.backend.exception.NaoEncontradoException;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)

class TipoCestaServiceTest {

    @InjectMocks
    private TipoCestaService tipoCestaService;

    @Mock
    private TipoCestaRepository tipoCestaRepository;

    @Test
    @DisplayName("Deve retornar TipoCesta por ID")
    void deveRetornarTipoCestaPorId() {
        TipoCesta tipoCesta = new TipoCesta();
        tipoCesta.setId(1);
        tipoCesta.setNome("Cesta Básica");

        when(tipoCestaRepository.findById(1)).thenReturn(Optional.of(tipoCesta));

        TipoCesta encontrada = tipoCestaService.porId(1);

        assertEquals(1, encontrada.getId());
        assertEquals("Cesta Básica", encontrada.getNome());
    }

    @Test
    @DisplayName("Deve lançar exceção quando ID não encontrado")
    void deveLancarExcecaoQuandoIdNaoEncontrado() {
        when(tipoCestaRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(NaoEncontradoException.class, () -> tipoCestaService.porId(1));
    }

    @Test
    @DisplayName("Deve listar todos os tipos de cesta")
    void deveListarTodosOsTiposDeCesta() {
        TipoCesta tipoCesta1 = new TipoCesta();
        tipoCesta1.setId(1);
        tipoCesta1.setNome("Cesta Básica");

        TipoCesta tipoCesta2 = new TipoCesta();
        tipoCesta2.setId(2);
        tipoCesta2.setNome("Cesta Premium");

        List<TipoCesta> tiposCesta = Arrays.asList(tipoCesta1, tipoCesta2);
        when(tipoCestaRepository.findAll()).thenReturn(tiposCesta);

        List<TipoCesta> lista = tipoCestaService.listar();

        assertEquals(2, lista.size());
        assertEquals("Cesta Básica", lista.get(0).getNome());
        assertEquals("Cesta Premium", lista.get(1).getNome());
    }
    
    @Test
    @DisplayName("Deve deletar um tipo de cesta por ID")
    void deveDeletarUmTipoDeCestaPorId() {
        TipoCesta tipoCesta = new TipoCesta();
        tipoCesta.setId(1);
        tipoCesta.setNome("Cesta Básica");

        when(tipoCestaRepository.findById(1)).thenReturn(Optional.of(tipoCesta));

        tipoCestaService.deletar(1);

    }
}