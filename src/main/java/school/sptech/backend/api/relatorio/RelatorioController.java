package school.sptech.backend.api.relatorio;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import school.sptech.backend.domain.produto.Produto;
import school.sptech.backend.domain.produtounitario.ProdutoUnitario;
import school.sptech.backend.domain.relatorio.Relatorio;
import school.sptech.backend.service.produto.ProdutoService;
import school.sptech.backend.service.produtounitario.ProdutoUnitarioService;
import school.sptech.backend.service.produtounitario.dto.ProdutoUnitarioListagemDto;
import school.sptech.backend.service.produtounitario.dto.ProdutoUnitarioMapper;
import school.sptech.backend.service.produtounitario.dto.ProdutoUnitarioRelatorioDto;
import school.sptech.backend.service.produtounitario.view.VencidoArrecadado;


import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@RestController
@RequestMapping("/relatorio")
@RequiredArgsConstructor
public class RelatorioController {

    private final ProdutoUnitarioService service;
    private final ProdutoUnitarioService produtoService;
    private final ProdutoUnitarioMapper mapper;

    @PostMapping("/importar")
    public ResponseEntity<Void> inserir(@RequestParam MultipartFile file) {
        String funcao = "";
//        Region region = Region.US_EAST_1;
//
//        LambdaClient awsLambda = LambdaClient.builder()
//                .region(region)
//                .build();
//
//        // Objeto para serializar/deserializar JSON
//        ObjectMapper objectMapper = new ObjectMapper();
//
//        InvokeResponse res = null;
//        try {
//            // "json" para enviar ao Lambda
//            Map<String, String> parametros = Map.of("", "");
//
//            // Serializa o objeto para JSON e cria um SdkBytes (que é o payload)
//            SdkBytes payload = SdkBytes.fromUtf8String(objectMapper.writeValueAsString(parametros));
//
//            // Configura a requisição para a Lambda
//            InvokeRequest request = InvokeRequest.builder()
//                    .functionName(funcao)
//                    .payload(payload)
//                    .build();
//
//            // Invoca a Lambda
//            res = awsLambda.invoke(request);
//
//            // Deserializa o JSON de resposta (convertendo para String)
//            String value = res.payload().asUtf8String();
//
//            // Deserializa o JSON de resposta (convertendo para objeto do tipo RespostaCpf)
//            RespostaCpf respostaCpf =
//                    objectMapper.readValue(value, RespostaCpf.class);
//
////			System.out.println(respostaCpf);
//
//            System.out.println();
//            if (respostaCpf.valido()) {
//                return  ResponseEntity.ok().build();
//            } else {
//                return  ResponseEntity.badRequest().build();
//            }
//
//        } catch (LambdaException | JsonProcessingException e) {
//            System.err.println(e.getMessage());
//            return  ResponseEntity.badRequest().build();
//        }
//        finally {
//            awsLambda.close();
//        }

        return ResponseEntity.ok().build();
    }

    @GetMapping("exportar/{dataInicio}/{dataFim}/{tipoArquivo}")
    public ResponseEntity<List<ProdutoUnitarioRelatorioDto>> download(@PathVariable LocalDate dataInicio, @PathVariable LocalDate dataFim, @PathVariable String tipoArquivo) {

        List<VencidoArrecadado> produtosArrecadados = produtoService.arrecadadosVencidos();
        List<ProdutoUnitario> produtoUnitarios = produtoService.listarPorDataEntre(dataInicio, dataFim);
        List<ProdutoUnitarioRelatorioDto> produtoRelatorios = mapper.toDtoRelatorio(produtoUnitarios);

        List<Relatorio> relatorios = new ArrayList<>();

        String periodo = dataInicio + "-" + dataFim;

            for (VencidoArrecadado produto : produtosArrecadados) {
            relatorios.add(new Relatorio(produto.getNome(), produto.getVencido(), produto.getArrecadado()));
        };

        String nomeArquivo;

        switch (tipoArquivo.toLowerCase(Locale.ROOT)) {
            case "csv":
                nomeArquivo = "relatorio-" + periodo ;
                gravarArquivoCsv(produtoRelatorios, nomeArquivo);
                break;
            case "txt":
                nomeArquivo = "relatorio-formato";
                gravaArquivoTxtRelatorio(relatorios, nomeArquivo, periodo );
                nomeArquivo = "produto-formato";
                gravaArquivoTxtProduto(produtoRelatorios, nomeArquivo);
                break;
            default:
                return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().body(produtoRelatorios);
    }

    public static void gravarArquivoCsv(List<ProdutoUnitarioRelatorioDto> lista, String nomeArq) {
        FileWriter arq = null;
        Formatter saida = null;
        Boolean deuRuim = false;

        nomeArq += ".csv";

        try {
            arq = new FileWriter(nomeArq);
            saida = new Formatter(arq);
        }
        catch (IOException erro) {
            System.out.println("Erro ao abrir o arquivo");
            System.exit(1);
        }

        try {
            for (ProdutoUnitarioRelatorioDto produto : lista) {


                String origem = produto.getOrigem().getItapora() == 1? "Itapora" : "Auto de Suuza";

                saida.format("%30s;%10s;%4.2f;%10s,%50s\n",
                        produto.getProduto().getNome(),
                        produto.getDataValidade(),
                        produto.getPeso(),
                        produto.getUnidadeMedida().getNome(),
                        origem);
            }
        }
        catch (FormatterClosedException erro) {
            System.out.println("Erro ao gravar o arquivo");
            deuRuim = true;
        }
        finally {
            saida.close();
            try {
                arq.close();
            }
            catch (IOException erro) {
                System.out.println("Erro ao fechar o arquivo");
                deuRuim = true;
            }
            if (deuRuim) {
                System.exit(1);
            }
        }
    }


    public static void gravaArquivoTxtRelatorio(List<Relatorio> lista, String nomeArq, String periodo) {
        int contaRegDados = 0;


        String header = "00";
        header += "RELATORIO";
        header += LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
        header += periodo;
        header += "01";
        gravaRegistro(nomeArq, header);

        String corpo = "";
        for (Relatorio r : lista) {

            corpo = "02";
            corpo += String.format("%30s",r.getProduto());
            corpo += String.format("%3s", r.getQtdVencido());
            corpo += String.format("%3s", r.getQtdArrecadado());

            gravaRegistro(nomeArq, corpo);
            contaRegDados++;
        }

        String trailer = "01";
        trailer += String.format("%010d", contaRegDados);
        gravaRegistro(nomeArq, trailer);
    }

    public static void gravaArquivoTxtProduto(List<ProdutoUnitarioRelatorioDto> lista, String nomeArq) {
        int contaRegDados = 0;


        String header = "00PRODUTO";
        header += LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
        header += "01";
        gravaRegistro(nomeArq, header);


        String corpo = "";
        for (ProdutoUnitarioRelatorioDto p : lista) {

            corpo = "02";
            corpo += String.format("%s",p.getProduto().getNome());
            corpo += String.format("%s", p.getDataValidade());
            corpo += String.format("%.2f", p.getPeso());
            corpo += String.format("%s", p.getUnidadeMedida().getNome());
            corpo += String.format("%s", p.getOrigem());
            gravaRegistro(nomeArq, corpo);
            contaRegDados++;
        }

        String trailer = "01";
        trailer += String.format("%010d", contaRegDados);
        gravaRegistro(nomeArq, trailer);
    }



    public static void gravaRegistro(String nomeArq, String registro) {
        BufferedWriter saida = null;

        try {
            saida = new BufferedWriter(new FileWriter(nomeArq, true));
        }
        catch (IOException erro) {
            System.out.println("Erro ao abrir o arquivo: " + erro.getMessage());
        }
        try {
            saida.append(registro + "\n");
            saida.close();
        }
        catch (IOException erro) {
            System.out.println("Erro na gravacao do arquivo: " + erro.getMessage());
        }
    }
}
