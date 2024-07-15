package daoImpl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import dao.MovimientoDao;
import entidad.Movimiento;
import entidad.Cuenta;
import entidad.TipoDeMovientos;

public class MovimientoImpl implements MovimientoDao {
    
    private Conexion cn;

    public MovimientoImpl() {}

    @Override
    public ArrayList<Movimiento> listar() {
        cn = new Conexion();
        cn.Open();
        ArrayList<Movimiento> list = new ArrayList<>();
        ResultSet rs = null;

        try {
            rs = cn.query("SELECT * FROM movimientos");
            
            while (rs.next()) {
                Movimiento mov = new Movimiento();
                
                mov.setIdMoviento(rs.getInt("Id_Movimiento"));
                
                Cuenta cuenta = new Cuenta();
                cuenta.setNumeroCuenta(rs.getInt("NroCuenta_Mov"));
                mov.setNroCuenta(cuenta);
                
                mov.setCBU_Origen(rs.getInt("Cbu_Origen"));
                mov.setCBU_Destino(rs.getInt("Cbu_Destino"));
                mov.setFechaDeMovimiento(rs.getDate("fecha_movimiento").toLocalDate());
                mov.setDetalle(rs.getString("Detalle"));
                mov.setImporte(rs.getDouble("Importe"));
                
                TipoDeMovientos tipoMov = new TipoDeMovientos();
                tipoMov.setId(rs.getInt("TipoMovimiento_Mov"));
                mov.setTipoDeMovientos(tipoMov);
                
                list.add(mov);
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
    public boolean AgregarTransferencia(Movimiento movimiento) {
        boolean estado = true;
        cn = new Conexion();
        cn.Open();

        try {
            String insertSql = "INSERT INTO movimientos (NroCuenta_Mov, Cbu_Origen, Cbu_Destino, fecha_movimiento, Detalle, Importe, TipoMovimiento_Mov) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = cn.getConnection().prepareStatement(insertSql);
            stmt.setInt(1, movimiento.getNroCuenta().getNumeroCuenta());
            stmt.setInt(2, movimiento.getCBU_Origen());
            stmt.setInt(3, movimiento.getCBU_Destino());
            stmt.setDate(4, java.sql.Date.valueOf(movimiento.getFechaDeMovimiento()));
            stmt.setString(5, movimiento.getDetalle());
            stmt.setDouble(6, movimiento.getImporte());
            stmt.setInt(7, movimiento.getTipoDeMovientos().getId());
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            estado = false;
        } finally {
            cn.close();
        }
        return estado;
    }

	@Override
	public ArrayList<Movimiento> listarReporte(LocalDate fecha1, LocalDate fecha2) {
        cn = new Conexion();
        cn.Open();
        ArrayList<Movimiento> list = new ArrayList<>();
        ResultSet rs = null;

        try {
            String fechaInicioStr = fecha1.toString();
            String fechaFinStr = fecha2.toString();
            rs = cn.query("SELECT * FROM bdsistemabanco.movimientos INNER JOIN TiposMovimientos ON TipoMovimiento_Tp = TipoMovimiento_Mov WHERE fecha_movimiento>= '" + fechaInicioStr + "' AND fecha_movimiento<= '" + fechaFinStr + "'");
            
            while (rs.next()) {
                Movimiento mov = new Movimiento();
                
                mov.setIdMoviento(rs.getInt("Id_Movimiento"));
                
                Cuenta cuenta = new Cuenta();
                cuenta.setNumeroCuenta(rs.getInt("NroCuenta_Mov"));
                mov.setNroCuenta(cuenta);
                
                mov.setCBU_Origen(rs.getInt("Cbu_Origen"));
                mov.setCBU_Destino(rs.getInt("Cbu_Destino"));
                mov.setFechaDeMovimiento(rs.getDate("fecha_movimiento").toLocalDate());
                mov.setDetalle(rs.getString("Detalle"));
                mov.setImporte(rs.getDouble("Importe"));
                
                TipoDeMovientos tipoMov = new TipoDeMovientos();
                tipoMov.setId(rs.getInt("TipoMovimiento_Mov"));
                tipoMov.setDescripcion(rs.getString("Descripcion_TipoMovimiento"));
                mov.setTipoDeMovientos(tipoMov);
                
                list.add(mov);
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
    public boolean registrarMovimiento(Movimiento movimiento) {
        boolean estado = true;
        cn = new Conexion();
        cn.Open();

        try {
            String insertSql = "INSERT INTO movimientos (NroCuenta_Mov, Cbu_Origen, Cbu_Destino, fecha_movimiento, Detalle, Importe, TipoMovimiento_Mov) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = cn.getConnection().prepareStatement(insertSql);
            stmt.setInt(1, movimiento.getNroCuenta().getNumeroCuenta());
            stmt.setInt(2, movimiento.getCBU_Origen());
            stmt.setInt(3, movimiento.getCBU_Destino());
            stmt.setDate(4, java.sql.Date.valueOf(movimiento.getFechaDeMovimiento()));
            stmt.setString(5, movimiento.getDetalle());
            stmt.setDouble(6, movimiento.getImporte());
            stmt.setInt(7, movimiento.getTipoDeMovientos().getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            estado = false;
        } finally {
            cn.close();
        }
        return estado;
    }
	
	public List<Movimiento> movimientosCuenta(int Nrcuenta) {
		cn = new Conexion();
		cn.Open();
		List<Movimiento> List = new ArrayList<>();
		ResultSet rs = null;
		try {
			
			rs = cn.query("SELECT movimientos.NroCuenta_Mov, movimientos.Cbu_Origen, movimientos.Cbu_Destino, movimientos.fecha_movimiento, movimientos.Detalle, movimientos.Importe, movimientos.TipoMovimiento_Mov "
						+ "FROM bdsistemabanco.movimientos "
						+ "WHERE movimientos.NroCuenta_Mov = "+Nrcuenta+ ";" );
			
			while(rs.next()) {
				Movimiento movimiento = new Movimiento();
				TipoDeMovientos tipoMovimientos = new TipoDeMovientos();
				Cuenta cuenta = new Cuenta();
				
				cuenta.setNumeroCuenta(rs.getInt("NroCuenta_Mov"));
				tipoMovimientos.setId(rs.getInt("TipoMovimiento_Mov"));
				
				movimiento.setNroCuenta(cuenta);
				movimiento.setCBU_Origen(rs.getInt("Cbu_Origen"));
				movimiento.setCBU_Destino(rs.getInt("Cbu_Destino"));
				movimiento.setFechaDeMovimiento(rs.getDate("fecha_movimiento").toLocalDate());
				movimiento.setDetalle(rs.getString("Detalle"));
				movimiento.setImporte(rs.getDouble("Importe"));
				movimiento.setTipoDeMovientos(tipoMovimientos);
				
				List.add(movimiento);
			}
		}catch(Exception e ) {
			e.printStackTrace();
		}finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            cn.close(); // Cierra la conexión
        }
		return List;
	}

	public List<Movimiento> listarTransferencias(int Nrcuenta) {
		cn = new Conexion();
		cn.Open();
		List<Movimiento> List = new ArrayList<>();
		ResultSet rs = null;
		try {
			
			rs = cn.query("SELECT movimientos.NroCuenta_Mov, movimientos.Cbu_Origen, movimientos.Cbu_Destino, movimientos.fecha_movimiento, movimientos.Detalle, movimientos.Importe, movimientos.TipoMovimiento_Mov "
						+ "FROM bdsistemabanco.movimientos "
						+ "WHERE movimientos.TipoMovimiento_Mov = 4 AND movimientos.NroCuenta_Mov = "+Nrcuenta+ ";");
			
			while(rs.next()) {
				Movimiento movimiento = new Movimiento();
				TipoDeMovientos tipoMovimientos = new TipoDeMovientos();
				Cuenta cuenta = new Cuenta();
				
				cuenta.setNumeroCuenta(rs.getInt("NroCuenta_Mov"));
				tipoMovimientos.setId(rs.getInt("TipoMovimiento_Mov"));
				movimiento.setNroCuenta(cuenta);
				movimiento.setCBU_Origen(rs.getInt("Cbu_Origen"));
				movimiento.setCBU_Destino(rs.getInt("Cbu_Destino"));
				movimiento.setFechaDeMovimiento(rs.getDate("fecha_movimiento").toLocalDate());
				movimiento.setDetalle(rs.getString("Detalle"));
				movimiento.setImporte(rs.getDouble("Importe"));
				movimiento.setTipoDeMovientos(tipoMovimientos);
				
				List.add(movimiento);
			}
		}catch(Exception e ) {
			e.printStackTrace();
		}finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            cn.close(); // Cierra la conexión
        }
		return List;
	}
}

