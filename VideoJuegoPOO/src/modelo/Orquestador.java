package modelo;

public class Orquestador {
	private Batalla batallaActual;
	private int contadorTurnos;
	private Partida partidaActual;
	
	// Constructor
	public Orquestador (Batalla batallaActual, Partida partidaActual) {
		this.batallaActual = batallaActual;
		this.contadorTurnos = 1;
		this.partidaActual = partidaActual;
	}
	
	public void procesarSiguienteTurno (Entidad personaje, Accion accionElegida) {
		if (this.batallaActual.evaluarEstado() != EstadoBatalla.EN_CURSO) {
			System.out.println("La batalla ya terminó. No se pueden jugar más turnos");
			return;
		}
	    
		Turno nuevoTurno = new Turno(this.contadorTurnos, personaje);
	    nuevoTurno.procesarAccion(accionElegida);
	    this.contadorTurnos++;

	    // --- EL FINAL ---
	    EstadoBatalla estadoFinal = this.batallaActual.evaluarEstado();

	    if (estadoFinal == EstadoBatalla.VICTORIA) {
	        System.out.println("¡Los héroes han ganado la batalla!");
	        this.partidaActual.pasarSiguienteNivel();
	        
	    } else if (estadoFinal == EstadoBatalla.DERROTA) {
	        System.out.println("Game Over. Los enemigos ganaron.");
	        this.partidaActual.verificarEstadoPartida();
	    }
	}
}
