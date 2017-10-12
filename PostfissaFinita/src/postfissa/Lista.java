package postfissa;

import java.util.Iterator;

/**
 *
 * @author florian.sabani
 */
public class Lista<T> implements Iterable<T> {

    private Nodo testa, coda;
    protected int lunghezza = 0;

    public Lista() {
        testa = null;
        coda = null;
    }

    public Lista(int lunghezza) {
    }

    public Lista(T[] array) {
        for (int i = 0; i < array.length; i++) {
            this.addTail(array[i]);
        }
    }

    public void addHead(T valore) {
        Nodo daAggiungere = new Nodo(valore);
        Nodo testaVecchia = testa;
        if (lunghezza == 0) {
            coda = testa = daAggiungere;
        } else {
            testa = daAggiungere;
            testa.setAvanti(testaVecchia);
        }
        lunghezza++;
    }

    public void addTail(T valore) {// aggiungo in coda
        Nodo daAggiungere = new Nodo(valore);
        if (lunghezza == 0) {
            testa = coda = daAggiungere;
        } else {
            coda.setAvanti(daAggiungere);
            coda = daAggiungere;
        }
        lunghezza++;
    }

    public void add(int i, T valore) {
        if (i == 0) {
            this.addHead(valore);
        }
        if (i == lunghezza) {
            this.addTail(valore);
        }

        Nodo<T> corrente = testa;
        for (int j = 0; j < lunghezza; j++) {
            if (j == i - 1) {
                Nodo daAggiungere = new Nodo(valore);
                Nodo aCuiPunta = corrente.getAvanti();
                corrente.setAvanti(daAggiungere);
                daAggiungere.setAvanti(aCuiPunta);
                break;
            }
            corrente = corrente.getAvanti();
        }
        lunghezza++;
    }

    public void remove(int i) {
        if (i == 0) {
            this.rimuoviHead();
        }
        if (i == lunghezza) {
            this.rimuoviCoda();
        }

        Nodo<T> corrente = testa;
        for (int j = 0; j < lunghezza - 1; j++) {
            if (j == i - 1) {
                corrente.setAvanti(corrente.getAvanti().getAvanti());
                break;
            }
            corrente = corrente.getAvanti();
        }
        lunghezza--;
    }

    public T rimuoviHead() {
        T valore = null;
        if (lunghezza == 0) {
            System.out.println("errore coda vuota"); //stampo errore;
        } else {
            valore = (T) testa.getAvanti();
            testa = testa.getAvanti();
            lunghezza--;
            return valore;
        }
        return valore;
    }

    public T rimuoviCoda() {
        T valore = null;
        Nodo viaggiante = testa;
        for (int i = 0; i < lunghezza-1; i++) {
            viaggiante = viaggiante.getAvanti();
        }
        valore = (T) viaggiante.getAvanti();
        coda = viaggiante;
        lunghezza--;
        return valore;
    }

    public T getInfo(int i) {
        Nodo<T> corrente = testa;
        for (int j = 0; j < lunghezza; j++) {
            if (j == i) {
                return corrente.getValore();
            }
            corrente = corrente.getAvanti();
        }
        return null;
    }

    public void setInfo(int i, T info) {
        Nodo<T> corrente = testa;
        for (int j = 0; j < lunghezza; j++) {
            if (j == i) {
                corrente.setValore(info);
            }
            corrente = corrente.getAvanti();
        }
    }

    @Override
    public String toString() {
        String valoriTeste = "";
        Nodo viaggiante = testa;
        for (int i = 0; i < lunghezza; i++) {
            valoriTeste += " [" + viaggiante.getValore() + "] ";
            viaggiante = viaggiante.getAvanti();
        }
        return "Lista{" + "testa=" + valoriTeste + "=coda" + ", lunghezza=" + lunghezza + '}';
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {

            Nodo viaggiante = testa;
            int l = 0;

            @Override
            public boolean hasNext() {
                return l < lunghezza;
            }

            @Override
            public T next() {
                T valoreAttuale = (T) viaggiante.getValore();
                l++;
                viaggiante = viaggiante.getAvanti();
                return valoreAttuale;
            }
        };
    }

    class Nodo<T> {

        private Nodo avanti;
        private T valore;

        public Nodo(T valore) {
            this.valore = valore;
        }

        public Nodo getAvanti() {
            return avanti;
        }

        public T getValore() {
            return valore;
        }

        public void setAvanti(Nodo avanti) {
            this.avanti = avanti;
        }

        public void setValore(T valore) {
            this.valore = valore;
        }

    }
}
