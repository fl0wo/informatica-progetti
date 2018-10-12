package geneticalgorithm;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author Florian
 */
public class Scheduler {
    
    static ArrayList<GraficaSemplice> finestre = new ArrayList<>();
    
    static void add(GraficaSemplice e){
        finestre.add(e);
        init("Applicazione Grafica Semplice Zuccante",e);
    }
    
    static void init(String titolo,GraficaSemplice finN) {
        if (finN.frame != null) {
            finN.frame.setVisible(false);
        }

        finN.frame = new JFrame();
        finN.offscreenImage = new BufferedImage(finN.larghezza, finN.altezza, 2);
        finN.onscreenImage = new BufferedImage(finN.larghezza, finN.altezza, 2);
        finN.offscreen = finN.offscreenImage.createGraphics();
        finN.onscreen = finN.onscreenImage.createGraphics();
        finN.setXscale();
        finN.setYscale();
        finN.offscreen.setColor(finN.DEFAULT_CLEAR_COLOR);
        finN.offscreen.fillRect(0, 0, finN.larghezza, finN.altezza);
        finN.setColore();
        finN.setGrossezza();
        finN.pulisci();
        ImageIcon icon = new ImageIcon(finN.onscreenImage);
        JLabel draw = new JLabel(icon);
        draw.addMouseListener(finN.std);
        draw.addMouseMotionListener(finN.std);
        finN.frame.setContentPane(draw);
        finN.frame.addKeyListener(finN.std);
        finN.frame.setResizable(false);
        finN.frame.setDefaultCloseOperation(3);
        finN.frame.setTitle(titolo);
        finN.frame.setJMenuBar(finN.createMenuBar());
        finN.frame.pack();
        finN.frame.requestFocusInWindow();
        finN.frame.setVisible(true);
    }
    
    
    
}
