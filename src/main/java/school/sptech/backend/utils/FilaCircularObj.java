package school.sptech.backend.utils;

import lombok.Data;

@Data
public class FilaCircularObj<T> {

    private int tamanho, inicio, fim;
    private T[] fila;

    public FilaCircularObj(int capacidade) {
        this.fila = (T[]) new Object[capacidade];
        this.inicio = 0;
        this.fim = 0;
        this.tamanho = 0;
    }

    public boolean isEmpty() {
        return tamanho == 0;
    }

    public boolean isFull() {
        return tamanho == fila.length;
    }

    public void insert(T info) {
        if (isFull()) {
            throw new IllegalStateException("Fila cheia");
        } else {
            fila[fim] = info;
            fim = (fim + 1) % fila.length;
            tamanho++;
        }
    }

    public T peek() {
        return fila[inicio];
    }

    public T poll() {
        T primeiro = peek();
        if (!isEmpty()) {
            fila[inicio] = null;
            inicio = (inicio+1) % fila.length;
            tamanho--;
        }
        return primeiro;
    }

    public void exibe() {
        if (isEmpty()) {
            System.out.println("Fila vazia");
        } else {
            for (int i = inicio; i < tamanho; i++) {
                System.out.printf("Faltam menos de 15 dias para o vencimento do produto %s, " +
                        "considere colocá-lo na próxima montagem de cestas.\n", fila[i]);
            }
        }
    }
}
