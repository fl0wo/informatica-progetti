package tramobserver;

import java.util.Observable;
import java.util.Observer;

/**
 *
 * @author Florian
 */
public class Fermata implements Observer{
    
    private final String nomeFermata;
    private int attesaSupposta;
    private int posizioneNellArray;

    public Fermata(String nomeFermata,int posizioneNellArray) {
        this.nomeFermata = nomeFermata;
        this.posizioneNellArray = posizioneNellArray;
    }
    
    public void addAttesa(int minuti){
        attesaSupposta = minuti;
    }

    public String getNomeFermata() {
        return nomeFermata;
    }

    @Override
    public String toString() {
        return nomeFermata+ "\n "+ (attesaSupposta<0 ? "Mezzo già passato.. sei in ritardo ..." : "Prossimo mezzo tra : \t"+attesaSupposta+" minuti.");
    }
    
    @Override
    public void update(Observable o, Object arg) {
        int tmp = posizioneNellArray-((MezzoTrasportoPubblico)o).getPosFermata();
       addAttesa(tmp >=0 ? tmp : -1);
    }

    public int getPosizione() {
        return posizioneNellArray;
    }
    
}
