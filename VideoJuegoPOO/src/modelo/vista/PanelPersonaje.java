package modelo.vista;

import java.awt.*;
import javax.swing.*;
import modelo.Entidad;
import modelo.entidades.Heroe;

public class PanelPersonaje extends JPanel {

    private final Entidad entidad;

    private final JLabel        lblNombre;
    private final JProgressBar  barraVida;
    private final JProgressBar  barraMana;
    private final JProgressBar  barraExp;
    private final JLabel        lblVidaTexto;
    private final JLabel        lblManaTexto;
    private final JLabel        lblExpTexto;
    private final JLabel        lblImagen;
    private ImageIcon spriteIdle;
    private ImageIcon spriteAtaque;

    private static final Color COLOR_VIDA_OK   = new Color(50, 200, 70);
    private static final Color COLOR_VIDA_BAJA = new Color(220, 50, 50);
    private static final Color COLOR_MANA      = new Color(60, 120, 220);
    private static final Color COLOR_EXP       = new Color(140, 70, 210); // Color púrpura para XP
    private static final Color COLOR_BG        = new Color(15, 15, 30, 210);
    private static final Color COLOR_BORDE     = new Color(90, 90, 150);
    private static final Color COLOR_ACTIVO    = new Color(255, 215, 0);
    private static final Font  FUENTE_NOMBRE   = new Font("Serif",      Font.BOLD,  13);
    private static final Font  FUENTE_STATS    = new Font("Monospaced", Font.PLAIN, 10);

    public PanelPersonaje(Entidad entidad) {
        if (entidad == null) throw new IllegalArgumentException("Entidad no puede ser nula.");
        this.entidad = entidad;

        setLayout(new GridBagLayout());
        setOpaque(true);
        setBackground(COLOR_BG);
        setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(COLOR_BORDE, 1, true),
            BorderFactory.createEmptyBorder(4, 8, 4, 8)
        ));
        setPreferredSize(new Dimension(170, esHeroe() ? 180 : 130)); // Aumentamos el alto para la barra de XP

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets    = new Insets(2, 2, 2, 2);
        gbc.fill      = GridBagConstraints.HORIZONTAL;
        gbc.gridwidth = 2;
        gbc.weightx   = 1.0;

        // Fila 0: sprite
        lblImagen = cargarSprite(entidad.getNombre());
        gbc.gridx = 0; gbc.gridy = 0;
        add(lblImagen, gbc);

        // Fila 1: nombre
        lblNombre = new JLabel(entidad.getNombre(), SwingConstants.CENTER);
        lblNombre.setFont(FUENTE_NOMBRE);
        lblNombre.setForeground(Color.WHITE);
        gbc.gridy = 1;
        add(lblNombre, gbc);

        // Fila 2-3: barra de vida
        barraVida = crearBarra(entidad.getVidaMax(), COLOR_VIDA_OK);
        gbc.gridy = 2;
        add(barraVida, gbc);

        lblVidaTexto = crearLabelStat();
        gbc.gridy = 3;
        add(lblVidaTexto, gbc);

        // Filas 4-5: maná (solo héroes)
        if (esHeroe()) {
            Heroe h = (Heroe) entidad;
            barraMana    = crearBarra(Math.max(h.getManaMax(), 1), COLOR_MANA);
            lblManaTexto = crearLabelStat();
            gbc.gridy = 4; add(barraMana,    gbc);
            gbc.gridy = 5; add(lblManaTexto, gbc);

            // Filas 6-7: experiencia y nivel
            barraExp    = crearBarra(h.getNivel() * 105, COLOR_EXP);
            lblExpTexto = crearLabelStat();
            gbc.gridy = 6; add(barraExp,    gbc);
            gbc.gridy = 7; add(lblExpTexto, gbc);
        } else {
            barraMana    = null;
            lblManaTexto = null;
            barraExp     = null;
            lblExpTexto  = null;
        }

        refresh();
    }

    public void refresh() {
        int vida    = entidad.getVida();
        int vidaMax = entidad.getVidaMax();

        barraVida.setMaximum(Math.max(vidaMax, 1));
        barraVida.setValue(Math.max(0, vida));
        barraVida.setForeground(
            (double) vida / vidaMax < 0.30 ? COLOR_VIDA_BAJA : COLOR_VIDA_OK
        );
        lblVidaTexto.setText("HP " + vida + "/" + vidaMax);

        if (esHeroe()) {
            Heroe h   = (Heroe) entidad;
            int mana  = h.getMana();
            int manaM = Math.max(h.getManaMax(), 1);
            barraMana.setMaximum(manaM);
            barraMana.setValue(Math.max(0, mana));
            lblManaTexto.setText("MP " + mana + "/" + manaM);

            // Actualizamos barra de XP y texto de Nivel
            int expMax = h.getNivel() * 105;
            barraExp.setMaximum(expMax);
            barraExp.setValue(Math.max(0, h.getExperiencia()));
            lblExpTexto.setText("LVL " + h.getNivel() + " | XP " + h.getExperiencia() + "/" + expMax);
        }

        boolean muerto = vida <= 0;
        lblNombre.setText(muerto
            ? "<html><s>" + entidad.getNombre() + "</s></html>"
            : entidad.getNombre());
    }

    public Entidad getEntidad() { return entidad; }

    private boolean esHeroe() { return entidad instanceof Heroe; }

    private JProgressBar crearBarra(int maximo, Color color) {
        JProgressBar b = new JProgressBar(0, maximo);
        b.setForeground(color);
        b.setBackground(new Color(35, 35, 55));
        b.setBorderPainted(false);
        b.setStringPainted(false);
        b.setPreferredSize(new Dimension(0, 10));
        return b;
    }

    private JLabel crearLabelStat() {
        JLabel lbl = new JLabel("", SwingConstants.CENTER);
        lbl.setFont(FUENTE_STATS);
        lbl.setForeground(Color.LIGHT_GRAY);
        return lbl;
    }

    private JLabel cargarSprite(String nombre) {
        JLabel lbl = new JLabel();
        lbl.setHorizontalAlignment(SwingConstants.CENTER);

        System.out.println("CARPETA IMG = " + getClass().getResource("/img/"));

        try {
            String base = nombre.toLowerCase().replace(" ", "_");

            java.net.URL idleURL   = getClass().getResource("/img/" + base + "_idle.png");
            java.net.URL ataqueURL = getClass().getResource("/img/" + base + "_ataque.png");

            System.out.println("Entidad: " + nombre);
            System.out.println("Buscando idle: /img/" + base + "_idle.png -> " + idleURL);
            System.out.println("Buscando ataque: /img/" + base + "_ataque.png -> " + ataqueURL);

            if (idleURL != null) {
                Image idleImg = new ImageIcon(idleURL)
                        .getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
                spriteIdle = new ImageIcon(idleImg);
                lbl.setIcon(spriteIdle);

                if (ataqueURL != null) {
                    Image atkImg = new ImageIcon(ataqueURL)
                            .getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
                    spriteAtaque = new ImageIcon(atkImg);
                }
            } else {
                lbl.setText("[" + nombre.charAt(0) + "]");
            }
        } catch (Exception e) {
            lbl.setText("[?]");
        }

        return lbl;
    }

    public void mostrarAtaque() {
        if (spriteAtaque == null) return;
        lblImagen.setIcon(spriteAtaque);
        javax.swing.Timer timer = new javax.swing.Timer(400, e -> lblImagen.setIcon(spriteIdle));
        timer.setRepeats(false);
        timer.start();
    }

    public void setActivo(boolean activo) {
        if (activo) {
            setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(COLOR_ACTIVO, 2, true),
                BorderFactory.createEmptyBorder(3, 7, 3, 7)
            ));
        } else {
            setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(COLOR_BORDE, 1, true),
                BorderFactory.createEmptyBorder(4, 8, 4, 8)
            ));
        }
    }
}
