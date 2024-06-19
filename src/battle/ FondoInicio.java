package battle;

import java.awt.Graphics;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

class FondoInicio extends JLabel {

    private int x, y;
    private final String path;
    //private Image fondo;

    public FondoInicio(JPanel panel, String path) {
        this.path = path;
        this.x = panel.getWidth();
        this.y = panel.getHeight();
        this.setSize(x, y);
    }

    @Override
    public void paint(Graphics g) {

        ImageIcon fondo = new ImageIcon(getClass().getResource(path));
        g.drawImage(fondo.getImage(), 0, 0, x, y, null);
        //super.paint(g); // Llama al método de la superclase para pintar correctamente
    }

}
