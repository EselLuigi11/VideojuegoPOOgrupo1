package modelo.vista;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.List;

public class VistaInventario extends JFrame {

    private JPanel panelParty;
    private JPanel panelItems;
    private List<JButton> botonesUsar;

    public VistaInventario() {
        setTitle("Inventario y Equipo");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));
        
        botonesUsar = new ArrayList<>();

        panelParty = new JPanel();
        panelParty.setLayout(new GridLayout(0, 1));
        panelParty.add(new JLabel("--- TU PARTY ---"));

        panelItems = new JPanel();
        panelItems.setLayout(new GridLayout(0, 1));
        panelItems.add(new JLabel("--- MOCHILA ---"));

        add(panelParty, BorderLayout.WEST);
        add(panelItems, BorderLayout.CENTER);
    }

    public void actualizarInventario(List<String> nombresItems) {
        panelItems.removeAll();
        panelItems.add(new JLabel("--- MOCHILA ---"));
        botonesUsar.clear();

        for (int i = 0; i < nombresItems.size(); i++) {
            JPanel filaItem = new JPanel(new FlowLayout(FlowLayout.LEFT));
            JLabel lblNombre = new JLabel("- " + nombresItems.get(i));
            JButton btnUsar = new JButton("Usar");
            
            botonesUsar.add(btnUsar);

            filaItem.add(lblNombre);
            filaItem.add(btnUsar);
            panelItems.add(filaItem);
        }
        
        panelItems.revalidate();
        panelItems.repaint();
    }

    public List<JButton> getBotonesUsar() {
        return botonesUsar;
    }
}
