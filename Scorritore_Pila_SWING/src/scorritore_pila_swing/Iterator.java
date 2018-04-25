package scorritore_pila_swing;
public class Iterator<T> implements IteratorInterface{
    private Pila pila;
    private NodoConc<T> node;
    private int index;
    public Iterator(Pila pila){
        this.pila = pila;
        this.node = pila.testa.next;
        this.index = 0;
    }
    @Override
    public boolean inside(){
        return index != pila.length();
    }
    @Override
    public T current(){
        return (T)node.info;
    }
    @Override
    public void goNext(){
        node = node.next;
        index++;
    }
    @Override
    public void goFirst(){
        node = pila.testa.next;
        index = 0;
    }
}