package scorritore_stringhe_awt;
import java.awt.Button;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;
public class FrameScorritoreStringhe extends Frame implements ActionListener{
    private Button inizioSequenza;
    private Label numeroElementiSequenza;
    private Button fineSequenza;
    private Button elementoPrecedente;
    private Label elementoCorrente;
    private Button elementoSuccessivo;
    private Button aggiungiInFondo;
    private TextField daAggiungereInFondo;
    private Button eliminaCorrente;
    private Button inserimentoCorrente;
    private File file;
    private Scanner scanner;
    private FileWriter fileWriter;
    private ListaConcatenata lista;
    private int index;
    private boolean booleanVariable;
    public FrameScorritoreStringhe(String title){
        super(title);
        file = new File("Contenitore_Stringhe.csv");
        lista = new ListaConcatenata();
        caricaLista();
        index = 0;
        inizioSequenza = new Button("<<");
        inizioSequenza.setBackground(Color.red);
        numeroElementiSequenza = new Label(lista.size() == 0 ? "length: 0" : "length: "+lista.size());
        numeroElementiSequenza.setBackground(Color.gray);
        fineSequenza = new Button(">>");
        fineSequenza.setBackground(Color.green);
        elementoPrecedente = new Button("<");
        elementoPrecedente.setBackground(Color.red);
        elementoCorrente = new Label(lista.size() == 0 ? "" : ""+lista.get(0));
        elementoCorrente.setBackground(Color.gray);
        elementoSuccessivo = new Button(">");
        elementoSuccessivo.setBackground(Color.green);
        aggiungiInFondo = new Button("ADD");
        daAggiungereInFondo = new TextField();
        eliminaCorrente = new Button("DEL");
        eliminaCorrente.setBackground(lista.size() == 0 ? Color.red : Color.white);
        inserimentoCorrente = new Button("INS");
        booleanVariable = false;
        if(lista.size() == 0 || lista.size() == 1){
            inizioSequenza.setBackground(Color.red);
            fineSequenza.setBackground(Color.red);
            elementoPrecedente.setBackground(Color.red);
            elementoSuccessivo.setBackground(Color.red);
        }
        addWindowListener(new WindowAdapter(){
            @Override
            public void windowClosing(WindowEvent e){
                if(isBeenModified()){
                    int risposta = javax.swing.JOptionPane.showConfirmDialog(null, "Vuoi salvare le modifiche effettuate?");
                    switch(risposta){
                        case 0: saveChanges();break;
                        case 1: break;
                        case 2: return;
                        default: return;
                    }
                }
                dispose();
            }
	});
        setSize(370, 260);
	setLayout(null);
        modellaInterfaccia();
        inizioSequenza.addActionListener(this);
        fineSequenza.addActionListener(this);
        elementoPrecedente.addActionListener(this);
        elementoSuccessivo.addActionListener(this);
        aggiungiInFondo.addActionListener(this);
        eliminaCorrente.addActionListener(this);
        inserimentoCorrente.addActionListener(this);
        add(inizioSequenza);
        add(numeroElementiSequenza);
        add(fineSequenza);
        add(elementoPrecedente);
        add(elementoSuccessivo);
        add(elementoCorrente);
        add(aggiungiInFondo);
        add(daAggiungereInFondo);
        add(eliminaCorrente);
        add(inserimentoCorrente);
    }
    private void modellaInterfaccia(){
        inizioSequenza.setBounds(30, 40, 60, 30);
        numeroElementiSequenza.setBounds(160, 40, 60, 30);
        fineSequenza.setBounds(280, 40, 60, 30);
        elementoPrecedente.setBounds(30, 110, 60, 30);
        elementoCorrente.setBounds(110, 110, 150, 30);
        elementoSuccessivo.setBounds(280, 110, 60, 30);
        aggiungiInFondo.setBounds(30, 180, 60, 30);
        daAggiungereInFondo.setBounds(110, 180, 150, 30);
        eliminaCorrente.setBounds(280, 180, 60, 30);
        inserimentoCorrente.setBounds(155, 220, 60, 30);
        setResizable(false);
    }
    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == inizioSequenza && inizioSequenza.getBackground() == Color.green){
            elementoCorrente.setText(""+lista.get(0));
            inizioSequenza.setBackground(Color.red);
            fineSequenza.setBackground(Color.green);
            elementoPrecedente.setBackground(Color.red);
            elementoSuccessivo.setBackground(Color.green);
            index = 0;
        }else if(e.getSource() == fineSequenza && fineSequenza.getBackground() == Color.green){
            elementoCorrente.setText(""+lista.get(lista.size()-1));
            inizioSequenza.setBackground(Color.green);
            fineSequenza.setBackground(Color.red);
            elementoPrecedente.setBackground(Color.green);
            elementoSuccessivo.setBackground(Color.red);
            index = lista.size()-1;
        }else if(e.getSource() == elementoPrecedente && elementoPrecedente.getBackground() == Color.green){
            elementoCorrente.setText(""+lista.get(index-1));
            index--;
            if(index == 0){
                inizioSequenza.setBackground(Color.red);
                elementoPrecedente.setBackground(Color.red);
            }
            fineSequenza.setBackground(Color.green);
            elementoSuccessivo.setBackground(Color.green);
        }else if(e.getSource() == elementoSuccessivo && elementoSuccessivo.getBackground() == Color.green){
            elementoCorrente.setText(""+lista.get(index+1));
            index++;
            if(index == lista.size()-1){
                fineSequenza.setBackground(Color.red);
                elementoSuccessivo.setBackground(Color.red);
            }
            inizioSequenza.setBackground(Color.green);
            elementoPrecedente.setBackground(Color.green);
        }else if(e.getSource() == aggiungiInFondo && !daAggiungereInFondo.getText().equals("")){
            booleanVariable = true;
            if(lista.size() == 0){
                elementoCorrente.setText(daAggiungereInFondo.getText());
                inizioSequenza.setBackground(Color.red);
                fineSequenza.setBackground(Color.red);
                elementoPrecedente.setBackground(Color.red);
                elementoSuccessivo.setBackground(Color.red);
            }else if(index == lista.size()-1){
                fineSequenza.setBackground(Color.green);
                elementoSuccessivo.setBackground(Color.green);
            }
            lista.addTail(daAggiungereInFondo.getText());
            numeroElementiSequenza.setText("length: "+lista.size());
            eliminaCorrente.setBackground(Color.white);
        }else if(e.getSource() == eliminaCorrente && eliminaCorrente.getBackground() == Color.white){
            booleanVariable = true;
            lista.remove(index);
            numeroElementiSequenza.setText("length: "+lista.size());
            if(lista.size() == 0 || lista.size() == 1){
                inizioSequenza.setBackground(Color.red);
                fineSequenza.setBackground(Color.red);
                elementoPrecedente.setBackground(Color.red);
                elementoSuccessivo.setBackground(Color.red);
                index = 0;
                elementoCorrente.setText(lista.size() == 0 ? "" : ""+lista.get(index));
                if(lista.size() == 0)
                    eliminaCorrente.setBackground(Color.red);
            }else{
                if(index == lista.size()){
                    elementoCorrente.setText(""+lista.get(index-1));
                    index--;
                    fineSequenza.setBackground(Color.red);
                    elementoSuccessivo.setBackground(Color.red);
                }else
                    elementoCorrente.setText(""+lista.get(index));
            }
        }else if(e.getSource() == inserimentoCorrente){
            booleanVariable = true;
            lista.add(index, daAggiungereInFondo.getText());
            elementoCorrente.setText(""+lista.get(index));
            numeroElementiSequenza.setText("length: "+lista.size());
            eliminaCorrente.setBackground(Color.white);
            if(lista.size() == 0){
                inizioSequenza.setBackground(Color.red);
                fineSequenza.setBackground(Color.red);
                elementoPrecedente.setBackground(Color.red);
                elementoSuccessivo.setBackground(Color.red);
            }else if(index+1 == lista.size()-1){
                fineSequenza.setBackground(Color.green);
                elementoSuccessivo.setBackground(Color.green);
            }
        }
    }
    private void caricaLista(){
        try{
            scanner = new Scanner(file);
        }catch(FileNotFoundException ex){
            ex.printStackTrace();
        }
        while(scanner.hasNextLine())
            lista.addTail(scanner.nextLine());
    }
    private boolean isBeenModified(){
        return booleanVariable;
    }
    private void saveChanges(){
        try{
            fileWriter = new FileWriter(file);
            for(int i=0;i<lista.size();i++){
                fileWriter.write(""+lista.get(i));
                if(i+1 != lista.size())
                    fileWriter.append('\n');
            }
            fileWriter.flush();
        }catch(IOException ex){
            ex.printStackTrace();
        }
    }
}