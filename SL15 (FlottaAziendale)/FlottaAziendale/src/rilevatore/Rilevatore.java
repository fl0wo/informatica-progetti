package rilevatore;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
public class Rilevatore implements Runnable{
    private Veicolo veicolo;
    private int intervalloRilevazione;
    private static final int PORT = 5000;
    public Rilevatore(Veicolo veicolo){
	this.veicolo = veicolo;
	intervalloRilevazione = new Random().nextInt(20)+1;
    }
    @Override
    public void run(){
	DatagramSocket clientSocket = null;
	ByteArrayOutputStream baos = new ByteArrayOutputStream(6400);
	ObjectOutputStream oos = null;
        try{
            clientSocket = new DatagramSocket();
        }catch(SocketException ex){
            ex.printStackTrace();
        }
        while(true){
            try{
                baos = new ByteArrayOutputStream(6400);
                oos = new ObjectOutputStream(baos);
                TimeUnit.SECONDS.sleep(intervalloRilevazione);
            }catch(InterruptedException | IOException ex){
		ex.printStackTrace();
            }
            PacchettoVeicoloRilevato detectionPacket = new PacchettoVeicoloRilevato(veicolo.targa, 
                    veicolo.marca, ZonedDateTime.now().getDayOfMonth(), ZonedDateTime.now().getMonthValue(), 
                    ZonedDateTime.now().getYear(), LocalDateTime.now().getHour(), 
                    LocalDateTime.now().getMinute(), LocalDateTime.now().getSecond(), 
                    ThreadLocalRandom.current().nextDouble(45.05, 45.65),
                    ThreadLocalRandom.current().nextDouble(11.88, 12.38)
            );
            DatagramPacket packet = null;
            try{
                oos.writeObject(detectionPacket);
		byte[] data = baos.toByteArray();
		InetAddress receiverAddress = InetAddress.getLocalHost();
		packet = new DatagramPacket(data, data.length, receiverAddress, PORT);
		clientSocket.send(packet);
            }catch(IOException ex){
                ex.printStackTrace();
            }
        }
    }
}