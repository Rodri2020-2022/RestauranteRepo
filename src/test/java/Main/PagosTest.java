package Main;

import Menu.Menu;
import Persistencia.EnDiscoRegistroDePago;
import ZonaComedor.*;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;

public class PagosTest {

    @Test
    public void pagarVISA() {
        //Setup
        var item1 = Menu.MILANESA_NAPOLITANA;
        var item2 = Menu.COCACOLA;
        var item3 = Menu.EMPANADAS;
        var pedido1 = new Pedido(new ProveedorDeFecha() {
            @Override
            public LocalDateTime fecha() {
                return LocalDateTime.now();
            }
        }, new EnDiscoRegistroDePago("C:Users/rodri/Desktop/registroDePagos2025.txt"), item1, item2, item3);

        var tarjeta = new TarjetaVISA(40000.00F);

        Comensal cliente = new Comensal("Rodrigo Quichan", "2333", tarjeta);
        //Execution
        cliente.pagarPedido(pedido1);

        //Verification
        Assert.assertTrue(pedido1.isPagado());
    }

    @Test
    public void pagarMasterCard() {
        //Setup
        var item1 = Menu.MILANESA_NAPOLITANA;
        var item2 = Menu.COCACOLA;
        var item3 = Menu.EMPANADAS;
        var pedido1 = new Pedido(new ProveedorDeFecha() {
            @Override
            public LocalDateTime fecha() {
                return LocalDateTime.now();
            }
        }, new EnDiscoRegistroDePago("C:Users/rodri/Desktop/registroDePagos2025.txt"), item1, item2, item3);

        var tarjeta = new TarjetaMastercard(40000.00F);

        Comensal cliente = new Comensal("Rodrigo Quichan", "2333", tarjeta);
        //Execution
        cliente.pagarPedido(pedido1);
        //Verification
        Assert.assertTrue(pedido1.isPagado());
    }

    @Test
    public void pagarComarcaPlus() {
        //Setup
        var item1 = Menu.MILANESA_NAPOLITANA;
        var item2 = Menu.COCACOLA;
        var item3 = Menu.EMPANADAS;
        var pedido1 = new Pedido(new ProveedorDeFecha() {
            @Override
            public LocalDateTime fecha() {
                return LocalDateTime.now();
            }
        }, new EnDiscoRegistroDePago("C:Users/rodri/Desktop/registroDePagos2025.txt"), item1, item2, item3);

        var tarjeta = new TarjetaComarcaPlus(40000.00F);

        Comensal cliente = new Comensal("Rodrigo Quichan", "2333", tarjeta);
        //Execution
        cliente.pagarPedido(pedido1);
        //Verification
        Assert.assertTrue(pedido1.isPagado());
    }
    @Test
    public void pagarViedma() {
        //Setup
        var item1 = Menu.MILANESA_NAPOLITANA;
        var item2 = Menu.COCACOLA;
        var item3 = Menu.EMPANADAS;
        var pedido1 = new Pedido(
                new ProveedorDeFecha() {
            @Override
            public LocalDateTime fecha() {
                return LocalDateTime.now();
            }
        }, new EnDiscoRegistroDePago("C:Users/rodri/Desktop/registroDePagos2025.txt"), item1, item2, item3);

        var tarjeta = new TarjetaViedma(40000.00F);

        Comensal cliente = new Comensal("Rodrigo Quichan", "2333", tarjeta);
        //Execution
        cliente.pagarPedido(pedido1);
        //Verification
        Assert.assertTrue(pedido1.isPagado());
    }
    @Test
    public void errorPago(){
        //Setup
        var item1 = Menu.MILANESA_NAPOLITANA;
        var item2 = Menu.COCACOLA;
        var item3 = Menu.EMPANADAS;
        var pedido1 = new Pedido(new ProveedorDeFecha() {
            @Override
            public LocalDateTime fecha() {
                return LocalDateTime.now();
            }
        }, new EnDiscoRegistroDePago("C:Users/rodri/Desktop/registroDePagos2025.txt"), item1, item2, item3);

        var tarjeta = new TarjetaVISA(10000.00F);

        Comensal cliente = new Comensal("Rodrigo Quichan", "2333", tarjeta);

        //Verification
        RuntimeException r1 = Assert.assertThrows(RuntimeException.class, () -> cliente.pagarPedido(pedido1));
        Assert.assertEquals(r1.getMessage(), "Saldo insuficiente");
    }
    @Test
    public void registrarPagoEnDisco() throws IOException {
        //Setup
        var item1 = Menu.MILANESA_NAPOLITANA;
        var item2 = Menu.COCACOLA;
        var item3 = Menu.EMPANADAS;
        var pedido1 = new Pedido(new ProveedorDeFecha() {
            @Override
            public LocalDateTime fecha() {
                return LocalDateTime.now();
            }
        }, new EnDiscoRegistroDePago("C:/Users/rodri/Desktop/registroDePagos2025.txt"), item1, item2, item3);

        var precioTotal = pedido1.total();
        var tarjeta = new TarjetaComarcaPlus(40000.00F);

        Comensal cliente = new Comensal("Rodrigo Quichan", "2333", tarjeta);

        //Execution
        cliente.pagarPedido(pedido1);

        //Verification
        var respuestaComparar = pedido1.fechaYhoraRegistro() + "||" + precioTotal + "||" + tarjeta.getClass().toString();
        Assert.assertEquals(true,
                Files.readString(Paths.get("C:/Users/rodri/Desktop/registroDePagos2025.txt")).
                        contains(respuestaComparar)
        );

    }
}