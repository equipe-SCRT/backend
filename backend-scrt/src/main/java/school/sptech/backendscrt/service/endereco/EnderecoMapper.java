package school.sptech.backendscrt.service.endereco;

import school.sptech.backendscrt.domain.endereco.Endereco;
import school.sptech.backendscrt.domain.rota.Rota;
import school.sptech.backendscrt.service.endereco.dto.EnderecoCriacaoDto;
import school.sptech.backendscrt.service.rota.dto.RotaCriacaoDto;

public class EnderecoMapper {
    public static Endereco of(EnderecoCriacaoDto rotaCriacaoDto){
        Endereco endereco = new Endereco();
        endereco.setLogradouro(rotaCriacaoDto.getLogradouro());
        endereco.setCep(rotaCriacaoDto.getCep());
        endereco.setNumero(rotaCriacaoDto.getNumero());
        endereco.setBairro(rotaCriacaoDto.getBairro());
        endereco.setFkRua(rotaCriacaoDto.getFkRua());
        return endereco;
    }
}
