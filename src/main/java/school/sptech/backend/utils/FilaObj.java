package school.sptech.backend.utils;

import lombok.Data;

@Data
public class FilaObj<T> {

    private T[] fila;
    private int tamanho;

    public FilaObj(int capacidade) {
        this.tamanho = 0;
        this.fila = (T[]) new Object[capacidade];
    }

    public boolean isEmpty() {
        return tamanho == 0;
    }

    public boolean isFull() {
        return tamanho == fila.length;
    }

    public void insert(T info) {
        if (!isFull()) {
            fila[tamanho++] = info;
        } else {
            throw new IllegalStateException();
        }
    }

    public T peek() {
        return fila[0];
    }

    public T poll() {
        T info = peek();
        if (!isEmpty()) {
            for (int i = 0; i < tamanho - 1; i++) {
                fila[i] = fila[i + 1];
            }
            fila[tamanho - 1] = null;
            tamanho--;
        }
        return info;
    }

    public void exibe() {
        for (int i = 0; i < tamanho; i++) {
            System.out.println("Faltam 15 dias para o vencimento do produto %s, considere colocá-lo na próxima montagem de cestas.".formatted(fila[i]));
        }
    }
}
