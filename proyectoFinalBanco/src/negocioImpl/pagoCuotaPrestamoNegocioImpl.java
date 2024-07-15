package negocioImpl;

import java.util.List;

import daoImpl.pagoCuotaPrestamoDaoImpl;
import entidad.Prestamo;
import negocio.pagoCuotaPrestamoNegocio;

public class pagoCuotaPrestamoNegocioImpl implements pagoCuotaPrestamoNegocio{
	pagoCuotaPrestamoDaoImpl pagoDAO = new pagoCuotaPrestamoDaoImpl();
	public int devolverNumeroDeCuotaPagada(int idPrestamo) {
		return pagoDAO.devolverNumeroDeCuotaPagada(idPrestamo);
		
	}

	@Override
	public boolean verificarCuotasPagadas(int idPrestamo) {
		return pagoDAO.verificarCuotasPagadas(idPrestamo) ;
	}
	@Override
	public boolean cambiarEstadoPrestamo(int idPrestamo, boolean estado) {
		return pagoDAO.cambiarEstadoPrestamo(idPrestamo, estado);
	}
	@Override
	public boolean pagarCuota(int idPrestamo, int numeroCuota, int numeroCuenta) {
		return pagoDAO.pagarCuota(idPrestamo, numeroCuota,numeroCuenta);
	}


}
