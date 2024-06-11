package school.sptech.backend.utils;

import school.sptech.backend.domain.produtounitario.ProdutoUnitario;
import school.sptech.backend.domain.unidademedida.UnidadeMedida;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TesteFilaObj {
    public static void main(String[] args) {

        UnidadeMedida kilo = new UnidadeMedida();
        kilo.setRepresentacao("kg");
        UnidadeMedida mililitro = new UnidadeMedida();
        mililitro.setRepresentacao("ml");
        UnidadeMedida grama = new UnidadeMedida();
        grama.setRepresentacao("g");

        FilaCircularObj<ProdutoUnitario> produtosEstragados = new FilaCircularObj<>(5);
        List<ProdutoUnitario> produtosUnitarios = new ArrayList<>();
        FilaCircularObj<ProdutoUnitario> filaAux = new FilaCircularObj<>(5);

        DateTimeFormatter dataFormatada = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        ProdutoUnitario produtoUnitario1 = new ProdutoUnitario();
        produtoUnitario1.setNome("Arroz");
        produtoUnitario1.setDataValidade(LocalDate.of(2024, 9, 25));
        produtoUnitario1.setPeso(5.0);
        produtoUnitario1.setUnidadeMedida(kilo);

        ProdutoUnitario produtoUnitario2 = new ProdutoUnitario();
        produtoUnitario2.setNome("Feijão");
        produtoUnitario2.setDataValidade(LocalDate.of(2024, 6, 20));
        produtoUnitario2.setPeso(1.0);
        produtoUnitario2.setUnidadeMedida(kilo);

        ProdutoUnitario produtoUnitario3 = new ProdutoUnitario();
        produtoUnitario3.setNome("Óleo");
        produtoUnitario3.setDataValidade(LocalDate.of(2024, 10, 25));
        produtoUnitario3.setPeso(900.0);
        produtoUnitario3.setUnidadeMedida(mililitro);

        ProdutoUnitario produtoUnitario4 = new ProdutoUnitario();
        produtoUnitario4.setNome("Molho de tomate");
        produtoUnitario4.setDataValidade(LocalDate.of(2024, 7, 30));
        produtoUnitario4.setPeso(300.0);
        produtoUnitario4.setUnidadeMedida(grama);

        produtosUnitarios.add(produtoUnitario1);
        produtosUnitarios.add(produtoUnitario2);
        produtosUnitarios.add(produtoUnitario3);
        produtosUnitarios.add(produtoUnitario4);

        Scanner leitorTexto = new Scanner(System.in);
        Scanner leitorNumerico = new Scanner(System.in);
        int opcaoEscolhida;

        do {
            System.out.println("""
                    \nEscolha a opção desejada:
                    
                    1 - Cadastrar produto
                    2 - Ver produtos cadastrados
                    3 - Sair
                    """);

            opcaoEscolhida = leitorNumerico.nextInt();

            switch (opcaoEscolhida) {
                case 1 -> {
                    System.out.println("Insira as informações do novo produto:");
                    System.out.println("Nome:");
                    String nome = leitorTexto.nextLine();
                    System.out.println("Peso:");
                    Double peso = leitorNumerico.nextDouble();
                    System.out.println("Data de validade:");
                    LocalDate dataValidade;
                    String input = leitorTexto.nextLine();
                    dataValidade = LocalDate.parse(input, dataFormatada);

                    ProdutoUnitario novoProduto = new ProdutoUnitario();
                    novoProduto.setNome(nome);
                    novoProduto.setPeso(peso);
                    if (novoProduto.getPeso() == 5.0 || novoProduto.getPeso() == 1.0) {
                        novoProduto.setUnidadeMedida(kilo);
                    } if (novoProduto.getPeso() == 500.0 || novoProduto.getPeso() == 900.0) {
                        novoProduto.setUnidadeMedida(grama);
                    } if (novoProduto.getPeso() == 300.0) {
                        novoProduto.setUnidadeMedida(mililitro);
                    }
                    novoProduto.setDataValidade(dataValidade);
                    produtosUnitarios.add(novoProduto);

                    long periodo = ChronoUnit.DAYS.between(LocalDate.now(), novoProduto.getDataValidade());

                    if (periodo <= 15) {
                        produtosEstragados.insert(novoProduto);
                    }

                    System.out.println("\n\u001B[32mProduto cadastrado!\u001B[0m");
                }
                case 2 -> {
                    if (produtosEstragados.isEmpty()) {
                        for (ProdutoUnitario produto : produtosUnitarios) {
                            long periodo = ChronoUnit.DAYS.between(LocalDate.now(), produto.getDataValidade());

                            if (periodo <= 15) {
                                produtosEstragados.insert(produto);
                            }
                        }
                    }

                    if (!produtosEstragados.isEmpty() && filaAux.isEmpty()) {
                        while (!produtosEstragados.isEmpty()) {
                            ProdutoUnitario produto = produtosEstragados.poll();
                            filaAux.insert(produto);
                            produtosUnitarios.remove(produto);
                        }

                        for (int i = 0; i < produtosUnitarios.size(); i++) {
                            filaAux.insert(produtosUnitarios.get(i));
                        }
                    }

                    System.out.println("PRODUTOS CADASTRADOS:");
                    System.out.print("\n----------------------------------------\n");
                    System.out.printf("%-15S | %-9S | %-12S\n", "nome", "peso", "vencimento");
                    produtosUnitarios.clear();
                    int tamanhoFila = filaAux.getTamanho();
                    for (int i = tamanhoFila; i > 0; i--) {
                        ProdutoUnitario produtoUnitario = filaAux.poll();
                        String dataValidadeFormatada = produtoUnitario.getDataValidade().format(dataFormatada);
                        long periodo = ChronoUnit.DAYS.between(LocalDate.now(), produtoUnitario.getDataValidade());

                        if (periodo <= 15) {
                            System.out.printf("\u001B[31m%-15s | %6.2f %-2s | %-12s\n\u001B[0m", produtoUnitario.getNome(), produtoUnitario.getPeso(), produtoUnitario.getUnidadeMedida(),
                                    dataValidadeFormatada);
                        } else {
                            System.out.printf("%-15s | %6.2f %-2s | %-12s\n", produtoUnitario.getNome(), produtoUnitario.getPeso(), produtoUnitario.getUnidadeMedida(),
                                    dataValidadeFormatada);
                        }
                        produtosUnitarios.add(produtoUnitario);
                    }
                    System.out.print("----------------------------------------\n");
                }
            }
        } while (opcaoEscolhida != 3);
    }
}
