/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package studenti4icabr;

import java.awt.Point;

/**
 *
 * @author Florian
 */
public class Nodo {

    private MioOggetto value;
    private Nodo sinistra, destra;
    private Nodo padre;
    private Point punto;

    public Nodo(MioOggetto[] lista, int inizio, int fine,Nodo padre) {
        int medio = (inizio + fine) / 2;

        this.setPadre(padre);
        this.setValue(lista[medio]);
        
        if (inizio < medio - 1) {
            this.sinistra = new Nodo(lista, inizio, medio,this);
        }
        if (medio + 1 < fine) {
            this.destra = new Nodo(lista, medio, fine,this); // verso destra
        }
    }

    public Nodo(MioOggetto valore) {
        value = valore;
        sinistra = destra = null;
    }

    public void setPadre(Nodo padre) {
        this.padre = padre;
    }

    public Nodo getPadre() {
        return padre;
    }

    public Nodo getSinistra() {
        return sinistra;
    }

    public Nodo getDestra() {
        return destra;
    }

    public void setSinistra(Nodo sinistra) {
        this.sinistra = sinistra;
    }

    public void setDestra(Nodo destra) {
        this.destra = destra;
    }

    public MioOggetto getValue() {
        return value;
    }

    public String getInfo() {
        return this.getValue().toString();
    }

    public boolean isFoglia() {
        return this.getSinistra() == null && this.getDestra() == null;
    }

    public void setValue(MioOggetto value) {
        this.value = value;
    }

    public void setPoint(Point point) {
        this.punto = point;
    }

    public Point getPunto() {
        return punto;
    }

}
