package geneticalgorithm;

import java.awt.Color;
import java.util.Arrays;
import java.util.Random;

public class Verme {

    private double passo = .025;
    private double incPasso = 0.005;

    private Tartaruga t;
    private final int nPassi = 1000;

    private double[] passi = new double[nPassi];
    private int[] rotazione = new int[nPassi];

    private int maxPassi;

    private Random rand;

    private int currentPasso = 0;
    private double xStart, yStart, x, y;

    private GraficaSemplice g;
    private Color colore;

    private Verme[] genitori = new Verme[2];

    private final int VAR_ROT_MAX = 90;

    private boolean first_GEN = true;

    public Verme(GraficaSemplice g, Color colore, Random rand, int maxPassi, Verme padre, Verme madre) {
        this.maxPassi = maxPassi;
        this.rand = rand;
        this.g = g;
        this.colore = colore;

        t = new Tartaruga(g);
        t.setColore(colore);

        genitori[0] = madre;
        genitori[1] = padre;
    }

    void setColore(Color colore) {
        t.setColore(colore);
    }

    public double posX() {
        return x;
    }

    public double posY() {
        return y;
    }

    void setPadre(Verme p) {
        genitori[1] = p;
    }

    void setMadre(Verme p) {
        genitori[0] = p;
    }

    void reset(Verme madre, Verme padre) {

        this.setMadre(madre);
        this.setPadre(padre);

        currentPasso = 0;
        x = xStart;
        y = yStart;

        if (padre == null || madre == null || first_GEN) {
            first_GEN = false;
            init();
        } else {
            adatt();
        }
    }
    
    void justReset() {
        currentPasso = 0;
        x = xStart;
        y = yStart;
    }

    private void init() {
        int rot_cur = 0;

        for (int i = 0; i < nPassi; i++) {
            passi[i] =  passo + (rand.nextDouble() * incPasso);
            rotazione[i] = rot_cur + (-(VAR_ROT_MAX) + (rand.nextInt(VAR_ROT_MAX * 2)));

            rot_cur = rotazione[i];
        }
    }

    private int RANDOM_ROT = 90;
    
    private void adatt() {
        //perc di adattamento della madre rispetto al padre 0-1
        double percAdat;
        int rot_cur = 0;
        RANDOM_ROT = Math.max(1, RANDOM_ROT-5);

        boolean seNeFrega = rand.nextDouble()<.2;
        
        for (int i = 0; i < nPassi; i++) {
            percAdat = rand.nextDouble();

            int minRot = Math.min(genitori[0].rotazione[i] % 360, genitori[1].rotazione[i] % 360);
            int maxRot = Math.max(genitori[0].rotazione[i] % 360, genitori[1].rotazione[i] % 360);

            rotazione[i] = ((minRot - RANDOM_ROT + rand.nextInt(RANDOM_ROT*2))) % 360;
            
            passi[i] = Math.min(genitori[0].passi[i],genitori[1].passi[i])+
                    (Math.abs(genitori[0].passi[i]-genitori[1].passi[i]) * rand.nextDouble());
            
            // se é un ribelle se ne frega
            if(seNeFrega)
                ;//rotazione[i] = rot_cur + (-(VAR_ROT_MAX) + (rand.nextInt(VAR_ROT_MAX * 2)));
            
            // se é un genitore non deve cambiare finche non diventa nonno
            for (int j = 0; j < 2; j++)
                if(this==genitori[j]){
                    rotazione[i] = genitori[j].rotazione[i];
                    passi[i] = genitori[j].passi[i];
                }
        }
        
    }

    void setStart(double xStart, double yStart) {
        this.xStart = xStart;
        this.yStart = yStart;
    }

    void nextPasso(double perc) {

        double newX = getX(this.rotazione[this.currentPasso], this.passi[currentPasso] * perc);
        double newY = getY(this.rotazione[this.currentPasso], this.passi[currentPasso] * perc);

        t.pennaSu();
        t.gotoXY(x, y);
        t.pennaGiu();
        t.gotoXY(newX, newY, 0.014);
        t.pennaSu();

        if (perc > 0.99) {
            currentPasso++;
            x = newX;
            y = newY;
        }

    }

    private double getX(double rotation, double step) {
        rotation = Math.toRadians(rotation);
        double endX = x + ((Math.cos(rotation)) * step);
        return endX;
    }

    private double getY(double d, double step) {
        //d = Math.toRadians(d);
        return y + ((Math.sin(d)) * step);
    }


}
