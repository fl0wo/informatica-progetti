package amiciziefacebook.src;

public interface Edge<E> {

    /* Controlla se l'arco è diretto */
    public boolean isDirect();

 /* Controlla l'arco ha il nodo connected come from o to */
    public boolean connect(E connected);

 /* Restituisce il nodo in entrata */
    public E getFrom();

 /* Restituisce il nodo in uscita*/
    public E getTo();

 /* Restituisce il peso corrispondente dell'arco  */
    public Number getWeight();
}
