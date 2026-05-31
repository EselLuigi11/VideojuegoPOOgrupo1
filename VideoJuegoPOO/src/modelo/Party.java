package modelo;

import java.util.ArrayList;
import java.util.List;

import modelo.entidades.Curador;
import modelo.entidades.Heroe;

public class Party {

    private String nombreParty;
    private List<Heroe> party;
    private List<Arma> inventarioArmas;
    private List<Armadura> inventarioArmaduras;
    private List<Pocion> inventarioPociones;

    // Constructor
    public Party(String nombreParty) {
        this.nombreParty = nombreParty;
        this.party = new ArrayList<>();
        this.inventarioArmas = new ArrayList<>();
        this.inventarioArmaduras = new ArrayList<>();
        this.inventarioPociones = new ArrayList<>();
    }

    // ─── HÉROES ───────────────────────────────────────────────

    public void agregarHeroe(Heroe heroe) {
        if (party.size() < 5) {
            party.add(heroe);
        } else {
            System.out.println("La party ya tiene 5 héroes, no se pueden agregar más.");
        }
    }

    public void eliminarHeroe(Heroe heroe) {
        party.remove(heroe);
    }

    public boolean estaVivo() {
        // La party sigue viva si al menos un héroe tiene vida
        for (Heroe h : party) {
            if (h.getVida() > 0) return true;
        }
        return false;
    }

    public Heroe getHeroeMasVida() {
        Heroe resultado = null;
        for (Heroe h : party) {
            if (resultado == null || h.getVida() > resultado.getVida()) {
                resultado = h;
            }
        }
        return resultado;
    }

    public Heroe getHeroeMenosVida() {
        Heroe resultado = null;
        for (Heroe h : party) {
            if (h.getVida() > 0 && (resultado == null || h.getVida() < resultado.getVida())) {
                resultado = h;
            }
        }
        return resultado;
    }

    public Curador getCurador() {
        for (Heroe h : party) {
            if (h instanceof Curador) return (Curador) h;
        }
        return null; // no hay curador en la party
    }

    public List<Heroe> getHeroesVivos() {
        List<Heroe> vivos = new ArrayList<>();
        for (Heroe h : party) {
            if (h.getVida() > 0) vivos.add(h);
        }
        return vivos;
    }


    // ─── INVENTARIO ARMAS ─────────────────────────────────────

    //si se obtiene arma se agrega al inventario, si se equipa a un heroe se quita del inventario,
    // si se desequipa de un heroe se vuelve a agregar al inventario, si se elimina del inventario se pierde para siempre
    public void agregarArma(Arma arma) {
        inventarioArmas.add(arma);
    }

    public void eliminarArma(Arma arma) {
        inventarioArmas.remove(arma);
    }

    public void equiparArma(Heroe heroe, Arma arma) {
        if (inventarioArmas.contains(arma)) {
            // Si el héroe ya tenía un arma, la devuelve al inventario
            if (heroe.getArma() != null) {
                inventarioArmas.add(modelo.entidades.getArma());
            }
            heroe.setArma(arma);
            inventarioArmas.remove(arma);
        } else {
            System.out.println("El arma no está en el inventario.");
        }
    }

    // ─── INVENTARIO ARMADURAS ─────────────────────────────────

    public void agregarArmadura(Armadura armadura) {
        inventarioArmaduras.add(armadura);
    }

    public void eliminarArmadura(Armadura armadura) {
        inventarioArmaduras.remove(armadura);
    }

    public void equiparArmadura(Heroe heroe, Armadura armadura) {
        if (inventarioArmaduras.contains(armadura)) {
            if (heroe.getArmadura() != null) {
                inventarioArmaduras.add(heroe.getArmadura());
            }
            heroe.setArmadura(armadura);
            inventarioArmaduras.remove(armadura);
        } else {
            System.out.println("La armadura no está en el inventario.");
        }
    }

    // ─── INVENTARIO POCIONES ──────────────────────────────────

    public void agregarPocion(Pocion pocion) {
        inventarioPociones.add(pocion);
    }

    public void usarPocion(Heroe heroe, Pocion pocion) {
        if (inventarioPociones.contains(pocion)) {
            pocion.usar(heroe);
            inventarioPociones.remove(pocion);
        } else {
            System.out.println("La poción no está en el inventario.");
        }
    }

    
    // ─── GETTERS Y SETTERS ────────────────────────────────────
	public String getNombreParty() {
		return nombreParty;
	}

	public void setNombreParty(String nombreParty) {
		this.nombreParty = nombreParty;
	}

	public List<Heroe> getParty() {
		return party;
	}

	public void setParty(List<Heroe> party) {
		this.party = party;
	}

	public List<Arma> getInventarioArmas() {
		return inventarioArmas;
	}

	public void setInventarioArmas(List<Arma> inventarioArmas) {
		this.inventarioArmas = inventarioArmas;
	}

	public List<Armadura> getInventarioArmaduras() {
		return inventarioArmaduras;
	}

	public void setInventarioArmaduras(List<Armadura> inventarioArmaduras) {
		this.inventarioArmaduras = inventarioArmaduras;
	}

	public List<Pocion> getInventarioPociones() {
		return inventarioPociones;
	}

	public void setInventarioPociones(List<Pocion> inventarioPociones) {
		this.inventarioPociones = inventarioPociones;
	}



}
