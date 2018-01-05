# Adapter

Per illustrare tale **design pattern** patiamo da una figura

![adapter](https://obsoletedeveloper.files.wordpress.com/2012/09/hf-adapter.jpg)

Proponiamo in UML la situazione esemplificata qui sopra!

![adapetr img](http://www.dofactory.com/images/diagrams/net/adapter.gif)

... dovrebbe il tutto risultare comprensibile: l'adapter nasce dall'esigenza di aggiustare l'interfaccia (intendi metodi ed altro, non strettamente in senso Java) di una classe in modo da poterne utilizzare gli oggetti in un altro contesto. Nel noto testo di E.Gamma, R.Helm, R.Johnson e J.Vlissides, la "Banda dei 4", si definisce tale pattern come quel dispositivo che oermette di

>convertire l'interfaccia di una classe in un'altra interfaccia che il cliente si attende. Permette alle classi di lavorare assieme quando questo normalmente non potrebbe accadere.

Ecco un esempio: in Android abbiamo un array di stringhe e vogliamo visualizzare degli item in una `ListView` e gestire tali item come elementi di una GUI (cosa che di certo non si può fare utilizzando le stringhe).

Qui di seguto proponiamo un esempio tratto da un noto sito [link](https://www.tutorialspoint.com/design_pattern/adapter_pattern.htm) che propone tutorial, ecco l'UML delle classi

![uml adapter](https://www.tutorialspoint.com/design_pattern/images/adapter_pattern_uml_diagram.jpg)

Il materiale che offriamo quindi è usare seguendo l'uso delle licenze note.

