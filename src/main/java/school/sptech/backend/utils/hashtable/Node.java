package school.sptech.backend.utils.hashtable;


import school.sptech.backend.domain.tipoproduto.TipoProduto;

public class Node {
    private TipoProduto info;
    private Node next;

    public Node(TipoProduto info) {
        this.info = info;
        this.next = null;
    }

    public TipoProduto getInfo() {
        return info;
    }

    public void setInfo(TipoProduto info) {
        this.info = info;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }

}
