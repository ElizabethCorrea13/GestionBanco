<%@page import="entidad.Prestamo"%>
<%@page import="java.util.List"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<!-- Meta tags requeridos -->
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>Mis Prestamos</title>

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

</head>
<body>

<div class="container-fluid">
    <div class="row">
        <div class="col-3">
            <jsp:include page="MenuCliente.jsp" />
        </div>
        <div class="col-9">
            <div class="card mt-5">
                <div class="card-header">
                    Prestamos solicitados
                </div>
                    <div class="card-body">
                        <table class="table">
                            <thead>
                                <tr>
                                    <th scope="col">N° PRESTAMO</th>
                                    <th scope="col">IMPORTE</th>
                                    <th scope="col">FECHA</th>
                                    <th scope="col">CUOTAS</th>
                                </tr>
                            </thead>
                            <tbody>
                                <%
                                    List<Prestamo> listaTransferencias = (List<Prestamo>) request.getAttribute("listarPrestamos");
                                    if (listaTransferencias != null) {
                                        for (Prestamo i : listaTransferencias) {
                                %>
                                <tr>
                                    <td><%= i.getIdNumeroDePrestamo() %></td>
                                    <td><%= i.getImportePedido() %></td>
                                    <td><%= i.getFechaPeticion() %></td>
                                    <td><%= i.getCantidadCuotas() %></td>
                                    <% if(i.isEstadoPagado()){ %>
                                    	<td> PAGADO</td>
                                    <%}else {%>
                                    <td>
				                        <a href="ServletPagoCuotaPrestamo?ParamPagar=activo&id=<%= i.getIdNumeroDePrestamo() %>" class="btn btn-danger btn-sm">Pagar</a>
				                    </td>
				                    <%}%>
                                </tr>
                                <%
                                        }
                                    }
                                %>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>


</body>
</html>
