package dao;

import java.util.List;

import entidad.TipoDeMovientos;

public interface TipoDeMovimientoDao {
	List<TipoDeMovientos> listarTiposDeMovimientos();
	TipoDeMovientos obtenerTipoPorId(int id);
}
