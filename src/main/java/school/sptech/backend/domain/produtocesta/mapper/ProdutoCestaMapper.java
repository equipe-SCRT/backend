package school.sptech.backend.domain.produtocesta.mapper;

import school.sptech.backend.domain.produto.Produto;
import school.sptech.backend.domain.produtocesta.entity.ProdutoCesta;
import school.sptech.backend.service.produtocesta.dto.ProdutoCestaEntityDto;

import java.util.ArrayList;
import java.util.List;

public class ProdutoCestaMapper {

    public static List<ProdutoCestaEntityDto> toEntity(List<ProdutoCesta> produtoCestas, List<Produto> produtos){
        List<ProdutoCestaEntityDto> produtoCestaEntityDtos = new ArrayList<>();
        for (int i = 0; i < produtoCestas.size(); i++) {
            ProdutoCestaEntityDto produtoCestaEntityDto = new ProdutoCestaEntityDto();
            produtoCestaEntityDto.setFkTipoProduto(produtoCestas.get(i).getIdProduto());
            produtoCestaEntityDto.setIdCesta(produtoCestas.get(i).getIdCesta());
            produtoCestaEntityDto.setNome(produtos.get(i).getNome());

            produtoCestaEntityDtos.add(produtoCestaEntityDto);
        }
        return produtoCestaEntityDtos;
    }

    public static ProdutoCestaEntityDto toEntity(ProdutoCesta produtoCesta, Produto produto){

        ProdutoCestaEntityDto produtoCestaEntityDto = new ProdutoCestaEntityDto();
        produtoCestaEntityDto.setFkTipoProduto(produtoCesta.getIdProduto());
        produtoCestaEntityDto.setIdCesta(produtoCesta.getIdCesta());
        produtoCestaEntityDto.setNome(produto.getNome());


        return produtoCestaEntityDto;
    }
}
