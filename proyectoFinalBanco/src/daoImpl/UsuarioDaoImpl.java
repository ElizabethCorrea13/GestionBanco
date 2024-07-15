package daoImpl;

import dao.UsuarioDao;
import entidad.Usuario;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.PreparedStatement;

public class UsuarioDaoImpl implements UsuarioDao{
	 Conexion cn = new Conexion();

	@Override
	public boolean validarDatos(Usuario user) {
        boolean isValid = false;
        cn.Open();
        
        String nombreUsuario = user.getNombreUsuario();
        String contrasena = user.getContrase�a();

        try {
        	PreparedStatement stmt = cn.prepareStatement("SELECT * FROM USUARIOS WHERE nombreUsuario_us = ? AND contrasena = ?");
            stmt.setString(1, nombreUsuario);
            stmt.setString(2, contrasena);
            
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                	boolean Admin = rs.getBoolean("admin");
                    user.setAdmin(Admin);
                    // Usuario y contrase�a v�lidos
                    System.out.println("Usuario y contrase�a v�lidos.");
                    isValid = true;
                    
                } else {
                    // Usuario o contrase�a inv�lidos
                    System.out.println("Usuario o contrase�a inv�lidos.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            cn.close();
        }
        return isValid;
    }
	    
	    
	    
		

}
