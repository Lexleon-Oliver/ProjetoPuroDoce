package purodoce.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import purodoce.conexao.Conexao;
import purodoce.model.Produto;

/**
 *
 * @author lex
 */
public class ProdutoDAO {

    public List<Produto> listar() {
        Connection con = Conexao.conexao();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String medida;
        
        List<Produto> produtos = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * FROM produto");
            rs = stmt.executeQuery();

            while (rs.next()) {
                
                Produto prod = new Produto();
                prod.setId(rs.getLong("id"));
                prod.setNome(rs.getString("nome"));
                medida=rs.getString("medida");
                prod.setQuantidade(rs.getFloat("quantidade"),medida);
              
                prod.setPreco(rs.getFloat("preco"));
               
                produtos.add(prod);
            }

        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, erro.getMessage(),"Erro ao consultar",JOptionPane.ERROR_MESSAGE);
        } finally {
            Conexao.desconectar(con, stmt, rs);
        }

        return produtos;
    }

    public void excluir(Long id) {
        Connection con = Conexao.conexao();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("DELETE FROM produto WHERE id = ?");
            stmt.setLong(1, id);
            
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null,"Excluído com sucesso!","Operação Bem Sucedida",JOptionPane.INFORMATION_MESSAGE);
           
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, erro.getMessage(),"Erro ao desconectar",JOptionPane.ERROR_MESSAGE);
        } finally {
            Conexao.desconectar(con, stmt);
        }
    }

    public void adicionar(Produto prod) {
        Connection con = Conexao.conexao();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("INSERT INTO produto(nome,quantidade,medida,preco)VALUES(?,?,?,?)");
            stmt.setString(1, prod.getNome());
            stmt.setFloat(2, prod.getQuantidade());
            stmt.setString(3, prod.getMedida());
            stmt.setFloat(4, prod.getPreco());
            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null,"Cadastrado com sucesso!","Operação bem sucedida",JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, erro.getMessage(),"Erro ao cadastrar",JOptionPane.ERROR_MESSAGE);
        } finally {
            Conexao.desconectar(con, stmt);
        }
    }

    public void alterar(Produto prod) {
        Connection con = Conexao.conexao();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("UPDATE produto SET nome =?, quantidade=?, medida=?, preco=? WHERE id = ?");
            stmt.setString(1, prod.getNome());
            stmt.setFloat(2, prod.getQuantidade());
            stmt.setString(3, prod.getMedida());
            stmt.setFloat(4, prod.getPreco());
            stmt.setLong(5, prod.getId());
            
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Atualizado com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar: " + ex);
        } finally {
            Conexao.desconectar(con, stmt);
        }

    }

    public Produto listarPorId(Long id) {
        Connection con = Conexao.conexao();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Produto produto = new Produto();
        String medida;

        try {
            stmt = con.prepareStatement("SELECT * FROM produto WHERE id = ?");
            stmt.setLong(1, id);
            rs = stmt.executeQuery();

            if (rs.next()) {

                produto.setId(rs.getLong("id"));
                produto.setNome(rs.getString("nome"));
                medida=rs.getString("medida");
                produto.setQuantidade(rs.getFloat("quantidade"),medida);
                produto.setMedida(rs.getString("medida"));
                produto.setPreco(rs.getFloat("preco"));

            }

        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, erro.getMessage(),"Erro ao consultar",JOptionPane.ERROR_MESSAGE);
        } finally {
            Conexao.desconectar(con, stmt, rs);
        }

        return produto;
    }

    public Produto listarPorNome(String nome) {
        Connection con = Conexao.conexao();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Produto produto = new Produto();
        String medida;

        try {
            stmt = con.prepareStatement("SELECT * FROM produto WHERE nome LIKE ?");
            stmt.setString(1, nome);
            rs = stmt.executeQuery();

            if (rs.next()) {

                produto.setId(rs.getLong("id"));
                produto.setNome(rs.getString("nome"));
                 medida=rs.getString("medida");
                produto.setQuantidade(rs.getFloat("quantidade"),medida);
                produto.setMedida(rs.getString("medida"));
                produto.setPreco(rs.getFloat("preco"));

            }

        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, erro.getMessage(),"Erro ao consultar",JOptionPane.ERROR_MESSAGE);
        } finally {
            Conexao.desconectar(con, stmt, rs);
        }

        return produto;

    }
    
}
