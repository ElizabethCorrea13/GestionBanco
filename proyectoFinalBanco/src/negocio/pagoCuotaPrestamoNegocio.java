package negocio;

import entidad.Prestamo;
import java.util.List;

public interface pagoCuotaPrestamoNegocio {
	
	public int devolverNumeroDeCuotaPagada(int idPrestamo);
	public boolean pagarCuota(int idPrestamo, int numeroCuota, int numeroCuenta);
	public boolean verificarCuotasPagadas(int idPrestamo);
	public boolean cambiarEstadoPrestamo(int idPrestamo, boolean estado);
}
