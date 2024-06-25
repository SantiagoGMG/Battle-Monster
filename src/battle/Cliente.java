package battle;

import java.awt.Color;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ConnectException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class Cliente extends javax.swing.JFrame {

    int PUERTO;
    DataInputStream in;
    DataOutputStream out;
    Socket socket;
    EscucharServer red;
    //private static Server server = new Server();
    ServerSocket servidor;
    public class EscucharServer extends Thread {

        @Override
        public void run() {
            /*while (true) {
                try {
                    String mensaje = in.readUTF();
                    System.out.println("El oponente uso: "+mensaje);
                    if(mensaje != null)
                    JOptionPane.showMessageDialog(null, "El oponente uso: " + mensaje);
                    mensaje="";
                } catch (SocketException ex) {
                    break;
                } catch (IOException ex) {
                    Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
                    break;
                }
            }*/

        }
    }

    public Cliente() {
        initComponents();
        setLocationRelativeTo(null);
        FondoInicio fondo = new FondoInicio(jPanel1, "/battle/imagenes/ingresar.png");
        jPanel1.add(fondo).repaint();
        jPanel1.setOpaque(false);
        jPanel1.setBorder(null);
        jPanel1.setBackground(new Color(0, 0, 0, 0));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        textIP = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        textPuerto = new javax.swing.JTextField();
        conectarBoton = new javax.swing.JButton();
        atrasBoton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("IP:");

        textIP.setText("192.168.1.116");
        textIP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textIPActionPerformed(evt);
            }
        });

        jLabel2.setText("Port:");

        textPuerto.setText("5000");

        conectarBoton.setText("Connect");
        conectarBoton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                conectarBotonActionPerformed(evt);
            }
        });

        atrasBoton.setText("BACK");
        atrasBoton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                atrasBotonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(189, 189, 189)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(textIP, javax.swing.GroupLayout.DEFAULT_SIZE, 125, Short.MAX_VALUE)
                            .addComponent(textPuerto)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(atrasBoton))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(254, 254, 254)
                        .addComponent(conectarBoton)))
                .addContainerGap(243, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(144, 144, 144)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(textIP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(textPuerto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(conectarBoton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 136, Short.MAX_VALUE)
                .addComponent(atrasBoton)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void textIPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textIPActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textIPActionPerformed

    private void conectarBotonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_conectarBotonActionPerformed
        String IP = textIP.getText();
        PUERTO = Integer.parseInt(textPuerto.getText());

        try { //Crea el socket
            socket = new Socket(IP, PUERTO);

            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());

            red = new EscucharServer();
            red.start();

        } catch (ConnectException ex) {
            JOptionPane.showMessageDialog(null, "Failed connection");
        } catch (IOException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (socket != null) {
            boolean bandera = true;
            crearMonster poke = new crearMonster(out, in,servidor,"0");
            poke.setVisible(true);
            this.setVisible(false);
        }

    }//GEN-LAST:event_conectarBotonActionPerformed

    private void atrasBotonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_atrasBotonActionPerformed
        Inicio volverInicio = new Inicio();
        volverInicio.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_atrasBotonActionPerformed

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
            java.util.logging.Logger.getLogger(Cliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Cliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Cliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Cliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Cliente().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton atrasBoton;
    private javax.swing.JButton conectarBoton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField textIP;
    private javax.swing.JTextField textPuerto;
    // End of variables declaration//GEN-END:variables
}
