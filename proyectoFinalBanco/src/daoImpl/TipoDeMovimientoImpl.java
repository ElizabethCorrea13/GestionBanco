package daoImpl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.TipoDeMovimientoDao;
import entidad.TipoDeMovientos;

public class TipoDeMovimientoImpl implements TipoDeMovimientoDao {
	
	private Conexion cn;

    public TipoDeMovimientoImpl() {}

	@Override
	public List<TipoDeMovientos> listarTiposDeMovimientos() {
		cn = new Conexion();
        cn.Open();
        List<TipoDeMovientos> list = new ArrayList<>();
        ResultSet rs = null;
        try {
            rs = cn.query("SELECT * FROM tiposmovimientos");

            while (rs.next()) {
            	TipoDeMovientos tipo = new TipoDeMovientos();
                tipo.setId(rs.getInt("TipoMovimiento_Tp"));
                tipo.setDescripcion(rs.getString("Descripcion_TipoMovimiento"));
                list.add(tipo);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            cn.close();
        }
        return list;
	}

	@Override
	public TipoDeMovientos obtenerTipoPorId(int id) {
		cn = new Conexion();
        cn.Open();
        TipoDeMovientos Tipo = null;
        String sql = "SELECT * FROM tiposmovimientos WHERE TipoMovimiento_Tp = ?";
        try (PreparedStatement stmt = cn.getConnection().prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                	Tipo = new TipoDeMovientos();
                	Tipo.setId(rs.getInt("TipoMovimiento_Tp"));
                	Tipo.setDescripcion(rs.getString("Descripcion_TipoMovimiento"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            cn.close();
        }
        return Tipo;
	}

}
