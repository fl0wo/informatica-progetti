package alberoespressioni;

import java.util.Arrays;

/**
 *
 * @author florian.sabani
 */
public class Espressione {

    Albero valutatore = new Albero();
    Postfissa lettore = new Postfissa();
    String espressione;
    
    Espressione(String espressione) {
        this.espressione = espressione;
        init(espressione);
    }

    private void costruisciAlbero(String[] postfissa) {
        Pila<Nodo> pila = new Pila<>();
        Nodo nodo, nodoSX, nodoDX;

        System.out.println(Arrays.toString(postfissa));

        for (int i = 0; i < postfissa.length; i++) {

            String letteraCorrente = postfissa[i];

            //Se noné un operatore lo aggiungo alla pila
            if (!isOperatore(letteraCorrente)) {

                if (isNumero(letteraCorrente)) {
                    System.out.println("INSERIAMO IL " + letteraCorrente);
                    nodo = new Nodo(new Item(Double.parseDouble(letteraCorrente), Item.NUMERO));
                    pila.push(nodo);
                } else {
                    System.out.println("INSERIAMO IL " + letteraCorrente);
                    nodo = new Nodo(new Item(letteraCorrente.charAt(0), Item.VARIABILE));
                    pila.push(nodo);
                }
            } else// Altrimenti mi prendo gliultimi due numeri aggiunti alla pila
            {
                nodo = new Nodo(new Item(letteraCorrente.charAt(0),Item.OPERATORE));

                // Una pila di nodi con il pop restituisce un nodo :P
                nodoSX = pila.pop();
                nodoDX = pila.pop();

                System.out.println(nodoSX.info + ":" + letteraCorrente + ":" + nodoDX.info);

                // Bisogna Invertire l'ordine per rispettare il segno nel caso della sottrazione
                nodo.sx = nodoDX;
                nodo.dx = nodoSX;

                //Aggiunge questa sotto espressione nella pila
                pila.push(nodo);
            }
        }

        //Il nodo rimanente nella pila sarà la radice
        nodo = pila.getFront();
        pila.pop();

        valutatore.radice = nodo;
    }

    boolean setVar(String x, double i) {
         espressione = espressione.replace(x,i+"");
         init(espressione);
         return true;
    }

    void stampaAlbero() {
        StampaAlbero.stampaNodo(valutatore.radice);
    }

    boolean isOperatore(String lettera) {
        return (lettera.equals("+") || lettera.equals("-") || lettera.equals("*") || lettera.equals("/") || lettera.equals("^"));
    }

    private boolean isNumero(String letteraCorrente) {
        if(letteraCorrente.contains("."))
         letteraCorrente = letteraCorrente.substring(0,letteraCorrente.indexOf("."));
        for (int i = 0; i < letteraCorrente.length(); i++) {
            int valore = (int) letteraCorrente.charAt(i);
            if (valore > 47 && valore < 58) {
                //va bene continua
            } else {
                return false;
            }
        }
        return true;
    }

    private void init(String espressione) {
        // Trasformo lespressione infissa in postfissa
        // in quanto risulta piu comodo poi da leggere
        String postfissa = lettore.leggi(espressione);
        System.out.println("Postfissa generata : " + postfissa);
        costruisciAlbero(postfissa.split(" "));
    }

    double getRis() {
        return valutatore.getRis();
    }

}
