package calcolatrice;
import java.awt.Button;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import static calcolatrice.Calcolatore.resolve;

public class Calcolatrice extends Frame implements ActionListener{
    private Button __numero_0 = new Button("0");
     private Button __numero_1 = new Button("1");
    private Button __numero_2 = new Button("2");
    private Button __numero_3 = new Button("3");
    private Button __numero_4 = new Button("4");
    private Button __numero_5 = new Button("5");
    private Button __numero_6 = new Button("6");
    private Button __numero_7 = new Button("7");
    private Button __numero_8 = new Button("8");
    private Button __numero_9 = new Button("9");
    private Button __segno = new Button("±");
    private Button __virgolaDecimale = new Button(",");
    private Button __addizione = new Button("+");
    private Button __sottrazione = new Button("-");
    private Button __divisione = new Button("÷");
    private Button __moltiplicazione = new Button("×");
    private Button __calcolaRisultato = new Button("=");
    private Button __cancellaTutto = new Button("C");
    private Button __cancellaCorrente = new Button("Œ");
    private Button __cancellaVersoSinistra = new Button("DEL");
    private Label __risultato = new Label("0");
    private Button __parentesiAperta = new Button("(");
    private Button __parentesiChiusa = new Button(")");
    private boolean __voidFlag = true;
    private int __decimalPointCount = 0;
    private int __openBrackets = 0;
    private int __lastOperatorPosition = 0;
    private boolean __sign = false;
    private boolean __resultLastOp = false;
    private boolean __exception = false;
    
    public Calcolatrice(String title){
	super(title);
	addWindowListener(new WindowAdapter(){
            @Override
            public void windowClosing(WindowEvent e){
		dispose();
            }
	});
        
        __risultato.setBackground(Color.yellow);
	__risultato.setAlignment(Label.RIGHT);
	setSize(347, 355);
	setLayout(null);
	modellaInterfaccia();
	__numero_0.addActionListener(this);
	__numero_1.addActionListener(this);
	__numero_2.addActionListener(this);
	__numero_3.addActionListener(this);
	__numero_4.addActionListener(this);
	__numero_5.addActionListener(this);
	__numero_6.addActionListener(this);
	__numero_7.addActionListener(this);
	__numero_8.addActionListener(this);
	__numero_9.addActionListener(this);
	__addizione.addActionListener(this);
	__sottrazione.addActionListener(this);
	__moltiplicazione.addActionListener(this);
	__divisione.addActionListener(this);
	__segno.addActionListener(this);
	__virgolaDecimale.addActionListener(this);
	__calcolaRisultato.addActionListener(this);
	__cancellaCorrente.addActionListener(this);
	__cancellaTutto.addActionListener(this);
	__cancellaVersoSinistra.addActionListener(this);
        __parentesiAperta.addActionListener(this);
        __parentesiChiusa.addActionListener(this);
	add(__risultato);
	add(__cancellaCorrente);
	add(__cancellaTutto);
	add(__cancellaVersoSinistra);
	add(__divisione);
	add(__numero_7);
	add(__numero_8);
	add(__numero_9);
	add(__moltiplicazione);
	add(__numero_4);
	add(__numero_5);
	add(__numero_6);
	add(__sottrazione);
	add(__numero_1);
	add(__numero_2);
	add(__numero_3);
	add(__addizione);
	add(__segno);
	add(__numero_0);
	add(__virgolaDecimale);
	add(__calcolaRisultato);
        add(__parentesiAperta);
        add(__parentesiChiusa);
	setResizable(false);
    }
    private void modellaInterfaccia(){
	__risultato.setBounds(6, 35, 335, 45);
	__cancellaCorrente.setBounds(5, 80, 84, 45);
	__cancellaTutto.setBounds(89, 80, 84, 45);
	__cancellaVersoSinistra.setBounds(173, 80, 84, 45);
	__divisione.setBounds(257, 80, 84, 45);
	__numero_7.setBounds(5, 125, 84, 45);
	__numero_8.setBounds(89, 125, 84, 45);
	__numero_9.setBounds(173, 125, 84, 45);
	__moltiplicazione.setBounds(257, 125, 84, 45);
	__numero_4.setBounds(5, 170, 84, 45);
	__numero_5.setBounds(89, 170, 84, 45);
	__numero_6.setBounds(173, 170, 84, 45);
	__sottrazione.setBounds(257, 170, 84, 45);
	__numero_1.setBounds(5, 215, 84, 45);
	__numero_2.setBounds(89, 215, 84, 45);
	__numero_3.setBounds(173, 215, 84, 45);
	__addizione.setBounds(257, 215, 84, 45);
	__segno.setBounds(5, 260, 84, 45);
	__numero_0.setBounds(89, 260, 84, 45);
	__virgolaDecimale.setBounds(173, 260, 84, 45);
	__calcolaRisultato.setBounds(257, 260, 84, 45);
        __parentesiAperta.setBounds(5, 305, 168, 45);
        __parentesiChiusa.setBounds(173, 305, 168, 45);
    }
    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == __numero_0){
            if(!__risultato.getText().equals("0"))
                numberAction('0');
            if(__risultato.getText().equals("0"))
                __voidFlag = true;
        }else if(e.getSource() == __numero_1){
            numberAction('1');
	}else if(e.getSource() == __numero_2){
            numberAction('2');
	}else if(e.getSource() == __numero_3){
            numberAction('3');
	}else if(e.getSource() == __numero_4){
            numberAction('4');
	}else if(e.getSource() == __numero_5){
            numberAction('5');
	}else if(e.getSource() == __numero_6){
            numberAction('6');
	}else if(e.getSource() == __numero_7){
            numberAction('7');
	}else if(e.getSource() == __numero_8){
            numberAction('8');
	}else if(e.getSource() == __numero_9){
            numberAction('9');
        }else if(e.getSource() == __addizione){
            operatorAction('+');
        }else if(e.getSource() == __sottrazione){
            operatorAction('-');
        }else if(e.getSource() == __moltiplicazione){
            operatorAction('×');
	}else if(e.getSource() == __divisione){
            operatorAction('÷');
        }else if(e.getSource() == __virgolaDecimale){
            char temp = __risultato.getText().charAt(__risultato.getText().length()-1);
            if(__voidFlag || __decimalPointCount == 0){
                if(__voidFlag || __exception)
                    __risultato.setText("0,");
                else if(__decimalPointCount == 0 && temp != ')')
                    __risultato.setText((!isOperator(temp) && temp != '(') ? __risultato.getText()+"," : __risultato.getText()+"0,");
                __decimalPointCount = 1;
                __voidFlag = false;
                __resultLastOp = false;
                __exception = false;
            }
	}else if(e.getSource() == __cancellaVersoSinistra){
            if(!__voidFlag && !__sign || __exception){
                if(__risultato.getText().length() == 1 || __exception){
                    __risultato.setText("0");
                    __voidFlag = true;
                    __exception = false;
                }else{
                    char temp = __risultato.getText().charAt(__risultato.getText().length()-1);
                    if(!isOperator(temp))
                        __risultato.setText(__risultato.getText().substring(0, __risultato.getText().length()-1));
                    if(isDecimalPoint(temp))
                        __decimalPointCount = 0;
                    else if(temp == '(')
                        __openBrackets--;
                    else if(temp == ')')
                        __openBrackets++;
                }
            }
        }else if(e.getSource() == __cancellaTutto){
            __risultato.setText("0");
            __decimalPointCount = 0;
            __voidFlag = true;
            __openBrackets = 0;
            __resultLastOp = false;
            __sign = false;
            __exception = false;
        }else if(e.getSource() == __cancellaCorrente){
            String temp = __risultato.getText();
            if(temp.indexOf('+') == -1 && temp.indexOf('-') == -1 && temp.indexOf('×') == -1 && temp.indexOf('÷') == -1 || 
                    __exception){
                __risultato.setText("0");
                __voidFlag = true;
                __openBrackets = 0;
                __exception = false;
            }else{
                for(int i=temp.length()-1;i>0;i--){
                    if(temp.charAt(i) == '(')
                        __openBrackets--;
                    else if(temp.charAt(i) == ')')
                        __openBrackets++;
                    if(isOperator(temp.charAt(i))){
                        __risultato.setText(temp.substring(0, i+1));
                        break;
                    }
                }
            }
            __decimalPointCount = 0;
            __resultLastOp = false;
            __sign = false;
        }else if(e.getSource() == __parentesiAperta){
            String temp = __risultato.getText();
            if(isOperator(temp.charAt(temp.length()-1)) || temp.charAt(temp.length()-1) == '(' || __voidFlag || __exception){
                __risultato.setText(__voidFlag ? "(" : temp+"(");
                __openBrackets++;
                __voidFlag = false;
                __resultLastOp = false;
                __exception = false;
            }
        }else if(e.getSource() == __parentesiChiusa){
            String temp = __risultato.getText();
            if(__openBrackets > 0){
                if(temp.equals("(")){
                    __risultato.setText("(0)");
                    __voidFlag = true;
                }else if(temp.charAt(temp.length()-1) == ','){
                    __risultato.setText(temp.substring(0, temp.length()-1) + ")");
                    __decimalPointCount = 0;
                }else if(isOperator(temp.charAt(temp.length()-1))){
                    for(int i=temp.length()-1;i>=0;i--){
                        if(temp.charAt(i) == ')'){
                            int count = 0;
                            for(int j=i-1;j>0;j--){
                                if(temp.charAt(j) == ')')
                                    count++;
                                else if(temp.charAt(j) == '('){
                                    if(count == 0){
                                        i = j;
                                        break;
                                    }else
                                        count--;
                                }
                            }
                        }else if(temp.charAt(i) == '('){
                            int count = 0;
                            for(int j=i+1;j<temp.length()-1;j++)
                                if(temp.charAt(j) == ')')
                                    count++;
                            __risultato.setText(count > 1 ? temp + temp.substring(i+1, temp.length()-1) + ")": 
                                    temp + temp.substring(i+1, temp.length()-1) + ")");
                            break;
                        }
                    }
                }else
                    __risultato.setText(temp.charAt(temp.length()-1) == '(' ? temp + "0)" : temp + ")");
                __openBrackets--;
            }
        }else if(e.getSource() == __segno){
            if(!__exception){
                String temp = __risultato.getText();
                boolean composto = true;
                if(temp.charAt(0) == '-' && __resultLastOp){
                    temp = "(0" + temp + ")";
                    __lastOperatorPosition = 2;
                    __sign = true;
                }else if(temp.length() >= 4 && temp.charAt(0) == '(' && temp.charAt(1) == '-' 
                        && temp.substring(2).indexOf('+') == -1 
                        && temp.substring(2).indexOf('-') == -1 && temp.substring(2).indexOf('×') == -1 
                        && temp.substring(2).indexOf('÷') == -1)
                    composto = false;
                else if(temp.indexOf('+') == -1 && temp.indexOf('-') == -1 && temp.indexOf('×') == -1 
                        && temp.indexOf('÷') == -1)
                    composto = false;
                if(!composto){
                    if(temp.contains("(-")){
                        __risultato.setText(temp.substring(0, temp.indexOf("(-")) + temp.substring(temp.indexOf("(-")+2, temp.length()-1));
                        __sign = false;
                    }else{
                        if(temp.charAt(temp.length()-1) == ','){
                            __risultato.setText("(-" + temp.substring(0, temp.length()-1) + ")");
                            __decimalPointCount = 0;
                        if(__risultato.getText().equals("(-0)"))
                            __voidFlag = true;
                        }else
                            __risultato.setText(temp.charAt(temp.length()-1) == '(' ? "(-" + temp + "0)" : "(-" + temp + ")");
                            __sign = true;
                    }
                    __resultLastOp = false;
                }else{
                    if(temp.length()-1 > __lastOperatorPosition){
                        String corrente = temp.substring(__lastOperatorPosition+1);
                        if(corrente.contains("(-")){
                            if(__resultLastOp)
                                __risultato.setText(temp.substring(4, temp.length()-2));
                            else
                                __risultato.setText(temp.substring(0, __lastOperatorPosition+1) + corrente.substring(2, corrente.length()-1));
                            __sign = false;
                        }else{
                            if(corrente.charAt(corrente.length()-1) == ','){
                                __risultato.setText(temp.substring(0, __lastOperatorPosition+1) + "(-" + corrente.substring(0, corrente.length()-1) + ")");
                                __decimalPointCount = 0;
                            }else
                                __risultato.setText(temp.substring(0, __lastOperatorPosition+1) + "(-" + corrente + ")");
                                __sign = true;
                        }
                        __resultLastOp = false;
                    }
                }
            }
        }else if(e.getSource() == __calcolaRisultato){
            if(!__resultLastOp || __exception){
                if(__exception){
                    __risultato.setText("0");
                    __exception = false;
                }else{
                    String result = new String();
                    try{
                        result = resolve(__risultato.getText());
                        __risultato.setText(result);
                    }catch(StringIndexOutOfBoundsException ex){
                        __risultato.setText("Invalid Expression");
                        __exception = true;
                    }catch(UnsupportedOperationException ex){
                        __risultato.setText(ex.getMessage());
                        __exception = true;
                    }
                }
                __voidFlag = true;
                __resultLastOp = true;
                __sign = false;
            }
        }
    }
    private void numberAction(char num){
        if(__risultato.getText().charAt(__risultato.getText().length()-1) != ')' || __voidFlag){
            String temp = __risultato.getText();
            if(!__voidFlag && temp.charAt(temp.length()-1) == '0' && isOperator(temp.charAt(temp.length()-2)))
                __risultato.setText(temp.substring(0, temp.length()-1) + num);
            else
                __risultato.setText(__voidFlag ? ""+num : __risultato.getText()+num);
            __voidFlag = false;
            __resultLastOp = false;
            __exception = false;
        }
    }
    private void operatorAction(char op){
        if(!__exception){
            String tempString = __risultato.getText();
            char tempChar = tempString.charAt(tempString.length()-1);
            if(tempString.indexOf('E') > -1)
                __risultato.setText("0" + op);
            else if(isOperator(tempChar)){
                if(tempChar != op)
                    __risultato.setText(tempString.substring(0, tempString.length()-1) + op);
            }else if(isDecimalPoint(tempChar))
                __risultato.setText(tempString.substring(0, tempString.length()-1) + op);
            else
                __risultato.setText(tempChar == '(' ? tempString + "0" + op : tempString + op);
            __decimalPointCount = 0;
            __voidFlag = false;
            __lastOperatorPosition = __risultato.getText().length()-1;
            __resultLastOp = false;
            __sign = false;
        }
    }
    private boolean isDecimalPoint(char character){
	return character == ',';
    }
    private boolean isOperator(char character){
	return character == '+' | character == '-' | character == '×'| character == '÷';
    }
}