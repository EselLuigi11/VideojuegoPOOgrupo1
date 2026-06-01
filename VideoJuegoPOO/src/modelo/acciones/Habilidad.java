package modelo.acciones;

import modelo.Accion;
import modelo.Entidad;

public class Habilidad extends Accion {
    private Entidad usuario;
    private Entidad objetivo;
    private String nombreHabilidad;

    public Habilidad(Entidad usuario, Entidad objetivo, String nombreHabilidad) {
        this.usuario = usuario;
        this.objetivo = objetivo;
        this.nombreHabilidad = nombreHabilidad;
    }

    @Override
    public void ejecutar() {
        System.out.println(usuario.getNombre() + " usa su habilidad especial: " + nombreHabilidad + " en " + objetivo.getNombre());
    }
}
