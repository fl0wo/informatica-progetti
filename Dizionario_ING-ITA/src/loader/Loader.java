package loader;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
public class Loader{
    public static ArrayList<String> load(String nameFile) throws FileNotFoundException, IOException{
        ArrayList<String> dizionario = new ArrayList();
        try(BufferedReader input = new BufferedReader(new FileReader(nameFile))){
            String line = input.readLine();
            while(line != null){
                dizionario.add(line);
                line = input.readLine();
            }
        }
        return dizionario;
    }
}