package scorritore_pila_awt;
public class Pila<T> implements CrossableInterface{
    NodoConc<T> testa;
    private int index;
    public Pila(){
        this.index = 0;
        this.testa = new NodoConc(null, null);
    }
    public void push(T info){
        NodoConc<T> node = new NodoConc(info, null);
        if(isEmpty())
            testa.next = node;
        else{
            node.next = testa.next;
            testa.next = node;
        }
        this.index++;
    }
    public T pop(){
        NodoConc<T> node = new NodoConc(testa.next.info, null);
        if(testa.next.next == null)
            testa.next = null;
        else
            testa.next = testa.next.next;
        this.index--;
        return node.info;
    }
    public T top(){
        return testa.next.info;
    }
    public boolean isEmpty(){
        return this.index == 0;
    }
    public int length(){
        return this.index;
    }
	@Override
    public Iterator iterator(){
        return new Iterator(this);
    }
}