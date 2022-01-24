<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

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
                            <th>Apellidos</th>
                            <th>Saldo</th>
                            <th></th>
                        </tr>
                    </thead>
                    <tbody>
                        
                    </tbody>
                </table>
            </div>

        </div>

    </div>
</section>

<ul>
    <c:forEach var="cliente" items="${clientes}">
        <li>${cliente.nombre} ${cliente.apellidos} ${cliente.email}
            ${cliente.telefono} ${cliente.saldo}</li>
        </c:forEach>
</ul>
