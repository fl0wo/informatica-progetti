package scorritore_pila_awt;
public interface IteratorInterface<T>{
    boolean inside();
    T current();
    void goNext();
    void goFirst();
}