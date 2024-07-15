<%@page import="entidad.Prestamo, entidad.Cuota, entidad.Cuenta"%>

<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Detalles del Préstamo</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.1.3/dist/css/bootstrap.min.css">
    <!-- Incluir archivo CSS externo -->
<link rel="stylesheet" type="text/css" href="css/StyleSheet.css">
</head>
<body>
    <div class="container-fluid">
        <div class="row">
            <div class="col-3 bg-light border-right">
                <jsp:include page="MenuCliente.jsp" />
            </div>
            <div class="col-9 mt-5">
                <div class="card">
                    <div class="card-body">
					    <h2 class="text-center">Detalles del Préstamo</h2>
					    <form method="post" action="ServletPagoCuotaPrestamo" >
					        <div class="form-group">
					            <label for="idPrestamo">ID de Préstamo:</label>
					            <input type="text" id="idPrestamo" class="form-control" value="${prestamo.idNumeroDePrestamo}" name = "idPrestamo" readonly>
					        </div>
					        <div class="form-group">
					            <label for="cuotasTotales">Cuotas Totales:</label>
					            <input type="text" id="cuotasTotales" class="form-control" value="${prestamo.cantidadCuotas}" name = "cuotasTotales"  readonly>
					        </div>
					        <div class="form-group">
					            <label for="montoCuota">Monto de la Cuota:</label>
					            <input type="text" id="montoCuota" class="form-control" value="${prestamo.montoCuota}" name = "montoCuota" readonly>
					        </div>
					        <div class="form-group">
					            <label for="cuotaPagar">Seleccione la Cuota a Pagar:</label>
					            <select class="form-control" id="cuotaPagar" name="cuotaPagar">
					            <option value="" disabled selected>-- Seleccione la cuota --</option>
					                <% 
		                                ArrayList<Cuota> listacu = (ArrayList<Cuota>) request.getAttribute("listaCuota");
		                                if (listacu != null) {
		                                    for (Cuota cu : listacu) {
		                                %>
		                                <option value="<%= cu.getNumeroCuota() %>"><%= cu.getNumeroCuota()%></option>
		                                <% 
		                                    }
		                                }
		                                %>
					            </select>
					        </div>
					        
					         <div class="form-group">
					            <label for="cuentaDebitar">Seleccione la cuenta a debitar:</label>
					            <select class="form-control" id="cuentaDebitar" name="cuentaDebitar">
					            <option value="" disabled selected>-- Seleccione la cuenta con la que desea pagar --</option>
					                <% 
		                                ArrayList<Cuenta> listaCuentas = (ArrayList<Cuenta>) request.getAttribute("listaCuentasXcliente");
		                                if (listaCuentas != null) {
		                                    for (Cuenta cu : listaCuentas) {
		                                %>
		                                <option value="<%= cu.getNumeroCuenta() %>"><%= cu.getNumeroCuenta()%></option>
		                                <% 
		                                    }
		                                }
		                                %>
					            </select>
					        </div>
					        <div class="form-group">
                            <button type="submit"   name ="btnPagar" class="btn btn-primary btn-block">Realizar Pago</button>
                      	  </div>
                      	  <% if (request.getAttribute("mensaje") != null) { %>
                            <div class="alert alert-info mt-3"><%= request.getAttribute("mensaje") %></div>
                       	 <% } %>
					    </form>
					</div>
                </div>
            </div>
        </div>
    </div>
</body>
</html>
