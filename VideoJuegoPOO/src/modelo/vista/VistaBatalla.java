package modelo.vista;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class VistaBatalla extends JFrame {

    private PanelEstado panelEstado;
    private PanelHistorial panelHistorial;
    private PanelAcciones panelAcciones;

    public VistaBatalla() {

        setTitle("Videojuego POO - Batalla");

        setSize(900, 600);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLocationRelativeTo(null);

        setLayout(new BorderLayout());

        panelEstado = new PanelEstado();
        panelHistorial = new PanelHistorial();
        panelAcciones = new PanelAcciones();

        // Historial más bajo y más pequeño
        panelHistorial.setPreferredSize(
                new java.awt.Dimension(900, 120));

        JPanel panelInferior = new JPanel(
                new BorderLayout());

        panelInferior.add(
                panelHistorial,
                BorderLayout.CENTER);

        panelInferior.add(
                panelAcciones,
                BorderLayout.SOUTH);

        add(panelEstado, BorderLayout.CENTER);
        add(panelInferior, BorderLayout.SOUTH);

        setVisible(true);
    }
    
    public PanelAcciones getPanelAcciones() {
        return panelAcciones;
    }

    public PanelEstado getPanelEstado() {
        return panelEstado;
    }

    public PanelHistorial getPanelHistorial() {
        return panelHistorial;
    }
   
}