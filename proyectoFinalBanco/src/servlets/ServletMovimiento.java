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

import entidad.Movimiento;
import negocio.MovimientoNegocio;
import negocioImpl.MovimientoNegocioImpl;


@WebServlet("/ServletMovimiento")
public class ServletMovimiento extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MovimientoNegocio movimientoNeg;
	private List <Movimiento> listaMovimiento;
    public ServletMovimiento() {
        super();
        movimientoNeg = new MovimientoNegocioImpl();
        listaMovimiento = new ArrayList<>();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
		int NrCuenta = (int)request.getSession().getAttribute("NrcuentaMov");
		listaMovimiento = movimientoNeg.movimientosCuenta(NrCuenta);
		System.out.println("-------------------");
		request.setAttribute("listaMovimiento", listaMovimiento);
		RequestDispatcher dispatcherDelete = request.getRequestDispatcher("/MovimientosCuenta.jsp");
        dispatcherDelete.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
