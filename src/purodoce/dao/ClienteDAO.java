
package purodoce.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import purodoce.conexao.Conexao;
import purodoce.model.Cliente;

/**
 *
 * @author lex
 */
public class ClienteDAO {

    public List<Cliente> listar() {
        Connection con = Conexao.conexao();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Cliente> clientes = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * FROM cliente");
            rs = stmt.executeQuery();

            while (rs.next()) {
                
                Cliente cliente = new Cliente();
                cliente.setId(rs.getLong("id"));
                cliente.setNome(rs.getString("nome"));
                cliente.setTelefone(rs.getString("telefone"));
                cliente.setRua(rs.getString("rua"));
                cliente.setNumero(rs.getString("numero"));
                cliente.setBairro(rs.getString("bairro"));
                cliente.setCidade(rs.getString("cidade"));
               
                clientes.add(cliente);
            }

        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, erro.getMessage(),"Erro ao consultar",JOptionPane.ERROR_MESSAGE);
        } finally {
            Conexao.desconectar(con, stmt, rs);
        }

        return clientes;
    }

    public Cliente listarPorId(Long id) {
        Connection con = Conexao.conexao();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Cliente cliente = new Cliente();

        try {
            stmt = con.prepareStatement("SELECT * FROM cliente WHERE id = ?");
            stmt.setLong(1, id);
            rs = stmt.executeQuery();

            if (rs.next()) {

                cliente.setId(rs.getLong("id"));
                cliente.setNome(rs.getString("nome"));
                cliente.setTelefone(rs.getString("telefone"));
                cliente.setRua(rs.getString("rua"));
                cliente.setNumero(rs.getString("numero"));
                cliente.setBairro(rs.getString("bairro"));
                cliente.setCidade(rs.getString("cidade"));
            }

        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, erro.getMessage(),"Erro ao consultar",JOptionPane.ERROR_MESSAGE);
        } finally {
            Conexao.desconectar(con, stmt, rs);
        }
        return cliente;
    }

    public void adicionar(Cliente cliente) {
        Connection con = Conexao.conexao();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("INSERT INTO cliente(nome, telefone, rua, numero, bairro, cidade)VALUES(?,?,?,?,?,?)");
            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getTelefone());
            stmt.setString(3, cliente.getRua());
            stmt.setString(4, cliente.getNumero());
            stmt.setString(5, cliente.getBairro());
            stmt.setString(6, cliente.getCidade());
            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null,"Cadastrado com sucesso!","Operação bem sucedida",JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, erro.getMessage(),"Erro ao cadastrar",JOptionPane.ERROR_MESSAGE);
        } finally {
            Conexao.desconectar(con, stmt);
        }
    }

    public void excluir(Long id) {
        Connection con = Conexao.conexao();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("DELETE FROM cliente WHERE id = ?");
            stmt.setLong(1, id);
            
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null,"Excluído com sucesso!","Operação Bem Sucedida",JOptionPane.INFORMATION_MESSAGE);
           
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, erro.getMessage(),"Erro ao desconectar",JOptionPane.ERROR_MESSAGE);
        } finally {
            Conexao.desconectar(con, stmt);
        }
    }

    public void alterar(Cliente cliente) {
        Connection con = Conexao.conexao();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("UPDATE cliente SET nome =?, telefone=?, rua=?, numero=?, bairro=?, cidade=? WHERE id = ?");
            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getTelefone());
            stmt.setString(3, cliente.getRua());
            stmt.setString(4, cliente.getNumero());
            stmt.setString(5, cliente.getBairro());
            stmt.setString(6, cliente.getCidade());
            stmt.setLong(7, cliente.getId());
            
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Atualizado com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar: " + ex);
        } finally {
            Conexao.desconectar(con, stmt);
        }
    }

    public Cliente listarPorNome(String nome) {
        Connection con = Conexao.conexao();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Cliente cliente = new Cliente();

        try {
            stmt = con.prepareStatement("SELECT * FROM cliente WHERE nome = ?");
            stmt.setString(1, nome);
            rs = stmt.executeQuery();

            if (rs.next()) {

                cliente.setId(rs.getLong("id"));
                cliente.setNome(rs.getString("nome"));
                cliente.setTelefone(rs.getString("telefone"));
                cliente.setRua(rs.getString("rua"));
                cliente.setNumero(rs.getString("numero"));
                cliente.setBairro(rs.getString("bairro"));
                cliente.setCidade(rs.getString("cidade"));
            }

        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, erro.getMessage(),"Erro ao consultar",JOptionPane.ERROR_MESSAGE);
        } finally {
            Conexao.desconectar(con, stmt, rs);
        }
        return cliente;
    }
    
}
