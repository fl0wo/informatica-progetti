package tabella;
import java.util.ArrayList;
public class Tabella{
    private ArrayList<Item> table;
    private int variable;
    public Tabella(){
        table = new ArrayList();
        variable = 0;
    }
    public void insert(int key, String value){
        table.add(new Item(key, value));
    }
    public String get(int key){
        for(int i=0;i<size();i++){
            if(table.get(i).getKey() == key)
                return i%2 == 0 ? table.get(i+1).getValue() : table.get(i-1).getValue();
        }
        return null;
    }
    public ArrayList<Item> getTable(){
        return table;
    }
    public int size(){
        return table.size();
    }
    public int hash(String key){
        int hash = 7;
        for(int i=0;i<key.length();i++)
            hash = hash*31 + key.charAt(i);
        return hash + variable++;
    }
}