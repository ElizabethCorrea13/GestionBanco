package dao;

import java.util.List;
import entidad.TipoDeCuenta;

public interface TipoDeCuentaDAO {
    List<TipoDeCuenta> listarTiposDeCuenta();
    TipoDeCuenta obtenerTipoDeCuentaPorId(int id);
}
