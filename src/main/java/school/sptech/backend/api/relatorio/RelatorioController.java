package school.sptech.backend.api.relatorio;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import school.sptech.backend.domain.produtounitario.ProdutoUnitario;
import school.sptech.backend.domain.relatorio.Relatorio;
import school.sptech.backend.service.produtounitario.ProdutoUnitarioService;
import school.sptech.backend.service.produtounitario.dto.ProdutoUnitarioLeituraDto;
import school.sptech.backend.service.produtounitario.dto.ProdutoUnitarioMapper;
import school.sptech.backend.service.produtounitario.dto.ProdutoUnitarioRelatorioDto;
import school.sptech.backend.service.produtounitario.view.VencidoArrecadado;

import java.nio.file.Paths;
import java.util.Map;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.SdkBytes;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.lambda.LambdaClient;
import software.amazon.awssdk.services.lambda.model.InvokeRequest;
import software.amazon.awssdk.services.lambda.model.InvokeResponse;
import software.amazon.awssdk.services.lambda.model.LambdaException;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.BufferedReader;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@RestController
@RequestMapping("/relatorio")
@RequiredArgsConstructor
public class RelatorioController {


    private final ProdutoUnitarioService produtoService;
    private final ProdutoUnitarioMapper mapper;

    @PostMapping("/importar/{fileName}")
    public ResponseEntity<List<ProdutoUnitarioLeituraDto>> importar(
            @RequestBody byte[] referenciaArquivo, @PathVariable String fileName
    ) {

//       String funcao = "lambda_handler";
//       Region region = Region.US_EAST_1;

        List<ProdutoUnitarioLeituraDto> listaProdutos;


        try {
            if (fileName.endsWith(".csv")) {
                listaProdutos = lerArquivoCsv(referenciaArquivo, fileName);
            } else {
                listaProdutos = lerArquivoTxt(referenciaArquivo);
            }
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().body(listaProdutos);
    }

//        LambdaClient awsLambda = LambdaClient.builder()
//                .region(region)
//                .build();
//
//
//        ObjectMapper objectMapper = new ObjectMapper();
//
//        try {
//
//            Map<String, Object> parametros = Map.of(
//                    "nomeArquivo", fileName,
//                    "dadosArquivo", listaProdutos
//            );
//
//           SdkBytes payload = SdkBytes.fromUtf8String(objectMapper.writeValueAsString(parametros));
//
//
//           InvokeRequest request = InvokeRequest.builder()
//                    .functionName(funcao)
//                    .payload(payload)
//                    .build();
//
//
//           InvokeResponse res = awsLambda.invoke(request);
//
//            String responseJson = res.payload().asUtf8String();
//
//            if (responseJson != null) {
//                return ResponseEntity.ok().build();
//            } else {
//                return  ResponseEntity.internalServerError().build();
//            }
//
//        } catch (LambdaException | JsonProcessingException e) {
//            System.err.println(e.getMessage());
//            return  ResponseEntity.noContent().build();
//        } finally {
//            awsLambda.close();
//        }
    // }




    public static List<ProdutoUnitarioLeituraDto> lerArquivoCsv(byte[] referenciaArquivo, String fileName) throws Exception {

        List<ProdutoUnitarioLeituraDto> listaProdutos = new ArrayList<>();

        FileWriter arq = null;  // objeto que representa o arquivo
        Formatter saida = null; // objeto que sera usado para escrever no arquivo
        Boolean deuRuim = false;

        try {
            arq = new FileWriter(fileName);
            saida = new Formatter(arq);
        }
        catch (IOException erro) {
            System.out.println("Erro ao abrir o arquivo");
            System.exit(1);
        }

        try {


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


        ByteArrayInputStream inputStream = new ByteArrayInputStream(referenciaArquivo);
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

        String linha;

        try {
            while ((linha = reader.readLine()) != null) {
                String[] valores = linha.split(";");

                ProdutoUnitarioLeituraDto produto = new ProdutoUnitarioLeituraDto();

                produto.setProduto(valores[0]);
                produto.setData(valores[1]);
                produto.setPeso(Double.parseDouble(valores[2]));
                produto.setUnidade(valores[3]);
                produto.setOrigem(valores[4]);\,00,,0
                        0,0

                listaProdutos.add(produto);
            }

            for (ProdutoUnitarioLeituraDto produto : listaProdutos) {
                System.out.println(produto);
            }
        }
        catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return listaProdutos;
    }

    public static List<ProdutoUnitarioLeituraDto> lerArquivoTxt(byte[] referenciaArquivo) throws Exception {
        List<ProdutoUnitarioLeituraDto> listaLida = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(referenciaArquivo)))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String tipoRegistro = linha.substring(0, 2);
                if (tipoRegistro.equals("02")) {
                    ProdutoUnitarioLeituraDto produto = new ProdutoUnitarioLeituraDto(
                            linha.substring(2, 3).trim(),
                            linha.substring(3, 34).trim(),
                            Double.parseDouble(linha.substring(45, 50).replace(',', '.')),
                            linha.substring(50, 61).trim(),
                            linha.substring(61, 110).trim()
                    );
                    listaLida.add(produto);
                }
            }
        }
        return listaLida;
    }


    @GetMapping("/exportar/{dataInicio}/{dataFim}/{tipoArquivo}")
    public ResponseEntity<Resource> download(@PathVariable LocalDate dataInicio, @PathVariable LocalDate dataFim, @PathVariable String tipoArquivo) throws IOException {

        List<VencidoArrecadado> produtosArrecadados = produtoService.arrecadadosVencidos();
        List<ProdutoUnitario> produtoUnitarios = produtoService.listarPorDataEntre(dataInicio, dataFim);
        List<ProdutoUnitarioRelatorioDto> produtoRelatorios = mapper.toDtoRelatorio(produtoUnitarios);

        List<Relatorio> relatorios = new ArrayList<>();

        String periodo = dataInicio + "-" + dataFim;

        for (VencidoArrecadado produto : produtosArrecadados) {
            relatorios.add(new Relatorio(produto.getNome(), produto.getVencido(), produto.getArrecadado()));
        }
        ;


        String nomeArquivo;

        switch (tipoArquivo.toLowerCase(Locale.ROOT)) {
            case "csv":
                nomeArquivo = "relatorio-" + periodo;

                return ResponseEntity.ok()
                        .contentType(MediaType.parseMediaType("text/csv"))
                        .header(HttpHeaders.CONTENT_DISPOSITION, "attachement; filename=\"" + nomeArquivo + ".csv\"")
                        .body(gravarArquivoCsv(produtoRelatorios, nomeArquivo));

            case "txt":
                nomeArquivo = "relatorio-formato";
                Resource conteudoRelatorio = gravaArquivoTxtRelatorio(relatorios, nomeArquivo, periodo);

                ByteArrayOutputStream byteArrayOutputStream = null;
                byteArrayOutputStream = new ByteArrayOutputStream();

                try (ZipOutputStream zipOutputStream = new ZipOutputStream(byteArrayOutputStream)) {
                    // Adicionar o primeiro arquivo ao ZIP
                    ZipEntry zipEntry1 = new ZipEntry(nomeArquivo + ".txt");
                    zipOutputStream.putNextEntry(zipEntry1);
                    copiarConteudoResourceParaZip(conteudoRelatorio, zipOutputStream);
                    zipOutputStream.closeEntry();

                    nomeArquivo = "produto-formato";
                    Resource conteudoProduto = gravaArquivoTxtProduto(produtoRelatorios, nomeArquivo);

                    ZipEntry zipEntry2 = new ZipEntry(nomeArquivo + ".txt");
                    zipOutputStream.putNextEntry(zipEntry2);
                    copiarConteudoResourceParaZip(conteudoProduto, zipOutputStream);
                    zipOutputStream.closeEntry();
                }

                ByteArrayResource resource = new ByteArrayResource(byteArrayOutputStream.toByteArray());
                return ResponseEntity.ok()
                        .contentType(MediaType.parseMediaType("application/zip"))
                        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"arquivos-relatorio.zip\"")
                        .body(resource);


            default:
                return ResponseEntity.notFound().build();
        }
    }


    public static Resource gravarArquivoCsv(List<ProdutoUnitarioRelatorioDto> lista, String nomeArq) throws IOException {

        Path tempFile = Files.createTempFile(nomeArq, ".csv");

        try (FileWriter arq = new FileWriter(String.valueOf(tempFile));
             Formatter saida = new Formatter(arq)) {
            for (ProdutoUnitarioRelatorioDto produto : lista) {
                String origem = produto.getOrigem().getItapora() == 1 ? "Itapora" : "Auto de Suuza";

                saida.format("%30s;%10s;%4.2f;%10s;%50s\n",
                        produto.getProduto().getNome(),
                        produto.getDataValidade(),
                        produto.getPeso(),
                        produto.getUnidadeMedida().getNome(),
                        origem);
            }
        } catch (IOException e) {

            System.err.println("Erro ao gravar o arquivo: " + e.getMessage());
            throw e;
        }

        return new UrlResource(tempFile.toUri());
    }

    public static Resource gravaArquivoTxtRelatorio(List<Relatorio> lista, String nomeArq, String periodo) throws IOException {

        Path tempFile = Files.createTempFile(nomeArq, ".txt");

        int contaRegDados = 0;


        String header = "00";
        header += "RELATORIO";
        header += LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
        header += periodo;
        gravaRegistro(tempFile, header);

        String corpo = "";
        for (Relatorio r : lista) {

            corpo = "02";
            corpo += String.format("%30s",r.getProduto());
            corpo += String.format("%3s", r.getQtdVencido());
            corpo += String.format("%3s", r.getQtdArrecadado());

            gravaRegistro(tempFile, corpo);
            contaRegDados++;
        }

        String trailer = "01";
        trailer += String.format("%010d", contaRegDados);
        gravaRegistro(tempFile, trailer);

        return new UrlResource(tempFile.toUri());
    }


    public static Resource gravaArquivoTxtProduto(List<ProdutoUnitarioRelatorioDto> lista, String nomeArq) throws IOException {
        int contaRegDados = 0;

        Path tempFile = Files.createTempFile(nomeArq, ".txt");


        String header = "00PRODUTO";
        header += LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
        gravaRegistro(tempFile, header);


        String corpo = "";
        for (ProdutoUnitarioRelatorioDto p : lista) {

            String origem = p.getOrigem().getItapora() == 1? "Itapora" : "Auto de Souza";

            corpo = "02";
            corpo += String.format("%s",p.getProduto().getNome());
            corpo += String.format("%s", p.getDataValidade());
            corpo += String.format("%.2f", p.getPeso());
            corpo += String.format("%s", p.getUnidadeMedida().getNome());
            corpo += String.format("%s", origem);
            gravaRegistro(tempFile, corpo);
            contaRegDados++;
        }

        String trailer = "01";
        trailer += String.format("%010d", contaRegDados);
        gravaRegistro(tempFile, trailer);

        return new UrlResource(tempFile.toUri());
    }


    public static void gravaRegistro(Path file, String registro) throws IOException {
        Files.write(file, (registro + System.lineSeparator()).getBytes(), StandardOpenOption.APPEND);
    }

    private void copiarConteudoResourceParaZip(Resource resource, ZipOutputStream zipOutputStream) throws IOException {
        try (InputStream inputStream = resource.getInputStream()) {
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                zipOutputStream.write(buffer, 0, bytesRead);
            }
        }
    }
}