
package battle;

import java.awt.Color;
import java.awt.Image;
import java.io.IOException;
import java.lang.System.Logger;
import java.lang.System.Logger.Level;
import static java.lang.System.out;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingUtilities;
import java.util.Random;
import javax.swing.plaf.basic.BasicProgressBarUI;


public class CampoBatalla extends javax.swing.JFrame {

    public static DataOutputStream out;
    public static DataInputStream in;
    int vida, atq, evasion;
    String imagenMonster;
    static Monster monster;
    static String eresServidor;
    static ServerSocket servidor;

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

    //int vida=monster.getVida();
    public CampoBatalla(DataOutputStream out, DataInputStream in, Monster monster, ServerSocket servidor, String eresServidor) {
        initComponents();
        panelEsperar.setVisible(false);
        int vidaMax = monster.getVida();
        this.vida = monster.getVida();
        this.atq = monster.getAtq();
        this.evasion = monster.getEvasion();
        this.imagenMonster = monster.getImagen();
        this.out = out;
        this.in = in;
        this.servidor = servidor;
        this.eresServidor = eresServidor;
        setLocationRelativeTo(null);
        FondoInicio fondo = new FondoInicio(jPanel1, "/battle/imagenes/campo de batalla.png");
        jPanel1.add(fondo).repaint();
        jPanel1.setOpaque(false);
        jPanel1.setBorder(null);
        jPanel1.setBackground(new Color(0, 0, 0, 0));

        setImage(labelMonster1, imagenMonster);
        EnivarImagen(imagenMonster);

        //setImage(labelMonster2, "/battle/imagenes/monster/monster3a.png");
        setImage(labelPiedra, "/battle/imagenes/piedra.png");
        setImage(labelPapel, "/battle/imagenes/papel.png");
        setImage(labelTijera, "/battle/imagenes/tijera.png");

        /*labelVida.setText(String.valueOf(vidaMax));
        labelAtq.setText(String.valueOf(atq));
        labelEvasion.setText(String.valueOf(evasion));*/
        int ataqueOponente = enviarDatos(vidaMax);
        barActualizar(vidaMax, monster.getVida(), 0);
        tijera(vidaMax, ataqueOponente);
        piedra(vidaMax, ataqueOponente);
        papel(vidaMax, ataqueOponente);

    }

    /*public void detenerServidor() {
        server.detenerEscucharRed();
    }*/
    public void EnivarImagen(String imagen) {
        try {
            out.writeUTF(imagen);
            String imagenOponente = in.readUTF();
            setImage(labelMonster2, imagenOponente);
        } catch (IOException ex) {
            java.util.logging.Logger.getLogger(CampoBatalla.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
    }

    public int enviarDatos(int vidaMax) {
        try {
            out.writeUTF(String.valueOf(vidaMax));
            out.writeUTF(String.valueOf(vida));
            out.writeUTF(String.valueOf(atq));
            out.writeUTF(String.valueOf(evasion));

            String vidaMaxOponente = in.readUTF();
            String vidaOponente = in.readUTF();
            String atqOponente = in.readUTF();
            String evasionOponente = in.readUTF();

            /*labelVida1.setText(String.valueOf(vidaMaxOponente));
            labelAtq1.setText(String.valueOf(atqOponente));
            labelEvasion1.setText(String.valueOf(evasionOponente));
            labelVidaActualOponente.setText(String.valueOf(vidaOponente));*/
            if (Integer.parseInt(vidaOponente) <= 0 && eresServidor == "1") {
                servidor.close();
                this.setVisible(false);
                JOptionPane.showMessageDialog(null, "Haz ganado");
                Inicio inicio = new Inicio();
                inicio.setVisible(true);
            }
            if (Integer.parseInt(vidaOponente) <= 0 && eresServidor == "0") {
                this.setVisible(false);
                JOptionPane.showMessageDialog(null, "Haz ganado");
                //detenerServidor();
                Inicio inicio = new Inicio();
                inicio.setVisible(true);
            }
            if (vida <= 0 && eresServidor == "1") {
                servidor.close();
                this.setVisible(false);
                JOptionPane.showMessageDialog(null, "Haz perdido");
                Inicio inicio = new Inicio();
                inicio.setVisible(true);
            }
            if (vida <= 0 && eresServidor == "0") {
                this.setVisible(false);
                JOptionPane.showMessageDialog(null, "Haz perdido");
                //detenerServidor();
                Inicio inicio = new Inicio();
                inicio.setVisible(true);
            }

            actualizarBarOponente(Integer.parseInt(vidaMaxOponente), Integer.parseInt(vidaOponente));

            //String[] datosOponentes = {vidaOponente, defOponente, evasionOponente};
            //actualizarBarOponente(datosOponentes, vidaOponente);
            return Integer.parseInt(atqOponente);

        } catch (IOException ex) {
            java.util.logging.Logger.getLogger(CampoBatalla.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        return 0;

    }

    public void actualizarBarOponente(int vidaMax, int vidaActual) {
        barVidaOponente.setMinimum(0);
        barVidaOponente.setMaximum(vidaMax);
        barVidaOponente.setStringPainted(true); // Mostrar el valor numérico en la barra
        vidaActual = vidaActual;
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

    public int getVida() {
        return vida;
    }

    public void setVida(int vida) {
        this.vida = vida;
    }

    public String esquive(int esquivar) {

        if (esquivar < evasion) {
            return "1";
        } else {
            return "0";
        }

    }

    public void tijera(int vidaMax, int ataqueOponente) {
        labelTijera.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                CalculoDelDuelo("tijera", vidaMax, ataqueOponente);
                /*
                
                // Actualiza el label inmediatamente después del clic
                label.setText("Usaste tijera");
                System.out.println("¡tijera clickeada!");

                new Thread(() -> {
                    try {
                        // Envía la jugada al servidor en un hilo separado
                        out.writeUTF("tijera");

                        // Espera y recibe la jugada del oponente
                        String mensaje = in.readUTF();
                        System.out.println("El oponente uso: " + mensaje);

                        Random rand = new Random();
                        int esquivar = rand.nextInt(100);
                        String esquiveTuAtaque = esquive(esquivar);
                        out.writeUTF(esquiveTuAtaque);
                        String miOponenteEsquivo = in.readUTF();

                        // Verifica si el mensaje no es nulo y muestra el diálogo
                        if (mensaje != null) {
                            SwingUtilities.invokeLater(() -> {
                                //JOptionPane.showMessageDialog(null, "El oponente uso: " + mensaje);

                                if (mensaje.equals("piedra") && esquivar > evasion) {
                                    barActualizar(vidaMax, getVida(), ataqueOponente);
                                    System.out.println("te hizo daño");
                                    enviarDatos(vidaMax);
                                    JOptionPane.showMessageDialog(null, "El oponente uso: " + mensaje + " y te infligio " + ataqueOponente + " de daño");
                                }
                                if (mensaje.equals("piedra") && esquivar < evasion) {
                                    enviarDatos(vidaMax);
                                    JOptionPane.showMessageDialog(null, "El oponente uso: " + mensaje + " pero lo esquivaste :)");
                                }
                                if (mensaje.equals("papel") && miOponenteEsquivo.equals("1")) {
                                    enviarDatos(vidaMax);
                                    JOptionPane.showMessageDialog(null, "El oponente uso: " + mensaje + " pero lo esquivo :c");
                                }
                                if (mensaje.equals("papel") && miOponenteEsquivo.equals("0")) {
                                    enviarDatos(vidaMax);
                                    JOptionPane.showMessageDialog(null, "El oponente uso: " + mensaje + " le inflijiste " + atq + " de daño");
                                }
                            });
                        }
                    } catch (SocketException ex) {
                        JOptionPane.showMessageDialog(null, "Cliente Desconectado");
                    } catch (IOException ex) {
                        java.util.logging.Logger.getLogger(Server.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                    } catch (NullPointerException ex) {
                        JOptionPane.showMessageDialog(null, "Ningún cliente conectado");
                    }

                }
                ).start();*/
            }
        }
        );
    }

    public void papel(int vidaMax, int ataqueOponente) {
        labelPapel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                CalculoDelDuelo("papel", vidaMax, ataqueOponente);
                /*
                // Actualiza el label inmediatamente después del clic
                label.setText("Usaste papel");
                System.out.println("¡papel clickeada!");

                new Thread(() -> {
                    try {
                        // Envía la jugada al servidor en un hilo separado
                        out.writeUTF("papel");

                        // Espera y recibe la jugada del oponente
                        String mensaje = in.readUTF();
                        System.out.println("El oponente uso: " + mensaje);

                        Random rand = new Random();
                        int esquivar = rand.nextInt(100);
                        String esquiveTuAtaque = esquive(esquivar);
                        out.writeUTF(esquiveTuAtaque);
                        String miOponenteEsquivo = in.readUTF();

                        // Verifica si el mensaje no es nulo y muestra el diálogo
                        if (mensaje != null) {
                            SwingUtilities.invokeLater(() -> {
                                //JOptionPane.showMessageDialog(null, "El oponente uso: " + mensaje);
                                if (mensaje.equals("tijera") && esquivar > evasion) {
                                    barActualizar(vidaMax, getVida(), ataqueOponente);
                                    System.out.println("te hizo daño");
                                    enviarDatos(vidaMax);
                                    JOptionPane.showMessageDialog(null, "El oponente uso: " + mensaje + " y te infligio " + ataqueOponente + " de daño");
                                }
                                if (mensaje.equals("tijera") && esquivar < evasion) {
                                    enviarDatos(vidaMax);
                                    JOptionPane.showMessageDialog(null, "El oponente uso: " + mensaje + " pero lo esquivaste :)");
                                }
                                if (mensaje.equals("piedra") && miOponenteEsquivo.equals("1")) {
                                    enviarDatos(vidaMax);
                                    JOptionPane.showMessageDialog(null, "El oponente uso: " + mensaje + " pero lo esquivo :c");
                                }
                                if (mensaje.equals("piedra") && miOponenteEsquivo.equals("0")) {
                                    enviarDatos(vidaMax);
                                    JOptionPane.showMessageDialog(null, "El oponente uso: " + mensaje + " le inflijiste " + atq + " de daño");
                                }
                            });
                        }

                    } catch (IOException ex) {
                        // Manejo de la excepción
                        ex.printStackTrace();
                    }
                }).start();*/
            }
        });
    }

    public void piedra(int vidaMax, int ataqueOponente) {
        labelPiedra.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                CalculoDelDuelo("piedra", vidaMax, ataqueOponente);
                // Actualiza el label inmediatamente después del clic
                /*
                label.setText("Usaste piedra");
                System.out.println("¡piedra clickeada!");

                new Thread(() -> {
                    try {
                        // Envía la jugada al servidor en un hilo separado
                        out.writeUTF("piedra");

                        // Espera y recibe la jugada del oponente
                        String mensaje = in.readUTF();
                        System.out.println("El oponente uso: " + mensaje);

                        Random rand = new Random();
                        int esquivar = rand.nextInt(100);
                        String esquiveTuAtaque = esquive(esquivar);
                        out.writeUTF(esquiveTuAtaque);
                        String miOponenteEsquivo = in.readUTF();

                        // Verifica si el mensaje no es nulo y muestra el diálogo
                        if (mensaje != null) {
                            SwingUtilities.invokeLater(() -> {
                                //JOptionPane.showMessageDialog(null, "El oponente uso: " + mensaje);
                                if (mensaje.equals("papel") && esquivar > evasion) {
                                    barActualizar(vidaMax, getVida(), ataqueOponente);
                                    System.out.println("te hizo daño");
                                    enviarDatos(vidaMax);
                                    JOptionPane.showMessageDialog(null, "El oponente uso: " + mensaje + " y te infligio " + ataqueOponente + " de daño");
                                }
                                if (mensaje.equals("papel") && esquivar < evasion) {
                                    enviarDatos(vidaMax);
                                    JOptionPane.showMessageDialog(null, "El oponente uso: " + mensaje + " pero lo esquivaste :)");
                                }
                                if (mensaje.equals("tijera") && miOponenteEsquivo.equals("1")) {
                                    enviarDatos(vidaMax);
                                    JOptionPane.showMessageDialog(null, "El oponente uso: " + mensaje + " pero lo esquivo :c");
                                }
                                if (mensaje.equals("tijera") && miOponenteEsquivo.equals("0")) {
                                    enviarDatos(vidaMax);
                                    JOptionPane.showMessageDialog(null, "El oponente uso: " + mensaje + " le inflijiste " + atq + " de daño");
                                }

                            });

                        }

                    } catch (IOException ex) {
                        // Manejo de la excepción
                        ex.printStackTrace();
                    }
                }).start();*/
            }
        });
    }

    public String yoEsquive() {

        Random rand = new Random();
        int esquivar = rand.nextInt(100);
        if (esquivar < evasion) {
            //out.writeUTF("1");
            //String miOponenteEsquivo = in.readUTF();
            return "1";
        } else {
            //out.writeUTF("0");
            //String miOponenteEsquivo = in.readUTF();
            return "0";
        }
    }

    public void CalculoDelDuelo(String miEleccion, int vidaMax, int ataqueOponente) {
        label.setText("Usaste " + miEleccion);
        new Thread(() -> {
            try {
                out.writeUTF(miEleccion);
                panelEsperar.setVisible(true);
                String oponenteEleccion = in.readUTF();
                if (oponenteEleccion != null) {
                   panelEsperar.setVisible(false); 
                }
                String esquivar = yoEsquive();
                out.writeUTF(esquivar);
                String oponenteEsquivar = in.readUTF();

                if (esquivar.equals("0") && oponenteEsquivar.equals("0")) {

                    int mio = elementosMio.valueOf(miEleccion).getValor();
                    int oponente = elementosOponente.valueOf(oponenteEleccion).getValor();
                    int parametro = debilidades[mio][oponente];

                    SwingUtilities.invokeLater(() -> {
                        switch (parametro) {
                            case 1: {
                                enviarDatos(vidaMax);
                                JOptionPane.showMessageDialog(null, "El oponente uso: " + oponenteEleccion + " le inflijiste " + atq + " de daño");
                                break;
                            }
                            case -1: {
                                barActualizar(vidaMax, getVida(), ataqueOponente);
                                enviarDatos(vidaMax);
                                JOptionPane.showMessageDialog(null, "El oponente uso: " + oponenteEleccion + " y te infligio " + ataqueOponente + " de daño");
                                break;
                            }
                            case 0: {
                                JOptionPane.showMessageDialog(null, "El oponente uso: " + oponenteEleccion + "hubo un empate");
                                break;
                            }
                        }
                    });
                } else if (esquivar.equals("1")) {
                    enviarDatos(vidaMax);
                    JOptionPane.showMessageDialog(null, "El oponente uso: " + oponenteEleccion + " pero lo esquivaste :)");

                } else if (oponenteEsquivar.equals("1")) {
                    enviarDatos(vidaMax);
                    JOptionPane.showMessageDialog(null, "El oponente uso: " + oponenteEleccion + " pero lo esquivo :c");
                }

            } catch (IOException ex) {
                java.util.logging.Logger.getLogger(CampoBatalla.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            }
        }).start();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        labelMonster1 = new javax.swing.JLabel();
        labelMonster2 = new javax.swing.JLabel();
        labelPiedra = new javax.swing.JLabel();
        labelPapel = new javax.swing.JLabel();
        labelTijera = new javax.swing.JLabel();
        label = new javax.swing.JLabel();
        panelEsperar = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        barVida = new javax.swing.JProgressBar();
        barVidaOponente = new javax.swing.JProgressBar();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        labelPiedra.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                labelPiedraAncestorAdded(evt);
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

        labelTijera.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                labelTijeraAncestorAdded(evt);
            }
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });

        label.setOpaque(true);

        jLabel1.setText("Esperando Oponente");

        javax.swing.GroupLayout panelEsperarLayout = new javax.swing.GroupLayout(panelEsperar);
        panelEsperar.setLayout(panelEsperarLayout);
        panelEsperarLayout.setHorizontalGroup(
            panelEsperarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelEsperarLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 125, Short.MAX_VALUE)
                .addContainerGap())
        );
        panelEsperarLayout.setVerticalGroup(
            panelEsperarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelEsperarLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

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

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(108, 108, 108)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(labelMonster1, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(213, 213, 213)
                                .addComponent(labelMonster2, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(label, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(173, 173, 173)
                        .addComponent(labelTijera, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(labelPiedra, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(labelPapel, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(208, 208, 208)
                        .addComponent(panelEsperar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(63, 63, 63)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(67, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                .addComponent(panelEsperar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(142, 142, 142)
                .addComponent(label, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelMonster2, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelMonster1, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(labelPiedra, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelPapel, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelTijera, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21))
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

    private void labelTijeraAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_labelTijeraAncestorAdded

    }//GEN-LAST:event_labelTijeraAncestorAdded

    private void labelPiedraAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_labelPiedraAncestorAdded

    }//GEN-LAST:event_labelPiedraAncestorAdded

    private void labelPapelAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_labelPapelAncestorAdded

    }//GEN-LAST:event_labelPapelAncestorAdded

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
        java.util.logging.Logger.getLogger(CampoBatalla.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    } catch (InstantiationException ex) {
        java.util.logging.Logger.getLogger(CampoBatalla.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    } catch (IllegalAccessException ex) {
        java.util.logging.Logger.getLogger(CampoBatalla.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    } catch (javax.swing.UnsupportedLookAndFeelException ex) {
        java.util.logging.Logger.getLogger(CampoBatalla.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    }
    //</editor-fold>

    /* Create and display the form */
    java.awt.EventQueue.invokeLater(new Runnable() {
        public void run() {
            new CampoBatalla(out, in, monster, servidor, eresServidor).setVisible(true);
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

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JProgressBar barVida;
    private javax.swing.JProgressBar barVidaOponente;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel label;
    private javax.swing.JLabel labelMonster1;
    private javax.swing.JLabel labelMonster2;
    private javax.swing.JLabel labelPapel;
    private javax.swing.JLabel labelPiedra;
    private javax.swing.JLabel labelTijera;
    private javax.swing.JPanel panelEsperar;
    // End of variables declaration//GEN-END:variables
}
