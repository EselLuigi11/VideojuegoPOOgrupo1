package modelo;

import java.util.ArrayList;
import java.util.List;

import modelo.entidades.Arquero;
import modelo.entidades.Asesino;
import modelo.entidades.Curador;
import modelo.entidades.Enemigo;
import modelo.entidades.Guerrero;
import modelo.entidades.Heroe;
import modelo.entidades.Mago;
import modelo.pociones.PocionMana;
import modelo.pociones.PocionVida;

public class Batalla {
	private List<Heroe> heroes;
	private List<Enemigo> enemigos;
	private EstadoBatalla estadoActual;

	public Batalla(List<Heroe> heroes, List<Enemigo> enemigos) {
		this.heroes = heroes;
		this.enemigos = enemigos;
		estadoActual = EstadoBatalla.EN_CURSO;
	}

	public EstadoBatalla evaluarEstado() {
		boolean todosHeroesMuertos = true;
		for (Heroe h : heroes) {
			if (h != null && h.estaVivo()) {
				todosHeroesMuertos = false;
			}
		}

		boolean todosEnemigosMuertos = true;
		for (Enemigo e : enemigos) {
			if (e != null && e.estaVivo()) {
				todosEnemigosMuertos = false;
			}
		}

		if (todosHeroesMuertos) {
			estadoActual = EstadoBatalla.DERROTA;
		} else if (todosEnemigosMuertos) {
			estadoActual = EstadoBatalla.VICTORIA;
		} else {
			estadoActual = EstadoBatalla.EN_CURSO;
		}
		return estadoActual;
	}

	public boolean hayEnemigosVivos() {
		for (Enemigo e : enemigos) {
			if (e != null && e.estaVivo()) return true;
		}
		return false;
	}

	public List<Heroe> getHeroesVivos() {
		List<Heroe> vivos = new ArrayList<>();
		for (Heroe h : heroes) {
			if (h != null && h.estaVivo()) vivos.add(h);
		}
		return vivos;
	}

	public List<Enemigo> getEnemigosVivos() {
		List<Enemigo> vivos = new ArrayList<>();
		for (Enemigo e : enemigos) {
			if (e != null && e.estaVivo()) vivos.add(e);
		}
		return vivos;
	}

	public int getExperienciaTotalOtorgada() {
		int total = 0;
		for (Enemigo e : enemigos) {
			if (e != null) total += e.getExperienciaOtorgada();
		}
		return total;
	}

	/**
	 * Otorga recompensas al terminar una batalla.
	 * Devuelve la lista de ítems entregados para que el Controlador
	 * los muestre en el mensaje de victoria.
	 *
	 * Batalla 1 → armadura por clase, se equipa automáticamente.
	 * Batalla 2 → arma por clase, se equipa automáticamente.
	 * Batalla 3 → pociones y equipo varios (sin cambios).
	 */
	public List<Item> otorgarRecompensas(Inventario inv, int numeroBatalla) {
		List<Item> itemsEntregados = new ArrayList<>();
		if (inv == null) return itemsEntregados;

		List<Heroe> vivos = getHeroesVivos();
		System.out.println("=== Recompensas de la batalla " + numeroBatalla + " ===");

		switch (numeroBatalla) {

			case 1:
				// Una armadura por clase para cada héroe vivo.
				// Se equipa automáticamente (equiparArmadura ya descuenta el bonus
				// de la armadura anterior si había una, evitando acumulación).
				for (Heroe h : vivos) {
					Armadura armadura;
					if (h instanceof Guerrero) {
						armadura = new Armadura("Armadura de Hierro", null, 15);
					} else if (h instanceof Arquero) {
						armadura = new Armadura("Coraza de Cuero", null, 10);
					} else if (h instanceof Asesino) {
						armadura = new Armadura("Cota de Sombra", null, 8);
					} else if (h instanceof Mago) {
						armadura = new Armadura("Manto Arcano", null, 6);
					} else if (h instanceof Curador) {
						armadura = new Armadura("Veste Sagrada", null, 7);
					} else {
						armadura = new Armadura("Armadura Simple", null, 5);
					}
					h.equiparArmadura(armadura);          // auto-equip
					itemsEntregados.add(armadura);
					System.out.println(h.getNombre() + " equipó: " + armadura.getNombre()
						+ " (+" + armadura.getplusDefensa() + " DEF)");
				}
				// Pociones de vida adicionales
				PocionVida pv1 = new PocionVida("Poción de Vida", "Restaura 50 HP.", 50);
				PocionVida pv2 = new PocionVida("Poción de Vida", "Restaura 50 HP.", 50);
				inv.agregarItem(pv1);
				inv.agregarItem(pv2);
				itemsEntregados.add(pv1);
				itemsEntregados.add(pv2);
				break;

			case 2:
				// Un arma por clase para cada héroe vivo.
				// Se equipa automáticamente.
				for (Heroe h : vivos) {
					Arma arma;
					if (h instanceof Guerrero) {
						arma = new Arma("Espada de Acero", null, 14);
					} else if (h instanceof Arquero) {
						arma = new Arma("Arco Largo", null, 12);
					} else if (h instanceof Asesino) {
						arma = new Arma("Daga Serrada", null, 16);
					} else if (h instanceof Mago) {
						arma = new Arma("Bastón de Cristal", null, 13);
					} else if (h instanceof Curador) {
						arma = new Arma("Báculo de Luz", null, 8);
					} else {
						arma = new Arma("Arma Simple", null, 6);
					}
					h.equiparArma(arma);                  // auto-equip
					itemsEntregados.add(arma);
					System.out.println(h.getNombre() + " equipó: " + arma.getNombre()
						+ " (+" + arma.getPlusDano() + " ATQ)");
				}
				// Pociones adicionales
				PocionVida pvm1 = new PocionVida("Poción de Vida Mayor", "Restaura 80 HP.", 80);
				PocionVida pvm2 = new PocionVida("Poción de Vida Mayor", "Restaura 80 HP.", 80);
				PocionMana pm   = new PocionMana("Poción de Maná", "Restaura 40 MP.", 40);
				inv.agregarItem(pvm1);
				inv.agregarItem(pvm2);
				inv.agregarItem(pm);
				itemsEntregados.add(pvm1);
				itemsEntregados.add(pvm2);
				itemsEntregados.add(pm);
				break;

			case 3:
				PocionVida elixir1 = new PocionVida("Elixir de Vida", "Restaura 120 HP.", 120);
				PocionVida elixir2 = new PocionVida("Elixir de Vida", "Restaura 120 HP.", 120);
				Arma baculo        = new Arma("Báculo del Gólem", "Forjado del núcleo del gólem.", 18);
				Armadura loriga    = new Armadura("Loriga de Piedra", "Alta defensa del gólem.", 20);
				inv.agregarItem(elixir1);
				inv.agregarItem(elixir2);
				inv.agregarItem(baculo);
				inv.agregarItem(loriga);
				itemsEntregados.add(elixir1);
				itemsEntregados.add(elixir2);
				itemsEntregados.add(baculo);
				itemsEntregados.add(loriga);
				break;

			default:
				PocionVida def = new PocionVida("Poción de Vida", "Restaura 50 HP.", 50);
				inv.agregarItem(def);
				itemsEntregados.add(def);
				break;
		}

		return itemsEntregados;
	}

	public List<Enemigo> getEnemigos() { return enemigos; }
	public List<Heroe> getHeroes()    { return heroes; }
}