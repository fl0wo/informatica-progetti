package scorritore_pila_awt;
import java.awt.Button;
import java.awt.Color;
import java.awt.Choice;
import java.awt.Frame;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
public class FrameScorritorePila extends Frame implements ActionListener, ItemListener{
    private Pila pila;
    private Iterator iterator;
    private TextField argPush;
    private Button push;
    private Label top;
    private Button pop;
    private Label isEmpty;
    private Label separatoreGrafico;
    private Button elementoTop;
    private Label elementoCorrente;
    private Button elementoSuccessivo;
    private Label pilaLength;
    private Choice sceltaElementoCorrente;
    public FrameScorritorePila(String title, Pila pila){
	super(title);
	this.pila = pila;
        iterator = (pila.isEmpty() ? null : pila.iterator());
	argPush = new TextField();
	push = new Button("push");
	top = new Label(pila.isEmpty() ? "" : ("top: ")+pila.top());
	top.setBackground(Color.gray);
	pop = new Button("pop");
	isEmpty = new Label(pila.isEmpty() ? "IS EMPTY" : "");
	isEmpty.setBackground(Color.gray);
	separatoreGrafico = new Label("  - - - - - - - - - - - - - - - - - - - - "
		+ "- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -");
	elementoTop = new Button("<<");
	elementoCorrente = new Label(pila.isEmpty() ? "" : "current: "+iterator.current());
	elementoSuccessivo = new Button(">");
        pilaLength = new Label("length: "+pila.length());
        sceltaElementoCorrente = new Choice();
	addWindowListener(new WindowAdapter(){
            @Override
            public void windowClosing(WindowEvent e){
		dispose();
            }
	});
	setSize(370, 260);
	setLayout(null);
	modellaInterfaccia();
	push.addActionListener(this);
	pop.addActionListener(this);
	elementoTop.addActionListener(this);
	elementoSuccessivo.addActionListener(this);
        sceltaElementoCorrente.addItemListener(this);
	add(argPush);
	add(push);
	add(top);
	add(pop);
	add(isEmpty);
	add(separatoreGrafico);
	add(elementoTop);
	add(elementoCorrente);
	add(elementoSuccessivo);
        add(pilaLength);
        add(sceltaElementoCorrente);
	setResizable(false);
    }
    private void modellaInterfaccia(){
	argPush.setBounds(150, 30, 80, 40);
	push.setBounds(260, 30, 80, 40);
	top.setBounds(30, 90, 200, 40);
	pop.setBounds(260, 90, 80, 40);
	isEmpty.setBounds(30, 135, 100, 40);
	separatoreGrafico.setBounds(20, 180, 320, 10);
	elementoTop.setBounds(30, 200, 90, 40);
	elementoCorrente.setBounds(140, 200, 90, 40);
	elementoSuccessivo.setBounds(250, 200, 90, 40);
        pilaLength.setBounds(30, 30, 80, 40);
        sceltaElementoCorrente.setBounds(260, 145, 80, 40);
    }
    @Override
    public void actionPerformed(ActionEvent e){
        if(iterator == null)
            iterator = pila.iterator();
	if(e.getSource() == push && !argPush.getText().equals("")){
            pila.push(argPush.getText());
            iterator = pila.iterator();
            elementoCorrente.setText("current: "+iterator.current());
            top.setText(pila.isEmpty() ? "" : ("top: ")+pila.top());
            isEmpty.setText(pila.isEmpty() ? "IS EMPTY" : "");
            pilaLength.setText("length: "+pila.length());
            updateChoice();
	}else if(e.getSource() == pop){
            try{
                pila.pop();
                iterator = pila.iterator();
                elementoCorrente.setText(pila.isEmpty() ? "" : "current: "+iterator.current());
            }catch(NullPointerException ex){
                javax.swing.JOptionPane.showMessageDialog(null, "ERRORE: PILA VUOTA");
                dispose();
            }
            top.setText(pila.isEmpty() ? "" : ("top: ")+pila.top());
            isEmpty.setText(pila.isEmpty() ? "IS EMPTY" : "");
            pilaLength.setText("length: "+pila.length());
            updateChoice();
	}else if(e.getSource() == elementoTop){
            try{
                iterator.goFirst();
                elementoCorrente.setText("current: "+iterator.current());
            }catch(NullPointerException ex){
                javax.swing.JOptionPane.showMessageDialog(null, "ERRORE: PILA VUOTA");
                dispose();
            }
        }else if(e.getSource() == elementoSuccessivo){
            try{
                iterator.goNext();
                elementoCorrente.setText("current: "+iterator.current());
            }catch(NullPointerException ex){
                javax.swing.JOptionPane.showMessageDialog(null, "ERRORE: SEI USCITO DALLA PILA");
                dispose();
            }
        }
    }
    private void updateChoice(){
        Iterator temp = pila.iterator();
        sceltaElementoCorrente.removeAll();
        while(temp.inside()){
            sceltaElementoCorrente.add(""+temp.current());
            temp.goNext();
        }
    }
    @Override
    public void itemStateChanged(ItemEvent e){
        iterator = pila.iterator();
        while(iterator.inside()){
            if(iterator.current().toString().equals(e.getItem().toString())){
                elementoCorrente.setText("current: "+iterator.current());
                break;
            }
            iterator.goNext();
        }
    }
}