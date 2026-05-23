package modelo;

public class Armadura extends Item {
	private int plusDefensa;

	public Armadura(String nombre, String descripcion, int plusDefensa) {
		super(nombre, descripcion);
		this.plusDefensa = plusDefensa;
	}
	
	public void usar(Heroe heroe) {
		heroe.setArmadura(this);
	}
	
}
