package modelo;

public class Partida {
	private int nivel;
	private boolean estado;
	private Party grupo;
	private Inventario inventarioPartida;
	
	public Partida() {
		this.nivel = 1;
		this.estado = true;
		this.grupo = new Party("Luchadores de la Luz"); //Nombre del grupo, se puede cambiar por input del usuario
		this.inventarioPartida = new Inventario(20); //20 objetos por inventario, podemos mejorar el espacio en algun nivel
	}
	
	public void pasarSiguienteNivel() {
		this.nivel++;
		System.out.println("Avanzaste al nivel: " + this.nivel + "!");
	}
	
	public void verificarEstadoPartida() {
		if (this.grupo.estaVivo() == false) {
			this.estado = false;
			System.out.println("Game Over");
		}
	}
	public Inventario getInventarioPartida() { 
		return inventarioPartida; 
	}

}
