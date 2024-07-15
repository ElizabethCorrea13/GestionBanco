package daoImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.jdbc.PreparedStatement;

public class Conexion {

    private String host = "jdbc:mysql://localhost:3306/";
    private String dbName = "bdsistemaBanco"; // Nombre de tu base de datos
    private String user = "root";
    private String pass = "root";

    private Connection connection;

    public Conexion() {
        // Constructor vacío
    }

    public Connection Open() {
        try {
            Class.forName("com.mysql.jdbc.Driver"); // Actualizar driver
            this.connection = DriverManager.getConnection(host + dbName, user, pass);
            System.out.println("Connection established successfully.");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("MySQL Driver not found.");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Failed to establish connection to the database.");
        }
        return this.connection;
    }
    
    public Connection getConnection() {
        return connection;
    }

    public ResultSet query(String query) {
        Statement st;
        ResultSet rs = null;
        try {
            if (connection == null || connection.isClosed()) {
                Open();
            }
            st = connection.createStatement();
            rs = st.executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }

    public boolean execute(String query) {
        Statement st;
        boolean save = true;
        try {
            if (connection == null || connection.isClosed()) {
                Open();
            }
            st = connection.createStatement();
            st.executeUpdate(query);
        } catch (SQLException e) {
            save = false;
            e.printStackTrace();
        }
        return save;
    }

    public boolean close() {
        boolean ok = true;
        try {
            if (connection != null) {
                connection.close();
                System.out.println("Connection closed successfully.");
            }
        } catch (Exception e) {
            ok = false;
            e.printStackTrace();
        }
        return ok;
    }

    public PreparedStatement prepareStatement(String query) {
        PreparedStatement pstmt = null;
        try {
            if (connection == null || connection.isClosed()) {
                Open();
            }
            pstmt = (PreparedStatement) connection.prepareStatement(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pstmt;
    }
    
    
}
