<%@page import="java.util.List"%>
<%@page import="modelo.Producto"%>
<%@page import="modeloDAO.ProductoDAO"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    // Consultar los productos actualizados desde MySQL
    // cada vez que se carga el catálogo.
    ProductoDAO productoDAO = new ProductoDAO();
    List<Producto> productosActualizados = productoDAO.ListarTodos();

    // Utilizar la lista actualizada en esta página.
    pageContext.setAttribute("productos", productosActualizados);
%>

<!doctype html>
<html lang="es">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>TecnoMundo | Productos</title>

        <link
            rel="stylesheet"
            href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.6.0/css/all.min.css"
            integrity="sha512-Kc323vGBEqzTmouAECnVceyQqyqdsSiqLQISBL29aUW4U/M7pSPA/gEUZQqv1cwx4OnYxTxve5UMg5GT6L4JJg=="
            crossorigin="anonymous"
            referrerpolicy="no-referrer"
        />

        <link
            href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
            rel="stylesheet"
        >
    </head>

    <body>

        <jsp:include page="components/Navegacion.jsp"/>

        <div class="container-fluid mt-2">

            <h5>Catálogo de Productos</h5>
            <hr />

            <div class="row">

                <c:forEach var="item" items="${productos}">

                    <div class="col-sm-3 mt-1">

                        <form action="CarritoControlador" method="get">

                            <div class="card">

                                <img
                                    src="productos/${item.imagen}"
                                    width="100%"
                                    alt="${item.imagen}"
                                />

                                <div class="card-body">

                                    <p class="fw-bold">
                                        ${item.nombre}
                                    </p>

                                    <input
                                        type="hidden"
                                        name="action"
                                        value="agregar"
                                    >

                                    <input
                                        type="hidden"
                                        name="id"
                                        value="${item.idProd}"
                                    >

                                    <div class="d-flex justify-content-between align-items-center">

                                        <button
                                            type="submit"
                                            class="btn btn-sm btn-primary"
                                            style="background-color: #06111A"
                                        >
                                            <i class="fa fa-shopping-cart"></i>
                                            Agregar Carrito
                                        </button>

                                        <small class="fw-bold">
                                            $/${item.precio}
                                        </small>

                                    </div>

                                </div>

                            </div>

                        </form>

                    </div>

                </c:forEach>

            </div>

        </div>

        <script
            src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js"
            integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r"
            crossorigin="anonymous"
        ></script>

        <script
            src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.min.js"
            integrity="sha384-0pUGZvbkm6XF6gxjEnlmuGrJXVbLwCsOGabYfZo0T0to5eqruptLy"
            crossorigin="anonymous"
        ></script>

    </body>
</html>