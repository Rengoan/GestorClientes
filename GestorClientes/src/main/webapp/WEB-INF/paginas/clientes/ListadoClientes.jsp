<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="es_ES"/> <!-- Para mostrar formato de los euros-->

<section id="clientes">
    <div class="container">
        <div class="row">
            <div class="col-md-9">
                <div class="card-header">
                    <h4>Listado Clientes</h4>
                </div>
                <table class="table table-striped">
                    <thead class="thead-dark">
                        <tr>
                            <th>#</th>
                            <th>Nombre</th>
                            <th>Saldo</th>
                            <th></th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="cliente" items="${clientes}" varStatus="status">
                            <tr>
                                <td>${status.count}</td>
                                <td>${cliente.id_cliente}</td>
                                <td>${cliente.nombre} ${cliente.apellidos}</td>
                                <td><fmt:formatNumber value="${cliente.saldo}" 
                                    type="currency" currencySymbol="&euro;"/></td>
                                <td>
                                    <a href="ServletControlador?accion=editarCliente&id_cliente=${cliente.id_cliente}" 
                                       class="btn btn-block">
                                        <i class="fas fa-angle-double-rigth"></i>Editar

                                    </a>
                                </td>
                            </tr>

                        <li>${cliente.nombre} ${cliente.apellidos} ${cliente.email}
                            ${cliente.telefono} ${cliente.saldo}</li>
                        </c:forEach>
                    </tbody>
                </table>
            </div>

        </div>

    </div>
</section>

<ul>

</ul>
