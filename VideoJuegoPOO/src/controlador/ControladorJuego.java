package controlador;

import modelo.Partida;
import modelo.Orquestador;
import modelo.vista.VistaMenuPrincipal;
import modelo.vista.VistaBatalla;
import modelo.vista.VistaInventario;

public class ControladorJuego {
	@SuppressWarnings("unused")
	private Partida partida;
	@SuppressWarnings("unused")
	private Orquestador orquestador;
	private VistaMenuPrincipal vistaMenu;
	private VistaBatalla vistaBatalla;
	private VistaInventario vistaInventario;
	
	public ControladorJuego(Partida partida, Orquestador orquestador, VistaMenuPrincipal vistaMenu, VistaBatalla vistaBatalla, VistaInventario vistaInventario) {
		this.partida = partida;
		this.orquestador = orquestador;
		this.vistaMenu = vistaMenu;
		this.vistaBatalla = vistaBatalla;
		this.vistaInventario = vistaInventario;
	}

	public void iniciar() {
		this.vistaMenu.setVisible(true);

		// Botón Nueva Partida
		this.vistaMenu.btnNuevaPartida.addActionListener(e -> {
			System.out.println("Iniciando el juego...");
			this.vistaMenu.setVisible(false);    // Apagamos el menú
			this.vistaBatalla.setVisible(true);  // Prendemos la pantalla de la batalla
		});

		// Botón Salir
		this.vistaMenu.btnSalir.addActionListener(e -> {
			System.out.println("Cerrando el juego. ¡Adiós!");
			System.exit(0); // Cierra todo el programa de Java
		});
		
		// 1. MICRÓFONO PARA "ATACAR"
		this.vistaBatalla.getPanelAcciones().getBtnAtacar().addActionListener(e -> {
			System.out.println("¡El Héroe eligió ATACAR!");
		});

		// 2. MICRÓFONO PARA "DEFENDER"
		this.vistaBatalla.getPanelAcciones().getBtnDefender().addActionListener(e -> {
			System.out.println("¡El Héroe se puso en posición de DEFENSA!");
		});
		
		// 3. MICRÓFONO PARA "USAR ÍTEM"
		this.vistaBatalla.getPanelAcciones().getBtnUsarItem().addActionListener(e -> {
			System.out.println("¡El Héroe quiere USAR UNA POCIÓN!");
			// Mostramos la vista del inventario
			this.vistaInventario.setVisible(true);
		});
	}
}
