package studenti4icabr;

import java.awt.Point;
import java.awt.event.ComponentListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 *
 * @author Florian
 */
public class EventiMouse implements MouseListener {

    private int lato;
    private AlberoBinario albero;
    private RappresentaAlbero schermo;

    public EventiMouse(AlberoBinario albero, int raggioNodo, RappresentaAlbero schermo) {
        this.lato = raggioNodo;
        this.albero = albero;
        this.schermo = schermo;
    }

    private void nodoCliccato(MouseEvent e) {
        Point posizioneMouse = e.getLocationOnScreen();     // Differenza fra pos locale o pos globale
        System.out.println(posizioneMouse);
        Nodo corrente = albero.getRadice();
        Point posizioneNodo = corrente.getPunto();

        while (distanza(posizioneMouse, posizioneNodo) < lato && corrente != null) {
            if (distanza(posizioneMouse, corrente.getDestra().getPunto()) < distanza(posizioneMouse, corrente.getSinistra().getPunto())) {
                corrente = corrente.getDestra();
            } else if (distanza(posizioneMouse, corrente.getSinistra().getPunto()) < distanza(posizioneMouse, corrente.getDestra().getPunto())) {
                corrente = corrente.getSinistra();
            }
            posizioneNodo = corrente.getPunto();
        }
        if (distanza(posizioneMouse, corrente.getPunto()) < lato) {
            this.albero.eliminaNodo(corrente.getValue());
        }

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println("ciao");
        nodoCliccato(e);
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    private int distanza(Point posizioneMouse, Point posizioneNodo) {
        int ipotenusa = (int) Math.sqrt((posizioneMouse.x * posizioneMouse.x) + (posizioneNodo.x * posizioneNodo.x));
        return ipotenusa;
    }

}
