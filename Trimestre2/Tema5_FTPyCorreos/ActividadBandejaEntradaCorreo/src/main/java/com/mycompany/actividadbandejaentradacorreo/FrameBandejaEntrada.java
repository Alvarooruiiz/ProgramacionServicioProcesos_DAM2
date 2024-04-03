package com.mycompany.actividadbandejaentradacorreo;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Properties;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Store;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

/**
 *
 * @author alvar
 */
public class FrameBandejaEntrada extends javax.swing.JFrame implements ActionListener {

    private Session session;
    private Store store;

    public FrameBandejaEntrada() {
        initComponents();
        cargarCarpetas();
        comboCarpetas.addActionListener(this);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        comboCarpetas = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        taBandeja = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel1.setText("GESTOR BUZON ENTRADA");

        comboCarpetas.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        comboCarpetas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboCarpetasActionPerformed(evt);
            }
        });

        taBandeja.setColumns(20);
        taBandeja.setRows(5);
        jScrollPane1.setViewportView(taBandeja);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(comboCarpetas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(128, 128, 128)
                            .addComponent(jLabel1))
                        .addGroup(layout.createSequentialGroup()
                            .addGap(28, 28, 28)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 332, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(40, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(17, 17, 17)
                .addComponent(comboCarpetas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(35, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void comboCarpetasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboCarpetasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboCarpetasActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FrameBandejaEntrada.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrameBandejaEntrada.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrameBandejaEntrada.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrameBandejaEntrada.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new FrameBandejaEntrada().setVisible(true);
            }
        });
    }

    
    private void cargarCarpetas() {
        try {
            Properties props = new Properties();
            props.setProperty("mail.store.protocol", "imaps");

            session = Session.getInstance(props, null);
            store = session.getStore();
            store.connect("imap.gmail.com", "alvaro.ruiz.enrique@gmail.com", "");

            Folder bandejaEntrada = store.getDefaultFolder();
            Folder[] carpetas = bandejaEntrada.list("*");
            comboCarpetas.removeAllItems();

            for (Folder carpeta : carpetas) {
                comboCarpetas.addItem(obtenerNombresDeCarpetas(carpeta.getName()));
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error cargando carpetas.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private String obtenerNombresDeCarpetas(String nombreCarpeta) {
        switch (nombreCarpeta) {
            case "Recibidos":
                return "INBOX";
            case "Destacados":
                return "[Gmail]/Destacados";
            case "Pospuestos":
                return "[Gmail]/Pospuestos";
            case "Importantes":
                return "[Gmail]/Importantes";
            case "Enviados":
                return "[Gmail]/Enviados";
            case "Borradores":
                return "[Gmail]/Borradores";
            case "Papelera":
                return "[Gmail]/Papelera";
            case "Spam":
                return "[Gmail]/Spam";
            case "Todos":
                return "[Gmail]/Todos";
            default:
                return nombreCarpeta;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            String nombreCarpeta = (String) comboCarpetas.getSelectedItem();
            Folder carpeta = store.getFolder(obtenerNombresDeCarpetas(nombreCarpeta));
            carpeta.open(Folder.READ_ONLY);

            Message[] mensajes = carpeta.getMessages();
            StringBuilder contenidoMensajes = new StringBuilder();
            for (Message mensaje : mensajes) {
                contenidoMensajes.append("De: ").append(Arrays.toString(mensaje.getFrom())).append("\n");
                contenidoMensajes.append("Asunto: ").append(mensaje.getSubject()).append("\n");
                contenidoMensajes.append("Fecha: ").append(mensaje.getSentDate()).append("\n\n");
            }

            taBandeja.setText(contenidoMensajes.toString());
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error cargando mensajes de la carpeta.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> comboCarpetas;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea taBandeja;
    // End of variables declaration//GEN-END:variables
}
