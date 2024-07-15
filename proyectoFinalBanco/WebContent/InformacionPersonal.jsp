<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="ISO-8859-1">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.1.3/dist/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-HoA5eYit5r5Y7hGFugf4n2aab/nLt0JQlvq0/p2iz0UqIxk8t22RFs2Fz6LRc6NN" crossorigin="anonymous"></script>
    <style type="text/css">
        <jsp:include page="css/StyleSheet.css"></jsp:include>
    </style>
    <title>Información Personal</title>
    <style>
        body, html {
            height: 100%;
            background-color: #f8f9fa;
        }
        .container-fluid {
            height: 100%;
        }
        .info-container {
            background-color: #fff;
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);
            margin-top: 20px;
            width: 100%;
        }
        .info-header {
            background-color: #007bff;
            color: white;
            padding: 10px;
            border-radius: 10px 10px 0 0;
        }
        .info-body {
            padding: 20px;
        }
        .info-row {
            margin-bottom: 15px;
        }
        .info-label {
            font-weight: bold;
            color: #333;
        }
    </style>
</head>
<body>
<div class="container-fluid">
    <div class="row">
        <div class="col-3 bg-light border-right">
            <jsp:include page="MenuCliente.jsp" />
        </div>
        <div class="col-9 d-flex justify-content-center align-items-start">
            <div class="info-container">
            	<h1><u>Datos Personales</u></h1>
                    <div class="info-body">
                    	<div class="info-row">
                            <span class="info-label">Nombre Completo: </span><span>${cliente.get_Nombre()} ${cliente.get_Apellido()}</span>
                        </div>
                        <div class="info-row">
                            <span class="info-label">DNI: </span><span>${cliente.get_DNI()}</span>
                        </div>
                        <div class="info-row">
                            <span class="info-label">CUIL: </span><span>${cliente.get_CUIL()}</span>
                        </div>
                        <div class="info-row">
                            <span class="info-label">Sexo: </span><span>${cliente.get_Sexo()}</span>
                        </div>
                        <div class="info-row">
                            <span class="info-label">Nacionalidad: </span><span>${cliente.get_Nacionalidad().getNombre()}</span>
                        </div>
                        <div class="info-row">
                            <span class="info-label">Provincia: </span><span>${cliente.get_Provincia().getNombre()}</span>
                        </div>
                        <div class="info-row">
                            <span class="info-label">Localidad: </span><span>${cliente.get_Localidad().getNombre()}</span>
                        </div>
                        <div class="info-row">
                            <span class="info-label">Dirección: </span><span>${cliente.get_Direccion()}</span>
                        </div>
                        <div class="info-row">
                        
                            <span class="info-label">Fecha de Nacimiento: </span><span>${cliente.get_Fecha_de_Nacimiento()}</span>
                        </div>
                        <div class="info-row">
                            <span class="info-label">Mail: </span><span>${cliente.get_Mail()}</span>
                        </div>
                        <div class="info-row">
                            <span class="info-label">Telefono: </span><span>${cliente.get_Teléfono()}</span>
                        </div>
                        <div class="info-row">
                            <span class="info-label">Cantidad de Cuentas: </span><span>${cliente.get_CantCuentas()}</span>
                        </div>
                    </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>