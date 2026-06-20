package modelo;

import modelo.entidades.Heroe;

public class Arma extends Item {
	private static final long serialVersionUID = 1L;
	private int plusdano;

	public Arma(String nombre, String descripcion, int dano) {
		super(nombre, descripcion != null ? descripcion : "");
		this.plusdano = dano;
	}

	public void usar(Heroe heroe) {
		heroe.equiparArma(this);
	}

	public int getPlusDano() {
		return plusdano;
	}

	public void setPlusDano(int plusdano) {
		this.plusdano = plusdano;
	}

	public String toString() {
		return super.toString() + "| Daño: " + plusdano;
	}
}