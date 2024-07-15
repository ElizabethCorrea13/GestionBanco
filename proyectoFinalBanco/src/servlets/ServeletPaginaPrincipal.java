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
import entidad.Cuenta;
import entidad.Movimiento;
import entidad.Usuario;
import negocio.CuentaNegocio;
import negocio.MovimientoNegocio;
import negocioImpl.CuentaNegocioImpl;
import negocioImpl.MovimientoNegocioImpl;
import negocio.ClienteNegocio;
import negocioImpl.ClienteNegocioImpl;
import negocio.PrestamoNegocio;
import negocioImpl.PrestamoNegocioImpl;

@WebServlet("/ServeletPaginaPrincipal")
public class ServeletPaginaPrincipal extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ClienteNegocio negCli = new ClienteNegocioImpl();
	CuentaNegocio cuentaNeg = new CuentaNegocioImpl();
	PrestamoNegocio prestamoNeg = new PrestamoNegocioImpl();
	MovimientoNegocio movNeg = new MovimientoNegocioImpl();
	List<Cuenta> list= new ArrayList<>();
	List<Movimiento> listMo= new ArrayList<>();
	private Cuenta NrCuenta;
	private String cbu;
	private double saldo;
    public ServeletPaginaPrincipal() {
        super();
       
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
            Usuario nombreUsuario = (Usuario) request.getSession().getAttribute("usuario");
    		Cliente cli = negCli.obtenerClientePorNombreUsuario(nombreUsuario.getNombreUsuario());
    		list = cuentaNeg.ListaCuentasPorCliente(cli.get_IdCliente());
    		 
        	request.setAttribute("listarCu", list);
        	HttpSession session = request.getSession();
        	System.out.println(session.getAttribute("NrcuentaMov"));
            if(request.getParameter("cuentaSeleccionada")!= null) {
            	
            	int NrCuenta =Integer.parseInt(request.getParameter("cuentaSeleccionada"));
            	System.out.println(NrCuenta);
            	for(Cuenta i : list) {
            		if(i.getNumeroCuenta() == NrCuenta) {
            			cbu = i.getCBU();
            			saldo = i.getSaldo();
            		}
            	}
            	 session.setAttribute("NrcuentaMov", NrCuenta);
            }
            else if(session.getAttribute("NrcuentaMov") == null) {
            	
            	NrCuenta = cuentaNeg.obtenerCuentaPorCliente(cli.get_IdCliente());
                System.out.println(NrCuenta.getNumeroCuenta());
                for(Cuenta i : list) {
            		if(i.getNumeroCuenta() == NrCuenta.getNumeroCuenta()) {
            			cbu = i.getCBU();
            			saldo = i.getSaldo();
            		}
            	}
                session.setAttribute("NrcuentaMov", NrCuenta.getNumeroCuenta());
            }
            
            if(request.getParameter("Param") != null) {
            	String opcion = request.getParameter("Param").toString();
                System.out.print(opcion);
                switch (opcion) {
                case "Salir":
                	 System.out.println("entro");
                 	session.setAttribute("NrcuentaMov", null);
                 	RequestDispatcher dispatchersalir = request.getRequestDispatcher("/Login.jsp");
                 	dispatchersalir.forward(request, response);
                	break;
                }
            }
            
            listMo =  movNeg.listarTransferencias((int)request.getSession().getAttribute("NrcuentaMov"));
        	request.setAttribute("listarMovimiento", listMo);
            session.setAttribute("cbu", cbu);
            session.setAttribute("saldo", saldo);
            RequestDispatcher dispatcherDelete = request.getRequestDispatcher("/PaginaPrincipal.jsp");
            dispatcherDelete.forward(request, response);
            
}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		  
	}

}
