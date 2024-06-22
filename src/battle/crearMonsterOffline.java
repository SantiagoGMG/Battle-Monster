
package battle;

import java.awt.Color;

public class crearMonsterOffline extends javax.swing.JFrame {
    private static int puntosTotales;
    public crearMonsterOffline(int puntos) {
        this.puntosTotales = puntos + puntosTotales;
        initComponents();
        labelPuntos.setText(String.valueOf(puntosTotales));
        setLocationRelativeTo(null);
        FondoInicio fondo = new FondoInicio(panelFondo, "/battle/imagenes/stats1.png");
        panelFondo.add(fondo).repaint();
        panelFondo.setOpaque(false);
        panelFondo.setBorder(null);
        panelFondo.setBackground(new Color(0, 0, 0, 0));
        
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelFondo = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        labelPuntos = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        evasion_mas = new javax.swing.JButton();
        evasion_menos = new javax.swing.JButton();
        labelEvasion = new javax.swing.JLabel();
        labelAtaque = new javax.swing.JLabel();
        ataque_menos = new javax.swing.JButton();
        ataque_mas = new javax.swing.JButton();
        vida_mas = new javax.swing.JButton();
        vida_menos = new javax.swing.JButton();
        labelVida = new javax.swing.JLabel();
        restablecerBoton = new javax.swing.JButton();
        siguienteBoton = new javax.swing.JButton();
        botonBack = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel5.setText("CREA TU MONSTER");

        jLabel4.setText("PUNTOS :");

        labelPuntos.setText("5");

        jLabel1.setText("Vida");

        jLabel2.setText("Ataque");

        jLabel3.setText("Evasion");

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

        labelEvasion.setText("10");

        labelAtaque.setText("1");

        ataque_menos.setText("-");
        ataque_menos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ataque_menosActionPerformed(evt);
            }
        });

        ataque_mas.setText("+");
        ataque_mas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ataque_masActionPerformed(evt);
            }
        });

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

        labelVida.setText("10");

        restablecerBoton.setText("RESTABLECER");
        restablecerBoton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                restablecerBotonActionPerformed(evt);
            }
        });

        siguienteBoton.setText("SIGUIENTE");
        siguienteBoton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                siguienteBotonActionPerformed(evt);
            }
        });

        botonBack.setText("BACK");
        botonBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonBackActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelFondoLayout = new javax.swing.GroupLayout(panelFondo);
        panelFondo.setLayout(panelFondoLayout);
        panelFondoLayout.setHorizontalGroup(
            panelFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelFondoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(botonBack)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 126, Short.MAX_VALUE)
                .addGroup(panelFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelFondoLayout.createSequentialGroup()
                        .addGroup(panelFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelFondoLayout.createSequentialGroup()
                                .addGroup(panelFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel2)
                                    .addGroup(panelFondoLayout.createSequentialGroup()
                                        .addGroup(panelFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel1)
                                            .addComponent(jLabel4))
                                        .addGap(10, 10, 10)))
                                .addGap(18, 18, 18)
                                .addGroup(panelFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(panelFondoLayout.createSequentialGroup()
                                        .addGroup(panelFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(panelFondoLayout.createSequentialGroup()
                                                .addComponent(vida_mas)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(vida_menos))
                                            .addGroup(panelFondoLayout.createSequentialGroup()
                                                .addComponent(ataque_mas)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(ataque_menos))
                                            .addGroup(panelFondoLayout.createSequentialGroup()
                                                .addComponent(evasion_mas)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(evasion_menos)))
                                        .addGap(26, 26, 26)
                                        .addGroup(panelFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(labelEvasion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(labelVida, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(labelAtaque, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                    .addComponent(labelPuntos, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelFondoLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(panelFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelFondoLayout.createSequentialGroup()
                                .addComponent(restablecerBoton)
                                .addGap(82, 82, 82))
                            .addComponent(siguienteBoton, javax.swing.GroupLayout.Alignment.TRAILING))))
                .addGap(111, 111, 111))
        );
        panelFondoLayout.setVerticalGroup(
            panelFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelFondoLayout.createSequentialGroup()
                .addGap(117, 117, 117)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(labelPuntos))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addGroup(panelFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(vida_mas)
                        .addComponent(vida_menos)
                        .addComponent(labelVida)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(ataque_menos)
                        .addComponent(labelAtaque))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(ataque_mas)
                        .addComponent(jLabel2)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(evasion_menos)
                        .addComponent(labelEvasion))
                    .addComponent(jLabel3)
                    .addComponent(evasion_mas))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(restablecerBoton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 70, Short.MAX_VALUE)
                .addComponent(siguienteBoton)
                .addGap(24, 24, 24))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelFondoLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(botonBack)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelFondo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelFondo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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

    private void vida_menosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_vida_menosActionPerformed
        int vida;
        vida = Integer.parseInt(labelVida.getText());
        int puntos;
        puntos = Integer.parseInt(labelPuntos.getText());
        if (puntos < 100 && vida > 2) {
            puntos = puntos + 1;
            vida = vida - 2;
            //seteamos los valores
            labelVida.setText(String.valueOf(vida));
            labelPuntos.setText(String.valueOf(puntos));
        }
    }//GEN-LAST:event_vida_menosActionPerformed

    private void ataque_menosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ataque_menosActionPerformed
        int atq;
        atq = Integer.parseInt(labelAtaque.getText());
        int puntos;
        puntos = Integer.parseInt(labelPuntos.getText());
        if (puntos < 100 && atq > 1) {
            puntos = puntos + 1;
            atq = atq - 1;
            //seteamos los valores
            labelAtaque.setText(String.valueOf(atq));
            labelPuntos.setText(String.valueOf(puntos));
        }
    }//GEN-LAST:event_ataque_menosActionPerformed

    private void botonBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonBackActionPerformed
        this.setVisible(false);
        Inicio inicio = new Inicio();
        inicio.setVisible(true);
    }//GEN-LAST:event_botonBackActionPerformed

    private void evasion_menosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_evasion_menosActionPerformed
        int evasion;
        evasion = Integer.parseInt(labelEvasion.getText());
        int puntos;
        puntos = Integer.parseInt(labelPuntos.getText());
        if (puntos < 100 && evasion > 0) {
            puntos = puntos + 1;
            evasion = evasion - 5;
            //seteamos los valores
            labelEvasion.setText(String.valueOf(evasion));
            labelPuntos.setText(String.valueOf(puntos));
        }
    }//GEN-LAST:event_evasion_menosActionPerformed

    private void restablecerBotonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_restablecerBotonActionPerformed

        labelVida.setText("10");
        labelAtaque.setText("1");
        labelEvasion.setText("10");
        labelPuntos.setText(String.valueOf(puntosTotales));
    }//GEN-LAST:event_restablecerBotonActionPerformed

    private void siguienteBotonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_siguienteBotonActionPerformed
        int vida;
        vida = Integer.parseInt(labelVida.getText());
        int evasion;
        evasion = Integer.parseInt(labelEvasion.getText());
        int atq;
        atq = Integer.parseInt(labelAtaque.getText());

        Monster monster = new Monster(vida, atq, evasion, "null");
        campoBatallaOffline batallaOffline = new campoBatallaOffline(monster);
        batallaOffline.setVisible(true);
        //elegirMonster elegirImagen = new elegirMonster();
        //elegirImagen.setVisible(true);
        /*CampoBatalla pelear = new CampoBatalla(out,in, monster);
        pelear.setVisible(true);*/
        this.setVisible(false);
    }//GEN-LAST:event_siguienteBotonActionPerformed

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
            java.util.logging.Logger.getLogger(crearMonsterOffline.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(crearMonsterOffline.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(crearMonsterOffline.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(crearMonsterOffline.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new crearMonsterOffline(puntosTotales).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ataque_mas;
    private javax.swing.JButton ataque_menos;
    private javax.swing.JButton botonBack;
    private javax.swing.JButton evasion_mas;
    private javax.swing.JButton evasion_menos;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel labelAtaque;
    private javax.swing.JLabel labelEvasion;
    private javax.swing.JLabel labelPuntos;
    private javax.swing.JLabel labelVida;
    private javax.swing.JPanel panelFondo;
    private javax.swing.JButton restablecerBoton;
    private javax.swing.JButton siguienteBoton;
    private javax.swing.JButton vida_mas;
    private javax.swing.JButton vida_menos;
    // End of variables declaration//GEN-END:variables
}
