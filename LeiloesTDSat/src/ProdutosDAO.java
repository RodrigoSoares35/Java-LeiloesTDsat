
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProdutosDAO {

    Connection conn;
    PreparedStatement prep;
    ResultSet resultset;
    ArrayList<ProdutosDTO> listagem = new ArrayList<>();

    public boolean conectar() {
        try {

            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/uc11", "root", "22095812");
            return true;

        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println("Erro ao conectar" + ex.getMessage());
            return false;
        }
    }

    public int cadastrarProduto(ProdutosDTO produto) {

        int status;

        try {
            prep = conn.prepareStatement("INSERT INTO produtos (nome, valor, status)VALUES(?,?,?)");
            prep.setString(1, produto.getNome());
            prep.setInt(2, produto.getValor());
            prep.setString(3, produto.getStatus());

            status = prep.executeUpdate();
            return status;

        } catch (SQLException ex) {
            System.out.println("“Não foi possível SALVAR os dados! \nPor favor, verifique valores digitados!”." + ex.getMessage());
            return ex.getErrorCode();
        }
    }

    public List<ProdutosDTO> listarProdutos() {
        String sql = "SELECT * FROM produtos";
        List<ProdutosDTO> lista = new ArrayList<>();

        try ( PreparedStatement prep = conn.prepareStatement(sql);  ResultSet resultset = prep.executeQuery()) {

            while (resultset.next()) {
                ProdutosDTO produto = new ProdutosDTO();
                produto.setId(resultset.getInt("id"));
                produto.setNome(resultset.getString("nome"));
                produto.setValor(resultset.getInt("valor"));
                produto.setStatus(resultset.getString("status"));

                lista.add(produto);
            }

        } catch (SQLException ex) {
            System.err.println("Erro ao conectar: " + ex.getMessage());

        }

        return lista;
    }

    public void desconectar() {
        try {
            conn.close();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro de conexão");

        }
    }

    public int atualizar(ProdutosDTO produto) {
        int status;

        try {
            prep = conn.prepareStatement("UPDATE produtos SET status = ? WHERE id = ?");

            prep.setString(1, produto.getStatus());
            prep.setInt(2, produto.getId());
            status = prep.executeUpdate();
            return status;

        } catch (SQLException ex) {
            System.out.println("Erro ao atualizar: " + ex.getMessage());
            return ex.getErrorCode();
        }
    }

    // FILTRO POR STATUS
    public List<ProdutosDTO> listagemStatus(String filtro) {

        String sql = "select * from produtos where status = 'Vendido'";
        try {
            prep = conn.prepareStatement(sql);

            resultset = prep.executeQuery();
            List<ProdutosDTO> lista = new ArrayList<>();
            while (resultset.next()) {
                ProdutosDTO produto = new ProdutosDTO();
                produto.setId(resultset.getInt("id"));
                produto.setNome(resultset.getString("nome"));
                produto.setValor(resultset.getInt("valor"));
                produto.setStatus(resultset.getString("status"));

                lista.add(produto);
            }
            return lista;
        } catch (SQLException ex) {
            System.out.println("Erro ao conectar: " + ex.getMessage());
            return null;
        }
    }

}
