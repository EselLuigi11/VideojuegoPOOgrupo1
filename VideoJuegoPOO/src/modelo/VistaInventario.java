import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.GridLayout;
import java.awt.BorderLayout;

public class VistaInventario extends JFrame {

    private JPanel panelParty;
    private JPanel panelItems;

    public VistaInventario() {
        setTitle("Inventario y Equipo");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        panelParty = new JPanel();
        panelParty.setLayout(new GridLayout(0, 1));
        panelParty.add(new JLabel("--- TU PARTY ---"));
        panelParty.add(new JLabel("Héroe 1: Arthur (Nivel 5)"));
        panelParty.add(new JLabel("Héroe 2: Merlín (Nivel 4)"));

        panelItems = new JPanel();
        panelItems.setLayout(new GridLayout(0, 1));
        panelItems.add(new JLabel("--- MOCHILA ---"));
        panelItems.add(new JLabel("- Poción de Vida x3"));
        panelItems.add(new JLabel("- Espada Larga x1"));

        add(panelParty, BorderLayout.WEST);
        add(panelItems, BorderLayout.CENTER);
    }
}
