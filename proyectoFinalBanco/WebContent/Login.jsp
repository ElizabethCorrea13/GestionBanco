<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<!-- Meta tags requeridos -->
    <meta charset="ISO-8859-1">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.1.3/dist/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
<!-- Datepicker CSS -->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.9.0/css/bootstrap-datepicker.min.css">

<style type="text/css">
		<jsp:include page="css\StyleSheet.css"></jsp:include>
</style>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Inicio de Sesion here</title>
</head>
<body>

<div class="container-fluid">
    <div class="row justify-content-center align-items-center" style="height: 100vh;">
        <div class="col-4">
            <form action="ServletUsuario" method="post" class="formulario">
            
            		<% if (request.getAttribute("IngresoINVALIDO") != null && (Boolean) request.getAttribute("IngresoINVALIDO")) { %>
					    <div class="alert alert-danger" role="alert">
					       <p>Datos inválidos o usuario inexistente.</p>
					    </div>
					<% } %>
            
                <fieldset>
                    <h2 class="text-center titulo">Iniciar Sesión</h2>
                    <div class="form-group">
                        <label for="usuario">Usuario:</label>
                        <input id="usuario" class="form-control" type="text" pattern="^[a-zA-Z0-9_\-]+$" placeholder="Ingrese nombre usuario" required name="usuario">
                    </div>
                    <div class="form-group">
                        <label for="contraseña">Contraseña:</label>
                        <input id="contraseña" class="form-control" type="password" placeholder="Ingrese contraseña" required name="contraseña" pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?=.*\W).{8,}" title="La contraseña debe contener al menos 8 caracteres, incluyendo al menos una letra mayúscula, una letra minúscula, un número y un carácter especial.">
                    </div>
                     <div class="form-group d-flex justify-content-between">
                        <div class="form-check">
                            <input type="checkbox" class="form-check-input" id="recordarUsuario" name="recordarUsuario">
                            <label class="form-check-label" for="recordarUsuario">Recordar Usuario</label>
                        </div>
                        <div>
                            <a href="recuperar_contraseña.html">¿Olvidó su contraseña?</a>
                            
                        </div>
                    </div>
                    <div class="form-group">
                        <input id="btnAgregar" class="btn btn-primary btn-block" type="submit" value="Iniciar Sesión" name="btnAgregar">
                    </div>
                   
                </fieldset>
            </form>
          
            
        </div>
    </div>
</div>

	<!-- jQuery (necesario para Bootstrap JavaScript plugins) -->
	<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
	<!-- Bootstrap JavaScript -->
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-HoA5eYit5r5Y7hGFugf4n2aab/nLt0JQlvq0/p2iz0UqIxk8t22RFs2Fz6LRc6NN" crossorigin="anonymous"></script>
	<!-- Datepicker JavaScript -->
	<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.9.0/js/bootstrap-datepicker.min.js"></script>
</body>
