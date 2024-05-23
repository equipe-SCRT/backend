package school.sptech.backend.service.produtounitario.dto;

import jakarta.persistence.ManyToOne;
import lombok.Data;
import school.sptech.backend.domain.cesta.Cesta;
import school.sptech.backend.domain.metrica.Metrica;
import school.sptech.backend.domain.produto.Produto;
import school.sptech.backend.domain.rota.Rota;

import java.time.LocalDate;

@Data
public class ProdutoUnitarioListagemDto {
    private Integer id;
    private String nome;
    private LocalDate dataValidade;
    private Double peso;
    private Boolean ativo;
    private int origem;
    private int unidadeMedida;
    private Cesta cesta;
    private Produto produto;
    private Rota rota;
    private Metrica metrica;
}
