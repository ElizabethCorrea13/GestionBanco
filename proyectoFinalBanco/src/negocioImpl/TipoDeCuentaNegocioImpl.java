package negocioImpl;

import java.util.ArrayList;
import dao.TipoDeCuentaDAO;
import daoImpl.TipoDeCuentaDAOImpl;
import entidad.TipoDeCuenta;
import negocio.TipoDeCuentaNegocio;

public class TipoDeCuentaNegocioImpl implements TipoDeCuentaNegocio {

    private TipoDeCuentaDAO tipoDeCuentaDAO;

    public TipoDeCuentaNegocioImpl(TipoDeCuentaDAO tipoDeCuentaDAO) {
        this.tipoDeCuentaDAO = tipoDeCuentaDAO;
    }

    public TipoDeCuentaNegocioImpl() {
        this.tipoDeCuentaDAO = new TipoDeCuentaDAOImpl(); // Inicialización aquí
    }

    @Override
    public ArrayList<TipoDeCuenta> listarTiposDeCuenta() {
        return (ArrayList<TipoDeCuenta>) tipoDeCuentaDAO.listarTiposDeCuenta();
    }

    @Override
    public TipoDeCuenta obtenerTipoDeCuentaPorId(int id) {
        return tipoDeCuentaDAO.obtenerTipoDeCuentaPorId(id);
    }
}

