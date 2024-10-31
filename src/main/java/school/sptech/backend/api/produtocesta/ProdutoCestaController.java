package school.sptech.backend.api.produtocesta;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.bind.Nested;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.backend.api.BaseController;
import school.sptech.backend.domain.produtocesta.ProdutoCesta;
import school.sptech.backend.service.produtocesta.dto.ProdutoCestaCriacaoDto;
import school.sptech.backend.service.produtocesta.dto.ProdutoCestaListagemDto;
import school.sptech.backend.service.produtocesta.dto.ProdutoCestaMapper;
import school.sptech.backend.service.produtocesta.ProdutoCestaService;
import school.sptech.backend.service.produtocesta.dto.ProdutoCestaEntityDto;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/produto-cestas")
public class ProdutoCestaController {

    private final ProdutoCestaService service;
    private final ProdutoCestaMapper mapper;

    @PostMapping
    public ResponseEntity<ProdutoCestaEntityDto> criar(@Valid @RequestBody ProdutoCestaCriacaoDto produtoCestaCriacaoDto){

        Integer produtoId = produtoCestaCriacaoDto.getProdutoId();
        Integer tipoCestaId = produtoCestaCriacaoDto.getTipoCestaId();

        ProdutoCesta adicionado = service.criar(mapper.toEntity(produtoCestaCriacaoDto), produtoId, tipoCestaId);
        ProdutoCestaEntityDto dto = mapper.toDto(adicionado);
        return ResponseEntity.status(201).body(dto);

    }
    @GetMapping
    public ResponseEntity<List<ProdutoCestaEntityDto>> listar(){
        List<ProdutoCesta> produtoCestas = service.listar();
        List<ProdutoCestaEntityDto> dto = mapper.toDto(produtoCestas);
        if (dto.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.status(200).body(dto);    }

    @GetMapping("/{id}")
    public ResponseEntity<List<ProdutoCestaListagemDto>> porId(@PathVariable Integer id){
        List<ProdutoCesta> produtoCesta = service.porId(id);

        List<ProdutoCestaListagemDto> listaProdutos = new ArrayList<>();
        for(ProdutoCesta p : produtoCesta){
            var produto = new ProdutoCestaListagemDto();
            produto.setId(p.getId());
            produto.setQtdProduto(p.getQtdProduto());
            produto.setProduto(p.getProduto().getNome());
            listaProdutos.add(produto);
        }
        return ResponseEntity.status(200).body(listaProdutos);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id){
        service.deletar(id);
        return ResponseEntity.status(204).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProdutoCestaEntityDto> atualizar(@PathVariable Integer id, @Valid @RequestBody ProdutoCesta produtoCesta){
        ProdutoCesta updated = service.atualizar(id, produtoCesta);
        ProdutoCestaEntityDto dto = mapper.toDto(updated);
        return ResponseEntity.status(200).body(dto);
    }
}
