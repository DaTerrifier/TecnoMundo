<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!doctype html>
<html lang="es">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>TecnoMundo | Inicio de Sesión</title>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.6.0/css/all.min.css" integrity="sha512-Kc323vGBEqzTmouAECnVceyQqyqdsSiqLQISBL29aUW4U/M7pSPA/gEUZQqv1cwx4OnYxTxve5UMg5GT6L4JJg==" crossorigin="anonymous" referrerpolicy="no-referrer" />
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
        <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
        <link href="css/estilos.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>  
        <jsp:include page="components/Navegacion.jsp"/>
        <jsp:include page="components/Mensaje.jsp"/>
        <div class="container-fluid mt-3"> 

            <div class="row">
                <div class="col-sm-12">
                    <div class="card form-Registro" >
                        <div class="card-body">
                            <h5>Inicio de Sesión</h5>
                            <hr />
                            <form action="AuthControlador" method="post">
                                <div class="mb-3 text-center">
                                    <img src="Imagenes/Logo-trans_1.png" 
                                         style="width: 200px;" alt="Login"/>
                                </div>
                                
                                
                                <div class="row">
                                    <div class="row">
                                        <div class="col-sm-12">
                                            <div class="mb-3">
                                                <label>Correo Electrónico:  <span class="obligatorio">(*)</span></label>
                                                <input values="${cliente.correo}" type="email" class="form-control" 
                                                       name="correo" placeholder="Ingrese su Correo Electronico" required/>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-sm-12">
                                            <div class="mb-3">
                                                <label>Contraseña:  <span class="obligatorio">(*)</span></label>
                                                <input values="${cliente.password}" type="password" class="form-control" 
                                                       name="password" placeholder="Ingrese su Contraseña" required/>
                                            </div>
                                        </div>
                                    </div>
                                    <input type="hidden" name="action" value="autentificarse">
                                    <button   type="submit" class="btn btn-primary" style="background-color: #06111A">Iniciar Sesión </button>
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