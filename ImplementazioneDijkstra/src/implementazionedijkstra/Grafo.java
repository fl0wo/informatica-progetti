package implementazionedijkstra;

import java.util.ArrayList;

/**
 *
 * @author Florian
 */
public class Grafo{

    protected int __nodi_instradamento[];
    protected int __peso_instradamento[];
    protected char __nodi[];
    
    protected ArrayList<pair<Integer,Integer>> __adiacenza[];
    
    protected Grafo(int N) {
        __nodi = new char[N];
        __nodi_instradamento = new int[N];
        __peso_instradamento = new int[N];
        
        __adiacenza = new ArrayList[N];
        
        for (int i = 0; i < N; i++) {
            __adiacenza[i] = new ArrayList<>();
        }
    }

    @Override
    public String toString() {
        String out = "";
        
        for (int i = 0; i < __nodi.length; i++) {
            
            out += "\t" + __nodi[i] + " N_ADJ : "+ __adiacenza[i].size()  +"\r\n";
            
            for (int j = 0; j < __adiacenza[i].size(); j++) {
                out += " to : " + __nodi[__adiacenza[i].get(j).I] + " peso : " + __adiacenza[i].get(j).P  + " , ";
            }
            
            out += "\r\n";
        }
        
        return out;
    }
    
    
    
}

class pair<K,P>{
    
    int I,P;

    public pair(int I, int P) {
        this.I = I;
        this.P = P;
    }
}