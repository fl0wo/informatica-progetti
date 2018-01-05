package iteratorevsloop;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

/**
 *
 * @author Florian
 */
public class IteratoreVSLoop {
    public static void main(String[] args) {
       
   
        LinkedList<Integer> arrayList = new LinkedList<>();
        //listaLinkata.get(0);
        ArrayList<Integer> lorenzoV02 = new ArrayList<>();
        System.out.println("Riempiendo l'arrayList");
        
        long nElementi = 7000000;
        long t = System.currentTimeMillis();
        for (int i = 0; i < nElementi; i++) {
            arrayList.add(i);
        }
        System.out.println("Tempo : "+(System.currentTimeMillis()-t)+"ms");
        
        System.out.println("Array List : ");
        System.out.println("N ELEMENTI = "+nElementi);
        
        System.out.println("\n For Normale : for (int i = 0; i < arrayList.size(); i++,arrayList.get(i));");
        t = System.currentTimeMillis();
        for (int i = 0; i < arrayList.size()-1; i++)arrayList.get(i);
        System.out.println("Tempo : "+(System.currentTimeMillis()-t)+"ms");
        
        System.out.println("\n For Each Loop : for(Integer i : arrayList);");
        t = System.currentTimeMillis();
        for(Integer i : arrayList);
        System.out.println("Tempo : "+(System.currentTimeMillis()-t)+"ms");
        
        System.out.println("Iteratore : for (Iterator it =arrayList.iterator();it.hasNext();it.next());");
        
        t = System.currentTimeMillis();
        for (Iterator it =arrayList.iterator();it.hasNext();it.next());
        System.out.println("Tempo : "+(System.currentTimeMillis()-t)+"ms");
        
        System.out.println("Functional Operation Lambda: arrayList.forEach((_item) -> {});");
        t = System.currentTimeMillis();
        arrayList.forEach((_item) -> {});
        System.out.println("Tempo : "+(System.currentTimeMillis()-t)+"ms");
        
        
    }
    
}
