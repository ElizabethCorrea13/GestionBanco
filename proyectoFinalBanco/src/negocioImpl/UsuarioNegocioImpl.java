package negocioImpl;

import daoImpl.UsuarioDaoImpl;
import entidad.Usuario;
import negocio.UsuarioNegocio;

public class UsuarioNegocioImpl implements UsuarioNegocio {
	UsuarioDaoImpl userDao ;
	@Override
	public boolean ValidarUsuario(Usuario user) {
		userDao = new UsuarioDaoImpl();
		return userDao.validarDatos(user);
	}

}
