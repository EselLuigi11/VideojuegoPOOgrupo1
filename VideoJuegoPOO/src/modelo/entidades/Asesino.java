package modelo.entidades;

import modelo.Arma;
import modelo.Armadura;
import modelo.habilidades.HabEspAsesino;

public class Asesino extends Heroe implements java.io.Serializable {
    private static final long serialVersionUID = 1L;
    private HabEspAsesino habilidadEspecial;

    public Asesino(String nombre, Arma arma, Armadura armadura) {
        super(nombre,
                80, 80,      // vida, vidaMax
                27, 8, 20,   // ataque, defensa, velocidad
                false,
                0, 1,        // experiencia, nivel
                90, 90,      // mana, manaMax
                40, 200,     // probCrit, danoCrit
                arma, armadura);
        this.habilidadEspecial = new HabEspAsesino();

        // (vidaMax, ataque, defensa, velocidad, manaMax, probCrit, danoCrit)
        tablaDeNiveles.put(2, new StatsNivel( 93, 46, 10, 23, 105, 45, 225));
        tablaDeNiveles.put(3, new StatsNivel(106, 58, 12, 26, 120, 51, 252));
        tablaDeNiveles.put(4, new StatsNivel(119, 71, 14, 29, 135, 58, 281));
        tablaDeNiveles.put(5, new StatsNivel(132, 85, 16, 32, 150, 66, 312));
    }

    @Override
    public void subirNivel() {
        super.subirNivel();
        System.out.println("  ¡El Asesino " + getNombre() + " subió de nivel!");
    }

	public HabEspAsesino getHabilidadEspecial() { return habilidadEspecial; }
	public void setHabilidadEspecial(HabEspAsesino habilidadEspecial) { this.habilidadEspecial = habilidadEspecial; }
}
