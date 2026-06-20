package modelo.vista;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import modelo.entidades.Enemigo;
import modelo.entidades.Heroe;

/**
 * PanelEstado — contenedor dinámico de PanelPersonaje + indicador de orden de turnos.
 */
public class PanelEstado extends JPanel {

    private final List<PanelPersonaje> panelesHeroes   = new ArrayList<>();
    private final List<PanelPersonaje> panelesEnemigos = new ArrayList<>();

    // Franja de turnos: panel dedicado y SIEMPRE opaco, en vez de JLabel
    // transparente. Un JLabel con fondo semi-transparente (alpha) sobre
    // setLayout(null) es justamente el patrón que más arrastra residuos
    // visuales en Swing porque su área "sucia" nunca queda 100% definida.
    // Con un JPanel opaco + JLabel hijo 100% opaco, cada repaint() limpia
    // su propio rectángulo de fondo sólido antes de dibujar el texto.
    private final JPanel franjaTurnos;
    private final JLabel lblOrdenTurnos;

    private static final int ANCHO = 900;
    private static final int ALTO  = 500;
    private static final int FRANJA_ALTO = 32;

    private static final int[][] POS_HEROES = {
        { 10,  10 }, { 10, 200 }, {190,  10 }, {190, 200 }, {370,  10 }
    };

    private static final int[][] POS_ENEMIGOS = {
        {660,  10  }, {660,  150 }, {660,  290 }
    };

    private static final Dimension TAM_PANEL_HEROE   = new Dimension(170, 180);
    private static final Dimension TAM_PANEL_ENEMIGO = new Dimension(170, 130);

    public PanelEstado() {
        setLayout(null);
        setOpaque(false);
        setPreferredSize(new Dimension(ANCHO, ALTO));

        // ── Franja de turnos: 100% opaca, fondo sólido (sin alpha) ───────────
        franjaTurnos = new JPanel(new BorderLayout());
        franjaTurnos.setOpaque(true);
        franjaTurnos.setBackground(new Color(10, 10, 20)); // sólido, sin transparencia
        franjaTurnos.setBorder(BorderFactory.createLineBorder(new Color(90, 90, 60), 1));
        franjaTurnos.setBounds(0, ALTO - FRANJA_ALTO, ANCHO, FRANJA_ALTO);

        lblOrdenTurnos = new JLabel("Turnos: —", SwingConstants.CENTER);
        lblOrdenTurnos.setFont(new Font("Monospaced", Font.BOLD, 13));
        lblOrdenTurnos.setForeground(Color.YELLOW);
        lblOrdenTurnos.setOpaque(false); // el fondo lo pone el JPanel padre, sólido

        franjaTurnos.add(lblOrdenTurnos, BorderLayout.CENTER);
        add(franjaTurnos);
    }

    public void inicializar(List<Heroe> heroes, List<Enemigo> enemigos) {
        removeAll();
        panelesHeroes.clear();
        panelesEnemigos.clear();
        add(franjaTurnos);

        for (int i = 0; i < heroes.size() && i < POS_HEROES.length; i++) {
            PanelPersonaje panel = new PanelPersonaje(heroes.get(i));
            panel.setPreferredSize(TAM_PANEL_HEROE);
            panel.setBounds(POS_HEROES[i][0], POS_HEROES[i][1], TAM_PANEL_HEROE.width, TAM_PANEL_HEROE.height);
            panelesHeroes.add(panel);
            add(panel);
        }

        for (int i = 0; i < enemigos.size() && i < POS_ENEMIGOS.length; i++) {
            PanelPersonaje panel = new PanelPersonaje(enemigos.get(i));
            panel.setPreferredSize(TAM_PANEL_ENEMIGO);
            panel.setBounds(POS_ENEMIGOS[i][0], POS_ENEMIGOS[i][1], TAM_PANEL_ENEMIGO.width, TAM_PANEL_ENEMIGO.height);
            panelesEnemigos.add(panel);
            add(panel);
        }

        // Reordenamos capas: los paneles de personajes van AL FRENTE de la
        // franja de turnos para que esta quede siempre debajo en el z-order
        // y no compita visualmente con nada que pueda superponerse.
        setComponentZOrder(franjaTurnos, getComponentCount() - 1);

        revalidate();
        repaint();
    }

    public PanelPersonaje buscarPanelHeroe(Heroe heroe) {
        for (PanelPersonaje p : panelesHeroes) if (p.getEntidad() == heroe) return p;
        return null;
    }

    public PanelPersonaje buscarPanelEnemigo(Enemigo enemigo) {
        for (PanelPersonaje p : panelesEnemigos) if (p.getEntidad() == enemigo) return p;
        return null;
    }

    public void refreshTodos() {
        for (PanelPersonaje p : panelesHeroes)   p.refresh();
        for (PanelPersonaje p : panelesEnemigos) p.refresh();
    }

    public void refrescarActivo(modelo.Entidad entidadActiva) {
        for (PanelPersonaje p : panelesHeroes)   p.setActivo(p.getEntidad() == entidadActiva);
        for (PanelPersonaje p : panelesEnemigos) p.setActivo(p.getEntidad() == entidadActiva);
    }

    /**
     * Actualiza el texto de orden de turnos. Como la franja ahora es un
     * JPanel 100% opaco con fondo sólido, cada repaint() limpia su propio
     * rectángulo antes de redibujar el texto — ya no hay solapamiento de
     * texto viejo/nuevo en capas de gris.
     */
    public void actualizarOrdenTurnos(List<modelo.Entidad> ordenTurnos) {
        if (ordenTurnos == null || ordenTurnos.isEmpty()) {
            lblOrdenTurnos.setText("Turnos: —");
        } else {
            StringBuilder sb = new StringBuilder("Turnos:  ");
            for (int i = 0; i < ordenTurnos.size(); i++) {
                sb.append(ordenTurnos.get(i).getNombre());
                if (i < ordenTurnos.size() - 1) sb.append("  →  ");
            }
            lblOrdenTurnos.setText(sb.toString());
        }
        franjaTurnos.revalidate();
        franjaTurnos.repaint();
    }

    public List<PanelPersonaje> getPanelesHeroes()   { return panelesHeroes; }
    public List<PanelPersonaje> getPanelesEnemigos() { return panelesEnemigos; }
}