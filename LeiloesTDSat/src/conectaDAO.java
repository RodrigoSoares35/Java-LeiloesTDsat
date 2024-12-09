
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;




public class conectaDAO {
    
  
    Connection conn;
    PreparedStatement st;
    ResultSet rs;
    
    public boolean conectar(){
        try {
        
    Class.forName("com.mysql.cj.jdbc.Driver");
    conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/uc11","root", "22095812");
    return true;
    
        } catch (ClassNotFoundException | SQLException ex) {
        System.out.println("Erro ao conectar" + ex.getMessage());
        return false;
        }
    }
}
