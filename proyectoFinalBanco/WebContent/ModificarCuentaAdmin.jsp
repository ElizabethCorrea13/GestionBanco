<%@page import="entidad.Cuenta"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <style>
        <jsp:include page="css\StyleSheet.css"></jsp:include>
    </style>
    <title>Modificar Datos de la Cuenta</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container-fluid">
    <div class="row">
        <div class="col-3 bg-light border-right">
            <jsp:include page="MenuAdmin.jsp" />
        </div>
        <div class="col-9">
            <div class="card mt-5">
                <div class="card-body">
                    <h2 class="text-center">Modificar Cuenta</h2>
                    <form action="ServletCuentas" method="post" class="formulario">
                    	<% Cuenta cuenta = new Cuenta(); %>
                        <div class="form-group">
                            <label for="numeroCuenta">Número de Cuenta:</label>
                            <input id="numeroCuenta" name="numeroCuenta" class="form-control" type="text" value="${cuenta.getNumeroCuenta()}" readonly>
                        </div>
                        <div class="form-group">
                            <label for="idCuenta">ID Cliente:</label>
                            <input id="idCuenta" name="idCliente" class="form-control" type="text" value="${cuenta.getCliente().get_IdCliente()}" readonly>
                        </div>
                        <div class="form-group">
                            <label for="fechaCreacion">Fecha de Creación:</label>
                            <input id="fechaCreacion" name="fechaCreacion" class="form-control" type="text" value="${cuenta.getFechaCreacion()}" readonly>
                        </div>
                        <div class="form-group">
                            <label for="tipoCuenta">Tipo de Cuenta:</label>
                            <select id="tipoCuenta" class="form-control" name="tipoCuenta">
                                <option value="1" ${cuenta.getTipoDeCuenta().getId() == 1 ? 'selected' : ''}>Cuenta Corriente</option>
                                <option value="2" ${cuenta.getTipoDeCuenta().getId() == 2 ? 'selected' : ''}>Caja de Ahorro</option>
                            </select>
                        </div>
                        <div class="form-group">
                            <label for="saldo">Saldo:</label>
                            <input id="saldo" name="saldo" class="form-control" type="text" value="${cuenta.getSaldo()}">
                        </div>
                        <button name="BtnModificar" type="submit" class="btn btn-primary btn-block">Modificar</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>