package Main;

import BaseDeDatos.RegistroPedidosDAO;
import Menu.Menu;
import Persistencia.EnDiscoRegistroDePago;
import ZonaComedor.*;
import org.junit.Test;

import java.sql.SQLException;
import java.time.LocalDateTime;

public class PagosDatabaseTest {

    @Test
    public void registrarPago() throws SQLException {
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
        var tarjeta = new TarjetaViedma(40000.00F);

        Comensal cliente = new Comensal("Rodrigo Quichan", "2333", tarjeta);

        //Execution
        cliente.pagarPedido(pedido1);

        var registroPedidoDAO = new RegistroPedidosDAO();
        registroPedidoDAO.create(pedido1);
    }
}
