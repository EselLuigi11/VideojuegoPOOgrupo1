package modelo.entidades;
 
import modelo.Arma;
import modelo.Armadura;
 
/**
 * ARQUERO — Perfil: DPS físico de alto crítico y velocidad.
 * Vida baja, defensa baja, pero el más rápido y con mayor prob. crítica.
 *
 * Progresión por nivel:
 *  - Ataque:    crece fuerte (+7~9 por nivel)
 *  - ProbCrit:  crece consistentemente (+3~4 por nivel)
 *  - Velocidad: crece moderado (+2 por nivel)
 *  - Vida:      crece poco (es frágil por diseño)
 *  - Defensa:   crece muy poco (sigue siendo frágil)
 */
public class Arquero extends Heroe {
 
    public Arquero(String nombre, Arma arma, Armadura armadura) {
        super(nombre,
            95, 95,      // vida, vidaMax
            28, 10, 18,  // ataque, defensa, velocidad
            false,       // estaDefendiendo
            0, 1,        // experiencia, nivel
            100, 0, 0,   // energiaMax, mana, manaMax
            30, 160,     // probCrit, danoCrit
            null, arma, armadura);
 
       // ── Tabla de niveles del Arquero ───────────────────────────────
 // tablaDeNiveles.put(nivel, new StatsNivel(vidaMax, ataque, defensa, velocidad, energiaMax, manaMax, probCrit, danoCrit))
 
        tablaDeNiveles.put(2, new StatsNivel(110, 37,  12, 20, 110, 0, 34, 168));
        tablaDeNiveles.put(3, new StatsNivel(125, 46,  14, 22, 120, 0, 38, 178));
        tablaDeNiveles.put(4, new StatsNivel(140, 56,  16, 24, 130, 0, 43, 190));
        tablaDeNiveles.put(5, new StatsNivel(155, 67,  18, 26, 140, 0, 49, 205));
    }
 
    @Override
    public void subirNivel() {
        super.subirNivel(); // aplica la tabla
        System.out.println("  ¡El Arquero " + getNombre() + " subió de nivel!");
    }
}
