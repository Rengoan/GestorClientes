package datos;


import dominio.Cliente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClientesDaoJDBC {

    //Obtener el listado de clientes
    private static final String SQL_SELECT = "SELECT * FROM clientes";
    //Filtrar un cliente por id_cliente
    private static final String SQL_SELECT_BY_ID = "SELECT * FROM clientes WHERE"
            + "id_cliente=?";
    //Añadir un cliente
    private static final String SQL_INSERT = "INSERT INTO clientes"
            + "(nombre,apellidos,email,telefono,saldo) VALUES "
            + "(?, ?, ?, ?, ?)";

    //Actualizar un cliente
    private static final String SQL_UPDATE = "UPDATE clientes SET "
            + "nombre = ?, "
            + "apellidos = ?, "
            + "email = ?, "
            + "telefono = ?  "
            + "saldo = ?  "
            + "WHERE id_cliente = ?";

    //Borrar un cliente
    private static final String SQL_DELETE = "DELETE FROM clientes  "
            + "WHERE id_cliente = ? ";

    private Connection conexion;

    public ClientesDaoJDBC() {
    }

    public ClientesDaoJDBC(Connection conexion) {
        this.conexion = conexion;
    }

    //OP: listar,buscar,insertar,actualizar,borrar
    //Listar clientes
    public List<Cliente> listar() {
        //CREAMOS NUESTROS OBJETOS A NULL
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Cliente cliente = null;
        List<Cliente> clientes = new ArrayList<>();

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery();

            while (rs.next()) {

                //Extraemos los campos de cada cliente
                int idCliente = rs.getInt("id_client");
                String nombre = rs.getString("nombre");
                String apellidos = rs.getString("apellidos");
                String email = rs.getString("email");
                String telefono = rs.getString("telefono");
                Double saldo = rs.getDouble("saldo");

                //Crear-Instanciar un nuevo objeto Cliente con los datos
                cliente = new Cliente(idCliente, nombre, apellidos, email, telefono, saldo);

                //Añadimos cada cliente a nuestro listado de clientes
                clientes.add(cliente);
            }
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return clientes;
    }

    //Buscar
    public Cliente buscar(Cliente cliente) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT_BY_ID);
            stmt.setInt(1, cliente.getIdCliente());//Es el where y 1, es el primer interrogante
            rs = stmt.executeQuery();

            if (rs.next()) {

                //Extraemos los campos de cada cliente
                String nombre = rs.getString("nombre");
                String apellidos = rs.getString("apellidos");
                String email = rs.getString("email");
                String telefono = rs.getString("telefono");
                Double saldo = rs.getDouble("saldo");

                //Setteamos los datos del cliente en un formulario
                cliente.setNombre(nombre);
                cliente.setApellidos(apellidos);
                cliente.setEmail(email);
                cliente.setTelefono(telefono);
                cliente.setSaldo(saldo);

            }
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        //Devuelve el objeto cliente
        return cliente;
    }

    //Agregar cliente
    public int insertar(Cliente cliente) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0; //num registros

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_INSERT);

            //Asignamos los valores a los campos de nuestro cliente
            stmt.setString(1, cliente.getNombre());
            stmt.setString(2, cliente.getApellidos());
            stmt.setString(3, cliente.getEmail());
            stmt.setString(4, cliente.getTelefono());
            stmt.setDouble(5, cliente.getSaldo());

            rows = stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace(System.out);
        } finally {

            Conexion.close(stmt);
            Conexion.close(conn);
        }

        return rows;
    }

    //Actualizar cliente
    public int actualizar(Cliente cliente) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0; //num registros

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_UPDATE);

            //Asignamos los valores de los ?
            stmt.setString(1, cliente.getNombre());
            stmt.setString(2, cliente.getApellidos());
            stmt.setString(3, cliente.getEmail());
            stmt.setString(4, cliente.getTelefono());
            stmt.setDouble(5, cliente.getSaldo());

            //Para el id_cliente
            stmt.setInt(6, cliente.getIdCliente());

            rows = stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace(System.out);
        } finally {

            Conexion.close(stmt);
            Conexion.close(conn);
        }

        return rows;
    }

    public int eliminar(Cliente cliente) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0; //num registros

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_DELETE);

            stmt.setInt(1, cliente.getIdCliente());

            rows = stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace(System.out);
        } finally {

            Conexion.close(stmt);
            Conexion.close(conn);
        }

        return rows;
    }

}
