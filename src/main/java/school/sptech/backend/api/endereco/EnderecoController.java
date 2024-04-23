package school.sptech.backend.api.endereco;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import school.sptech.backend.service.endereco.EnderecoViaCep;
import school.sptech.backend.domain.endereco.repository.EnderecoRepository;

@RequiredArgsConstructor
@RestController
@RequestMapping("/enderecos")
public class EnderecoController {
    private final EnderecoRepository enderecoRepository;


    @GetMapping("/{cep}")
    public ResponseEntity<EnderecoViaCep> cep(@PathVariable String cep) throws JsonProcessingException {
        RestTemplate restTemplate = new RestTemplate();
        ObjectMapper objectMapper = new ObjectMapper();
        var result = restTemplate.getForObject(String.format("https://viacep.com.br/ws/%s/json/", cep), String.class);
        EnderecoViaCep endereco = objectMapper.readValue(result, EnderecoViaCep.class);
        return ResponseEntity.status(200).body(endereco);
    }
}
