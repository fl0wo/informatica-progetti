package mappa;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
public class Loader{
    public static ArrayList<PuntoMappa> getMappa(String fileName){
        ArrayList<PuntoMappa> mappa = new ArrayList();
        Scanner scanner = null;
        File file = new File(fileName);
        String[] dati;
        try{
            scanner = new Scanner(file);
        }catch(FileNotFoundException ex){
            ex.printStackTrace();
        }
        scanner.nextLine();
        while(scanner.hasNextLine()){
            dati = scanner.nextLine().split(",");
            mappa.add(new PuntoMappa(Integer.parseInt(dati[0]), dati[2], Double.parseDouble(dati[4]), Double.parseDouble(dati[5])));
        }
        return mappa;
    }
}