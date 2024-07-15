<%@page import="entidad.Cliente"%>
<%@page import="entidad.Nacionalidad"%>
<%@page import="entidad.Provincia"%>
<%@page import="entidad.Localidad"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="dao.ClienteDao"%>
<%@page import="daoImpl.ClienteDaoImpl"%>
<%@page import="negocio.ClienteNegocio"%>
<%@page import="negocioImpl.ClienteNegocioImpl"%>
<%@page import="servlets.ServletCliente"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="es">
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
    </script>
    
</head>
<body>
    <%
        List<Cliente> listaC = new ArrayList<Cliente>();
        if (request.getAttribute("listaCli") != null) {
            listaC = (List<Cliente>) request.getAttribute("listaCli");
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
                                <h3 class="text-center">Lista de clientes</h3>
                            </div>
                            <div class="table-responsive table-container mt-4 mb-5" style="max-width: 900px;">
                                <!-- LISTADO -->
                                <table id="table_id" class="table table-bordered small">
                                    <thead class="thead-light">
                                        <tr class="text-center">
                                        	<th>ELIMINAR</th>
                                            <th>MODIFICAR</th>
                                            <th>ID CLIENTE</th>
                                            <th>DNI</th>
                                            <th>CUIL</th>
                                            <th>NOMBRE</th>
                                            <th>APELLIDO</th>
                                            <th>SEXO</th>
                                            <th>NACIONALIDAD</th>
                                            <th>FECHA NACIMIENTO</th>
                                            <th>DIRECCION</th>
                                            <th>LOCALIDAD</th>
                                            <th>PROVINCIA</th>
                                            <th>MAIL</th>
                                            <th>TELEFONO</th>
                                            <th>USUARIO</th>
                                            <th>CONTRASEÑA</th>
                                            <th>Nº DE CUENTAS</th>
                                            <th>ESTADO</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <% for (Cliente cliente : listaC) { %>
                                            <tr class="text-center">
                                                <% 
    													String nombreBoton = "Activar";
    													if (cliente.get_Estado()) { 
       													nombreBoton = "Eliminar"; 
    													}
														%>
														<td>
    													<a href="ServletCliente?Param=delete&id=<%= cliente.get_IdCliente() %>&estado=<%= cliente.get_Estado() %>" class="btn btn-danger btn-sm">
        												<%= nombreBoton %>
    													</a>  
													</td>
                           						<td>
                                                    <a href="ServletCliente?Param=ObtenerUsuario&id=<%= cliente.get_IdCliente() %>" class="btn btn-primary btn-sm">Modificar</a>
                                                </td>
                                                <td><%= cliente.get_IdCliente() %></td>
                                                <td><%= cliente.get_DNI() %></td>
                                                <td><%= cliente.get_CUIL() %></td>
                                                <td><%= cliente.get_Nombre() %></td>
                                                <td><%= cliente.get_Apellido() %></td>
                                                <td><%= cliente.get_Sexo() %></td>
                                                <td><%= cliente.get_Nacionalidad().getNombre() %></td>
                                                <td><%= cliente.get_Fecha_de_Nacimiento().toString() %></td>
                                                <td><%= cliente.get_Direccion() %></td>
                                                <td><%= cliente.get_Localidad().getNombre() %></td>
                                                <td><%= cliente.get_Provincia().getNombre() %></td>
                                                <td><%= cliente.get_Mail() %></td>
                                                <td><%= cliente.get_Teléfono() %></td>
                                                <td><%= cliente.get_Usuario() %></td>
                                                <td><%= cliente.get_Contraseña() %></td>
                                                <td><%= cliente.get_CantCuentas() %></td>
                                                <td><%= cliente.get_Estado() %></td>
                                            </tr>
                                        <% } %>
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
    
    <!-- JavaScript opcional -->
    <!-- Bootstrap JS -->
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.14.3/dist/umd/popper.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.1.3/dist/js/bootstrap.min.js"></script>
</body>
</html>
