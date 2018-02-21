package tramobserver;

import java.util.Observable;

/**
 *
 * @author Florian
 */
public class Bus extends Observable implements MezzoTrasportoPubblico{
    //        setChanged(); notifyObservers();
    private int fermataAttuale;
    private int direzione = 1;
    private boolean inServizio = false;
    private final int TEMPO_TRA_UNA_FERMATA_E_LALTRA = 1000;    // ci mette 1 secondo per passare da una fermata all altra...

    public Bus(int fermataAttuale) {
        this.fermataAttuale = fermataAttuale;
    }
    
    public Bus(){
        this(0);
    }
    
    @Override
    public void nextFermata(){
        fermataAttuale+=direzione;
        setChanged();notifyObservers();
    }
    
    @Override
    public void gira(){
        direzione*=-1;
    }

    @Override
    public void aggiornaPos() {
        
    }

    @Override
    public void setInServizio(boolean inServizio) {
        this.inServizio = inServizio;
    }

    @Override
    public int getPosFermata() {
        return this.fermataAttuale;
    }

    @Override
    public boolean isInServizio() {
        return inServizio;
    }

    @Override
    public int getTempo() {
        return this.TEMPO_TRA_UNA_FERMATA_E_LALTRA;
    }
    
}
