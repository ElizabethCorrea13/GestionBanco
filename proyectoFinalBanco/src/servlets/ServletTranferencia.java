package servlets;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidad.Cliente;
import entidad.Cuenta;
import entidad.Movimiento;
import entidad.TipoDeMovientos;
import entidad.Usuario;
import negocio.ClienteNegocio;
import negocio.CuentaNegocio;
import negocio.MovimientoNegocio;
import negocio.TipoDeMovimientoNegocio;
import negocioImpl.ClienteNegocioImpl;
import negocioImpl.CuentaNegocioImpl;
import negocioImpl.TipoDeMovimientoNegocioImpl;
import negocioImpl.MovimientoNegocioImpl;

@WebServlet("/ServletTranferencia")
public class ServletTranferencia extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ClienteNegocio clienteNegocio;
    private CuentaNegocio cuentaNegocio;
    private TipoDeMovimientoNegocio tipoDeMovimientoNegocio;
    private MovimientoNegocio movimientoNegocio;
    
    
    public ServletTranferencia() {
        clienteNegocio = new ClienteNegocioImpl();
        tipoDeMovimientoNegocio = new TipoDeMovimientoNegocioImpl();
        movimientoNegocio = new MovimientoNegocioImpl();
        cuentaNegocio = new CuentaNegocioImpl();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Usuario nombreUsuario = (Usuario) request.getSession().getAttribute("usuario");

        if (nombreUsuario == null) {
            request.setAttribute("mensaje", "Debe iniciar sesión para realizar una transferencia.");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/login.jsp");
            dispatcher.forward(request, response);
            return;
        }

        Cliente cliente = clienteNegocio.obtenerClientePorNombreUsuario(nombreUsuario.getNombreUsuario());

        if (cliente == null) {
            request.setAttribute("mensaje", "Cliente no encontrado.");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/paginaTranferir.jsp");
            dispatcher.forward(request, response);
            return;
        }

        int idCliente = cliente.get_IdCliente();
        List<Cuenta> listarCuentas = cuentaNegocio.ListaCuentasPorCliente(idCliente);
        if (listarCuentas != null && !listarCuentas.isEmpty()) {
            request.setAttribute("listarCuentas", listarCuentas);
        } else {
            request.setAttribute("mensaje", "No hay cuentas disponibles.");
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("/paginaTranferir.jsp");
        dispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nroCuentaOrigen = request.getParameter("NroCuenta");
        String CbuOrigen = request.getParameter("CBUO");
        String cbuDestino = request.getParameter("CBUD");
        String concepto = request.getParameter("concept");
        String fecha = request.getParameter("date");
        double monto = Double.parseDouble(request.getParameter("Monto"));

        int nroCuenta = Integer.parseInt(nroCuentaOrigen);

        Cuenta cuentaOrigen = cuentaNegocio.obtenerCuentaPorNumero(nroCuenta);
        Cuenta cuentaDestino = cuentaNegocio.obtenerCuentaPorCBU(cbuDestino);

        if (cuentaOrigen == null || cuentaDestino == null) {
            request.setAttribute("mensaje", "El CBU de destino no existe.");
            Usuario nombreUsuario = (Usuario) request.getSession().getAttribute("usuario");
            Cliente cliente = clienteNegocio.obtenerClientePorNombreUsuario(nombreUsuario.getNombreUsuario());
            int idCliente = cliente.get_IdCliente();
            List<Cuenta> listarCuentas = cuentaNegocio.ListaCuentasPorCliente(idCliente);
            request.setAttribute("listarCuentas", listarCuentas);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/paginaTranferir.jsp");
            dispatcher.forward(request, response);
            return;
        }

        if (cuentaOrigen.getSaldo() < monto) {
            request.setAttribute("mensaje", "Saldo insuficiente en la cuenta de origen.");
            Usuario nombreUsuario = (Usuario) request.getSession().getAttribute("usuario");
            Cliente cliente = clienteNegocio.obtenerClientePorNombreUsuario(nombreUsuario.getNombreUsuario());
            int idCliente = cliente.get_IdCliente();
            List<Cuenta> listarCuentas = cuentaNegocio.ListaCuentasPorCliente(idCliente);
            request.setAttribute("listarCuentas", listarCuentas);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/paginaTranferir.jsp");
            dispatcher.forward(request, response);
            return;
        }
        
        if (CbuOrigen.equalsIgnoreCase(cbuDestino)) {
            request.setAttribute("mensaje", "No te podes tranferir a tu misma cuenta.");
            Usuario nombreUsuario = (Usuario) request.getSession().getAttribute("usuario");
            Cliente cliente = clienteNegocio.obtenerClientePorNombreUsuario(nombreUsuario.getNombreUsuario());
            int idCliente = cliente.get_IdCliente();
            List<Cuenta> listarCuentas = cuentaNegocio.ListaCuentasPorCliente(idCliente);
            request.setAttribute("listarCuentas", listarCuentas);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/paginaTranferir.jsp");
            dispatcher.forward(request, response);
            return;
        }

        cuentaOrigen.setSaldo(cuentaOrigen.getSaldo() - monto);
        cuentaDestino.setSaldo(cuentaDestino.getSaldo() + monto);

        Movimiento movimiento = new Movimiento();
        movimiento.setNroCuenta(cuentaOrigen);
        movimiento.setCBU_Origen(Integer.parseInt(cuentaOrigen.getCBU()));
        movimiento.setCBU_Destino(Integer.parseInt(cuentaDestino.getCBU()));
        movimiento.setFechaDeMovimiento(LocalDate.parse(fecha));
        movimiento.setDetalle(concepto);
        movimiento.setImporte(monto);
        TipoDeMovientos tipoDeMovientos = tipoDeMovimientoNegocio.obtenerTipoPorId(4);
        movimiento.setTipoDeMovientos(tipoDeMovientos);
        boolean exitoMovimiento = movimientoNegocio.registrarMovimiento(movimiento);
        boolean exitoActualizarOrigen = cuentaNegocio.actualizarCuenta(cuentaOrigen);
        boolean exitoActualizarDestino = cuentaNegocio.actualizarCuenta(cuentaDestino);

        if (exitoMovimiento && exitoActualizarOrigen && exitoActualizarDestino) {
            request.setAttribute("mensaje", "Transferencia realizada con éxito.");
        } else {
            request.setAttribute("mensaje", "Error al realizar la transferencia.");
        }

        // Recargar la lista de cuentas después de la transferencia
        Usuario nombreUsuario = (Usuario) request.getSession().getAttribute("usuario");
        Cliente cliente = clienteNegocio.obtenerClientePorNombreUsuario(nombreUsuario.getNombreUsuario());
        int idCliente = cliente.get_IdCliente();
        List<Cuenta> listarCuentas = cuentaNegocio.ListaCuentasPorCliente(idCliente);
        request.setAttribute("listarCuentas", listarCuentas);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/paginaTranferir.jsp");
        dispatcher.forward(request, response);
    }
    
   
    
}
