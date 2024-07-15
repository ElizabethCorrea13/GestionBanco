package daoImpl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.CuentaDAO;
import entidad.Cliente;
import entidad.Cuenta;
import entidad.TipoDeCuenta;

public class CuentaDAOImpl implements CuentaDAO {

    private Conexion cn;

    public CuentaDAOImpl() {
        // Constructor vacío
    }

    @Override
    public List<Cuenta> listarCuentas() {
        cn = new Conexion();
        cn.Open();
        List<Cuenta> cuentas = new ArrayList<>();
        ResultSet rs = null;
        
        String sql = "SELECT c.NroCuenta_Cu, c.IdCliente_Cu, c.ID_TIPOCUENTA_Cu, c.fecha_creacion, c.CBU, c.saldo, c.estado, cl.idCliente, cl.nombre, cl.apellido " +
                     "FROM cuentas c " +
                     "JOIN clientes cl ON c.IdCliente_Cu = cl.idCliente";
        try {
            rs = cn.query(sql);
            while (rs.next()) {
                Cuenta cuenta = new Cuenta();
                cuenta.setNumeroCuenta(rs.getInt("NroCuenta_Cu"));

                Cliente cliente = new Cliente();
                cliente.set_IdCliente(rs.getInt("IdCliente_Cu"));
                cuenta.setCliente(cliente);

                TipoDeCuenta tipoDeCuenta = new TipoDeCuenta();
                tipoDeCuenta.setId(rs.getInt("ID_TIPOCUENTA_Cu"));
                cuenta.setTipoDeCuenta(tipoDeCuenta);

                cuenta.setFechaCreacion(rs.getDate("fecha_creacion").toLocalDate());
                cuenta.setCBU(rs.getString("CBU"));
                cuenta.setSaldo(rs.getDouble("saldo"));
                cuenta.setEstado(rs.getBoolean("estado"));

                cuentas.add(cuenta);
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
        return cuentas;
    }

    @Override
    public Cuenta obtenerCuentaPorNumero(int numeroCuenta) {
        Cuenta cuenta = null;
        String sql = "SELECT * FROM cuentas WHERE NroCuenta_Cu = ?";
        try {
            cn = new Conexion();
            cn.Open();
            try (PreparedStatement stmt = cn.getConnection().prepareStatement(sql)) {
                stmt.setInt(1, numeroCuenta);
                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        cuenta = new Cuenta();
                        cuenta.setNumeroCuenta(rs.getInt("NroCuenta_Cu"));
                        Cliente cliente = new Cliente();
                        cliente.set_IdCliente(rs.getInt("IdCliente_Cu"));
                        cuenta.setCliente(cliente);
                        TipoDeCuenta tipoDeCuenta = new TipoDeCuenta();
                        tipoDeCuenta.setId(rs.getInt("ID_TIPOCUENTA_Cu"));
                        cuenta.setTipoDeCuenta(tipoDeCuenta);
                        cuenta.setFechaCreacion(rs.getDate("fecha_creacion").toLocalDate());
                        cuenta.setCBU(rs.getString("CBU"));
                        cuenta.setSaldo(rs.getDouble("saldo"));
                        cuenta.setEstado(rs.getBoolean("estado"));
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            cn.close(); 
        }
        return cuenta;
    }

    @Override
    public boolean agregarCuenta(Cuenta cuenta) {
        boolean estado = true;
        cn = new Conexion();
        cn.Open();

        if (cuenta == null || cuenta.getCliente() == null) {
            throw new IllegalArgumentException("Cuenta o Cliente no pueden ser null");
        }

        try {
        	// Verificar cantidad de cuentas del cliente
            if (cuenta.getCliente().get_CantCuentas() >= 3) {
                throw new IllegalStateException("El cliente ya tiene el máximo de 3 cuentas.");
            }

            // Insertar nueva cuenta
            String insertSql = "INSERT INTO cuentas (IdCliente_Cu, ID_TIPOCUENTA_Cu, fecha_creacion, CBU, saldo, estado) VALUES (?, ?, ?, ?, ?, ?)";
            try (PreparedStatement stmt = cn.getConnection().prepareStatement(insertSql)) {
                stmt.setInt(1, cuenta.getCliente().get_IdCliente());
                stmt.setInt(2, cuenta.getTipoDeCuenta().getId());
                stmt.setObject(3, cuenta.getFechaCreacion());
                stmt.setString(4, cuenta.getCBU());
                stmt.setDouble(5, cuenta.getSaldo());
                stmt.setBoolean(6, cuenta.isEstado());
                stmt.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
                estado = false;
            }
        } finally {
            cn.close();
        }
        return estado;
    }
    
    @Override
    public boolean actualizarCantidadCuentasCliente(Cliente cliente) {
        boolean exito = false;
        cn = new Conexion();
        cn.Open();

        String sql = "UPDATE clientes SET cantidadCuentas = cantidadCuentas + 1 WHERE idCliente = ?";
        try (PreparedStatement stmt = cn.getConnection().prepareStatement(sql)) {
            stmt.setInt(1, cliente.get_IdCliente());
            int rowsUpdated = stmt.executeUpdate();
            exito = rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            cn.close();
        }
        return exito;
    }
    
    public List<Cuenta> obtenerTodasLasCuentas() {
		cn = new Conexion();
		cn.Open();
		List<Cuenta> List = new ArrayList<>();
		ResultSet rs = null;
		try {
			
			rs = cn.query("SELECT * FROM cuentas WHERE estado !="+false);
			while(rs.next()) {
				Cuenta obj = new Cuenta();
				
				obj.setNumeroCuenta(rs.getInt("NroCuenta_Cu"));
				Cliente cliente = new Cliente();
                cliente.set_IdCliente(rs.getInt("IdCliente_Cu"));
                obj.setCliente(cliente);

                TipoDeCuenta tipoDeCuenta = new TipoDeCuenta();
                tipoDeCuenta.setId(rs.getInt("ID_TIPOCUENTA_Cu"));
                obj.setTipoDeCuenta(tipoDeCuenta);
                
				obj.setFechaCreacion(rs.getDate("fecha_creacion").toLocalDate());
				obj.setCBU(rs.getString("CBU"));
				obj.setSaldo(rs.getFloat("saldo"));
				obj.setEstado(rs.getBoolean("estado"));
				
				List.add(obj);
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
	
	public List<Cuenta> EliminarCuentas(Cuenta cuenta){
		cn = new Conexion();
		cn.Open();
		List<Cuenta> List = new ArrayList<>();
		PreparedStatement statement = null;
		//boolean execute;
		try {
			statement = cn.prepareStatement("update bdsistemabanco.cuentas " + 
					"set estado = ?"+
					" where NroCuenta_Cu = ?");
			statement.setBoolean(1, cuenta.isEstado());
			statement.setInt(2, cuenta.getNumeroCuenta());
			/*execute = cn.execute("DELETE FROM movimientos WHERE NroCuenta_Mov IN (SELECT NroCuenta_Cu FROM cuentas WHERE NroCuenta_Cu ="+id+")");
			execute = cn.execute("DELETE FROM prestamos WHERE NroCuenta_Pr IN (SELECT NroCuenta_Cu FROM cuentas WHERE NroCuenta_Cu ="+id+")");
			execute = cn.execute("DELETE FROM cuentas WHERE NroCuenta_Cu ="+id);*/
			if(statement.executeUpdate() > 0){
				//cn.getConnection().commit();
				List = this.obtenerTodasLasCuentas();
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
            cn.close(); // Cierra la conexión
        }
		return List;
	}
	
	public List<Cuenta> modificarCuentas(Cuenta cuenta){
		cn = new Conexion();
		cn.Open();
		List<Cuenta> List = new ArrayList<>();
		PreparedStatement statement = null;
		try {
			statement = cn.prepareStatement("update bdsistemabanco.cuentas " + 
					"set ID_TIPOCUENTA_Cu = ?, "+
					" saldo = ? "+
					" where NroCuenta_Cu = ?");
			statement.setInt(1, cuenta.getTipoDeCuenta().getId());
			statement.setDouble(2, cuenta.getSaldo());
			statement.setInt(3, cuenta.getNumeroCuenta());
			
			if(statement.executeUpdate() > 0) {
				List = this.obtenerTodasLasCuentas();
			}
		
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
            cn.close(); // Cierra la conexión
        }
		return List;
	}
	
	public Cuenta obtenerUno(int id) {
	    cn = new Conexion();
	    cn.Open();
	    Cuenta cuenta = new Cuenta();
	    ResultSet rs = null;

	    try {
	       
	        rs = cn.query("Select cuentas.NroCuenta_Cu, cuentas.IdCliente_Cu, cuentas.ID_TIPOCUENTA_Cu,cuentas.fecha_creacion,cuentas.CBU, cuentas.saldo"+
	        		" From bdsistemabanco.cuentas where  NroCuenta_Cu = "+id+";");

	        if (rs.next()) {
	        	Cuenta obj = new Cuenta();
				
				obj.setNumeroCuenta(rs.getInt("NroCuenta_Cu"));
				Cliente cliente = new Cliente();
                cliente.set_IdCliente(rs.getInt("IdCliente_Cu"));
                obj.setCliente(cliente);

                TipoDeCuenta tipoDeCuenta = new TipoDeCuenta();
                tipoDeCuenta.setId(rs.getInt("ID_TIPOCUENTA_Cu"));
                obj.setTipoDeCuenta(tipoDeCuenta);
                
				obj.setFechaCreacion(rs.getDate("fecha_creacion").toLocalDate());
				obj.setCBU(rs.getString("CBU"));
	        	cuenta.setSaldo(rs.getFloat("saldo"));
	        }

	    } catch (SQLException e) {
	        e.printStackTrace(); // Manejo básico de la excepción, considera mejorar el manejo según tus necesidades
	    } finally {
	        if (rs != null) {
	            try {
	                rs.close();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	        cn.close(); // Cierra la conexión
	    }

	    return cuenta;
	}
	
	@Override
	public String obtenerUltimoCBU() {
		cn = new Conexion();
	    cn.Open();
	    String ultimoCBU = "";
	    String query = "SELECT CBU FROM cuentas ORDER BY CBU DESC LIMIT 1";
	    try (PreparedStatement ps = cn.prepareStatement(query)) {
	        ResultSet rs = ps.executeQuery();
	        if (rs.next()) {
	            ultimoCBU = rs.getString("CBU");
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	    	cn.close();
	    }
	    return ultimoCBU;
	}
	
	@Override
    public Cuenta obtenerCuentaPorCliente(int idCliente) {
        Cuenta cuenta = null;
        cn = new Conexion();
	    cn.Open();
        String query = "SELECT * FROM cuentas WHERE IdCliente_Cu = ?";

        try (PreparedStatement stmt = cn.prepareStatement(query)) {
            stmt.setInt(1, idCliente);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    cuenta = new Cuenta();
                    cuenta.setNumeroCuenta(rs.getInt("NroCuenta_Cu"));
                    Cliente cliente = new Cliente();
                    cliente.set_IdCliente(rs.getInt("IdCliente_Cu"));
                    cuenta.setCliente(cliente);
                    TipoDeCuenta tipoDeCuenta = new TipoDeCuenta();
                    tipoDeCuenta.setId(rs.getInt("ID_TIPOCUENTA_Cu"));
                    cuenta.setTipoDeCuenta(tipoDeCuenta);
                    cuenta.setCBU(rs.getString("CBU"));
                    cuenta.setFechaCreacion(rs.getDate("fecha_creacion").toLocalDate());
                    cuenta.setSaldo(rs.getDouble("saldo"));
                    cuenta.setEstado(rs.getBoolean("estado"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            cn.close(); // Cierra la conexión
        }
        return cuenta;
    }
	
	public ArrayList<Cuenta> ListaCuentasPorCliente(int idCliente) {
        ArrayList<Cuenta> cuentas = new ArrayList<>();
        cn = new Conexion();
	    cn.Open();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = cn.prepareStatement("SELECT * FROM cuentas WHERE IdCliente_Cu = ?");
            ps.setInt(1, idCliente);
            rs = ps.executeQuery();

            while (rs.next()) {
                Cuenta cuenta = new Cuenta();
                cuenta.setNumeroCuenta(rs.getInt("NroCuenta_Cu"));
                Cliente cliente = new Cliente();
                cliente.set_IdCliente(rs.getInt("IdCliente_Cu"));
                cuenta.setCliente(cliente);
                TipoDeCuenta tipoDeCuenta = new TipoDeCuenta();
                tipoDeCuenta.setId(rs.getInt("ID_TIPOCUENTA_Cu"));
                cuenta.setTipoDeCuenta(tipoDeCuenta);
                cuenta.setCBU(rs.getString("CBU"));
                cuenta.setFechaCreacion(rs.getDate("fecha_creacion").toLocalDate());
                cuenta.setSaldo(rs.getDouble("saldo"));
                cuenta.setEstado(rs.getBoolean("estado"));
                cuentas.add(cuenta);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
        	 cn.close(); 
        }

        return cuentas;
    }
	
	@Override
    public Cuenta obtenerCuentaPorCBU(String cbu) {
        Cuenta cuenta = null;
        cn = new Conexion();
        cn.Open();
        String sql = "SELECT * FROM cuentas WHERE CBU = ?";

        try (PreparedStatement stmt = cn.getConnection().prepareStatement(sql)) {
            stmt.setString(1, cbu);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    cuenta = new Cuenta();
                    cuenta.setNumeroCuenta(rs.getInt("NroCuenta_Cu"));
                    Cliente cliente = new Cliente();
                    cliente.set_IdCliente(rs.getInt("IdCliente_Cu"));
                    cuenta.setCliente(cliente);
                    TipoDeCuenta tipoDeCuenta = new TipoDeCuenta();
                    tipoDeCuenta.setId(rs.getInt("ID_TIPOCUENTA_Cu"));
                    cuenta.setTipoDeCuenta(tipoDeCuenta);
                    cuenta.setFechaCreacion(rs.getDate("fecha_creacion").toLocalDate());
                    cuenta.setCBU(rs.getString("CBU"));
                    cuenta.setSaldo(rs.getDouble("saldo"));
                    cuenta.setEstado(rs.getBoolean("estado"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            cn.close();
        }
        return cuenta;
    }

    @Override
    public boolean actualizarCuenta(Cuenta cuenta) {
        boolean estado = true;
        cn = new Conexion();
        cn.Open();

        try {
            String updateSql = "UPDATE cuentas SET IdCliente_Cu = ?, ID_TIPOCUENTA_Cu = ?, fecha_creacion = ?, CBU = ?, saldo = ?, estado = ? WHERE NroCuenta_Cu = ?";
            PreparedStatement stmt = cn.getConnection().prepareStatement(updateSql);
            stmt.setInt(1, cuenta.getCliente().get_IdCliente());
            stmt.setInt(2, cuenta.getTipoDeCuenta().getId());
            stmt.setDate(3, java.sql.Date.valueOf(cuenta.getFechaCreacion()));
            stmt.setString(4, cuenta.getCBU());
            stmt.setDouble(5, cuenta.getSaldo());
            stmt.setBoolean(6, cuenta.isEstado());
            stmt.setInt(7, cuenta.getNumeroCuenta());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            estado = false;
        } finally {
            cn.close();
        }
        return estado;
    }


}
