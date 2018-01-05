package snakefatto;

/**
 *
 * @author Florian
 */
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Panel;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.Timer;

public class Schermo extends Panel implements ActionListener {

    private final static int LARGHEZZA = 500;
    private final static int ALTEZZA = 500;

    private final static int _lato = 20;

    private final static int size = (LARGHEZZA * ALTEZZA) / (_lato * _lato);

    private boolean perso = true;

    private Timer timer;

    private static int ritardo = 35;

    private Serpente serpente = new Serpente();
    private Cibo cibo = new Cibo();

    public Schermo() {

        addKeyListener(new Tastiera());
        
        setBackground(Color.white);
        setFocusable(true);

        setPreferredSize(new Dimension(LARGHEZZA, ALTEZZA));

        starta();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        disegna(g);
    }

    void disegna(Graphics g) {
       //disegnaGrigliaDietro(g);

        if (perso == true) {
            g.setColor(Color.green);
            g.fillRect(cibo.ciboX(), cibo.ciboY(), _lato, _lato);

            // Disegna serpente
            for (int i = 0; i < serpente.getPezzi(); i++) {
                if (i == 0) {
                    g.setColor(Color.RED);
                } else {
                    if (i < 4) {
                        g.setColor(Color.ORANGE);
                    } else {
                        g.setColor(Color.YELLOW);
                    }
                }
                g.fillRect(serpente.getX(i), serpente.getY(i), _lato, _lato);
            }
            Toolkit.getDefaultToolkit().sync(); //sincronizza i disegni
        } else {
            perso(g);
        }
    }

    void starta() {
        serpente.setPezzi(3);

        for (int i = 0; i < serpente.getPezzi(); i++) {
            serpente.x(LARGHEZZA / 2);
            serpente.y(ALTEZZA / 2);
        }

        serpente.dx(true);

        cibo.spawnaCibo();

        timer = new Timer(ritardo, this);
        timer.start();
    }

    void collisioneCibo() {
        if ((isVicino(serpente.getX(0), cibo.ciboX(), 20)) && (isVicino(serpente.getY(0), cibo.ciboY(), 20))) {
            serpente.setPezzi(serpente.getPezzi() + 1);
            cibo.spawnaCibo();
        }
    }

    void collisioni() {

        //SE SI MANGIA
        for (int i = serpente.getPezzi(); i > 0; i--) {
            if ((i > 5) && (serpente.getX(0) == serpente.getX(i) && (serpente.getY(0) == serpente.getY(i)))) {
                perso = false;  // il serpente non puo mangiarsi se non ha almento 5 cosi
            }
        }

        // SE VA NEI BORDI
        if (serpente.getY(0) >= ALTEZZA) {
            serpente.y(0);
        }

        if (serpente.getY(0) < 0) {
            serpente.y(ALTEZZA);
        }

        if (serpente.getX(0) >= LARGHEZZA) {
            serpente.x(0);
        }

        if (serpente.getX(0) < 0) {
            serpente.x(LARGHEZZA);
        }

        if (!perso) {
            timer.stop();
        }
    }

    void perso(Graphics g) {
        String message = "Perso : " + this.serpente.getPezzi();

        Font font = new Font("Arial", Font.BOLD, 30);
        FontMetrics metrics = getFontMetrics(font);

        g.setColor(Color.black);
        g.setFont(font);

        g.drawString(message, (LARGHEZZA - metrics.stringWidth(message)) / 2, ALTEZZA / 2);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (perso == true) {
            collisioneCibo();
            collisioni();
            serpente.muovi();
        }
        repaint();
    }

    private void disegnaGrigliaDietro(Graphics g) {
        int nRow = _lato, nCol = _lato, nLastCol = _lato;

//Initialize values
        for (int i = 0; i < _lato; i++) {
            for (int j = 0; j < ((i == nRow - 1) ? nLastCol : nCol); j++) {
                g.fillRect(i * 15, j * 15, 10, 10);
            }
        }
    }

    private class Tastiera extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {

            int tasto = e.getKeyCode();

            if ((tasto == KeyEvent.VK_LEFT) && (!serpente.isDx())) {
                serpente.sx(true);
                serpente.su(false);
                serpente.giu(false);
            }

            if ((tasto == KeyEvent.VK_RIGHT) && (!serpente.isSx())) {
                serpente.dx(true);
                serpente.su(false);
                serpente.giu(false);
            }

            if ((tasto == KeyEvent.VK_UP) && (!serpente.isGiu())) {
                serpente.su(true);
                serpente.dx(false);
                serpente.sx(false);
            }

            if ((tasto == KeyEvent.VK_DOWN) && (!serpente.isSu())) {
                serpente.giu(true);
                serpente.dx(false);
                serpente.sx(false);
            }

            if ((tasto == KeyEvent.VK_ENTER) && (perso == false)) {

                perso = true;
                serpente.giu(false);
                serpente.dx(false);
                serpente.sx(false);
                serpente.su(false);

                starta();
            }
        }
    }

    private boolean isVicino(int a, int b, int closeness) {
        return Math.abs((long) a - b) <= closeness;
    }

    public static int nPunti() {
        return size;
    }

    public static int lunghezza() {
        return _lato;
    }
}
