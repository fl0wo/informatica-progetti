package scorritore_pila_swing;
import java.awt.Color;
public class FrameScorritorePila extends javax.swing.JFrame{
    private Pila pila;
    private Iterator iterator;
    public FrameScorritorePila(){
        initComponents();
        pila = new Pila();
        iterator = pila.iterator();
        top.setBackground(Color.lightGray);
        top.setOpaque(true);
        isEmpty.setBackground(Color.lightGray);
        isEmpty.setOpaque(true);
        pilaLength.setText(pilaLength.getText() + "0");
        setSize(370, 290);
        setResizable(false);
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pilaLength = new javax.swing.JLabel();
        argPush = new javax.swing.JTextField();
        push = new javax.swing.JButton();
        pop = new javax.swing.JButton();
        sceltaElementoCorrente = new javax.swing.JComboBox();
        top = new javax.swing.JLabel();
        isEmpty = new javax.swing.JLabel();
        separatoreGrafico = new javax.swing.JLabel();
        elementoTop = new javax.swing.JButton();
        elementoSuccessivo = new javax.swing.JButton();
        elementoCorrente = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Scorritore_Pila");
        setBounds(new java.awt.Rectangle(0, 0, 370, 260));
        setResizable(false);
        getContentPane().setLayout(null);

        pilaLength.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        pilaLength.setText("length: ");
        getContentPane().add(pilaLength);
        pilaLength.setBounds(30, 20, 100, 40);
        pilaLength.getAccessibleContext().setAccessibleName("");

        argPush.setPreferredSize(new java.awt.Dimension(80, 40));
        getContentPane().add(argPush);
        argPush.setBounds(150, 20, 80, 40);

        push.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        push.setText("push");
        push.setPreferredSize(new java.awt.Dimension(80, 40));
        push.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pushActionPerformed(evt);
            }
        });
        getContentPane().add(push);
        push.setBounds(270, 20, 80, 40);

        pop.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        pop.setText("pop");
        pop.setPreferredSize(new java.awt.Dimension(80, 40));
        pop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                popActionPerformed(evt);
            }
        });
        getContentPane().add(pop);
        pop.setBounds(270, 80, 80, 40);

        sceltaElementoCorrente.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        sceltaElementoCorrente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sceltaElementoCorrenteActionPerformed(evt);
            }
        });
        getContentPane().add(sceltaElementoCorrente);
        sceltaElementoCorrente.setBounds(270, 140, 80, 23);

        top.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        getContentPane().add(top);
        top.setBounds(30, 80, 200, 40);

        isEmpty.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        isEmpty.setText(" IS EMPTY");
        getContentPane().add(isEmpty);
        isEmpty.setBounds(30, 130, 80, 40);

        separatoreGrafico.setText("- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -");
        getContentPane().add(separatoreGrafico);
        separatoreGrafico.setBounds(30, 180, 320, 14);

        elementoTop.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        elementoTop.setText("<<");
        elementoTop.setPreferredSize(new java.awt.Dimension(90, 40));
        elementoTop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                elementoTopActionPerformed(evt);
            }
        });
        getContentPane().add(elementoTop);
        elementoTop.setBounds(30, 200, 90, 40);
        elementoTop.getAccessibleContext().setAccessibleName("");

        elementoSuccessivo.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        elementoSuccessivo.setText(">");
        elementoSuccessivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                elementoSuccessivoActionPerformed(evt);
            }
        });
        getContentPane().add(elementoSuccessivo);
        elementoSuccessivo.setBounds(260, 200, 90, 40);

        elementoCorrente.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        elementoCorrente.setText("current: ");
        getContentPane().add(elementoCorrente);
        elementoCorrente.setBounds(130, 200, 120, 40);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void pushActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pushActionPerformed
        if(!argPush.getText().equals("")){
            pila.push(argPush.getText());
            iterator = pila.iterator();
            elementoCorrente.setText("current: "+iterator.current());
            top.setText(pila.isEmpty() ? "" : ("top: ")+pila.top());
            isEmpty.setText(pila.isEmpty() ? "IS EMPTY" : "");
            pilaLength.setText("length: "+pila.length());
            updateChoice();
        }
    }//GEN-LAST:event_pushActionPerformed

    private void sceltaElementoCorrenteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sceltaElementoCorrenteActionPerformed
        iterator = pila.iterator();
        while(iterator.inside()){
            if(iterator.current().toString().equals(String.valueOf(sceltaElementoCorrente.getSelectedItem()))){
                elementoCorrente.setText("current: "+iterator.current());
                break;
            }
            iterator.goNext();
        }
    }//GEN-LAST:event_sceltaElementoCorrenteActionPerformed
    
    private void elementoTopActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_elementoTopActionPerformed
        try{
            iterator.goFirst();
            elementoCorrente.setText("current: "+iterator.current());
        }catch(NullPointerException ex){
            javax.swing.JOptionPane.showMessageDialog(null, "ERRORE: PILA VUOTA");
            dispose();
        }
    }//GEN-LAST:event_elementoTopActionPerformed
    
    private void elementoSuccessivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_elementoSuccessivoActionPerformed
        try{
            iterator.goNext();
            elementoCorrente.setText("current: "+iterator.current());
        }catch(NullPointerException ex){
            javax.swing.JOptionPane.showMessageDialog(null, "ERRORE: SEI USCITO DALLA PILA");
            dispose();
        }
    }//GEN-LAST:event_elementoSuccessivoActionPerformed

    private void popActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_popActionPerformed
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
    }//GEN-LAST:event_popActionPerformed
    private void updateChoice(){
        Iterator temp = pila.iterator();
        sceltaElementoCorrente.removeAllItems();
        while(temp.inside()){
            sceltaElementoCorrente.addItem(""+temp.current());
            temp.goNext();
        }
    }
    public static void main(String args[]){
	java.awt.EventQueue.invokeLater(() -> {
            new FrameScorritorePila().setVisible(true);
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField argPush;
    private javax.swing.JLabel elementoCorrente;
    private javax.swing.JButton elementoSuccessivo;
    private javax.swing.JButton elementoTop;
    private javax.swing.JLabel isEmpty;
    private javax.swing.JLabel pilaLength;
    private javax.swing.JButton pop;
    private javax.swing.JButton push;
    private javax.swing.JComboBox sceltaElementoCorrente;
    private javax.swing.JLabel separatoreGrafico;
    private javax.swing.JLabel top;
    // End of variables declaration//GEN-END:variables
}