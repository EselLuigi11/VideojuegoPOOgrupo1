package modelo.mapa;
 
import modelo.entidades.Enemigo;
import modelo.entidades.Enemigo.TipoEnemigo;
import modelo.pociones.PocionVida;

public class ConstructorMapa {
	
	public static NodoMapa construirNivel1() {
		 
        // ── Nodo raíz ─────────────────────────────────────────
        NodoMapa inicio = new NodoMapa(
            "Te adentras en el mapa, se extienden dos posibles caminos.",
            TipoNodo.INICIO
        );
 
        // ── Camino A: BATALLA ─────────────────────────────────
        NodoMapa camBatalla = new NodoMapa(
            "Guarida de los Goblins — se escuchan dientes rechinar y chillidos.",
            TipoNodo.BATALLA
        );
        Enemigo goblin1 = new Enemigo("Goblin Pequeño",
            40, 40, 7, 5, 14, false, 25, 1, TipoEnemigo.GOBLIN); // vida baja, ataque bajo, defensa baja, velocidad alta
        Enemigo goblin2 = new Enemigo("Goblin Adulto",
            50, 50, 12, 15, 10, false, 30, 1, TipoEnemigo.GOBLIN);
        camBatalla.agregarEnemigo(goblin1);
        camBatalla.agregarEnemigo(goblin2);
 
        // ── Camino B: DESCANSO ────────────────────────────────
        NodoMapa camDescanso = new NodoMapa(
            "Zona protegida, aquí puedes descansar y recuperar tus heridas.",
            TipoNodo.DESCANSO
        );
        camDescanso.setCantidadDescanso(40); // recupera 40 de vida
 
        // ── Nodo compartido: RECOMPENSA ───────────────────────
        NodoMapa recompensa = new NodoMapa(
            "Cofre al fondo del pasillo — algo brilla dentro.",
            TipoNodo.RECOMPENSA
        );
        recompensa.setRecompensa(
            new PocionVida("Poción de Vida", "Restaura 50 puntos de vida.", 50)
        );
 
        // ── Nodo final: JEFE ──────────────────────────────────
        NodoMapa jefe = new NodoMapa(
            "Accedes al.",
            TipoNodo.JEFE
        );
        Enemigo goblinRey = new Enemigo("Goblin Rey",
            120, 120, 22, 18, 12, false, 100, 2, TipoEnemigo.GOBLIN);
        jefe.agregarEnemigo(goblinRey);
 
        // ── Conectar nodos ────────────────────────────────────
        inicio.agregarSiguiente(camBatalla);   // opción [1]
        inicio.agregarSiguiente(camDescanso);  // opción [2]
 
        camBatalla.agregarSiguiente(recompensa);
        camDescanso.agregarSiguiente(recompensa);
 
        recompensa.agregarSiguiente(jefe);
 
        return inicio; // El Orquestador arranca desde acá
    }
 
 
    /**
     * Construye el árbol del Nivel 2 — más complejo, tres caminos iniciales.
     *
     * Estructura:
     *
     *                  [INICIO]
     *             /      |      \
     *        [BAT]    [DESC]   [BAT]
     *       "Ladrones" "Fogón" "Brujos"
     *             \      |      /
     *            [RECOMPENSA]
     *                  |
     *               [JEFE]
     *            "El Brujo Mayor"
     */
    public static NodoMapa construirNivel2() {
 
        NodoMapa inicio = new NodoMapa(
            "Cruce de caminos — tres senderos se abren ante vosotros.",
            TipoNodo.INICIO
        );
 
        NodoMapa ladrones = new NodoMapa(
            "Refugio de Ladrones — emboscada.",
            TipoNodo.BATALLA
        );
        ladrones.agregarEnemigo(new Enemigo("Ladrón Veloz",
            55, 55, 18, 8, 20, false, 40, 2, TipoEnemigo.LADRON));
        ladrones.agregarEnemigo(new Enemigo("Ladrón Archer",
            45, 45, 22, 6, 18, false, 35, 2, TipoEnemigo.LADRON));
 
        NodoMapa fogonn = new NodoMapa(
            "Fogón abandonado — el calor restaura las fuerzas.",
            TipoNodo.DESCANSO
        );
        fogonn.setCantidadDescanso(60);
 
        NodoMapa brujos = new NodoMapa(
            "Torre de los Brujos — magia oscura en el aire.",
            TipoNodo.BATALLA
        );
        brujos.agregarEnemigo(new Enemigo("Brujo Aprendiz",
            60, 60, 20, 10, 14, false, 50, 2, TipoEnemigo.BRUJO));
 
        NodoMapa recompensa = new NodoMapa(
            "Bóveda secreta — un ítem valioso te aguarda.",
            TipoNodo.RECOMPENSA
        );
        recompensa.setRecompensa(
            new PocionVida("Poción de Vida Grande", "Restaura 80 puntos de vida.", 80)
        );
 
        NodoMapa jefe = new NodoMapa(
            "Cámara del Brujo Mayor — el suelo tiembla.",
            TipoNodo.JEFE
        );
        jefe.agregarEnemigo(new Enemigo("Brujo Mayor",
            200, 200, 35, 20, 15, false, 150, 3, TipoEnemigo.BRUJO));
 
        inicio.agregarSiguiente(ladrones);   // [1]
        inicio.agregarSiguiente(fogonn);     // [2]
        inicio.agregarSiguiente(brujos);     // [3]
 
        ladrones.agregarSiguiente(recompensa);
        fogonn.agregarSiguiente(recompensa);
        brujos.agregarSiguiente(recompensa);
 
        recompensa.agregarSiguiente(jefe);
 
        return inicio;
    }
}


