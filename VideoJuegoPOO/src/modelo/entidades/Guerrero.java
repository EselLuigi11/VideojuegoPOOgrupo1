package modelo.entidades;

import java.util.List;

public class Guerrero extends Heroe {


    public Guerrero(String nombre, Arma arma, Armadura armadura) {
        super(nombre,
            150, 150,   // vida alta
            20, 25, 8,  // ataque medio, defensa alta, velocidad baja
            false,
            0, 1,
            100, 0, 0,  // sin mana, usa energía
            5, 150,     // poca prob crit, pero daño crit alto
            null, arma, armadura);
    }

    @Override
    public void subirNivel() {
        super.subirNivel();
        this.setVidaMax(this.getVidaMax() + 30);  // más vida extra
        this.setDefensa(this.getDefensa() + 20);   // más defensa extra
    }

}