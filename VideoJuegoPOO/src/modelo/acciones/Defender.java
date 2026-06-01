package modelo.acciones;

import modelo.Accion;
import modelo.Entidad;

public class Defender extends Accion {
    private Entidad defensor;

    public Defender(Entidad defensor) {
        this.defensor = defensor;
    }

    @Override
    public void ejecutar() {
        System.out.println(defensor.getNombre() + " adopta una postura defensiva.");
    }
}
