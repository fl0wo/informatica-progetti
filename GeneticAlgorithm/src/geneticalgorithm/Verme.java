package geneticalgorithm;

import java.awt.Color;
import java.util.Random;

public class Verme {

	private double passo = .05;
	private double incPasso = 0.00005;
	
	private final int nPassi = 1000;
	
	private double[] passi = new double[nPassi];
	private int[] rotazione = new int[nPassi];
	
	private int maxPassi;
	
	private Random rand;

	private int currentPasso = 0;
	private double xStart,yStart,x,y;
	
	private GraficaSemplice g;
	private Color colore;
	
	public Verme(GraficaSemplice g,Color colore,Random rand,int maxPassi){
		this.maxPassi = maxPassi;
		this.rand = rand;
		this.g = g;
		this.colore = colore;
	}

	void reset() {
		
		currentPasso = 0;
		x = xStart;
		y = yStart;
		int rot_cur = 0;
		
		for (int i = 0; i < nPassi; i++) {
			passi[i] = passo;
			rotazione[i] = rot_cur + (-60+(rand.nextInt(120)));

			rot_cur = rotazione[i];
		}
	}

	void setStart(double xStart, double yStart) {
		this.xStart = xStart;
		this.yStart = yStart;
	}

	void nextPasso() {

		double newX = getX(this.rotazione[this.currentPasso],passo);
		double newY = getY(this.rotazione[this.currentPasso],passo);
		
		System.out.println(x);
		
		Tartaruga t = new Tartaruga(g);
		t.pennaSu();
		t.setGrossezza(0.013);
		t.gotoXY(x, y);
		t.pennaGiu();
		t.gotoXY(newX, newY);
		//t.setAngolo(this.rotazione[this.currentPasso]);
		//t.avanti(this.passi[currentPasso]);
		t.pennaSu();
		
		
			currentPasso++;
		//g.linea(x, y,newX,newY, colore, 0.013);
		
		x = newX;
		y = newY;
		
	}

	private double getX(double rotation, double step) {
		rotation = Math.toRadians(rotation);
		double endX  = x +((Math.cos(rotation)) * step);
		return endX;
	}

	private double getY(double d, double step) {
		//d = Math.toRadians(d);
		return y + ((Math.sin(d)) * step);
	}
	
}
