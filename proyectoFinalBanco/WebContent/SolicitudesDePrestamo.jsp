
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="dao.ClienteDao"%>
<%@page import="daoImpl.ClienteDaoImpl"%>
<%@page import="negocio.ClienteNegocio"%>
<%@page import="negocioImpl.ClienteNegocioImpl"%>
<%@page import="servlets.ServletCliente"%>
<%@page import="entidad.Prestamo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="es">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Autorización de préstamo</title>
<link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">

    <head>
    <!-- Meta tags requeridos -->
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Listar, modificar y eliminar cliente</title>

    <!-- CSS de Bootstrap -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.1.3/dist/css/bootstrap.min.css">

    <!-- DATA TABLES -->
    <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.19/css/jquery.dataTables.css">

    <!-- jQuery (debe estar antes de Bootstrap JS) -->
    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>

    <!-- DataTables JS -->
    <script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.js"></script>

	    <!-- Incluir archivo CSS externo -->
    <link rel="stylesheet" type="text/css" href="css/StyleSheet.css">

    <script type="text/javascript">
        $(document).ready(function() {
            $('#table_id').DataTable({
                "scrollX": true,
                "scrollCollapse": true,
                "scrollY": "400px",
                "paging": true,
                "responsive": true
            });
        });
        
        $(document).ready(function() {
            $('#table_id2').DataTable({
                "scrollX": true,
                "scrollCollapse": true,
                "scrollY": "400px",
                "paging": true,
                "responsive": true
            });
        });
    </script>
    
</head>
<body>
    <%
        List<Prestamo> Prest = new ArrayList<Prestamo>();
        if (request.getAttribute("listaPrestamos") != null) {
            Prest = (List<Prestamo>) request.getAttribute("listaPrestamos");
        } else {
            out.println("<p>No hay clientes disponibles.</p>");
        }
    %>
    
    <%
        List<Prestamo> Prest2 = new ArrayList<Prestamo>();
        if (request.getAttribute("listaPrestamosAR") != null) {
            Prest2 = (List<Prestamo>) request.getAttribute("listaPrestamosAR");
        } else {
            out.println("<p>No hay clientes disponibles.</p>");
        }
    %>
    
    <div class="container-fluid">
        <div class="row">
            <div class="col-3 bg-light border-right">
                <!-- MENU LATERAL IZQUIERDO -->
		<jsp:include page="MenuAdmin.jsp" />
        </div>
            <div class="col-9">
                <div class="row">
                    <div class="col d-flex justify-content-center">
                        <form action="get" class="mt-4">
                            <div>
                                <h3 class="text-center">Solicitudes de prestamo</h3>
                            </div>
                            <div class="table-responsive table-container mt-4 mb-5" style="max-width: 900px;">
                                <!-- LISTADO -->
                                <table id="table_id" class="table table-bordered small">
                                    <thead class="thead-light">
                                        <tr class="text-center">
                                        	<th>RECHAZAR</th>
                                            <th>ACEPTAR</th>
                                            <th>ID PRESTAMO</th>
                                            <th>NUMERO DE CUENTA</th>
                                            <th>FECHA DE PETICION</th>
                                            <th>VALOR DE CUOTA</th>
                                            <th>CANTIDAD DE CUOTAS</th>
                                            <th>IMPORTE SIN INTERESES</th> 
                                            <th>IMPORTE CON INTERESES</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                       <% for (Prestamo Presta : Prest) { %>
                                            <tr class="text-center">
												<td>
    												<a href="ServletPrestamo?Param=MODIFICAR&id=<%= Presta.getIdNumeroDePrestamo() %>&estado=<%= false %>" class="btn btn-danger btn-sm">
        											RECHAZAR
    												</a>  
												</td>
                           						<td>
                                                    <a href="ServletPrestamo?Param=MODIFICAR&id=<%= Presta.getIdNumeroDePrestamo() %>&estado=<%= true %>" class="btn btn-primary btn-sm">
                                                    ACEPTAR
                                                    </a>
                                                </td>
                                                <td><%= Presta.getIdNumeroDePrestamo() %></td>
                                                <td><%= Presta.getNroCuenta_Cu()%></td>
                                                <td><%= Presta.getFechaPeticion()%></td>
                                                <td><%= Presta.getMontoCuota()%></td>
                                                <td><%= Presta.getCantidadCuotas() %></td>
                                                <td><%= Presta.getImportePedido() %></td>
                                                <td><%= Presta.getImporteConIntereses() %></td>
                                            </tr>
                                        <%} %>
                                    </tbody>
                                </table>
                                <!-- FIN LISTADO -->
                            </div >
                            
                             <div class="table-responsive table-container mt-4 mb-5" style="max-width: 900px;">
                             <h3 class="text-center">Historial de Prestamos</h3>
                                <!-- LISTADO ACEPTADOS RECHAZADOS-->
                                <table id="table_id2" class="table table-bordered small">
                                    <thead class="thead-light">
                                        <tr class="text-center">
                                        	<th>ESTADO</th>
                                            <th>ID PRESTAMO</th>
                                            <th>NUMERO DE CUENTA</th>
                                            <th>FECHA DE PETICION</th>
                                            <th>VALOR DE CUOTA</th>
                                            <th>CANTIDAD DE CUOTAS</th>
                                            <th>IMPORTE SIN INTERESES</th> 
                                            <th>IMPORTE CON INTERESES</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                       <% for (Prestamo Presta2 : Prest2) { %>
                                            <tr class="text-center">

												<td>
												    <% if (Presta2.getEstadoPrestamo() == true) { %>
        										<span style="background-color: green; color: white; padding: 5px; border-radius: 6px;">ACEPTADO</span>												    
        										<% } else if (Presta2.getEstadoPrestamo() == false) { %>
												 <span style="background-color: red; color: white; padding: 5px; border-radius: 6px;">RECHAZADO</span>
												    <% } %>
												</td>
                                                <td><%= Presta2.getIdNumeroDePrestamo() %></td>
                                                <td><%= Presta2.getNroCuenta_Cu()%></td>
                                                <td><%= Presta2.getFechaPeticion()%></td>
                                                <td><%= Presta2.getMontoCuota()%></td>
                                                <td><%= Presta2.getCantidadCuotas() %></td>
                                                <td><%= Presta2.getImportePedido() %></td>
                                                <td><%= Presta2.getImporteConIntereses() %></td>
                                            </tr>
                                        <%} %>
                                    </tbody>
                                </table>
                                <!-- FIN LISTADO -->
                            </div >
                            
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- Incluir los archivos JS de Bootstrap -->
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.bundle.min.js"></script>
</body>
</html>
