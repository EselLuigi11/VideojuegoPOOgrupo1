package modelo;

import modelo.entidades.Heroe;

public class PocionVelocidad extends Pocion {
	private int aumentoVel;
	
	
	public PocionVelocidad(String nombre, String descripcion, int aumentoVel) {
		super(nombre, descripcion, 0);
		this.aumentoVel = aumentoVel;
		
	}
	
	public void usar(Heroe heroe) {
		heroe.aumentarVel(aumentoVel);
		System.out.println(heroe.getNombre() + "aumento su velocidad en " + aumentoVel);
	}

	public int getAumentoVel() {
		return aumentoVel;
	}

	public void setAumentoVel(int aumentoVel) {
		this.aumentoVel = aumentoVel;
	}
	
	  public String toString() {

	        return super.toString() +
	               " | Aumento de velocidad: " +
	               aumentoVel;
	    }
	

}
