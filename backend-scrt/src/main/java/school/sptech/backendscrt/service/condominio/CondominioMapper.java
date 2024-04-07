package school.sptech.backendscrt.service.condominio;


import school.sptech.backendscrt.domain.condominio.Condominio;
import school.sptech.backendscrt.service.condominio.dto.CondominioCriacaoDto;

public class CondominioMapper {
    public static Condominio of(CondominioCriacaoDto condominioCriacaoDto){
        Condominio condominio = new Condominio();
        condominio.setFkEndereco(condominioCriacaoDto.getFkEndereco());
        condominio.setNomeCondominio(condominioCriacaoDto.getNomeCondominio());

        return condominio;
    }
}
