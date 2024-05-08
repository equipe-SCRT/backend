package school.sptech.backend.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import school.sptech.backend.api.produto.ProdutoController;
import school.sptech.backend.domain.endereco.Endereco;
import school.sptech.backend.domain.produto.Produto;
import school.sptech.backend.service.endereco.EnderecoViaCep;

public class TesteListaObj {


    
    public static void main(String[] args) {



        menuInicial();


        Scanner leitor = new Scanner(System.in);
        
        System.out.println(
            """
            Número de ruas por rota:        
            """
        );

        int numeroRuas = leitor.nextInt();

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


    public static void menuInicial(){

        Scanner leitorInt = new Scanner(System.in);

        System.out.println(
                """
                1- Cadastrar Nova Rota
                2- Sair
                """
        );

        int respostaUsuario = leitorInt.nextInt();


        switch (respostaUsuario){

            case 1:
                cadastrarRota();
                menuInicial();
                break;

            case 2:
                System.out.println(
                        "\nSaindo...."
                );
                break;

            default:
                System.out.println("\n Valor incorreto, por favor, tente novamente!\n");
                menuInicial();
        }
    }

    public static void cadastrarRota(){

        Scanner leitor = new Scanner(System.in);
        ArrayList<String> ruasDaRota = new ArrayList<>();
        System.out.println(
                """
                Cadastro
                
                Nome da rota
                """
        );

        String nomeRota = leitor.nextLine();

        if (alterarNome(nomeRota, leitor)){
            cadastrarRota();
        }

        int ordemRua = 0;
        adicionarRua(++ordemRua, nomeRota, leitor, ruasDaRota);

    }

    public static boolean alterarNome(String nomeObjeto, Scanner leitor){
        System.out.println("Nome da rota: " + nomeObjeto + "\nDeseja alterar o nome? S/N");

        return leitor.nextLine().equals("S");

    }


    public static void adicionarRua(int quantidadeRua, String nomeRota, Scanner leitor, ArrayList ruasDaRota) {
        System.out.println(nomeRota);
        System.out.println("Rua " + quantidadeRua + "\n");
        String rua = leitor.nextLine();

        if (alterarNome(rua, leitor)) {
            adicionarRua(quantidadeRua, nomeRota, leitor, ruasDaRota);
        }

        System.out.println(
                """
                        1- Adicionar outra rua
                        2- Cadastrar Rota
                        """
        );

        int escolhaUsuario = leitor.nextInt();

        switch (escolhaUsuario) {
            case 1:
                ruasDaRota.add(rua);
                adicionarRua(++quantidadeRua, nomeRota, leitor, ruasDaRota);
                break;

            case 2:
                exibirCadastroRota(nomeRota, ruasDaRota);
                break;
        }
    }


    public static void exibirCadastroRota(String nomedaRota, ArrayList ruasDaRota){
        Scanner leitor = new Scanner(System.in);

        System.out.print("Rota cadastrada: " + nomedaRota + "\nRuas cadastradas: ");

        for (int i = 0; i < ruasDaRota.size(); i++) {
            System.out.printf("%d - %s", i+1, ruasDaRota.get(i));
        }

        System.out.println(
                """
                       
                """
        );

        System.out.println("Deseja alterar alguma rua? S/N");

        if (leitor.nextLine().equals("S")){
            alterarRota();
        }

        //Aqui salva no banco


    }


    public static  void alterarRota(){}



































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
