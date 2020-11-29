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

    void crearSurtidor(String[] mensaje) throws SQLException {
        stmt = con.createStatement();
        rs = stmt.executeQuery("INSERT surtidor VALUES ('"+mensaje[1]+"', 0, '"+mensaje[2]+"');");
        
        /*
            INSERT INTO surtidor
            VALUES ('"+mensaje[1]+"', "+mensaje[2]+")
            WHERE NOT EXISTS ( SELECT * FROM surtidor 
                   WHERE '"+mensaje[1]+"' = '"+mensaje[1]+"'
                   AND '"+mensaje[2]+"' = '"+mensaje[2]+"';
        
            INSERT INTO surtidor
            VALUES ('1', 0 ,'Estacion Curico')
            WHERE NOT EXISTS ( SELECT * FROM surtidor 
                   WHERE ID = '1'
                   AND refSucursal = 'Estacion Curico');
        
        
                IF NOT EXISTS (SELECT * FROM surtidor 
                    WHERE ID = '1'
                    AND refSucursal = 'Estacion Curico'
                BEGIN
                    INSERT INTO EmailsRecebidos (De, Assunto, Data)
                    VALUES (@_DE, @_ASSUNTO, @_DATA);
        
        INSERT INTO surtidor (ID, monto_recaudado, refSucursal)
        SELECT 23, DATE('2013-02-12'), 22.5
            FROM dual
            WHERE NOT EXISTS (SELECT 1 
                FROM funds 
                WHERE ID = 23
                AND date = DATE('2013-02-12'));
        
        
        */

    }


    
}
