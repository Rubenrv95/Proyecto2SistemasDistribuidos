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
    Connection con;
    Statement stmt;
    ResultSet rs;

    public login(){
           
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
            /*
            rs = stmt.executeQuery("SELECT * FROM central");
            while (rs.next()) {
                System.out.println(rs.getString("user") + " " + rs.getString("pass"));
            }*/
        }
        catch(SQLException e) {
            System.out.println(e);          
        }
        


    }
    
    public int obtenerPrecio(String v) throws SQLException {
        stmt = con.createStatement();
        rs = stmt.executeQuery("SELECT " +v+ " FROM sucursal WHERE nombre = 'Estacion Curico';");
        
        String foundType="";
        int valor = -1;
        if(rs.next()){               
            foundType = rs.getString(v);
            valor = Integer.parseInt(foundType);
            System.out.println("valor "+v+" : "+foundType);
        }
        else
            System.out.println("Consulta no obtenida");
        
        
        return valor;
        
    }
    
    public void actualizarPreciosEnBDD(String[] mensaje) throws SQLException
    {
        stmt = con.createStatement();
        rs = stmt.executeQuery("UPDATE sucursal SET valor93 = "+mensaje[1]+", valor95 = "+mensaje[2]+", valor97 = "+mensaje[3]+", valorDiesel="+mensaje[4]+", valorKerosene="+mensaje[5]);
        
        ////Guardar info en BDD, mensaje[1] 93, mensaje[2] 95, mensaje[3] 97, mensaje[4] Diesel, mensaje[5] kerosene
    }
    
    public void crearServicentro(String nombre) throws SQLException {
        stmt = con.createStatement();
        rs = stmt.executeQuery("INSERT INTO sucursal VALUES ('" + nombre + "');" );
        
    }
    
}
