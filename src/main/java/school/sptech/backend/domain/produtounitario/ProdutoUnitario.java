package school.sptech.backend.domain.produtounitario;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import school.sptech.backend.domain.produto.Produto;

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
    private Boolean ativo;


    private int origem;


    private int unidadeMedida;


    private int cesta;

    @ManyToOne
    private Produto produto;

    private int rota;

    private int metrica;

}
