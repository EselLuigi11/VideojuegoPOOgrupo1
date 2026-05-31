package modelo;

import modelo.entidades.Heroe;

public class Arma extends Item {
	private int plusdaño;
	
	public Arma(String nombre, String descripcion, int daño) {
		super(nombre, descripcion);
		this.plusdaño = daño;
	}
	
	public void usar(Heroe heroe) {
		heroe.aumentarAtaque(plusdaño);
		System.out.println("Arma equipada");
		
	}

	public int getPlusDaño() {
		return plusdaño;
	}

	public void setPlusDaño(int plusdaño) {
		this.plusdaño = plusdaño;
	}
	
	public String toString() {
		return super.toString() + 
				"| Daño: " +
				plusdaño;
	}

}
