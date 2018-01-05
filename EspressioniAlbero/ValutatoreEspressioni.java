package alberoespressioni;

import java.util.Scanner;

/**
 *
 * @author florian.sabani
 */
public class ValutatoreEspressioni {
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
                String espressione = "(3*x+5)*2*x-7";
                System.out.println("Espressione : "+espressione);
		Espressione e = new Espressione(espressione);
		e.stampaAlbero();
		
		int x = s.nextInt();
                
		e.setVar("x",x);
                
                e.stampaAlbero();
                
                System.out.println(e.getRis());
                
	}
}
