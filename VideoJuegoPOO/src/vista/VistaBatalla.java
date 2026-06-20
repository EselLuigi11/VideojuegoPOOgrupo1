package vista;

import java.awt.*;
import java.net.URL;
import javax.swing.*;

/**
 * VistaBatalla — ventana principal del combate.
 */
public class VistaBatalla extends JFrame {

    private final PanelEstado    panelEstado;
    private final PanelHistorial panelHistorial;
    private final PanelAcciones  panelAcciones;

    public VistaBatalla() {
        setTitle("Videojuego POO — Batalla");
        setSize(960, 680);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        PanelFondo panelFondo = new PanelFondo("/img/fondo_batalla.png");
        panelFondo.setLayout(new BorderLayout());
        setContentPane(panelFondo);

        panelEstado    = new PanelEstado();
        panelHistorial = new PanelHistorial();
        panelAcciones  = new PanelAcciones();

        panelHistorial.setPreferredSize(new Dimension(900, 120));
        panelHistorial.setOpaque(false);

        JPanel panelInferior = new JPanel(new BorderLayout());
        panelInferior.setOpaque(false);
        panelInferior.add(panelHistorial, BorderLayout.CENTER);
        panelInferior.add(panelAcciones,  BorderLayout.SOUTH);

        panelFondo.add(panelEstado,   BorderLayout.CENTER);
        panelFondo.add(panelInferior, BorderLayout.SOUTH);
    }

    public PanelAcciones  getPanelAcciones()  { return panelAcciones;  }
    public PanelEstado    getPanelEstado()    { return panelEstado;    }
    public PanelHistorial getPanelHistorial() { return panelHistorial; }

    public void appendHistorial(String mensaje) {
        panelHistorial.getHistorial().append(mensaje + "\n");
        panelHistorial.getHistorial().setCaretPosition(
            panelHistorial.getHistorial().getDocument().getLength()
        );
    }

    /**
     * Fuerza un repintado completo y diferido al final de la cola del EDT.
     *
     * Por qué esta forma y no repaint()/paintImmediately() directos:
     * el JOptionPane corre su propio sub-loop modal dentro del Event
     * Dispatch Thread. Si se pide repintar inmediatamente apenas el
     * diálogo se cierra, todavía pueden quedar eventos de pintado del
     * diálogo encolados DESPUÉS del nuestro, y terminan pintándose
     * encima, dejando el residuo. SwingUtilities.invokeLater encola
     * nuestro repintado al FINAL de la cola de eventos, garantizando
     * que se ejecuta después de que el diálogo terminó de limpiar todo
     * lo suyo. Se repinta getRootPane() completo (incluye contentPane,
     * layeredPane y glassPane) para cubrir cualquier capa donde el
     * JOptionPane haya dejado píxeles.
     */
    public void repintarCompleto() {
        SwingUtilities.invokeLater(() -> {
            JRootPane root = getRootPane();
            root.revalidate();
            root.repaint();
        });
    }

    private static class PanelFondo extends JPanel {

        private Image imagenFondo;

        public PanelFondo(String rutaRecurso) {
            try {
                URL url = getClass().getResource(rutaRecurso);
                if (url != null) {
                    imagenFondo = new ImageIcon(url).getImage();
                } else {
                    System.out.println("[VistaBatalla] Advertencia: no se encontró "
                        + rutaRecurso + ". Se usará fondo degradado.");
                }
            } catch (Exception e) {
                System.out.println("[VistaBatalla] Error al cargar fondo: " + e.getMessage());
            }
            setOpaque(true);
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (imagenFondo != null) {
                g.drawImage(imagenFondo, 0, 0, getWidth(), getHeight(), this);
            } else {
                Graphics2D g2 = (Graphics2D) g;
                g2.setPaint(new GradientPaint(
                    0, 0, new Color(20, 10, 40),
                    0, getHeight(), new Color(5, 5, 15)
                ));
                g2.fillRect(0, 0, getWidth(), getHeight());
            }
        }
    }
}