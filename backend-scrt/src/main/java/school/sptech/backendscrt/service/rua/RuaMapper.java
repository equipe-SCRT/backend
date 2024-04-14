package school.sptech.backendscrt.service.rua;

import school.sptech.backendscrt.domain.rua.Rua;
import school.sptech.backendscrt.service.rua.dto.RuaCriacaoDto;

public class RuaMapper {
    public static Rua of(RuaCriacaoDto usuarioCriacaoDto){
        Rua rua = new Rua();
        rua.setNomeRua(usuarioCriacaoDto.getNomeRua());
        rua.setEnderecos(usuarioCriacaoDto.getEnderecos());
        rua.setFkRota(usuarioCriacaoDto.getFkRota());

        return rua;
    }
}
