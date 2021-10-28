
package purodoce.conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author lex
 */
public class Conexao {
    public static Connection conexao (){
        try {
            Class.forName("org.postgresql.Driver");
            Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/purodocedb","postgres_user_admin", "super_admin");
            return con;
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, erro.getMessage(),"Erro ao conectar",JOptionPane.ERROR_MESSAGE);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Conexao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public static void desconectar(Connection con){
   
        try {
            if (!con.isClosed()){
                con.close();
                
            }
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, erro.getMessage(),"Erro ao desconectar",JOptionPane.ERROR_MESSAGE);
        }
     }
        
        public static void desconectar (Connection con, PreparedStatement pstmt){
        desconectar(con);
        try {
            if (pstmt != null){ 
                pstmt.close();
            }
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, erro.getMessage(),"Erro ao desconectar",JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public static void desconectar (Connection con, PreparedStatement pstmt, ResultSet rs){
        desconectar(con, pstmt);
        try {
            if (rs != null){ 
                rs.close();
            }
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, erro.getMessage(),"Erro ao desconectar",JOptionPane.ERROR_MESSAGE);
        }
    }
}
