package school.sptech.backend.api.endereco;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;

import java.util.*;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import school.sptech.backend.service.endereco.EnderecoViaCep;
import school.sptech.backend.utils.ListaObj;


@RequiredArgsConstructor
@RestController
@RequestMapping("/enderecos")
public class EnderecoController {
    private final ListaObj<EnderecoViaCep> enderecos = new ListaObj<>(10);

    @PostMapping("/{cep}/{numero}")
    public ResponseEntity<EnderecoViaCep> adicionar(@PathVariable String cep, @PathVariable int numero) throws JsonProcessingException {
        cep = cep.replace("-", "");
        if (cep.length() != 8)
            return ResponseEntity.badRequest().build();

        RestTemplate restTemplate = new RestTemplate();
        ObjectMapper objectMapper = new ObjectMapper();
        String url = "https://viacep.com.br/ws/" + cep + "/json/";
        var result = restTemplate.getForObject(url, String.class);
        if (result.toString().equalsIgnoreCase("{\n" +
                "  \"erro\": true\n" +
                "}")) {
            return ResponseEntity.notFound().build();
        }
        EnderecoViaCep endereco = objectMapper.readValue(result, EnderecoViaCep.class);
        endereco.setNumero(numero);
        enderecos.adiciona(endereco);
        return ResponseEntity.status(201).body(endereco);
    }

    @GetMapping
    public ResponseEntity<EnderecoViaCep[]> listar() {
        if(enderecos.getTamanho() == 0) return ResponseEntity.noContent().build();
        EnderecoViaCep[] vetorCep = new EnderecoViaCep[enderecos.getTamanho()];
        for (int i = 0; i < vetorCep.length; i++) {
            vetorCep[i] = enderecos.getElemento(i);
        }
        ListaObj.quickSort(vetorCep, 0, vetorCep.length - 1) ;
        enderecos.setVetor(vetorCep);
        return ResponseEntity.status(200).body(vetorCep);
    }

    @GetMapping("/{logradouro}")
    public ResponseEntity<EnderecoViaCep> buscarPorLogradouro(@PathVariable String logradouro){
        if(enderecos.getTamanho() == 0) return ResponseEntity.noContent().build();
        EnderecoViaCep[] vetorCep = new EnderecoViaCep[enderecos.getTamanho()];
        for (int i = 0; i < vetorCep.length; i++) {
            vetorCep[i] = enderecos.getElemento(i);
        }
        ListaObj.quickSort(vetorCep, 0, vetorCep.length - 1);
        enderecos.setVetor(vetorCep);
        EnderecoViaCep enderecoBuscado = enderecos.pesquisaBinariaLougradoro(vetorCep, logradouro);
        if(enderecoBuscado == null) return ResponseEntity.noContent().build();

        return ResponseEntity.ok(enderecoBuscado);
    }


}
