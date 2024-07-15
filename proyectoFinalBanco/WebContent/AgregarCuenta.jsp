<%@page import="dao.ClienteDao"%>
<%@page import="daoImpl.ClienteDaoImpl"%>
<%@page import="entidad.Cliente"%>
<%@page import="entidad.Cuenta"%>
<%@page import="negocioImpl.ClienteNegocioImpl"%>
<%@page import="entidad.TipoDeCuenta"%>
<%@page import="entidad.Cliente"%>
<%@page import="java.util.ArrayList"%>
<%@page import="servlets.ServletCuentas"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Agregar Cuenta</title>
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <!-- Datepicker CSS -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.9.0/css/bootstrap-datepicker.min.css">
    <!-- jQuery -->
    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
    <!-- Bootstrap JS -->
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    <!-- Datepicker JS -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.9.0/js/bootstrap-datepicker.min.js"></script>
    <!-- Estilo personalizado -->
    <style>
        <jsp:include page="css/StyleSheet.css"></jsp:include>
    </style>
    <!-- Script para inicializar datepicker y establecer la fecha actual -->
    <script>
        $(document).ready(function() {
            // Inicializar datepicker
            $('#fechaCre').datepicker({
                format: 'yyyy-mm-dd',
                autoclose: true,
                todayHighlight: true
            }).datepicker('setDate', new Date());

            // Establecer la fecha actual en el campo de fecha
            var today = new Date();
            var formattedDate = today.getFullYear() + '-' + 
                                ('0' + (today.getMonth() + 1)).slice(-2) + '-' + 
                                ('0' + today.getDate()).slice(-2);
            $('#fechaCre').val(formattedDate);
        });
    </script>
</head>
<body>
    <div class="container-fluid">
        <div class="row">
            <div class="col-3 bg-light border-right">
                <!-- MENU LATERAL IZQUIERDO -->
                <%-- Reemplaza esto por tu menú lateral --%>
                <jsp:include page="MenuAdmin.jsp" />
            </div>
            <div class="col-9">
                <form method="post" action="ServletCuentas" class="mt-5 mb-5">
                    <fieldset>
                        <h2 class="text-center titulo">Agregar Cuenta</h2>
                        <div class="form-group">
                            <label for="selCliente">Cliente:</label>
                            <select id="selCliente" class="form-control" name="clienteId" required>
                                <option value="" disabled selected>-- Seleccione un cliente --</option>
                                <% 
                                ArrayList<Cliente> listacli = (ArrayList<Cliente>) request.getAttribute("listaClientes");
                                if (listacli != null) {
                                    for (Cliente cli : listacli) {
                                %>
                                <option value="<%= cli.get_IdCliente() %>"><%= cli.get_Nombre() + ", " + cli.get_Apellido()  %></option>
                                <% 
                                    }
                                }
                                %>
                            </select>
                        </div>
                        <div class="form-group">
                            <label for="fechaCre">Fecha de Creación:</label>
                            <input id="fechaCre" class="form-control" type="text" required name="fechaCreacion">
                        </div>
                        <div class="form-group">
                            <label for="tipoCuenta">Tipo de Cuenta:</label>
                            <select id="tipoCuenta" name="tipoCuentaId" class="form-control" required>
                                <option value="" disabled selected>-- Seleccione Tipo de Cuenta --</option>
                                <% 
                                ArrayList<TipoDeCuenta> ListaTipo = (ArrayList<TipoDeCuenta>) request.getAttribute("listaTiposCuenta");
                                if (ListaTipo != null) {
                                    for (TipoDeCuenta tipo : ListaTipo) {
                                %>
                                <option value="<%= tipo.getId() %>"><%= tipo.getNombreDeCuenta() %></option>
                                <% 
                                    }
                                }
                                %>
                            </select>
                        </div>
                        <!-- Campo CBU -->
                        <div class="form-group">
                            <label for="CBU">CBU:</label>
                            <input type="text" name="CBU" id="CBU" class="form-control" value="${nuevoCBU}" readonly>
                        </div>

                        <div class="form-group">
                            <label for="txtSaldo">Saldo:</label>
                            <input id="txtSaldo" class="form-control" type="text" pattern="\d+(\.\d{1,2})?" required name="saldo" value="10000">
                        </div>
                        <div class="form-group">
                            <input id="btnAgregar" class="btn btn-primary" type="submit" value="Agregar" name="btnAgregar">
                        </div>
                        <%-- Mostrar mensajes de éxito o error --%>
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
                    </fieldset>
                </form>
            </div>
        </div>
    </div>
</body>
</html>