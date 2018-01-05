package grafica;

import java.awt.Color;
import java.util.Random;

public class GeneraTerreno {

    private static double ruginezza = 1;
    protected static int grandezzaMappa = 50;
    static double grandezza = 2;
    //   static Color G = new Color(155, 155, 155); // Grigio assurdo
    static Color G = new Color(120, 100, 80);   // Marrone assurdo

    /**
     * Genera il terreno aggiungendolo all arraylist di poligoni
     */
    public GeneraTerreno() {
        Random r = new Random();
        
        double[] valori1 = new double[grandezzaMappa];
        double[] valori2 = new double[grandezzaMappa];

        // Genera poligono per poligono dei poligoni con lati adiacenti e li aggiunge all arrayList
        // DA OTTIMIZZARE !
        for (int y = 0; y < grandezzaMappa / 2; y += 2) {
            for (int i = 0; i < grandezzaMappa; i++) {
                valori1[i] = valori2[i];
                valori2[i] = r.nextDouble() * ruginezza;
            }

            if (y != 0) {
                for (int x = 0; x < grandezzaMappa / 2; x++) {
                    Schermo.poligoni3d.add(new Poligono(new double[]{(grandezza * x), (grandezza * x), grandezza + (grandezza * x)}, new double[]{(grandezza * y), grandezza + (grandezza * y), grandezza + (grandezza * y)}, new double[]{valori1[x], valori2[x], valori2[x + 1]}, G, false));
                    Schermo.poligoni3d.add(new Poligono(new double[]{(grandezza * x), grandezza + (grandezza * x), grandezza + (grandezza * x)}, new double[]{(grandezza * y), grandezza + (grandezza * y), (grandezza * y)}, new double[]{valori1[x], valori2[x + 1], valori1[x + 1]}, G, false));
                }
            }

            for (int i = 0; i < grandezzaMappa; i++) {
                valori1[i] = valori2[i];
                valori2[i] = r.nextDouble() * ruginezza;
            }

            if (y != 0) {
                for (int x = 0; x < grandezzaMappa / 2; x++) {
                    Schermo.poligoni3d.add(new Poligono(new double[]{(grandezza * x), (grandezza * x), grandezza + (grandezza * x)}, new double[]{(grandezza * (y + 1)), grandezza + (grandezza * (y + 1)), grandezza + (grandezza * (y + 1))}, new double[]{valori1[x], valori2[x], valori2[x + 1]}, G, false));
                    Schermo.poligoni3d.add(new Poligono(new double[]{(grandezza * x), grandezza + (grandezza * x), grandezza + (grandezza * x)}, new double[]{(grandezza * (y + 1)), grandezza + (grandezza * (y + 1)), (grandezza * (y + 1))}, new double[]{valori1[x], valori2[x + 1], valori1[x + 1]}, G, false));
                }
            }
        }
    }
    
    public int getTerreno(){
        return (int) grandezza;
    }
}
