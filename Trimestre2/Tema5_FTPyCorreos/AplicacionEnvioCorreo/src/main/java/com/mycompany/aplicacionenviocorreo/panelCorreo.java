package com.mycompany.aplicacionenviocorreo;

import jakarta.mail.MessagingException;
import java.io.IOException;
import javax.swing.JOptionPane;

public class panelCorreo extends javax.swing.JFrame {

    boolean enviarAdjunto = false;

    public panelCorreo() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTextField1 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        btnCerrar = new javax.swing.JButton();
        btnEnviar = new javax.swing.JButton();
        adjImagen = new javax.swing.JCheckBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        msjAEnviar = new javax.swing.JTextArea();
        correoDest = new javax.swing.JTextField();
        msjAsunto = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("CORREO ELECTRÓNICO");

        btnCerrar.setText("CERRAR");
        btnCerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCerrarActionPerformed(evt);
            }
        });

        btnEnviar.setText("ENVIAR");
        btnEnviar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEnviarActionPerformed(evt);
            }
        });

        adjImagen.setText("ADJUNTAR IMAGEN");
        adjImagen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                adjImagenActionPerformed(evt);
            }
        });

        msjAEnviar.setColumns(20);
        msjAEnviar.setRows(5);
        jScrollPane1.setViewportView(msjAEnviar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(138, 138, 138)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(msjAsunto, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(correoDest, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(adjImagen)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 88, Short.MAX_VALUE)
                        .addComponent(btnEnviar)
                        .addGap(18, 18, 18)
                        .addComponent(btnCerrar)))
                .addGap(17, 17, 17))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(correoDest, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(msjAsunto, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 9, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCerrar)
                    .addComponent(btnEnviar)
                    .addComponent(adjImagen))
                .addGap(17, 17, 17))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnEnviarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEnviarActionPerformed
        String correoDestinatario = this.correoDest.getText();
        String mensaje = this.msjAEnviar.getText();
        String asunto = this.msjAsunto.getText();

        String correoEmisor = "alvaro.ruiz.enrique@gmail.com";
        String passwordEmisor = "likt hjet gkav gteb";
        
        String rutaImagenAdjunta = "img.jpg";

        try {
            GestorEmail gestorEmail = new GestorEmail();
            
            if(enviarAdjunto){
                gestorEmail.enviarMensajeConAdjunto(correoEmisor, correoDestinatario, asunto, mensaje,
                    correoEmisor, passwordEmisor, rutaImagenAdjunta);
            }else{
                gestorEmail.enviarMensajeTexto(correoEmisor, correoDestinatario, asunto, mensaje, correoEmisor, passwordEmisor);
            }
            msjAEnviar.setText("");
            msjAsunto.setText("");
            correoDest.setText("");
            JOptionPane.showMessageDialog(this, "Correo enviado correctamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);

        } catch (IOException | MessagingException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al enviar el correo", "Error", JOptionPane.ERROR_MESSAGE);

        }
    }//GEN-LAST:event_btnEnviarActionPerformed

    private void adjImagenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_adjImagenActionPerformed
        if (adjImagen.isSelected()) {
            enviarAdjunto = true;
        } else if (!adjImagen.isSelected()) {
            enviarAdjunto = false;
        }
    }//GEN-LAST:event_adjImagenActionPerformed

    private void btnCerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCerrarActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnCerrarActionPerformed

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new panelCorreo().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox adjImagen;
    private javax.swing.JButton btnCerrar;
    private javax.swing.JButton btnEnviar;
    private javax.swing.JTextField correoDest;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextArea msjAEnviar;
    private javax.swing.JTextField msjAsunto;
    // End of variables declaration//GEN-END:variables
}
