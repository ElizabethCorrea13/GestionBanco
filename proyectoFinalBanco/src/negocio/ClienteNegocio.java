package negocio;

import java.util.ArrayList;
import entidad.Cliente;
import entidad.Localidad;
import entidad.Nacionalidad;
import entidad.Provincia;

public interface ClienteNegocio {
	public ArrayList<Cliente> listarClientes();
	public ArrayList<Cliente> listarClientesxNombre();
	public boolean insertar(Cliente cliente);
	public ArrayList<Nacionalidad> listarNacionalidad();
	public ArrayList<Provincia> listarProvincia();
	public ArrayList<Localidad> listarLocalidad();
	public Cliente obtenerClientePorId(int idCliente);
	public boolean borrar(int idClienteEliminar, boolean estado);
	public boolean Modificar(Cliente x);
	public Cliente obtenerClientePorNombreUsuario(String nombreUsuario);
}
