package pizzeria;

import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Florian
 */
public class Verifica {
    public static void main(String[] args) {
        Pizzeria p = new Pizzeria(10);
        new Thread(p).start();
    }
    
}
class Pizzeria implements Runnable{
    final int tavoli;
    final int simulSec = 60,personeMax = 12;
    final int min = 5,max = 7;
    static Semaphore sem;
    
    final String[] nomi = {"Pino","Pippo","GianLuigi","Luca","Andrea","Marco"};
    
    Random r;
    
    public Pizzeria(int tavoli) {
        this.tavoli = tavoli;
        sem = new Semaphore(tavoli);
        r = new Random();
    }

    @Override
    public void run() {
        int tempoTrascorso = 0;
        int personeRandom = 0;
        int attesaOgniRound = 2;
        int personeSoddisfatte = 0;
        
        while((personeSoddisfatte <= personeMax) && (tempoTrascorso<simulSec)) {
            personeRandom = r.nextInt(personeMax-personeSoddisfatte);
            personeSoddisfatte+=personeRandom;
            for (int j = 0; j < personeRandom; j++) {
                int tempo = min+r.nextInt(max-min);
                new Thread(new Cliente(tempo,nomi[r.nextInt(nomi.length)]+j)).start();
            }
            
            try {Thread.sleep(attesaOgniRound*1000);} catch (InterruptedException ex) {}
            tempoTrascorso+=attesaOgniRound;
        }
        
    }
    
}

class Cliente implements Runnable{
    final int secondi;
    String nome;

    public Cliente(int secondi, String nome) {
        this.secondi = secondi;
        this.nome = nome;
    }

    @Override
    public void run() {
        try {
            Pizzeria.sem.acquire();
            Thread.sleep(secondi*1000);
            System.out.println("Io "+nome+" ho mangiato la pizza per "+secondi+" secondi :D");
        } catch (InterruptedException ex) {}
        finally{
            Pizzeria.sem.release();
        }
    }
    
}