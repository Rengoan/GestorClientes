package datos;

public class ClientesDaoJDBC {

    private static final String SQL_SELECT = "SELECT * FROM clientes";
    private static final String SQL_INSERT = "INSERT INTO clientes"
            + "(nombre,apellidos,email,telefono,saldo) VALUES "
            + "(?, ?, ?, ?, ?)";

    private static final String SQL_UPDATE = "UPDATE clientes SET "
            + "nombre = ?, "
            + "apellidos = ?, "
            + "email = ?, "
            + "telefono = ?  "
            + "saldo = ?  "
            + "WHERE nombre = ?";

    private static final String SQL_BUSCAR = "SELECT * FROM clientes WHERE id_cliente='?'";

    private static final String SQL_DELETE = "DELETE FROM clientes  "
            + "WHERE id_cliente = ? ";
}
