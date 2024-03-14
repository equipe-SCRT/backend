package school.sptech.backendscrt;

import org.springframework.cglib.core.Local;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Ordenacao {
    public static void main(String[] args) {
        List<TipoProduto> tipoProdutoList = new ArrayList<>();
        tipoProdutoList.add(TipoProduto.PERECIVEL);

        Produto produto1 = new Produto(1, "Arroz", 5.90, LocalDate.of(2024, 12, 30), 0.5, "2812817389313", LocalDate.now(), "MarcaA", tipoProdutoList);

        Produto produto2 = new Produto(2, "Sabonete", 2.49, LocalDate.of(2025, 6, 30), 0.2, "9876543210987", LocalDate.now(), "MarcaB", tipoProdutoList);

        Produto produto3 = new Produto(3, "Macarrão", 3.20, LocalDate.of(2024, 10, 20), 0.4, "1234567890123", LocalDate.now(), "MarcaC", tipoProdutoList);

        Produto produto4 = new Produto(4, "Óleo de Soja", 6.80, LocalDate.of(2025, 5, 10), 1.0, "9876543210987", LocalDate.now(), "MarcaD", tipoProdutoList);

        Produto produto5 = new Produto(5, "Café", 9.75, LocalDate.of(2024, 9, 8), 0.3, "5678901234567", LocalDate.now(), "MarcaE", tipoProdutoList);

        Produto produto6 = new Produto(6, "Feijão", 4.50, LocalDate.of(2024, 11, 15), 0.8, "8712345678901", LocalDate.now(), "MarcaB", tipoProdutoList);

        Produto[] produtos = {
                produto1, produto2, produto3, produto4, produto5, produto6
        };

        Produto[] produtos2 = {
                produto1, produto2, produto3, produto4, produto5, produto6
        };

        System.out.println("Lista Desordenada");
        Arrays.stream(produtos).forEach(produto -> {
            System.out.println(produto.getNome());
        });

        System.out.println("----------------------");

        System.out.println("Bubble Sort");
        bubbleSort(produtos);
        Arrays.stream(produtos).forEach(produto -> {
            System.out.println(produto.getNome());
        });

        System.out.println("----------------------");

        System.out.println("Selection Sort");
        selectionSort(produtos2);
        Arrays.stream(produtos2).forEach(produto -> {
            System.out.println(produto.getNome());
        });

    }

    public static void bubbleSort(Produto[] produtos){
        for (int i = 0; i < produtos.length - 1; i++) {
            for (int j = 1; j < produtos.length - i; j++) {
                if (produtos[j - 1].getDataValidade().isAfter(produtos[j].getDataValidade())){
                    Produto aux = produtos[j];
                    produtos[j] = produtos[j - 1];
                    produtos[j - 1] = aux;
                }
            }
        }
    }

    public static void selectionSort(Produto[] programas) {
        for (int i = 0; i < programas.length - 1; i++) {
            int indMenor = i;
            for (int j = i + 1; j < programas.length; j++) {
                if (programas[j].getDataValidade().isBefore(programas[indMenor].getDataValidade())){
                    indMenor = j;
                }
            }
            Produto aux = programas[i];
            programas[i] = programas[indMenor];
            programas[indMenor] = aux;
        }
    }

}
