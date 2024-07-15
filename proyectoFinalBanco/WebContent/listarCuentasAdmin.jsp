<%@page import="entidad.Cuenta"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="es">
<head>
    <!-- Meta tags requeridos -->
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Listar, modificar y eliminar cuenta</title>

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
        List<Cuenta> listaCuenta = (List<Cuenta>) request.getAttribute("listarCuentas");
        if (listaCuenta == null) {
            listaCuenta = new ArrayList<>();
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
                        <div class="mt-4">
                            <div>
                                <h3 class="text-center">Lista de cuentas</h3>
                            </div>
                            <div class="table-responsive table-container mt-4 mb-5" style="max-width: 900px;">
                                <!-- LISTADO -->
                                <table id="table_id" class="table table-bordered small">
                                    <thead class="thead-light">
                                        <tr class="text-center">
                                            <th>ELIMINAR</th>
                                            <th>MODIFICAR</th>
                                            <th>CUENTA ID</th>
                                            <th>ID CLIENTE</th>
                                            <th>TIPO DE CUENTA</th>
                                            <th>FECHA DE CREACION</th>
                                            <th>CBU</th>
                                            <th>SALDO</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <% if (!listaCuenta.isEmpty()) { 
                                            for (Cuenta cuenta : listaCuenta) { %>
                                            <tr class="text-center">
                                                <td>
                                                    <a href="ServletCuentas?Param=delete&NrCuenta=<%= cuenta.getNumeroCuenta() %>" class="btn btn-danger btn-sm">Eliminar</a>  
                                                </td>
                                                <td>
                                                    <a href="ServletCuentas?Param=edit&NrCuenta=<%= cuenta.getNumeroCuenta() %>" class="btn btn-primary btn-sm">Modificar</a>
                                                </td>
                                                <td><%= cuenta.getNumeroCuenta() %></td>
                                                <td><%= cuenta.getCliente().get_IdCliente() %></td>
                                                <td><%= cuenta.getTipoDeCuenta().getId() %></td>
                                                <td><%= cuenta.getFechaCreacion() %></td>
                                                <td><%= cuenta.getCBU() %></td>
                                                <td><%= cuenta.getSaldo() %></td>
                                            </tr>
                                        <% }
                                        } else { %>
                                            <tr>
                                                <td colspan="8" class="text-center">No hay cuentas disponibles.</td>
                                            </tr>
                                        <% } %>
                                    </tbody>
                                </table>
                                <!-- FIN LISTADO -->
                            </div>
                        </div>
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