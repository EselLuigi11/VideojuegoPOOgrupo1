package modelo.habilidades;

import modelo.entidades.Arquero;
import modelo.entidades.Enemigo;

public class HabEspArquero implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	private String nombre = "Disparo Preciso";
	private int costoMana = 30;
	private double multiplicadorDano = 1.8;

	public String getNombre() { return nombre; }
	public int getCostoMana() { return costoMana; }

	public String ejecutar(Arquero arquero, Enemigo objetivo) {
		if (arquero == null || objetivo == null) {
			return "No se puede usar " + nombre + " sin usuario u objetivo.";
		}
		if (!arquero.estaVivo()) {
			return arquero.getNombre() + " no puede usar " + nombre + " porque está fuera de combate.";
		}
		if (!objetivo.estaVivo()) {
			return objetivo.getNombre() + " ya está derrotado.";
		}
		if (!arquero.verificarMana(costoMana)) {
			return arquero.getNombre() + " no tiene maná suficiente para usar " + nombre + ".";
		}

		arquero.consumirMana(costoMana);
		int dano = (int) Math.round(arquero.calcularDanoBase() * multiplicadorDano);
		objetivo.recibirDano(dano);

		return arquero.getNombre() + " usa " + nombre + " contra " + objetivo.getNombre()
			+ " y causa " + dano + " de daño.";
	}
}
