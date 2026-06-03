package modelo;

import modelo.entidades.Heroe;

public abstract class Pocion extends Item {
	
	// Constructor
	public Pocion (String nombre, String descripcion) {
		super(nombre, descripcion);
	}
	/* HAY QUE HABILITAR ESTA FUNCION  AQ
	public void usarPocion(Heroe heroe, Pocion pocion) {
        if (inventarioPociones.contains(pocion)) {
            pocion.usar(heroe);
            inventarioPociones.remove(pocion);
        } else {
            System.out.println("La poción no está en el inventario.");
        }
    }
    */
}
