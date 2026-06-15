package modelo.habilidades;

import modelo.entidades.Asesino;
import modelo.entidades.Enemigo;

public class HabEspAsesino implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	private String nombre = "Puñalada Certera";
	private int costoMana = 45;
	private double multiplicadorDano = 2.4;

	public String getNombre() { return nombre; }
	public int getCostoMana() { return costoMana; }

	public String ejecutar(Asesino asesino, Enemigo objetivo) {
		if (asesino == null || objetivo == null) {
			return "No se puede usar " + nombre + " sin usuario u objetivo.";
		}
		if (!asesino.estaVivo()) {
			return asesino.getNombre() + " no puede usar " + nombre + " porque está fuera de combate.";
		}
		if (!objetivo.estaVivo()) {
			return objetivo.getNombre() + " ya está derrotado.";
		}
		if (!asesino.verificarMana(costoMana)) {
			return asesino.getNombre() + " no tiene maná suficiente para usar " + nombre + ".";
		}

		asesino.consumirMana(costoMana);
		int dano = (int) Math.round(asesino.calcularDanoBase() * multiplicadorDano);
		objetivo.recibirDano(dano);

		return asesino.getNombre() + " usa " + nombre + " contra " + objetivo.getNombre()
			+ " y causa " + dano + " de daño crítico.";
	}
}
