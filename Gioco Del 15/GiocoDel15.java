import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import java.util.*;
import java.io.*;


class GiocoDel15 extends JFrame implements ActionListener, Serializable {
  private ArrayList<JButton> bottoni;
  private int index;
  private JPanel pannello;
  GiocoDel15() {
    super("Gioco del 15 Sabani 3IC");
    index = 15;
    
    bottoni = new ArrayList<JButton>(15);
    bottoni.ensureCapacity(16);
    pannello = new JPanel(new GridLayout(4,4));
    inizializzaArray();

    Collections.shuffle(bottoni);

    addButton();
    JButton b = new JButton();
    b.addActionListener(this);
    b.setBackground(Color.WHITE);
    b.setEnabled(false);
    bottoni.add(b);
    pannello.add(b);
    
    getContentPane().add(pannello);
 
    JMenuBar barraMenu = new JMenuBar();
    setJMenuBar(barraMenu);

    JMenu file = new JMenu("File");
    JMenuItem salva = new JMenuItem("Salva Partita");
    JMenuItem carica = new JMenuItem("Carica Partita");
    JMenuItem exit = new JMenuItem("Exit");
    
    salva.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent ae) {
        salvaPartita();
      }
    });
    carica.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent ae) {
        caricaPartita();
      }
    });
    exit.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent ae) {
        esci();
      }
    });
    
    file.add(salva);
    file.add(carica);
    file.addSeparator();
    file.add(exit);
    
    barraMenu.add(file);
    // Fine menu
    
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }
  private void addButton() {
    // Aggiungo gli elementi al pannello
    for(int i=0; i<bottoni.size(); i++) {
      pannello.add(bottoni.get(i));
    }
  }
  private void inizializzaArray() {
    for(int i=0; i<15; i++) {
      JButton b = new JButton(""+(i+1));
      b.addActionListener(this);
      bottoni.add(b);
    }
  }

  public void actionPerformed(ActionEvent ae) {
    JButton bottonePremuto = (JButton) ae.getSource();
    JButton greenButton = bottoni.get(index);
    int i = bottoni.indexOf(bottonePremuto); // indice del bottone clickato
    if(((i + 1) == index) || ((i -1) == index) || ((i + 4) == index) || ((i - 4) == index)) {
      greenButton.setText(bottonePremuto.getText());
      greenButton.setBackground(bottonePremuto.getBackground());
      greenButton.setEnabled(true);
      
      bottonePremuto.setBackground(Color.WHITE);
      bottonePremuto.setText("");
      bottonePremuto.setEnabled(false);
      index = i;
      if(controllaCorrispondenze()) {
        JOptionPane.showMessageDialog(null,"Hai Vinto, Complimenti!",null,JOptionPane.PLAIN_MESSAGE);
      }
    }
  }
  private boolean controllaCorrispondenze() {
    for(int i=0; i<bottoni.size()-1; i++) {
      if(!bottoni.get(i).getText().equals(""+(i+1))) {
        return false;
      }
    }
    return true;
  }
  private void salvaPartita() {
    try {
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File("Partita.txt")));
        oos.writeObject(bottoni);
        oos.flush();
        oos.close();
      
    } catch(Exception e) {e.printStackTrace();}
    
  }
  private void caricaPartita() {
    try {       
        bottoni.clear();
        pannello.removeAll();
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File("Partita.txt")));
        bottoni = (ArrayList<JButton>) ois.readObject();
        ois.close();
      
        addButton();
        pannello.repaint();
        pannello.revalidate();
      
        index = bottoni.indexOf("");

    } catch(Exception e) {e.getCause();}
  }
  private void esci() {
    int scelta = JOptionPane.showConfirmDialog(null,"Sicuro di voler uscire?","Sicuro?",JOptionPane.OK_CANCEL_OPTION);
    if(scelta != 0) {
      JOptionPane.showMessageDialog(null,"Operazione annullata!","Operazione Annullata!",JOptionPane.INFORMATION_MESSAGE);
    } else {
      dispose();
    }
  }
    
    
    
    
  public static void makeGUI() {
    GiocoDel15 g15 = new GiocoDel15();
    g15.setSize(300,300);
    g15.setVisible(true);
  }
  
  public static void main(String[] args) {
    try {
      SwingUtilities.invokeAndWait(new Runnable() {
        public void run() {
          makeGUI();
        }
      });
    } catch(Exception e) {}
  }
}