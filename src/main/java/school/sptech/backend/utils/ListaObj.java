package school.sptech.backend.utils;

import school.sptech.backend.service.endereco.EnderecoViaCep;

import java.util.Arrays;
import java.util.Comparator;

public class ListaObj <T> {

    private T[] vetor;
    private int nroElem;

    public ListaObj(int tamanho) {
        vetor = (T[]) new Object[tamanho];
        nroElem = 0;
    }

    public void setVetor(T[] vetor) {
        this.vetor = vetor;
    }

    public void adiciona(T elemento) {
        if (nroElem >= vetor.length) {
            System.out.println("Lista está cheia");
        }
        else {
            vetor[nroElem++] = elemento;
        }
    }

    public void exibe() {
        if (nroElem == 0) {
            System.out.println("\nA lista está vazia.");
        }
        else {
            System.out.println("\nElementos da lista:");
            for (int i = 0; i < nroElem; i++) {
                System.out.println(vetor[i]);
            }
        }
    }

    public int busca(T elementoBuscado) {
        for (int i = 0; i < nroElem; i++) {
            if (vetor[i].equals(elementoBuscado)) {
                return i;
            }
        }
        return -1;
    }
    public static EnderecoViaCep[] quickSort(EnderecoViaCep[] v, int indInicio, int indFim) {
        int i, j;
        String pivo;
        i = indInicio;
        j = indFim;
        pivo = v[(indInicio + indFim) / 2].getLogradouro();

        while (i <= j) {
            while (i < indFim && v[i].getLogradouro().compareTo(pivo) < 0) {
                i = i + 1;
            }
            while (j > indInicio && v[j].getLogradouro().compareTo(pivo) > 0) {
                j = j - 1;
            }
            if (i <= j) {
                EnderecoViaCep aux = v[i];
                v[i] = v[j];
                v[j] = aux;
                i = i + 1;
                j = j - 1;
            }
        }
        if (indInicio < j) {
            quickSort(v, indInicio, j);
        }
        if (i < indFim) {
            quickSort(v, i, indFim);
        }
        return v;
    }

    public boolean removePeloIndice (int indice) {
        if (indice < 0 || indice >= nroElem) {
            System.out.println("\nIndice invalido!");
            return false;
        }
        for (int i = indice; i < nroElem-1; i++) {
            vetor[i] = vetor[i+1];
        }

        nroElem--;
        return true;
    }

    public boolean removeElemento(T elementoARemover) {
        return removePeloIndice(busca(elementoARemover));
    }

    public int getTamanho() {
        return nroElem;
    }

    public T getElemento(int indice) {
        if (indice < 0 || indice >= nroElem) {
            return null;
        }
        else {
            return vetor[indice];
        }
    }

    public void setElemento(int indice, T elemeto){
        if (indice >= 0 && indice < nroElem) {
            vetor[indice] = elemeto;
        }
    }

    public void limpa() {
        nroElem = 0;
    }

    public EnderecoViaCep pesquisaBinariaLougradoro(EnderecoViaCep[] vetor, String x){
        int intSuperior, intInferior, intMeio, intComparacao;
        intInferior = 0;
        intSuperior = vetor.length-1;
        
        while (intInferior <= intSuperior) {
            intMeio = (intInferior + intSuperior)/2;
            intComparacao = x.compareTo(vetor[intMeio].getLogradouro());

            if (intComparacao == 0) {
                return vetor[intMeio];
            } 

            if (intComparacao > 0) {
                intInferior = intMeio + 1;                
            } else {
                intSuperior = intMeio -1;
            }
        }

        System.out.println("Elemento não está no vetor!");
        return null;
    }

    @Override
    public String toString() {
        return Arrays.toString(vetor);
    }
}
