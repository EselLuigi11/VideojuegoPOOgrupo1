package modelo.pociones;

import modelo.Pocion;
import modelo.entidades.Heroe;

public class PocionEnergia extends Pocion {
	private int energiaRecuperada;
	
	public PocionEnergia(String nombre, String descripcion, int energiaRecuperada) {
		super(nombre, descripcion);
		this.energiaRecuperada = energiaRecuperada;
	}
	
	@Override
	public void usar(Heroe heroe) {
		System.out.println("Usando " + this.getNombre() + " en " + heroe.getNombre());
		heroe.recuperarEnergia(energiaRecuperada);
		System.out.println("¡" + heroe.getNombre() + " ha recuperado " + this.energiaRecuperada + " puntos de energía!");
	}
}