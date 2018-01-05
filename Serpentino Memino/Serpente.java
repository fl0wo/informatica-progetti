package snakefatto;

/**
 *
 * @author Florian
 */
public class Serpente {

//pezzi
    private final int[] x = new int[Schermo.nPunti()];
    private final int[] y = new int[Schermo.nPunti()];

//direzioni
    private boolean spostaSinistra = false;
    private boolean spostaDestra = false;
    private boolean spostaSu = false;
    private boolean spostaGiu = false;

    private int pezzi = 0;

    public int getX(int index) {
        return x[index];
    }

    public int getY(int index) {
        return y[index];
    }

    public void x(int i) {
        x[0] = i;
    }

    public void y(int i) {
        y[0] = i;
    }

    public boolean isSx() {
        return spostaSinistra;
    }

    public void sx(boolean movingLeft) {
        this.spostaSinistra = movingLeft;
    }

    public boolean isDx() {
        return spostaDestra;
    }

    public void dx(boolean movingRight) {
        this.spostaDestra = movingRight;
    }

    public boolean isSu() {
        return spostaSu;
    }

    public void su(boolean movingUp) {
        this.spostaSu = movingUp;
    }

    public boolean isGiu() {
        return spostaGiu;
    }

    public void giu(boolean movingDown) {
        this.spostaGiu = movingDown;
    }

    public int getPezzi() {
        return pezzi;
    }

    public void setPezzi(int j) {
        pezzi = j;
    }

    public void muovi() {
        for (int i = pezzi; i > 0; i--) {
            x[i] = x[(i - 1)];
            y[i] = y[(i - 1)];
        }
        if (spostaSinistra) {
            x[0] -= Schermo.lunghezza();
        }
        if (spostaDestra) {
            x[0] += Schermo.lunghezza();
        }
        if (spostaGiu) {
            y[0] += Schermo.lunghezza();
        }
        if (spostaSu) {
            y[0] -= Schermo.lunghezza();
        }
    }
}
