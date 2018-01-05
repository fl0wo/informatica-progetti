package amiciziefacebook.src;

public interface Graph<E, N extends Edge<E>> {

    /* Aggiunge il nodo toAdd ai vertici*/
    public void addNode(E toAdd);

    /* Aggiunge l'arco toAdd ai link*/
    public void addEdge(N toAdd);

    /* Rimuove, se presente, il nodo toRemove e i suoi link incidenti*/
    public void removeNode(E toRemove);

    /* Rimuove, se presente, l'arco toRemove dall'insieme Nodes */
    public void removeEdge(N toRemove);
}
