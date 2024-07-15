package negocioImpl;

import java.util.ArrayList;
import java.util.List;

import dao.PrestamoDao;
import daoImpl.PrestamoDaoImpl;
import entidad.Cuota;
import entidad.Prestamo;
import negocio.PrestamoNegocio;

public class PrestamoNegocioImpl implements PrestamoNegocio {
	PrestamoDao presDao = new PrestamoDaoImpl();
	@Override
	public boolean insertarNuevoPrestamo(Prestamo objPrestamo) {
		return presDao.insertarNuevoPrestamo(objPrestamo);
	}

	@Override
	public ArrayList<Prestamo> listarPrestamos() {
		return (ArrayList<Prestamo>) presDao.obtenerTodos();
	}
	
	@Override
	public ArrayList<Prestamo> listarPrestamosAR() {
		return (ArrayList<Prestamo>) presDao.obtenerTodosAR();
	}

	@Override
	public boolean ModificarEstado(int idPrestamo, boolean estado) {
		return presDao.Modificar(idPrestamo,estado);
	}
	public List<Prestamo> PrestamosPorNrCuenta(int NrCuenta){
		return presDao.PrestamosPorNrCuenta(NrCuenta);
	}
	
	@Override
    public Prestamo obtenerPrestamoPorId(int idPrestamo) {
        return presDao.obtenerPrestamoPorId(idPrestamo); 
    }

    @Override
    public List<Cuota> obtenerCuotasPorPrestamo(int idPrestamo) {
        return presDao.obtenerCuotasPorPrestamo(idPrestamo); 
    }
	
}
