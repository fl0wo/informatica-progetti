package calcolatrice;
import java.awt.Color;
import static calcolatrice.Calcolatore.resolve;
public class FrameCalcolatore_SWING extends javax.swing.JFrame{
    private boolean voidFlag = true;
    private int decimalPointCount = 0;
    private int openBrackets = 0;
    private int lastOperatorPosition = 0;
    private boolean sign = false;
    private boolean resultLastOp = false;
    private boolean exception = false;
    public FrameCalcolatore_SWING(){
        initComponents();
        setSize(405, 480);
        getContentPane().setBackground(Color.lightGray);
        setResizable(false);
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        risultato = new javax.swing.JLabel();
        cancellaCorrente = new javax.swing.JButton();
        cancellaTutto = new javax.swing.JButton();
        cancellaVersoSinistra = new javax.swing.JButton();
        divisione = new javax.swing.JButton();
        moltiplicazione = new javax.swing.JButton();
        sottrazione = new javax.swing.JButton();
        addizione = new javax.swing.JButton();
        calcolaRisultato = new javax.swing.JButton();
        numero_7 = new javax.swing.JButton();
        numero_8 = new javax.swing.JButton();
        numero_9 = new javax.swing.JButton();
        numero_4 = new javax.swing.JButton();
        numero_5 = new javax.swing.JButton();
        numero_6 = new javax.swing.JButton();
        numero_2 = new javax.swing.JButton();
        numero_3 = new javax.swing.JButton();
        numero_0 = new javax.swing.JButton();
        numero_1 = new javax.swing.JButton();
        virgolaDecimale = new javax.swing.JButton();
        segno = new javax.swing.JButton();
        parentesiAperta = new javax.swing.JButton();
        parentesiChiusa = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Calcolatrice_SWING");
        setBounds(new java.awt.Rectangle(0, 0, 0, 0));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        getContentPane().setLayout(null);

        risultato.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        risultato.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        risultato.setText("0");
        risultato.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 18))); // NOI18N
        getContentPane().add(risultato);
        risultato.setBounds(10, 10, 380, 75);

        cancellaCorrente.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        cancellaCorrente.setText("Œ");
        cancellaCorrente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancellaCorrenteActionPerformed(evt);
            }
        });
        getContentPane().add(cancellaCorrente);
        cancellaCorrente.setBounds(10, 90, 80, 55);

        cancellaTutto.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        cancellaTutto.setText("C");
        cancellaTutto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancellaTuttoActionPerformed(evt);
            }
        });
        getContentPane().add(cancellaTutto);
        cancellaTutto.setBounds(110, 90, 80, 55);

        cancellaVersoSinistra.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        cancellaVersoSinistra.setText("DEL");
        cancellaVersoSinistra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancellaVersoSinistraActionPerformed(evt);
            }
        });
        getContentPane().add(cancellaVersoSinistra);
        cancellaVersoSinistra.setBounds(210, 90, 80, 55);

        divisione.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        divisione.setText("÷");
        divisione.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                divisioneActionPerformed(evt);
            }
        });
        getContentPane().add(divisione);
        divisione.setBounds(310, 90, 80, 55);

        moltiplicazione.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        moltiplicazione.setText("×");
        moltiplicazione.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                moltiplicazioneActionPerformed(evt);
            }
        });
        getContentPane().add(moltiplicazione);
        moltiplicazione.setBounds(310, 150, 80, 55);

        sottrazione.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        sottrazione.setText("-");
        sottrazione.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sottrazioneActionPerformed(evt);
            }
        });
        getContentPane().add(sottrazione);
        sottrazione.setBounds(310, 210, 80, 55);

        addizione.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        addizione.setText("+");
        addizione.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addizioneActionPerformed(evt);
            }
        });
        getContentPane().add(addizione);
        addizione.setBounds(310, 270, 80, 55);

        calcolaRisultato.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        calcolaRisultato.setText("=");
        calcolaRisultato.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                calcolaRisultatoActionPerformed(evt);
            }
        });
        getContentPane().add(calcolaRisultato);
        calcolaRisultato.setBounds(310, 330, 80, 55);

        numero_7.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        numero_7.setText("7");
        numero_7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                numero_7ActionPerformed(evt);
            }
        });
        getContentPane().add(numero_7);
        numero_7.setBounds(10, 150, 80, 55);

        numero_8.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        numero_8.setText("8");
        numero_8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                numero_8ActionPerformed(evt);
            }
        });
        getContentPane().add(numero_8);
        numero_8.setBounds(110, 150, 80, 55);

        numero_9.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        numero_9.setText("9");
        numero_9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                numero_9ActionPerformed(evt);
            }
        });
        getContentPane().add(numero_9);
        numero_9.setBounds(210, 150, 80, 55);

        numero_4.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        numero_4.setText("4");
        numero_4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                numero_4ActionPerformed(evt);
            }
        });
        getContentPane().add(numero_4);
        numero_4.setBounds(10, 210, 80, 55);

        numero_5.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        numero_5.setText("5");
        numero_5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                numero_5ActionPerformed(evt);
            }
        });
        getContentPane().add(numero_5);
        numero_5.setBounds(110, 210, 80, 55);

        numero_6.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        numero_6.setText("6");
        numero_6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                numero_6ActionPerformed(evt);
            }
        });
        getContentPane().add(numero_6);
        numero_6.setBounds(210, 210, 80, 55);

        numero_2.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        numero_2.setText("2");
        numero_2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                numero_2ActionPerformed(evt);
            }
        });
        getContentPane().add(numero_2);
        numero_2.setBounds(110, 270, 80, 55);

        numero_3.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        numero_3.setText("3");
        numero_3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                numero_3ActionPerformed(evt);
            }
        });
        getContentPane().add(numero_3);
        numero_3.setBounds(210, 270, 80, 55);

        numero_0.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        numero_0.setText("0");
        numero_0.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                numero_0ActionPerformed(evt);
            }
        });
        getContentPane().add(numero_0);
        numero_0.setBounds(110, 330, 80, 55);

        numero_1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        numero_1.setText("1");
        numero_1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                numero_1ActionPerformed(evt);
            }
        });
        getContentPane().add(numero_1);
        numero_1.setBounds(10, 270, 80, 55);

        virgolaDecimale.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        virgolaDecimale.setText(",");
        virgolaDecimale.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                virgolaDecimaleActionPerformed(evt);
            }
        });
        getContentPane().add(virgolaDecimale);
        virgolaDecimale.setBounds(210, 330, 80, 55);

        segno.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        segno.setText("±");
        segno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                segnoActionPerformed(evt);
            }
        });
        getContentPane().add(segno);
        segno.setBounds(10, 330, 80, 55);

        parentesiAperta.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        parentesiAperta.setText("(");
        parentesiAperta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                parentesiApertaActionPerformed(evt);
            }
        });
        getContentPane().add(parentesiAperta);
        parentesiAperta.setBounds(10, 390, 180, 55);

        parentesiChiusa.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        parentesiChiusa.setText(")");
        parentesiChiusa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                parentesiChiusaActionPerformed(evt);
            }
        });
        getContentPane().add(parentesiChiusa);
        parentesiChiusa.setBounds(210, 390, 180, 55);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cancellaTuttoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancellaTuttoActionPerformed
        risultato.setText("0");
        decimalPointCount = 0;
        voidFlag = true;
        openBrackets = 0;
        resultLastOp = false;
        sign = false;
        exception = false;
    }//GEN-LAST:event_cancellaTuttoActionPerformed

    private void cancellaCorrenteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancellaCorrenteActionPerformed
        String temp = risultato.getText();
        if(temp.indexOf('+') == -1 && temp.indexOf('-') == -1 && temp.indexOf('×') == -1 && temp.indexOf('÷') == -1 || 
                exception){
            risultato.setText("0");
            voidFlag = true;
            openBrackets = 0;
            exception = false;
        }else{
            for(int i=temp.length()-1;i>0;i--){
                if(temp.charAt(i) == '(')
                    openBrackets--;
                else if(temp.charAt(i) == ')')
                    openBrackets++;
                if(isOperator(temp.charAt(i))){
                    risultato.setText(temp.substring(0, i+1));
                    break;
                }
            }
        }
        decimalPointCount = 0;
        resultLastOp = false;
        sign = false;
    }//GEN-LAST:event_cancellaCorrenteActionPerformed

    private void cancellaVersoSinistraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancellaVersoSinistraActionPerformed
        if(!voidFlag && !sign || exception){
            if(risultato.getText().length() == 1 || exception){
                risultato.setText("0");
                voidFlag = true;
                exception = false;
            }else{
                char temp = risultato.getText().charAt(risultato.getText().length()-1);
                if(!isOperator(temp))
                    risultato.setText(risultato.getText().substring(0, risultato.getText().length()-1));
                if(isDecimalPoint(temp))
                    decimalPointCount = 0;
                else if(temp == '(')
                    openBrackets--;
                else if(temp == ')')
                    openBrackets++;
            }
        }
    }//GEN-LAST:event_cancellaVersoSinistraActionPerformed

    private void divisioneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_divisioneActionPerformed
        operatorAction('÷');
    }//GEN-LAST:event_divisioneActionPerformed

    private void moltiplicazioneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_moltiplicazioneActionPerformed
        operatorAction('×');
    }//GEN-LAST:event_moltiplicazioneActionPerformed

    private void sottrazioneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sottrazioneActionPerformed
        operatorAction('-');
    }//GEN-LAST:event_sottrazioneActionPerformed

    private void addizioneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addizioneActionPerformed
        operatorAction('+');
    }//GEN-LAST:event_addizioneActionPerformed

    private void calcolaRisultatoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_calcolaRisultatoActionPerformed
        if(!resultLastOp || exception){
            if(exception){
                risultato.setText("0");
                exception = false;
            }else{
                String result = new String();
                try{
                    result = resolve(risultato.getText());
                    risultato.setText(result);
                }catch(StringIndexOutOfBoundsException ex){
                    risultato.setText("Invalid Expression");
                    exception = true;
                }catch(UnsupportedOperationException ex){
                    risultato.setText(ex.getMessage());
                    exception = true;
                }
            }
            voidFlag = true;
            resultLastOp = true;
            sign = false;
        }
    }//GEN-LAST:event_calcolaRisultatoActionPerformed

    private void numero_7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_numero_7ActionPerformed
        numberAction('7');
    }//GEN-LAST:event_numero_7ActionPerformed

    private void numero_8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_numero_8ActionPerformed
        numberAction('8');
    }//GEN-LAST:event_numero_8ActionPerformed

    private void numero_9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_numero_9ActionPerformed
        numberAction('9');
    }//GEN-LAST:event_numero_9ActionPerformed

    private void numero_4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_numero_4ActionPerformed
        numberAction('4');
    }//GEN-LAST:event_numero_4ActionPerformed

    private void numero_5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_numero_5ActionPerformed
        numberAction('5');
    }//GEN-LAST:event_numero_5ActionPerformed

    private void numero_6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_numero_6ActionPerformed
        numberAction('6');
    }//GEN-LAST:event_numero_6ActionPerformed

    private void numero_2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_numero_2ActionPerformed
        numberAction('2');
    }//GEN-LAST:event_numero_2ActionPerformed

    private void numero_3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_numero_3ActionPerformed
        numberAction('3');
    }//GEN-LAST:event_numero_3ActionPerformed

    private void numero_0ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_numero_0ActionPerformed
        if(!risultato.getText().equals("0"))
            numberAction('0');
        if(risultato.getText().equals("0"))
            voidFlag = true;
    }//GEN-LAST:event_numero_0ActionPerformed

    private void numero_1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_numero_1ActionPerformed
        numberAction('1');
    }//GEN-LAST:event_numero_1ActionPerformed

    private void virgolaDecimaleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_virgolaDecimaleActionPerformed
        char temp = risultato.getText().charAt(risultato.getText().length()-1);
        if(voidFlag || decimalPointCount == 0){
            if(voidFlag || exception)
                risultato.setText("0,");
            else if(decimalPointCount == 0 && temp != ')')
                risultato.setText((!isOperator(temp) && temp != '(') ? risultato.getText()+"," : risultato.getText()+"0,");
            decimalPointCount = 1;
            voidFlag = false;
            resultLastOp = false;
            exception = false;
        }
    }//GEN-LAST:event_virgolaDecimaleActionPerformed

    private void segnoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_segnoActionPerformed
        if(!exception){
            String temp = risultato.getText();
            boolean composto = true;
            if(temp.charAt(0) == '-' && resultLastOp){
                temp = "(0" + temp + ")";
                lastOperatorPosition = 2;
                sign = true;
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
                    risultato.setText(temp.substring(0, temp.indexOf("(-")) + temp.substring(temp.indexOf("(-")+2, temp.length()-1));
                    sign = false;
                }else{
                    if(temp.charAt(temp.length()-1) == ','){
                        risultato.setText("(-" + temp.substring(0, temp.length()-1) + ")");
                        decimalPointCount = 0;
                    if(risultato.getText().equals("(-0)"))
                        voidFlag = true;
                    }else
                        risultato.setText(temp.charAt(temp.length()-1) == '(' ? "(-" + temp + "0)" : "(-" + temp + ")");
                        sign = true;
                }
                resultLastOp = false;
            }else{
                if(temp.length()-1 > lastOperatorPosition){
                    String corrente = temp.substring(lastOperatorPosition+1);
                    if(corrente.contains("(-")){
                        if(resultLastOp)
                            risultato.setText(temp.substring(4, temp.length()-2));
                        else
                            risultato.setText(temp.substring(0, lastOperatorPosition+1) + corrente.substring(2, corrente.length()-1));
                        sign = false;
                    }else{
                        if(corrente.charAt(corrente.length()-1) == ','){
                            risultato.setText(temp.substring(0, lastOperatorPosition+1) + "(-" + corrente.substring(0, corrente.length()-1) + ")");
                            decimalPointCount = 0;
                        }else
                            risultato.setText(temp.substring(0, lastOperatorPosition+1) + "(-" + corrente + ")");
                            sign = true;
                    }
                    resultLastOp = false;
                }
            }
        }
    }//GEN-LAST:event_segnoActionPerformed

    private void parentesiApertaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_parentesiApertaActionPerformed
        String temp = risultato.getText();
        if(isOperator(temp.charAt(temp.length()-1)) || temp.charAt(temp.length()-1) == '(' || voidFlag || exception){
            risultato.setText(voidFlag ? "(" : temp+"(");
            openBrackets++;
            voidFlag = false;
            resultLastOp = false;
            exception = false;
        }
    }//GEN-LAST:event_parentesiApertaActionPerformed

    private void parentesiChiusaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_parentesiChiusaActionPerformed
        String temp = risultato.getText();
        if(openBrackets > 0){
            if(temp.equals("(")){
                risultato.setText("(0)");
                voidFlag = true;
            }else if(temp.charAt(temp.length()-1) == ','){
                risultato.setText(temp.substring(0, temp.length()-1) + ")");
                decimalPointCount = 0;
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
                            risultato.setText(count > 1 ? temp + temp.substring(i+1, temp.length()-1) + ")": 
                                    temp + temp.substring(i+1, temp.length()-1) + ")");
                            break;
                    }
                }
            }else
                risultato.setText(temp.charAt(temp.length()-1) == '(' ? temp + "0)" : temp + ")");
            openBrackets--;
        }
    }//GEN-LAST:event_parentesiChiusaActionPerformed
    private void numberAction(char num){
        if(risultato.getText().charAt(risultato.getText().length()-1) != ')' || voidFlag){
            String temp = risultato.getText();
            if(!voidFlag && temp.charAt(temp.length()-1) == '0' && isOperator(temp.charAt(temp.length()-2)))
                risultato.setText(temp.substring(0, temp.length()-1) + num);
            else
                risultato.setText(voidFlag ? ""+num : risultato.getText()+num);
            voidFlag = false;
            resultLastOp = false;
            exception = false;
        }
    }
    private void operatorAction(char op){
        if(!exception){
            String tempString = risultato.getText();
            char tempChar = tempString.charAt(tempString.length()-1);
            if(tempString.indexOf('E') > -1)
                risultato.setText("0" + op);
            else if(isOperator(tempChar)){
                if(tempChar != op)
                    risultato.setText(tempString.substring(0, tempString.length()-1) + op);
            }else if(isDecimalPoint(tempChar))
                risultato.setText(tempString.substring(0, tempString.length()-1) + op);
            else
                risultato.setText(tempChar == '(' ? tempString + "0" + op : tempString + op);
            decimalPointCount = 0;
            voidFlag = false;
            lastOperatorPosition = risultato.getText().length()-1;
            resultLastOp = false;
            sign = false;
        }
    }
    private boolean isDecimalPoint(char character){
	return character == ',';
    }
    private boolean isOperator(char character){
	return character == '+' | character == '-' | character == '×'| character == '÷';
    }
    public static void main(String args[]){
        try{
            for(javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()){
                if("Nimbus".equals(info.getName())){
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        }catch(ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex){
            java.util.logging.Logger.getLogger(FrameCalcolatore_SWING.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        java.awt.EventQueue.invokeLater(() -> {
            new FrameCalcolatore_SWING().setVisible(true);
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addizione;
    private javax.swing.JButton calcolaRisultato;
    private javax.swing.JButton cancellaCorrente;
    private javax.swing.JButton cancellaTutto;
    private javax.swing.JButton cancellaVersoSinistra;
    private javax.swing.JButton divisione;
    private javax.swing.JButton moltiplicazione;
    private javax.swing.JButton numero_0;
    private javax.swing.JButton numero_1;
    private javax.swing.JButton numero_2;
    private javax.swing.JButton numero_3;
    private javax.swing.JButton numero_4;
    private javax.swing.JButton numero_5;
    private javax.swing.JButton numero_6;
    private javax.swing.JButton numero_7;
    private javax.swing.JButton numero_8;
    private javax.swing.JButton numero_9;
    private javax.swing.JButton parentesiAperta;
    private javax.swing.JButton parentesiChiusa;
    private javax.swing.JLabel risultato;
    private javax.swing.JButton segno;
    private javax.swing.JButton sottrazione;
    private javax.swing.JButton virgolaDecimale;
    // End of variables declaration//GEN-END:variables
}