package school.sptech.backend.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import school.sptech.backend.domain.endereco.Endereco;
import school.sptech.backend.service.endereco.EnderecoViaCep;

public class TesteListaObj {
    
    public static void main(String[] args) {


        Scanner leitorInt = new Scanner(System.in);
        
        System.out.println(
            """
            Número de ruas por rota:        
            """
        );

        int numeroRuas = leitorInt.nextInt();

        if (numeroRuas <= 0) {
            System.out.println("Opção inválida");
        }


        EnderecoViaCep endereco1 = new EnderecoViaCep();
        EnderecoViaCep endereco2 = new EnderecoViaCep();
        EnderecoViaCep endereco3 = new EnderecoViaCep();
        EnderecoViaCep endereco4 = new EnderecoViaCep();

        endereco1.setLogradouro("Marco Aurélio 547");
        endereco2.setLogradouro("Ana Julieta 577");
        endereco3.setLogradouro("Haddock Lobo 595");
        endereco4.setLogradouro("Avenida Paulista 4600");

        EnderecoViaCep[] lista = {endereco1,endereco2,endereco3, endereco4};
        
        ListaObj listaObj = new ListaObj<>(4);

        for (EnderecoViaCep endereco : lista) {
            System.out.println(endereco.getLogradouro());
        }

        System.out.println("----".repeat(20));


        listaObj.quickSort(lista, 0, lista.length-1);

        for (EnderecoViaCep endereco : lista) {
            System.out.println(endereco.getLogradouro());
        }

        System.out.println("----".repeat(20));

        System.out.println(listaObj.pesquisaBinariaLougradoro(lista, "Haddock Lobo 595").getLogradouro());

        System.out.println(listaObj.pesquisaBinariaLougradoro(lista, "Haddock Lobo 550"));

    }


    public static void menu(int numeroRuas){

        Scanner leitorInt = new Scanner(System.in);
        EnderecoViaCep[] listaRuas = new EnderecoViaCep[numeroRuas];
        

        System.out.println(

            """

            Selecione a opção:
            
            1- Adicionar uma nova rua
            2- Organizar as ruas em ordem alfabéticas
            3- Pesquisar por rua específica
            4- Sair
            """
        );

        int opcao = leitorInt.nextInt();

            switch (opcao) {
                case 1:
                    
                    break;
            
                default:
                    break;
            }

    }
}
