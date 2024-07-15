<%@page import="entidad.Cuenta"%>
<%@page import="entidad.Movimiento"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="es">
<head>
<meta charset="ISO-8859-1">
<title>Transferencia Bancaria</title>
<link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
<!-- Incluir archivo CSS externo -->
<link rel="stylesheet" type="text/css" href="css/StyleSheet.css">

<style>
    body, html {
        height: 100%;
        margin: 0;
        background: url('https://images.pexels.com/photos/281260/pexels-photo-281260.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1') no-repeat center center fixed;
        background-size: cover;
    }
    .card, .list-group-item, .btn {
        background-color: rgba(255, 255, 255, 0.9); /* Fondo blanco semi-transparente */
        border: 1px solid black; /* Borde negro */
    }
    .carousel-inner {
        height: 280px; /* Ajusta esta altura según tus necesidades */
    }
    .carousel-inner img {
        width: 100%;
        height: 100%;
        object-fit: cover;
    }
    .descubre-mas {
        font-size: 24px; /* Tamaño de fuente más grande */
        color: white; /* Color blanco */
    }
    .mt-5 {
        margin-top: 3rem!important;
    }
</style>
<script>
    function submitForm() {
        document.getElementById("cuentaForm").submit();
    }
</script>
</head>
<body>
<%
HttpSession sessionEnMenu = request.getSession();
int NrCuenta = (int)request.getSession().getAttribute("NrcuentaMov");
String cbu = (String)request.getSession().getAttribute("cbu");
double saldo = (double)request.getSession().getAttribute("saldo");
%>
    <div class="container-fluid">
        <div class="row">
            <div class="col-3">
                <jsp:include page="MenuCliente.jsp" />
            </div>

            <div class="col-4 mt-5">
                <!-- CARD DEL SALDO -->
                <div class="card" style="padding: 10px; margin-bottom: 20px;">
                    <div class="card-body">
                        <h5 class="card-title">Cuenta N° <%= NrCuenta %></h5>
                        <p class="card-text">
                            <span>$</span>
                            <span id="balanceAmount"><%= saldo %></span>
                        </p>
                        <div class="d-flex justify-content-between">
                            <h5 class="card-title">CBU <%= cbu %></h5>
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="col-4 mt-3">
                        <button type="button" class="btn btn-light">Transferir</button>
                    </div>
                    <div class="col-4 mt-3">
                        <!-- Formulario para el dropdown list -->
                        <form id="cuentaForm" action="ServeletPaginaPrincipal" method="get">
                            <select class="form-control" name="cuentaSeleccionada" onchange="submitForm()">
                             <option value="" disabled selected>Seleccione cuenta</option>
                                <%
                                    List<Cuenta> listaCuentas = (List<Cuenta>) request.getAttribute("listarCu");
                                    if (listaCuentas != null) {
                                        for (Cuenta cuenta : listaCuentas) {
                                %>
                                <option value="<%= cuenta.getNumeroCuenta() %>"><%= cuenta.getNumeroCuenta() %></option>
                                <%
                                        }
                                    }
                                %>
                            </select>
                        </form>
                    </div>
                    <div class="col-4 mt-3">
                        <button type="button" class="btn btn-light">Prestamos</button>
                    </div>
                </div>

                <div class="mt-2">
                    <h5 class="descubre-mas">Descubrí más</h5> <!-- Aplicación de clase para estilos -->
                </div>

                <!-- Slider -->
                <div id="carouselExampleSlidesOnly" class="carousel slide mt-1" data-ride="carousel">
                    <div class="carousel-inner">
                        <div class="carousel-item active">
                            <img src="https://img.freepik.com/vector-gratis/banners-planos-lunes-cibernetico_23-2148318186.jpg?t=st=1718452790~exp=1718456390~hmac=c51b7f28fa960baa6b8222c40d1fbdd775988aecbecf598c50e15c071bea5b97&w=740" class="d-block w-100" alt="Slide 1">
                        </div>
                        <div class="carousel-item">
                            <img src="https://img.freepik.com/vector-gratis/conjunto-banners-horizontales-viernes-negro-geometrico-plano_23-2149092478.jpg?t=st=1718452661~exp=1718456261~hmac=02ca121215df9d70df5f61002cfed90eb8bd5a08ddabf8452b4f3a655091705a&w=740" class="d-block w-100" alt="Slide 2">
                        </div>
                        <div class="carousel-item">
                            <img src="https://img.freepik.com/vector-gratis/banners-planos-lunes-cibernetico_23-2148318186.jpg?t=st=1718452790~exp=1718456390~hmac=c51b7f28fa960baa6b8222c40d1fbdd775988aecbecf598c50e15c071bea5b97&w=740" class="d-block w-100" alt="Slide 3">
                        </div>
                    </div>
                </div>
            </div>

            <!-- Tabla de movimientos -->
            <div class="col-5 mt-5">
                <div class="card">
                    <div class="card-header">
                        Transferencias Realizadas
                    </div>
                    <div class="card-body">
                        <table class="table">
                            <thead>
                                <tr>
                                    <th scope="col">CBU DESTINO</th>
                                    <th scope="col">IMPORTE</th>
                                    <th scope="col">FECHA</th>
                                    <th scope="col">DETALLE</th>
                                </tr>
                            </thead>
                            <tbody>
                                <%
                                    List<Movimiento> listaTransferencias = (List<Movimiento>) request.getAttribute("listarMovimiento");
                                    if (listaTransferencias != null) {
                                        for (Movimiento i : listaTransferencias) {
                                %>
                                <tr>
                                    <td><%= i.getCBU_Destino() %></td>
                                    <td><%= i.getImporte() %></td>
                                    <td><%= i.getFechaDeMovimiento() %></td>
                                    <td><%= i.getDetalle() %></td>
                                </tr>
                                <%
                                        }
                                    }
                                %>
                            </tbody>
                        </table>
                    </div>
                </div>

                <!-- Paginación -->
                <nav aria-label="Page navigation example" class="mt-3">
                    <ul class="pagination">
                        <li class="page-item disabled">
                            <a class="page-link" href="#" tabindex="-1" aria-disabled="true">Previous</a>
                        </li>
                        <li class="page-item"><a class="page-link" href="#">1</a></li>
                        <li class="page-item"><a class="page-link" href="#">2</a></li>
                        <li class="page-item"><a class="page-link" href="#">3</a></li>
                        <li class="page-item">
                            <a class="page-link" href="#">Next</a>
                        </li>
                    </ul>
                </nav>
            </div>
        </div>
    </div>
    <!-- Incluir jQuery y Bootstrap JS -->
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.3/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>