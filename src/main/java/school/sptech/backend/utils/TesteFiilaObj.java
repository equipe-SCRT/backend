package school.sptech.backend.utils;

import school.sptech.backend.domain.produtounitario.ProdutoUnitario;
import school.sptech.backend.domain.unidademedida.UnidadeMedida;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TesteFiilaObj {
    public static void main(String[] args) {

        UnidadeMedida kilo = new UnidadeMedida();
        kilo.setRepresentacao("kg");
        UnidadeMedida mililitro = new UnidadeMedida();
        mililitro.setRepresentacao("ml");
        UnidadeMedida grama = new UnidadeMedida();
        grama.setRepresentacao("g");

        FilaObj<ProdutoUnitario> produtosEstragados = new FilaObj<>(5);

        ProdutoUnitario produtoUnitario1 = new ProdutoUnitario();
        produtoUnitario1.setNome("Arroz");
        produtoUnitario1.setDataValidade(LocalDate.of(2024, 5, 25));
        produtoUnitario1.setPeso(5.0);
        produtoUnitario1.setUnidadeMedida(kilo);

        ProdutoUnitario produtoUnitario2 = new ProdutoUnitario();
        produtoUnitario2.setNome("Feijão");
        produtoUnitario2.setDataValidade(LocalDate.of(2024, 6, 30));
        produtoUnitario2.setPeso(1.0);
        produtoUnitario2.setUnidadeMedida(kilo);

        ProdutoUnitario produtoUnitario3 = new ProdutoUnitario();
        produtoUnitario3.setNome("Óleo");
        produtoUnitario3.setDataValidade(LocalDate.of(2024, 5, 25));
        produtoUnitario3.setPeso(900.0);
        produtoUnitario3.setUnidadeMedida(mililitro);

        ProdutoUnitario produtoUnitario4 = new ProdutoUnitario();
        produtoUnitario4.setNome("Molho de tomate");
        produtoUnitario4.setDataValidade(LocalDate.of(2024, 5, 30));
        produtoUnitario4.setPeso(300.0);
        produtoUnitario4.setUnidadeMedida(grama);

        ProdutoUnitario produtoUnitario5 = new ProdutoUnitario();
        produtoUnitario5.setNome("Macarrão");
        produtoUnitario5.setDataValidade(LocalDate.of(2024, 6, 3));
        produtoUnitario5.setPeso(500.0);
        produtoUnitario5.setUnidadeMedida(grama);

        List<ProdutoUnitario> produtosUnitarios = new ArrayList<>();
        produtosUnitarios.add(produtoUnitario1);
        produtosUnitarios.add(produtoUnitario2);
        produtosUnitarios.add(produtoUnitario3);
        produtosUnitarios.add(produtoUnitario4);
        produtosUnitarios.add(produtoUnitario5);

        for (ProdutoUnitario produto : produtosUnitarios) {
            Period periodo = Period.between(produto.getDataValidade(), LocalDate.now());

            if (periodo.getDays() == 15) {
                produtosEstragados.insert(produto);
            }
        }

        Scanner leitor = new Scanner(System.in);
        int opcaoEscolhida;

        do {
            System.out.println("""
                    \nEscolha a opção desejada:
                    
                    1 - Ver produtos cadastrados
                    2 - Receber notificações
                    3 - Sair
                    """);

            opcaoEscolhida = leitor.nextInt();

            switch (opcaoEscolhida) {
                case 1 -> {
                    System.out.println("PRODUTOS CADASTRADOS:");
                    System.out.printf("\n----------------------------------------\n");
                    System.out.printf("%-15S | %-9S | %-12S\n", "nome", "peso", "vencimento");
                    for (int i = 0; i < produtosUnitarios.size(); i++) {
                        ProdutoUnitario produtoUnitario = produtosUnitarios.get(i);
                        System.out.printf("%-15s | %6.2f %-2s | %-12s\n", produtoUnitario.getNome(), produtoUnitario.getPeso(), produtoUnitario.getUnidadeMedida(),
                                produtoUnitario.getDataValidade());
                    }
                    System.out.printf("----------------------------------------\n");
                }
                case 2 -> {
                    System.out.println("NOTIFICAÇÕES:");
                    System.out.printf("-----------------------------------------------------------------------------------------------------\n");
                    produtosEstragados.exibe();
                    System.out.printf("-----------------------------------------------------------------------------------------------------\n");
                }
            }
        } while (opcaoEscolhida != 3);

        /*
            colocar os produtos proximos do vencimento acima na listagem, e em vermelho
            cadastrar um prod que ta pra vencer amanhã pra demonstrar a hora 
        */
    }
}