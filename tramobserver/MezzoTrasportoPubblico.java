package tramobserver;

/**
 * Interfaccia che devono avere sia i bus che i tram che i vaporetti.
 * 
 * @author Florian
 */
public interface MezzoTrasportoPubblico {
    
    /**
     * Sposta il mezzo alla fermata sucessiva
    */
    public void nextFermata();
    /**
     * Inverte la direzione del mezzo
     */
    public void gira();
    /**
     * Aggiorna tutte le fermate sulla sua posizione
     */
    public void aggiornaPos();
    
    /**
     * Notific tutte le fermate evidenziando che da ora
     * devono tener conto di questo mezzo
     */
    public void setInServizio(boolean inServizio);
    
    /**
     * Restituisce la posizione nella quale si trova
     * @return la posizione della fermata in cui si 
     * trova rispetto all'array
     */
    public int getPosFermata();
    
    /**
     * Dice se si sta muovendo
     * @return true se si sta muovendo
     */
    public boolean isInServizio();

    public int getTempo();
    
}
