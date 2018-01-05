package studenti4icabr;

import java.util.Arrays;

/**
 *
 * @author Florian
 */
public class AlberoBinario {

    private Nodo radice;
    private int n_elementi;
    private MioOggetto[] lista;

    public AlberoBinario(MioOggetto[] lista) {
        this.lista = lista;
        this.n_elementi += lista.length;

        Arrays.sort(lista);

        this.radice = new Nodo(lista, -1, lista.length,null);
    }

    public AlberoBinario() {
        this.radice = null;
        this.lista = new MioOggetto[0];
    }

    public AlberoBinario(Nodo radice) {
        this.radice = radice;
    }

    /**
     * Ci permette di aggiungere un nodo al nostro Albero, rispettando la
     * convenzione che il nodo di sinistra sarà sempre più piccolo di quello di
     * destra
     *
     * @param mioOggetto
     */
    public void aggiungiNodo(MioOggetto mioOggetto) {

        //Crea il nuovo nodo con valore l'oggetto che vogliamo aggiungere
        Nodo nuovoNodo = new Nodo(mioOggetto);

        // Se non c'e ancora una radice , vuol dire che questa é la prima
        if (this.radice == null) {
            this.radice = nuovoNodo;
            this.n_elementi = 1;
        } else {
            // Impostiamo un nodo la nostra radice
            // dalla quale parteremo
            Nodo nodoCorrente = this.radice;

            // Padre del nodo corrente
            Nodo padre;

            while (true) {
                // inizialmente nodoCorrente é il padre di se stesso
                padre = nodoCorrente;

                // Controlliamo dove dovrebbe andare
                // Controlliamo con l'interfaccia Comparable
                if (nuovoNodo.getValue().compareTo(nodoCorrente.getValue()) < 0) {

                    // Se returna -1 vado a sinistra in quanto sono piu piccolo
                    nodoCorrente = nodoCorrente.getSinistra();

                    //Se il figlio di sinistra é nullo
                    if (nodoCorrente == null) {

                        // settaci il nostro nodo
                        padre.setSinistra(nuovoNodo);
                        this.incrementaLivello();
                        return; // Esco in quanto aggiunto il nodo

                    }

                } //Se il valore é maggiore di 0 quindi 1 dobbiamo andare a destra
                else /*if(nuovoNodo.getValue().compareTo(nodoCorrente.getValue())>0)*/ {
                    nodoCorrente = nodoCorrente.getDestra();

                    // Se il nodo di destra non ha figli
                    if (nodoCorrente == null) {
                        //Semplicemente ci settiamo il nodo a destra...
                        padre.setDestra(nuovoNodo);
                        this.incrementaLivello();
                        return; // Esco in quanto aggiunto il nodo

                    }
                }/*else{
                    // Se voglio aggiungere un nodo uguale ad uno che gia c'e
                    // Perche il metodo compareTo ha dato come risultato 0
                    //In ogni caso ci chiediamo se quello di destra
                }*/
            }
        }

    }

    /**
     * Tutti i nodi vengono visitati dall'alto verso il basso, ma stampando le
     * informazioni di quelli di sinistra prima anzichè quelli di destra ,
     * stamando gli "MioOggetto" in ordine crescente
     *
     * @param nodoCorrente
     */
    public void visitaSimmetricaCrescente(Nodo nodoCorrente) {

        if (nodoCorrente != null) {
            // Da sinistra verso destra
            visitaSimmetricaCrescente(nodoCorrente.getSinistra());
            System.out.println(nodoCorrente.getInfo());
            visitaSimmetricaCrescente(nodoCorrente.getDestra());
        }
    }

    public void visitaSimmetricaDecrescente(Nodo nodoCorrente) {

        if (nodoCorrente != null) {
            // Da destra verso sinistra
            visitaSimmetricaDecrescente(nodoCorrente.getDestra());
            System.out.println(nodoCorrente.getInfo());
            visitaSimmetricaDecrescente(nodoCorrente.getSinistra());
        }
    }

    public void visitaAnticipata(Nodo nodoCorrente) {

        if (nodoCorrente != null) {
            System.out.println(nodoCorrente.getInfo());
            visitaAnticipata(nodoCorrente.getSinistra());
            visitaAnticipata(nodoCorrente.getDestra());
        }
    }

    public void visitaPosticipata(Nodo nodoCorrente) {

        if (nodoCorrente != null) {
            visitaPosticipata(nodoCorrente.getSinistra());
            visitaPosticipata(nodoCorrente.getDestra());
            System.out.println(nodoCorrente.getInfo());
        }
    }

    public void aggiungiNodi(MioOggetto[] alunni) {

        concatenaLista(alunni);

        for (int i = 0; i < alunni.length; i++) {
            this.aggiungiNodo(alunni[i]);  //Aggiungo 1 ad 1 gli elementi dell'array non ordinato
        }
    }

    public void eliminaNodo(MioOggetto daCercare) {
        
        Nodo elimina = this.trovaNodo(daCercare);
        
        if (elimina != null) {  // Se esiste l'oggetto che vogliamo eliminare
            Nodo padre = elimina.getPadre();    // Mi prendo il padre
            if (elimina.isFoglia()) {   // Se e una foglia 
                if(padre.getDestra().getValue().compareTo(daCercare) ==0){  // scopro se fa parte del ramo di destra
                    padre.setDestra(null);
                }else if(padre.getSinistra().getValue().compareTo(daCercare) ==0){  // o di sinistra
                    padre.setSinistra(null);    // e poi lo fuck dappo
                }
                
            } else {
                if (elimina.getDestra() != null) {  // Mi chiedo ha nodi a destra
                    padre.setDestra(elimina.getDestra()); 
                }
                if (elimina.getSinistra() != null) {    // o a sinistra
                    padre.setSinistra(elimina.getSinistra());  // Se si glieli setto al padre
                }
                
            }
        }
    }

    public Nodo trovaNodo(MioOggetto daCercare) {

        // Inizia all'inizio dell'albero
        Nodo nodoCorrente = radice;
        Nodo padre = nodoCorrente;
        // Finche non troviamo il nodo continuiamo a cercare
        while (nodoCorrente.getValue().compareTo(daCercare) != 0) {

            // se dovrebbe essere a sinistra
            if (nodoCorrente.getValue().compareTo(daCercare) > 0) {
                // Concentriamoci sul nodo di sinistra
                nodoCorrente = nodoCorrente.getSinistra();
            } else {
                if (nodoCorrente.getValue().compareTo(daCercare) < 0) {
                    // Concentriamoci su quello di destra
                    nodoCorrente = nodoCorrente.getDestra();
                }
            }
            // Non labbiamo trovato
            if (nodoCorrente == null) {
                return null;
            }
            if (/*(nodoCorrente.getDestra() != null && nodoCorrente.getSinistra()!=null)*/!nodoCorrente.isFoglia()
                    || nodoCorrente.getValue().compareTo(daCercare) != 0) {
                padre = nodoCorrente;
            }
            //confronto = nodoCorrente.getValue().compareTo(daCercare);
        }
        nodoCorrente.setPadre(padre);
        // Vuol dire che é uscito 0
        return nodoCorrente;
    }

    public Nodo getRadice() {
        return this.radice;
    }

    public int getLivello() {
        return log2(this.n_elementi) + 1;    //2^n -1 
    }

    public int getNElementi() {
        return this.n_elementi + 1;
    }

    private int log2(int n_elementi) {
        return 31 - Integer.numberOfLeadingZeros(n_elementi);
    }

    private void incrementaLivello() {
        this.n_elementi++;
    }

    private void concatenaLista(MioOggetto[] alunni) {

        int nuovaGrandezza = lista.length + alunni.length;

        MioOggetto[] nuovaLista = new MioOggetto[nuovaGrandezza];

        for (int i = 0; i < lista.length; i++) {
            nuovaLista[i] = lista[i];
        }
        for (int i = 0; i < alunni.length; i++) {
            nuovaLista[i + lista.length] = alunni[i];
        }

        this.lista = nuovaLista;
    }

    private int controllaBilanciamento(Nodo nodoCorrente) {
        if (nodoCorrente == null) {
            return 0;
        }
        int alberoSinistra = controllaBilanciamento(nodoCorrente.getSinistra());
        if (alberoSinistra == -1) {
            return -1;
        }
        int alberoDestra = controllaBilanciamento(nodoCorrente.getDestra());
        if (alberoDestra == -1) {
            return -1;
        }
        //abs = a < 0 se si returno -a  altrimenti lascio a é il modulo in pratica
        if (Math.abs(alberoSinistra - alberoDestra) > 1) {
            return -1;
        }
        //max = il valore maggiore dei due
        //se e ordinato returna il livello dell'albero
        return (Math.max(alberoSinistra, alberoDestra) + 1);
    }

    public boolean isBilanciato() {
        return controllaBilanciamento(this.radice) != -1;
    }

    private void assegnaPadre(){
        
    }
    
}
