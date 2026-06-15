package modelo.acciones;

import modelo.Accion;
import modelo.Entidad;

public class Curar extends Accion {
    private Entidad curador;
    private Entidad objetivo;
    private int cantidad;
    private String mensajeEjecucion = "";

    public Curar(Entidad curador, Entidad objetivo, int cantidad) {
        this.curador = curador;
        this.objetivo = objetivo;
        this.cantidad = cantidad;
    }

    @Override
    public void ejecutar() {
        if (curador == null || objetivo == null) {
            mensajeEjecucion = "No se puede curar sin curador u objetivo.";
            return;
        }

        if (!curador.estaVivo()) {
            mensajeEjecucion = curador.getNombre() + " no puede curar porque está fuera de combate.";
            return;
        }

        if (!objetivo.estaVivo()) {
            mensajeEjecucion = objetivo.getNombre() + " está fuera de combate y no puede ser curado.";
            return;
        }

        objetivo.curarse(cantidad);
        mensajeEjecucion = curador.getNombre() + " cura a " + objetivo.getNombre() + " por " + cantidad + " puntos.";
    }

    @Override
    public String getMensajeEjecucion() {
        return mensajeEjecucion;
    }
}
