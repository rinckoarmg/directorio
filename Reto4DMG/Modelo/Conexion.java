package Modelo;

/*
 * @author dianagamboa
 */
import java.sql.Connection;
import java.sql.DriverManager;

public class Conexion {
    
    public static final String URL = "jdbc:mysql://localhost:3306/bd_estudiantes";
    public static final String USERNAME = "root";
    public static final String PASSWORD = "";
    
    public static Connection getConection(){
        Connection con = null;   
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = (Connection) DriverManager.getConnection(URL, USERNAME, PASSWORD);

        } catch (Exception e) {
            System.out.println("Error " + e.getMessage());
        }
        return con;
    }
}