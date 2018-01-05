package grafica;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;

public class PolygonObject {

    Polygon P;
    Color c;
    boolean draw = true, visible = true, seeThrough;
    double lighting = 1;

    public PolygonObject(double[] x, double[] y, Color c, int n, boolean seeThrough) {
        P = new Polygon();
        for (int i = 0; i < x.length; i++) {
            P.addPoint((int) x[i], (int) y[i]);
        }
        this.c = c;
        this.seeThrough = seeThrough;
    }

    void updatePolygon(double[] x, double[] y) {
        P.reset();
        for (int i = 0; i < x.length; i++) {
            P.xpoints[i] = (int) x[i];
            P.ypoints[i] = (int) y[i];
            P.npoints = x.length;
        }
    }

    void drawPolygon(Graphics g) {
        if (draw && visible) {
            g.setColor(new Color((int) (c.getRed() * lighting), (int) (c.getGreen() * lighting), (int) (c.getBlue() * lighting)));
            if (seeThrough) {
                g.drawPolygon(P);
            } else {
                g.fillPolygon(P);
            }
            if (Schermo.contorno) {
                g.setColor(new Color(0, 0, 0));
                g.drawPolygon(P);
            }

            if (Schermo.poligonoPuntato == this) {
                g.setColor(new Color(255, 255, 255, 100));
                g.fillPolygon(P);
            }
        }
    }

    protected boolean haMouseSopra() {
        return P.contains(Main.getRisoluzione().getWidth() / 2, Main.getRisoluzione().getHeight() / 2);
    }
}
