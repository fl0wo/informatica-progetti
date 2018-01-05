package alberoespressioni;

import java.text.DecimalFormat;

/**
 *
 * @author Florian
 */
public class Item {

    final static short VARIABILE = -1;
    final static short OPERATORE = 0;
    final static short NUMERO = 1;
    final static double MOLTIPLICAZIONE = '*',
            DIVISIONE = '/',
            SOMMA = '+';

    public double valore;
    short tipo;

    public Item(double valore, short tipo) {
        this.valore = valore;
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        String r = "";
        DecimalFormat formattazione = new DecimalFormat("#");
        if (this.tipo == VARIABILE) {
            r = "" + (char) valore;
        }
        if (this.tipo == OPERATORE) {
            r = "" + (char) valore;
        }
        if (this.tipo == NUMERO) {
            r = "" + formattazione.format(valore);
        }

        return r;
    }
    
}
