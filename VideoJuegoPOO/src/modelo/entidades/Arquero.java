package modelo.entidades;

import java.util.List;

import modelo.Arma;
import modelo.Armadura;
import modelo.habilidades.HabEspArquero;

public class Arquero extends Heroe implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	private HabEspArquero habilidadEspecial;

	public Arquero(String nombre, Arma arma, Armadura armadura) {
		super(nombre,
				95, 95,
				23, 10, 18,
				false,
				0, 1,
				70, 70,
				30, 160,
				arma, armadura);
		this.habilidadEspecial = new HabEspArquero();

		tablaDeNiveles.put(2, new StatsNivel(110, 37, 12, 20, 80, 34, 168));
		tablaDeNiveles.put(3, new StatsNivel(125, 46, 14, 22, 90, 38, 178));
		tablaDeNiveles.put(4, new StatsNivel(140, 56, 16, 24, 100, 43, 190));
		tablaDeNiveles.put(5, new StatsNivel(155, 67, 18, 26, 110, 49, 205));
	}

	@Override
	public String usarHabilidadEspecial(Enemigo objetivo, List<Enemigo> enemigosVivos, List<Heroe> aliadosVivos) {
		return habilidadEspecial.ejecutar(this, objetivo);
	}

	@Override
	public void subirNivel() {
		super.subirNivel();
	}

	public HabEspArquero getHabilidadEspecial() { return habilidadEspecial; }
	public void setHabilidadEspecial(HabEspArquero h) { this.habilidadEspecial = h; }
}
