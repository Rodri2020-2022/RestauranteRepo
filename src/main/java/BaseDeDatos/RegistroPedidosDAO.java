package BaseDeDatos;

import ZonaComedor.Pedido;
import java.sql.*;

public class RegistroPedidosDAO {

    public void create(Pedido pedido) throws SQLException {
        System.out.println("Ejecutando ------> create(Pedido pedido);");
        System.out.println("");

        try{
            Connection conn = ConnectionManager.getConnection();
            PreparedStatement statement =
                    conn.prepareStatement(
                            "INSERT INTO pedido("
                                    + "fecha, "
                                    + "precio, "
                                    + "detallePago) "
                                    + "VALUES (?, ?, ?)");

            statement.setDate(1, Date.valueOf(pedido.fechaRegistro()));
            statement.setFloat(2, pedido.total());
            statement.setString(3, pedido.tipoTarjetaLeida());

            int cantidad = statement.executeUpdate();
            if(cantidad > 0) {
                System.out.println("Pedido creado exitosamente");
            } else {
                throw new RuntimeException("Error al crear pedido");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Error SQL");
        } finally {
            System.out.println("Desconectar servidor");
            ConnectionManager.disconnect();
        }

    }
}
