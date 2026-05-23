package modelo;

import java.util.List;

public class Mago extends Heroe {

    private int poderMagico; // Bonus al daño de habilidades mágicas

    public Mago(String nombre, Arma arma, Armadura armadura) {
        super(nombre,
            75, 75,     // vida muy baja
            15, 8, 16,  // ataque físico bajo, defensa baja, velocidad media-alta
            false,
            0, 1,
            60, 120, 120, // mucho mana
            20, 180,    // crit medio, daño crit alto
            null, arma, armadura);
        this.poderMagico = 30;
    }

    @Override
    public void subirNivel() {
        super.subirNivel();
        this.poderMagico += 8;                    // más poder mágico extra
        this.setManaMax(this.getManaMax() + 10);  // más mana extra
    }

    public int getPoderMagico(){ 
    	return poderMagico; 
    }
    
    public void setPoderMagico(int poderMagico){ 
    	this.poderMagico = poderMagico; 
    }
}