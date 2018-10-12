package geneticalgorithm;

public class GeneticAlgorithm {

	public static void main(String[] args) {
		
			double bordo = .2;
			
		WormPath wp = new WormPath(100,bordo,.5,1-bordo,.5);
		
		wp.start(bordo,.5);
	}
	
}
