package servlets;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidad.Movimiento;
import negocio.MovimientoNegocio;
import negocioImpl.MovimientoNegocioImpl;


@WebServlet("/ServletReporte")
public class ServletReporte extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	MovimientoNegocio negMov = new MovimientoNegocioImpl();   

    public ServletReporte() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		///ACEPTAR FECHAS
		if (request.getParameter("btnAceptar") != null) {
			try {
				///OBTENGO FECHAS
	            String fechaInicio = request.getParameter("fechaInicio");
	            String fechaFin = request.getParameter("fechaFinal");
	            LocalDate fecha1 = LocalDate.parse(fechaInicio);
	            LocalDate fecha2 = LocalDate.parse(fechaFin);
	            ///OBTENGO REPORTE
				ArrayList <Movimiento> listaMovimientosReporte= negMov.listarReporte(fecha1,fecha2);
		        if (listaMovimientosReporte != null && !listaMovimientosReporte.isEmpty()) {
		            request.setAttribute("listaReporte", listaMovimientosReporte);
		            
		            ///CALCULO CANTIDADES
		            int qAltaCuenta=0, qAltaPrestamo=0, qPagoPrestamo=0, qTransferencias=0;
		            double sAltaCuenta=0, sAltaPrestamo=0, sPagoPrestamo=0, sTransferencias=0;
		            for (Movimiento movimiento : listaMovimientosReporte) {
		                int tipoMovimiento = movimiento.getTipoDeMovientos().getId(); 
		                
		                switch (tipoMovimiento) {
		                    case 1:
		                        qAltaCuenta++;
		                        sAltaCuenta += movimiento.getImporte();  
		                        break;
		                    case 2:
		                        qAltaPrestamo++;
		                        sAltaPrestamo += movimiento.getImporte();
		                        break;
		                    case 3:
		                        qPagoPrestamo++;
		                        sPagoPrestamo += movimiento.getImporte();
		                        break;
		                    case 4:
		                        qTransferencias++;
		                        sTransferencias += movimiento.getImporte();
		                        break;
		                    default:
		                        break;
		                }
		            }
		            ///ENVIO CANTIDADES
					request.setAttribute("qAltaCuenta", qAltaCuenta);
					request.setAttribute("sAltaCuenta", sAltaCuenta);
					request.setAttribute("qAltaPrestamo", qAltaPrestamo);
					request.setAttribute("sAltaPrestamo", sAltaPrestamo);
					request.setAttribute("qPagoPrestamo", qPagoPrestamo);
					request.setAttribute("sPagoPrestamo", sPagoPrestamo);
					request.setAttribute("qTransferencias", qTransferencias);
					request.setAttribute("sTransferencias", sTransferencias);
		            
		        } else {
		            request.setAttribute("mensajeReporte", "No hay movimientos disponibles en el periodo seleccionado.");
		        }
				//ENVIO FECHAS
				request.setAttribute("seleccion1", fechaInicio);
				request.setAttribute("seleccion2", fechaFin);
			}
			catch (Exception e){
				e.printStackTrace();
                request.setAttribute("error", "Error al procesar los datos: " + e.getMessage());
			}
			RequestDispatcher dispatcher = request.getRequestDispatcher("/ReporteTransferencia.jsp");
            dispatcher.forward(request, response);
		}
		///FIN ACEPTAR FECHAS
		
	}
	///FIN DO POST

}
