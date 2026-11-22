<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!doctype html>
<html lang="es">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>TecnoMundo | Carrito</title>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.6.0/css/all.min.css" integrity="sha512-Kc323vGBEqzTmouAECnVceyQqyqdsSiqLQISBL29aUW4U/M7pSPA/gEUZQqv1cwx4OnYxTxve5UMg5GT6L4JJg==" crossorigin="anonymous" referrerpolicy="no-referrer" />
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
        <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
        <link href="css/estilos.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>  
        <jsp:include page="components/Navegacion.jsp"/>
        <jsp:include page="components/Mensaje.jsp"/>
        <div class="container-fluid mt-3"> 
            <h5>Carrito</h5>
            <hr />
            <div class="row">
                <div class="col-sm-9">
                    <div class="card">
                        <div class="card-body">
                            <div class="table-responsive">
                                <table class="table table-bordered table-striped">
                                    <thead>
                                        <tr>
                                            <th>Imagen</th>
                                            <th>Producto</th>
                                            <th>Precio (S/)</th>
                                            <th>Cantidad</th>
                                            <th>Importe (S/)</th>
                                            <th>Acción</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach items="${carrito}" var="item" varStatus="loop">
                                            <tr>
                                                <td>
                                                    <img src="productos/${item.producto.imagen}" width="50" height="60" alt="${item.producto.nombre}" /> 
                                                </td>
                                                <td>${item.producto.nombre}</td>
                                                <td>${item.producto.precio}</td>
                                                <td>${item.cantidad}</td>
                                                <td>${item.Importe()}</td>
                                                <td>
                                                    <a href="CarritoControlador?action=eliminar&indice=${loop.index}" title="Eliminar" class="btn btn-danger btn-sm">
                                                        <i class="fas fa-trash-alt"></i> Eliminar
                                                    </a>
                                                </td>
                                            </tr>
                                        </c:forEach>
                                        <c:if test="${!(carrito != null && carrito.size() > 0)}">
                                            <tr class="text-center">
                                                <td colspan="6"> Carrito Vacio</td>
                                            </tr>

                                        </c:if>

                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-sm-3">
                    <div class="card">
                        <div class="card-body">
                            <<form action="PedidoControlador" method="post">
                                <div class="row">
                                    <h5>RESUMEN COMPRA</h5>
                                    <hr />
                                    <div class="d-flex justify-content-between mb-4">
                                        <p class="mb-2">Total</p>
                                        <p class="mb-2">S/${Total}  </p>
                                    </div>
                                    <input type="hidden" name="action" value="procesar">
                                    <button class="btn btn-warning btn-block btn-lg">
                                        <div ${carrito.size() == 0 ? 'disabled':''} type="submit" class="d-flex justify-content-between">
                                            <span>i<i class="fa fa-credit-card"></i> PROCESAR</span>
                                        </div>
                                    </button>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
            <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js" integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r" crossorigin="anonymous"></script>
            <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.min.js" integrity="sha384-0pUGZvbkm6XF6gxjEnlmuGrJXVbNuzT9qBBavbLwCsOGabYfZo0T0to5eqruptLy" crossorigin="anonymous"></script>
        </div>
    </body>
</html>
<div></div>