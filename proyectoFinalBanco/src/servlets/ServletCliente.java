package servlets;

import javax.servlet.RequestDispatcher;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidad.Cliente;
import entidad.Localidad;
import entidad.Nacionalidad;
import entidad.Provincia;
import entidad.Usuario;
import negocio.ClienteNegocio;
import negocioImpl.ClienteNegocioImpl;
import excepciones.CargaClientesException;
import excepciones.CargaDescolgablesException;


@WebServlet("/ServletCliente")
public class ServletCliente extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	ClienteNegocio negCli = new ClienteNegocioImpl();   

    public ServletCliente() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getParameter("Param") != null) {
            String opcion = request.getParameter("Param").toString();
            System.out.print(opcion);
            switch (opcion) {
            	case "info":
            		Usuario nombreUsuario = (Usuario) request.getSession().getAttribute("usuario");
            		Cliente cli = negCli.obtenerClientePorNombreUsuario(nombreUsuario.getNombreUsuario());
            		
            		if (cli != null) {
            			System.out.print(cli);
                        request.setAttribute("cliente", cli);
                        RequestDispatcher dispatcher = request.getRequestDispatcher("/InformacionPersonal.jsp");
                        dispatcher.forward(request, response);
                    }
            		break;
                case "list1":
                    cargarDesplegable(request, response);
                    cargarListaClientes(request, response);
                    RequestDispatcher dispatcher = request.getRequestDispatcher("/AgregarCliente.jsp");
                    dispatcher.forward(request, response);
                    break;
                    
                case "list":
                	cargarListaClientes(request, response);
                    RequestDispatcher dispatcher1 = request.getRequestDispatcher("/ListarModificarEliminarCliente.jsp");
                    dispatcher1.forward(request, response);
                    break;
                case "delete":
                    int idClienteEliminar = Integer.parseInt(request.getParameter("id"));
                    boolean estado = Boolean.parseBoolean(request.getParameter("estado"));
                    
                    boolean eliminacionExitosa = negCli.borrar(idClienteEliminar,estado);

                    if (eliminacionExitosa) {
                        request.setAttribute("mensaje", "Cliente eliminado exitosamente.");
                    } else {
                        request.setAttribute("mensaje", "Error al eliminar el cliente.");
                    }

                    ArrayList<Cliente> listaClientesDelete = negCli.listarClientes();
                    if (listaClientesDelete != null && !listaClientesDelete.isEmpty()) {
                        request.setAttribute("listaCli", listaClientesDelete);
                    } else {
                        request.setAttribute("mensaje", "No hay clientes disponibles.");
                    }

                    RequestDispatcher dispatcherDelete = request.getRequestDispatcher("/ListarModificarEliminarCliente.jsp");
                    dispatcherDelete.forward(request, response);
                    break;
                case "ObtenerUsuario":
                    String idParam = request.getParameter("id");
                    if (idParam != null && !idParam.isEmpty()) {
                        int id = Integer.parseInt(idParam);
                        Cliente cliente = negCli.obtenerClientePorId(id); // Obtener cliente por ID desde la capa de negocio

                        if (cliente != null) {
                            request.setAttribute("cliente", cliente); // Establecer el cliente como atributo de solicitud
                        } else {
                            request.setAttribute("mensaje", "Cliente no encontrado."); // Configurar mensaje si el cliente no se encuentra
                        }
                    } else {
                        request.setAttribute("mensaje", "ID de cliente no proporcionado."); // Configurar mensaje si no se proporciona el ID del cliente
                    }
                    cargarDesplegable(request, response);
                    RequestDispatcher dispatcher11 = request.getRequestDispatcher("/ModificarCliente.jsp");
                    dispatcher11.forward(request, response); // Redirigir la solicitud a la página de ModificarCliente.jsp
                    break;
                	default:
                    break;
            }
        }
    }
    

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		///BOTON MODIFICAR CLIENTE
		if(request.getParameter("BtnModificar")!= null) { 
		      	Cliente x = new Cliente();
		      	x.set_IdCliente(Integer.parseInt(request.getParameter("idCliente")));
		    	x.set_DNI(request.getParameter("txtDni"));
		    	x.set_CUIL(request.getParameter("txtCuil"));
		    	x.set_Nombre(request.getParameter("txtNombre"));
		    	x.set_Apellido(request.getParameter("txtApellido"));
		    	x.set_Sexo(request.getParameter("selSexo"));
		    	Nacionalidad nacionalidad = new Nacionalidad();
                nacionalidad.setId(Integer.parseInt(request.getParameter("txtNacionalidad")));
                x.set_Nacionalidad(nacionalidad);				        
                x.set_Direccion(request.getParameter("txtDireccion"));
                Localidad localidad = new Localidad();
                localidad.setId(Integer.parseInt(request.getParameter("txtLocalidad")));
                x.set_Localidad(localidad);				        
                Provincia provincia = new Provincia();
                provincia.setId(Integer.parseInt(request.getParameter("txtProvincia")));
                x.set_Provincia(provincia);				        
                x.set_Mail(request.getParameter("txtCorreo"));
		        x.set_Teléfono(Integer.parseInt(request.getParameter("txtTelefono")));
		        x.set_Contraseña(request.getParameter("txtContraseña"));
		    	negCli.Modificar(x);
		    	cargarListaClientes(request, response);
                RequestDispatcher dispatcher1 = request.getRequestDispatcher("/ListarModificarEliminarCliente.jsp");
                dispatcher1.forward(request, response);
		    	}
				 
		
		///BOTON AGREGAR CLIENTE
		if(request.getParameter("btnAgregar")!= null) {
			try {
			//LISTA CLIENTES PARA VALIDAR
	        ArrayList<Cliente> listaClientes = negCli.listarClientes();
	        if (listaClientes != null && !listaClientes.isEmpty()) {
	            request.setAttribute("listaCli", listaClientes);
	        } else {
	            request.setAttribute("mensaje", "No hay clientes disponibles.");
	        }
            
            //VALIDAR DNI
            String dniIngresado = request.getParameter("txtDni");
		    boolean dniExiste = false;
		    if (dniIngresado != null && listaClientes != null) {
		        for (Cliente cliente : listaClientes) {
		            if (cliente.get_DNI().equals(dniIngresado)) {
		                dniExiste = true;
		                break; 
		            }
		        }
		    }
		    if(dniExiste) {
                request.setAttribute("mensajeDNI", "El DNI ya se encuentra registrado");
                cargarDesplegable(request, response);
		    	RequestDispatcher dispatcher = request.getRequestDispatcher("/AgregarCliente.jsp");
				dispatcher.forward(request, response);
		    }
		    
		    //VALIDAR USUARIO
            String usuarioIngresado = request.getParameter("txtUsuario");
		    boolean usuarioExiste = false;
		    if (usuarioIngresado != null && listaClientes != null) {
		        for (Cliente cliente : listaClientes) {
		            if (cliente.get_Usuario().equals(usuarioIngresado)) {
		            	usuarioExiste = true;
		                break; 
		            }
		        }
		    }
		    if(usuarioExiste) {
                request.setAttribute("mensajeUsuario", "El nombre de usuario ya se encuentra en uso");
                cargarDesplegable(request, response);  
		    	RequestDispatcher dispatcher = request.getRequestDispatcher("/AgregarCliente.jsp");
				dispatcher.forward(request, response);
		    }
		    	
	    	//AGREGAR CLIENTE
	    	if(dniExiste==false && usuarioExiste==false) {
	      	Cliente x = new Cliente();
	    	x.set_DNI(request.getParameter("txtDni"));
	    	x.set_CUIL(request.getParameter("txtCuil"));
	    	x.set_Nombre(request.getParameter("txtNombre"));
	    	x.set_Apellido(request.getParameter("txtApellido"));
	    	x.set_Sexo(request.getParameter("selSexo"));
            Nacionalidad nacionalidad = new Nacionalidad();
            nacionalidad.setId(Integer.parseInt(request.getParameter("txtNacionalidad")));
            x.set_Nacionalidad(nacionalidad);	    	
	    	x.set_Fecha_de_Nacimiento(LocalDate.parse(request.getParameter("fecha")));
	        x.set_Direccion(request.getParameter("txtDireccion"));	        
	        Localidad localidad = new Localidad();
            localidad.setId(Integer.parseInt(request.getParameter("txtLocalidad")));
            x.set_Localidad(localidad);           
            Provincia provincia = new Provincia();
            provincia.setId(Integer.parseInt(request.getParameter("txtProvincia")));
            x.set_Provincia(provincia);	        
	        x.set_Mail(request.getParameter("txtCorreo"));
	        x.set_Teléfono(Integer.parseInt(request.getParameter("txtTelefono")));
	        x.set_Usuario(request.getParameter("txtUsuario"));
	        x.set_Contraseña(request.getParameter("txtContraseña"));
	        x.set_CantCuentas(0);
	        x.set_Estado(true);
	    	boolean estado=true;
	    	estado = negCli.insertar(x);
	    	request.setAttribute("estadoCliente", estado);
	    	}
			}catch (Exception e){
				e.printStackTrace();
                request.setAttribute("error", "Error al procesar los datos: " + e.getMessage());
			}
		    	cargarDesplegable(request, response);
		    	RequestDispatcher dispatcher = request.getRequestDispatcher("/AgregarCliente.jsp");
				dispatcher.forward(request, response);
		} /// FIN BOTON AGREGAR CLIENTE
	}/// FIN DO POST
	
	//CARGAR DESPLEGABLES
	void cargarDesplegable(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, CargaDescolgablesException {
        ArrayList<Nacionalidad> listaNacionalidad = negCli.listarNacionalidad();
        if (listaNacionalidad != null && !listaNacionalidad.isEmpty()) {
            request.setAttribute("listaNac", listaNacionalidad);
        } else {
            CargaDescolgablesException exc1 = new CargaDescolgablesException();
            throw exc1;
        }
        
        ArrayList<Provincia> listaProvincia = negCli.listarProvincia();
        if (listaProvincia != null && !listaProvincia.isEmpty()) {
            request.setAttribute("listaPro", listaProvincia);
        } else {
            CargaDescolgablesException exc2 = new CargaDescolgablesException();
            throw exc2;
        }
        
        ArrayList<Localidad> listaLocalidad = negCli.listarLocalidad();
        if (listaLocalidad != null && !listaLocalidad.isEmpty()) {
            request.setAttribute("listaLoc", listaLocalidad);
        } else {
            CargaDescolgablesException exc3 = new CargaDescolgablesException();
            throw exc3;
        } 
	}
	
	//CARGAR LISTA CLIENTES
	void cargarListaClientes(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, CargaClientesException {
        ArrayList<Cliente> listaClientes = negCli.listarClientes();
        if (listaClientes != null && !listaClientes.isEmpty()) {
            request.setAttribute("listaCli", listaClientes);
        } else {
            CargaClientesException exc1 = new CargaClientesException();
            throw exc1;
        }
	}
	
	
	
	
}

