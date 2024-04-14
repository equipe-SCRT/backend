package school.sptech.backendscrt.service.rota;

import school.sptech.backendscrt.domain.rota.Rota;
import school.sptech.backendscrt.service.rota.dto.RotaCriacaoDto;

public class RotaMapper {
    public static Rota of(RotaCriacaoDto rotaCriacaoDto){
        Rota rota = new Rota();
        rota.setNomeRota(rotaCriacaoDto.getNomeRota());
        rota.setRuas(rotaCriacaoDto.getRuas());

        return rota;
    }
}
