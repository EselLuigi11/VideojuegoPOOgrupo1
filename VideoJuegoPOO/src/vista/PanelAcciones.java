package vista;

import javax.swing.JButton;
import javax.swing.JPanel;

public class PanelAcciones extends JPanel {

    private JButton btnAtacar;
    private JButton btnDefender;
    private JButton btnHabilidad;
    private JButton btnUsarItem;
    private JButton btnVerStats;
    private JButton btnVerEquipo; // NUEVO: botón pedido en "Interfaz #3" del PDF de correcciones
    private JButton btnGuardarPartida;

    public PanelAcciones() {
        btnAtacar = new JButton("Atacar");
        btnDefender = new JButton("Defender");
        btnHabilidad = new JButton("Habilidad");
        btnUsarItem = new JButton("Usar Ítem");
        btnVerStats = new JButton("Ver Stats");
        btnVerEquipo = new JButton("Ver Equipo"); // NUEVO
        btnGuardarPartida = new JButton("Guardar Partida");

        add(btnAtacar);
        add(btnDefender);
        add(btnHabilidad);
        add(btnUsarItem);
        add(btnVerStats);
        add(btnVerEquipo); // NUEVO
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

    public JButton getBtnVerStats() {
        return btnVerStats;
    }

    // NUEVO
    public JButton getBtnVerEquipo() {
        return btnVerEquipo;
    }

    public JButton getBtnGuardarPartida() {
        return btnGuardarPartida;
    }
}
