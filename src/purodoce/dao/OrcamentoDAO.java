
package purodoce.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import purodoce.conexao.Conexao;
import purodoce.model.Cliente;
import purodoce.model.Pedido;

/**
 *
 * @author lex
 */
public class OrcamentoDAO {

    public String buscarDimensao(int numPessoas, String forma) {
        Connection con = Conexao.conexao();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String dimensao, dimensao2 = new String();
        dimensao2 ="";

        try {
            stmt = con.prepareStatement("SELECT dimensao FROM medida WHERE forma = ? AND pessoas = ? ");
            stmt.setString(1, forma);
            stmt.setInt(2, numPessoas);
            rs = stmt.executeQuery();

            while (rs.next()) {
                dimensao = rs.getString("dimensao").concat(", ");
                dimensao2 = dimensao2.concat(dimensao);
            }

        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, erro.getMessage(),"Erro ao consultar",JOptionPane.ERROR_MESSAGE);
        } finally {
            Conexao.desconectar(con, stmt, rs);
        }
        return dimensao2;
    }

    public void salvar(Pedido pedido) {
        Connection con = Conexao.conexao();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("INSERT INTO pedido(cliente,forma,dimensao,datacompra,dataentrega,valor,pago,custo)VALUES(?,?,?,?,?,?,?,?)");
            stmt.setString(1, pedido.getCliente());
            stmt.setString(2, pedido.getForma());
            stmt.setString(3, pedido.getDimensao());
            stmt.setObject(4, pedido.getDataCompra());
            stmt.setObject(5, pedido.getDataEntrega());
            stmt.setFloat(6, pedido.getValor());
            stmt.setBoolean(7, pedido.isPago());
            stmt.setFloat(8, pedido.getCusto());
            
            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null,"Cadastrado com sucesso!","Operação bem sucedida",JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, erro.getMessage(),"Erro ao cadastrar",JOptionPane.ERROR_MESSAGE);
        } finally {
            Conexao.desconectar(con, stmt);
        }
    }

    public Long buscarId(Cliente cliente, String dataCompra) {
        Long id = null;
        Connection con = Conexao.conexao();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            stmt = con.prepareStatement("SELECT id FROM pedido WHERE cliente = ? AND datacompra = ? ");
            stmt.setString(1, cliente.getNome());
            stmt.setString(2, dataCompra);
            rs = stmt.executeQuery();

            if (rs.next()) {
                id = rs.getLong("id");
            }

        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, erro.getMessage(),"Erro ao consultar",JOptionPane.ERROR_MESSAGE);
        } finally {
            Conexao.desconectar(con, stmt, rs);
        }
        return id;
    }

    public List<Pedido> listar() {
        Connection con = Conexao.conexao();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Pedido> pedidos = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * FROM pedido");
            rs = stmt.executeQuery();

            while (rs.next()) {
                
                Pedido pedido = new Pedido();
                pedido.setId(rs.getLong("id"));
                pedido.setCliente(rs.getString("cliente"));
                pedido.setForma(rs.getString("forma"));
                pedido.setDimensao(rs.getString("dimensao"));
                pedido.setDataCompra(LocalDate.parse(rs.getString("datacompra"), DateTimeFormatter.ofPattern("yyyy-MM-dd")));
                pedido.setDataEntrega(LocalDate.parse(rs.getString("dataentrega"), DateTimeFormatter.ofPattern("yyyy-MM-dd")));
                pedido.setValor(rs.getFloat("valor"));
                pedido.setPago(rs.getBoolean("pago"));
               
                pedidos.add(pedido);
            }

        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, erro.getMessage(),"Erro ao consultar",JOptionPane.ERROR_MESSAGE);
        } finally {
            Conexao.desconectar(con, stmt, rs);
        }

        return pedidos;
    }
    
    public List<Pedido> buscarPorPeriodo(LocalDate dataInicio, LocalDate dataFim) {
        Connection con = Conexao.conexao();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Pedido> pedidos = new ArrayList<>();

        try {
            
            stmt = con.prepareStatement("SELECT * FROM pedido WHERE datacompra BETWEEN ? AND ?");
            stmt.setObject(1, dataInicio);
            stmt.setObject(2, dataFim);
            rs = stmt.executeQuery();
            
            while (rs.next()) {
                
                Pedido pedido = new Pedido();
                pedido.setId(rs.getLong("id"));
                pedido.setCliente(rs.getString("cliente"));
                pedido.setForma(rs.getString("forma"));
                pedido.setDimensao(rs.getString("dimensao"));
                pedido.setDataCompra(LocalDate.parse(rs.getString("datacompra"), DateTimeFormatter.ofPattern("yyyy-MM-dd")));
                pedido.setDataEntrega(LocalDate.parse(rs.getString("dataentrega"), DateTimeFormatter.ofPattern("yyyy-MM-dd")));
                pedido.setValor(rs.getFloat("valor"));
                pedido.setPago(rs.getBoolean("pago"));
                pedidos.add(pedido);
                
            }

        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, erro.getMessage(),"Erro ao consultar",JOptionPane.ERROR_MESSAGE);
        } finally {
            Conexao.desconectar(con, stmt, rs);
        }

        return pedidos;
    }

    
    public Pedido listarPorId(Long id){
        Connection con = Conexao.conexao();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        Pedido pedido = new Pedido();

        try {
            stmt = con.prepareStatement("SELECT * FROM pedido WHERE id = ?");
            stmt.setLong(1, id);
            rs = stmt.executeQuery();

            if (rs.next()) {
                
                
                pedido.setId(rs.getLong("id"));
                pedido.setCliente(rs.getString("cliente"));
                pedido.setForma(rs.getString("forma"));
                pedido.setDimensao(rs.getString("dimensao"));
                pedido.setDataCompra(LocalDate.parse(rs.getString("datacompra"), DateTimeFormatter.ofPattern("yyyy-MM-dd")));
                pedido.setDataEntrega(LocalDate.parse(rs.getString("dataentrega"), DateTimeFormatter.ofPattern("yyyy-MM-dd")));
                pedido.setValor(rs.getFloat("valor"));
                pedido.setPago(rs.getBoolean("pago"));
                pedido.setCusto(rs.getFloat("custo"));
               
            }

        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, erro.getMessage(),"Erro ao consultar",JOptionPane.ERROR_MESSAGE);
        } finally {
            Conexao.desconectar(con, stmt, rs);
        }

        return pedido;

    }
    
    public void alterar(Pedido pedido) {
        Connection con = Conexao.conexao();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("UPDATE pedido SET cliente=?, forma=?, dimensao=?, datacompra=?, dataentrega=?, valor=?, pago=?, custo=? WHERE id = ?");
            stmt.setString(1, pedido.getCliente());
            stmt.setString(2, pedido.getForma());
            stmt.setString(3, pedido.getDimensao());
            stmt.setObject(4, pedido.getDataCompra());
            stmt.setObject(5, pedido.getDataEntrega());
            stmt.setFloat(6, pedido.getValor());
            stmt.setBoolean(7, pedido.isPago());
            stmt.setFloat(8, pedido.getCusto());
            stmt.setLong(9, pedido.getId());
            
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Atualizado com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar: " + ex);
        } finally {
            Conexao.desconectar(con, stmt);
        }
    }

    public float buscarDespesa() {
        float despesa = 0;
        float despesaTotal = 0;
        Connection con = Conexao.conexao();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            stmt = con.prepareStatement("SELECT custo FROM pedido");
            rs = stmt.executeQuery();

            while (rs.next()) {
                despesa = rs.getFloat("custo");
                despesaTotal=despesaTotal + despesa;
            }

        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, erro.getMessage(),"Erro ao consultar",JOptionPane.ERROR_MESSAGE);
        } finally {
            Conexao.desconectar(con, stmt, rs);
        }
        return despesaTotal;
    }
    
    public float buscarDespesa(LocalDate inicio, LocalDate fim) {
        float despesa = 0;
        float despesaTotal = 0;
        Connection con = Conexao.conexao();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            stmt = con.prepareStatement("SELECT custo FROM pedido WHERE datacompra BETWEEN ? AND ?");
            stmt.setObject(1, inicio);
            stmt.setObject(2, fim);
            rs = stmt.executeQuery();

            while (rs.next()) {
                despesa = rs.getFloat("custo");
                despesaTotal=despesaTotal + despesa;
            }

        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, erro.getMessage(),"Erro ao consultar",JOptionPane.ERROR_MESSAGE);
        } finally {
            Conexao.desconectar(con, stmt, rs);
        }
        return despesaTotal;
    }

    public float buscarPedidosReceber() {
        float receber = 0;
        float receberTotal = 0;
        Connection con = Conexao.conexao();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            stmt = con.prepareStatement("SELECT valor FROM pedido WHERE pago = false");
            rs = stmt.executeQuery();

            while (rs.next()) {
                receber = rs.getFloat("valor");
                receberTotal=receberTotal + receber;
            }

        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, erro.getMessage(),"Erro ao consultar",JOptionPane.ERROR_MESSAGE);
        } finally {
            Conexao.desconectar(con, stmt, rs);
        }
        return receberTotal;
    }
    
    public float buscarPedidosReceber(LocalDate inicio, LocalDate fim) {
        float receber = 0;
        float receberTotal = 0;
        Connection con = Conexao.conexao();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            stmt = con.prepareStatement("SELECT valor FROM pedido WHERE(datacompra BETWEEN ? AND ?) AND pago = false");
            stmt.setObject(1, inicio);
            stmt.setObject(2, fim);
            rs = stmt.executeQuery();

            while (rs.next()) {
                receber = rs.getFloat("valor");
                receberTotal=receberTotal + receber;
            }

        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, erro.getMessage(),"Erro ao consultar",JOptionPane.ERROR_MESSAGE);
        } finally {
            Conexao.desconectar(con, stmt, rs);
        }
        return receberTotal;

    }

    
    
    public float buscarReceita() {
        float receita = 0;
        float receitaTotal = 0;
        Connection con = Conexao.conexao();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            stmt = con.prepareStatement("SELECT valor FROM pedido");
            rs = stmt.executeQuery();

            while (rs.next()) {
                receita = rs.getFloat("valor");
                receitaTotal=receitaTotal + receita;
            }

        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, erro.getMessage(),"Erro ao consultar",JOptionPane.ERROR_MESSAGE);
        } finally {
            Conexao.desconectar(con, stmt, rs);
        }
        return receitaTotal;
    }
    
    public float buscarReceita(LocalDate inicio, LocalDate fim) {
        float receita = 0;
        float receitaTotal = 0;
        Connection con = Conexao.conexao();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
           stmt = con.prepareStatement("SELECT valor FROM pedido WHERE datacompra BETWEEN ? AND ?");
            stmt.setObject(1, inicio);
            stmt.setObject(2, fim);
            rs = stmt.executeQuery();
            
            while (rs.next()) {
                receita = rs.getFloat("valor");
                receitaTotal=receitaTotal + receita;
            }

        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, erro.getMessage(),"Erro ao consultar",JOptionPane.ERROR_MESSAGE);
        } finally {
            Conexao.desconectar(con, stmt, rs);
        }
        return receitaTotal;
        
    }
    

    
}
