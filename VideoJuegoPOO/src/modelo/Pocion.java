package modelo;

import modelo.Entidades.Heroe;

public class Pocion extends Item {
	private int curacion;
	
	public Pocion(String nombre, String descripcion, int curacion) {
		super (nombre, descripcion);
		this.curacion= curacion;
		
	}
	
	public void usar(Heroe heroe) {
		heroe.curarse(curacion);
		
		System.out.println(heroe.getNombre() + "recupero " + curacion + " HPs");
	}

	public int getCuracion() {
		return curacion;
	}

	public void setCuracion(int curacion) {
		this.curacion = curacion;
	}
	
	public String toString() {
		return super.toString() +
	               " | Curación: " + curacion;

	}
	
	
}
