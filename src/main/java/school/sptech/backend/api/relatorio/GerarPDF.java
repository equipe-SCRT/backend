package school.sptech.backend.api.relatorio;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.kernel.pdf.PdfWriter;

public class GerarPDF {
    public static void main(String[] args) {
        try {
            // Define o caminho do arquivo PDF a ser gerado
            String destino = "documento_exemplo.pdf";

            // Cria o PdfWriter
            PdfWriter writer = new PdfWriter(destino);

            // Cria o PdfDocument com o escritor
            PdfDocument pdf = new PdfDocument(writer);

            // Cria um objeto Document para adicionar conteúdo
            Document document = new Document(pdf);

            // Adiciona um parágrafo ao documento
            document.add(new Paragraph("Olá, este é um exemplo de PDF gerado com iText!"));

            // Fecha o documento
            document.close();

            System.out.println("PDF gerado com sucesso!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
