package school.sptech.backend.api.endereco;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

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

    public static EnderecoViaCep cepCodigo(String cep) throws JsonMappingException, JsonProcessingException {
        cep = cep.replace("-", "");
        if (cep.length() != 8)
            return null;

        RestTemplate restTemplate = new RestTemplate();
        ObjectMapper objectMapper = new ObjectMapper();
        String url = "https://viacep.com.br/ws/" + cep + "/json/";
        var result = restTemplate.getForObject(url, String.class);
        if (result.toString().equalsIgnoreCase("{\n" +
                "  \"erro\": true\n" +
                "}")) {
            return null;
        }
        EnderecoViaCep endereco = objectMapper.readValue(result, EnderecoViaCep.class);
        return endereco;
    }



    public static void menuEndereco() throws JsonMappingException, JsonProcessingException { 

        List<EnderecoViaCep> enderecosCEP = new ArrayList();

        Scanner leitor = new Scanner(System.in);

        System.out.println(
            """
            1- Adicionar Endereço
            2- Listar Endereço
            3- Sair   
            """
        );

        int respostaUsuario = leitor.nextInt();

        switch (respostaUsuario) {
            case 1:
                int numeroVez = 1;
                adicionarEndereco(numeroVez, leitor, enderecosCEP);
                menuEndereco();
                break;
                
            case 2: 
                exibirEndereco();
                break;

                case 3:
                sair();
                break;
        
            default:
                System.out.println("Número inválido");
                menuEndereco();
            break;

        }

        
    }

    public static void adicionarEndereco(int numeroVez, Scanner leitor, List<EnderecoViaCep> enderecosCEP) throws JsonMappingException, JsonProcessingException{
        
        System.out.printf("Informe o CEP da rua %d :", numeroVez );
        int cep = leitor.nextInt();
        String cepArgumento = "" + cep;
        EnderecoViaCep endereco = cepCodigo(cepArgumento);

        if (endereco == null) {
            System.out.println("cep inválido, tente novamente!");
            adicionarEndereco(numeroVez, leitor, enderecosCEP);
        }

        int numeroEnderco = 0;

        while (numeroEnderco == 0) {
            System.out.printf("Informe o número do endereço da rua %d :", numeroVez );
            numeroEnderco = leitor.nextInt();
    
            if (numeroEnderco < 0 || numeroEnderco < 10000) {
                System.out.println("Número inválido");
                numeroEnderco = 0;
            }
            
        }

      // endereco.setNumero(numeroEnderco);
       enderecosCEP.add(endereco);

        System.out.println("Adicionar outro endereço? S/N");
        boolean resposta = leitor.nextLine().equals("S");

        if (resposta) {
            adicionarEndereco(++numeroVez, leitor, enderecosCEP);
        }
    }

    public static void exibirEndereco(){}
    public static void sair(){}

}
