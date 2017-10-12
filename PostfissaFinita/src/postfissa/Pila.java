package postfissa;

import java.util.Iterator;

/**
 *
 * @author Florian
 */
public class Pila<T> implements Iterable<T>{

    private Nodo<T> testa;
    private int lunghezza;

    public Pila() {
        this.testa = new Nodo<>(null);
    }

    public void push(T x) {
        Nodo nuovaTesta = new Nodo(x);
        if(testa==null){
            testa = nuovaTesta;
        }else{
            nuovaTesta.setSotto(testa);
            testa = nuovaTesta;
        }
        lunghezza++;
    }
    
    public T pop() {
        Nodo testAttuale = null;
        if(lunghezza>=0){
            testAttuale = testa;
            testa = testa.getSotto();
            lunghezza--;
        }else{
            System.out.println("La pila é vuota");
        }
        return (T) testAttuale.getValore();
    }

    public T getFront() {
        return this.testa.getValore();
    }

    public void visita(){
        System.out.println("VISITA : ");
        testa.visita(this.lunghezza);
    }
    
    public int getLunghezza() {
        return lunghezza;
    }

    public T getValore(int pos){
        Nodo viaggiante = testa;
        for (int i = 0; i < pos; i++) {
            viaggiante = viaggiante.getSotto();
        }
        return (T) viaggiante.getValore();
    }
    
    public Pila<T> clona() {
        Pila<T> pilaClonata = new Pila<>();
        int nElementi = this.lunghezza;
        Nodo aCapo = testa;
        for (int i = 0; i < nElementi; i++) {
            pilaClonata.push((T) aCapo.getValore());
            aCapo = (Nodo) aCapo.getSotto();
        }
        return pilaClonata;
    }
    
    public void rovescia(){
        Pila<T> pilaClonata = this.clona();
    }

    @Override
    public Iterator iterator() {
        return new Iterator<T>() {
            
            private int l = 0;
            private Nodo viaggiante = testa;
            
            @Override
            public boolean hasNext() {
                return l<lunghezza;
            }

            @Override
            public T next() {
                l++;
                T valoreAttuale = (T) viaggiante.getValore();
                viaggiante = viaggiante.getSotto(); // get next :P
                return valoreAttuale;
            }
        };
    }
}

class Nodo<T> {

    private T valore;
    private Nodo<T> sotto;

    public Nodo(T valore) {
        this.valore = valore;
    }

    public T getValore() {
        return valore;
    }

    public Nodo<T> getSotto() {
        return sotto;
    }

    public void setValore(T valore) {
        this.valore = valore;
    }

    public void visita(int contatore){
        System.out.println(valore);
        if(contatore>1)
            sotto.visita(contatore-1);
    }
    
    public void setSotto(Nodo<T> sopra) {
        this.sotto = sopra;
    }
    
}
