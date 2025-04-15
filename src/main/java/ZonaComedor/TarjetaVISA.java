package ZonaComedor;

public class TarjetaVISA implements Tarjeta{
    private float saldo;

    public TarjetaVISA(float saldo) {
        this.saldo = saldo;
    }

    @Override
    public float pagar(float monto) {
        if(saldo < monto){
            throw new RuntimeException("Saldo insuficiente");
        }else{
            this.saldo -= monto;
        }
        return monto;
    }
}
