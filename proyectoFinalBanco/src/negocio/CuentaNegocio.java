package negocio;

import entidad.Cuenta;
import java.util.ArrayList;
import java.util.List;
public interface CuentaNegocio {

	public ArrayList<Cuenta> listarCuentas();
    Cuenta obtenerCuentaPorNumero(int numeroCuenta);
    boolean agregarCuenta(Cuenta cuenta);
    public ArrayList<Cuenta> obtenerTodasLasCuentas();
	public ArrayList<Cuenta> EliminarCuentas(Cuenta cuenta);
	public Cuenta obtenerUno(int id);
	public List<Cuenta> modificarCuentas(Cuenta cuenta);
	public String obtenerUltimoCBU();
	public Cuenta obtenerCuentaPorCliente(int idCliente);
	public ArrayList<Cuenta> ListaCuentasPorCliente(int idCliente);
	public Cuenta obtenerCuentaPorCBU(String cbuDestino);
	public boolean actualizarCuenta(Cuenta cuenta);
}
