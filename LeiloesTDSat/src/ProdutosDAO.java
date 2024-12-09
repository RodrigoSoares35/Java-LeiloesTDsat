

import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class ProdutosDAO {
    
    Connection conn;
    PreparedStatement prep;
    ResultSet resultset;
    ArrayList<ProdutosDTO> listagem = new ArrayList<>();
    
    public boolean conectar(){
        try {
        
    Class.forName("com.mysql.cj.jdbc.Driver");
    conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/uc11","root","22095812");
    return true;
    
        } catch (ClassNotFoundException | SQLException ex) {
        System.out.println("Erro ao conectar" + ex.getMessage());
        return false;
        }
    }
    
    public int cadastrarProduto (ProdutosDTO produto){
        //conn = new conectaDAO().connectDB();
         int status;
        
        try {
            prep = conn.prepareStatement("INSERT INTO produtos (nome, valor, status)VALUES(?,?,?)");
            prep.setString(1,produto.getNome());
            prep.setInt(2,produto.getValor());
            prep.setString(3,produto.getStatus());
            
            status = prep.executeUpdate();
            return status; 
            
            } catch (SQLException ex) {
            System.out.println("“Não foi possível SALVAR os dados! Por favor, verifique valores digitados!”." + ex.getMessage());
            return ex.getErrorCode();
            }
    }    
    
    
    public ArrayList<ProdutosDTO> listarProdutos(){
        
        return listagem;
    }
    
    
    
        
}

