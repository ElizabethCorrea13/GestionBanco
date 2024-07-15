<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@page import="entidad.Cuenta"%>
<%@page import="entidad.Prestamo"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>

   
    <!DOCTYPE html>
<html lang="es">
<head>
<meta charset="ISO-8859-1">
<title>Transferencia Bancaria</title>
  <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
<!-- Incluir archivo CSS externo -->
    <link rel="stylesheet" type="text/css" href="css/StyleSheet.css">
</head>

<body>
<% 

ArrayList<Cuenta> cuentasPorCliente=null;
if(request.getAttribute("listaCuentasPorCliente")!=null){
	 cuentasPorCliente = new ArrayList<Cuenta>();
	 cuentasPorCliente = (ArrayList<Cuenta>) request.getAttribute("listaCuentasPorCliente");
}




%>

<div class="container-fluid">
    <div class="row">
        <div class="col-3">
            <jsp:include page="MenuCliente.jsp" />
        </div>
        <div class="col-4 mt-5">
            <div class="card mt-5">
                <div class="card-body">
                    <h2 class="text-center">Solicitud de Préstamo</h2>
                    <form action="ServletPrestamo" method="post">
                        <div class="form-group">
                            <label for="montoImporte">Importe:</label>
                            <input type="number" class="form-control" id="montoImporte" name="montoImporte" min="1" required>
                        </div>
                        <div class="form-group">
                            <label for="cuotas">Cuotas:</label>
                            <input type="number" class="form-control" id="cuotas" name="cuotas" min="1" max ="12" required>
                        </div>
                      	
                        <div class="form-group">
                            <label for="cuentaSeleccionada">Cuenta a Depositar:</label>
                            <select class="form-control" id="cuentaSeleccionada" name="cuentaSeleccionada" >
                                <option value="">Seleccione una cuenta</option>
                                <% 
                                if (cuentasPorCliente != null) {
                                    for (Cuenta cuenta : cuentasPorCliente) {
                                %>
                                <option value="<%= cuenta.getNumeroCuenta() %>"><%= cuenta.getNumeroCuenta() %></option>
                                <% 
                                    }
                                }
                                %>
                            </select>
                        </div>
                        
                        <% 
                        String mensaje = (String) request.getAttribute("mensaje");
                        String error = (String) request.getAttribute("error");

                        if (mensaje != null) { %>
                        <div class="alert alert-success mt-3" role="alert">
                            <%= mensaje %>
                        </div>
                        <% } else if (error != null) { %>
                        <div class="alert alert-danger mt-3" role="alert">
                            <%= error %>
                        </div>
                        <% } %>
                        
                        <button type="submit"   name ="btnSolicitarPrestamo" class="btn btn-primary btn-block">Solicitar Préstamo</button>
                    </form>
                </div>
            </div>
        </div>
</div>


	
</body>
</html>