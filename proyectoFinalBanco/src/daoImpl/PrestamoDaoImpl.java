package daoImpl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.PrestamoDao;
import entidad.Cuota;
import entidad.Prestamo;

public class PrestamoDaoImpl implements PrestamoDao{
	private Conexion cn;
	@Override
	public boolean insertarNuevoPrestamo(Prestamo objPrestamo) {
		boolean estado = true;
        cn = new Conexion();
        cn.Open();


        try {
 
            
            String insertSql = "INSERT INTO prestamos (NroCuenta_Pr, importeTotalConInteres, importeTotalSolicitado, cuota_mensual, cantidadCuotas) VALUES (?, ?, ?, ?, ?)";
            try (PreparedStatement stmt = cn.getConnection().prepareStatement(insertSql)) {
                stmt.setInt(1, objPrestamo.getNroCuenta_Cu());
                stmt.setFloat(2, objPrestamo.getImporteConIntereses());
                stmt.setFloat(3, objPrestamo.getImportePedido());
                stmt.setFloat(4, objPrestamo.getMontoCuota());
                stmt.setInt(5, objPrestamo.getCantidadCuotas());
                stmt.executeUpdate();
                //pendiente es default true ya que siempre esta pendiente de aprobacion en principio
                //y estado permite null ya que no se sabe si esta aprobado
                //o rechazado hasta que intervenga el adm 
                System.out.println("Se agrego a la base de datos");
            } catch (SQLException e) {
            	System.out.println("NO se agrego a la base de datos");
                e.printStackTrace();
                estado = false;
            }
        } finally {
            cn.close();
        }
        return estado;
	}

	@Override
	public List<Prestamo> obtenerTodos() {
		cn = new Conexion();
	     cn.Open();
	     List<Prestamo> list = new ArrayList<>();
	     ResultSet rs= null;
	     
	     try {
	    	 rs = cn.query("SELECT Id_Prestamo, NroCuenta_Pr, fecha_peticion, importeTotalConInteres, importeTotalSolicitado, cuota_mensual, cantidadCuotas, estadoPrestamo FROM prestamos WHERE estadoPrestamo IS NULL;");
	    	 
	    	 while (rs.next()) {
	                Prestamo prestamo = new Prestamo();
	                prestamo.setIdNumeroDePrestamo(rs.getInt("Id_Prestamo"));
	                prestamo.setNroCuenta_Cu(rs.getInt("NroCuenta_Pr"));
	                prestamo.setFechaActual(rs.getDate("fecha_peticion"));
	                prestamo.setImporteConIntereses(rs.getInt("importeTotalConInteres"));
	                prestamo.setImportePedido(rs.getInt("importeTotalSolicitado"));
	                prestamo.setMontoCuota(rs.getInt("cuota_mensual"));
	                prestamo.setCantidadCuotas(rs.getInt("cantidadCuotas"));
	                prestamo.setEstadoPrestamo(rs.getBoolean("estadoPrestamo"));

	                list.add(prestamo);
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
	public List<Prestamo> obtenerTodosAR() {
		cn = new Conexion();
	     cn.Open();
	     List<Prestamo> list = new ArrayList<>();
	     ResultSet rs= null;
	     
	     try {
	    	 rs = cn.query("SELECT Id_Prestamo, NroCuenta_Pr, fecha_peticion, importeTotalConInteres, importeTotalSolicitado, cuota_mensual, cantidadCuotas, estadoPrestamo FROM prestamos WHERE estadoPrestamo = TRUE OR estadoPrestamo = FALSE;");
	    	 
	    	 while (rs.next()) {
	                Prestamo prestamo = new Prestamo();
	                prestamo.setIdNumeroDePrestamo(rs.getInt("Id_Prestamo"));
	                prestamo.setNroCuenta_Cu(rs.getInt("NroCuenta_Pr"));
	                prestamo.setFechaActual(rs.getDate("fecha_peticion"));
	                prestamo.setImporteConIntereses(rs.getInt("importeTotalConInteres"));
	                prestamo.setImportePedido(rs.getInt("importeTotalSolicitado"));
	                prestamo.setMontoCuota(rs.getInt("cuota_mensual"));
	                prestamo.setCantidadCuotas(rs.getInt("cantidadCuotas"));
	                prestamo.setEstadoPrestamo(rs.getBoolean("estadoPrestamo"));

	                list.add(prestamo);
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
	
	public List<Prestamo> PrestamosPorNrCuenta(int NrCuenta) {
		cn = new Conexion();
	     cn.Open();
	     List<Prestamo> list = new ArrayList<>();
	     ResultSet rs= null;
	     
	     try {
	    	 rs = cn.query("SELECT * FROM prestamos WHERE NroCuenta_Pr = " + NrCuenta +" AND estadoPrestamo = True;");
	    	 
	    	 while (rs.next()) {
	                Prestamo prestamo = new Prestamo();
	                prestamo.setIdNumeroDePrestamo(rs.getInt("Id_Prestamo"));
	                prestamo.setNroCuenta_Cu(rs.getInt("NroCuenta_Pr"));
	                prestamo.setFechaActual(rs.getDate("fecha_peticion"));
	                prestamo.setImporteConIntereses(rs.getInt("importeTotalConInteres"));
	                prestamo.setImportePedido(rs.getInt("importeTotalSolicitado"));
	                prestamo.setMontoCuota(rs.getInt("cuota_mensual"));
	                prestamo.setCantidadCuotas(rs.getInt("cantidadCuotas"));
	                prestamo.setEstadoPrestamo(rs.getBoolean("estadoPrestamo"));
	                prestamo.setEstadoPagado(rs.getBoolean("estadoPagado"));
	                list.add(prestamo);
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
	public boolean Modificar(int idPrestamo, boolean estado) {
		 Conexion cn = new Conexion();
		    cn.Open();
		    PreparedStatement statement = null;
		    try {
		    statement = cn.prepareStatement( "UPDATE prestamos SET estadoPrestamo=? WHERE Id_Prestamo=?"); 
		    	statement.setBoolean(1,estado );
		    	statement.setInt(2, idPrestamo);

		    	if(statement.executeUpdate() > 0) {
					estado = true;
				}else {estado = false;}
			
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
	            cn.close();
	        }
		    return estado;
	}
	
	public Prestamo obtenerPrestamoPorId(int idPrestamo) {
	    Conexion cn = new Conexion();
	    cn.Open();
	    Prestamo prestamo = null;
	    ResultSet rs = null;
	    PreparedStatement stmt = null;

	    try {
	        String query = "SELECT * FROM prestamos WHERE Id_Prestamo = ?";
	        stmt = cn.getConnection().prepareStatement(query);
	        stmt.setInt(1, idPrestamo);
	        rs = stmt.executeQuery();

	        if (rs.next()) {
	            prestamo = new Prestamo();
	            prestamo.setIdNumeroDePrestamo(rs.getInt("Id_Prestamo"));
	            prestamo.setNroCuenta_Cu(rs.getInt("NroCuenta_Pr"));
	            prestamo.setFechaActual(rs.getDate("fecha_peticion"));
	            prestamo.setImporteConIntereses(rs.getFloat("importeTotalConInteres"));
	            prestamo.setImportePedido(rs.getFloat("importeTotalSolicitado"));
	            prestamo.setMontoCuota(rs.getFloat("cuota_mensual"));
	            prestamo.setCantidadCuotas(rs.getInt("cantidadCuotas"));
	            prestamo.setEstadoPrestamo(rs.getBoolean("estadoPrestamo"));
	            // Setear los demás atributos del préstamo según tu estructura de datos
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        // Cerrar conexiones y liberar recursos
	        cn.close();
	        if (rs != null) {
	            try {
	                rs.close();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	        if (stmt != null) {
	            try {
	                stmt.close();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	    }
	    return prestamo;
	}
	
	public List<Cuota> obtenerCuotasPorPrestamo(int idPrestamo) {
	    Conexion cn = new Conexion();
	    cn.Open();
	    List<Cuota> cuotas = new ArrayList<>();
	    ResultSet rs = null;
	    PreparedStatement stmt = null;

	    try {
	        String query = "SELECT * FROM CuotasPrestamo WHERE IdPrestamo = ? AND EstadoPago = False";
	        stmt = cn.getConnection().prepareStatement(query);
	        stmt.setInt(1, idPrestamo);
	        rs = stmt.executeQuery();

	        while (rs.next()) {
	            Cuota cuota = new Cuota();
	            cuota.setIdPrestamo(rs.getInt("IdPrestamo"));
	            cuota.setNumeroCuota(rs.getInt("NumeroCuota"));
	            cuota.setFechaVencimiento(rs.getDate("FechaVencimiento"));
	            cuota.setImporteCuota(rs.getFloat("ImporteCuota"));
	            cuota.setEstadoPago(rs.getBoolean("EstadoPago"));
	            cuotas.add(cuota);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        // Cerrar conexiones y liberar recursos
	        cn.close();
	        if (rs != null) {
	            try {
	                rs.close();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	        if (stmt != null) {
	            try {
	                stmt.close();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	    }
	    return cuotas;
	}
	}


