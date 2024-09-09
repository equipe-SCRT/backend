package school.sptech.backend.utils.hashtable;


import school.sptech.backend.domain.tipoproduto.TipoProduto;

import java.util.ArrayList;
import java.util.List;

public class HashTable {
    private List<LinkedList> tab;
    private Integer tamanho;

    public HashTable(Integer tamanho) {
        this.tamanho = tamanho;
        this.tab = new ArrayList<>();
        for(int i = 0; i <= tamanho; i++){
            this.tab.add(new LinkedList());
        }
        System.out.println(this.tab);
    }

    public int funcaoHash(int x){
        return this.tamanho % x;
    }

    public void insere(TipoProduto x){
        this.tab.get(this.funcaoHash(x.getId())).insereNode(x);
    }

    public TipoProduto busca(int x){
        return this.tab.get(this.funcaoHash(x)).buscaNode(x).getInfo();
    }

    public boolean remove(Integer x){
        return this.tab.get(this.funcaoHash(x)).removeNode(x);
    }

    public static void main(String[] args) {
        HashTable t = new HashTable(20);
        TipoProduto t1 = new TipoProduto();
        t1.setId(2);
        t.insere(t1);
        System.out.println(t.busca(1));
    }


}
