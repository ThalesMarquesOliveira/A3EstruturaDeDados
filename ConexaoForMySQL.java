package conexoes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/*
* Classe util para gerenciar conexões com banco de dados MySQL
*/


public class ConexaoForMySQL {
   
   // Parâmetros de conexão - ajuste conforme sua configuração
    
   private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
   private static final String URL = "jdbc:mysql://localhost:3306/DataFraude";
   private static final String USUARIO = "root"; // Altere para seu usuário
   private static final String SENHA = "1234"; // Altere para sua senha
   
   /*
    * Obtem uma conexão com o banco de dados
    * @return objeto Connection ou null em caso de falha
    */
   
   public static Connection getConexao() {
       Connection conexao = null;
       
       try {
           // Carrega o driver JDBC
           Class.forName(DRIVER);
           
           // Obtem a conexao
           conexao = DriverManager.getConnection(URL, USUARIO, SENHA);
           return conexao;
           
       } catch (ClassNotFoundException e) {
           System.err.println("Driver MySQL não encontrado: " + e.getMessage());
       } catch (SQLException e) {
           System.err.println("Erro ao conectar ao MySQL: " + e.getMessage());
       }
       
       return null;
   }

}