package modelo.Entidades;

import java.util.List;

public class Curador extends Heroe {


    public Curador(String nombre, Arma arma, Armadura armadura) {
        super(nombre,
            90, 90,     // vida media-baja
            10, 12, 14, // ataque bajo, defensa media, velocidad media
            false,
            0, 1,
            80, 80, 80, // mucho mana, sus habilidades lo consumen
            5, 100,     // casi sin crit
            null, arma, armadura);
    }

    public void curar() {

    }

    @Override
    public void subirNivel() {
        super.subirNivel();
        this.setManaMax(this.getManaMax() + 15);  // más mana extra
    }
    
}