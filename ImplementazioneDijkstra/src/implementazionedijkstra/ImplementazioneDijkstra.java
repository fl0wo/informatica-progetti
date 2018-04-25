package implementazionedijkstra;

/**
 *
 * @author Florian
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ImplementazioneDijkstra {

    private static void __input(Grafo __grafo, Scanner __scan){
        for (int i = 0; i < __grafo.__nodi.length; i++) {
            int N_ADJ = __scan.nextInt();
            __grafo.__nodi[i] = ((char)(i+'A'));
          
            
            for (int j = 0; j < N_ADJ; j++) {
                
                int A_CHI = __scan.nextInt();
                int PESO = __scan.nextInt();

                __grafo.__adiacenza[j].add(new pair(A_CHI,PESO));
            }
        }
    }
    
    public static void main(String[] args) throws FileNotFoundException {

        Scanner __scan = new Scanner(new File("graf_input.txt"/*System.in*/));
        
        int N = __scan.nextInt();
        
        Grafo __grafo = new Grafo(N);
        
        __input(__grafo,__scan);
        
        System.out.println("g : "+__grafo);
    }

}
