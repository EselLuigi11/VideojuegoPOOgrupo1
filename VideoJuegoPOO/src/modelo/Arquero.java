package modelo;

import java.util.List;

public class Arquero extends Heroe {
	//Decidir si ponemos limite o no de flechas. Si no, quitamos las lineas comentadas.
    //private int flechasMax;
    //private int flechasActuales;

    public Arquero(String nombre, Arma arma, Armadura armadura) {
        super(nombre,
            95, 95,     // vida, vidamax
            28, 10, 18, // ataque, defensa, velocidad
            false,		//estaDefendiendo
            0, 1,		// experiencia, nivel
            100, 0, 0,  // energia, mana, manaMax
            30, 160,    // prob crit, daño crit 
            null, arma, armadura); //Habilidades, arma, armadura
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