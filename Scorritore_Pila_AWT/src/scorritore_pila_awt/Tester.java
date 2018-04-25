package scorritore_pila_awt;
public class Tester{
    public static void main(String[] args){
	Pila modello = new Pila();
        modello.push(1);
        modello.push(2);
        modello.push(3);
        modello.push(4);
        modello.push(5);
	FrameScorritorePila f = new FrameScorritorePila("Scorritore_Pila", modello);
	f.setVisible(true);
    }
}