package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entidad.Usuario;
import negocioImpl.UsuarioNegocioImpl;

/**
 * Servlet implementation class ServletUsuario
 */
@WebServlet("/ServletUsuario")
public class ServletUsuario extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public ServletUsuario() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Usuario userIngresado;
		UsuarioNegocioImpl userNeg = new UsuarioNegocioImpl(); // Inicializar fuera del bloque if

		if (request.getParameter("btnAgregar") != null) {
		    String nombreUsuarioIngresado = request.getParameter("usuario");
		    String contraseñaIngresada = request.getParameter("contraseña");

		    if (nombreUsuarioIngresado != null && contraseñaIngresada != null) {
		        userIngresado = new Usuario();
		        userIngresado.setNombreUsuario(nombreUsuarioIngresado);
		        userIngresado.setContraseña(contraseñaIngresada);
		       

		        if (userNeg.ValidarUsuario(userIngresado)) {
		        	
		        	 HttpSession session = request.getSession();//obtengo sesion o creo una
		        	 //en este caso crearia una
		             session.setAttribute("usuario", userIngresado);
		             //guardo el usuario
		        	if(userIngresado.isAdmin()) {
		                response.sendRedirect("ServletCliente?Param=list1");
		        	}
		        	else {
		        		response.sendRedirect("ServeletPaginaPrincipal");
		        	}
		           
		        } else {
		        	 request.setAttribute("IngresoINVALIDO", true);
		                RequestDispatcher dispatcher = request.getRequestDispatcher("/Login.jsp");
		                dispatcher.forward(request, response);
		        }
		    } 
		}

	}

	}


