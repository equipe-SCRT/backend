//package school.sptech.backend.service.tipoprodutocesta;
//
//import jakarta.persistence.*;
//import lombok.AllArgsConstructor;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//import school.sptech.backend.domain.produto.Produto;
//import school.sptech.backend.domain.tipocesta.TipoCesta;
//
//@Entity
//@Getter
//@Setter
//@AllArgsConstructor
//@NoArgsConstructor
//public class TipoProdutoCesta {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Integer idProdutoTipoCesta;
//    @ManyToOne
//    private Produto produto;
//    @ManyToOne
//    private TipoCesta tipoCesta;
//    private Integer qtdProdutos;
//}
