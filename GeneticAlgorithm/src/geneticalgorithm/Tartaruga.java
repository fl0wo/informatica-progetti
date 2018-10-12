package geneticalgorithm;

import java.awt.Color;

public class Tartaruga {
    double x;
    double y;
    double angolo;
    boolean penDown;
    Color colore;
    double grossezza;
    GraficaSemplice g ;
    public Tartaruga(GraficaSemplice g) {
		this.g = g;
        this.x = this.y = 0.5D;
        this.angolo = 90.0D;
        this.penDown = true;
        this.colore = g.NERO;
        this.grossezza = 0.002D;
    }

    public Tartaruga(double x0, double y0, double alfa, Color c) {
        this.x = x0;
        this.y = y0;
        this.angolo = alfa;
        this.penDown = true;
        this.colore = c;
        this.grossezza = 0.002D;
    }

    public void sinistra(double alfa) {
        this.angolo += alfa;
    }

    public void destra(double alfa) {
        this.angolo -= alfa;
    }

    public double getAngolo() {
        double x = Math.IEEEremainder(this.angolo, 360.0D);
        return x >= 0.0D ? Math.abs(x) : 360.0D + x;
    }

    public void setAngolo(double angolo) {
        this.angolo = angolo;
    }

    public void pennaGiu() {
        this.penDown = true;
    }

    public void pennaSu() {
        this.penDown = false;
    }

    public boolean isPennaSu() {
        return this.penDown;
    }

    public double getX() {
        return this.x;
    }

    public double getY() {
        return this.y;
    }

    public void avanti(double passo) {
        double xInizio = this.x;
        double yInizio = this.y;
        this.x += passo * Math.cos(Math.toRadians(this.angolo));
        this.y += passo * Math.sin(Math.toRadians(this.angolo));
        if (this.penDown) {
            g.linea(xInizio, yInizio, this.x, this.y, this.colore, this.grossezza);
        }
    }

    public void indietro(double passo) {
        this.avanti(-passo);
    }

    public void aspetta(int n) throws InterruptedException {
        Thread.sleep(n);
        /*this.ritardo = n;*/
    }

    public void gotoXY(double x, double y) {
        double oldx = this.x;
        double oldy = this.y;
        this.x = x;
        this.y = y;
        if (this.penDown) {
            g.linea(oldx, oldy, this.x, this.y);
        }

    }
    
    public void gotoXY(double x, double y,double grossezza) {
        double oldx = this.x;
        double oldy = this.y;
        this.x = x;
        this.y = y;
        if (this.penDown) {
            g.linea(oldx, oldy, this.x, this.y,this.colore,grossezza);
        }

    }

    public void setColore(Color c) {
        this.colore = c;
    }

    public Color getColore() {
        return this.colore;
    }

    public void setGrossezza(double gross) {
        this.grossezza = gross;
    }

    public double getGrossezza() {
        return this.grossezza;
    }
}
