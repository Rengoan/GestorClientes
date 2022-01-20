<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Gestor Clientes</title>
    </head>
    <body>
        <h1>Gestor Clientes</h1>
        <ul>
            <c:forEach var="cliente" items="${clientes}">
                <li>${cliente.nombre} ${cliente.apellidos} ${cliente.email}
                ${cliente.telefono} ${cliente.saldo}</li>
            </c:forEach>
        </ul>
    </body>
</html>
