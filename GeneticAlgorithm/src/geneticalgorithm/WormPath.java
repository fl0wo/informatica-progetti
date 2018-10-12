package geneticalgorithm;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author florian.sabani
 */
public class WormPath {
	
	private Verme[] vermi;
	
	private final int maxPassi = 10;
	private int NGENERAZIONI = 50;
	private GraficaSemplice c;
	
	WormPath(int l,double xStart,double yStart,double xEnd,double yEnd) {
		vermi = new Verme[l];
		Random r = new Random();
		
		c = new GraficaSemplice();
		
		for (int i = 0; i < vermi.length; i++) {
			vermi[i] = new Verme(c,c.coloreACaso(),r,maxPassi);
			vermi[i].setStart(xStart,yStart);
		}
		
	}

	void start(double xStart,double yStart) {
		for (int i = 0; i < vermi.length; i++) {
			vermi[i].reset();
		}
		
		final int FPS = 60;
		
		for (int j = 0; j < maxPassi; j++) {
			for (int i = 0; i < vermi.length; i++) {
				vermi[i].nextPasso();
			}
			dormi(FPS);
			c.pulisci();
		}
		
		if(NGENERAZIONI>0) {
			NGENERAZIONI--;
			start(xStart,yStart);
		}
		
		
	}

	private void dormi(int FPS) {
		try {
			Thread.sleep(1000/FPS);
		} catch (InterruptedException ex) {
			Logger.getLogger(WormPath.class.getName()).log(Level.SEVERE, null, ex);
		}

	}
	
}
