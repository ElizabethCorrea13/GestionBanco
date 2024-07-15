<%@page import="dao.ClienteDao"%>
<%@page import="daoImpl.ClienteDaoImpl"%>
<%@page import="entidad.Cliente"%>
<%@page import="entidad.Nacionalidad"%>
<%@page import="entidad.Provincia"%>
<%@page import="entidad.Localidad"%>
<%@page import="negocio.ClienteNegocio"%>
<%@page import="negocioImpl.ClienteNegocioImpl"%>
<%@page import="servlets.ServletCliente"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="es">
  <head>
    <!-- Meta tags requeridos -->
    <meta charset="ISO-8859-1">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- CSS de Bootstrap -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.1.3/dist/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">

    <!-- Incluir archivo CSS externo -->
    <link rel="stylesheet" type="text/css" href="css/StyleSheet.css">
    
    <title>Agregar Cliente</title>
  </head>
  
  <body>
    <div class="container-fluid">
	    <div class="row">
            <div class="col-3 bg-light border-right">
                <!-- MENU LATERAL IZQUIERDO -->
                <jsp:include page="MenuAdmin.jsp" />
            </div>
	    	
	    	<div class="col-9 d-flex justify-content-center">	
		    	<div class="col-8">
		    	<form method="post" action="ServletCliente" class="mt-5 mb-5" onsubmit="return validarFormulario()">	
		    	<fieldset>
		    	
		    	<h3 class="text-center mt-2">Agregar Cliente</h3>
		    	
		    	      	<% 
                        String mensajeDni = (String) request.getAttribute("mensajeDNI");
                        String mensajeUsuario = (String) request.getAttribute("mensajeUsuario");

                        if (mensajeDni != null) { %>
                        <div class="alert alert-danger mt-3" role="alert">
                            <%= mensajeDni %>
                        </div>
                        <% }  if (mensajeUsuario != null) { %>
                        <div class="alert alert-danger mt-3" role="alert">
                            <%= mensajeUsuario %>
                        </div>
                        <% } %>
		    	
				<p class="mt-3">
				    <label for="dni">DNI:</label>
				    <input id="dni" class="form-control" type="text" pattern="^\d{5,8}$" placeholder="Ingrese DNI" required name="txtDni" >				    
				</p>
						     	
		     	<p>
			        <label for="cuil">CUIL:</label>
			        <input id="cuil" class="form-control" type="text" pattern="^\d{8,11}$" placeholder="Ingrese CUIL" required name="txtCuil">
		     	</p>
		    	
		    	<p>
			        <label for="nombre">Nombre:</label>
			        <input id="nombre" class="form-control" type="text" pattern="^[a-zA-Z\s]+$" placeholder="Ingrese nombre" required name="txtNombre">
		      	</p>
		      	
		      	<p>
			        <label for="apellido">Apellido:</label>
			        <input id="apellido" class="form-control" type="text" pattern="^[a-zA-Z\s]+$" placeholder="Ingrese apellido" required name="txtApellido">
		      	</p>
		      	
		      	<p>
                <label for="sexo">Sexo:</label>
                <select id="sexo" class="form-control" name="selSexo" required>
                	<option value="" disabled hidden selected>--seleccione opcion--</option>
                    <option value="Femenino">Femenino</option>
                    <option value="Masculino">Masculino</option>
                </select>
            	</p>
            	
		      	<p>
		      	<label for="nacionalidad">Nacionalidad:</label>
			    <select id="nacionalidad" name="txtNacionalidad" class="form-control" required>
			    <option value="" disabled hidden selected>--seleccione opcion--</option>
			    <%
			        ArrayList<Nacionalidad> listaNacionalidad = null;
			        Object listaN = request.getAttribute("listaNac");
			        if (listaN instanceof ArrayList<?>) {
			            ArrayList<?> tempList = (ArrayList<?>) listaN;
			            if (tempList.isEmpty() || tempList.get(0) instanceof Nacionalidad) {
			                @SuppressWarnings("unchecked")
			                ArrayList<Nacionalidad> tempListaNacionalidad = (ArrayList<Nacionalidad>) tempList;
			                listaNacionalidad = tempListaNacionalidad;
			            }
			        }
			
			        if (listaNacionalidad != null) {
			            for (Nacionalidad nac : listaNacionalidad) {
			    %>
			                <option value="<%= nac.getId() %>"><%= nac.getNombre() %> </option>
			    <%
			            }
			        }
			    %>
			    </select>
			    </p>
		      	
		      	<p>
			      	<label for="fechaNac">Fecha de nacimiento:</label>
			      	<input id="fechaNac" class="form-control" type="date" max="2006-12-31" required name="fecha"></input>
		      	</p>
		      	
		      	<p>
		      	<label for="provincia">Provincia:</label>
			    <select id="provincia" name="txtProvincia" class="form-control" required onchange="filtrarLocalidades()">
			    <option value="" disabled hidden selected>--seleccione opcion--</option>
			    <%
			        ArrayList<Provincia> listaProvincia = null;
			        Object listaP = request.getAttribute("listaPro");
			        if (listaP instanceof ArrayList<?>) {
			            ArrayList<?> tempList = (ArrayList<?>) listaP;
			            if (tempList.isEmpty() || tempList.get(0) instanceof Provincia) {
			                @SuppressWarnings("unchecked")
			                ArrayList<Provincia> tempListaProvincia = (ArrayList<Provincia>) tempList;
			                listaProvincia = tempListaProvincia;
			            }
			        }
			
			        if (listaProvincia != null) {
			            for (Provincia pro : listaProvincia) {
			    %>
			                <option value="<%= pro.getId() %>"><%= pro.getNombre() %> </option>
			    <%
			            }
			        }
			    %>
			    </select>
			    </p>
		      	
		      	<p>
		      	<label for="localidad">Localidad:</label>
			    <select id="localidad" name="txtLocalidad" class="form-control" required>
			    <option value="" disabled hidden selected>--seleccione opcion--</option>
			    <%
			        ArrayList<Localidad> listaLocalidad = null;
			        Object listaL = request.getAttribute("listaLoc");
			        if (listaL instanceof ArrayList<?>) {
			            ArrayList<?> tempList = (ArrayList<?>) listaL;
			            if (tempList.isEmpty() || tempList.get(0) instanceof Localidad) {
			                @SuppressWarnings("unchecked")
			                ArrayList<Localidad> tempListaLocalidad = (ArrayList<Localidad>) tempList;
			                listaLocalidad = tempListaLocalidad;
			            }
			        }
			
			        if (listaLocalidad != null) {
			            for (Localidad loc : listaLocalidad) {
			    %>
					<option value="<%= loc.getId() %>" data-provincia="<%= loc.getIdProvincia() %>"><%= loc.getNombre() %> </option>
			    <%
			            }
			        }
			    %>
			    </select>
			    </p>
			    
			    <p>
			        <label for="direccion">Direccion:</label>
			        <input id="direccion" class="form-control" type="text" pattern="^[a-zA-Z0-9\s.,#-]+$" placeholder="Ingrese direccion" required name="txtDireccion">
		      	</p>
		      	
		      	<p>
			        <label for="correo">Correo electronico:</label>
			        <input id="correo" class="form-control" type="email"  placeholder="Ingrese correo" required name="txtCorreo">
		      	</p>
		      	
		      	<p>
			        <label for="telefono">Telefono:</label>
			        <input id="telefono" class="form-control" type="text" pattern="^[0-9+\-().\s]{8,16}$" placeholder="Ingrese telefono" required name="txtTelefono">
		      	</p>
		      	
		      	<p>
			        <label for="usuario">Usuario:</label>
			        <input id="usuario" class="form-control" type="text" pattern="^[a-zA-Z0-9_\-]+$" placeholder="Ingrese nombre usuario" required name="txtUsuario">
		      	</p>
		      	
		      	<p>
		      		<label for="contraseña">Contraseña:</label>
					<input id="contraseña" class="form-control" type="password" placeholder="Ingrese contraseña" required name="txtContraseña" pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?=.*\W).{8,}" title="La contraseña debe contener al menos 8 caracteres, incluyendo al menos una letra mayúscula, una letra minúscula, un número y un carácter especial.">
		      	</p>
		      	
		      	<p>
			      	<label for="contraseña1">Confirme contraseña:</label>
					<input id="contraseña1" class="form-control" type="password" placeholder="Confirme contraseña" required name="txtContraseña1" oninput="limpiarAlerta()" pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?=.*\W).{8,}" title="La contraseña debe contener al menos 8 caracteres, incluyendo al menos una letra mayúscula, una letra minúscula, un número y un carácter especial.">
		      		<span id="mensajeError" style="color: red; display: none;">Las contraseñas no coinciden</span>
		      	</p>
		      	
		      	<!--VALIDAR FORMULARIO-->
		        <script>
		        function validarFormulario() {
                    if (!validarContraseñas()) {
                        return false;
                    }
                    return true;
		        	}
		        
		            function validarContraseñas() {
		                var contraseña1 = document.getElementById("contraseña").value;
		                var contraseña2 = document.getElementById("contraseña1").value;		  
		                if (contraseña1 !== contraseña2) {
		                    document.getElementById("mensajeError").style.display = "inline";
		                    return false;
		                } else {
		                    document.getElementById("mensajeError").style.display = "none";
		                    return true;
		                }
		            }
		
		            function limpiarAlerta() {
		                document.getElementById("mensajeError").style.display = "none";
		            }		            
		        </script>
		        <!--FIN VALIDAR FORMULARIO-->
		        
		        <!--FILTRAR LOCALIDADES-->
				<script>
				function filtrarLocalidades() {
				    var provinciaSeleccionada = document.getElementById("provincia").value;
				    var localidadSelect = document.getElementById("localidad");	
				    console.log("Provincia seleccionada:", provinciaSeleccionada);
				
				    for (var i = 0; i < localidadSelect.options.length; i++) {
				        var option = localidadSelect.options[i];
				        var optionProvincia = option.getAttribute("data-provincia");
				        console.log("Localidad:", option.textContent, "Provincia:", optionProvincia);
				
				        if (optionProvincia === provinciaSeleccionada || option.value === "") {
				            option.style.display = "block"; 
				        } else {
				            option.style.display = "none";
				        }
				    }
				
				    //RESETEAR SELECCION
				    if (localidadSelect.selectedIndex !== -1 && localidadSelect.options[localidadSelect.selectedIndex].style.display === "none") {
				        localidadSelect.value = "";
				    }
				}
				</script>
				<!--FIN FILTRAR LOCALIDADES-->
		      	
		      	<p class="d-flex justify-content-end mr-2">
        			<input id="agregar" class="btn btn-primary" type="submit" value="Agregar" required name="btnAgregar">
      			</p>
		    	
		    	</fieldset>
		    	</form>
		    	
		    	<!-- ALERTAS -->
				<%
					if (request.getAttribute("estadoCliente") != null) {
				%>
					 <script>
				        function mostrarAlerta() {
				            alert("¡cliente agregado con exito!");
				        }
				        mostrarAlerta();
					 </script>
				<%
					}
				%>				
				
				
				<!-- FIN ALERTAS -->
				
	    		</div>
	    	</div>
	    </div>
    </div>

    <!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.14.3/dist/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.1.3/dist/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>


		
  </body>
</html>
