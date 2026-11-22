<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<c:if test="${sessionScope.success != null}">
    <script>
        Swal.fire({
            title: "¡Enhorabuena!",
            text: "${sessionScope.success}",
            icon: "success",
            confirmButtonColor: "#06111A" 
        });
    </script>         
</c:if>

    
<c:if test="${sessionScope.error != null}">
    <script>
        Swal.fire({
            title: "¡Algo Pasó!",
            text: "${sessionScope.error}",
            icon: "error",
            confirmButtonColor: "#06111A" 
        });
    </script>         
</c:if>
    
    <c:remove var="success" scope="session"/>
    <c:remove var="error" scope="session"/>