
package snakefatto;

import java.util.Random;

/**
 *
 * @author Florian
 */
public class Cibo {

    private Serpente serpente = new Serpente();
    private int posX;
    private int posY;

    private final int maxPos = 20;
    Random r = new Random();

    public void spawnaCibo() {
        posX = ((r.nextInt(maxPos) * Schermo.lunghezza()));
        posY = ((r.nextInt(maxPos) * Schermo.lunghezza()));

        if ((posX == serpente.getX(0)) && (posY == serpente.getY(0))) {
            spawnaCibo();
        }
    }

    public int ciboX() {

        return posX;
    }

    public int ciboY() {
        return posY;
    }
}
