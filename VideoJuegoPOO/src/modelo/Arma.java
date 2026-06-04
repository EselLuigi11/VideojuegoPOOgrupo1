package modelo;

import modelo.entidades.Heroe;

public class Arma extends Item {
	private int plusdano;
	
	public Arma(String nombre, String descripcion, int daño) {
		super(nombre, descripcion);
		this.plusdano = daño;
	}
	
	public void usar(Heroe heroe) {
		heroe.aumentarAtaque(plusdano);
		System.out.println("Arma equipada");
		
	}

	public int getPlusDaño() {
		return plusdano;
	}

	public void setPlusDaño(int plusdaño) {
		this.plusdano = plusdaño;
	}
	
	public String toString() {
		return super.toString() + 
				"| Daño: " +
				plusdano;
	}

}
