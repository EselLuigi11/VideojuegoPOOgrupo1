package modelo;

public class Arma extends Item {
	private int plusDano;
	public Arma(String nombre, String descripcion, int plusDano) {
		super(nombre, descripcion);
		this.plusDano = plusDano;
	}
	
	public void usar(Heroe heroe) {
		heroe.setArma(this);
	}
	
	
	
//Getters y Setters
	public int getPlusDano() {
		return plusDano;
	}

	public void setPlusDano(int plusDano) {
		this.plusDano = plusDano;
	}
	
	
	
}
