package ZonaComedor;

public class TarjetaComarcaPlus implements Tarjeta{
    private float saldo;

    public TarjetaComarcaPlus(float saldo) {
        this.saldo = saldo;
    }

    public float pagar(float monto) {
        if(saldo < monto){
            throw new RuntimeException("Saldo insuficiente");
        }
        this.saldo -= monto;
        return monto;
    }
}
