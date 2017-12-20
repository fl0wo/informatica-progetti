# ORDINAMENTO DI UN ARRAY MULTYTHREADING

Un'algoritmo iterativo che ordina un array sfruttando i thread... 

## Complessità ? 

Scorro metà array, (per ogni thread) , ogni ciclo di ogni elemento della metà di array presa, scorro tutto l'array , 
tranne gli elementi gia visitati.

Quindi complessita :
Caso migliore <--> Caso Medio 
Caso Medio <--> Caso Peggiore
Caso Peggiore : O( (n/2)^(n-i) )

Dove n sono i numero di elementi, e i é il numero di elementi già visitati...

### Come Funziona ? 

L'array intero va affidato a due thread, ogni thread ha un task diverso , il primo cerca i minimi e li mette in posizione i ,
l'altro task consiste nel trovare i massimi e petterli in posizione j.

i e j sono rispettivamente inizialmente 0 e la lunghezza dell array -1.

```
In questo caso i prenderebbe il valore 1 e j il valore 2
  
3 1 8 5 7 3 6
  ^ ^
  | |
  i j
  | |
  V V
0 1 2 3 4 5 6
```

### MultiTask ? MultiThread ? Concorrente ?

E' multithread ? Si , in quanto dal main vengono "forkati" due thread , con due compiti diversi 
quindi é anche MultiTask...

Non si può raggiungere ad una conclusione cosi affrettata anche per la concorrenza di questo programma.
In quanto é vero che ogni thread ha la stessa risorsa in mano ( l'array )  , ma é anche vero che non succederà mai
che il thread col compito dei minimi , trovi e si prenda un elemento , che coincide col massimo trovato dal thread dei massimi...
Quindi ogni thread si prende il proprio elemento dell 'array e lo swappa evitando compromessi, a meno che....
L'elemento che andiamo ad analizzare sia l'ultimo , e N numero di elementi sia dispari , in tal caso succede che il minimo coincide
col massimo e i due thread prendono in mano l'elemento della stessa posizione dell'array e lo swappano nella stessa posizione nella quale
si trova , in quanto é appunto l'ultimo elemento trovato , ossia quello nel mezzo.

## Run

Avviandolo possiamo anche notare il tempo che ci mette all'esecuzione.

### Output

Vediamo l'array prima e dopo l'ordinamento

```
1 22 54 12 6 34 7 3
1 3 6 7 12 22 34 54
```

### CountDownLatch 

Nel codice d'esempio viene utilizzato un CountDownLatch  per aspettare che entrambi i thread finiscano l'esecuzione.

## Autori

* **Sabani Florian** - *Progetto Introduttivo* - [fl0wo](https://github.com/fl0wo)

## Suggerimenti

* Aumentare il numero di thread oltre a 2 , peggiora le prestazioni in ogni caso
* Ho avuto l'ispirazione dall'esercizio visto in classe find MAX multithreading...
* basta tutto qua...
