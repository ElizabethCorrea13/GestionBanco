package negocioImpl;

import java.util.ArrayList;

import negocio.ClienteNegocio;
import dao.ClienteDao;
import daoImpl.ClienteDaoImpl;
import entidad.Cliente;
import entidad.Localidad;
import entidad.Nacionalidad;
import entidad.Provincia;

public class ClienteNegocioImpl implements ClienteNegocio{
	private ClienteDao cliDao = new ClienteDaoImpl();
	
	//Se puede recibir por constructor
	public ClienteNegocioImpl(ClienteDao cliDao)
	{
		this.cliDao = cliDao;
	}
	
	public ClienteNegocioImpl()
	{
	}

	@Override
	public boolean insertar(Cliente cliente) {
		return cliDao.insertar(cliente);
	}
	
	@Override
	public ArrayList<Cliente> listarClientes() {
		return (ArrayList<Cliente>) cliDao.obtenerTodos();
	}

	@Override
	public ArrayList<Nacionalidad> listarNacionalidad() {
		return (ArrayList<Nacionalidad>) cliDao.obtenerTodosNacionalidad();
	}

	@Override
	public ArrayList<Provincia> listarProvincia() {
		return (ArrayList<Provincia>) cliDao.obtenerTodosProvincia();
	}

	@Override
	public ArrayList<Localidad> listarLocalidad() {
		return (ArrayList<Localidad>) cliDao.obtenerTodosLocalidad();
	}
	
	@Override
    public Cliente obtenerClientePorId(int idCliente) {
        return cliDao.obtenerClientePorId(idCliente);
    }

	@Override
	public ArrayList<Cliente> listarClientesxNombre() {
		return (ArrayList<Cliente>) cliDao.Listarpornombre();
	}

	@Override
	public boolean borrar(int idClienteEliminar, boolean estado) {
		return cliDao.borrar(idClienteEliminar,estado);
	}

	@Override
	public boolean Modificar(Cliente x) {
		
		return cliDao.Modificar(x);
	}
	
	public Cliente obtenerClientePorNombreUsuario(String nombreUsuario) {
        return cliDao.obtenerClientePorNombreUsuario(nombreUsuario);
    }
	
}


