package dao;

import java.util.List;

import entidad.Cuota;
import entidad.Prestamo;

public interface PrestamoDao {
	public boolean insertarNuevoPrestamo(Prestamo objPrestamo);
	public List<Prestamo> obtenerTodos() ;
	public List<Prestamo> PrestamosPorNrCuenta(int NrCuenta);
	public boolean Modificar(int idPrestamo, boolean estado);
	public Prestamo obtenerPrestamoPorId(int idPrestamo);
	public List<Cuota> obtenerCuotasPorPrestamo(int idPrestamo);
	List<Prestamo> obtenerTodosAR();
}