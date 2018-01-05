package grafica;

import java.awt.Color;

public class Poligono {

    private Color colore;
    protected double[] x, y, z;
    protected boolean daDisegnare = true, vedereAttraverso = false;
    private double[] calcPos, newX, newY;
    protected PolygonObject poligonoDisegnabile;
    protected double AvgDist;

    /**
     * Costruttore che delinea i vertici del poligono , il colore e la trasparenza.
     * @param x Vector n di vertici tenendo conto i punti x
     * @param y Vector n di vertici tenendo conto i punti y
     * @param z Vector n di vertici tenendo conto i punti z
     * @param colore Il Colore
     * @param vediAttraverso possibilita di vedere attraverso ( acqua vetro ecc)
     */
    public Poligono(double[] x, double[] y, double[] z, Color colore, boolean vediAttraverso) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.colore = colore;
        this.vedereAttraverso = vediAttraverso;
        createPolygon();
    }

    void createPolygon() {
        poligonoDisegnabile = new PolygonObject(new double[x.length], new double[x.length], colore, Schermo.poligoni3d.size(), vedereAttraverso);
    }

    void updatePolygon() {
        newX = new double[x.length];
        newY = new double[x.length];
        daDisegnare = true;
        for (int i = 0; i < x.length; i++) {
            calcPos = Calculator.CalculatePositionP(Schermo.ViewFrom, Schermo.ViewTo, x[i], y[i], z[i]);
            newX[i] = (Main.getRisoluzione().getWidth() / 2 - Calculator.CalcFocusPos[0]) + calcPos[0] * Schermo.zoom;
            newY[i] = (Main.getRisoluzione().getHeight() / 2 - Calculator.CalcFocusPos[1]) + calcPos[1] * Schermo.zoom;
            if (Calculator.t < 0) {
                daDisegnare = false;
            }
        }

        calcLighting();

        poligonoDisegnabile.draw = daDisegnare;
        poligonoDisegnabile.updatePolygon(newX, newY);
        AvgDist = GetDist();
    }

    void calcLighting() {
        Piano lightingPlane = new Piano(this);
        double angle = Math.acos(((lightingPlane.NV.x * Schermo.direzioneLuce[0])
                + (lightingPlane.NV.y * Schermo.direzioneLuce[1]) + (lightingPlane.NV.z * Schermo.direzioneLuce[2]))
                / (Math.sqrt(Schermo.direzioneLuce[0] * Schermo.direzioneLuce[0] + Schermo.direzioneLuce[1] * Schermo.direzioneLuce[1] + Schermo.direzioneLuce[2] * Schermo.direzioneLuce[2])));

        poligonoDisegnabile.lighting = 0.2 + 1 - Math.sqrt(Math.toDegrees(angle) / 180);

        if (poligonoDisegnabile.lighting > 1) {
            poligonoDisegnabile.lighting = 1;
        }
        if (poligonoDisegnabile.lighting < 0) {
            poligonoDisegnabile.lighting = 0;
        }
    }

    double GetDist() {
        double total = 0;
        for (int i = 0; i < x.length; i++) {
            total += GetDistanceToP(i);
        }
        return total / x.length;
    }

    double GetDistanceToP(int i) {
        return Math.sqrt((Schermo.ViewFrom[0] - x[i]) * (Schermo.ViewFrom[0] - x[i])
                + (Schermo.ViewFrom[1] - y[i]) * (Schermo.ViewFrom[1] - y[i])
                + (Schermo.ViewFrom[2] - z[i]) * (Schermo.ViewFrom[2] - z[i]));
    }
}
