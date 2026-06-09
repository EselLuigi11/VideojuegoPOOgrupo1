package modelo.vista;

import javax.swing.JPanel;

public class PanelEstado extends JPanel {

    private PanelPersonaje panelGuerrero;
    private PanelPersonaje panelMago;
    private PanelPersonaje panelArquero;
    private PanelPersonaje panelAsesino;
    private PanelPersonaje panelCurador;
    private PanelPersonaje panelEnemigo1;
    private PanelPersonaje panelEnemigo2;
    private PanelPersonaje panelEnemigo3;

    public PanelEstado() {

        setLayout(null);

        panelGuerrero = new PanelPersonaje("Guerrero");
        panelMago = new PanelPersonaje("Mago");
        panelArquero = new PanelPersonaje("Arquero");
        panelAsesino = new PanelPersonaje("Asesino");
        panelCurador = new PanelPersonaje("Curador");

        panelEnemigo1 = new PanelPersonaje("Enemigo");
        panelEnemigo2 = new PanelPersonaje("Enemigo");
        panelEnemigo3 = new PanelPersonaje("Enemigo");


        // Héroes abajo a la izquierda en diagonal
        panelCurador.setBounds(20, 20, 100, 80);

        panelAsesino.setBounds(90, 90, 100, 80);

        panelArquero.setBounds(160, 160, 100, 80);

        panelMago.setBounds(230, 230, 100, 80);

        panelGuerrero.setBounds(300, 300, 100, 80);
        
        // Enemigo arriba a la derecha
        panelEnemigo1.setBounds(620, 30, 120, 90);
        panelEnemigo2.setBounds(560, 120, 120, 90);
        panelEnemigo3.setBounds(620, 210, 120, 90);

        add(panelGuerrero);
        add(panelMago);
        add(panelArquero);
        add(panelAsesino);
        add(panelCurador);

        add(panelEnemigo1);
        add(panelEnemigo2);
        add(panelEnemigo3);    }

    public PanelPersonaje getPanelGuerrero() {
        return panelGuerrero;
    }

    public PanelPersonaje getPanelMago() {
        return panelMago;
    }

    public PanelPersonaje getPanelArquero() {
        return panelArquero;
    }

    public PanelPersonaje getPanelAsesino() {
        return panelAsesino;
    }

    public PanelPersonaje getPanelCurador() {
        return panelCurador;
    }

    public PanelPersonaje getPanelEnemigo1() {
        return panelEnemigo1;
    }

    public PanelPersonaje getPanelEnemigo2() {
        return panelEnemigo2;
    }

    public PanelPersonaje getPanelEnemigo3() {
        return panelEnemigo3;
    }
}