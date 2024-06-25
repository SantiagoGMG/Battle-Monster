package battle;

import static battle.CampoBatalla.in;
import static battle.CampoBatalla.monster;
import static battle.CampoBatalla.out;
import java.awt.Color;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class elegirMonster extends javax.swing.JFrame {

    public static DataOutputStream out;
    public static DataInputStream in;
    public static String imagen;
    public int vida, atq, evasion;
    static String eresServidor;
    static ServerSocket servidor;
//Monster monster = new Monster(vida,atq,evasion,"null");
    public elegirMonster(DataOutputStream out, DataInputStream in, Monster monster,ServerSocket servidor, String eresServidor) {
        initComponents();
        lucharBoton.setVisible(false);
        this.out = out;
        this.in = in;
        this.imagen = monster.getImagen();
        this.vida = monster.getVida();
        this.atq = monster.getAtq();
        this.evasion = monster.getEvasion();
        this.eresServidor = eresServidor;
        this.servidor = servidor;
        setLocationRelativeTo(null);
        FondoInicio fondo = new FondoInicio(jPanel1, "/battle/imagenes/campo.png");
        jPanel1.add(fondo).repaint();
        jPanel1.setOpaque(false);
        jPanel1.setBorder(null);
        jPanel1.setBackground(new Color(0, 0, 0, 0));

        setImage(monster1, "/battle/imagenes/monster/monster1.png");
        setImage(monster2, "/battle/imagenes/monster/monster2.png");
        setImage(monster3, "/battle/imagenes/monster/monster3.png");
        setImage(monster4, "/battle/imagenes/monster/monster4.png");
        setImage(monster5, "/battle/imagenes/monster/monster5.png");
        mosnter1();
        mosnter2();
        mosnter3();
        mosnter4();
        mosnter5();
    }

    public void mosnter1() {
        monster1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                imagen = "/battle/imagenes/monster/monster1.png";
                lucharBoton.setVisible(true);
            }
        });
    }

    public void mosnter2() {
        monster2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                imagen = "/battle/imagenes/monster/monster2.png";
                lucharBoton.setVisible(true);
            }
        });
    }

    public void mosnter3() {
        monster3.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                imagen = "/battle/imagenes/monster/monster3.png";
                lucharBoton.setVisible(true);
            }
        });
    }

    public void mosnter4() {
        monster4.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                imagen = "/battle/imagenes/monster/monster4.png";
                lucharBoton.setVisible(true);
            }
        });
    }

    public void mosnter5() {
        monster5.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                imagen = "/battle/imagenes/monster/monster5.png";

                lucharBoton.setVisible(true);
            }
        });
    }

    public void setImage(JLabel labelName, String ruta) {
        ImageIcon image = new ImageIcon(getClass().getResource(ruta));
        Icon icon = new ImageIcon(
                image.getImage().getScaledInstance(labelName.getWidth(), labelName.getHeight(), Image.SCALE_DEFAULT));
        labelName.setIcon(icon);
        this.repaint();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        monster2 = new javax.swing.JLabel();
        monster3 = new javax.swing.JLabel();
        monster4 = new javax.swing.JLabel();
        monster1 = new javax.swing.JLabel();
        monster5 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        lucharBoton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel1.setText("CHOOSE YOUR MONSTER");

        lucharBoton.setText("FIGHT !!!");
        lucharBoton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lucharBotonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 110, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(lucharBoton)
                        .addGap(50, 50, 50))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(73, 73, 73)
                                .addComponent(monster4, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(52, 52, 52)
                                .addComponent(monster5, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(monster1, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(48, 48, 48)
                                .addComponent(monster2, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(55, 55, 55)
                                .addComponent(monster3, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(84, 84, 84))))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(160, 160, 160)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 41, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(monster3, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(monster2, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(monster1, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(32, 32, 32)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(monster4, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(monster5, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addComponent(lucharBoton)
                .addGap(17, 17, 17))
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

    private void lucharBotonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lucharBotonActionPerformed
        Monster monster = new Monster(vida,atq,evasion,imagen);
        CampoBatalla pelear = new CampoBatalla(out, in, monster,servidor,eresServidor);
        pelear.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_lucharBotonActionPerformed

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
            java.util.logging.Logger.getLogger(elegirMonster.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(elegirMonster.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(elegirMonster.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(elegirMonster.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new elegirMonster(out, in, monster,servidor,eresServidor).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JButton lucharBoton;
    private javax.swing.JLabel monster1;
    private javax.swing.JLabel monster2;
    private javax.swing.JLabel monster3;
    private javax.swing.JLabel monster4;
    private javax.swing.JLabel monster5;
    // End of variables declaration//GEN-END:variables
}
