package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class Conexao {
   private Connection conexao;
    String db = "BancoHoteleiro"; 
   String url = "jdbc:mysql://127.0.0.1/" + db;
   String user = "root";
   String password = "";
  

   public Conexao(){

   }

   public Connection conectar(){
       Connection link = null;
       try{
           Class.forName("org.gjt.mm.mysql.Driver");
           link = DriverManager.getConnection(this.url,this.user,this.password);
       }catch(ClassNotFoundException | SQLException ex){
           JOptionPane.showConfirmDialog(null, ex);
       }
       return link;
    }
   
   public void encerrarConexao(){
        try{
            conexao.close();
        }catch(SQLException ex){
            System.out.println("Erro no fechamento do banco" +ex.getMessage());
        }
    }
   
}
