package school.sptech.backend.service.produtounitario.view;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "v_produto_unitario_vencido_arrecadado")
public class VencidoArrecadado {
    @Id
    private String nome;
    private Integer vencido;
    private Integer arrecadado;
}
