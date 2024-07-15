package dao;
import java.util.List;
import entidad.Cliente;
import entidad.Localidad;
import entidad.Nacionalidad;
import entidad.Provincia;

public interface ClienteDao {	
	public List<Cliente> obtenerTodos();
	public List<Cliente> Listarpornombre();
	public boolean insertar(Cliente cliente);
	public List<Nacionalidad> obtenerTodosNacionalidad();
	public List<Provincia> obtenerTodosProvincia();
	public List<Localidad> obtenerTodosLocalidad();
	public Cliente obtenerClientePorId(int idCliente);
	public boolean borrar(int idCliente, boolean estado);
	public boolean Modificar(Cliente x);
	public Cliente obtenerClientePorNombreUsuario(String nombreUsuario);
}