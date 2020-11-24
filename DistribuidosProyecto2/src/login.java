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
    
    private String url ="ruben@45.236.129.24";
    private String userDB = "ruben";
    private String passDB = "sistemasdistribuidos";

    public login() {
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
            con = DriverManager.getConnection(url, this.userDB, this.passDB);
            stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT * FROM");
            rs.next();
        }
        catch(SQLException e) {
            System.out.println("Error en la consulta");          
        }
        


    }
    
}
