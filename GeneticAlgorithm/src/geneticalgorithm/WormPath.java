package geneticalgorithm;

import java.awt.Color;
import java.awt.Font;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author florian.sabani
 */
public class WormPath {

    private Verme[] vermi;

    private final int maxPassi = 30;
    private final double T_ESECUZIONE = 3;
    private int NGENERAZIONI = 200;
    private GraficaSemplice c;

    private Color rosso = new Color(231, 76, 60);
    private Color verde = new Color(46, 204, 113);
    private Color giallo = new Color(253, 203, 110);
    private Color viola = new Color(108, 92, 231);
    private Color blu = new Color(9, 132, 227);

    private Color background = Color.WHITE;

    private final Random r;

    private double endX, endY, startX, startY;

    private double grossezzaLinee = 0.0015;

    //first generation
    private boolean fG = true;
    private int nGEN = 0;

    WormPath(int l, double xStart, double yStart, double xEnd, double yEnd) {
        vermi = new Verme[l];
        r = new Random();
        c = new GraficaSemplice();

        c.setFont(new Font("Arial", 10, 10));
            
        c.testo(xStart,yStart, "GENERAZIONE N : "+nGEN);
        c.testo(xStart, yStart, "INIZIO");
        c.testo(xEnd, yEnd, "FINE");

        this.endX = xEnd;
        this.endY = yEnd;
        this.startX = xStart;
        this.startY = yStart;

        init(xStart, yStart, r);

    }

    private void init(double xStart, double yStart, Random r1) {
        vermi[0] = new Verme(c, rosso, r, maxPassi, null, null);
        vermi[1] = new Verme(c, rosso, r, maxPassi, null, null);

        vermi[0].setStart(xStart, yStart);
        vermi[1].setStart(xStart, yStart);

        for (int i = 0; i < vermi.length; i++) {
            vermi[i] = new Verme(c, verde, r, maxPassi, null, null);
            vermi[i].setStart(xStart, yStart);
        }
    }

    void start(double xStart, double yStart) {

        int start = 2;
        if (fG) {
            fG = false;
            start = 0;
            vermi[0].reset(null, null);
            vermi[1].reset(null, null);
        } else {
            vermi[0].justReset();
            vermi[1].justReset();
        }
        for (int i = start; i < vermi.length; i++) {
            vermi[i].reset(vermi[0], vermi[1]);
        }

        final int FPS = 60;
        double perc_incremento_fluidita = .1;

        
        
        for (int j = 0; j < maxPassi; j++) {
            c.pulisci(background);

            for (double p = 0; p <= 1; p += perc_incremento_fluidita) {
                c.testoDestra(xStart, yStart, "START");
                c.testoSinistra(endX, endY, "END");
                
                c.setFont(new Font("Arial", 10, 13));
                c.testoSinistra(.01,.9, "generation n : "+nGEN);
                c.testoSinistra(.01,.85, "n worms : "+vermi.length);
                
                for (int i = 0; i < vermi.length; i++) {
                    vermi[i].nextPasso(p);
                }

                int posmom = getMostVicino();
                
                c.testoSinistra(.01,.8, "curr closest distance : "+distanza(vermi[posmom].posX(), vermi[posmom].posY(), endX, endY));
                c.setFont(new Font("Arial", 10, 10));
                c.cerchioPieno(vermi[posmom].posX(), vermi[posmom].posY(), 0.015, giallo);
                c.cerchioPieno(vermi[posmom].posX(), vermi[posmom].posY(), 0.015 / 1.5D, background);

                c.linea(startX, startY, vermi[0].posX(), vermi[0].posY(), blu, grossezzaLinee);
                c.linea(startX, startY, vermi[1].posX(), vermi[1].posY(), blu, grossezzaLinee);
                c.linea(vermi[0].posX(), vermi[0].posY(), vermi[1].posX(), vermi[1].posY(), blu, grossezzaLinee);
                c.linea(vermi[0].posX(), vermi[0].posY(), vermi[1].posX(), vermi[1].posY(), blu, grossezzaLinee);
                c.linea(vermi[posmom].posX(), vermi[posmom].posY(), endX, endY, blu, grossezzaLinee);

                c.linea(x_tmp, 0, x_tmp, 1, viola, grossezzaLinee);
            }
            dormi((1000 / FPS) / vermi.length);

        }

        findGenitori();

        if (NGENERAZIONI > 0) {
            NGENERAZIONI--;
            nGEN++;
            start(xStart, yStart);
        }

    }

    private void dormi(int FPS) {
        try {
            Thread.sleep(FPS);
        } catch (InterruptedException ex) {
            Logger.getLogger(WormPath.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void findGenitori() {
        int posMadre = 0, posPadre = 1;
        double minDistance = 1;

        System.out.println(endX);

        for (int i = 0; i < vermi.length; i++) {
            double d = distanza(vermi[i].posX(), vermi[i].posY(), endX, endY);
            if (d < minDistance) {
                minDistance = d;
                posMadre = i;
            }
        }

        minDistance = 1;

        for (int i = 0; i < vermi.length; i++) {
            if (i != posMadre) {
                double d = distanza(vermi[i].posX(), vermi[i].posY(), endX, endY);
                if (d < minDistance) {
                    minDistance = d;
                    posPadre = i;
                }
            }
        }

        Verme tmpM = vermi[0];
        Verme tmpP = vermi[1];

        vermi[0] = vermi[posMadre];
        vermi[1] = vermi[posPadre];

        vermi[posMadre] = tmpM;
        vermi[posPadre] = tmpP;

        vermi[posMadre].setColore(Color.blue);
        vermi[posPadre].setColore(Color.blue);

        vermi[0].setColore(rosso);
        vermi[1].setColore(rosso);

        if (vermi[posMadre].posX() > x_tmp) {
            x_tmp = vermi[posMadre].posX();
            y_tmp = vermi[posMadre].posY();
            best = posMadre;
        }

    }
    
    int best = 0;
    double x_tmp = 0, y_tmp = 0;

    private double distanza(double posX, double posY, double endX, double endY) {
        return Math.sqrt(((posX - endX) * (posX - endX)) + ((posY - endY) * (posY - endY)));
    }

    private int getMostVicino() {
        int posMadre = 0;
        double minDistance = 1;
        int i;
        for (i = 0; i < vermi.length; i++) {
            double d = distanza(vermi[i].posX(), vermi[i].posY(), endX, endY);
            if (d < minDistance) {
                minDistance = d;
                posMadre = i;
            }
        }

        if (vermi[posMadre].posX() > x_tmp) {
            x_tmp = vermi[posMadre].posX();
            y_tmp = vermi[posMadre].posY();
        }

        return posMadre;
    }

}
