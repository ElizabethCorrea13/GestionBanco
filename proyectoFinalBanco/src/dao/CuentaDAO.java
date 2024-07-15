package dao;

import java.util.ArrayList;
import java.util.List;

import entidad.Cliente;
import entidad.Cuenta;

public interface CuentaDAO {
    public List<Cuenta> listarCuentas();
    Cuenta obtenerCuentaPorNumero(int numeroCuenta);
    boolean agregarCuenta(Cuenta cuenta);
	boolean actualizarCantidadCuentasCliente(Cliente cliente);
	public List<Cuenta> obtenerTodasLasCuentas();
	public List<Cuenta> EliminarCuentas(Cuenta cuenta);
	public List<Cuenta> modificarCuentas(Cuenta cuenta);
	public Cuenta obtenerUno(int id);
	public String obtenerUltimoCBU();
	public Cuenta obtenerCuentaPorCliente(int idCliente);
	public ArrayList<Cuenta> ListaCuentasPorCliente(int idCliente);
	public Cuenta obtenerCuentaPorCBU(String cbu);
	public boolean actualizarCuenta(Cuenta cuenta);
}
