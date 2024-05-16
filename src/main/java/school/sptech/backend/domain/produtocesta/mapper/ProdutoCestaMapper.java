package school.sptech.backend.domain.produtocesta.mapper;

import school.sptech.backend.domain.produtocesta.entity.ProdutoCesta;
import school.sptech.backend.service.produtocesta.dto.ProdutoCestaEntityDto;

import java.util.ArrayList;
import java.util.List;

public class ProdutoCestaMapper {

    public static List<ProdutoCestaEntityDto> toDto(){
        List<ProdutoCestaEntityDto> produtoCestaEntityDtos = new ArrayList<>();
        for (int i = 0; i < produtoCestas.size(); i++) {
            ProdutoCestaEntityDto produtoCestaEntityDto = new ProdutoCestaEntityDto();
            produtoCestaEntityDto.setIdCesta(produtoCestas.get(i).getCesta());
            produtoCestaEntityDto.setIdProduto(produtoCestas.get(i).getProduto().getId());

            produtoCestaEntityDtos.add(produtoCestaEntityDto);
        }
        return produtoCestaEntityDtos;
    }

    public static ProdutoCestaEntityDto toDto(ProdutoCesta produtoCesta){

        ProdutoCestaEntityDto produtoCestaEntityDto = new ProdutoCestaEntityDto();
        produtoCestaEntityDto.setIdCesta(produtoCesta.getCesta());
        produtoCestaEntityDto.setIdProduto(produtoCesta.getProduto().getId());


        return produtoCestaEntityDto;
    }
}
