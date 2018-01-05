package studenti4icabr;

import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

/**
 *
 * @author Florian
 */
public class Studenti4ICABR extends JPanel {
    
    static JFrame schermo;
    private AlberoBinario albero;

    Studenti4ICABR(AlberoBinario albero) {
        this.albero = albero;   // questo fa startare il metodo PaintComponent!
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        RappresentaAlbero schermo = new RappresentaAlbero(this.albero,this.schermo, g);
    }

    public static void main(String[] args) {

        RappresentaAlbero schermo = new RappresentaAlbero();

        String[] listaAlunni = schermo.alunni4IC(); // Mi prendo la lista di alunni

        MioOggetto[] alunni = schermo.assegnaAlunni(listaAlunni);   // Li aggiungo in un array di classi "Nome" e "Cognome"
        
        AlberoBinario b = new AlberoBinario(alunni);  // Instanzio l'abero binario
        
        //b.aggiungiNodo(new MioOggetto("FRANCESCO", "FORTUNATO1"));
        
        //b.eliminaNodo(new MioOggetto("FLORIAN", "SABANI"));

        //b.aggiungiNodi(alunni);
        
        b.visitaSimmetricaCrescente(b.getRadice());

        System.out.println("Livello dell'albero é : " + b.getLivello());
        System.out.println("Il numero di alunni é : " + b.getNElementi());
        
        apriFinestra(b);
    }

    private static void apriFinestra(AlberoBinario b) {
        int SCHERMO_LARGHEZZA = 700;
        int SCHERMO_ALTEZZA = 700;
        boolean PUOI_MODIFICARE_SCHERMO = true;
        
        
        schermo = new JFrame();
        schermo.add(new Studenti4ICABR(b));
        schermo.setSize(SCHERMO_LARGHEZZA, SCHERMO_ALTEZZA);
        schermo.setResizable(PUOI_MODIFICARE_SCHERMO);
        schermo.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        schermo.setTitle("Elenco Alunni 4IC Albero Grafico by#Sabani.Florian");
        schermo.setVisible(true);
       //addMouseListener(this);
        
    }
    
    
}
