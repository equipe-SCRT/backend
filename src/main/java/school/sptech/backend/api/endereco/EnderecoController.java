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
        return ResponseEntity.status(200).body(endereco);
    }
}
