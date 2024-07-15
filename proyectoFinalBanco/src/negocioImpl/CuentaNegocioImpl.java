package negocioImpl;

import dao.CuentaDAO;
import daoImpl.CuentaDAOImpl;
import entidad.Cuenta;
import negocio.CuentaNegocio;

import java.util.ArrayList;
import java.util.List;

public class CuentaNegocioImpl implements CuentaNegocio {

    private CuentaDAO cuentaDAO;
    
    
    public CuentaNegocioImpl() {
        cuentaDAO = new CuentaDAOImpl();
    }

    @Override
    public Cuenta obtenerCuentaPorNumero(int numeroCuenta) {
        return cuentaDAO.obtenerCuentaPorNumero(numeroCuenta);
    }

    @Override
    public boolean agregarCuenta(Cuenta cuenta) {
        boolean exito = false;
        try {
        	  if (cuenta.getCliente().get_CantCuentas() >= 3) {
                throw new IllegalStateException("El cliente ya tiene el máximo de 3 cuentas.");
            }

            exito = cuentaDAO.agregarCuenta(cuenta);

            if (exito) {
            	cuentaDAO.actualizarCantidadCuentasCliente(cuenta.getCliente());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return exito;
    }

	@Override
	public ArrayList<Cuenta> listarCuentas() {
		return (ArrayList<Cuenta>) cuentaDAO.listarCuentas();
	}
	
	public ArrayList<Cuenta>obtenerTodasLasCuentas(){
		return (ArrayList<Cuenta>)cuentaDAO.obtenerTodasLasCuentas();
	}
	
	public ArrayList<Cuenta> EliminarCuentas(Cuenta cuenta){
		return (ArrayList<Cuenta>) cuentaDAO.EliminarCuentas(cuenta);
	}
	public Cuenta obtenerUno(int id) {
		return (Cuenta)cuentaDAO.obtenerUno(id);
	}
	public List<Cuenta> modificarCuentas(Cuenta cuenta){
		return (ArrayList<Cuenta>) cuentaDAO.modificarCuentas(cuenta);
	}
	
	@Override
    public String obtenerUltimoCBU() {
        return cuentaDAO.obtenerUltimoCBU();
    }
	
	@Override
	public Cuenta obtenerCuentaPorCliente(int idCliente) {
        return cuentaDAO.obtenerCuentaPorCliente(idCliente);
    }
	
	@Override
    public ArrayList<Cuenta> ListaCuentasPorCliente(int idCliente) {
        return cuentaDAO.ListaCuentasPorCliente(idCliente);
    }
	
	 @Override
    public Cuenta obtenerCuentaPorCBU(String cbu) {
        return cuentaDAO.obtenerCuentaPorCBU(cbu);
    }

    @Override
    public boolean actualizarCuenta(Cuenta cuenta) {
        return cuentaDAO.actualizarCuenta(cuenta);
    }
}
