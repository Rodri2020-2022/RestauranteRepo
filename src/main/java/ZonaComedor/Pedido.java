package ZonaComedor;

import Menu.Bebida;
import Menu.ItemMenu;
import Menu.Plato;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class Pedido {

    private List<ItemMenu> items;
    private float total;
    private boolean pagado;
    private String tipoTarjetaLeida;
    private LocalDateTime fechaPagoRegistro;

    private RegistroDePago registro;
    private ProveedorDeFecha proveedor;



    public Pedido(ProveedorDeFecha proveedor, RegistroDePago registro, ItemMenu... items) {
        this.proveedor = proveedor;
        this.registro = registro;
        this.items = List.of(items);
        this.total = total();
    }

    public String tipoTarjetaLeida() {
        return tipoTarjetaLeida;
    }

    public LocalDateTime fechaYhoraRegistro() {
        return fechaPagoRegistro;
    }
    public LocalDate fechaRegistro(){
        return fechaPagoRegistro.toLocalDate();
    }

    public float totalBebidas() {
        float total = 0;
        for (ItemMenu itemMenu : items) {
            if (itemMenu instanceof Bebida) {
                total += itemMenu.precio();
            }
        }
        return total;
    }

    public float totalPlatos() {
        float total = 0;
        for (ItemMenu itemMenu : items) {
            if (itemMenu instanceof Plato) {
                total += itemMenu.precio();
            }
        }
        return total;
    }

    public float total(){
        float total = 0;
        for (ItemMenu itemMenu : items) {
            total += itemMenu.precio();
        }
        return total;
    }

    public void recibirPago(Tarjeta tarjeta){
        var precioTotal = this.total;
        leerTarjeta(tarjeta);
        this.pagado = true;

        this.fechaPagoRegistro = LocalDateTime.now();

        String fechaYprecio =
                proveedor.fecha().toString() + "||" + precioTotal + "||" + tarjeta.getClass().toString() + "\n";
        this.registro.registrar(fechaYprecio);

    }

    private void leerTarjeta(Tarjeta tarjeta) {
        if(tarjeta instanceof TarjetaVISA){
            this.total = this.total;
            //Obtener precio real con descuento aplicado
            this.total -= this.totalBebidas() * (1.00F - 0.03F);

            //Cobrar
            this.total -= tarjeta.pagar(total);
            this.total = total;
            //Verificar si se efectuo el pago
            if(total != 0) throw new RuntimeException("Pago imcompleto");
            this.tipoTarjetaLeida = "VISA";

        }else if (tarjeta instanceof TarjetaMastercard){

            //Obtener precio real con descuento aplicado
            var montoDeDescuento = this.total - this.totalPlatos() * (1.00F - 0.02F);
            this.total -= montoDeDescuento;

            //Cobrar
            this.total -= tarjeta.pagar(total);

            //Verificar si se efectuo el pago
            if(total != 0) throw new RuntimeException("Pago imcompleto");
            this.tipoTarjetaLeida = "MASTERCARD";

        }else if(tarjeta instanceof TarjetaComarcaPlus){

            //Obtener precio real con descuento aplicado
            var montoDeDescuento = this.total - this.total() * (1.00F - 0.02F);
            this.total -= montoDeDescuento;

            //Cobrar
            this.total -= tarjeta.pagar(total);

            //Verificar si se efectuo el pago
            if(total != 0) throw new RuntimeException("Pago imcompleto");
            this.tipoTarjetaLeida = "COMARCAPLUS";

        }else{

            //Cobrar
            this.total -= tarjeta.pagar(total);

            //Verificar si se efectuo el pago
            if(total != 0) throw new RuntimeException("Pago imcompleto");
            this.tipoTarjetaLeida = "VIEDMA";
        }
    }

    public boolean isPagado() {
        return pagado;
    }
}