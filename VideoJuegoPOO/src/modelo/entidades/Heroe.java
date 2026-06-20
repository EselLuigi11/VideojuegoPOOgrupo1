package modelo.entidades;

import java.util.HashMap;

import modelo.Arma;
import modelo.Armadura;
import modelo.Entidad;
import modelo.Inventario;


public class Heroe extends Entidad {
	private static final long serialVersionUID = 1L;

	private int experiencia;
	private int nivel;
	private int mana;
	private int manaMax;
	private int probCrit;
	private int danoCrit;
	private Arma arma;
	private Armadura armadura;
    protected HashMap<Integer, StatsNivel> tablaDeNiveles;

	public Heroe(String nombre, int vida, int vidaMax,
			int ataque, int defensa,
			int velocidad, boolean estaDefendiendo,
			int experiencia, int nivel,
			int mana, int manaMax,
			int probCrit, int danoCrit,
			Arma arma, Armadura armadura) {

		super(nombre, vida, vidaMax, ataque, defensa, velocidad, estaDefendiendo);

        this.experiencia  = experiencia;
        this.nivel        = nivel;
        this.mana         = mana;
        this.manaMax      = manaMax;
        this.probCrit     = probCrit;
        this.danoCrit     = danoCrit;
        this.arma         = arma;
        this.armadura     = armadura;
        this.tablaDeNiveles = new HashMap<>();
	}

	public int calcularDanoBase() {
		int danoTotal = this.getAtaque();
		if (arma != null) {
			danoTotal += arma.getPlusDano();
		}
		return danoTotal;
	}

	public boolean verificarMana(int costoHabilidad) {
		return this.mana >= costoHabilidad;
	}

	public void consumirMana(int costoHabilidad) {
		if (verificarMana(costoHabilidad)) {
			this.mana -= costoHabilidad;
		}
	}

	public void recuperarMana(int cantidad) {
		this.mana += cantidad;
		if (this.mana > this.manaMax) {
			this.mana = this.manaMax;
		}
	}

	public void ganarExperiencia(int exp) {
		this.experiencia += exp;
		while (this.experiencia >= nivel * 105) {
			this.experiencia -= nivel * 105;
			subirNivel();
		}
	}

	public void subirNivel() {
		this.nivel++;
        System.out.println("¡" + this.getNombre() + " subió al nivel " + this.nivel + "!");

        StatsNivel statsDelNivel = tablaDeNiveles.get(this.nivel);
        if (statsDelNivel != null) {
            this.setVidaMax(statsDelNivel.getVidaMax());
            this.setAtaque(statsDelNivel.getAtaque());
            this.setDefensa(statsDelNivel.getDefensa());
            this.setVelocidad(statsDelNivel.getVelocidad());
            this.manaMax  = statsDelNivel.getManaMax();
            this.probCrit = statsDelNivel.getProbCrit();
            this.danoCrit = statsDelNivel.getDanoCrit();
            System.out.println("  → Stats aplicados desde tabla: nivel " + this.nivel);
        } else {
            this.setVidaMax(this.getVidaMax() + 20);
            this.setAtaque(this.getAtaque() + 5);
            this.setDefensa(this.getDefensa() + 5);
            this.setManaMax(this.getManaMax() + 10);
            System.out.println("  → Stats aplicados con incremento genérico (nivel no definido en tabla).");
        }
        this.setVida(this.getVidaMax());
        this.setMana(this.manaMax);
	}

	public String equiparArma(Arma arma, Inventario inventario) {
		if (!inventario.contieneItem(arma)) {
			return "El arma '" + arma.getNombre() + "' no está en la mochila.";
		}
		String msj = "";
		if (this.arma != null) {
			inventario.agregarItem(this.arma);
			msj += this.getNombre() + " desequipa " + this.arma.getNombre() + " y ";
		} else {
			msj += this.getNombre() + " ";
		}
		this.arma = arma;
		inventario.eliminarItem(arma);
		msj += "equipa " + arma.getNombre() + ".";
		return msj;
	}

	public String equiparArmadura(Armadura armadura, Inventario inventario) {
		if (!inventario.contieneItem(armadura)) {
			return "La armadura '" + armadura.getNombre() + "' no está en la mochila.";
		}
		String msj = "";
		if (this.armadura != null) {
			inventario.agregarItem(this.armadura);
			msj += this.getNombre() + " desequipa " + this.armadura.getNombre() + " y ";
		} else {
			msj += this.getNombre() + " ";
		}
		this.armadura = armadura;
		inventario.eliminarItem(armadura);
		msj += "equipa " + armadura.getNombre() + ".";
		return msj;
	}

	public void restaurarStatusCompleto() {
		this.setVida(this.getVidaMax());
		if (this.getManaMax() > 0) {
			this.setMana(this.getManaMax());
		}
		this.setEstaDefendiendo(false);
	}

	/**
	 * Devuelve un texto formateado con el desglose de stats: base + bonus de equipo.
	 * Ej: "Ataque: 20 (+8 arma) = 28"
	 *
	 * Cohesión POO: el cálculo de stats vive en el Modelo (Heroe), no en el Controlador.
	 */
	public String getResumenEstadisticas() {
		int bonusAtaque  = (arma != null) ? arma.getPlusDano() : 0;
		int bonusDefensa = (armadura != null) ? armadura.getplusDefensa() : 0;

		StringBuilder sb = new StringBuilder();
		sb.append(getNombre()).append("  (Nivel ").append(nivel).append(")\n");
		sb.append("──────────────────────────\n");
		sb.append("Vida:      ").append(getVida()).append(" / ").append(getVidaMax()).append("\n");
		sb.append("Mana:      ").append(mana).append(" / ").append(manaMax).append("\n");
		sb.append("Ataque:    ").append(getAtaque());
		if (bonusAtaque > 0) sb.append(" (+").append(bonusAtaque).append(" arma)");
		sb.append(" = ").append(calcularDanoBase()).append("\n");
		sb.append("Defensa:   ").append(getDefensa());
		if (bonusDefensa > 0) sb.append(" (+").append(bonusDefensa).append(" armadura)");
		sb.append(" = ").append(getDefensa() + bonusDefensa).append("\n");
		sb.append("Velocidad: ").append(getVelocidad()).append("\n");
		sb.append("Crítico:   ").append(probCrit).append("% (x").append(danoCrit / 100.0).append(")\n");
		sb.append("Arma:      ").append(arma != null ? arma.getNombre() : "Ninguna").append("\n");
		sb.append("Armadura:  ").append(armadura != null ? armadura.getNombre() : "Ninguna");
		return sb.toString();
	}

	public HashMap<Integer, StatsNivel> getTablaDeNiveles() { return tablaDeNiveles; }
	public void setTablaDeNiveles(HashMap<Integer, StatsNivel> t) { this.tablaDeNiveles = t; }

	public int getExperiencia() { return experiencia; }
	public void setExperiencia(int experiencia) { this.experiencia = experiencia; }

	public int getNivel() { return nivel; }
	public void setNivel(int nivel) { this.nivel = nivel; }

	public int getMana() { return mana; }
	public void setMana(int mana) { this.mana = mana; }

	public int getManaMax() { return manaMax; }
	public void setManaMax(int manaMax) { this.manaMax = manaMax; }

	public int getProbCrit() { return probCrit; }
	public void setProbCrit(int probCrit) { this.probCrit = probCrit; }

	public int getDanoCrit() { return danoCrit; }
	public void setDanoCrit(int danoCrit) { this.danoCrit = danoCrit; }

	public Arma getArma() { return arma; }
	public void setArma(Arma arma) { this.arma = arma; }

	public Armadura getArmadura() { return armadura; }
	public void setArmadura(Armadura armadura) { this.armadura = armadura; }
}