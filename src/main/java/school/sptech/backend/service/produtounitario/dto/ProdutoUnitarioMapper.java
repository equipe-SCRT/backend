package school.sptech.backend.service.produtounitario.dto;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import school.sptech.backend.domain.cesta.Cesta;
import school.sptech.backend.domain.metrica.Metrica;
import school.sptech.backend.domain.origem.Origem;
import school.sptech.backend.domain.produto.Produto;
import school.sptech.backend.domain.produtounitario.ProdutoUnitario;
import school.sptech.backend.domain.rota.Rota;
import school.sptech.backend.domain.unidademedida.UnidadeMedida;
import school.sptech.backend.service.produtounitario.view.QtdAtivoPorMes;
import school.sptech.backend.service.produtounitario.view.QtdVencidoPorMes;
import school.sptech.backend.service.produtounitario.view.VencidoArrecadado;
import school.sptech.backend.service.produtounitario.view.Vencimento15E30Dias;


import java.util.List;

@Mapper(componentModel = "spring")
public interface ProdutoUnitarioMapper {

    ProdutoUnitarioListagemDto toDto(ProdutoUnitario entity);

    @Mapping(target = "origem", source = "origemId", qualifiedByName = "origemFromId")
    @Mapping(target = "produto", source = "produtoId", qualifiedByName = "produtoFromId")
    ProdutoUnitario toEntity(ProdutoUnitarioCriacaoDto dto);


    @Mapping(target = "origem", source = "origemId", qualifiedByName = "origemFromId")
    @Mapping(target = "produto", source = "produtoId", qualifiedByName = "produtoFromId")
    ProdutoUnitario toEntity(ProdutoUnitarioAtualizacaoDto dto);


    List<ProdutoUnitarioListagemDto> toDto(List<ProdutoUnitario> entities);

    List<ProdutoUnitarioRelatorioDto> toDtoRelatorio(List<ProdutoUnitario> entities);

    @Mapping(target = "origem", source = "origemId", qualifiedByName = "origemFromId")
    @Mapping(target = "produto", source = "produtoId", qualifiedByName = "produtoFromId")
    ProdutoUnitario toDto(ProdutoUnitarioAtualizacaoDto dto);

    QtdVencidoPorMesDto qtdVencidoPorMestoDto(QtdVencidoPorMes entity);
    List<QtdVencidoPorMesDto> qtdVencidoPorMesToDto(List<QtdVencidoPorMes> entity);

    QtdAtivoPorMesDto qtdAtivoPorMesToDto(QtdAtivoPorMes entity);
    List<QtdAtivoPorMesDto> qtdAtivoPorMesToDto(List<QtdAtivoPorMes> entity);

    Vencimento15E30DiasDto vencimento15E30DiasToDto(Vencimento15E30Dias entity);

    VencidoArrecadadoDto vencidoArrecadadoToDto(VencidoArrecadado entity);
    List<VencidoArrecadadoDto> vencidoArrecadadoToDto(List<VencidoArrecadado> entity);


    @Named("origemFromId")
    default Origem origemFromId(Integer id) {
        if (id == null) {
            return null;
        }
        Origem origem = new Origem();
        origem.setId(id);
        return origem;
    }
    @Named("unidadeMedidaFromId")
    default UnidadeMedida unidadeMedidaFromId(Integer id) {
        if (id == null) {
            return null;
        }
        UnidadeMedida unidadeMedida = new UnidadeMedida();
        unidadeMedida.setId(id);
        return unidadeMedida;
    }
    @Named("cestaFromId")
    default Cesta cestaFromId(Integer id) {
        if (id == null) {
            return null;
        }
        Cesta cesta = new Cesta();
        cesta.setId(id);
        return cesta;
    }
    @Named("produtoFromId")
    default Produto produtoFromId(Integer id) {
        if (id == null) {
            return null;
        }
        Produto produto = new Produto();
        produto.setId(id);
        return produto;
    }
    @Named("rotaFromId")
    default Rota rotaFromId(Integer id) {
        if (id == null) {
            return null;
        }
        Rota rota = new Rota();
        rota.setId(id);
        return rota;
    }
    @Named("metricaFromId")
    default Metrica metricaFromId(Integer id) {
        if (id == null) {
            return null;
        }
        Metrica metrica = new Metrica();
        metrica.setId(id);
        return metrica;
    }
    
    
}