package modelo.vista;

import javax.swing.JButton;
import javax.swing.JPanel;

public class PanelAcciones extends JPanel {

    private JButton btnAtacar;
    private JButton btnDefender;
    private JButton btnHabilidad;
    private JButton btnUsarItem;
    private JButton btnGuardarPartida;

    public PanelAcciones() {
        btnAtacar = new JButton("Atacar");
        btnDefender = new JButton("Defender");
        btnHabilidad = new JButton("Habilidad");
        btnUsarItem = new JButton("Usar Ítem");
        btnGuardarPartida = new JButton("Guardar Partida");

        add(btnAtacar);
        add(btnDefender);
        add(btnHabilidad);
        add(btnUsarItem);
        add(btnGuardarPartida);
    }

    public JButton getBtnAtacar() {
        return btnAtacar;
    }

    public JButton getBtnDefender() {
        return btnDefender;
    }

    public JButton getBtnHabilidad() {
        return btnHabilidad;
    }

    public JButton getBtnUsarItem() {
        return btnUsarItem;
    }

    public JButton getBtnGuardarPartida() {
        return btnGuardarPartida;
    }
}
