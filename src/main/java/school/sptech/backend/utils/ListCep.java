package school.sptech.backend.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import school.sptech.backend.service.endereco.EnderecoViaCep;

public class ListCep {

    public static void main(String[] args) throws JsonMappingException, JsonProcessingException {

        List<EnderecoViaCep> enderecosCEP = new ArrayList();
        menuEndereco(enderecosCEP);
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
                " \"erro\": true\n" +
                "}")) {
            return null;
        }

        return objectMapper.readValue(result, EnderecoViaCep.class);
    }

    public static void menuEndereco(List<EnderecoViaCep> enderecosCEP)
            throws JsonMappingException, JsonProcessingException {

        Scanner leitor = new Scanner(System.in);

        System.out.println(
                """
                        \n
                            1- Adicionar Endereço
                            2- Listar Endereço
                            3- Sair
                            """);

        int respostaUsuario = leitor.nextInt();

        switch (respostaUsuario) {
            case 1:
                int numeroVez = 1;
                adicionarEndereco(numeroVez, leitor, enderecosCEP);
                break;

            case 2:
                exibirEndereco(enderecosCEP);
                menuEndereco(enderecosCEP);
                break;

            case 3:
                sair();
                break;

            default:
                System.out.println("Número inválido");
                menuEndereco(enderecosCEP);
                break;

        }

    }

    public static void adicionarEndereco(int numeroVez, Scanner leitor, List<EnderecoViaCep> enderecosCEP)
            throws JsonMappingException, JsonProcessingException {

        System.out.printf("Informe o CEP da rua %d :\n", numeroVez);
        String cep = "";
        while (cep.equals("")) {
            cep = leitor.nextLine();
        }
        EnderecoViaCep endereco = cepCodigo(cep);

        if (endereco == null) {
            System.out.println("cep inválido, tente novamente!");
            adicionarEndereco(numeroVez, leitor, enderecosCEP);
        }

        int numeroEnderco = 0;

        while (numeroEnderco == 0) {
            System.out.printf("Informe o número do endereço da rua %d :\n", numeroVez);
            numeroEnderco = leitor.nextInt();

            if (numeroEnderco < 0 || numeroEnderco > 10000) {
                System.out.println("Número inválido");
                numeroEnderco = 0;
            }

        }

        endereco.setNumero(numeroEnderco);

        enderecosCEP.add(endereco);

        System.out.println("Adicionar outro endereço? S/N");

        int adiciona = 0;
        while (adiciona == 0) {
            String resposta = leitor.nextLine();
            if (resposta.equalsIgnoreCase("S")) {
                adicionarEndereco(++numeroVez, leitor, enderecosCEP);
            } else if (resposta.equalsIgnoreCase("N")) {
                adiciona = 1;
            }
        }

        menuEndereco(enderecosCEP);

    }

    public static void exibirEndereco(List<EnderecoViaCep> enderecosCEP)
            throws JsonMappingException, JsonProcessingException {

        Scanner leitor = new Scanner(System.in);

        for (EnderecoViaCep enderecosCEP2 : enderecosCEP) {
            System.out.println(enderecosCEP2.getLogradouro());
        }

        if (enderecosCEP.isEmpty()) {
            System.out.println("Nenhum endereço cadastrado!");
        } else {

            ListaObj<EnderecoViaCep> enderecosObj = new ListaObj<>(enderecosCEP.size());
            EnderecoViaCep[] enderecoVetor = new EnderecoViaCep[enderecosCEP.size()];

            System.out.println("\nEndereços cadastrados:");

            for (int i = 0; i < enderecosCEP.size(); i++) {
                System.out.printf("\n%d - %s", (i + 1), enderecosCEP.get(i).getLogradouro());
                enderecosObj.adiciona(enderecosCEP.get(i));
                enderecoVetor[i] = enderecosCEP.get(i);
            }

            int respostaUsuario = 0;
            
            while (respostaUsuario == 0) {

                System.out.println(
                        """
                                \nAperte:
                                1- Pesquisar por nome da rua
                                2- Voltar
                                """);

                respostaUsuario = leitor.nextInt();

                switch (respostaUsuario) {
                    case 1:
                        ListaObj.quickSort(enderecoVetor, 0, enderecosCEP.size() - 1);

                        System.out.println("\nNome da rua: ");
                        String teste = "";
                        while (teste.equals("")) {
                            teste = leitor.nextLine();
                        }

                        EnderecoViaCep enderecoProcurado = enderecosObj.pesquisaBinariaLougradoro(enderecoVetor,
                                teste);

                        if (enderecoProcurado == null) {
                            System.out.println("\nRua não cadastrada!");
                        } else {
                            System.out.println("Rua cadastrada: ");
                            System.out.println(enderecoProcurado.toString());
                        }

                        respostaUsuario = 0;
                        break;
                    case 2:
                        break;

                    default:
                        System.out.println("número inválido");
                        respostaUsuario = 0;
                        break;
                }
            }

        }

    }

    public static void sair() {
        System.out.println("Saindo...");
    }
}
