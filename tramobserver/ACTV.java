package tramobserver;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.Scanner;

/**
 *
 * @author Florian
 */
public class ACTV {

    private Observer[] fermate;
    private ArrayList<Observable> mezziDiTrasporto;

    /**
     * Mappa che coordinerà tutti i mezzi di trasporto aggiunti...
     */
    public ACTV() {
        try {
            leggiDaFile("fermate.txt");
            mezziDiTrasporto = new ArrayList<>();
        } catch (FileNotFoundException ex) {
            System.err.println("File non trovato!");
        }
    }

    /**
     * Metodo che permette di aggiungere un mezzo di trasporto alla mappa.
     * Notare che una volta aggiunto il mezzo , tutte le fermate devono sapere
     * della sua esistenza, e il mezzo deve sapere dell esistenza di tutte le
     * fermate
     *
     * @param mezzo Il mezzo da aggiungere
     */
    public void addMezzo(Observable mezzo) {
        int posMezzo = ((MezzoTrasportoPubblico)mezzo).getPosFermata();
        
        for (int i = 0,tmp; i < fermate.length-1; i++) {
            mezzo.addObserver(fermate[i]);
            
            tmp = i-posMezzo;
            ((Fermata)fermate[i]).addAttesa(tmp >=0 ? tmp : -1);            // Se e gia passato setto -1
        }
        mezziDiTrasporto.add(mezzo);
    }

    public void disableMezzo(Observable mezzo) {
        attiva(mezzo,false);
    }

    public void activateMezzo(Observable mezzo) {
        attiva(mezzo,true);
    }

    /**
     * Sposta un mezzo alla fermata sucessiva.
     * @param mezzo Il mezzo da spostare
     */
    void muoviMezzo(Observable mezzo) {
        if(((MezzoTrasportoPubblico)mezzo).isInServizio()){
            ((MezzoTrasportoPubblico)mezzo).nextFermata();
        }
    }

    /**
     * Programma che mi permette di leggere e caricare da file le fermate in un array.
     * @param nomeFile il percorso del file dalla quale andare a leggere...
     * @throws FileNotFoundException in casi il file non é stato trovato
     */
    private void leggiDaFile(String nomeFile) throws FileNotFoundException {
        int numFermate = 0;

        Scanner scan = new Scanner(new BufferedReader(new FileReader(nomeFile)));
        scan.useDelimiter("-");

        if (scan.hasNext()){
            numFermate = Integer.parseInt(scan.next());
            fermate = new Fermata[numFermate];
        }

        for (int i = 0; i < numFermate && scan.hasNext(); i++) {
            fermate[i] = new Fermata(scan.next(),i);
        }
    }

    @Override
    public String toString() {
        String fermateString = "";

        for (int i = 0; i < fermate.length-1; i++) {
            fermateString += fermate[i];
        }
        return "fermate : \n " + fermateString;
    }

    public char[] toDisegnino(){
        char[] fermate = new char[this.fermate.length];
        for (int i = 0; i < fermate.length; i++) {
            fermate[i]='.';
        }
        for (int i = 0; i < mezziDiTrasporto.size(); i++) {
            fermate[((MezzoTrasportoPubblico)mezziDiTrasporto.get(i)).getPosFermata()] = 'M';
        }
        return fermate;
    }
    
    private void attiva(Observable mezzo, boolean b) {
        ((MezzoTrasportoPubblico)mezziDiTrasporto.get(mezziDiTrasporto.indexOf(mezzo))).setInServizio(b);
    }

    

}
