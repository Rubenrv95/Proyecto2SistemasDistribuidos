import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ruben
 */
public class login {
    
    private static String url ="jdbc:mysql://ruben@45.236.129.24/empresa";
    private static String usuarioBD = "ruben";
    private static String contraseñaDB = "sistemasdistribuidos";

    public login(){
        Connection con;
        Statement stmt;
        ResultSet rs;
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } 
        catch (ClassNotFoundException ex) {
            Logger.getLogger(login.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("No se encontró la clase");
        }
        
        
        try {
            con = DriverManager.getConnection(url, usuarioBD, contraseñaDB);
            System.out.println("Conectado");
            stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT * FROM central");
            while (rs.next()) {
                System.out.println(rs.getString("user") + " " + rs.getString("pass"));
            }
        }
        catch(SQLException e) {
            System.out.println(e);          
        }
        


    }
    
}
