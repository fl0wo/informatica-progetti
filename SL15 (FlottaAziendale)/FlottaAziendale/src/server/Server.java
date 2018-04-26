package Server;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.ArrayList;
import rilevatore.PacchettoVeicoloRilevato;
import mappa.*;
public class Server extends Thread{
    private static final int PORT = 5000;
    private static ArrayList<PuntoMappa> mappa;
    public ArrayList<PacchettoVeicoloRilevato> rilevazioni = new ArrayList();
    @Override
    public void run(){
        mappa = Loader.getMappa("Stops.csv");
        boolean received = false;
        DatagramSocket serverSocket;
        byte[] receiveData = new byte[1024];
        try{
            serverSocket = new DatagramSocket(PORT);
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            do{
                do{
                    try{
                        serverSocket.receive(receivePacket);
                        received = true;
                    }catch(java.net.SocketTimeoutException ex){}
                }while(!received);
                byte[] dataReceived = receivePacket.getData();
		ByteArrayInputStream arrayInputStream = new ByteArrayInputStream(dataReceived);
		ObjectInputStream objInputStream = new ObjectInputStream(arrayInputStream);
		PacchettoVeicoloRilevato rilevazione = null;
		try{
                    rilevazione = (PacchettoVeicoloRilevato)objInputStream.readObject();
		}catch(ClassNotFoundException ex){
		}
                rilevazioni.add(rilevazione);
                updateDetection(rilevazioni);
                received = false;
            }while(true);
        }catch(SocketException ex){
        }catch(IOException ex){
	}
    }
    
    public static PuntoMappa getPosizione(PacchettoVeicoloRilevato rilevazione){
        double lat = rilevazione.getLatitudine();
        double lon = rilevazione.getLongitudine();
        double distanzaLat, distanzaLon, distanzaMinore, distanzaConfronto;
        distanzaLat = Math.abs(lat - mappa.get(0).stop_lat);
        distanzaLon = Math.abs(lon - mappa.get(0).stop_lon);
        distanzaMinore = Math.sqrt(Math.pow(distanzaLat, 2) + Math.pow(distanzaLon, 2));
        PuntoMappa puntoMappa = mappa.get(0);
        for(int i=1;i<mappa.size();i++){
            distanzaLat = Math.abs(lat - mappa.get(i).stop_lat);
            distanzaLon = Math.abs(lon - mappa.get(i).stop_lon);
            distanzaConfronto = Math.sqrt(Math.pow(distanzaLat, 2) + Math.pow(distanzaLon, 2));
            if(distanzaMinore > distanzaConfronto){
                distanzaMinore = distanzaConfronto;
                puntoMappa = mappa.get(i);
            }
        }
        return puntoMappa;
    }
    
    private static void updateDetection(ArrayList<PacchettoVeicoloRilevato> rilevazioni){
        if(rilevazioni.size() != 1){
            String targa = rilevazioni.get(rilevazioni.size()-1).getTarga();
            for(int i=rilevazioni.size()-2;i>=0;i--){
                if(rilevazioni.get(i).getTarga().equals(targa)){
                    rilevazioni.remove(i);
                    if(i == 0)
                        return;
                }
            }
        }
    }
}