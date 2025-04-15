package ZonaComedor;

public class TarjetaMastercard implements Tarjeta{
    private float saldo;

    public TarjetaMastercard(float saldo) {
        this.saldo = saldo;
    }

    public float pagar(float monto) {
        if(saldo < monto){
            throw new RuntimeException("Saldo insuficiente");
        }else{
            this.saldo -= monto;
        }
        return monto;
    }
}
