package school.sptech.backend.domain.produtounitario;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import school.sptech.backend.domain.cesta.Cesta;
import school.sptech.backend.domain.metrica.Metrica;
import school.sptech.backend.domain.origem.Origem;
import school.sptech.backend.domain.produto.Produto;
import school.sptech.backend.domain.rota.Rota;
import school.sptech.backend.domain.unidademedida.UnidadeMedida;

import java.time.LocalDate;

@Entity
@Getter
@Setter
public class ProdutoUnitario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nome;
    private LocalDate dataValidade;
    private Double peso;
    private boolean ativo;

    @ManyToOne
    private Origem origem;

    @ManyToOne
    private UnidadeMedida unidadeMedida;

    @ManyToOne
    private Cesta cesta;

    @ManyToOne
    private Produto produto;

    @ManyToOne
    private Rota rota;

    @ManyToOne
    private Metrica metrica;

}
