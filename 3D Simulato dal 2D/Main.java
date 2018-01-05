package grafica;

import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.JTextField;

public class Main {

    private final static Dimension risoluzione = Toolkit.getDefaultToolkit().getScreenSize();  // Mi prendo la risoluzione dello schermo
    static JTextField TF;

    public static void main(String[] args) {
        JFrame jframe = new JFrame();
        Schermo display = new Schermo();
        jframe.add(display);
        jframe.setUndecorated(true);
        jframe.setSize(risoluzione);
        jframe.setVisible(true);
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static Dimension getRisoluzione() {
        return risoluzione;
    }

    public static JTextField getTF() {
        return TF;
    }
    
    
}
