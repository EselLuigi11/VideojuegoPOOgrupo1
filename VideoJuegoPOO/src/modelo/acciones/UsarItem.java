package modelo.acciones;

import modelo.Accion;
import modelo.Inventario;
import modelo.Item;
import modelo.Partida;
import modelo.entidades.Heroe;

public class UsarItem extends Accion {
    private Partida partida;
    private Heroe heroe;
    private Item item;
    private String mensajeEjecucion = "";

    public UsarItem(Partida partida, Heroe heroe, Item item) {
        this.partida = partida;
        this.heroe = heroe;
        this.item = item;
    }

    @Override
    public void ejecutar() {
        if (partida == null || heroe == null || item == null) {
            mensajeEjecucion = "No se puede usar el ítem porque faltan datos.";
            return;
        }

        if (!heroe.estaVivo()) {
            mensajeEjecucion = heroe.getNombre() + " no puede usar ítems porque está fuera de combate.";
            return;
        }

        Inventario inventario = partida.getInventarioPartida();

        if (inventario.contieneItem(item)) {
            item.usar(heroe);
            inventario.eliminarItem(item);
            mensajeEjecucion = heroe.getNombre() + " usa " + item.getNombre() + ".";
        } else {
            mensajeEjecucion = "El ítem " + item.getNombre() + " no está en el inventario.";
        }
    }

    @Override
    public String getMensajeEjecucion() {
        return mensajeEjecucion;
    }
}
