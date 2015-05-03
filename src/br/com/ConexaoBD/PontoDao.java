/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ConexaoBD;

import br.com.Objetos.Ponto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Raiane
 */
public class PontoDao {

    // a conexão com o banco de dados
    private final Connection connection;
    private PreparedStatement stmt;
    private ResultSet rs;

    public PontoDao() {
        this.connection = new ConnectionFactory().getConnection("linhas", "299071", "root");
        createTablePonto();
    }

    public void adicionaPonto(Ponto ponto) {

        String sql = "insert into ponto"
                + " (nome,alt, lng, lat) "
                + "values (?,?,?,?)";

        try {
            // seta os valores
            try ( // prepared statement para inserção
                    PreparedStatement novoStmt = connection.prepareStatement(sql)) {
                // seta os valores
                novoStmt.setString(1, ponto.getNome());
                novoStmt.setFloat(2, ponto.getAlt());
                novoStmt.setFloat(3, ponto.getLng());
                novoStmt.setFloat(4, ponto.getLat());

                // executa
                novoStmt.execute();
                novoStmt.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void createTablePonto() {

        String sql = "create table IF NOT EXISTS ponto (\n"
                + "id integer primary key NOT NULL AUTO_INCREMENT,\n"
                + "nome VARCHAR(15) not null unique,\n"
                + "alt float not null,\n"
                + "lng  float not null,\n"
                + "lat  float not null\n"
                + ")";

        try {
            stmt = connection.prepareStatement(sql);
            stmt.execute();
            stmt.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void closeConection() throws SQLException {
        try {
            if (!rs.isClosed()) {
                rs.close();
                stmt.close();
                connection.close();
            } else {
                connection.close();
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
}
