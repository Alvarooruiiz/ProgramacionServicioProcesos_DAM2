/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.practicaapiconcoches;

import javax.swing.JTextArea;
import javax.swing.JViewport;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Catalogo extends javax.swing.JFrame {

    public Catalogo() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnVolver = new javax.swing.JButton();
        lbDesc = new javax.swing.JLabel();
        txtDesc = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btnVolver.setText("Volver");
        btnVolver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVolverActionPerformed(evt);
            }
        });

        lbDesc.setText("Descripción");

        jTextArea1.setEditable(false);
        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        txtDesc.setViewportView(jTextArea1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(168, 168, 168)
                .addComponent(lbDesc, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(16, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtDesc, javax.swing.GroupLayout.PREFERRED_SIZE, 365, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnVolver))
                .addGap(19, 19, 19))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(7, Short.MAX_VALUE)
                .addComponent(lbDesc, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtDesc, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnVolver)
                .addGap(14, 14, 14))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnVolverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVolverActionPerformed
        VentanaPrincipal vp = new VentanaPrincipal();
        vp.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnVolverActionPerformed

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            new Catalogo().setVisible(true);
        });
    }

    private String formatJSON(String rawJSON) {
        StringBuilder formattedJSON = new StringBuilder();

        try {
           
            JSONArray jsonArray  = new JSONArray(rawJSON);

            for (int i = 0; i < jsonArray .length(); i++) {
                JSONObject dataObject = jsonArray .getJSONObject(i);

                formattedJSON.append("-------------Criptos-------------\n");
                formattedJSON.append("ID: ").append(dataObject.getString("id")).append("\n");
                formattedJSON.append("Símbolo: ").append(dataObject.getString("symbol")).append(System.lineSeparator());
                formattedJSON.append("Nombre: ").append(dataObject.getString("name")).append("\n");
                formattedJSON.append("Precio más alto últomas 24 horas: ").append(dataObject.getDouble("high_24h")).append("€\n");
                formattedJSON.append("Precio más alto últomas 24 horas: ").append(dataObject.getDouble("low_24h")).append("€\n");
                formattedJSON.append("\n\n");
                
            }
        } catch (JSONException e) {
            e.printStackTrace();
            formattedJSON.append("Error al procesar el JSON: ").append(e.getMessage());
        }

        return formattedJSON.toString();
    }

    public void setInformacion(String info) {
    String formattedContent = formatJSON(info);

    JTextArea textArea = (JTextArea) ((JViewport) txtDesc.getComponent(0)).getView();
    textArea.setText(formattedContent);
}

    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnVolver;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JLabel lbDesc;
    private javax.swing.JScrollPane txtDesc;
    // End of variables declaration//GEN-END:variables
}
