package modelo;

import java.util.List;

public class Arquero extends Heroe {

    //private int flechasMax;
    //private int flechasActuales;

    public Arquero(String nombre, Arma arma, Armadura armadura) {
        super(nombre,
            95, 95,     // vida media
            28, 10, 18, // ataque alto, defensa baja, velocidad alta
            false,
            0, 1,
            100, 0, 0,  // usa energía
            30, 160,    // prob crit alta, daño crit alto
            null, arma, armadura);
        //this.flechasMax = 20;
        //this.flechasActuales = 20;
    }

    @Override
    public void subirNivel() {
        super.subirNivel();
        this.setAtaque(this.getAtaque() + 6);     // más ataque extra
        this.setProbCrit(this.getProbCrit() + 2); // más crit extra
        //this.flechasMax += 5;
    }

    //public int getFlechasMax() { return flechasMax; }
    //public int getFlechasActuales() { return flechasActuales; }
    //public void setFlechasActuales(int flechasActuales) { this.flechasActuales = flechasActuales; }
}