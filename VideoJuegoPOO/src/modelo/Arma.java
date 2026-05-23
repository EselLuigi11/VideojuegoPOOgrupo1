package modelo;

public class Arma extends Item {
	private int daño;
	
	public Arma(String nombre, String descripcion, int daño) {
		super(nombre, descripcion);
		this.daño = daño;
	}
	
	public void usar(Heroe heroe) {
		heroe.aumentarAtaque(daño);
		System.out.println("Arma equipada");
		
	}

	public int getDaño() {
		return daño;
	}

	public void setDaño(int daño) {
		this.daño = daño;
	}
	
	public String toString() {
		return super.toString() + 
				"| Daño: " +
				daño;
	}

}
