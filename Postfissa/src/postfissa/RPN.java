package postfissa;

import java.util.Iterator;

/**
 *
 * @author Florian
 */
public class RPN {

    Lista<Item> lista;  // Lista Concatenata
    Lettore c = new Lettore();

    public RPN(String equazione) {
        lista = c.leggi(equazione);
    }

    public int calcola() {
        Pila<Integer> risposta = new Pila<>();
        Iterator<Item> it = lista.iterator();
        while (it.hasNext()) {
            Item elemento = it.next();
            if (elemento.isOperatore()) {
                risposta.push(risultato(risposta.pop(),risposta.pop(), elemento.getOperatore()));
            } else {
                risposta.push(elemento.getNumero());
            }
        }
        
        
        
        return risposta.pop();
    }

    @Override
    public String toString() {
        return "RPN{\n" + c.toString() + "\n}";
    }

    private Integer risultato(int n1, int n2, char operatore) {
        switch (operatore) {
            case '+':
                return n1 + n2;
            case '-':
                return n2 - n1;
            case '*':
                return n2 * n1;
            case '/':
                return n1 / n2;
            case '^':
                return (int) Math.round(Math.pow(n2, n1));
        }
        return -1;
    }

    class Item {

        private int numero;
        private char operatore;
        private boolean isOperatore = false;

        public Item(int numero) {
            this.numero = numero;
        }

        public Item(char operatore) {
            this.operatore = operatore;
            isOperatore = true;
        }

        public Item(Item i) {
            this.numero = i.getNumero();
            this.operatore = i.getOperatore();
            this.isOperatore = i.isOperatore;
        }

        public boolean isOperatore() {
            return isOperatore;
        }

        public int getNumero() {
            return numero;
        }

        public char getOperatore() {
            return operatore;
        }

    }

    class Lettore {

        String[] dizionario = {"+", "-", "*", "/", "P", "^", "S"};
        String[] parentesi = {"(", ")", "[", "]", "{", "}"};    // inutili per ora :P

        protected Lista<Item> leggi(String equazione) {
            Lista<Item> lista = new Lista<>();
            char pos, valoreSpostante = 0;
            equazione = rimuoviParentesi(equazione);    // per ora
            boolean medio = false;
            boolean aggiungi = false;
            for (int i = 0; i < equazione.length(); i++) {
                pos = equazione.charAt(i);
                System.out.println(pos);
                if (isOperatore(pos)) {
                    if (medio) {
                        medio = false;
                        aggiungi = true;
                        valoreSpostante = pos;
                        continue;
                    }

                    String nSinistra = "", nDestra = "";
                    //if(i==equazione.length()) return null;
                    char posSinistra = equazione.charAt(i - 1), posDestra = equazione.charAt(i + 1);
                    for (int j = i - 1; isNumero(posSinistra) && j >= 0; j--) {
                        posSinistra = equazione.charAt(j);
                        if (isNumero(posSinistra)) {
                            nSinistra += posSinistra;
                        } else {
                            //errore
                            //return null;
                        }
                    }

                    nSinistra = inverti(nSinistra);

                    for (int j = i + 1; isNumero(posDestra) && j < equazione.length(); j++) {
                        posDestra = equazione.charAt(j);
                        if (isNumero(posDestra)) {
                            nDestra += posDestra;
                        }
                    }

                    /*
                    System.out.println("<-" + nSinistra);
                    System.out.println("." + pos);
                    System.out.println("->" + nDestra);
                     */
                    int n1 = Integer.parseInt(nSinistra);
                    int n2 = Integer.parseInt(nDestra);
                    char operatore = pos;
                    lista.addTail(new Item(n1));
                    lista.addTail(new Item(n2));
                    lista.addTail(new Item(operatore));
                    if (aggiungi) {
                        lista.addTail(new Item(valoreSpostante));
                        aggiungi = false;
                    }
                    medio = !medio;
                }
                //niente...
            }

            return lista;
        }

        private boolean isNumero(char n) {
            int valore = (int) n;
            return valore > 47 && valore < 58;
        }

        private boolean isOperatore(char pos) {
            for (int i = 0; i < dizionario.length; i++) {
                if (pos == dizionario[i].charAt(0)) {
                    return true;
                }
            }
            return false;
        }

        private String rimuoviParentesi(String equazione) {
            String senzaParentesi = "";
            for (int i = 0; i < equazione.length(); i++) {
                if (!isParentesi(equazione.charAt(i))) {
                    senzaParentesi += equazione.charAt(i);
                }
            }
            return senzaParentesi;
        }

        private boolean isParentesi(char charAt) {
            for (int i = 0; i < parentesi.length; i++) {
                if (charAt == parentesi[i].charAt(0)) {
                    return true;
                }
            }
            return false;
        }

        private String inverti(String nSinistra) {
            String invertita = "";
            for (int i = nSinistra.length() - 1; i >= 0; i--) {
                invertita += nSinistra.charAt(i);
            }
            return invertita;
        }

        @Override
        public String toString() {
            Iterator<Item> it = lista.iterator();
            String costrutta = "";
            while (it.hasNext()) {
                Item elLista = it.next();
                if (!elLista.isOperatore()) {
                    costrutta += "Numero : " + elLista.getNumero() + "\n";
                } else {
                    costrutta += "Operatore : " + elLista.getOperatore() + "\n";
                }
            }
            return "Lettore{\n" + costrutta + "  }";
        }

    }
}
