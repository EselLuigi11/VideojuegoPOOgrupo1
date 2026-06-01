package modelo.entidades;

import java.util.List;

import modelo.Arma;
import modelo.Armadura;
import modelo.Entidad;
import modelo.acciones.Habilidad;

import java.util.ArrayList;

public class Heroe extends Entidad {
	
	private int experiencia;
	private int nivel;
	private int energia;
	private int energiaMax;
	private int mana;
	private int manaMax;
	private int probCrit;
	private int danoCrit;
	private List<Habilidad> habilidades;
	private modelo.Arma arma;
	private modelo.Armadura armadura;
	
	
	
	public Heroe(String nombre, int vida, int vidaMax, int ataque, int defensa, int velocidad, boolean estaDefendiendo,
			int experiencia, int nivel, int energia, int energiaMax, int mana, int manaMax, int probCrit, int danoCrit,
			List<Habilidad> habilidades, Arma arma, Armadura armadura) {
		super(nombre, vida, vidaMax, ataque, defensa, velocidad, estaDefendiendo);
		this.experiencia = experiencia;
		this.nivel = nivel;
		this.energia = energia;
		this.energiaMax = energiaMax;
		this.mana = mana;
		this.manaMax = manaMax;
		this.probCrit = probCrit;
		this.danoCrit = danoCrit;
		this.habilidades = new ArrayList<>();
		this.arma = arma;
		this.armadura = armadura;
	}

	public int calcularDanoBase() {
		int danoTotal = this.getAtaque();
		if (arma != null) {
			danoTotal += arma.getPlusDaño();
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
	
	public void recuperarEnergia(int cantidad) {
		this.energia += cantidad;
		
		if (this.energia > this.energiaMax) {
			this.energia = this.energiaMax;
		}
	}
	
	public void ganarExperiencia(int exp) {
		this.experiencia += exp;
		if (this.experiencia >= nivel * 105) {
			subirNivel();
		}
	}
	public void subirNivel() {
		this.nivel++;
		this.experiencia = 0;
		 // Al subir de nivel, podrías aumentar las estadísticas del héroe
		 this.setVidaMax(this.getVidaMax() + 20); // Ejemplo: aumentar
		 this.setVida(this.getVidaMax()); // Restaurar vida al subir de nivel
		 this.setAtaque(this.getAtaque() + 5);
		 this.setDefensa(this.getDefensa() + 5);
		 this.setManaMax(this.getManaMax() + 10);
		 this.setMana(this.getManaMax());
		 this.energiaMax += 10;
		 this.energia = this.energiaMax;
	}
	
	
	
	
	
	

	// Getters y Setters
	public int getExperiencia() {
		return experiencia;
	}

	public void setExperiencia(int experiencia) {
		this.experiencia = experiencia;
	}

	public int getNivel() {
		return nivel;
	}

	public void setNivel(int nivel) {
		this.nivel = nivel;
	}

	public int getEnergia() {
		return energia;
	}

	public void setEnergia(int energia) {
		this.energia = energia;
	}

	public int getMana() {
		return mana;
	}

	public void setMana(int mana) {
		this.mana = mana;
	}

	public int getManaMax() {
		return manaMax;
	}

	public void setManaMax(int manaMax) {
		this.manaMax = manaMax;
	}

	public int getProbCrit() {
		return probCrit;
	}

	public void setProbCrit(int probCrit) {
		this.probCrit = probCrit;
	}

	public int getDanoCrit() {
		return danoCrit;
	}

	public void setDanoCrit(int danoCrit) {
		this.danoCrit = danoCrit;
	}

	public List<Habilidad> getHabilidades() {
		return habilidades;
	}

	public void setHabilidades(List<Habilidad> habilidades) {
		this.habilidades = habilidades;
	}

	public Arma getArma() {
		return arma;
	}

	public void setArma(Arma arma) {
		this.arma = arma;
	}

	public Armadura getArmadura() {
		return armadura;
	}

	public void setArmadura(Armadura armadura) {
		this.armadura = armadura;
	}
}
