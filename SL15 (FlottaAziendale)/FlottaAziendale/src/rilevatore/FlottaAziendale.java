package rilevatore;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
public class FlottaAziendale{
    private static ArrayList<String> carBrands = new ArrayList();
    public static PacchettoVeicoloRilevato newVehicle(){
        loadCarBrand();
        String targa = newLicensePlate();
        String marca = carBrands.get(new Random().nextInt(carBrands.size()));
        Veicolo temp = new Veicolo(targa, marca);
        temp.start();
        return new PacchettoVeicoloRilevato(targa, marca);
    }
    private static String newLicensePlate(){
	Random random = new Random();
	return "" + rndChar() + rndChar() + random.nextInt(9) + random.nextInt(9) + 
                random.nextInt(9) + rndChar() + rndChar();
    }
    private static char rndChar(){
        final String alphabet = "ABCDEFGHIJLKLMNOPQRSTUVWXYZ";
        Random randrom = new Random();
        return alphabet.charAt(randrom.nextInt(alphabet.length()));
    }
    private static void loadCarBrand(){
        Scanner scanner = null;
        File file = new File("MarcheAuto.csv");
        try{
            scanner = new Scanner(file);
        }catch(FileNotFoundException ex){
            ex.printStackTrace();
        }
        while(scanner.hasNextLine())
            carBrands.add(scanner.nextLine());
    }
}