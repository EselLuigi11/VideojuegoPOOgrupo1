package vista;

import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class PanelEstado extends JPanel {

    public PanelEstado() {

        setLayout(new GridLayout(7, 1, 5, 5));

        setBorder(
            BorderFactory.createTitledBorder("Estado de los Personajes")
        );

        add(new PanelPersonaje("Guerrero"));
        add(new PanelPersonaje("Mago"));
        add(new PanelPersonaje("Arquero"));
        add(new PanelPersonaje("Asesino"));
        add(new PanelPersonaje("Curador"));
        add(new PanelPersonaje("Heroe 6"));

        add(new PanelPersonaje("Enemigo"));
    }
}


