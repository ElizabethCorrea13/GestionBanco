<%@page import="dao.MovimientoDao"%>
<%@page import="daoImpl.MovimientoImpl"%>
<%@page import="entidad.Movimiento"%>
<%@page import="negocio.MovimientoNegocio"%>
<%@page import="negocioImpl.MovimientoNegocioImpl"%>
<%@page import="servlets.ServletReporte"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
  <head>
    <!-- Meta tags requeridos -->
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Reporte movimientos</title>

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
        List<Movimiento> listaM = new ArrayList<Movimiento>();
        if (request.getAttribute("listaReporte") != null) {
            listaM = (List<Movimiento>) request.getAttribute("listaReporte");
        } else {
        }
      %>
  
    <div class="container-fluid">
    
	    <div class="row">
	    
        <div class="col-3 bg-light border-right">
          <!-- MENU LATERAL IZQUIERDO -->
		<jsp:include page="MenuAdmin.jsp" />
        </div>

	    	
	    <div class="col-9 ">
	    <div class="row">
	    	<div class="col d-flex justify-content-center">
	    	<form method="post" action="ServletReporte" class="mt-5">

        		<div>
        		<h3 class="text-center">Reporte general de movimientos</h3>
                <p class="text-left mt-4">A continuación, seleccione el periodo de fecha del cual desea ver los movimientos realizados:</p>
        		</div>
        		     
                <div class="form-row mt-4">
                <!-- CONTROLES DE INICIO -->
                	<div class="form-group col-2 d-flex justify-content-left align-items-end">
                    	<label for="fechaI">Fecha inicio:</label>
                    </div>
                    <div class="form-group col-3 p-0 ">
                        <input id="fechaI" type="date" required name="fechaInicio" class="form-control">
                    </div>
                    <div class="form-group col-2 d-flex justify-content-center align-items-end">
                         <label for="fechaF">Fecha final:</label>
                    </div>
                    <div class="form-group p-0 col-3">
                         <input id="fechaF" type="date" required name="fechaFinal" class="form-control" disabled>
                    </div>
                    <div class="form-group col-2 d-flex justify-content-end align-items-end">
                         <input id="aceptar" type="submit" value="Aceptar" class="btn btn-primary" required name="btnAceptar">
                    </div>
                    
			    <script>				    
			        document.addEventListener('DOMContentLoaded', (event) => {
			            const date1 = document.getElementById('fechaI');
			            const date2 = document.getElementById('fechaF');
			            const today = "2024-07-11";
			            
			            date1.max = today;
			            date2.max = today;
			
			            date1.addEventListener('change', function() {
			                if (date1.value) {
			                    date2.disabled = false;
			                    date2.min = date1.value;
			                } else {
			                    date2.disabled = true;
			                    date2.value = '';
			                    date2.min = '';
			                }
			            });
			        });
			    </script> 
                </div>
                
              <%
		        if (request.getAttribute("seleccion1") != null & request.getAttribute("seleccion2") != null) {
	                String fecha1=request.getAttribute("seleccion1").toString();
	                String fecha2=request.getAttribute("seleccion2").toString();
	                %><div class="mt-2">
	                <b><label>Perdiodo seleccionado:</label></b>&nbsp;&nbsp;<%=fecha1%>&nbsp;&nbsp;al&nbsp;&nbsp;<%=fecha2%>
	                </div>
		       <% } else {
		        }
		      %>
                 
                 <!-- NO HAY MOVIMIENTOS -->
                <%String mensajeReporte = (String) request.getAttribute("mensajeReporte");
                if (mensajeReporte != null) { %>
                  <div class="alert alert-danger mt-3" role="alert">
                     <%= mensajeReporte %>
                  </div>
                     <% } %>
                
                

                
                <div style="max-width: 550px;" style="max-width: 550px;">
                <!-- CUADRO DESCRIPTIVO -->
                					    <!-- OBTENER VALORES DE CUADRO DISCRIPTIVO-->                    
		                <% if (request.getAttribute("listaReporte") != null) {  
		                int qAltaCuenta = (int) request.getAttribute("qAltaCuenta");
		                double sAltaCuenta = (double) request.getAttribute("sAltaCuenta");
		                int qAltaPrestamo = (int) request.getAttribute("qAltaPrestamo");
		                double sAltaPrestamo = (double) request.getAttribute("sAltaPrestamo");
		                int qPagoPrestamo = (int) request.getAttribute("qPagoPrestamo");
		                double sPagoPrestamo = (double) request.getAttribute("sPagoPrestamo");
		                int qTransferencias = (int) request.getAttribute("qTransferencias");
		                double sTransferencias = (double) request.getAttribute("sTransferencias"); %>
			    <table class="table table-bordered mt-3">
			        <thead class="thead-light">
			            <tr class="text-center">
			                <th></th>
			                <th>CANTIDAD</th>
			                <th>TOTAL DE IMPORTES</th>
			            </tr>
			        </thead>
			        <tbody>

			            <tr class="text-center">
			                <th scope="row" style="background-color: #F5F7F8;" class="text-muted">ALTAS DE CUENTAS</th>
			                <td><%=qAltaCuenta%></td>
			                <td>$<%=sAltaCuenta%></td>
			            </tr>
			            <tr class="text-center">
			                <th scope="row" style="background-color: #F5F7F8;" class="text-muted">ALTAS DE PRESTAMOS</th>
			                <td><%=qAltaPrestamo%></td>
			                <td>$<%=sAltaPrestamo%></td>
			            </tr>
			            <tr class="text-center">
			                <th scope="row" style="background-color: #F5F7F8;" class="text-muted">PAGOS DE PRESTAMOS</th>
			                <td><%=qPagoPrestamo%></td>
			                <td>$<%=sPagoPrestamo%></td>
			            </tr>
			            <tr class="text-center">
			                <th scope="row" style="background-color: #F5F7F8;" class="text-muted">TRANSFERENCIAS</th>
			                <td><%=qTransferencias%></td>
			                <td>$<%=sTransferencias%></td>
			            </tr>
			      		<% } %>
			        </tbody>
			    </table>
                 <!-- FIN CUADRO DESCRIPTIVO -->
                </div>
                
                 <div class="table-responsive table-container mt-5 mb-5" style="max-width: 900px;">
                 <!-- LISTADO -->
                 <table id="table_id" class="table table-bordered small">
                     <thead class="thead-light">
                         <tr class="text-center">
                             <th>ID MOVIMIENTO</th>
                             <th>NUMERO CUENTA</th>
                             <th>CBU ORIGEN</th>
                             <th>CBU DESTINO</th>
                             <th>FECHA DE MOVIMIENTO</th>
                             <th>DETALLE</th>
                             <th>IMPORTE</th>
                             <th>TIPO DE MOVIMIENTO</th>
                         </tr>
                     </thead>
                     <tbody>
                     <% for (Movimiento movimiento : listaM) { %>
						<tr class="text-center">
                            <td><%= movimiento.getIdMoviento()%></td>
                            <td><%= movimiento.getNroCuenta().getNumeroCuenta()%></td>
                            <td><%= movimiento.getCBU_Origen() %></td>
                            <td><%= movimiento.getCBU_Destino() %></td>
                            <td><%= movimiento.getFechaDeMovimiento().toString() %></td>
                            <td><%= movimiento.getDetalle() %></td>
                            <td>$<%= movimiento.getImporte() %></td>
                            <td><%= movimiento.getTipoDeMovientos().getDescripcion() %></td>
                        </tr>
                     <% } %>    
                     </tbody>
                 </table>
                <!-- FIN LISTADO -->
                </div>
                
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