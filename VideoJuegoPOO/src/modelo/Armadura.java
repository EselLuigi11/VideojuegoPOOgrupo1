package modelo;

import modelo.entidades.Heroe;

public class Armadura extends Item {
	private int defensa;
	
	public Armadura(String nombre, String descripcion, int defensa) {
		super(nombre, descripcion);
		this.defensa = defensa;
		
	}
	
	
	public void usar(Heroe heroe) {
		heroe.aumentarDefensa(defensa);
		System.out.println("Armadura equipada");
		
	}


	public int getDefensa() {
		return defensa;
	}


	public void setDefensa(int defensa) {
		this.defensa = defensa;
	}
	
	
	public String toString() {
		return super.toString() + 
				"| Defensa: " +
				defensa;
	}
}
