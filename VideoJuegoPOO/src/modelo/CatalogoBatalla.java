package modelo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import modelo.entidades.Enemigo;
import modelo.entidades.Enemigo.TipoEnemigo;
import modelo.entidades.Heroe;

public class CatalogoBatalla {

	private static CatalogoBatalla instancia;
	private Map<Integer, List<Enemigo>> batallas;

	private CatalogoBatalla() {
		batallas = new HashMap<>();
		cargarBatallas();
	}

	public static CatalogoBatalla getInstance() {
		if (instancia == null) {
			instancia = new CatalogoBatalla();
		}
		return instancia;
	}

	private void cargarBatallas() {
		List<Enemigo> batalla1 = new ArrayList<>();
		batalla1.add(new Enemigo("Goblin Explorador", 50, 50, 22, 10, 14, false, 30, 1, TipoEnemigo.GOBLIN));
		batalla1.add(new Enemigo("Goblin Escudo", 70, 70, 18, 25, 10, false, 25, 1, TipoEnemigo.GOBLIN));
		batalla1.add(new Enemigo("Goblin Salvaje", 50, 50, 24, 11, 16, false, 28, 1, TipoEnemigo.GOBLIN));
		batallas.put(1, batalla1);

		List<Enemigo> batalla2 = new ArrayList<>();
		batalla2.add(new Enemigo("Goblin Escudo", 70, 70, 18, 25, 10, false, 25, 1, TipoEnemigo.GOBLIN));
		batalla2.add(new Enemigo("Ladrón Veloz", 65, 65, 22, 9, 20, false, 40, 2, TipoEnemigo.LADRON));
		batalla2.add(new Enemigo("Ladrón Arquero", 50, 50, 27, 10, 18, false, 35, 2, TipoEnemigo.LADRON));
		batallas.put(2, batalla2);

		List<Enemigo> batalla3 = new ArrayList<>();
		batalla3.add(new Enemigo("Brujo Aprendiz", 60, 60, 30, 10, 14, false, 50, 2, TipoEnemigo.BRUJO));
		batalla3.add(new Enemigo("Brujo Oscuro", 70, 70, 25, 12, 12, false, 60, 2, TipoEnemigo.BRUJO));
		batalla3.add(new Enemigo("Gólem de Piedra", 180, 180, 28, 35, 6, false, 120, 3, TipoEnemigo.GOLEM));
		batallas.put(3, batalla3);
	}

	/**
	 * Devuelve una Batalla con instancias CLONADAS de los enemigos base.
	 * El Map interno actúa como plantilla inmutable; cada llamada genera
	 * entidades frescas, evitando que el estado de una batalla anterior
	 * (vida, defensa, muerte) persista entre reintentos o nuevas partidas.
	 */
	public Batalla construirBatalla(int numeroBatalla, List<Heroe> heroesVivos) {
		List<Enemigo> plantilla = batallas.get(numeroBatalla);
		if (plantilla == null) {
			System.out.println("No existe la batalla número " + numeroBatalla);
			return null;
		}

		List<Enemigo> enemigosFrescos = new ArrayList<>();
		for (Enemigo base : plantilla) {
			enemigosFrescos.add(new Enemigo(base));
		}

		return new Batalla(heroesVivos, enemigosFrescos);
	}

	public int getTotalBatallas() {
		return batallas.size();
	}
}
