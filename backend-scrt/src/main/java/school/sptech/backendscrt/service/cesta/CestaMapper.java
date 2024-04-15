package school.sptech.backendscrt.service.cesta;

import school.sptech.backendscrt.domain.cesta.Cesta;
import school.sptech.backendscrt.domain.condominio.Condominio;
import school.sptech.backendscrt.service.cesta.dto.CestaCriacaoDto;
import school.sptech.backendscrt.service.condominio.dto.CondominioCriacaoDto;

public class CestaMapper {
    public static Cesta of(CestaCriacaoDto cestaCriacaoDto){
        Cesta cesta = new Cesta();
        cesta.setFkTipoCesta(cestaCriacaoDto.getFkTipoCesta());
        cesta.setDataMontagem(cestaCriacaoDto.getDataMontagem());
        cesta.setFkLote(cestaCriacaoDto.getFkLote());
        cesta.setFkHistoricoRota(cestaCriacaoDto.getFkHistoricoRota());

        return cesta;
    }
}
