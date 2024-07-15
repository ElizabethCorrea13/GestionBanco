<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*, entidad.Cliente, entidad.Cuenta" %>
<%@ page import="java.text.SimpleDateFormat" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Transferencia Bancaria</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
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
                <div class="card-body">
                    <h2 class="text-center">Transferencia Bancaria</h2>
                    <form action="ServletTranferencia" method="post">
                        <div class="form-group">
                            <label for="NroCuenta">Nro de Cuenta:</label>
                            <% 
                                ArrayList<Cuenta> lista = (ArrayList<Cuenta>) request.getAttribute("listarCuentas");
                                if (lista != null && !lista.isEmpty()) { 
                                    if (lista.size() > 1) { %>
                                        <select id="SelCuenta" class="form-control" name="NroCuenta" onchange="actualizarDatos()" required>
                                            <option value="" disabled selected>-- Seleccione la cuenta con la que desea transferir --</option>
                                            <% 
                                            for (Cuenta cu : lista) { %>
                                                <option value="<%= cu.getNumeroCuenta() %>"><%= cu.getNumeroCuenta() %></option>
                                            <% 
                                            } %>
                                        </select>
                                    <% } else { %>
                                        <input type="text" class="form-control" id="NroCuenta" name="NroCuenta" value="<%= lista.get(0).getNumeroCuenta() %>" readonly>
                                    <% }
                                } else { %>
                                    <p>No hay cuentas disponibles.</p>
                                <% } %>
                        </div>
                        <div class="form-group">
                            <label for="CBUO">Cuenta de Origen (CBU):</label>
                            <input type="text" class="form-control" id="CBUO" name="CBUO" readonly>
                        </div>
                        <div class="form-group">
                            <label for="CBUD">Cuenta de Destino (CBU):</label>
                            <input type="text" class="form-control" id="CBUD" name="CBUD" pattern="\d{8}" title="Debe ser un CBU válido de 8 dígitos." required>
                        </div>
                        <div class="form-group">
                            <label for="saldoDisponible">Saldo Disponible:</label>
                            <input type="text" class="form-control" id="saldoDisponible" name="saldoDisponible" readonly>
                        </div>
                        <div class="form-group">
                            <label for="concept">Detalle o Concepto:</label>
                            <select class="form-control" id="concept" name="concept" required>
                                <option value="">Seleccione un concepto</option>
                                <option value="varios">Varios</option>
                                <option value="alquiler">Alquiler</option>
                                <option value="cuota">Cuota</option>
                                <option value="expensas">Expensas</option>
                                <option value="haberes">Haberes</option>
                                <option value="honorarios">Honorarios</option>
                                <option value="seguro">Seguro</option>
                                <option value="prestamo">Préstamo</option>
                            </select>
                        </div>
                        <div class="form-group">
                            <label for="date">Fecha:</label>
                            <% 
                                // Obtener la fecha actual en formato yyyy-MM-dd
                                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                                String currentDate = sdf.format(new Date());
                            %>
                            <input type="date" class="form-control" id="date" name="date" value="<%= currentDate %>" required>
                        </div>
                        <div class="form-group">
                            <label for="Monto">Monto:</label>
                            <input type="number" class="form-control" id="Monto" name="Monto" step="0.01" min="0" required>
                        </div>
                        <button type="submit" class="btn btn-primary btn-block">Transferir</button>
                        <% if (request.getAttribute("mensaje") != null) { %>
                            <div class="alert alert-info mt-3"><%= request.getAttribute("mensaje") %></div>
                        <% } %>
                    </form>
                </div>
            </div>
        </div>
    </div>
    <script>
        function actualizarDatos() {
            var cuentas = <%
                ArrayList<Cuenta> listacu = (ArrayList<Cuenta>) request.getAttribute("listarCuentas");
                if (listacu != null) {
                    out.print("[");
                    for (int i = 0; i < listacu.size(); i++) {
                        Cuenta cu = listacu.get(i);
                        if (i > 0) {
                            out.print(",");
                        }
                        out.print("{\"numeroCuenta\":\"" + cu.getNumeroCuenta() + "\", \"cbu\":\"" + cu.getCBU() + "\", \"saldo\":\"" + cu.getSaldo() + "\"}");
                    }
                    out.print("]");
                } else {
                    out.print("[]");
                }
            %>;
            var selectedCuenta = document.getElementById("SelCuenta") ? document.getElementById("SelCuenta").value : (cuentas.length > 0 ? cuentas[0].numeroCuenta : '');
            var cbuInput = document.getElementById("CBUO");
            var saldoInput = document.getElementById("saldoDisponible");

            for (var i = 0; i < cuentas.length; i++) {
                if (cuentas[i].numeroCuenta == selectedCuenta) {
                    cbuInput.value = cuentas[i].cbu;
                    saldoInput.value = cuentas[i].saldo;
                    break;
                }
            }
        }

        document.addEventListener("DOMContentLoaded", function() {
            actualizarDatos();

            // Establecer la fecha actual en el campo de fecha
            var today = new Date();
            var formattedDate = today.getFullYear() + '-' + 
                                ('0' + (today.getMonth() + 1)).slice(-2) + '-' + 
                                ('0' + today.getDate()).slice(-2);
            document.getElementById('date').value = formattedDate;
        });
        
        $(document).ready(function() {
            // Inicializar datepicker
            $('#date').datepicker({
                format: 'yyyy-mm-dd',
                autoclose: true,
                todayHighlight: true
            }).datepicker('setDate', new Date());
        });
    </script>
</div>
</body>
</html>