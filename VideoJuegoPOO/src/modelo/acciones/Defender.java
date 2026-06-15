package modelo.acciones;

import modelo.Accion;
import modelo.Entidad;

public class Defender extends Accion {
    private Entidad defensor;
    private String mensajeEjecucion = "";

    public Defender(Entidad defensor) {
        this.defensor = defensor;
    }

    @Override
    public void ejecutar() {
        if (defensor == null) {
            mensajeEjecucion = "No se puede defender sin personaje.";
            return;
        }

        if (!defensor.estaVivo()) {
            mensajeEjecucion = defensor.getNombre() + " no puede defenderse porque está fuera de combate.";
            return;
        }

        defensor.defenderse();
        mensajeEjecucion = defensor.getNombre() + " se defiende.";
    }

    public String getMensajeEjecucion() {
        return mensajeEjecucion;
    }
}