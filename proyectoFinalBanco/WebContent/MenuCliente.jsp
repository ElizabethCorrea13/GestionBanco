<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    <%@page import="entidad.Usuario"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%
HttpSession sessionEnMenu = request.getSession();

Usuario usuarioLogeado = (Usuario) session.getAttribute("usuario");
%>

  <div class="sidebar-sticky">
            <div class="logo">
              <a href="PaginaPrincipal.jsp">BlueBank</a>
            </div>
            <div class="login-section">
              <a href="#">
                <svg xmlns="http://www.w3.org/2000/svg" fill="currentColor" class="bi bi-person" viewBox="0 0 16 16">
                  <path d="M8 8a3 3 0 100-6 3 3 0 000 6zM0 15a7 7 0 1114 0H0z"/>
                </svg>
              </a>
              <label><%=usuarioLogeado.getNombreUsuario() %></label>
            </div>
            <ul class="nav flex-column">
              <li class="nav-item">
                <a class="nav-link active" href="ServeletPaginaPrincipal">
                  Home
                </a>
              </li>
              <li class="nav-item">
                <a class="nav-link" href="ServletMovimiento">
                  Movimientos
                </a>
              </li>
              <li class="nav-item">
                <a class="nav-link" href="ServletTranferencia">
                  Transferencias
                </a>
              </li>
              <li class="nav-item">
                <a class="nav-link" href="ServletPrestamo?linkPedidoPrestamo=activo.jsp">
                  Prestamos
                </a>
              </li>
              <li class="nav-item">
                <a class="nav-link" href="ServletPrestamo?ParamMisPrestamos=activo">
                  Mis prestamos
                </a>
              </li>
              <li class="nav-item">
                <a class="nav-link" href="ServletCliente?Param=info">
                  Informacion Personal
                </a>
              </li>
               <li class="nav-item">
                <a class="nav-link" href="ServeletPaginaPrincipal?Param=Salir">
                  Cerrar Sesion
                </a>
              </li>
            </ul>
          </div>

</body>
</html>