package school.sptech.backend.service.cesta;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import school.sptech.backend.domain.cesta.Cesta;
import school.sptech.backend.domain.cesta.repository.CestaRepository;
import school.sptech.backend.domain.tipocesta.TipoCesta;
import school.sptech.backend.exception.NaoEncontradoException;
import school.sptech.backend.service.tipocesta.TipoCestaService;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
@ExtendWith(MockitoExtension.class)

class CestaServiceTest {

    @InjectMocks
    private CestaService cestaService;

    @Mock
    private CestaRepository cestaRepository;

    @Mock
    private TipoCestaService tipoCestaService;

    @Test
    @DisplayName("Deve retornar cesta por ID")
    void deveRetornarCestaPorId() {
        TipoCesta tipoCesta = new TipoCesta();
        tipoCesta.setId(1);

        Cesta cesta = new Cesta();
        cesta.setId(1);
        cesta.setLote("Lote A");
        cesta.setDataMontagem(LocalDate.now());
        cesta.setTipoCesta(tipoCesta);

        when(cestaRepository.findById(1)).thenReturn(Optional.of(cesta));

        Cesta encontrada = cestaService.porId(1);

        assertEquals(1, encontrada.getId());
        assertEquals("Lote A", encontrada.getLote());
    }

    @Test
    @DisplayName("Deve lançar exceção quando ID não encontrado")
    void deveLancarExcecaoQuandoIdNaoEncontrado() {
        when(cestaRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(NaoEncontradoException.class, () -> cestaService.porId(1));
    }

    @Test
    @DisplayName("Deve listar todas as cestas")
    void deveListarTodasAsCestas() {
        TipoCesta tipoCesta = new TipoCesta();
        tipoCesta.setId(1);

        Cesta cesta = new Cesta();
        cesta.setId(1);
        cesta.setLote("Lote A");
        cesta.setDataMontagem(LocalDate.now());
        cesta.setTipoCesta(tipoCesta);

        List<Cesta> cestas = Arrays.asList(cesta);
        when(cestaRepository.findAll()).thenReturn(cestas);

        List<Cesta> lista = cestaService.listar();

        assertEquals(1, lista.size());
        assertEquals("Lote A", lista.get(0).getLote());
    }



    @Test
    @DisplayName("Deve deletar uma cesta por ID")
    void deveDeletarUmaCestaPorId() {
        TipoCesta tipoCesta = new TipoCesta();
        tipoCesta.setId(1);

        Cesta cesta = new Cesta();
        cesta.setId(1);
        cesta.setLote("Lote A");
        cesta.setDataMontagem(LocalDate.now());
        cesta.setTipoCesta(tipoCesta);

        when(cestaRepository.findById(1)).thenReturn(Optional.of(cesta));

        cestaService.deletar(1);
    }
}