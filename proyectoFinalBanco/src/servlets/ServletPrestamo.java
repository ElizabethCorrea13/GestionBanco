package servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entidad.Cliente;
import entidad.Usuario;
import entidad.Cuenta;
import entidad.Cuota;
import entidad.Prestamo;
import negocio.ClienteNegocio;
import negocioImpl.ClienteNegocioImpl;

import negocio.CuentaNegocio;
import negocio.PrestamoNegocio;
import negocioImpl.CuentaNegocioImpl;
import negocioImpl.PrestamoNegocioImpl;
import negocioImpl.pagoCuotaPrestamoNegocioImpl;


@WebServlet("/ServletPrestamo")
public class ServletPrestamo extends HttpServlet {
	private static final long serialVersionUID = 1L;
	PrestamoNegocio prNeg = new PrestamoNegocioImpl();
	ClienteNegocio negCli = new ClienteNegocioImpl(); 
	CuentaNegocio negCue = new CuentaNegocioImpl();
	List<Prestamo> listMo= new ArrayList<>();
  
    public ServletPrestamo() {

    }

	
protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//precargar ddl de cuentas
		if(request.getParameter("linkPedidoPrestamo")!=null && request.getSession().getAttribute("usuario")!=null) {
			Usuario userLogeado = (Usuario) request.getSession().getAttribute("usuario");
			Cliente clienteLogeado = null;
			if(userLogeado!=null) {
				 clienteLogeado = new Cliente ();
				 clienteLogeado = negCli.obtenerClientePorNombreUsuario(userLogeado.getNombreUsuario());
			}
			else {
				System.out.println("No se pudo obtener el usuario");
			}
			if(clienteLogeado!=null) {
				ArrayList<Cuenta> cuentasClienteLogeado = new ArrayList<Cuenta>();
				cuentasClienteLogeado= negCue.ListaCuentasPorCliente(clienteLogeado.get_IdCliente());
				request.setAttribute("listaCuentasPorCliente", cuentasClienteLogeado);
				listMo = prNeg.PrestamosPorNrCuenta((int)request.getSession().getAttribute("NrcuentaMov"));
		    	request.setAttribute("listarPrestamos", listMo);
				RequestDispatcher dispatcher = request.getRequestDispatcher("/paginaPedidoPrestamo.jsp");
                dispatcher.forward(request, response);
			}
			else {
				System.out.println("No se pudo obtener el cliente segun usuario");
			}	
		}
		else {
			System.out.println("el link no se habilito desde el menu o el usuario no se pudo reconocer de la sesion");
		}
		
			
		
		
		if (request.getParameter("Param") != null) {
            String opcion = request.getParameter("Param").toString();
            
            switch (opcion) {
            case "listaPrestamo":
            	cargarListaPrestamo(request, response);
            	cargarListaPrestamoAR(request, response);
                RequestDispatcher dispatcher1 = request.getRequestDispatcher("/SolicitudesDePrestamo.jsp");
                dispatcher1.forward(request, response);
                break;
             case "MODIFICAR":
                 int idPrestamo = Integer.parseInt(request.getParameter("id"));
                 boolean estado = Boolean.parseBoolean(request.getParameter("estado"));
                 
                 boolean eliminacionExitosa = prNeg.ModificarEstado(idPrestamo,estado);

                 if (eliminacionExitosa) {
                     request.setAttribute("mensaje", "Prestamo modificado exitosamente.");
                 } else {
                     request.setAttribute("mensaje", "Error al modificar el estado.");
                 }

                 cargarListaPrestamo(request, response);
                 cargarListaPrestamoAR(request, response);
                 RequestDispatcher dispatcher2 = request.getRequestDispatcher("/SolicitudesDePrestamo.jsp");
                 dispatcher2.forward(request, response);
                 break;
                default:
                    break;
            }
		}
		
			if(request.getParameter("ParamMisPrestamos")!=null){	
				HttpSession session = request.getSession();
				if(session.getAttribute("NrcuentaMov")!=null) {
					
					listMo = prNeg.PrestamosPorNrCuenta((int)request.getSession().getAttribute("NrcuentaMov"));
			    	request.setAttribute("listarPrestamos", listMo);
					RequestDispatcher dispatcher1 = request.getRequestDispatcher("/MisPrestamos.jsp");
	                dispatcher1.forward(request, response);
				}
				else{
					System.out.println("no encontro la variable session ");
				}
			}
			else{
				System.out.println("no encontro el parametro ");
			}
			
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Prestamo objPrestamo = new Prestamo ();
		boolean resultado = false;
		if(request.getParameter("btnSolicitarPrestamo")!=null) {
			try {
				int numCuenta = Integer.parseInt(request.getParameter("cuentaSeleccionada"));
				float importeSolicitado = Float.valueOf(request.getParameter("montoImporte")); 
				float ImporteConIntereses = importeSolicitado + (importeSolicitado * 0.20f);
				int cantidadCuotas = Integer.parseInt(request.getParameter("cuotas"));
				float montoPorCuota = ImporteConIntereses /cantidadCuotas;
				objPrestamo.setNroCuenta_Cu(numCuenta);
				objPrestamo.setImportePedido(importeSolicitado);
				objPrestamo.setImporteConIntereses(ImporteConIntereses);
				objPrestamo.setMontoCuota(montoPorCuota);
				objPrestamo.setCantidadCuotas(cantidadCuotas);
				 resultado= prNeg.insertarNuevoPrestamo(objPrestamo);
			}
			catch (Exception e) {
	             e.printStackTrace();
	             
	         }
				if(resultado) {
					request.setAttribute("mensajeExito", "Cuenta agregada con éxito.");
				}
				else {
					 request.setAttribute("mensajeError", "Error al agregar a la base de datos: ");
					System.out.println("NO se agrego a la BD");
				}
			  RequestDispatcher dispatcher = request.getRequestDispatcher("/paginaPedidoPrestamo.jsp");
              dispatcher.forward(request, response);
		}
		
		
		if(request.getParameter("BtnCargarPrestamo")!= null) 
		{
			Prestamo x = new Prestamo();
			x.setImportePedido(Integer.parseInt(request.getParameter("TxtImporte")));
			x.setCantidadCuotas(Integer.parseInt(request.getParameter("TxtCuotas")));
		} 
	}




void cargarListaPrestamo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    ArrayList<Prestamo> listaPrestamos = prNeg.listarPrestamos();
    if (listaPrestamos != null && !listaPrestamos.isEmpty()) {
        request.setAttribute("listaPrestamos", listaPrestamos);
    } else {
        request.setAttribute("mensaje", "No hay solicitudes de prestamo.");
    }
}

void cargarListaPrestamoAR(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    ArrayList<Prestamo> listaPrestamosAR = prNeg.listarPrestamosAR();
    if (listaPrestamosAR != null && !listaPrestamosAR.isEmpty()) {
        request.setAttribute("listaPrestamosAR", listaPrestamosAR);
    } else {
        request.setAttribute("mensaje", "No hay solicitudes de prestamo.");
    }
}


}