package negocioImpl;

import java.util.ArrayList;

import dao.TipoDeMovimientoDao;
import daoImpl.TipoDeMovimientoImpl;
import entidad.TipoDeMovientos;
import negocio.TipoDeMovimientoNegocio;

public class TipoDeMovimientoNegocioImpl implements TipoDeMovimientoNegocio{
	 	private TipoDeMovimientoDao tipoDeMovimientoDao;

	    public TipoDeMovimientoNegocioImpl(TipoDeMovimientoDao tipoDeMovimientoDao) {
	        this.tipoDeMovimientoDao = tipoDeMovimientoDao;
	    }

	    public TipoDeMovimientoNegocioImpl() {
	        this.tipoDeMovimientoDao = new TipoDeMovimientoImpl(); // Inicialización aquí
	    }

	    public ArrayList<TipoDeMovientos> ListarMovimientos() {
	        return (ArrayList<TipoDeMovientos>) tipoDeMovimientoDao.listarTiposDeMovimientos();
	    }

	    @Override
	    public TipoDeMovientos obtenerTipoPorId(int id) {
	        return tipoDeMovimientoDao.obtenerTipoPorId(id);
	    }
}
