package scorritore_pila_swing;
public interface IteratorInterface<T>{
    boolean inside();
    T current();
    void goNext();
    void goFirst();
}