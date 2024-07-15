package negocio;

import java.util.ArrayList;

import entidad.TipoDeMovientos;

public interface TipoDeMovimientoNegocio {
	public ArrayList<TipoDeMovientos> ListarMovimientos();
	TipoDeMovientos obtenerTipoPorId(int id);
}
