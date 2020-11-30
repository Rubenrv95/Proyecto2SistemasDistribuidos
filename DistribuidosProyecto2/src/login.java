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
        stmt.executeUpdate("UPDATE sucursal SET valor93 = "+mensaje[1]+", valor95 = "+mensaje[2]+", valor97 = "+mensaje[3]+", valorDiesel="+mensaje[4]+", valorKerosene="+mensaje[5]);
        
        ////Guardar info en BDD, mensaje[1] 93, mensaje[2] 95, mensaje[3] 97, mensaje[4] Diesel, mensaje[5] kerosene
    }

    void crearSurtidor(String[] mensaje) throws SQLException {
        stmt = con.createStatement();
        stmt.executeUpdate("INSERT INTO surtidor (ID, monto_recaudado, refSucursal) SELECT * FROM (SELECT '"+mensaje[1]+"', '0', '"+mensaje[2]+"') AS tmp WHERE NOT EXISTS ( SELECT ID, refSucursal FROM surtidor WHERE ID = '"+mensaje[1]+"' AND refSucursal = '"+mensaje[2]+"') LIMIT 1;  ");
        System.out.println(mensaje[1] +" "+ mensaje[2]);
        /*
            INSERT INTO surtidor (ID, monto_recaudado, refSucursal)
            SELECT * FROM (SELECT '"+mensaje[1]+"', '0', '"+mensaje[2]+"') AS tmp
            WHERE NOT EXISTS (
            SELECT ID, refSucursal FROM surtidor WHERE ID = '"+mensaje[1]+"' AND refSucursal = '"+mensaje[2]+"'
        ) LIMIT 1;   
        
        */

    }


    
    public void crearServicentro(String nombre) throws SQLException {
        stmt = con.createStatement();

        rs = stmt.executeQuery("SELECT nombre FROM sucursal;");
        while (rs.next()) {
            if (rs.getString("nombre").equals(nombre)) {
                System.out.println("Ya existe esa sucursal en nuestra base de datos. Pruebe con otro nombre");
                return;
            }
        }   
        stmt.executeUpdate("INSERT INTO sucursal(nombre, monto_recaudado) VALUES ('" + nombre + "', 0);" );
        
    }

    public void generarCarga(String[] mensaje) throws SQLException {
        stmt = con.createStatement();
             
        rs = stmt.executeQuery("SELECT * FROM surtidor WHERE refSucursal = '" + mensaje[4] + "';");
        
        double monto_surtidor =0;
        while (rs.next()) {
            if (rs.getInt("ID") == Integer.parseInt(mensaje[2])) {
                monto_surtidor = rs.getDouble("monto_recaudado");
                break;
            }
        }
        
        monto_surtidor += Integer.parseInt(mensaje[3]);
        
        stmt.executeUpdate("UPDATE surtidor SET monto_recaudado = " + monto_surtidor + " WHERE surtidor.ID = " + mensaje[2] + " AND refSucursal = '" + mensaje[4] + "';");
        
        System.out.println("Monto del surtidor: " + monto_surtidor);
        
        rs = stmt.executeQuery("SELECT * FROM sucursal WHERE nombre = '" + mensaje[4] + "';");
        
        double monto_sucursal = 0;
        while (rs.next()) {
            monto_sucursal = rs.getDouble("monto_recaudado");
            break;
        }
        
        System.out.println("Monto de sucursal: " + monto_sucursal);

        
        double total = monto_surtidor + monto_sucursal;
        System.out.println(total);
        
        
        stmt.executeUpdate("UPDATE sucursal SET monto_recaudado = " + total + " WHERE sucursal.nombre = '" + mensaje[4] + "';");
        
        System.out.println("Se actualizó la wea");
        
        
        //Guardar info en BDD, mensaje[1] cantidadLitros, mensaje[2] nombreSurtidor, mensaje[3] Monto a pagar, mensaje [4] nobmreServicentro
    }
    
}
