package modelo.Entidades;

import java.util.List;

public class Asesino extends Heroe {
	//como idea, el asesino podría tener una habilidad de sigilo que le permita garantizar un ataque crítico en su próximo ataque, pero solo se puede usar una vez cada cierto tiempo o bajo ciertas condiciones. Esto lo haría un personaje más estratégico y emocionante de jugar, ya que el jugador tendría que decidir cuándo usar esa habilidad para maximizar su efectividad.
    //private boolean sigilo; // Si está en sigilo, el próximo ataque es crítico garantizado

    public Asesino(String nombre, Arma arma, Armadura armadura) {
        super(nombre,
            80, 80,     // vida, vidamax
            35, 8, 20,  // ataque , defensa, velocidad
            false,		//estaDefendiendo
            0, 1,		// experiencia, nivel
            120, 0, 0,  // energia, mana, manaMax
            40, 200,    // prob crit, daño crit 
            null, arma, armadura);//Habilidades, arma, armadura
        //this.sigilo = false;
    }

    
    //List<Habilidad> habilidades = new ArrayList<>();
    //habilidades.add(Habilidades.golpeBrutal());
    //habilidades.add(Habilidades.escudoDefensivo());
    //this.setHabilidades(habilidades);

    @Override
    public void subirNivel() {
        super.subirNivel();
        this.setAtaque(this.getAtaque() + 8);     // más ataque extra
        this.setProbCrit(this.getProbCrit() + 3); // más crit extra
    }

    //public boolean isSigilo() { return sigilo; }
    //public void setSigilo(boolean sigilo) { this.sigilo = sigilo; }
}