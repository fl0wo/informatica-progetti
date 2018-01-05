# AMICIZIE DI FACEBOOK

Un programma che simula le relazioni tra piu persone (relazioni di amicizia) con un Grafo Direzionato di amicizie.

![alt text](https://github.com/fl0wo/informatica-progetti/blob/master/AmicizieFacebook/foto_esempio.png)


### Classi :

class FacebookGraph <E, N extends Amicizia<E>> implements Graph<E, Amicizia<E>> : 

                addNode(E toAdd) /* Aggiunge il nodo toAdd ai vertici*/
                addEdge(Amicizia<E> toAdd) /* Aggiunge l'arco toAdd ai link*/
                public void addEdge(E v1, E v2) /* Aggiunge l'arco tra v1 e v2*/
                public void removeNode(E toRemove) /* Rimuove, se presente, il nodo toRemove e i suoi link incidenti*/
                public void removeEdge(Amicizia<E> toRemove) /* Rimuove, se presente, l'arco toRemove dall'insieme Nodes */
                public void removeEdge(E v1, E v2) /*Crea l'arco tra V1 e V2 e lo rimuove*/
                public String toString() /*Stampa il contenuto del grafo */
                
class Amicizia<E> implements Edge<E> :
                
                public boolean isDirect() /*Restituisce false, visto che non è orientato*/
                public boolean connect(E connected) /*Restituisce true se connected è uno dei vertici*/
                public E getFrom()  /*Restituisce il primo nodo*/
                public E getTo()  /*Restituisce il secondo nodo*/
                public Number getWeight()  /*Restituisce 1, visto che non è pesato*/
                public String toString() /*Restituisce una stringa corrispondente all'oggetto*/
                public boolean equals(Object obj) /*Restituisce il confronto con un altro oggetto*/
                
public class Persona : 

               public Persona(String nome, String cognome, String data) {
                    this.nome = nome;
                    this.cognome = cognome;
                    this.data = data;
                }
                
                getNome(),getCognome(),getData() : +
                
### Interfacce :

        public interface Graph<E, N extends Edge<E>> 
        
              public void addNode(E toAdd); /* Aggiunge il nodo toAdd ai vertici*/
              public void addEdge(N toAdd); /* Aggiunge l'arco toAdd ai link*/
              public void removeNode(E toRemove);/* Rimuove, se presente, il nodo toRemove e i suoi link incidenti*/
              public void removeEdge(N toRemove);/* Rimuove, se presente, l'arco toRemove dall'insieme Nodes */

        public interface Edge<E> 

              public boolean isDirect();/* Controlla se l'arco è diretto */
              public boolean connect(E connected);/* Controlla l'arco ha il nodo connected come from o to */
              public E getFrom(); /* Restituisce il nodo in entrata */
              public E getTo();/* Restituisce il nodo in uscita*/
              public Number getWeight();/* Restituisce il peso corrispondente dell'arco  */
          
          
          
## Autori

* **Sabani Florian** - *Progetto Introduttivo* - [fl0wo](https://github.com/fl0wo)
