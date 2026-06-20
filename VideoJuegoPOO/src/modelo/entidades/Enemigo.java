package modelo.entidades;

import java.util.List;
import java.util.Random;

import modelo.Entidad;

public class Enemigo extends Entidad {

	public enum TipoEnemigo {
		GOBLIN, LADRON, BRUJO, GOLEM, DRAGON
	}

	private int experienciaOtorgada;
	private int nivelEnemigo;
	private TipoEnemigo tipo;

	public Enemigo(String nombre, int vida, int vidaMax, int ataque, int defensa, int velocidad,
			boolean estaDefendiendo, int experienciaOtorgada, int nivelEnemigo, TipoEnemigo tipo) {
		super(nombre, vida, vidaMax, ataque, defensa, velocidad, estaDefendiendo);
		this.experienciaOtorgada = experienciaOtorgada;
		this.nivelEnemigo = nivelEnemigo;
		this.tipo = tipo;
	}

	/**
	 * Constructor de copia: genera una instancia fresca con vida al máximo,
	 * desacoplada del enemigo original del catálogo. Evita que el estado
	 * (vida, defensa) de una batalla anterior contamine la siguiente.
	 */
	public Enemigo(Enemigo origen) {
		this(origen.getNombre(), origen.getVidaMax(), origen.getVidaMax(), origen.getAtaque(),
				origen.getDefensa(), origen.getVelocidad(), false,
				origen.experienciaOtorgada, origen.nivelEnemigo, origen.tipo);
	}

	public String EnemigoAtaca(List<Heroe> heroes) {
		if (heroes == null || heroes.isEmpty()) return "";
		Heroe objetivo = null;
		Random rand = new Random();
		switch (this.tipo) {
			case GOBLIN:
			case BRUJO:
				objetivo = heroes.get(rand.nextInt(heroes.size()));
				break;
			case LADRON:
				objetivo = heroes.get(0);
				break;
			case GOLEM:
				objetivo = heroes.get(0);
				for (Heroe h : heroes) {
					if (h.getVida() > objetivo.getVida()) objetivo = h;
				}
				break;
			case DRAGON:
				Heroe curador = null;
				for (Heroe h : heroes) {
					if (h instanceof Curador) { curador = h; break; }
				}
				if (curador == null) {
					objetivo = heroes.get(rand.nextInt(heroes.size()));
				} else {
					Heroe masDebil = curador;
					for (Heroe h : heroes) {
						if (h.getVida() < masDebil.getVida()) masDebil = h;
					}
					objetivo = masDebil;
				}
				break;
		}

		if (objetivo != null) {
			int vidaAntes = objetivo.getVida();
			objetivo.recibirDano(this.getAtaque());
			int danoReal = vidaAntes - objetivo.getVida();
			return this.getNombre() + " ataca a " + objetivo.getNombre() + " por " + danoReal + " de daño.";
		}
		return this.getNombre() + " no encontró objetivo.";
	}

	public int calcularDanoRecibeEnemigo(int cantDano) {
		if (this.isEstaDefendiendo()) {
			cantDano *= 0.7;
		}
		return cantDano;
	}

	public void recibirDano(int cantidadDano) {
		int danoFinal = calcularDanoRecibeEnemigo(cantidadDano);
		int vidaRestante = this.getVida() - danoFinal;
		this.setVida(Math.max(vidaRestante, 0));
	}

	public void otorgarExperiencia(Heroe heroe) {
		heroe.ganarExperiencia(this.experienciaOtorgada);
	}

	public int getExperienciaOtorgada() {
		return experienciaOtorgada;
	}

	public void setExperienciaOtorgada(int experienciaOtorgada) {
		this.experienciaOtorgada = experienciaOtorgada;
	}

	public int getNivelEnemigo() {
		return nivelEnemigo;
	}

	public void setNivelEnemigo(int nivelEnemigo) {
		this.nivelEnemigo = nivelEnemigo;
	}

	public TipoEnemigo getTipo() {
		return tipo;
	}

	public void setTipo(TipoEnemigo tipo) {
		this.tipo = tipo;
	}
}
