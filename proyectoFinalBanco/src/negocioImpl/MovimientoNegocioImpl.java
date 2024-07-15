package negocioImpl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import dao.MovimientoDao;
import daoImpl.MovimientoImpl;
import entidad.Movimiento;
import negocio.MovimientoNegocio;

public class MovimientoNegocioImpl implements MovimientoNegocio{
	
	private MovimientoDao movimientoDao = new MovimientoImpl();
	
	public MovimientoNegocioImpl() {
		movimientoDao = new MovimientoImpl();
	}

	@Override
	public ArrayList<Movimiento> listar() {
		return (ArrayList<Movimiento>) movimientoDao.listar();
	}

	@Override
	public boolean AgregarTransferencia(Movimiento movimiento) {
		return movimientoDao.AgregarTransferencia(movimiento);
	}

	@Override
	public ArrayList<Movimiento> listarReporte(LocalDate fecha1, LocalDate fecha2) {
		return (ArrayList<Movimiento>) movimientoDao.listarReporte(fecha1,fecha2);
	}
	
	@Override
    public boolean registrarMovimiento(Movimiento movimiento) {
        return movimientoDao.registrarMovimiento(movimiento);
    }
	
	public List<Movimiento> movimientosCuenta(int Nrcuenta){
		return movimientoDao.movimientosCuenta(Nrcuenta);
	}

	@Override
	public List<Movimiento> listarTransferencias(int Nrcuenta) {
		return movimientoDao.listarTransferencias(Nrcuenta);
	}
}
