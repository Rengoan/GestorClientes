package web;

import datos.ClientesDaoJDBC;
import dominio.Cliente;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/ServletControlador")
public class ServletControlador extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        //1. Obtenemos el listado de los clientes
        List<Cliente> clientes = new ClientesDaoJDBC().listar();

        System.out.println("Clientes = " + clientes);

        //2. Compartimos el listado de clientes en alcance de request
        request.setAttribute("clientes", clientes);

        //3. Redireccionamos el flujo a una nueva pagina
        request.getRequestDispatcher("clientes.jsp").forward(request, response);

    }

}
