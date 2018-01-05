package snakefatto;

/**
 *
 * @author Florian
*/
import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Game extends Frame {

    public Game() {
        add(new Schermo());
        setResizable(false);
        pack();

        setTitle("Serpentino");
        //setBounds(400,400,400,400);
        setLocationRelativeTo(null);

        chiusura();
    }

    public static void main(String[] args) {
        Frame frame = new Game();
        frame.setVisible(true);
    }

    private void chiusura() {
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                dispose();
                System.exit(0);
            }
        });
    }
}
