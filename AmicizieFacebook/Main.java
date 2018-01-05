package amiciziefacebook.src;

public class Main {

    public static void main(String args[]) {

        // Creo il network 
        FacebookGraph<Persona, Amicizia<Persona>> facebook;
        facebook = new FacebookGraph<Persona, Amicizia<Persona>>();

        // Creo un insieme di persone e amicizie
        Persona Florian = new Persona("Florian", "Sabani", "20/09/2009");
        Persona Pippo = new Persona("Pippo", "Goofy", "27/11/1990");
        Persona Pluto = new Persona("Pluto", "Mouse", "17/07/1985");
        Persona Topolino = new Persona("Topolino", "Mouse", "7/01/2000");
        Persona Paperino = new Persona("Paolino", "Paperino", "30/12/1998");
        Persona Minnie = new Persona("Minnie", "Mouse", "7/02/2002");
        Persona Vuoto = new Persona("Non", "Raggiungibile", "7/02/2004");

        facebook.addNode(Florian);
        facebook.addNode(Pippo);
        facebook.addNode(Pluto);
        facebook.addNode(Topolino);
        facebook.addNode(Paperino);
        facebook.addNode(Minnie);
        facebook.addNode(Vuoto);

        facebook.addEdge(Florian, Pippo);
        facebook.addEdge(Pippo, Pluto);
        facebook.addEdge(Pluto, Topolino);
        facebook.addEdge(Topolino, Paperino);

        facebook.addEdge(Pippo, Topolino);
        facebook.addEdge(Pluto, Florian);
        facebook.addEdge(Paperino, Pluto);
        facebook.addEdge(Minnie, Pluto);

        // Stampo il grafo
        System.out.println(facebook);

        // Test 1
        try {
            facebook.addNode(null);
            System.out.println("[Test-1] Fallito!");
        } catch (NullPointerException e) {
            System.out.println("[Test-1] Ok!");
        } catch (IllegalArgumentException e) {
            System.out.println("[Test-1] Fallito!");
        }

        // Test 2
        try {
            facebook.addEdge(null);
            System.out.println("[Test-2] Fallito!");
        } catch (NullPointerException e) {
            System.out.println("[Test-2] Ok!");
        } catch (IllegalArgumentException e) {
            System.out.println("[Test-2] Fallito!");
        }

        // Test 3
        try {
            facebook.addEdge(Pippo, new Persona("falsa", "errore", "01/01/1990"));
            System.out.println("[Test-3] Fallito!");
        } catch (NullPointerException e) {
            System.out.println("[Test-3] Fallito!");
        } catch (IllegalArgumentException e) {
            System.out.println("[Test-3] Ok!");
        }

        // Test 4
        try {
            facebook.removeNode(null);
            System.out.println("[Test-4] Fallito!");
        } catch (NullPointerException e) {
            System.out.println("[Test-4] Ok!");
        } catch (IllegalArgumentException e) {
            System.out.println("[Test-4] Fallito!");
        }

        // Test 5
        try {
            facebook.removeNode(new Persona("falsa", "errore", "01/01/1990"));
            System.out.println("[Test-5] Fallito!");
        } catch (NullPointerException e) {
            System.out.println("[Test-5] Fallito!");
        } catch (IllegalArgumentException e) {
            System.out.println("[Test-5] Ok!");
        }

        // Test 6
        try {
            facebook.removeEdge(null);
            System.out.println("[Test-6] Fallito!");
        } catch (NullPointerException e) {
            System.out.println("[Test-6] Ok!");
        } catch (IllegalArgumentException e) {
            System.out.println("[Test-6] Fallito!");
        }

        // Test 7
        try {
            facebook.removeEdge(Pippo, new Persona("Falsa", "Errore", "01/01/1990"));
            System.out.println("[Test-7] Fallito!");
        } catch (NullPointerException e) {
            System.out.println("[Test-7] Fallito!");
        } catch (IllegalArgumentException e) {
            System.out.println("[Test-7] Ok!");
        }

        // Stampo un po' di distanze
        System.out.println("Distanza tra Florian e Pippo: " + facebook.distance(Florian, Pippo));
        System.out.println("Distanza tra Florian e Paperino: " + facebook.distance(Florian, Paperino));
        System.out.println("Distanza tra Florian e Minnie: " + facebook.distance(Florian, Minnie));
        System.out.println("Distanza tra Florian e Vuoto: " + facebook.distance(Florian, Vuoto));

        // Test 8
        try {
            facebook.removeNode(Minnie);
            System.out.println("[Test-8] Ok!");
        } catch (NullPointerException | IllegalArgumentException e) {
            System.out.println("[Test-8] Fallito!");
        }
        // Stampo il diametro
        System.out.println("Diametro della rete: " + facebook.diameter());
    }

}
