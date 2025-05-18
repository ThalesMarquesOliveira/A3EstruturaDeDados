
package conexoes;
import EstruturaDeDados.Registro;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class QueryBuscaDados {
   
 private Connection connection;

 // Construtor recebe a conexão existente
 public QueryBuscaDados(Connection connection) {
     this.connection = connection;
 }

     // Método para executar uma query SELECT e retornar até 1000 registros
  public List<Registro> executeQuery(String query) {
      List<Registro> registros = new ArrayList();

      try (PreparedStatement statement = connection.prepareStatement(query);
           ResultSet resultSet = statement.executeQuery()) {

          int count = 0; // Contador para limitar a 1000 registros
          while (resultSet.next() && count < 1000) {
              // query com colunas do banco selecionadas 
              String nome = resultSet.getString("nome");
              String genero = resultSet.getString("genero");
              int idade = resultSet.getInt("idade");
              String estado = resultSet.getString("estado");
              String cidade = resultSet.getString("cidade");
              String tipodaconta = resultSet.getString("tipodaconta");
              String datatransacao = resultSet.getString("datatransacao");
              Float valortransacao = resultSet.getFloat("valortransacao");
              String tipodetransacao = resultSet.getString("tipotransacao");
              String categoria = resultSet.getString("categoria");
              String dispositivodetransacao = resultSet.getString("dispositivodetransacao");
              Boolean fraude = resultSet.getBoolean("fraude");

              // Adiciona o registro à lista
              registros.add(new Registro(nome, genero, idade, estado, cidade, tipodaconta, datatransacao, valortransacao, tipodetransacao,
              categoria, dispositivodetransacao, fraude));
              count++;
          }

      } catch (SQLException e) {
          System.err.println("Erro ao executar a query: " + e.getMessage());
      }

      return registros;
  }
}

