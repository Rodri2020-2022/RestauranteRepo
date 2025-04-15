package Menu;

public class Bebida extends ItemMenu{
    private float capacidadEnLitros;

    public Bebida(float precio, String descripcion, float litros) {
        super(precio, descripcion);
        this.capacidadEnLitros = litros;
    }

    public float capacidadEnLitros() {
        return capacidadEnLitros;
    }
}

