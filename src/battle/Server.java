package battle;

import java.awt.Color;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class Server extends javax.swing.JFrame {

    int PUERTO;
    ServerSocket servidor;
    Socket socket;
    DataInputStream in;
    DataOutputStream out;
    //StoppableTask task = new StoppableTask();
    EscucharRed red = new EscucharRed();
    static boolean bandera;
    //private static Server server = new Server();

    public Server() {
        initComponents();
        setLocationRelativeTo(null);
        FondoInicio fondo = new FondoInicio(jPanel1, "/battle/imagenes/ingresar.png");
        jPanel1.add(fondo).repaint();
        jPanel1.setOpaque(false);
        jPanel1.setBorder(null);
        jPanel1.setBackground(new Color(0, 0, 0, 0));
        comenzarBoton.setVisible(false);

        //Obtener la IP de la maquina
        try {
            InetAddress direccionIP = InetAddress.getLocalHost();

            labelIP.setText(direccionIP.getHostAddress());
        } catch (UnknownHostException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void detenerEscucharRed() {
        if (red != null) {
            try {
                servidor.close();
            } catch (IOException ex) {
                Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
            }
            red.alto();
        }
    }

    public class EscucharRed extends Thread {

        @Override
        public void run() {
            while (bandera == true) {
                try {
                    socket = servidor.accept(); //Espera a que un cliente se conecte y almacena su socket
                    JOptionPane.showMessageDialog(null, "Tu rival se ha conectado");
                    comenzarBoton.setVisible(true);
                    in = new DataInputStream(socket.getInputStream());
                    out = new DataOutputStream(socket.getOutputStream());
                    
                } catch (EOFException ex) //El cliente se desconecto
                {
                    JOptionPane.showMessageDialog(null, "El cliente se desconectó");
                    break;
                } catch (SocketException ex) {
                    
                } catch (Exception ex) {
                    Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
                }
            } //Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
            // Limpieza de recursos
            try {
                if (socket != null && !socket.isClosed()) {
                    socket.close();
                }
                if (servidor != null && !servidor.isClosed()) {
                    servidor.close();
                }
            } catch (IOException e) {
                Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, e);
            }
        }

        public void alto() {
            bandera = false;
            try {
                if (servidor != null && !servidor.isClosed()) {
                    servidor.close(); // Cerrar el servidor para liberar el bloqueo en accept()
                    System.out.println("se cerro");
                }
            } catch (IOException e) {
                Logger.getLogger(EscucharRed.class.getName()).log(Level.SEVERE, null, e);
            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        labelIP = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        comenzarBoton = new javax.swing.JButton();
        textPuerto = new javax.swing.JTextField();
        iniciarBoton = new javax.swing.JButton();
        atrasBoton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("IP:");

        jLabel2.setText("Puerto:");

        comenzarBoton.setText("Comenzar");
        comenzarBoton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comenzarBotonActionPerformed(evt);
            }
        });

        textPuerto.setText("5000");
        textPuerto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textPuertoActionPerformed(evt);
            }
        });

        iniciarBoton.setText("Iniciar server");
        iniciarBoton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                iniciarBotonActionPerformed(evt);
            }
        });

        atrasBoton.setText("Atras");
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
                        .addGap(195, 195, 195)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(jLabel2)
                                    .addGap(18, 18, 18)
                                    .addComponent(textPuerto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(jLabel1)
                                    .addGap(18, 18, 18)
                                    .addComponent(labelIP, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(iniciarBoton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(comenzarBoton))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(atrasBoton)))
                .addContainerGap(219, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(123, 123, 123)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(labelIP, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(textPuerto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(comenzarBoton)
                    .addComponent(iniciarBoton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 151, Short.MAX_VALUE)
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

    private void comenzarBotonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comenzarBotonActionPerformed
        crearMonster poke = new crearMonster(out, in, servidor, "1");
        poke.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_comenzarBotonActionPerformed

    private void textPuertoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textPuertoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textPuertoActionPerformed

    private void iniciarBotonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_iniciarBotonActionPerformed
        PUERTO = Integer.parseInt(textPuerto.getText());
        try {
            servidor = new ServerSocket(PUERTO);

        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
        JOptionPane.showMessageDialog(null, "Servidor activado, espere al rival");
        bandera = true;
        red.start();

    }//GEN-LAST:event_iniciarBotonActionPerformed

    private void atrasBotonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_atrasBotonActionPerformed
        bandera = false;
        Inicio volverInicio = new Inicio();
        volverInicio.setVisible(true);
        this.setVisible(false);
        red.alto();
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
            java.util.logging.Logger.getLogger(Server.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Server.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Server.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Server.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Server().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton atrasBoton;
    private javax.swing.JButton comenzarBoton;
    private javax.swing.JButton iniciarBoton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel labelIP;
    private javax.swing.JTextField textPuerto;
    // End of variables declaration//GEN-END:variables
}
