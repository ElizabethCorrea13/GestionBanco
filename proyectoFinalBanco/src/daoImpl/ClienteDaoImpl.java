package daoImpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.PreparedStatement;

import dao.ClienteDao;
import entidad.Cliente;
import entidad.Localidad;
import entidad.Nacionalidad;
import entidad.Provincia;

public class ClienteDaoImpl implements ClienteDao {

    private Conexion cn;

    public ClienteDaoImpl() {
    }

    @Override
    public List<Cliente> obtenerTodos() {
        cn = new Conexion();
        cn.Open();
        List<Cliente> list = new ArrayList<>();
        ResultSet rs = null;
        
        try {
            rs = cn.query("SELECT c.idCliente,c.dniCliente,c.cuil,c.nombre,c.apellido,c.sexo,c.nacionalidad,n.nombre,c.fechaNacimiento,c.direccion,c.localidad,l.localidad,c.provincia,p.provincia,c.mail,c.telefono,c.nombreUsuario_Cli,c.contraseña,c.cantidadCuentas,c.estado FROM clientes c INNER JOIN nacionalidades n ON c.nacionalidad = n.id INNER JOIN localidades l ON c.localidad = l.id INNER JOIN provincias p ON c.provincia = p.id;"); 
            
            while (rs.next()) {
                Cliente cli = new Cliente();
                
                cli.set_IdCliente(rs.getInt("idCliente"));
                cli.set_DNI(rs.getString("dniCliente"));
                cli.set_CUIL(rs.getString("cuil"));
                cli.set_Nombre(rs.getString("nombre"));
                cli.set_Apellido(rs.getString("apellido"));
                cli.set_Sexo(rs.getString("sexo"));
                
                Nacionalidad nacionalidad = new Nacionalidad();
                nacionalidad.setId(rs.getInt("c.nacionalidad"));
                nacionalidad.setNombre(rs.getString("n.nombre"));
                cli.set_Nacionalidad(nacionalidad);
                
                cli.set_Fecha_de_Nacimiento(rs.getDate("fechaNacimiento").toLocalDate());
                cli.set_Direccion(rs.getString("direccion"));
                
                Localidad localidad = new Localidad();
                localidad.setId(rs.getInt("c.localidad"));
                localidad.setNombre(rs.getString("l.localidad"));
                cli.set_Localidad(localidad);
                
                Provincia provincia = new Provincia();
                provincia.setId(rs.getInt("c.provincia"));
                provincia.setNombre(rs.getString("p.provincia"));
                cli.set_Provincia(provincia);
                
                cli.set_Mail(rs.getString("mail"));
                cli.set_Teléfono(rs.getInt("telefono"));
                cli.set_Usuario(rs.getString("nombreUsuario_Cli"));
                cli.set_Contraseña(rs.getString("contraseña"));
                cli.set_CantCuentas(rs.getInt("cantidadCuentas"));
                cli.set_Estado(rs.getBoolean("estado"));

                list.add(cli);
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
    public boolean insertar(Cliente cliente) {
        boolean estado = true;
        cn = new Conexion();
        cn.Open();

        try {         
            String insertSql = "INSERT INTO clientes (dniCliente, cuil, nombre, apellido, sexo, nacionalidad, fechaNacimiento, direccion, localidad, provincia, mail, telefono, nombreUsuario_Cli, contraseña, cantidadCuentas, estado) VALUES (?, ?, ?, ?, ?, ?,  ?, ?, ?,  ?, ?, ?,  ?, ?, ?, ? )";
             PreparedStatement stmt = (PreparedStatement) cn.getConnection().prepareStatement(insertSql);
                stmt.setString(1, cliente.get_DNI());
                stmt.setString(2, cliente.get_CUIL());
                stmt.setString(3, cliente.get_Nombre());
                stmt.setString(4, cliente.get_Apellido());
                stmt.setString(5, cliente.get_Sexo());
                stmt.setInt(6, cliente.get_Nacionalidad().getId());
                stmt.setObject(7, cliente.get_Fecha_de_Nacimiento());
                stmt.setString(8, cliente.get_Direccion());
                stmt.setInt(9, cliente.get_Localidad().getId());
                stmt.setInt(10, cliente.get_Provincia().getId());
                stmt.setString(11, cliente.get_Mail());
                stmt.setInt(12, cliente.get_Teléfono());
                stmt.setString(13, cliente.get_Usuario());
                stmt.setString(14, cliente.get_Contraseña());
                stmt.setInt(15, cliente.get_CantCuentas());
                stmt.setBoolean(16, cliente.get_Estado());
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
	public List<Nacionalidad> obtenerTodosNacionalidad() {
        cn = new Conexion();
        cn.Open();
        List<Nacionalidad> list = new ArrayList<>();
        ResultSet rs = null;
        
        try {
            rs = cn.query("SELECT * FROM nacionalidades"); 
            
            while (rs.next()) {
                Nacionalidad nac = new Nacionalidad();
                nac.setId(rs.getInt("id"));
                nac.setNombre(rs.getString("nombre"));

                list.add(nac);
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
	public List<Provincia> obtenerTodosProvincia() {
        cn = new Conexion();
        cn.Open();
        List<Provincia> list = new ArrayList<>();
        ResultSet rs = null;
        
        try {
            rs = cn.query("SELECT * FROM provincias"); 
            
            while (rs.next()) {
                Provincia pro = new Provincia();
                pro.setId(rs.getInt("id"));
                pro.setNombre(rs.getString("provincia"));

                list.add(pro);
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
	public List<Localidad> obtenerTodosLocalidad() {
        cn = new Conexion();
        cn.Open();
        List<Localidad> list = new ArrayList<>();
        ResultSet rs = null;
        
        try {
            rs = cn.query("SELECT * FROM localidades"); 
            
            while (rs.next()) {
                Localidad loc = new Localidad();
                loc.setId(rs.getInt("id"));
                loc.setIdProvincia(rs.getInt("id_privincia"));
                loc.setNombre(rs.getString("localidad"));

                list.add(loc);
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
	public Cliente obtenerClientePorId(int idCliente) {
	    Cliente cliente = null;
	    String sql = "SELECT c.*, n.nombre as nombreNacionalidad, l.localidad as nombreLocalidad, p.provincia as nombreProvincia " +
	                 "FROM clientes c " +
	                 "JOIN nacionalidades n ON c.nacionalidad = n.id " +
	                 "JOIN localidades l ON c.localidad = l.id " +
	                 "JOIN provincias p ON c.provincia = p.id " +
	                 "WHERE c.idCliente = ?";
	    cn = new Conexion();
	    cn.Open();  
	    try (PreparedStatement stmt = (PreparedStatement) cn.prepareStatement(sql)) {
	        stmt.setInt(1, idCliente);
	        try (ResultSet rs = stmt.executeQuery()) {
	            if (rs.next()) {
	                cliente = new Cliente();
	                cliente.set_IdCliente(rs.getInt("idCliente"));
	                cliente.set_DNI(rs.getString("dniCliente"));
	                cliente.set_CUIL(rs.getString("cuil"));
	                cliente.set_Nombre(rs.getString("nombre"));
	                cliente.set_Apellido(rs.getString("apellido"));
	                cliente.set_Sexo(rs.getString("sexo"));
	                
	                Nacionalidad nacionalidad = new Nacionalidad();
	                nacionalidad.setId(rs.getInt("nacionalidad"));
	                nacionalidad.setNombre(rs.getString("nombreNacionalidad"));
	                cliente.set_Nacionalidad(nacionalidad);
	                
	                cliente.set_Fecha_de_Nacimiento(rs.getDate("fechaNacimiento").toLocalDate());
	                cliente.set_Direccion(rs.getString("direccion"));
	                
	                Localidad localidad = new Localidad();
	                localidad.setId(rs.getInt("localidad"));
	                localidad.setNombre(rs.getString("nombreLocalidad"));
	                cliente.set_Localidad(localidad);
	                
	                Provincia provincia = new Provincia();
	                provincia.setId(rs.getInt("provincia"));
	                provincia.setNombre(rs.getString("nombreProvincia"));
	                cliente.set_Provincia(provincia);
	                
	                cliente.set_Mail(rs.getString("mail"));
	                cliente.set_Teléfono(rs.getInt("telefono"));
	                cliente.set_Usuario(rs.getString("nombreUsuario_Cli"));
	                cliente.set_Contraseña(rs.getString("contraseña"));
	                cliente.set_CantCuentas(rs.getInt("cantidadCuentas"));
	                cliente.set_Estado(rs.getBoolean("estado"));
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        cn.close(); 
	    }
	    return cliente;
	}


	@Override
	public List<Cliente> Listarpornombre() {
		cn = new Conexion();
        cn.Open();
        List<Cliente> list = new ArrayList<>();
        ResultSet rs = null;
        
        try {
            rs = cn.query("SELECT idCliente, nombre FROM bdsistemabanco.clientes;"); 
            
            while (rs.next()) {
                Cliente cli = new Cliente();
                cli.set_IdCliente(rs.getInt("idCliente"));
                cli.set_Nombre(rs.getString("nombre"));
                list.add(cli);
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
	public boolean borrar(int idCliente, boolean estado) {
		cn = new Conexion();
	    cn.Open();
	    PreparedStatement statement = null;
	    boolean execute;
	    try {
	    	if(!estado) 
		    {
	    		statement = cn.prepareStatement("UPDATE clientes SET estado = true WHERE idCliente = ?");	
		    }else{
		    	statement = cn.prepareStatement("UPDATE clientes SET estado = false WHERE idCliente = ?");
	        }
	        statement.setInt(1, idCliente);
	        execute = statement.executeUpdate() > 0; // Ejecuta la actualización y verifica si se realizó correctamente
	    } catch (SQLException e) {
	        e.printStackTrace();
	        execute = false; // En caso de error, establece execute a false
	    } finally {
	        cn.close(); // Cierra la conexión
	    }
	    return execute;
	}

	@Override
	public boolean Modificar(Cliente x) {
		 boolean estado = true;
		    Conexion cn = new Conexion();
		    cn.Open();
		    PreparedStatement statement = null;
		    try {
		    statement = cn.prepareStatement( "UPDATE clientes SET dniCliente=?, cuil=?, nombre=?, apellido=?, sexo=?, nacionalidad=?, direccion=?, localidad=?, provincia=?, mail=?, telefono=?, contraseña=? WHERE idCliente=?"); 
		    	statement.setString(1, x.get_DNI());
		    	statement.setString(2, x.get_CUIL());
		    	statement.setString(3, x.get_Nombre());
		    	statement.setString(4, x.get_Apellido());
		    	statement.setString(5, x.get_Sexo());
		    	statement.setInt(6, x.get_Nacionalidad().getId());
		    	statement.setString(7, x.get_Direccion());
		    	statement.setInt(8, x.get_Localidad().getId());
		    	statement.setInt(9, x.get_Provincia().getId());
		    	statement.setString(10, x.get_Mail());
		    	statement.setString(11, String.valueOf(x.get_Teléfono())); // Assuming phone is a String
		    	statement.setString(12, x.get_Contraseña());
		    	statement.setInt(13, x.get_IdCliente());

		    	if(statement.executeUpdate() > 0) {
					
				}
			
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
	            cn.close(); // Cierra la conexión
	        }
		    return estado;
	}
	public Cliente obtenerClientePorNombreUsuario(String nombreUsuario) {
	    Cliente cliente = null;
	    cn = new Conexion();
	    cn.Open();
	    String query = "SELECT c.*, n.nombre as nombreNacionalidad, l.localidad as nombreLocalidad, p.provincia as nombreProvincia " +
	                   "FROM clientes c " +
	                   "JOIN nacionalidades n ON c.nacionalidad = n.id " +
	                   "JOIN localidades l ON c.localidad = l.id " +
	                   "JOIN provincias p ON c.provincia = p.id " +
	                   "WHERE c.nombreUsuario_Cli = ?";

	    try (PreparedStatement stmt = cn.prepareStatement(query)) {
	        stmt.setString(1, nombreUsuario);
	        try (ResultSet rs = stmt.executeQuery()) {
	            if (rs.next()) {
	                cliente = new Cliente();
	                cliente.set_IdCliente(rs.getInt("idCliente"));
	                cliente.set_DNI(rs.getString("dniCliente"));
	                cliente.set_CUIL(rs.getString("cuil"));
	                cliente.set_Nombre(rs.getString("nombre"));
	                cliente.set_Apellido(rs.getString("apellido"));
	                cliente.set_Sexo(rs.getString("sexo"));

	                Nacionalidad nacionalidad = new Nacionalidad();
	                nacionalidad.setId(rs.getInt("nacionalidad"));
	                nacionalidad.setNombre(rs.getString("nombreNacionalidad"));
	                cliente.set_Nacionalidad(nacionalidad);

	                cliente.set_Fecha_de_Nacimiento(rs.getDate("fechaNacimiento").toLocalDate());
	                cliente.set_Direccion(rs.getString("direccion"));

	                Localidad localidad = new Localidad();
	                localidad.setId(rs.getInt("localidad"));
	                localidad.setNombre(rs.getString("nombreLocalidad"));
	                cliente.set_Localidad(localidad);

	                Provincia provincia = new Provincia();
	                provincia.setId(rs.getInt("provincia"));
	                provincia.setNombre(rs.getString("nombreProvincia"));
	                cliente.set_Provincia(provincia);

	                cliente.set_Mail(rs.getString("mail"));
	                cliente.set_Teléfono(rs.getInt("telefono"));
	                cliente.set_Usuario(rs.getString("nombreUsuario_Cli"));
	                cliente.set_Contraseña(rs.getString("contraseña"));
	                cliente.set_CantCuentas(rs.getInt("cantidadCuentas"));
	                cliente.set_Estado(rs.getBoolean("estado"));
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        cn.close(); // Cierra la conexión
	    }

	    return cliente;
	}

}
	

