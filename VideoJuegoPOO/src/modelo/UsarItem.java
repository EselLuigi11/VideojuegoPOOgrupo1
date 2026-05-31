package modelo;

import modelo.entidades.Heroe;

public class UsarItem implements Accion {
    private Partida partida;
    private Heroe heroe; 
    private Item item;

    public UsarItem(Partida partida, Heroe heroe, Item item) {
        this.partida = partida;
        this.heroe = heroe;
        this.item = item;
    }

    @Override
    public void ejecutar() {
        Inventario inventario = partida.getInventarioPartida();
        
        if (inventario.contieneItem(item)) {
            inventario.eliminarItem(item);
            System.out.println(heroe.getNombre() + " saca " + item.getNombre() + " de la mochila.");
            
            item.usar(heroe); 
        } else {
            System.out.println("Error: El ítem " + item.getNombre() + " no está en el inventario.");
        }
    }
}
