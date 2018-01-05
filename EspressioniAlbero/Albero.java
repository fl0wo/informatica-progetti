package alberoespressioni;

import java.util.ArrayList;


/**
 *
 * @author florian.sabani
 */
class Albero {

    Nodo radice;
    
    
    public Albero() {
    }

    double getRis() {
        return vis(radice,0,0,0,' ');
    }

    private double vis(Nodo p,ArrayList<Double> numeri,ArrayList<Double> operatori){
        Item c = (Item) p.info;
        if (p.sx != null) {
            if(c.tipo == Item.OPERATORE)
                operatori.add(c.valore);
            vis(p.sx, numeri,operatori);
        }else{
            if(c.tipo == Item.NUMERO)
                numeri.add(c.valore);
        }
        
        
        return 0;
    }
    
    private double vis(Nodo p, double risultato,double n1,double n2,double operatore) {
        return risultato;
    }
}

class Nodo{

    Item info;
    Nodo dx;
    Nodo sx;

    public Nodo(Item valore) {
        this.info = valore;
    }
    
    Item getInfo(){
        return info;
    }
}
