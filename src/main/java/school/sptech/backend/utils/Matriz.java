package school.sptech.backend.utils;


import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import school.sptech.backend.domain.produtounitario.ProdutoUnitario;


public class Matriz {

    public static void main(String[] args) {

        List<ProdutoUnitario> produtosUnitarios = new ArrayList();

        LocalDate data1 = LocalDate.of(2024, 6, 20);
        LocalDate dataCriacao1 = LocalDate.of(2024, 6, 6);

        LocalDate data2 = LocalDate.of(2024, 4, 6);
        LocalDate dataCriacao2 = LocalDate.of(2024, 2, 15);

        LocalDate data3 = LocalDate.of(2024, 7, 6);
        LocalDate dataCriacao3 = LocalDate.of(2024, 3, 2);

        LocalDate data4 = LocalDate.of(2024, 4, 30);
        LocalDate dataCriacao4 = LocalDate.of(2024, 10, 3);

        LocalDate data5 = LocalDate.of(2024, 6, 12);
        LocalDate dataCriacao5 = LocalDate.of(2024, 2, 1);

        LocalDate dataHora = LocalDate.now();

//
//        ProdutoUnitario produtoUnitario1 = new ProdutoUnitario(1, "Arroz", data1, 5.0, false,false, dataCriacao1, null, null,
//                null, null, null, null);
//
//        ProdutoUnitario produtoUnitario2 = new ProdutoUnitario(2, "Feijão", data2, 1.0, true,false, dataCriacao2, null, null,
//                null, null, null, null);
//
//        ProdutoUnitario produtoUnitario3 = new ProdutoUnitario(3, "Macarrão", data3, 5.0, false,false, dataCriacao3, null,
//                null,
//                null, null, null, null);
//
//        ProdutoUnitario produtoUnitario4 = new ProdutoUnitario(4, "Óleo", data4, 1.0, true,false, dataCriacao4, null, null,
//                null, null, null, null);
//
//        ProdutoUnitario produtoUnitario5 = new ProdutoUnitario(5, "Leite", data5, 1.0, true,false, dataCriacao5, null, null,
//                null, null, null, null);

//        produtosUnitarios.add(produtoUnitario1);
//        produtosUnitarios.add(produtoUnitario2);
//        produtosUnitarios.add(produtoUnitario3);
//        produtosUnitarios.add(produtoUnitario4);
//        produtosUnitarios.add(produtoUnitario5);

        Object[][] matriz = new Object[produtosUnitarios.size()][5];

        Boolean[] produtoVencido = new Boolean[produtosUnitarios.size()];

        for (int row = 0; row < matriz.length; row++) {
            matriz[row][0] = produtosUnitarios.get(row).getId();
            matriz[row][1] = produtosUnitarios.get(row).getPeso();
            matriz[row][2] = produtosUnitarios.get(row).getDataCriacao();
            matriz[row][3] = produtosUnitarios.get(row).getDataValidade();
            matriz[row][4] = produtosUnitarios.get(row).isAtivo();
            produtoVencido[row] = produtosUnitarios.get(row).getDataValidade().isBefore(dataHora);
        }

        double pesoSoma = 0.0;

        for (int row = 0; row < matriz.length; row++) {

            pesoSoma += (double) matriz[row][1];

        }

        int qtdProdutosVencidos = 0;
        int qtdProdutosDoados = 0;

        for (int row = 0; row < matriz.length; row++) {

            if (produtoVencido[row] && produtosUnitarios.get(row).isAtivo()) {
                qtdProdutosVencidos++;
            }

            if (!produtosUnitarios.get(row).isAtivo()) {
                qtdProdutosDoados++;
            }

        }



        System.out.println("\n");
        System.out.println("-".repeat(161));
        System.out.printf("|%-20s | %-20s | %-20s | %-20s | %-20s | %-20s | %-20s |%n", "Produto unitário", "Id",
                "Peso", "Chegou no estoque", "Data Validade", "Em estoque", "Produto vencido");

        for (int i = 0; i < matriz[0].length; i++) {
            System.out.println("-".repeat(161));
            System.out.printf("|%-20s | %20d | %20.2f | %-20s | %-20s | %-20b | %-20b |%n",
                    produtosUnitarios.get(i).getNome(), produtosUnitarios.get(i).getId(),
                    produtosUnitarios.get(i).getPeso(),
                    produtosUnitarios.get(i).getDataCriacao().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                    produtosUnitarios.get(i).getDataValidade().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                    produtosUnitarios.get(i).isAtivo(),
                    produtoVencido[i]);
        }

        System.out.println("-".repeat(161));
        System.out.println("\n");
        System.out.println("-".repeat(36));
        System.out.printf("|%-20s | %10d |%n", "Qtd. total:", produtosUnitarios.size());
        System.out.println("-".repeat(36));
        System.out.printf("|%-20s | %10d |%n", "Qtd. doações: ", qtdProdutosDoados);
        System.out.println("-".repeat(36));
        System.out.printf("|%-20s | %10.2f |%n", "Qtd. doações(kg): ", pesoSoma);

        System.out.println("-".repeat(36));
        System.out.printf("|%-20s | %10d |%n", "Qtd. perca: ", qtdProdutosVencidos);
        System.out.println("-".repeat(36));
        System.out.printf("|%-20s | %10d |%n", "Em estoque: ",
                produtosUnitarios.size() - (qtdProdutosDoados + qtdProdutosVencidos));
        System.out.println("-".repeat(36));

        System.out.println("\n");
        gerarCsv(matriz, produtosUnitarios, produtoVencido, pesoSoma, qtdProdutosDoados, qtdProdutosVencidos);
    }

    public static void gerarCsv(Object[][] matriz, List<ProdutoUnitario> produtosUnitarios, Boolean[] produtoVencido, double pesoSoma, int qtdProdutosDoados, int qtdProdutosVencidos) {
        String csvFile = "dados.csv";
        try (FileWriter out = new FileWriter(csvFile);
                CSVPrinter printer = new CSVPrinter(out, CSVFormat.DEFAULT.withHeader("Produto unitário", "Id","Peso", "Chegou no estoque", "Data Validade", "Em estoque", "Produto vencido"))) {

                    for (int i = 0; i < matriz[0].length; i++) {
                        
                        printer.printRecord(
                                produtosUnitarios.get(i).getNome(),
                                produtosUnitarios.get(i).getId(),
                                produtosUnitarios.get(i).getPeso(),
                                produtosUnitarios.get(i).getDataCriacao().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                                produtosUnitarios.get(i).getDataValidade().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                                produtosUnitarios.get(i).isAtivo(),
                                produtoVencido[i]);
                    }

            System.out.println("\n");
            System.out.println("Arquivo CSV gerado com sucesso!");
        } catch (IOException e) {
            e.printStackTrace();
        }

        String csvFile2 = "relatorio.csv";
        try (FileWriter out = new FileWriter(csvFile2);
                CSVPrinter printer = new CSVPrinter(out, CSVFormat.DEFAULT.withHeader(
                    "Qtd. total","Qtd. doações", "Qtd. doações(Kg)", "Qtd. perca", "Em estoque"
                ))) {

                    for (int i = 0; i < matriz[0].length; i++) {
                        
                        printer.printRecord(
                            produtosUnitarios.size(),
                            qtdProdutosDoados,
                            pesoSoma,
                            qtdProdutosVencidos,
                            qtdProdutosVencidos
                        );
                    }

            System.out.println("\n");
            System.out.println("Arquivo CSV gerado com sucesso!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

