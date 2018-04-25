package scorritore_stringhe_awt;
public final class ListaConcatenata<T>{
    private NodoConc<T> testa;
    public ListaConcatenata(){
        this.testa = new NodoConc(null, null);
    }
    public int size(){
        NodoConc<T> node = testa;
        int index = 0;
        while(node.next != null){
            node = node.next;
            index++;
        }
        return index;
    }
    public void addTail(T info){
        NodoConc<T> node1 = testa;
        NodoConc<T> node2 = new NodoConc(info, null);
        while(node1.next != null)
            node1 = node1.next;
        node1.next = node2;
    }
    public T get(int pos){
        NodoConc<T> node = testa.next;
        for(int i=0;i<pos;i++)
            node = node.next;
        return node.info;
    }
    public void add(int pos, T info){
        NodoConc<T> node1 = testa;
        NodoConc<T> node2 = new NodoConc(info, null);
        for(int i=0;i<pos;i++)
            node1 = node1.next;
        node2.next = node1.next;
        node1.next = node2;
    }
    public T remove(int pos){
        NodoConc<T> node1 = testa;
        NodoConc<T> node2;
        for(int i=0;i<pos;i++)
            node1 = node1.next;
        node2 = node1.next;
        node1.next = node2.next;
        return node2.info;
    }
    private class NodoConc<T>{
        T info;
        NodoConc<T> next;
        NodoConc(T info, NodoConc<T> next){
            this.info = info;
            this.next = next;
        }
    }
}