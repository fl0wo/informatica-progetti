package ordinamentomultithreaditerativo;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.CountDownLatch;

/**
 * @author Florian Sabani
 */
public class OrdinamentoMultiThreadIterativo {

    public static void main(String[] args) throws InterruptedException {
        int[] array = arrayCasuale(1000, 10, 100);
        System.out.println(Arrays.toString(array));
        long t = System.currentTimeMillis();

        ordina(array, 2);

        t = System.currentTimeMillis() - t;
        System.out.println("Tempo : " + t + " ms");

        System.out.println(Arrays.toString(array));
    }

    private static int[] arrayCasuale(int nElem, int min, int max) {
        int[] a = new int[nElem];
        Random r = new Random();
        for (int i = 0; i < nElem; i++) {
            a[i] = min + r.nextInt(max - min);
        }
        return a;
    }

    private static void ordina(int[] array, int nThread) throws InterruptedException {
        CountDownLatch cd = new CountDownLatch(nThread);

        int threadMin = nThread / 2;
        int threadMax = nThread-threadMin;
        
        for (int j = 0; j < threadMin; j++) {
            new Thread(new Ordina(array, true, cd)).start(); // META THREAD VANNO A PRENDERSI I MIN
        }
        for (int j = 0; j < threadMax; j++) {
            new Thread(new Ordina(array, false, cd)).start(); // META THREAD VANNO A PRENDERSI I MAX
        }

        cd.await();
    }

}

class Ordina implements Runnable {

    CountDownLatch notifica;
    int[] array;
    boolean min;

    Ordina(int[] array, boolean chiDevoOrdinare, CountDownLatch cd) {
        this.array = array;
        this.min = chiDevoOrdinare;
        this.notifica = cd;
    }

    @Override
    public void run() {
        if (min) {
            ordinaMin();
        } else {
            ordinaMax();
        }
    }

    private void ordinaMin() {
        
        int posMin;
        
        for (int i = 0; i < array.length/2; i++) {
            posMin = i;
            
            for (int j = i; j < array.length; j++) {  // Escludo quelli prima di i in quanto li ho gia ordinati
                if (array[j] < array[posMin]) {
                    posMin = j;
                }
            }
            swap(i, posMin);
        }
        System.out.println("IO HO ORDINATO I MIN");
        notifica.countDown();
    }

    private void ordinaMax() {
        int posMax;
        
        for (int i = 0; i < array.length/2; i++) {
            posMax = array.length-1-i;
            for (int j = 0; j < array.length-i; j++) {  // Escludo quelli prima di i in quanto li ho gia ordinati
                if (array[j] > array[posMax]) {
                    posMax = j;
                }
            }
            swap(array.length-1-i, posMax);
        }
        
        System.out.println("IO HO ORDINATO I MAX");
        notifica.countDown();
    }

    private void swap(int da, int a) {
        int tmp = array[da];
        array[da] = array[a];
        array[a] = tmp;
        
        /*
        ES SWAP SENZA VARIABILE TMP: 
            pos 2 : num 4
            e
            pos 5 : num 6
        
            pos2 = pos2+6 = 10
            pos5 = pos2-pos5 = 4
            pos 2 = pos2-pos5 = 6
        */
        /*
        array[da]+=array[a];
        array[a] = array[da]-array[a];
        array[da]-=array[a];
        */
    }

}
