package servlets;

import entidad.Cliente;
import entidad.Cuenta;
import entidad.TipoDeCuenta;
import negocio.ClienteNegocio;
import negocio.CuentaNegocio;
import negocio.TipoDeCuentaNegocio;
import negocioImpl.ClienteNegocioImpl;
import negocioImpl.CuentaNegocioImpl;
import negocioImpl.TipoDeCuentaNegocioImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/ServletCuentas")
public class ServletCuentas extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private ClienteNegocio clienteNegocio;
    private TipoDeCuentaNegocio tipoDeCuentaNegocio;
    private CuentaNegocio cuentaNegocio;
    Cuenta cuenta = new Cuenta();

    public ServletCuentas() {
        clienteNegocio = new ClienteNegocioImpl();
        tipoDeCuentaNegocio = new TipoDeCuentaNegocioImpl();
        cuentaNegocio = new CuentaNegocioImpl();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getParameter("Param") != null) {
            String opcion = request.getParameter("Param").toString();
            
            switch (opcion) {
                case "list2":
                	ArrayList<TipoDeCuenta> listaTiposCuenta = tipoDeCuentaNegocio.listarTiposDeCuenta();
                    if (listaTiposCuenta != null && !listaTiposCuenta.isEmpty()) {
                        request.setAttribute("listaTiposCuenta", listaTiposCuenta);
                    } else {
                        request.setAttribute("mensaje", "No hay tipos de cuenta disponibles.");
                    }

                    ArrayList<Cliente> listaClientes = clienteNegocio.listarClientes();
                    if (listaClientes != null && !listaClientes.isEmpty()) {
                        request.setAttribute("listaClientes", listaClientes);
                    } else {
                        request.setAttribute("mensaje", "No hay clientes disponibles.");
                    }
                    
                    String ultimoCBU = cuentaNegocio.obtenerUltimoCBU();
                    if (ultimoCBU != null && !ultimoCBU.isEmpty()) {
                        long nuevoCBU = Long.parseLong(ultimoCBU) + 1;
                        request.setAttribute("nuevoCBU", nuevoCBU);
                    } else {
                        request.setAttribute("nuevoCBU", 1000016); // Valor inicial si no hay CBU en la BD
                    }
                    
                    RequestDispatcher dispatcher = request.getRequestDispatcher("/AgregarCuenta.jsp");
                    dispatcher.forward(request, response);
                    break;
                case "list":
                    List<Cuenta> listarCuentas = cuentaNegocio.obtenerTodasLasCuentas();
                    if (listarCuentas != null && !listarCuentas.isEmpty()) {
                        request.setAttribute("listarCuentas", listarCuentas);
                    } else {
                        request.setAttribute("mensaje", "No hay cuentas disponibles.");
                    }
                    RequestDispatcher dispatcher1 = request.getRequestDispatcher("/listarCuentasAdmin.jsp");
                    dispatcher1.forward(request, response);
                    break;

                case "delete":
                    if (request.getParameter("NrCuenta") != null) {
                        int id = Integer.parseInt(request.getParameter("NrCuenta"));
                        
                        cuenta.setNumeroCuenta(id);
                        
                        List<Cuenta> list = cuentaNegocio.EliminarCuentas(cuenta);
                        request.setAttribute("listarCuentas", list);
                    }
                    RequestDispatcher dispatcher3 = request.getRequestDispatcher("/listarCuentasAdmin.jsp");
                    dispatcher3.forward(request, response);
                    break;
                    
                case "edit":
                	if (request.getParameter("NrCuenta") != null) {
                		int id = Integer.parseInt(request.getParameter("NrCuenta"));
                		cuenta = cuentaNegocio.obtenerCuentaPorNumero(id);
               		
                		request.setAttribute("cuenta", cuenta);
                		
                		RequestDispatcher dispatcher2 = request.getRequestDispatcher("/ModificarCuentaAdmin.jsp");
                        dispatcher2.forward(request, response);
                    }
                    break;



                default:
                    break;
            }
        }
       
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getParameter("btnAgregar") != null) {
            String clienteIdParam = request.getParameter("clienteId");
            String tipoCuentaIdParam = request.getParameter("tipoCuentaId");
            String fechaCreacionParam = request.getParameter("fechaCreacion");
            String saldoParam = request.getParameter("saldo");

            try {
                int idCliente = Integer.parseInt(clienteIdParam);
                LocalDate fechaCreacion = LocalDate.parse(fechaCreacionParam, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                int tipoCuentaId = Integer.parseInt(tipoCuentaIdParam);
                double saldo = Double.parseDouble(saldoParam);
                Cliente cliente = clienteNegocio.obtenerClientePorId(idCliente);
                TipoDeCuenta tipoDeCuenta = tipoDeCuentaNegocio.obtenerTipoDeCuentaPorId(tipoCuentaId);

                if (cliente == null || tipoDeCuenta == null) {
                    throw new IllegalArgumentException("Cliente o TipoDeCuenta no pueden ser null");
                }
                
                String nuevoCBU = request.getParameter("CBU");
                Cuenta cuenta = new Cuenta(cliente, fechaCreacion, tipoDeCuenta, saldo, nuevoCBU, true);

                // Agregar la cuenta a la base de datos
                boolean exito = cuentaNegocio.agregarCuenta(cuenta);

                if (exito) {
                    request.setAttribute("mensaje", "Cuenta agregada con éxito.");
                } else {
                    request.setAttribute("error",
                            "Error al agregar la cuenta. El cliente ya tiene el máximo de 3 cuentas.");
                }
            } catch (Exception e) {
                e.printStackTrace();
                request.setAttribute("error", "Error al procesar los datos: " + e.getMessage());
            }

            RequestDispatcher dispatcher = request.getRequestDispatcher("/AgregarCuenta.jsp");
            dispatcher.forward(request, response);
        }
        
        if (request.getParameter("BtnModificar") != null) {
            cuenta.setNumeroCuenta(Integer.parseInt(request.getParameter("numeroCuenta")));
            String tipoCuentaIdParam = request.getParameter("tipoCuenta");
            int tipoCuentaId = Integer.parseInt(tipoCuentaIdParam);
            TipoDeCuenta tipoDeCuenta = tipoDeCuentaNegocio.obtenerTipoDeCuentaPorId(tipoCuentaId);
            cuenta.setTipoDeCuenta(tipoDeCuenta);
            cuenta.setSaldo(Float.parseFloat(request.getParameter("saldo")));
            
            List<Cuenta> list = cuentaNegocio.modificarCuentas(cuenta);
            request.setAttribute("listarCuentas", list);
            RequestDispatcher dispatcher4 = request.getRequestDispatcher("/listarCuentasAdmin.jsp");
            dispatcher4.forward(request, response);
        }
    }
}
