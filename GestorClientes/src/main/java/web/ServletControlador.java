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
        double saldoTotal = calcularTotal(clientes);

        System.out.println("Clientes = " + clientes);

        //2. Compartimos el listado de clientes en alcance de request
        request.setAttribute("clientes", clientes);
        request.setAttribute("saldoTotal", saldoTotal);
        request.setAttribute("totalClientes", clientes.size());

        //3. Redireccionamos el flujo a una nueva pagina
        request.getRequestDispatcher("clientes.jsp").forward(request, response);

    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException,SQLException{
        //1. Leemos los parametros de nuestros request
        String accion = request.getParameter("accion");
        if (accion != null) {
            switch(accion){
                case "insertar":
                    this.insertarCliente(request, response);
                    break;
                default:
                    this.accionDefault(request,response);
            }
        }else{
            this.accionDefault(request,response);
        }
    }
    
    private void accionDefault(HttpServletRequest request, HttpServletResponse response)
            throws IOException,ServletException{
        
        //1. Obtenemos el listado de los clientes
        List<Cliente> clientes = new ClientesDaoJDBC().listar();
        System.out.println("clientes= "+clientes);
        
        //2. Definimos un objeto session para compartir nuestros atributos en un contexto mas amplio
        HttpSession sesion = request.getSession();
        
        //3. Compartir en el nuevo alcance los atributos
        sesion.setAttribute("clientes", clientes);
        sesion.setAttribute("totalClientes", clientes.size());
        sesion.setAttribute("saldoTotal", calcularTotal(clientes));
    }
    
    private double calcularTotal(List<Cliente> clientes){
        double saldoTotal = 0;
        
        for (Cliente cliente : clientes) {
            saldoTotal += cliente.getSaldo();
        }
        
        return saldoTotal;
    }

}
