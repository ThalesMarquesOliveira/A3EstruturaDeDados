/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.a3estruturadedados;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import conexoes.*;


public class A3EstruturaDeDados {

public static void main(String[] args) {
    // Variáveis para consulta
    Connection conexao = null;
    PreparedStatement stmt = null;
    ResultSet resultado = null;
    
    try {
        // Obter conexão usando sua classe existente
        conexao = ConexaoForMySQL.getConexao();
        
        if (conexao != null) {
            System.out.println("Conexão estabelecida com sucesso!");
            
            // SQL para consulta - ajuste conforme sua tabela
            String sql = "SELECT * FROM Golpes WHERE fraude > ? ORDER BY data DESC LIMIT 10";
            
            // Preparar a consulta
            stmt = conexao.prepareStatement(sql);
            
            // Definir parâmetros (exemplo: valor mínimo de 1000)
            stmt.setDouble(1, 1000.0);
            
            // Executar a consulta
            System.out.println("Executando consulta: " + sql);
            resultado = stmt.executeQuery();
            
            // Processar os resultados
            System.out.println("\nResultados da consulta:");
            System.out.println("---------------------");
            
            // Cabeçalho da tabela (ajuste conforme suas colunas)
            System.out.printf("%-5s | %-30s | %-12s | %-10s | %-10s\n", 
                    "ID", "DESCRIÇÃO", "DATA", "VALOR", "FRAUDE");
            System.out.println("-----------------------------------------------------");
            
            // Iterar pelos resultados
            boolean encontrouRegistros = false;
            while (resultado.next()) {
                encontrouRegistros = true;
                
                // Obter valores das colunas (ajuste os nomes e tipos conforme sua tabela)
                int id = resultado.getInt("id");
                String descricao = resultado.getString("descricao");
                String data = resultado.getString("data");
                double valor = resultado.getDouble("valor");
                boolean fraude = resultado.getBoolean("fraude");
                
                // Exibir os dados formatados
                System.out.printf("%-5d | %-30s | %-12s | R$%-8.2f | %-10s\n", 
                        id, descricao, data, valor, fraude ? "SIM" : "NÃO");
            }
            
            if (!encontrouRegistros) {
                System.out.println("Nenhum registro encontrado para os critérios especificados.");
            }
            
            System.out.println("-----------------------------------------------------");
            System.out.println("Consulta executada com sucesso!");
        } else {
            System.err.println("Não foi possível estabelecer conexão com o banco de dados.");
        }
        
    } catch (SQLException e) {
        System.err.println("Erro ao executar consulta SQL: " + e.getMessage());
    } finally {
        // Usar sua classe para fechar recursos
    }
}

// Método de exemplo para consulta específica de fraudes
public static void consultarFraudesPorTipo(String tipoFraude) {
    Connection conexao = null;
    PreparedStatement stmt = null;
    ResultSet resultado = null;
    
    try {
        conexao = ConexaoForMySQL.getConexao();
        
        String sql = "SELECT * FROM Golpes WHERE tipo_fraude = ?";
        stmt = conexao.prepareStatement(sql);
        stmt.setString(1, tipoFraude);
        
        resultado = stmt.executeQuery();
        
        System.out.println("\nFraudes do tipo: " + tipoFraude);
        System.out.println("---------------------");
        
        while (resultado.next()) {
            // Processar resultados
            int id = resultado.getInt("id");
            double valor = resultado.getDouble("valor");
            
            System.out.println("ID: " + id + ", Valor: R$" + valor);
        }
        
    } catch (SQLException e) {
        System.err.println("Erro: " + e.getMessage());
    } finally {
    }
}
}
