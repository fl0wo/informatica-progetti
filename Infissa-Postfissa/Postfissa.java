package postfissa;
/**
 *
 * @author Florian
 */
public class Postfissa {
    public static void main(String[] args) {
        RPN r = new RPN("((10+2)-(4-1))*2");
        System.out.println(r.toString());
        System.out.println("Risultato ==" +r.calcola());
    }
    
}
