package tramobserver;
/**
 *
 * @author Florian
 */
public class TramObserver {
    public static void main(String[] args) {
        
        ACTV mappa = new ACTV();
        
        Tram t1 = new Tram();
        Tram t2 = new Tram();
        Bus b1 = new Bus();
        
        mappa.addMezzo(t1);
        mappa.addMezzo(t2);
        mappa.addMezzo(b1);
        
        mappa.activateMezzo(t1);    // Lo metto in servizio = true
        mappa.activateMezzo(b1);    // Lo metto in servizio = true
        
        // Muovo il tram 1 di due fermate
        for (int i = 0; i < 10; i++) {
            mappa.muoviMezzo(t1);
        }
        
        // e il bus 1 di una ...
        for (int i = 0; i < 4; i++) {
            mappa.muoviMezzo(t2);   // ma non si muove in quanto é activate = false
        }

/* 
        A questo punto tutte le fermate sapranno quante fermate mancano perchè
        tram 1 e bus 1 arrivino da loro :D
*/
        
        System.out.println(mappa);
        System.out.println(mappa.toDisegnino());

        // se provo a muovere il tram di una fermata non si muovera in quanto ha in servizio = true
        mappa.addMezzo(t2); 
        
        
    }
    
}
