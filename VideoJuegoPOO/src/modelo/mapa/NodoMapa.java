package modelo.mapa;

import java.util.ArrayList;
import java.util.List;
 
import modelo.Item;
import modelo.entidades.Enemigo;
 
public class NodoMapa {
	private String descripcion;   // Texto que ve el jugador al llegar al nodo
    private TipoNodo tipo;
 
    // ─── Contenido (solo uno aplica según el tipo) ────────────
    private List<Enemigo> enemigos;   // Para BATALLA y JEFE
    private Item recompensa;          // Para RECOMPENSA
    private int cantidadDescanso;     // Para DESCANSO: puntos de vida que recupera la party
 
    // ─── Estructura del árbol ─────────────────────────────────
    private List<NodoMapa> siguientes;  // Caminos que salen de este nodo
    private boolean visitado;           // true si el jugador ya pasó por este nodo
 
    // ─── Constructor ──────────────────────────────────────────
    public NodoMapa(String descripcion, TipoNodo tipo) {
        this.descripcion      = descripcion;
        this.tipo             = tipo;
        this.siguientes       = new ArrayList<>();
        this.enemigos         = new ArrayList<>();
        this.recompensa       = null;
        this.cantidadDescanso = 0;
        this.visitado         = false;
    }
 
    // ─── Construcción del árbol ───────────────────────────────
 
    /** Conecta este nodo con un nodo siguiente (agrega un camino posible). */
    public void agregarSiguiente(NodoMapa nodo) {
        this.siguientes.add(nodo);
    }
 
    /** Agrega un enemigo a este nodo (para nodos BATALLA o JEFE). */
    public void agregarEnemigo(Enemigo enemigo) {
        this.enemigos.add(enemigo);
    }
 
    // ─── Acción del nodo ──────────────────────────────────────
 
    /**
     * Ejecuta lo que pasa al llegar a este nodo.
     * Muestra la descripción y el detalle según el tipo.
     * La lógica real (iniciar batalla, dar item, etc.) la maneja el Orquestador.
     */
    public void mostrarContenido() {
        System.out.println("\n══════════════════════════════════════");
        System.out.println("  " + descripcion);
        System.out.println("══════════════════════════════════════");
 
        switch (tipo) {
            case BATALLA:
                System.out.println("  Tipo: BATALLA");
                System.out.println("  Enemigos:");
                for (Enemigo e : enemigos) {
                    System.out.println("    · " + e.getNombre() +
                                       " (Nivel " + e.getNivelEnemigo() + ")");
                }
                break;
 
            case JEFE:
                System.out.println("  Tipo: ¡BATALLA CONTRA EL JEFE!");
                for (Enemigo e : enemigos) {
                    System.out.println("    ⚠ " + e.getNombre() +
                                       " (Nivel " + e.getNivelEnemigo() + ")");
                }
                break;
 
            case RECOMPENSA:
                System.out.println("  Tipo: RECOMPENSA");
                if (recompensa != null) {
                    System.out.println("  Encontraste: " + recompensa.getNombre());
                    System.out.println("  " + recompensa.getDescripcion());
                } else {
                    System.out.println("  El cofre está vacío...");
                }
                break;
 
            case DESCANSO:
                System.out.println("  Tipo: DESCANSO");
                System.out.println("  La party recupera " + cantidadDescanso + " puntos de vida.");
                break;
 
            case INICIO:
                System.out.println("  Tipo: INICIO - Elige tu primer camino.");
                break;
        }
 
        // Mostrar caminos disponibles si los hay
        if (!siguientes.isEmpty()) {
            System.out.println("\n  Caminos disponibles:");
            for (int i = 0; i < siguientes.size(); i++) {
                NodoMapa sig = siguientes.get(i);
                System.out.println("    [" + (i + 1) + "] " +
                                   sig.getDescripcion() +
                                   " (" + sig.getTipo() + ")");
            }
        } else {
            System.out.println("\n  → No hay más caminos. Fin del nivel.");
        }
    }
 
    /**
     * El jugador elige un camino por número (1, 2, ...).
     * Devuelve el nodo elegido o null si la opción no es válida.
     */
    public NodoMapa elegirCamino(int opcion) {
        int indice = opcion - 1;
        if (indice >= 0 && indice < siguientes.size()) {
            return siguientes.get(indice);
        }
        System.out.println("Opción inválida. Elegí entre 1 y " + siguientes.size() + ".");
        return null;
    }
 
    /** Marca el nodo como visitado cuando el jugador llega a él. */
    public void marcarVisitado() {
        this.visitado = true;
    }
 
    /** True si no tiene nodos siguientes (es el final del árbol). */
    public boolean esFinal() {
        return siguientes.isEmpty();
    }
 
    // ─── Getters y Setters ────────────────────────────────────
 
    public String getDescripcion()              { return descripcion; }
    public void setDescripcion(String d)        { this.descripcion = d; }
 
    public TipoNodo getTipo()                   { return tipo; }
 
    public List<Enemigo> getEnemigos()          { return enemigos; }
    public void setEnemigos(List<Enemigo> e)    { this.enemigos = e; }
 
    public Item getRecompensa()                 { return recompensa; }
    public void setRecompensa(Item item)        { this.recompensa = item; }
 
    public int getCantidadDescanso()            { return cantidadDescanso; }
    public void setCantidadDescanso(int cant)   { this.cantidadDescanso = cant; }
 
    public List<NodoMapa> getSiguientes()       { return siguientes; }
 
    public boolean isVisitado()                 { return visitado; }
}

