package dao;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import entidad.Movimiento;

public interface MovimientoDao {
	public List<Movimiento> listar();
	boolean AgregarTransferencia(Movimiento movimiento);
	public ArrayList<Movimiento> listarReporte(LocalDate fecha1 , LocalDate fecha2);
	public boolean registrarMovimiento(Movimiento movimiento);
	public List<Movimiento> movimientosCuenta(int Nrcuenta);
	public List<Movimiento> listarTransferencias(int Nrcuenta);

}
