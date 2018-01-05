package alberoespressioni;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Florian
 */
class StampaAlbero {

    public static <T> void stampaNodo(Nodo radice) {
        int livello = StampaAlbero.livelloMassimo(radice);
        stampaNodoRic(Collections.singletonList(radice), 1, livello);
    }

    private static <T> void stampaNodoRic(List<Nodo> nodi, int livello, int livelloMassimo) {
        if (nodi.isEmpty() || StampaAlbero.haNodiVuoti(nodi)) {
            return;
        }

        int livelloAttuale = livelloMassimo - livello;
        int lineeCollegamento = (int) Math.pow(2, (Math.max(livelloAttuale - 1, 0)));
        int spaziIniziali = (int) Math.pow(2, (livelloAttuale)) - 1;
        int spaziNelMezzo = (int) Math.pow(2, (livelloAttuale + 1)) - 1;

        StampaAlbero.nSpazi(spaziIniziali);

        List<Nodo> nuoviNodi = new ArrayList<>();

        for (Nodo nodoCorrente : nodi) {
            if (nodoCorrente != null) {
                System.out.print(nodoCorrente.info);
                nuoviNodi.add(nodoCorrente.sx);
                nuoviNodi.add(nodoCorrente.dx);
            } else {
                nuoviNodi.add(null);
                nuoviNodi.add(null);
                System.out.print(" ");
            }

            StampaAlbero.nSpazi(spaziNelMezzo);
        }
        System.out.println("");

        for (int i = 1; i <= lineeCollegamento; i++) {
            for (int j = 0; j < nodi.size(); j++) {
                StampaAlbero.nSpazi(spaziIniziali - i);
                if (nodi.get(j) == null) {
                    StampaAlbero.nSpazi(lineeCollegamento + lineeCollegamento + i + 1);
                    continue;
                }

                if (nodi.get(j).sx != null) {
                    System.out.print("/");
                } else {
                    StampaAlbero.nSpazi(1);
                }

                StampaAlbero.nSpazi(i + i - 1);

                if (nodi.get(j).dx != null) {
                    System.out.print("\\");
                } else {
                    StampaAlbero.nSpazi(1);
                }

                StampaAlbero.nSpazi(lineeCollegamento + lineeCollegamento - i);
            }

            System.out.println("");
        }

        stampaNodoRic(nuoviNodi, livello + 1, livelloMassimo);
    }

    private static void nSpazi(int N) {
        for (int i = 0; i < N; i++) {
            System.out.print(" ");
        }
    }

    private static <T> int livelloMassimo(Nodo node) {
        if (node == null) {
            return 0;
        }
        return Math.max(StampaAlbero.livelloMassimo(node.sx), StampaAlbero.livelloMassimo(node.dx)) + 1;
    }

    private static <T> boolean haNodiVuoti(List<T> lista) {
        for (Object elemento : lista) {
            if (elemento != null) {
                return false;
            }
        }
        return true;
    }

}
