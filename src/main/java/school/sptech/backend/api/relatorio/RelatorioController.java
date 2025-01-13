package school.sptech.backend.api.relatorio;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.backend.domain.produtounitario.ProdutoUnitario;
import school.sptech.backend.domain.relatorio.Relatorio;
import school.sptech.backend.service.produtounitario.ProdutoUnitarioService;
import school.sptech.backend.service.produtounitario.dto.ProdutoUnitarioMapper;
import school.sptech.backend.service.produtounitario.dto.ProdutoUnitarioRelatorioDto;
import school.sptech.backend.service.produtounitario.view.VencidoArrecadado;
import java.util.List;
import org.springframework.web.bind.annotation.RestController;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.kernel.pdf.PdfWriter;


@RestController
@RequestMapping("/relatorio")
@RequiredArgsConstructor
public class RelatorioController {


    private final ProdutoUnitarioService produtoUnitarioService;
    private final ProdutoUnitarioMapper mapper;

    @GetMapping("/exportar/relatorio")
    public ResponseEntity<InputStreamResource> gerarPdf() throws FileNotFoundException {

        List<ProdutoUnitario> produtoUnitarios = produtoUnitarioService.listar();
        List<ProdutoUnitarioRelatorioDto> produtoRelatorios = mapper.toDtoRelatorio(produtoUnitarios);

        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            PdfWriter writer = new PdfWriter(baos);
            PdfDocument pdf = new PdfDocument(writer);
            Document document = new Document(pdf);

            for (ProdutoUnitarioRelatorioDto produto : produtoRelatorios) {
                document.add(new Paragraph(produto.toString()));
            }

            document.close();
            ByteArrayInputStream inputStream = new ByteArrayInputStream(baos.toByteArray());

            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_PDF)
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=documento_exemplo.pdf")
                    .body(new InputStreamResource(inputStream));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).build();
        }

    }


    @GetMapping("/exportar/{tipoArquivo}")
    public ResponseEntity<Resource> download(@PathVariable String tipoArquivo) throws IOException {

        List<VencidoArrecadado> produtosArrecadados = produtoUnitarioService.arrecadadosVencidos();
        List<ProdutoUnitario> produtoUnitarios = produtoUnitarioService.listar();
        List<ProdutoUnitarioRelatorioDto> produtoRelatorios = mapper.toDtoRelatorio(produtoUnitarios);

        List<Relatorio> relatorios = new ArrayList<>();

        for (VencidoArrecadado produto : produtosArrecadados) {
            relatorios.add(new Relatorio(produto.getNome(), produto.getVencido(), produto.getArrecadado()));
        };

        String nomeArquivo = "relatorio";

        switch (tipoArquivo.toLowerCase(Locale.ROOT)) {
            case "csv":

                return ResponseEntity.ok()
                        .contentType(MediaType.parseMediaType("text/csv"))
                        .header(HttpHeaders.CONTENT_DISPOSITION, "attachement; filename=\"" + nomeArquivo + ".csv\"")
                        .body(gravarArquivoCsv(produtoRelatorios, nomeArquivo));

            case "txt":
                nomeArquivo = "relatorio-formato";
                Resource conteudoRelatorio = gravaArquivoTxtRelatorio(relatorios, nomeArquivo);

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

    public static Resource gravaArquivoTxtRelatorio(List<Relatorio> lista, String nomeArq) throws IOException {

        Path tempFile = Files.createTempFile(nomeArq, ".txt");

        int contaRegDados = 0;


        String header = "00";
        header += "RELATORIO";
        header += LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
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