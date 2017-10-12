package postfissa;
/**
 *
 * @author Florian
 */
public class Postfissa {
    public static void main(String[] args) {
        RPN r = new RPN("((10+2)*(7-5)-(3+1)");
        System.out.println(r.toString());
        System.out.println(r.calcola());
    }
    
}
