package battle;

import java.awt.Color;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import javax.swing.JOptionPane;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
public class crearMonster extends javax.swing.JFrame {

    public static DataOutputStream out;
    public static DataInputStream in;
    //private static Server server = new Server();
    //private static Server.EscucharRed Red = server.new EscucharRed();
    static ServerSocket servidor;
    static boolean bandera;
    static String eresServidor;
    public crearMonster(DataOutputStream out, DataInputStream in, ServerSocket servidor,String eresServidor) {
        initComponents();
        this.setResizable(false);
        this.out = out;
        this.in = in;
        this.servidor = servidor;
        this.eresServidor=eresServidor;
        setLocationRelativeTo(null);
        FondoInicio fondo = new FondoInicio(jPanel1, "/battle/imagenes/stats1.png");
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
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        vida_mas = new javax.swing.JButton();
        vida_menos = new javax.swing.JButton();
        ataque_mas = new javax.swing.JButton();
        ataque_menos = new javax.swing.JButton();
        evasion_mas = new javax.swing.JButton();
        evasion_menos = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        labelPuntos = new javax.swing.JLabel();
        labelVida = new javax.swing.JLabel();
        labelAtaque = new javax.swing.JLabel();
        labelEvasion = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        restablecerBoton = new javax.swing.JButton();
        siguienteBoton = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("HP");

        jLabel2.setText("ATTACK");

        jLabel3.setText("EVASION");

        vida_mas.setText("+");
        vida_mas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                vida_masActionPerformed(evt);
            }
        });

        vida_menos.setText("-");
        vida_menos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                vida_menosActionPerformed(evt);
            }
        });

        ataque_mas.setText("+");
        ataque_mas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ataque_masActionPerformed(evt);
            }
        });

        ataque_menos.setText("-");
        ataque_menos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ataque_menosActionPerformed(evt);
            }
        });

        evasion_mas.setText("+");
        evasion_mas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                evasion_masActionPerformed(evt);
            }
        });

        evasion_menos.setText("-");
        evasion_menos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                evasion_menosActionPerformed(evt);
            }
        });

        jLabel4.setText("POINTS:");

        labelPuntos.setText("5");

        labelVida.setText("10");

        labelAtaque.setText("1");

        labelEvasion.setText("10");

        jLabel5.setText("CREATE YOUR MONSTER");

        restablecerBoton.setText("RESTORE");
        restablecerBoton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                restablecerBotonActionPerformed(evt);
            }
        });

        siguienteBoton.setText("NEXT");
        siguienteBoton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                siguienteBotonActionPerformed(evt);
            }
        });

        jButton1.setText("BACK");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(siguienteBoton)
                .addGap(16, 16, 16))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(203, 203, 203)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(restablecerBoton, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel2)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel1)
                                            .addComponent(jLabel4))
                                        .addGap(10, 10, 10)))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(vida_mas)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(vida_menos))
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(ataque_mas)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(ataque_menos))
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(evasion_mas)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(evasion_menos)))
                                        .addGap(26, 26, 26)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(labelEvasion)
                                            .addComponent(labelAtaque)
                                            .addComponent(labelVida)))
                                    .addComponent(labelPuntos, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 23, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(207, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(125, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(labelPuntos))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(vida_mas)
                                .addComponent(vida_menos)
                                .addComponent(labelVida)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(ataque_menos)
                                .addComponent(labelAtaque))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(ataque_mas)
                                .addComponent(jLabel2)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(evasion_menos)
                                .addComponent(labelEvasion))
                            .addComponent(jLabel3)
                            .addComponent(evasion_mas))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(restablecerBoton)
                        .addGap(72, 72, 72)
                        .addComponent(siguienteBoton)
                        .addGap(14, 14, 14))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addContainerGap())))
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

    private void vida_masActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_vida_masActionPerformed

        int vida;
        vida = Integer.parseInt(labelVida.getText());
        int puntos;
        puntos = Integer.parseInt(labelPuntos.getText());
        if (puntos > 0) {
            puntos = puntos - 1;
            vida = vida + 2;
            //seteamos los valores
            labelVida.setText(String.valueOf(vida));
            labelPuntos.setText(String.valueOf(puntos));
        }

    }//GEN-LAST:event_vida_masActionPerformed

    private void ataque_masActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ataque_masActionPerformed
        int atq;
        atq = Integer.parseInt(labelAtaque.getText());
        int puntos;
        puntos = Integer.parseInt(labelPuntos.getText());
        if (puntos > 0) {
            puntos = puntos - 1;
            atq = atq + 1;
            //seteamos los valores
            labelAtaque.setText(String.valueOf(atq));
            labelPuntos.setText(String.valueOf(puntos));
        }
    }//GEN-LAST:event_ataque_masActionPerformed

    private void ataque_menosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ataque_menosActionPerformed
        int atq;
        atq = Integer.parseInt(labelAtaque.getText());
        int puntos;
        puntos = Integer.parseInt(labelPuntos.getText());
        if (puntos < 13 && atq > 1) {
            puntos = puntos + 1;
            atq = atq - 1;
            //seteamos los valores
            labelAtaque.setText(String.valueOf(atq));
            labelPuntos.setText(String.valueOf(puntos));
        }
    }//GEN-LAST:event_ataque_menosActionPerformed

    private void evasion_masActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_evasion_masActionPerformed
        int evasion;
        evasion = Integer.parseInt(labelEvasion.getText());
        int puntos;
        puntos = Integer.parseInt(labelPuntos.getText());
        if (puntos > 0) {
            puntos = puntos - 1;
            evasion = evasion + 5;
            //seteamos los valores
            labelEvasion.setText(String.valueOf(evasion));
            labelPuntos.setText(String.valueOf(puntos));
        }
    }//GEN-LAST:event_evasion_masActionPerformed

    private void evasion_menosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_evasion_menosActionPerformed
        int evasion;
        evasion = Integer.parseInt(labelEvasion.getText());
        int puntos;
        puntos = Integer.parseInt(labelPuntos.getText());
        if (puntos < 13 && evasion > 0) {
            puntos = puntos + 1;
            evasion = evasion - 5;
            //seteamos los valores
            labelEvasion.setText(String.valueOf(evasion));
            labelPuntos.setText(String.valueOf(puntos));
        }
    }//GEN-LAST:event_evasion_menosActionPerformed

    private void vida_menosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_vida_menosActionPerformed
        int vida;
        vida = Integer.parseInt(labelVida.getText());
        int puntos;
        puntos = Integer.parseInt(labelPuntos.getText());
        if (puntos < 13 && vida > 2) {
            puntos = puntos + 1;
            vida = vida - 2;
            //seteamos los valores
            labelVida.setText(String.valueOf(vida));
            labelPuntos.setText(String.valueOf(puntos));
        }

    }//GEN-LAST:event_vida_menosActionPerformed

    private void restablecerBotonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_restablecerBotonActionPerformed

        labelVida.setText("10");
        labelAtaque.setText("1");
        labelEvasion.setText("10");
        labelPuntos.setText("5");
    }//GEN-LAST:event_restablecerBotonActionPerformed

    private void siguienteBotonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_siguienteBotonActionPerformed
        int vida;
        vida = Integer.parseInt(labelVida.getText());
        int evasion;
        evasion = Integer.parseInt(labelEvasion.getText());
        int atq;
        atq = Integer.parseInt(labelAtaque.getText());

        Monster monster = new Monster(vida, atq, evasion, "null");

        elegirMonster elegirImagen = new elegirMonster(out, in, monster,servidor,eresServidor);
        elegirImagen.setVisible(true);
        /*CampoBatalla pelear = new CampoBatalla(out,in, monster);
        pelear.setVisible(true);*/
        this.setVisible(false);
    }//GEN-LAST:event_siguienteBotonActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        this.setVisible(false);
        if(eresServidor == "1")
        {
                    try {
            //JOptionPane.showMessageDialog(null, "Haz perdido");
            servidor.close();
                        System.out.println("cerrado");
        } catch (IOException ex) {
            Logger.getLogger(crearMonster.class.getName()).log(Level.SEVERE, null, ex);
        }
        }else
        {
            
        }

        Inicio inicio = new Inicio();
        inicio.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(crearMonster.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(crearMonster.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(crearMonster.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(crearMonster.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new crearMonster(out, in, servidor,eresServidor).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ataque_mas;
    private javax.swing.JButton ataque_menos;
    private javax.swing.JButton evasion_mas;
    private javax.swing.JButton evasion_menos;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel labelAtaque;
    private javax.swing.JLabel labelEvasion;
    private javax.swing.JLabel labelPuntos;
    private javax.swing.JLabel labelVida;
    private javax.swing.JButton restablecerBoton;
    private javax.swing.JButton siguienteBoton;
    private javax.swing.JButton vida_mas;
    private javax.swing.JButton vida_menos;
    // End of variables declaration//GEN-END:variables
}
