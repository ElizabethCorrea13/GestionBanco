package negocio;

import java.util.ArrayList;
import java.util.List;

import entidad.Cuota;
import entidad.Prestamo;

public interface PrestamoNegocio {
	public boolean insertarNuevoPrestamo(Prestamo objPrestamo);
	 public ArrayList<Prestamo> listarPrestamos();
	 public List<Prestamo> PrestamosPorNrCuenta(int NrCuenta);
	 public boolean ModificarEstado(int idPrestamo, boolean estado);
	 public	Prestamo obtenerPrestamoPorId(int idPrestamo);
	 public	List<Cuota> obtenerCuotasPorPrestamo(int idPrestamo);
	ArrayList<Prestamo> listarPrestamosAR();
}
