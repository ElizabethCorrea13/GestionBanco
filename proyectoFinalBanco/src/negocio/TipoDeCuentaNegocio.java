package negocio;

import java.util.ArrayList;
import entidad.TipoDeCuenta;

public interface TipoDeCuentaNegocio {
    public ArrayList<TipoDeCuenta> listarTiposDeCuenta();
    TipoDeCuenta obtenerTipoDeCuentaPorId(int id);
}
