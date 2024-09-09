package school.sptech.backend.utils.hashtable;


import school.sptech.backend.domain.tipoproduto.TipoProduto;

public class LinkedList {
    protected Node head;
    public LinkedList(){
        this.head = new Node(null);
    }

    public void insereNode(TipoProduto valor){
        Node node = new Node(valor);
        node.setNext(head.getNext());
        head.setNext(node);
    }

    public void exibe(){
        Node atual = head.getNext();
        while(atual != null){
            System.out.println(atual.getInfo());
            atual = atual.getNext();
        }
    }

    public Node buscaNode(Integer id){
        Node atual = head.getNext();
        while(atual != null){
            if (atual.getInfo().getId().equals(id)){
                return atual;
            }
            atual = atual.getNext();
        }
        return null;
    }

    public boolean removeNode(Integer valor){
        Node atual = head.getNext();
        Node anterior = head;
        while(atual != null){
            if (atual.getInfo().getId().equals(valor)){
                anterior.setNext(atual.getNext());
                return true;
            }
            anterior = atual;
            atual = atual.getNext();
        }
        return false;
    }
    public int getTamanho(){
        int tamanho = 0;
        Node atual = head.getNext();
        while(atual != null){
            tamanho++;
            atual = atual.getNext();
        }
        return tamanho;
    }
}
