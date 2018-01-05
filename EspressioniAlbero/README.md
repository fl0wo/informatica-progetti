# ESPRESSIONI ALBERO

Un programma data una stringa (valida) di un'equazione infissa , ne stampa il risultato , sfruttando un albero binario.

![alt text](https://github.com/fl0wo/informatica-progetti/blob/master/EspressioniAlbero/espressioneAlbero.png)


### Classi :
    
    class Albero :
          
          double getRis() /* restituisce il risultato dell 'equazione */
          private double vis(Nodo p, double risultato,double n1,double n2,double operatore) /* visita l'albero */
          
          
    class Nodo : 
    
          Item getInfo() /* restituisce l'Item che può essere un numero come un operatore... */
    
          
    class Item : 

          final static short VARIABILE = -1;
          final static short OPERATORE = 0;
          final static short NUMERO = 1;
          final static double MOLTIPLICAZIONE = '*',
                  DIVISIONE = '/',
                  SOMMA = '+';
                  
          public double valore;
          short tipo;
                  
              /* bisogna definirne sia il tipo che ovviamente il valore*/
              public Item(double valore, short tipo);
              
              /* tenendo conto che Item potrebbe essere sia un numero che un operatore
              sta a toString decidere se castarlo in char o lasciarlo double mentre si stampa*/
              public String toString() 
              
           
     class class Pila<T> :
     
          //mia implementazione della classe Pila
          
          push(T x)
          T pop()
          T getFront()
          void visita()
          int getLunghezza()
          Pila<T> clona()
          void rovescia()
          
          
     class Espressione : 
          
          Albero valutatore = new Albero();
          Postfissa lettore = new Postfissa();
          String espressione;
     
          Espressione(String espressione) /*costruttore che chiama il metodo init*/
          
          init(String espressione)/* inizia a costruire l'albero*/
          
           /* dopo che in init l'espressione sia diventata una postfissa genero l'albero*/
          costruisciAlbero(String[] postfissa) 
          
          /* imposto la variabile x col valore i , returno true se trovo la variabile*/
          boolean setVar(String x, double i)
          
          /* stampo l'albero disegnandolo*/
          void stampaAlbero()
          
          /*ci dice se lettera é uno dei operatori previsti*/
          boolean isOperatore(String lettera)
          
          boolean isNumero(String letteraCorrente) /* true se letteraCorrente é un numero */
          
          
      class Postfissa : 
      
          String leggi(String input)/* converte da infissa a postfissa */
      
      class class StampaAlbero :
      
          public static <T> void stampaNodo(Nodo radice)/* stampa tutti i nodi seguenti*/
          
          private static <T> void stampaNodoRic(List<Nodo> nodi, int livello, int livelloMassimo)
          private static void nSpazi(int N)
          private static <T> int livelloMassimo(Nodo node)
          private static <T> boolean haNodiVuoti(List<T> lista)
          
      
## Autori

* **Sabani Florian** - *Progetto Introduttivo* - [fl0wo](https://github.com/fl0wo)
