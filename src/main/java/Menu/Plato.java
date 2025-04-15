package Menu;

public class Plato extends ItemMenu {
    private TIPO tipoDePlato;

    public Plato(String descripcion, float precio, TIPO tipodePlato) {
        super(precio, descripcion);
        this.tipoDePlato = tipodePlato;
    }

    public String tipoDePlato() {
        return this.tipoDePlato.name();
    }
}