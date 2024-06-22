package battle;

import static battle.CampoBatalla.in;
import static battle.CampoBatalla.out;
import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.Random;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.plaf.basic.BasicProgressBarUI;

public class campoBatallaOffline extends javax.swing.JFrame {

    private Random rand = new Random();
    static Monster monster;
    int vida, atq, evasion;
    String imagenMonster;
    private boolean seleccion = false;
    /*  Es una matriz que indica las debilidades de cada elemento, 
                piedra , papel o tijera 
        piedra    0      -1      1
        papel     1       0     -1
        tijera   -1       1      0
        donde 0 es empate, -1 pierde contra ese elemento, 1 ganas a ese elemento
     */
    private int[][] debilidades = {
        {0, -1, 1},
        {1, 0, -1},
        {-1, 1, 0},};
    //iniciamos un objeto elemento con cualquier valor, esto nos servirá despues para obtener los valores que envian los jugadores
    Elementos elementosMio = Elementos.valueOf("piedra");
    Elementos elementosOponente = Elementos.valueOf("piedra");
    private static int NoRival = 1;

    /*  Es una matriz que indica las debilidades de cada elemento, 
                    vida , atq , evasion 
        monster0    0       0      0
        monster1    12      3      0
        monster2    14      4      10
        monster3    20      6      20
        monster4    25      8      30
        monster5    30      10     35 
        monster6    40      15     70
        donde 0 es empate, -1 pierde contra ese elemento, 1 ganas a ese elemento
     */
    private int[][] rivalPC = {
        {0, 0, 0},
        {12, 3, 0},
        {14, 4, 10},
        {20, 6, 20},
        {25, 8, 30},
        {30, 10, 35},
        {40, 15, 70},};

    private String[] attacks = {"piedra", "papel", "tijera"};
    private int HPMaxRival = rivalPC[NoRival][0];

    public campoBatallaOffline(Monster monster, int NoRival) {
        initComponents();
        this.NoRival = NoRival;

        int vidaMax = monster.getVida();
        this.vida = monster.getVida();
        this.atq = monster.getAtq();
        this.evasion = monster.getEvasion();
        setLocationRelativeTo(null);
        FondoInicio fondo = new FondoInicio(panelFondo, "/battle/imagenes/campo de batalla.png");
        panelFondo.add(fondo).repaint();
        panelFondo.setOpaque(false);
        panelFondo.setBorder(null);
        panelFondo.setBackground(new Color(0, 0, 0, 0));

        setImage(labelMonster1, "/battle/imagenes/monster/monster5.png");
        setImage(labelMonster2, "/battle/imagenes/monster/monster" + NoRival + ".png");

        setImageElementosPPT();

        barActualizar(vidaMax, monster.getVida(), 0);

        actualizarBarOponente(rivalPC[NoRival][0], rivalPC[NoRival][0]);
        tijera(vidaMax, rivalPC[NoRival][1]);
        piedra(vidaMax, rivalPC[NoRival][1]);
        papel(vidaMax, rivalPC[NoRival][1]);
    }

    public void setImage(JLabel labelName, String ruta) {
        ImageIcon image = new ImageIcon(getClass().getResource(ruta));
        Icon icon = new ImageIcon(
                image.getImage().getScaledInstance(labelName.getWidth(), labelName.getHeight(), Image.SCALE_DEFAULT));
        labelName.setIcon(icon);
        this.repaint();
    }

    public void setImageElementosPPT() {
        setImage(labelPiedra, "/battle/imagenes/piedra_1.png");
        setImage(labelPapel, "/battle/imagenes/papel_1.png");
        setImage(labelTijera, "/battle/imagenes/tijera_1.png");
    }

    /*
        Nos ayuda a colorear el String que tiene el progress Bar
     */
    private static class CustomProgressBarUI extends BasicProgressBarUI {

        private final Color textColor;

        public CustomProgressBarUI(Color textColor) {
            this.textColor = textColor;
        }

        @Override
        protected void paintString(Graphics g, int x, int y, int width, int height, int amountFull, Insets b) {
            if (!(g instanceof Graphics2D)) {
                return;
            }

            Graphics2D g2d = (Graphics2D) g;
            String progressString = progressBar.getString();
            g2d.setFont(progressBar.getFont());
            g2d.setColor(textColor); // Usar el color de texto personalizado
            FontMetrics fontMetrics = g2d.getFontMetrics();
            int stringWidth = fontMetrics.stringWidth(progressString);
            int stringHeight = fontMetrics.getAscent();
            int stringX = (width - stringWidth) / 2;
            int stringY = (height + stringHeight) / 2;

            g2d.drawString(progressString, stringX, stringY);
        }
    }

    /*
       Actualiza nuestro progress bar con la vida actual que tenemos con respecto de nuestra vida maxima 
            este metodo es el encargado de asignar colores dependiendo de la vida actual 
        donde un 0.8 por encima de la vida maxima es de color verde
        un 0.4 por arriba es de color naranja
        y por debajo es de color rojo
     */
    public void barActualizar(int vidaMax, int vidaActual, int daño) {
        barVida.setMinimum(0);
        barVida.setMaximum(vidaMax);
        barVida.setStringPainted(true); // Mostrar el valor numérico en la barra
        vidaActual = vidaActual - daño;
        setVida(vidaActual);
        barVida.setString(String.valueOf(vidaActual));
        barVida.setValue(vidaActual); // Valor inicial de la barra de progreso
        barVida.setUI(new CustomProgressBarUI(Color.BLACK));
        double porcentajeVida = (double) vidaActual / vidaMax;
        if (porcentajeVida >= 0.8) {
            barVida.setForeground(Color.GREEN);
        }
        if (porcentajeVida >= 0.4 && porcentajeVida < 0.8) {
            barVida.setForeground(Color.ORANGE);
        }
        if (porcentajeVida < 0.4) {
            barVida.setForeground(Color.RED);
        }

    }

    public void actualizarBarOponente(int vidaMax, int vidaActual) {
        barVidaOponente.setMinimum(0);
        barVidaOponente.setMaximum(vidaMax);
        barVidaOponente.setStringPainted(true); // Mostrar el valor numérico en la barra
        barVidaOponente.setString(String.valueOf(vidaActual));
        barVidaOponente.setValue(vidaActual); // Valor inicial de la barra de progreso
        barVidaOponente.setUI(new CustomProgressBarUI(Color.BLACK));
        double porcentajeVida = (double) vidaActual / vidaMax;

        if (porcentajeVida > 0.8) {
            barVidaOponente.setForeground(Color.GREEN);
        } else if (porcentajeVida > 0.4) {
            barVidaOponente.setForeground(Color.ORANGE);
        } else {
            barVidaOponente.setForeground(Color.RED);
        }
    }

    public int getVida() {
        return vida;
    }

    public void setVida(int vida) {
        this.vida = vida;
    }

    public void tijera(int vidaMax, int ataqueOponente) {
        labelTijera.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                if (seleccion == false) {
                    setImage(labelTijera, "/battle/imagenes/tijeraOscuro.png");
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if (seleccion == false) {
                    setImage(labelTijera, "/battle/imagenes/tijera_1.png");
                }
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                if (seleccion == false) {
                    seleccion = true;
                    setImage(labelTijera, "/battle/imagenes/tijeraOscuro.png");
                    CalculoDelDuelo("tijera", vidaMax, ataqueOponente);

                }

            }
        }
        );
    }

    public void papel(int vidaMax, int ataqueOponente) {
        labelPapel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                if (seleccion == false) {
                    setImage(labelPapel, "/battle/imagenes/papelOscuro.png");
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if (seleccion == false) {
                    setImage(labelPapel, "/battle/imagenes/papel_1.png");
                }
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                if (seleccion == false) {
                    seleccion = true;
                    setImage(labelPapel, "/battle/imagenes/papelOscuro.png");
                    CalculoDelDuelo("papel", vidaMax, ataqueOponente);

                }

            }
        });
    }

    public void piedra(int vidaMax, int ataqueOponente) {
        labelPiedra.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                if (seleccion == false) {
                    setImage(labelPiedra, "/battle/imagenes/piedraOscuro.png");
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if (seleccion == false) {
                    setImage(labelPiedra, "/battle/imagenes/piedra_1.png");
                }
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                if (seleccion == false) {
                    seleccion = true;
                    setImage(labelPiedra, "/battle/imagenes/piedraOscuro.png");
                    CalculoDelDuelo("piedra", vidaMax, ataqueOponente);

                }

            }
        });
    }

    /*
        Verifica si esquivaste el ataque por medio de tu evasion
     */
    public String yoEsquive(int evasion) {
        int esquivar = rand.nextInt(100);
        if (esquivar < evasion) {
            return "1";
        } else {
            return "0";
        }
    }

    /*
        Este es el metodo que calcula si ganaste el duelo de PPT
        lo que hace es mandar la eleccion de tu ataque y recibe la eleccion de tu rival
        llama el metodo de yoEsquive si verifica si esquivaste y lo manda al rival y recibe si el rival esquivo
        si uno de los dos esquivo no se efectua un calculo de daño, en caso contrario si los dos no esquivaron 
        se hace el calculo de daño en donde tu eleccion es x e y es la del rival,
        se verifica en la matriz de debilidades y si es un -1 significa que perdiste el duelo y por ende recibes 
        daño al igual al ataque de tu oponente y es un 1 ganaste al rival y si es 0 es que ambos usaron el mismo ataque
        en cada caso se usa el metodo enviarDatos para actualizar su progress bar de vida y verificar si alguien gano
     */
    public void CalculoDelDuelo(String miEleccion, int vidaMax, int ataqueOponente) {
        int rivalAtkRandom = rand.nextInt(3);
        String rivalAttacks = attacks[rivalAtkRandom];

        new Thread(() -> {
            //out.writeUTF(miEleccion);
            //panelEsperar.setVisible(true);
            //String oponenteEleccion = in.readUTF();

            String esquivar = yoEsquive(evasion);
            String dodgeRival = yoEsquive(rivalPC[NoRival][2]);
            //out.writeUTF(esquivar);
            //String oponenteEsquivar = in.readUTF();

            if (esquivar.equals("0") && dodgeRival.equals("0")) {

                int mio = elementosMio.valueOf(miEleccion).getValor();
                int oponente = elementosOponente.valueOf(rivalAttacks).getValor();
                int parametro = debilidades[mio][oponente];

                SwingUtilities.invokeLater(() -> {
                    switch (parametro) {
                        case 1: {
                            //enviarDatos(vidaMax);
                            JOptionPane.showMessageDialog(null, "El oponente uso: " + rivalAttacks + " le inflijiste " + atq + " de daño");
                            rivalPC[NoRival][0] = rivalPC[NoRival][0] - atq;
                            actualizarBarOponente(HPMaxRival, rivalPC[NoRival][0]);
                            verificarSiGanaste(vida, rivalPC[NoRival][0]);
                            break;
                        }
                        case -1: {
                            barActualizar(vidaMax, getVida(), ataqueOponente);
                            JOptionPane.showMessageDialog(null, "El oponente uso: " + rivalAttacks + " y te infligio " + ataqueOponente + " de daño");
                            verificarSiGanaste(vida, rivalPC[NoRival][0]);
                            break;
                        }
                        case 0: {
                            JOptionPane.showMessageDialog(null, "El oponente uso: " + rivalAttacks + " hubo un empate");
                            break;
                        }
                    }
                });
            } else if (esquivar.equals("1")) {
                //enviarDatos(vidaMax);
                JOptionPane.showMessageDialog(null, "El oponente uso: " + rivalAttacks + " pero lo esquivaste :)");

            } else if (dodgeRival.equals("1")) {
                //enviarDatos(vidaMax);
                JOptionPane.showMessageDialog(null, "El oponente uso: " + rivalAttacks + " pero lo esquivo :c");

            }

            seleccion = false;
            setImageElementosPPT();

        }).start();
    }

    public void verificarSiGanaste(int vida, int HPRival) {
        if (vida <= 0) {
            JOptionPane.showMessageDialog(null, "You LOSE ");
            Inicio inicio = new Inicio();
            inicio.setVisible(true);
            this.setVisible(false);
            
        }
        if (HPRival <= 0) {
            
            if (NoRival != 6) {
                JOptionPane.showMessageDialog(null, "You WIN, preparate para tu proxima batalla");
                NoRival = NoRival + 1;
                crearMonsterOffline crearOffline = new crearMonsterOffline(3, NoRival);
                crearOffline.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, "Congratulations, you defeated all the Monster");
                this.dispose();
            }

            this.setVisible(false);
        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelFondo = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        barVida = new javax.swing.JProgressBar();
        barVidaOponente = new javax.swing.JProgressBar();
        labelTijera = new javax.swing.JLabel();
        labelPapel = new javax.swing.JLabel();
        labelPiedra = new javax.swing.JLabel();
        labelMonster2 = new javax.swing.JLabel();
        labelMonster1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel2.setOpaque(false);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(barVida, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 137, Short.MAX_VALUE)
                .addComponent(barVidaOponente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(barVida, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(barVidaOponente, javax.swing.GroupLayout.DEFAULT_SIZE, 16, Short.MAX_VALUE))
                .addContainerGap())
        );

        labelTijera.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                labelTijeraAncestorAdded(evt);
            }
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });

        labelPapel.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                labelPapelAncestorAdded(evt);
            }
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });

        labelPiedra.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                labelPiedraAncestorAdded(evt);
            }
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });

        javax.swing.GroupLayout panelFondoLayout = new javax.swing.GroupLayout(panelFondo);
        panelFondo.setLayout(panelFondoLayout);
        panelFondoLayout.setHorizontalGroup(
            panelFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelFondoLayout.createSequentialGroup()
                .addGap(107, 107, 107)
                .addComponent(labelMonster1, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(213, 213, 213)
                .addComponent(labelMonster2, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(115, Short.MAX_VALUE))
            .addGroup(panelFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelFondoLayout.createSequentialGroup()
                    .addGap(65, 65, 65)
                    .addGroup(panelFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(panelFondoLayout.createSequentialGroup()
                            .addGap(110, 110, 110)
                            .addComponent(labelTijera, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(labelPiedra, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(labelPapel, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addContainerGap(65, Short.MAX_VALUE)))
        );
        panelFondoLayout.setVerticalGroup(
            panelFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelFondoLayout.createSequentialGroup()
                .addContainerGap(249, Short.MAX_VALUE)
                .addGroup(panelFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelMonster2, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelMonster1, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(79, 79, 79))
            .addGroup(panelFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelFondoLayout.createSequentialGroup()
                    .addGap(10, 10, 10)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(300, 300, 300)
                    .addGroup(panelFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(labelPiedra, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(labelPapel, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(labelTijera, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addContainerGap(10, Short.MAX_VALUE)))
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

    private void labelTijeraAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_labelTijeraAncestorAdded

    }//GEN-LAST:event_labelTijeraAncestorAdded

    private void labelPapelAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_labelPapelAncestorAdded

    }//GEN-LAST:event_labelPapelAncestorAdded

    private void labelPiedraAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_labelPiedraAncestorAdded

    }//GEN-LAST:event_labelPiedraAncestorAdded

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
            java.util.logging.Logger.getLogger(campoBatallaOffline.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(campoBatallaOffline.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(campoBatallaOffline.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(campoBatallaOffline.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new campoBatallaOffline(monster, NoRival).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JProgressBar barVida;
    private javax.swing.JProgressBar barVidaOponente;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel labelMonster1;
    private javax.swing.JLabel labelMonster2;
    private javax.swing.JLabel labelPapel;
    private javax.swing.JLabel labelPiedra;
    private javax.swing.JLabel labelTijera;
    private javax.swing.JPanel panelFondo;
    // End of variables declaration//GEN-END:variables
}
