package grafica;

import grafica.Poligono;
import grafica.Schermo;
import java.awt.Color;

public class Cubo {

    protected double x, y, z, width, length, height, rotation = Math.PI * 0.75;
    private double[] RotAdd = new double[4];
    private Color c;
    private double x1, x2, x3, x4, y1, y2, y3, y4;
    private Poligono[] facce = new Poligono[6];
    private double[] angle;

    public Cubo(double x, double y, double z, double larghezza, double profondita, double altezza, Color colore) {
        
        facce[0] = new Poligono(new double[]{x, x + larghezza, x + larghezza, x}, new double[]{y, y, y + profondita, y + profondita}, new double[]{z, z, z, z}, colore, false);
        Schermo.poligoni3d.add(facce[0]);
        facce[1] = new Poligono(new double[]{x, x + larghezza, x + larghezza, x}, new double[]{y, y, y + profondita, y + profondita}, new double[]{z + altezza, z + altezza, z + altezza, z + altezza}, colore, false);
        Schermo.poligoni3d.add(facce[1]);
        facce[2] = new Poligono(new double[]{x, x, x + larghezza, x + larghezza}, new double[]{y, y, y, y}, new double[]{z, z + altezza, z + altezza, z}, colore, false);
        Schermo.poligoni3d.add(facce[2]);
        facce[3] = new Poligono(new double[]{x + larghezza, x + larghezza, x + larghezza, x + larghezza}, new double[]{y, y, y + profondita, y + profondita}, new double[]{z, z + altezza, z + altezza, z}, colore, false);
        Schermo.poligoni3d.add(facce[3]);
        facce[4] = new Poligono(new double[]{x, x, x + larghezza, x + larghezza}, new double[]{y + profondita, y + profondita, y + profondita, y + profondita}, new double[]{z, z + altezza, z + altezza, z}, colore, false);
        Schermo.poligoni3d.add(facce[4]);
        facce[5] = new Poligono(new double[]{x, x, x, x}, new double[]{y, y, y + profondita, y + profondita}, new double[]{z, z + altezza, z + altezza, z}, colore, false);
        Schermo.poligoni3d.add(facce[5]);

        this.c = colore;
        this.x = x;
        this.y = y;
        this.z = z;
        this.width = larghezza;
        this.length = profondita;
        this.height = altezza;

        setRotAdd();
        aggiornaPoligono();
    }

    private void setRotAdd() {
        angle = new double[4];

        double xdif = -width / 2 + 0.00001;
        double ydif = -length / 2 + 0.00001;

        angle[0] = Math.atan(ydif / xdif);

        if (xdif < 0) {
            angle[0] += Math.PI;
        }

/////////
        xdif = width / 2 + 0.00001;
        ydif = -length / 2 + 0.00001;

        angle[1] = Math.atan(ydif / xdif);

        if (xdif < 0) {
            angle[1] += Math.PI;
        }
/////////
        xdif = width / 2 + 0.00001;
        ydif = length / 2 + 0.00001;

        angle[2] = Math.atan(ydif / xdif);

        if (xdif < 0) {
            angle[2] += Math.PI;
        }

/////////
        xdif = -width / 2 + 0.00001;
        ydif = length / 2 + 0.00001;

        angle[3] = Math.atan(ydif / xdif);

        if (xdif < 0) {
            angle[3] += Math.PI;
        }

        RotAdd[0] = angle[0] + 0.25 * Math.PI;
        RotAdd[1] = angle[1] + 0.25 * Math.PI;
        RotAdd[2] = angle[2] + 0.25 * Math.PI;
        RotAdd[3] = angle[3] + 0.25 * Math.PI;

    }

    protected void aggiornaDirezione(double toX, double toY) {
        double xdif = toX - (x + width / 2) + 0.00001;
        double ydif = toY - (y + length / 2) + 0.00001;

        double anglet = Math.atan(ydif / xdif) + 0.75 * Math.PI;

        if (xdif < 0) {
            anglet += Math.PI;
        }

        rotation = anglet;
        aggiornaPoligono();
    }

    protected void aggiornaPoligono() {
        for (int i = 0; i < 6; i++) {
            Schermo.poligoni3d.add(facce[i]);
            Schermo.poligoni3d.remove(facce[i]);
        }

        double radius = Math.sqrt(width * width + length * length);

        x1 = x + width * 0.5 + radius * 0.5 * Math.cos(rotation + RotAdd[0]);
        x2 = x + width * 0.5 + radius * 0.5 * Math.cos(rotation + RotAdd[1]);
        x3 = x + width * 0.5 + radius * 0.5 * Math.cos(rotation + RotAdd[2]);
        x4 = x + width * 0.5 + radius * 0.5 * Math.cos(rotation + RotAdd[3]);

        y1 = y + length * 0.5 + radius * 0.5 * Math.sin(rotation + RotAdd[0]);
        y2 = y + length * 0.5 + radius * 0.5 * Math.sin(rotation + RotAdd[1]);
        y3 = y + length * 0.5 + radius * 0.5 * Math.sin(rotation + RotAdd[2]);
        y4 = y + length * 0.5 + radius * 0.5 * Math.sin(rotation + RotAdd[3]);

        facce[0].x = new double[]{x1, x2, x3, x4};
        facce[0].y = new double[]{y1, y2, y3, y4};
        facce[0].z = new double[]{z, z, z, z};

        facce[1].x = new double[]{x4, x3, x2, x1};
        facce[1].y = new double[]{y4, y3, y2, y1};
        facce[1].z = new double[]{z + height, z + height, z + height, z + height};

        facce[2].x = new double[]{x1, x1, x2, x2};
        facce[2].y = new double[]{y1, y1, y2, y2};
        facce[2].z = new double[]{z, z + height, z + height, z};

        facce[3].x = new double[]{x2, x2, x3, x3};
        facce[3].y = new double[]{y2, y2, y3, y3};
        facce[3].z = new double[]{z, z + height, z + height, z};

        facce[4].x = new double[]{x3, x3, x4, x4};
        facce[4].y = new double[]{y3, y3, y4, y4};
        facce[4].z = new double[]{z, z + height, z + height, z};

        facce[5].x = new double[]{x4, x4, x1, x1};
        facce[5].y = new double[]{y4, y4, y1, y1};
        facce[5].z = new double[]{z, z + height, z + height, z};

    }

    protected void removeCube() {
        for (int i = 0; i < 6; i++) {
            Schermo.poligoni3d.remove(facce[i]);
        }
        Schermo.cubi.remove(this);
    }
}
