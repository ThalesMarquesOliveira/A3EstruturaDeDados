package conexoes;

import static conexoes.FechaConexaoMysql.fecharConnection;
import static conexoes.ConexaoForMySQL.getConexao;
import java.sql.Connection;


public class TestaConexao {
       public static boolean testarConexao() {
       Connection conexao = getConexao();
       if (conexao != null) {
           fecharConnection (conexao);
           return true;
       }
       return false;
   }
}
