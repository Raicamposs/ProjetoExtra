/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.Kml;

import java.sql.*;
import javax.swing.*;

public class ConexaoBD {

    final private String driver = "com.mysql.jdbc.Driver";
    final private String url = "jdbc:mysql://localhost/linhas";
    final private String usuario = "root";
    final private String senha = "12345";
    private Connection conexao;
    public Statement statement;
    public ResultSet resultset;

    public boolean conecta() {
        boolean result = true;
        try {
            Class.forName(driver);
            conexao = DriverManager.getConnection(url, usuario, senha);
            JOptionPane.showMessageDialog(null, "Conectou");
        } catch (ClassNotFoundException Driver) {
            JOptionPane.showMessageDialog(null, "Driver não localizado: " + Driver);
            result = false;
        } catch (SQLException Fonte) {
            JOptionPane.showMessageDialog(null, "Deu erro na conexão "
                    + "com a fonte de dados: " + Fonte);
            result = false;
        }
        return result;
    }

    public void desconecta() {
        boolean result = true;
        try {
            conexao.close();
            JOptionPane.showMessageDialog(null, "banco fechado");
        } catch (SQLException fecha) {
            JOptionPane.showMessageDialog(null, "Não foi possivel "
                    + "fechar o banco de dados: " + fecha);
            result = false;
        }

    }

    public void executeSQL(String sql) {
        try {
            statement = conexao.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            resultset = statement.executeQuery(sql);
            JOptionPane.showMessageDialog(null, " o sql passado: \n' " + sql + " '\n Foi executado com sucesso!");

        } catch (SQLException sqlex) {
//               JOptionPane.showMessageDialog(null,
            System.out.println("Não foi possível "
                    + "executar o comando sql,\n" + sqlex + ",\n o sql passado foi " + sql);
        }

    }

    public void InserirSQL(String sql) {
        try {
            executeSQL("select*from ponto");
            statement.executeUpdate(sql);
        } catch (SQLException e) {
             System.out.println("Não foi possível "
                    + "executar o comando sql:"+ sql);
        
        }
    }

}
