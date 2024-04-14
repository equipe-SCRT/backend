package school.sptech.backendscrt.service.historicorota;

import school.sptech.backendscrt.domain.historicoRota.HistoricoRota;
import school.sptech.backendscrt.domain.rota.Rota;
import school.sptech.backendscrt.service.historicorota.dto.HistoricoRotaCriacaoDto;
import school.sptech.backendscrt.service.rota.dto.RotaCriacaoDto;

public class HistoricoRotaMapper {
    public static HistoricoRota of(HistoricoRotaCriacaoDto historicoRotaCriacaoDto){
        HistoricoRota historicoRota = new HistoricoRota();
        historicoRota.setKmRodados(historicoRotaCriacaoDto.getKmRodados());
        historicoRota.setDataRota(historicoRotaCriacaoDto.getDataRota());
        historicoRota.setFkRota(historicoRotaCriacaoDto.getFkRota());
        historicoRota.setFkLote(historicoRotaCriacaoDto.getFkLote());
        historicoRota.setHoraInicio(historicoRotaCriacaoDto.getHoraInicio());
        historicoRota.setHoraFim(historicoRotaCriacaoDto.getHoraFim());
        historicoRota.setQtdColaboradores(historicoRotaCriacaoDto.getQtdColaboradores());
        historicoRota.setQtdCestas(historicoRotaCriacaoDto.getQtdCestas());

        return historicoRota;
    }
}
