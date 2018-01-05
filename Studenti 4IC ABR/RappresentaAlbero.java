package studenti4icabr;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author Florian
 */
public final class RappresentaAlbero extends JPanel implements MouseListener, KeyListener {

    private int SCHERMO_LARGHEZZA = 700;
    private int SCHERMO_ALTEZZA = 700;

    private int bordoAltezza = 50;
    private Color colorePallini;

    private int raggio;
    private AlberoBinario albero;
    private EventiMouse mouse;

    private Nodo nodoIlluminato;
    private boolean provoDisegnare;
    JPanel panel = new JPanel();
    JPanel c;
    public JFrame jframe;
    Graphics gi;

    public RappresentaAlbero() {
    }

    RappresentaAlbero(AlberoBinario albero, JFrame j, Graphics g) {
        this.gi = g;
        this.albero = albero;
        this.jframe = j;
        this.provoDisegnare = false;
        if (nodoIlluminato == null) {
            nodoIlluminato = this.albero.getRadice();
        }
        disegnaAlbero(g);
        addMouseListener(this);
        addKeyListener(this);
        addMouseListener(this);
        panel.addKeyListener(this);
        panel.addMouseListener(this);
        jframe.addKeyListener(this);
        jframe.addMouseListener(this);
        //jframe.mouse = new EventiMouse(albero,this.raggio,this);
    }

    public MioOggetto[] assegnaAlunni(String[] listaAlunni) {
        MioOggetto[] alunni = new MioOggetto[listaAlunni.length];
        for (int i = 0; i < listaAlunni.length; i++) {
            alunni[i] = new MioOggetto(listaAlunni[i].substring(listaAlunni[i].indexOf(" ") + 1, listaAlunni[i].length()), listaAlunni[i].substring(0, listaAlunni[i].indexOf(" ")));
        }
        return alunni;
    }

    public void disordina(MioOggetto[] alunni) {
        Random r = new Random();
        int min = 1 * alunni.length;
        int max = 10 * alunni.length;

        /*
        Faccio una cosa furbissima sposto lelemento centrale all inizio 
        mescolando larray senza toccare il primo elemento :D :D 
         */
        int sfasamento = 4;
        int sfasamentoCasuale = sfasamento - r.nextInt(sfasamento * 2);
        MioOggetto centrale = alunni[alunni.length / 2 + sfasamentoCasuale];
        alunni[alunni.length / 2 + sfasamentoCasuale] = alunni[0];
        alunni[0] = centrale;

        for (int i = 1; i < r.nextInt((min + max)) - min; i++) {
            for (int j = 1; j < alunni.length; j++) {
                int primoElementoCasuale = 1 + r.nextInt(alunni.length - 1);
                int secondoElementoCasuale = 1 + r.nextInt(alunni.length - 1);
                while (secondoElementoCasuale == primoElementoCasuale) {
                    secondoElementoCasuale = 1 + r.nextInt(alunni.length - 1);  // Non swappo lo stesso elemento non avrebbe senso
                }
                //SWAP SWAP SWAP SWAP
                MioOggetto temporaneo = alunni[primoElementoCasuale];
                alunni[primoElementoCasuale] = alunni[secondoElementoCasuale];
                alunni[secondoElementoCasuale] = temporaneo;
            }
        }
    }

    public String[] alunni4IC() {
        return new String[]{
            "AMBROS DAVID",
            "ARGENTI MICHELE",
            "BOLZONELLA SIMONE",
            "BOTUSHAROV STOYAN MIHAYLOV",
            "CAPPELLATO PIETRO",
            "D'ATTERO ANDREA",
            "DAMINATO LUCA",
            "FORTUNATO FRANCESCO",
            "FUSATI MATTEO",
            "GAVIOLI FEDERICO",
            "INGALISO CARLO",
            "MILETO EDOARDO",
            "NARDI ANDREA",
            "PAGANO MATTEO",
            "PISCIOTTA DAVIDE",
            "PISTOLLATO MARCO",
			"DELAZZARI RICCARDO",
            "RAGAZZO MATTEO",
            "RAVAGNAN GIACOMO COSTANTINO",
            "ROMANO GIACOMO",
            "ROSSATO FRANCESCO",
            "SABANI FLORIAN",
            "SCAGGIANTE FEDERICO",
            "STEVANATO GIOVANNI",
            "TREVISAN FRANCESCO"
        };
    }

    public void disegnaAlbero(Graphics g) {
        final int VALORE_MEDIO = 25;
        int percentualeBordo = 10;  // 10% di schermo é di bordo
        int bordoLarghezza = (percentualeBordo * SCHERMO_LARGHEZZA) / 100; // in pxl il 10%
        this.raggio = (bordoLarghezza / 2) - ((bordoLarghezza / 2) - VALORE_MEDIO);

        int x = SCHERMO_LARGHEZZA / 2, y = bordoAltezza;

        int spazio = 140;   //Larghezza iniziale
        colorePallini = g.getColor();

        stampaAlbero(g, this.nodoIlluminato, x, y, spazio, raggio / 2);

        if (albero.isBilanciato()) {
            stampaLivello(g, albero.getLivello());
        }
    }

    private void disegnaPallino(Nodo n, int x, int y, int raggioPalliniX, int raggioPalliniY, Graphics g) {
        // Se non é nullo lo disegno pieno
        if (n != null) {
            n.setPoint(new Point(x, y));

            if (n == this.nodoIlluminato) {
                System.out.println("ciao");
                this.c = new GPanel(n.getPunto());
                
                this.jframe.add(c);
            }

            // Se é una foglia lo disegno verde
            if (n.isFoglia()) {
                g.setColor(Color.green);    // Se e una foglia
                g.fillOval(x, y, raggioPalliniX, raggioPalliniY);
                g.setColor(colorePallini);
                //disegnaCollegamento();
            } else {
                // Altrimenti nero
                g.fillOval(x, y, raggioPalliniX, raggioPalliniY);
            }
        }

        int larghezzaMaxTesto = 100;
        int altezzaMaxTesto = 20;

        int xCentrato = x - (larghezzaMaxTesto / 2) + raggioPalliniX / 2;   //Formula assurdissima
        int yCentrato = y - (altezzaMaxTesto / 2) - raggioPalliniY / 2;   //Formula assurdissima

        scriviCentrato(n.getValue().getCognome(), new Rectangle(xCentrato, yCentrato, larghezzaMaxTesto, altezzaMaxTesto), new Font("Raku.ttf", 10, 10), g);
    }

    private void scriviCentrato(String testo, Rectangle rettangolo, Font font, Graphics g) {
        // FontMetrics e una classe che dato il font lo converte in px lettera per lettera
        FontMetrics metrica = g.getFontMetrics(font);
        // Troviamo le coordinate x per il testo
        int x = rettangolo.x + (rettangolo.width - metrica.stringWidth(testo)) / 2;
        // Troviamo la coordinata y 
        //+getAscent perche in JavaFX si parde da in alto a destra
        int y = rettangolo.y + ((rettangolo.height - metrica.getHeight()) / 2) + metrica.getAscent();

        g.setFont(font);
        g.setColor(Color.BLACK);
        g.drawString(testo, x, y);
        g.setColor(colorePallini);
    }

    private void stampaAlbero(Graphics g, Nodo n, int x, int y, int bordoLaterale, int raggio) {
        // Display the root

        disegnaPallino(n, x - raggio, y - raggio, 2 * raggio, 2 * raggio, g);   // SCRIVO ANCHE LE INFO

        if (n.getSinistra() != null) {
            // Draw a line to the left node
            g.setColor(Color.black);
            alberoSinistra(g, x - bordoLaterale, y + bordoAltezza, x, y, raggio);
            //Draw the left subtree 
            stampaAlbero(g, n.getSinistra(), x - bordoLaterale, y + bordoAltezza, bordoLaterale / 2, raggio);
        }

        if (n.getDestra() != null) {
            // disegna la line e verdo destra
            g.setColor(Color.black);
            alberoDestra(g, x + bordoLaterale, y + bordoAltezza, x, y, raggio);
            // Disegna lalbero in modo ricorsivo
            stampaAlbero(g, n.getDestra(), x + bordoLaterale, y + bordoAltezza, bordoLaterale / 2, raggio);
        }
        //this.gi.drawOval(x, y, bordoAltezza, bordoAltezza);
    }

    public void stampaLivello(Graphics g, int livello) {
        int percentualeBordo = 10;  // 10% di schermo é di bordo
        int bordoLarghezza = (percentualeBordo * SCHERMO_LARGHEZZA) / 100; // in pxl il 10%
        int bordoAltezza = (percentualeBordo * SCHERMO_ALTEZZA) / 100; // in pxl il 10%

        int x = 0 + bordoLarghezza;
        int y = 0;

        int larghezzaMaxTesto = 100;
        int altezzaMaxTesto = 20;

        int xCentrato = (x - (larghezzaMaxTesto / 2)) / 2;   //Formula assurdissima
        int yCentrato = y + (larghezzaMaxTesto / 2) - bordoLarghezza / 2;

        scriviCentrato("Lv Albero", new Rectangle(xCentrato, yCentrato, larghezzaMaxTesto, altezzaMaxTesto), new Font("Raku.ttf", 10, 15), g);

        yCentrato += yCentrato * 2;
        for (int i = 0; i < livello; i++, yCentrato += bordoAltezza / 1.5) {
            scriviCentrato(i + 1 + "", new Rectangle(xCentrato, yCentrato, larghezzaMaxTesto, altezzaMaxTesto), new Font("Raku.ttf", 10, 15), g);
        }
    }

    private void alberoSinistra(Graphics g, int x, int y, int xo, int yo, int raggio) {
        double d = Math.sqrt(bordoAltezza * bordoAltezza + (xo - x) * (xo - x));    //ipotenusa mi dicono
        int xo1 = (int) (x + raggio * (xo - x) / d);
        int yo1 = (int) (y - raggio * bordoAltezza / d);
        int xo2 = (int) (xo - raggio * (xo - x) / d);
        int yo2 = (int) (yo + raggio * bordoAltezza / d);
        g.drawLine(xo1, yo1, xo2, yo2);

        //scriviCentrato(null, new Rectangle(((x21 + x11) - 20) / 2, (y21 + y11) / 2, 20, 20), new Font("Raku.ttf", 10, 10), g);
        //g.drawString("0", ((x21 + x11) - 20) / 2, (y21 + y11) / 2);
    }

    private void alberoDestra(Graphics g, int x1, int y1, int x2, int y2, int raggio) {
        double d = Math.sqrt(bordoAltezza * bordoAltezza + (x2 - x1) * (x2 - x1));
        int x11 = (int) (x1 - raggio * (x1 - x2) / d);
        int y11 = (int) (y1 - raggio * bordoAltezza / d);
        int x21 = (int) (x2 + raggio * (x1 - x2) / d);
        int y21 = (int) (y2 + raggio * bordoAltezza / d);
        g.drawLine(x11, y11, x21, y21);

        //scriviCentrato(null, new Rectangle(((x21 + x11) - 20) / 2, (y21 + y11) / 2, 20, 20), new Font("Raku.ttf", 10, 10), g);
        //g.drawString("1", (x21 + x11) / 2, (y21 + y11) / 2);
    }

    private int distanza(Point posizioneMouse, Point posizioneNodo) {
        int ipotenusa = (int) Math.sqrt((posizioneMouse.x * posizioneMouse.x) + (posizioneNodo.x * posizioneNodo.x));
        return ipotenusa;
    }

    private void nodoCliccato(KeyEvent e) {
        //Point posizioneMouse = e.getLocationOnScreen();     // Differenza fra pos locale o pos globale
        int direzione = e.getKeyCode();

        //37 <- 38 su 39 -> 40 giu
        if (direzione == 37) {
            sinistra();
        }
        if (direzione == 38) {
            su();
        }
        if (direzione == 39) {
            destra();
        }
        if (direzione == 40) {
            giu();
        }

        //panel.repaint();
        //this.c = new GPanel(this.nodoIlluminato.getPunto());
        //this.disegnaAlbero(gi);
        //super.paintComponent(gi);
        //this.jframe.revalidate();
        //jframe.repaint();
        //this.illumina(gi, this.nodoIlluminato.getPunto());
    }

    @Override
    public void keyPressed(KeyEvent e) {
        nodoCliccato(e);
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    private void giu() {
        System.out.println("V");
        if (!this.nodoIlluminato.isFoglia() && (this.nodoIlluminato.getDestra() == null || this.nodoIlluminato.getSinistra() == null)) {
            if (this.nodoIlluminato.getDestra() != null) {
                this.nodoIlluminato = this.nodoIlluminato.getDestra();
                segnalaSpostamento("giu-destra", nodoIlluminato);
            } else if (this.nodoIlluminato.getSinistra() != null) {
                this.nodoIlluminato = this.nodoIlluminato.getSinistra();
                segnalaSpostamento("giu-sinistra", nodoIlluminato);
            }
        }
    }

    private void destra() {
        System.out.println("--->");
        if (this.nodoIlluminato.getDestra() != null) {
            this.nodoIlluminato = this.nodoIlluminato.getDestra();
            segnalaSpostamento("destra", nodoIlluminato);
        }
    }

    private void su() {
        System.out.println("^");
        if (this.nodoIlluminato.getPadre() != null) {
            this.nodoIlluminato = this.nodoIlluminato.getPadre();
            segnalaSpostamento("su", nodoIlluminato);
        }
    }

    private void sinistra() {
        System.out.println("<---");
        if (this.nodoIlluminato.getSinistra() != null) {
            this.nodoIlluminato = this.nodoIlluminato.getSinistra();
            segnalaSpostamento("sinistra", nodoIlluminato);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    private void segnalaSpostamento(String direzione, Nodo nodoIlluminato) {
        System.out.println("Spostamento verso " + direzione + " , nel nodo : " + nodoIlluminato.getInfo());
    }

    class GPanel extends JPanel {

        Point punto;
        
        public GPanel(Point punto) {
            jframe.setPreferredSize(new Dimension(300, 300));
            this.punto = punto;
        }

        @Override
        public void paintComponent(Graphics g) {
            this.illumina(g, punto);
        }

        public void illumina(Graphics g, Point punto) {
            //Se é vuoto lo disegno vuoto
            double moltiPlicatore = 1.5;
            g.setColor(Color.blue);    // Se e vuoto

            g.fillOval((int) (punto.x - (raggio / 2) / moltiPlicatore) + 2, (int) ((punto.y - (raggio / 2) / moltiPlicatore) + 1), (int) (raggio * moltiPlicatore), (int) (raggio * moltiPlicatore));
            g.setColor(colorePallini);
            
            this.setVisible(true);
        }
    }

}
