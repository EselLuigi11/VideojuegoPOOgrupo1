public class Defender implements Accion {
    private Entidad defensor;

    public Defender(Entidad defensor) {
        this.defensor = defensor;
    }

    @Override
    public void ejecutar() {
        System.out.println("🛡️ " + defensor.getNombre() + " adopta una postura defensiva.");
        // Lógica para subir defensa temporal (dependerá de cómo Ignacio arme la Entidad)
    }
}
