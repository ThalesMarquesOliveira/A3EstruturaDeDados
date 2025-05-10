
package conexoes;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/*
 * Classe utilitária para fechamento de recursos JDBC (Connection, Statement, ResultSet)
 */
public class FechaConexaoMysql {

    /*
     * Fecha a conexão, se não for nula.
     * @param conn Connection a ser fechada.
     */
    public static void fecharConnection(Connection conn) {
        if (conn != null) {
            try {
                if (!conn.isClosed()) {
                    conn.close();
                    System.out.println("Conexão encerrada com sucesso!");
                }
            } catch (SQLException e) {
                System.err.println("Erro ao fechar conexão: " + e.getMessage());
            }
        }
    }

    /*
     * Fecha o Statement, se não for nulo.
     * @param stmt Statement a ser fechado.
     */
    public static void fecharStatement(Statement stmt) {
        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException e) {
                System.err.println("Erro ao fechar Statement: " + e.getMessage());
            }
        }
    }

    /*
     * Fecha o ResultSet, se não for nulo.
     * @param rs ResultSet a ser fechado.
     */
    public static void fecharResultSet(ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                System.err.println("Erro ao fechar ResultSet: " + e.getMessage());
            }
        }
    }

    /*
     * Fecha todos os recursos passados (ResultSet, Statement, Connection).
     * Usar para garantir fechamento correto no final.
     * @param rs ResultSet
     * @param stmt Statement
     * @param conn Connection
     */
    public static void fecharTudo(ResultSet rs, Statement stmt, Connection conn) {
        fecharResultSet(rs);
        fecharStatement(stmt);
        fecharConnection(conn);
    }
}

