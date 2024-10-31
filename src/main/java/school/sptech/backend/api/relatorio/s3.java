package school.sptech.backend.api.relatorio;

import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.nio.file.Path;
import java.nio.file.Paths;

public class s3 {
    private S3Client criarClienteS3() {
        Region region = Region.US_EAST_1;
        S3Client s3 = S3Client.builder().region(region).build();
        return s3;
    }

    private void enviarArquivoS3(String nomeArquivo, byte[] conteudoArquivo) {
        PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                .bucket("bucket-scrt")
                .key(nomeArquivo)
                .build();

        Path path = Paths.get("download.jpg");

        software.amazon.awssdk.core.sync.RequestBody requestBody =
                software.amazon.awssdk.core.sync.RequestBody.fromBytes(conteudoArquivo);

        S3Client s3 = criarClienteS3();
        s3.putObject(putObjectRequest, requestBody);
    }

    private byte[] baixarArquivoS3(String nomeArquivo) {
        GetObjectRequest getObjectRequest = GetObjectRequest.builder()
                .bucket("bucket-scrt")
                .key(nomeArquivo)
                .build();

        S3Client s3 = criarClienteS3();

        byte[] arquivo = s3.getObjectAsBytes(getObjectRequest).asByteArray();
        return arquivo;
    }
}
