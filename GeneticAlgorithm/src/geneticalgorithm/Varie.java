//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//
package geneticalgorithm;

import java.util.Random;
import java.util.Scanner;

public class Varie {
    static Scanner tastiera;
    static boolean convErr;
    static Random randomg;

    public Varie() {
    }

    public static void ritardo(int millis) {
        try {
            Thread.currentThread();
            Thread.sleep((long)millis);
        } catch (InterruptedException var2) {
            System.out.println("Si e' verificato un errore facendo attesa");
        }

    }

    public static int random(int a, int b) {
        return a + randomg.nextInt(b - a + 1);
    }

    public static char primoCar(String s) {
        if (s.length() == 0) {
            throw new IllegalArgumentException("primoCar proibito su stringa vuota");
        } else {
            return s.charAt(0);
        }
    }

    public static String senzaPrimo(String s) {
        if (s.length() == 0) {
            throw new IllegalArgumentException("senzaPrimo proibito su stringa vuota");
        } else {
            return s.substring(1);
        }
    }

    public static boolean isVuota(String s) {
        return s.length() == 0;
    }

    public static boolean rispettaRegex(String s, String regex) {
        return s.matches(regex);
    }

    public static int lunghezza(String s) {
        return s.length();
    }

    public static char selCar(String s, int i) {
        if (i >= 0 && i < s.length()) {
            return s.charAt(i);
        } else {
            throw new IllegalArgumentException("indice di carattere fuori dai limiti");
        }
    }

    public static String selString(String s, int i, int n) {
        if (i >= 0 && i < s.length()) {
            return s.substring(i, n);
        } else {
            throw new IllegalArgumentException("indice di carattere fuori dai limiti");
        }
    }

    public static char ultimoCar(String s) {
        if (s.length() == 0) {
            throw new IllegalArgumentException("ultimoCar proibito su stringa vuota");
        } else {
            return s.charAt(s.length() - 1);
        }
    }

    public static String senzaUltimo(String s) {
        if (s.length() == 0) {
            throw new RuntimeException("senzaUltimoCar proibito su stringa vuota");
        } else {
            return s.substring(0, s.length() - 1);
        }
    }

    public static boolean convOK() {
        return convErr;
    }

    public static int intConv(String s) {
        int ris = 0;
        convErr = false;

        try {
            ris = Integer.parseInt(s);
            convErr = true;
        } catch (NumberFormatException var3) {
            ;
        }

        return ris;
    }

    public static double doubleConv(String s) {
        double ris = 0.0D;
        convErr = false;

        try {
            ris = Double.parseDouble(s);
            convErr = true;
        } catch (NumberFormatException var4) {
            ;
        }

        return ris;
    }

    public static int askInt(String invito) {
        int ris = 0;
        boolean successo = false;

        do {
            System.out.print(invito);
            String s = tastiera.nextLine();

            try {
                ris = Integer.parseInt(s);
                successo = true;
            } catch (NumberFormatException var5) {
                System.out.println("Intero non valido. RIPROVA");
            }
        } while(!successo);

        return ris;
    }

    public static double askDouble(String invito) {
        double ris = 0.0D;
        boolean successo = false;

        do {
            System.out.print(invito);

            try {
                ris = tastiera.nextDouble();
                successo = true;
            } catch (Exception var5) {
                System.out.println("Double non valido. RIPROVA");
                tastiera.nextLine();
            }
        } while(!successo);

        return ris;
    }

    public static String askString(String invito) {
        System.out.print(invito);
        return tastiera.nextLine();
    }

    public static void write(Object... args) {
        Object[] var1 = args;
        int var2 = args.length;

        for(int var3 = 0; var3 < var2; ++var3) {
            Object x = var1[var3];
            System.out.print(x);
        }

    }

    public static void writeLn(Object... args) {
        write(args);
        System.out.println();
    }

    public static String doubleFormat(double x, int larghezza, int precisione) {
        return String.format("%1$" + larghezza + "." + precisione + "f", x);
    }

    public static String intFormat(int x, int larghezza) {
        return String.format("%1$" + larghezza + "d", x);
    }

    public static String leftFormat(String s, int larghezza) {
        return String.format("%1$" + larghezza + "s", s);
    }

    public static String rightFormat(String s, int larghezza) {
        return String.format("%1$-" + larghezza + "s", s);
    }

    static {
        tastiera = new Scanner(System.in);
        convErr = true;
        randomg = new Random();
    }
}
