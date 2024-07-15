package daoImpl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.TipoDeCuentaDAO;
import entidad.TipoDeCuenta;

public class TipoDeCuentaDAOImpl implements TipoDeCuentaDAO {

    private Conexion cn;

    public TipoDeCuentaDAOImpl() {}

    @Override
    public List<TipoDeCuenta> listarTiposDeCuenta() {
        cn = new Conexion();
        cn.Open();
        List<TipoDeCuenta> list = new ArrayList<>();
        ResultSet rs = null;

        try {
            rs = cn.query("SELECT * FROM tipocuentas");

            while (rs.next()) {
                TipoDeCuenta tipo = new TipoDeCuenta();
                tipo.setId(rs.getInt("Id_TipoCuentas_TP"));
                tipo.setNombreDeCuenta(rs.getString("Descripcion_TipoCuenta"));
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
    public TipoDeCuenta obtenerTipoDeCuentaPorId(int id) {
        cn = new Conexion();
        cn.Open();
        TipoDeCuenta tipoDeCuenta = null;
        String sql = "SELECT Id_TipoCuentas_TP, Descripcion_TipoCuenta FROM tipocuentas WHERE Id_TipoCuentas_TP = ?";

        try (PreparedStatement stmt = cn.getConnection().prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    tipoDeCuenta = new TipoDeCuenta();
                    tipoDeCuenta.setId(rs.getInt("Id_TipoCuentas_TP"));
                    tipoDeCuenta.setNombreDeCuenta(rs.getString("Descripcion_TipoCuenta"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            cn.close();
        }
        return tipoDeCuenta;
    }
}