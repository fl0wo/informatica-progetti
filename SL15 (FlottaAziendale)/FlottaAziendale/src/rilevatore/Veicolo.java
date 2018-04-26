package rilevatore;
public class Veicolo extends Thread{
    String targa;
    String marca;
    private Runnable task;
    public Veicolo(String targa, String marca){
        task = new Rilevatore(this);
        this.targa = targa;
        this.marca = marca;
    }
    @Override
    public void run(){
	task.run();
    }
}