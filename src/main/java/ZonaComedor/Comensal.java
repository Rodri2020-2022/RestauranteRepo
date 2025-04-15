package ZonaComedor;

public class Comensal {
    private String nombre;
    private String dni;
    private Tarjeta tarjeta;

    public Comensal(String nombre, String dni, Tarjeta tarjeta) {
        this.nombre = nombre;
        this.dni = dni;
        this.tarjeta = tarjeta;
    }

    public void pagarPedido(Pedido pedido){
        pedido.recibirPago(tarjeta);
    }


}
