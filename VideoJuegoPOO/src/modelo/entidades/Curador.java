package modelo.entidades;

import java.util.List;

import modelo.Arma;
import modelo.Armadura;
import modelo.habilidades.HabEspCurador;

public class Curador extends Heroe implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	private HabEspCurador habilidadEspecial;

	public Curador(String nombre, Arma arma, Armadura armadura) {
		super(nombre,
				90, 90,
				10, 12, 14,
				false,
				0, 1,
				80, 80,
				5, 100,
				arma, armadura);
		this.habilidadEspecial = new HabEspCurador();

		tablaDeNiveles.put(2, new StatsNivel(108, 12, 20, 15, 100, 5, 105));
		tablaDeNiveles.put(3, new StatsNivel(126, 14, 28, 16, 122, 5, 110));
		tablaDeNiveles.put(4, new StatsNivel(144, 16, 36, 17, 146, 6, 115));
		tablaDeNiveles.put(5, new StatsNivel(162, 18, 44, 18, 172, 6, 120));
	}

	@Override
	public String usarHabilidadEspecial(Enemigo objetivo, List<Enemigo> enemigosVivos, List<Heroe> aliadosVivos) {
		return habilidadEspecial.ejecutar(this, aliadosVivos);
	}

	@Override
	public void subirNivel() {
		super.subirNivel();
	}

	public HabEspCurador getHabilidadEspecial() { return habilidadEspecial; }
	public void setHabilidadEspecial(HabEspCurador h) { this.habilidadEspecial = h; }
}
