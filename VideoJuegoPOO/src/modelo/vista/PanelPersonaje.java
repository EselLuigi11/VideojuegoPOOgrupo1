package modelo.vista;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingConstants;

public class PanelPersonaje extends JPanel {

    private JLabel lblNombre;
    private JProgressBar barraVida;
    private JLabel lblImagen;

    public PanelPersonaje(String nombre) {

        setLayout(new BorderLayout());

        lblNombre = new JLabel(nombre, SwingConstants.CENTER);

        barraVida = new JProgressBar(0, 100);
        barraVida.setValue(100);

        // Oculta el texto 100/100 dentro de la barra
        barraVida.setStringPainted(false);

        // Barra pequeña
        barraVida.setPreferredSize(new Dimension(70, 10));

        try {

            ImageIcon iconoOriginal = new ImageIcon(
                    getClass().getResource("/img/" + nombre.toLowerCase() + ".png"));

            Image imagenEscalada = iconoOriginal.getImage()
                    .getScaledInstance(64, 64, Image.SCALE_SMOOTH);

            lblImagen = new JLabel(new ImageIcon(imagenEscalada));
            lblImagen.setHorizontalAlignment(SwingConstants.CENTER);

            // Barra arriba
            add(barraVida, BorderLayout.NORTH);

            // Sprite en el centro
            add(lblImagen, BorderLayout.CENTER);

            // Nombre abajo

        } catch (Exception e) {
            System.out.println("No se encontró la imagen para: " + nombre);
        }
    }

    public JProgressBar getBarraVida() {
        return barraVida;
    }

    public void actualizarBarraVisual(int vidaActual, int vidaMax) {
        barraVida.setMaximum(vidaMax);
        barraVida.setValue(vidaActual);

        // El texto se muestra como tooltip en lugar de dentro de la barra
        barraVida.setToolTipText(vidaActual + "/" + vidaMax);
    }
}