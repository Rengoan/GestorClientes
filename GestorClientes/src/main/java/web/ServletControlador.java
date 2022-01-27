package web;

import datos.ClientesDaoJDBC;
import dominio.Cliente;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/ServletControlador")
public class ServletControlador extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        //1. Leer los parametros de nuestro request
        String accion = request.getParameter("accion");
        if (accion != null) {
            switch(accion){
                case "editar":
                    this.editarCliente(request,response);
                    break;
                default:
                    this.accionDefault(request, response);
            }
        } else {
            this.accionDefault(request, response);
        }
        
//        this.accionDefault(request, response);
//        //1. Obtenemos el listado de los clientes
//        List<Cliente> clientes = new ClientesDaoJDBC().listar();
//        double saldoTotal = calcularTotal(clientes);
//
//        System.out.println("Clientes = " + clientes);
//
//        //2. Compartimos el listado de clientes en alcance de request
//        request.setAttribute("clientes", clientes);
//        request.setAttribute("saldoTotal", saldoTotal);
//        request.setAttribute("totalClientes", clientes.size());
//
//        //3. Redireccionamos el flujo a una nueva pagina
//        request.getRequestDispatcher("clientes.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        //1. Leemos los parametros de nuestros request
        String accion = request.getParameter("accion");
        if (accion != null) {
            switch (accion) {
                case "insertar":
                    this.insertarCliente(request, response);
                    break;
                default:
                    this.accionDefault(request, response);
            }
        } else {
            this.accionDefault(request, response);
        }
    }

    private void accionDefault(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        //1. Obtenemos el listado de los clientes
        List<Cliente> clientes = new ClientesDaoJDBC().listar();
        System.out.println("clientes= " + clientes);

        //2. Definimos un objeto session para compartir nuestros atributos en un contexto mas amplio
        HttpSession sesion = request.getSession();

        //3. Compartir en el nuevo alcance los atributos
        sesion.setAttribute("clientes", clientes);
        sesion.setAttribute("totalClientes", clientes.size());
        sesion.setAttribute("saldoTotal", calcularTotal(clientes));

        //4. Redirigir el flujo desde el controlador a un JSP
        response.sendRedirect("clientes.jsp");
    }

    //Devuelve el saldo total de mis clientes
    private double calcularTotal(List<Cliente> clientes) {
        double saldoTotal = 0;

        for (Cliente cliente : clientes) {
            saldoTotal += cliente.getSaldo();
        }

        return saldoTotal;
    }

    //Metodo que inserta un nuevo cliente
    private void insertarCliente(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        //1. Recuperamos los parametros del request
        String nombre = request.getParameter("nombre");
        String apellido = request.getParameter("apellido");
        String email = request.getParameter("email");
        String telefono = request.getParameter("telefono");

        String saldoString = request.getParameter("saldo");
        double saldo = 0;

        if (saldoString != null && !"".equals(saldoString)) {
            saldo = Double.parseDouble(saldoString);
        }
        
        //2. Creamos nuestro objeto Cliente
        Cliente cliente = new Cliente(nombre, apellido, email, telefono, saldo);
        
        //3. Invocamos al metodo de acceso a datos que insertar un cliente
        int registrosModificados = new ClientesDaoJDBC().insertar(cliente);
        System.out.println("registrosModificados = "+registrosModificados);
        
        //4. Redirigimos a la accion por defecto
//        this.accionDefault(request, response);
    }
    
    private void editarCliente(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException{
        
        //1. Recuperamos los parametros
        int idCliente = Integer.parseInt(request.getParameter("idCliente"));
        
        //2. Ahora invocamos al metodo buscar cliente de acceso a datos
        Cliente cliente = new ClientesDaoJDBC().buscar(new Cliente(idCliente));
        
        //3. Compartimos el cliente en el alcance de request
        request.setAttribute("cliente",cliente);
        String jspeditar = "/paginas/clientes/editarCliente.jsp";
        
        //4. Redirigimos y propagamos
        request.getRequestDispatcher(jspeditar).forward(request, response);
        
        
    }
}
