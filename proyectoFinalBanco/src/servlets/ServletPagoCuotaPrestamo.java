package servlets;

import entidad.Cliente;
import entidad.Cuenta;
import entidad.Cuota;
import entidad.Prestamo;
import entidad.Usuario;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import negocio.PrestamoNegocio;
import negocio.pagoCuotaPrestamoNegocio;
import negocioImpl.ClienteNegocioImpl;
import negocioImpl.CuentaNegocioImpl;
import negocioImpl.PrestamoNegocioImpl;
import negocioImpl.pagoCuotaPrestamoNegocioImpl;

@WebServlet("/ServletPagoCuotaPrestamo")
public class ServletPagoCuotaPrestamo extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private PrestamoNegocio prestamoNegocio = new PrestamoNegocioImpl();
    private pagoCuotaPrestamoNegocio pagoCu = new pagoCuotaPrestamoNegocioImpl();
    private ClienteNegocioImpl negCli = new ClienteNegocioImpl();
    private CuentaNegocioImpl negCue = new CuentaNegocioImpl();

    public ServletPagoCuotaPrestamo() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    		int idPrestamo = Integer.parseInt(request.getParameter("idPrestamo"));
            int numeroCuota = Integer.parseInt(request.getParameter("cuotaPagar"));
            int numeroCuenta = Integer.parseInt(request.getParameter("cuentaDebitar"));
            HttpSession session = request.getSession();//obtengo sesion o creo una
            Usuario userLogeado = (Usuario) session.getAttribute("usuario");
            Prestamo prestamo = prestamoNegocio.obtenerPrestamoPorId(idPrestamo);
            ArrayList<Cuota> listaCuota = (ArrayList<Cuota>) prestamoNegocio.obtenerCuotasPorPrestamo(idPrestamo);
            Cliente clientelogeado = negCli.obtenerClientePorNombreUsuario(userLogeado.getNombreUsuario());
            ArrayList<Cuenta> cuentasXusuarioLogeado = negCue.ListaCuentasPorCliente(clientelogeado.get_IdCliente());
            
            if (request.getParameter("btnPagar") != null) {
                
                boolean pagoExitoso = pagoCu.pagarCuota(idPrestamo, numeroCuota,numeroCuenta);
               
                if (pagoExitoso) {
                    if (listaCuota != null && !listaCuota.isEmpty()) {
                        request.setAttribute("listaCuota", listaCuota);
                    } else {
                        request.setAttribute("mensaje", "No hay cuotas disponibles para este préstamo.");
                    }
                    if (cuentasXusuarioLogeado != null && !cuentasXusuarioLogeado.isEmpty()) {
                    	request.setAttribute("listaCuentasXcliente", cuentasXusuarioLogeado);
                    } else {
                        request.setAttribute("mensaje", "no hay cuentas encontradas");
                    }

                    request.setAttribute("prestamo", prestamo);
                    request.setAttribute("mensaje", "Cuota pagada exitosamente.");
                } else {
                    if (listaCuota != null && !listaCuota.isEmpty()) {
                        request.setAttribute("listaCuota", listaCuota);
                    } else {
                        request.setAttribute("mensaje", "No hay cuotas disponibles para este préstamo.");
                    }
                    if (cuentasXusuarioLogeado != null && !cuentasXusuarioLogeado.isEmpty()) {
                    	request.setAttribute("listaCuentasXcliente", cuentasXusuarioLogeado);
                    } else {
                        request.setAttribute("mensaje", "no hay cuentas encontradas");
                    }

                    request.setAttribute("prestamo", prestamo);
                    request.setAttribute("mensaje", "Error al procesar el pago de la cuota.");
                }

                boolean prestamoFinalizado = pagoCu.verificarCuotasPagadas(idPrestamo);

                if (prestamoFinalizado) {
                	pagoCu.cambiarEstadoPrestamo(idPrestamo, true);
                    request.setAttribute("mensajePrestamo", "¡Prestamo finalizado!");
                }

                RequestDispatcher dispatcher = request.getRequestDispatcher("/PagoCuotaPrestamo.jsp");
                dispatcher.forward(request, response);
            }
    	
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getParameter("ParamPagar") != null) {
            int idPrestamo = Integer.parseInt(request.getParameter("id"));
            Prestamo prestamo = prestamoNegocio.obtenerPrestamoPorId(idPrestamo);
            ArrayList<Cuota> listaCuota = (ArrayList<Cuota>) prestamoNegocio.obtenerCuotasPorPrestamo(idPrestamo);
            
            HttpSession session = request.getSession();//obtengo sesion o creo una
            Usuario userLogeado = (Usuario) session.getAttribute("usuario");
            
            Cliente clientelogeado = negCli.obtenerClientePorNombreUsuario(userLogeado.getNombreUsuario());
            ArrayList<Cuenta> cuentasXusuarioLogeado = negCue.ListaCuentasPorCliente(clientelogeado.get_IdCliente());
            request.setAttribute("listaCuentasXcliente", cuentasXusuarioLogeado);
            if (cuentasXusuarioLogeado != null && !cuentasXusuarioLogeado.isEmpty()) {
            	request.setAttribute("listaCuentasXcliente", cuentasXusuarioLogeado);
            } else {
                request.setAttribute("mensaje", "no hay cuentas encontradas");
            }

            if (listaCuota != null && !listaCuota.isEmpty()) {
                request.setAttribute("listaCuota", listaCuota);
            } else {
                request.setAttribute("mensaje", "No hay cuotas disponibles para este préstamo.");
            }

            request.setAttribute("prestamo", prestamo);

            RequestDispatcher dispatcher = request.getRequestDispatcher("/PagoCuotaPrestamo.jsp");
            dispatcher.forward(request, response);
        	}
    }
}
