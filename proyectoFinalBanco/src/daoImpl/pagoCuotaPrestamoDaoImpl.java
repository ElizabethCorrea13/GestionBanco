package daoImpl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.pagoCuotaPrestamo;
import entidad.Prestamo;

public class pagoCuotaPrestamoDaoImpl implements pagoCuotaPrestamo{
	private Conexion cn;

	@Override
	public int devolverNumeroDeCuotaPagada(int idPrestamo) {
	    Conexion cn = new Conexion(); // Aquí asumo que 'Conexion' es tu clase para manejar la conexión a la base de datos
	    cn.Open();

	    ResultSet rs = null;
	    int cuotasPagadas = 0; // Inicialización adecuada de la variable

	    try {
	        String query = "SELECT CuotasPagadas FROM pagoCuotaPrestamo WHERE IdPrestamo_Cu = " + idPrestamo;
	        rs = cn.query(query);

	        if (rs != null && rs.next()) {
	            cuotasPagadas = rs.getInt("CuotasPagadas");
	        } else {
	            System.out.println("No se encontraron resultados para el IdPrestamo: " + idPrestamo);
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

	    return cuotasPagadas;
	}

	@SuppressWarnings("resource")
	@Override
	public boolean pagarCuota(int idPrestamo, int numeroCuota, int numeroCuenta) {
	    Conexion cn = null;
	    PreparedStatement stmt = null;
	    ResultSet rs = null;
	    boolean pagoExitoso = false;

	    try {
	        cn = new Conexion();
	        cn.Open();

	        String obtenerCuotaQuery = "SELECT ImporteCuota FROM CuotasPrestamo WHERE IdPrestamo = ? AND NumeroCuota = ?";
	        stmt = cn.getConnection().prepareStatement(obtenerCuotaQuery);
	        stmt.setInt(1, idPrestamo);
	        stmt.setInt(2, numeroCuota);
	        rs = stmt.executeQuery();

	        if (rs.next()) {
	            double importeCuota = rs.getDouble("ImporteCuota");

	            String obtenerCuentaQuery = "SELECT saldo FROM cuentas WHERE NroCuenta_Cu =" + numeroCuenta +";";
	                                        
	            stmt = cn.getConnection().prepareStatement(obtenerCuentaQuery);
	            ResultSet rsCuenta = stmt.executeQuery();

	            if (rsCuenta.next()) {
	                
	                double saldoCuenta = rsCuenta.getDouble("saldo");

	                if (saldoCuenta >= importeCuota) {
	                    String actualizarSaldoQuery = "UPDATE cuentas SET saldo = saldo - ? WHERE NroCuenta_Cu = "+numeroCuenta+";";
	                    stmt = cn.getConnection().prepareStatement(actualizarSaldoQuery);
	                    stmt.setDouble(1, importeCuota);
	                   
	                    int filasActualizadas = stmt.executeUpdate();

	                    if (filasActualizadas > 0) {
	                        String pagarCuotaQuery = "UPDATE CuotasPrestamo SET EstadoPago = true WHERE IdPrestamo = ? AND NumeroCuota = ?";
	                        stmt = cn.getConnection().prepareStatement(pagarCuotaQuery);
	                        stmt.setInt(1, idPrestamo);
	                        stmt.setInt(2, numeroCuota);
	                        int filasPagadas = stmt.executeUpdate();

	                        if (filasPagadas > 0) {
	                            pagoExitoso = true;
	                        } else {
	                            System.out.println("No se pudo actualizar el estado de la cuota.");
	                        }
	                    } else {
	                        System.out.println("No se pudo actualizar el saldo de la cuenta.");
	                    }
	                } else {
	                    System.out.println("Saldo insuficiente para pagar la cuota.");
	                }
	            } else {
	                System.out.println("No se encontró la cuenta asociada al préstamo.");
	            }
	        } else {
	            System.out.println("No se encontró la cuota especificada.");
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
	        if (stmt != null) {
	            try {
	                stmt.close();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	        if (cn != null) {
	            cn.close();
	        }
	    }

	    return pagoExitoso;
	}


    @Override
    public boolean verificarCuotasPagadas(int idPrestamo) {
        Conexion cn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        boolean todasPagadas = false;

        try {
            cn = new Conexion();
            cn.Open();

            String query = "SELECT COUNT(*) AS TotalCuotas FROM cuotasprestamo WHERE IdPrestamo = ? AND EstadoPago = false";
            stmt = cn.getConnection().prepareStatement(query);
            stmt.setInt(1, idPrestamo);

            rs = stmt.executeQuery();

            if (rs.next()) {
                int totalCuotasPendientes = rs.getInt("TotalCuotas");
                todasPagadas = (totalCuotasPendientes == 0);
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
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (cn != null) {
                cn.close();
            }
        }
        System.out.println(todasPagadas);

        return todasPagadas;
        
    }

    @Override
    public boolean cambiarEstadoPrestamo(int idPrestamo, boolean estado) {
        Conexion cn = null;
        PreparedStatement stmt = null;
        boolean cambioExitoso = false;

        try {
            cn = new Conexion();
            cn.Open();

            String query = "UPDATE Prestamos SET estadoPagado = ? WHERE Id_Prestamo = ?";
            stmt = cn.getConnection().prepareStatement(query);
            stmt.setBoolean(1, estado);
            stmt.setInt(2, idPrestamo);

            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
                cambioExitoso = true;
            } else {
                System.out.println("No se pudo actualizar el estado del préstamo.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (cn != null) {
                cn.close();
            }
        }

        return cambioExitoso;
    }

}