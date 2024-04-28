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

       // listaObj.quickSort(lista, 0, lista.length-1);

    }


    public static void menuInicial(){

        Scanner leitorInt = new Scanner(System.in);

        System.out.println(
                """
                1- Cadastrar Nova Rota
                2- Buscar por rua específica
                3- Sair
                """
        );

        int respostaUsuario = leitorInt.nextInt();


        switch (respostaUsuario){

            case 1:
                cadastrarRota();
                break;

            case 2:
                System.out.println("Necessário criar uma rota primeiro");
                menuInicial();
                break;

            case 3:
                System.out.println(
                        "\nSaindo...."
                );
                break;

            default:
                System.out.println("\n Valor incorreto, por favor, tente novamente!\n");
                menuInicial();
        }
    }


    public static void menuInicial(ArrayList lista, String nomeRota){
        Scanner leitorInt = new Scanner(System.in);

        System.out.println(
                """
                1- Cadastrar Nova Rota
                2- Buscar por rua específica
                3- Sair
                """
        );
        int respostaUsuario = leitorInt.nextInt();


        switch (respostaUsuario) {

            case 1:
                cadastrarRota();
                break;

            case 2:
                buscarRuaEspecifica(lista, nomeRota);
                break;

            case 3:
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
                \nCadastrar
                
                Nome da rota:
                """
        );

        String nomeRota = leitor.nextLine();

        if (alterarNome(nomeRota, leitor)){
            cadastrarRota();
        }

        int ordemRua = 0;
        String rua = "";
        adicionarRua(++ordemRua, nomeRota, leitor, ruasDaRota, rua);

    }

    public static boolean alterarNome(String nomeObjeto, Scanner leitor){
        System.out.println("Nome da rota: " + nomeObjeto + "\nDeseja alterar o nome? S/N");

        return leitor.nextLine().equals("S");

    }


    public static void adicionarRua(int quantidadeRua, String nomeRota, Scanner leitor, ArrayList ruasDaRota, String rua) {
        rua = "";
        System.out.println("\n"  + nomeRota);
        System.out.println("\nRua " + quantidadeRua + ":\n");
        while (rua.isEmpty()){
            rua = leitor.nextLine();
        }


//        if (alterarNome(rua, leitor)) {
//            adicionarRua(quantidadeRua, nomeRota, leitor, ruasDaRota);
//        }

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
                adicionarRua(++quantidadeRua, nomeRota, leitor, ruasDaRota, rua);
                break;

            case 2:
                ruasDaRota.add(rua);
                exibirCadastroRota(nomeRota, ruasDaRota);
                break;
        }
    }


    public static void exibirCadastroRota(String nomedaRota, ArrayList ruasDaRota){
        Scanner leitor = new Scanner(System.in);

        System.out.print("\nRota cadastrada: " + nomedaRota + "\nRuas cadastradas: ");

        for (int i = 0; i < ruasDaRota.size(); i++) {
            System.out.printf("\n%d - %s", (i+1), ruasDaRota.get(i));
        }


        System.out.println("\nDeseja alterar alguma rua? S/N");

        if (leitor.nextLine().equals("S")){
            alterarRota();
        }

        System.out.println("Rota cridada com sucesso!");

        //Aqui salva no banco
        menuInicial(ruasDaRota, nomedaRota);

    }


    public static  void alterarRota(){}

    public static void buscarRuaEspecifica(ArrayList lista, String nomeRota){
        ListaObj<String> listaObj = new ListaObj<>(lista.size());
        Scanner leitor = new Scanner(System.in);

        EnderecoViaCep endereco1 = new EnderecoViaCep();
        endereco1.setLogradouro((String) lista.get(0));
        EnderecoViaCep endereco2 = new EnderecoViaCep();
        endereco2.setLogradouro((String) lista.get(1));
        EnderecoViaCep endereco3 = new EnderecoViaCep();
        endereco3.setLogradouro((String) lista.get(2));

        for (int i = 0; i < lista.size(); i++) {
           listaObj.adiciona((String) lista.get(i));

        }

        EnderecoViaCep[] vetorOrdenado = {endereco1,endereco2,endereco3};


        System.out.println("Digite o nome da rua: ");
        String ruaProcurada = leitor.nextLine();

        listaObj.quickSort(vetorOrdenado, 0, vetorOrdenado.length-1);
        EnderecoViaCep pesquisa = listaObj.pesquisaBinariaLougradoro(vetorOrdenado, ruaProcurada);

        if (pesquisa == null){
            System.out.println("\nRua não cadastrada!" + "\n");
        } else {
            System.out.println("\nRua cadastrada na rota: " + nomeRota + "\n" );
        }

        menuInicial(lista, nomeRota);

    }

}
