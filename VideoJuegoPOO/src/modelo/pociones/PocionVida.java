package modelo.pociones;

import modelo.Pocion;
import modelo.entidades.Heroe;

public class PocionVida extends Pocion {
	private int cantidadCuracion;
	
	//Constructor
	public PocionVida(String nombre, String descripcion, int cantidadCuracion) {
		super(nombre, descripcion);
		this.cantidadCuracion = cantidadCuracion;
	}
	
	@Override
	public void usar(Heroe heroe) {
		System.out.println("Usando " + this.getNombre() + " en " + heroe.getNombre());
		heroe.curarse(cantidadCuracion);
		System.out.println("¡ " + heroe.getNombre() + " ha recuperado " + this.cantidadCuracion + " puntos de vida!");
	}
}