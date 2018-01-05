package factory;



import java.util.Random;

public class ShapeFactory {
    
    private static Random random = new Random();
	
   //use getShape method to get object of type shape 
   public Shape getShape(String shapeType){
      if(shapeType == null){
         return null;
      }		
      if(shapeType.equalsIgnoreCase("CIRCLE")){
         return new Circle();
         
      } else if(shapeType.equalsIgnoreCase("RECTANGLE")){
         return new Rectangle();
         
      } else if(shapeType.equalsIgnoreCase("SQUARE")){
         return new Square();
      }
      return null;
   }
   
    public Shape getRandomShape(){
        int n = random.nextInt();
        n = n > 0 ? n : -n; // ?
        switch(n%3) {
            case 0:
                return new Circle(); // you do not need break perche ce gia il return down
            case 1:
                return new Rectangle();
            case 2:
                return new Square();
        }
        return null;
    }
}
