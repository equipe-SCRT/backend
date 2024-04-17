/*
package school.sptech.backendscrt;


import school.sptech.backendscrt.domain.produto.Produto;

import java.time.LocalDate;
import java.util.Arrays;

public class Ordenacao {
    public static void main(String[] args) {

        Produto produto1 = new Produto(1, "Arroz", LocalDate.of(2024, 12, 30), 5.90);

        Produto produto2 = new Produto(2, "Molho de Tomate", LocalDate.of(2025, 6, 30), 2.49);

        Produto produto3 = new Produto(3, "Macarrão", LocalDate.of(2024, 10, 20), 3.20);

        Produto produto4 = new Produto(4, "Óleo de Soja", LocalDate.of(2025, 5, 10), 6.80);

        Produto produto5 = new Produto(5, "Café", LocalDate.of(2024, 9, 8), 9.75);

        Produto produto6 = new Produto(6, "Feijão", LocalDate.of(2024, 11, 15), 4.50);

        Produto[] produtos = {
                produto1, produto2, produto3, produto4, produto5, produto6
        };

        Produto[] produtos2 = {
                produto1, produto2, produto3, produto4, produto5, produto6
        };
        Produto[] produtos3 = {
                produto1, produto2, produto3, produto4, produto5, produto6
        };

        System.out.println("Lista Desordenada");
        Arrays.stream(produtos).forEach(produto -> {
            System.out.println(produto.getNomeProduto());
        });

        System.out.println("----------------------");

        System.out.println("Bubble Sort");
        bubbleSort(produtos);
        Arrays.stream(produtos).forEach(produto -> {
            System.out.println(produto.getNomeProduto());
        });

        System.out.println("----------------------");

        System.out.println("Selection Sort");
        selectionSort(produtos2);
        Arrays.stream(produtos2).forEach(produto -> {
            System.out.println(produto.getNomeProduto());
        });

        System.out.println("----------------------");

        System.out.println("Insertion Sort");


        insertionSort(produtos3);
        Arrays.stream(produtos3).forEach(produto -> {
            System.out.println(produto.getNomeProduto());
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

    public static void insertionSort(Produto[] array) {
        int n = array.length;
        for (int j = 1; j < n; j++) {
            Produto key = array[j];
            int i = j-1;
            while ( (i > -1) && ( array [i].getDataValidade().isAfter(key.getDataValidade()) ) ) {
                array [i+1] = array [i];
                i--;
            }
            array[i+1] = key;
        }
    }
}
*/
