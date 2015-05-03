/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ConexaoBD;

import br.com.Objetos.Linha;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Raiane
 */
public class LinhaDao {

    // a conexão com o banco de dados
    private final Connection connection;
    private PreparedStatement stmt;
    private ResultSet rs;

    public LinhaDao() {
        this.connection = new ConnectionFactory().getConnection("linhas", "299071", "root");
    }

    public void adicionaLinha(Linha linha) {

        String sql = "insert into " + linha.getNome()
                + " (alt, lng, lat) "
                + "values (?,?,?)";

        try {
            // seta os valores
            try ( // prepared statement para inserção
                    PreparedStatement novoStmt = connection.prepareStatement(sql)) {
                // seta os valores
                novoStmt.setFloat(1, linha.getAlt());
                novoStmt.setFloat(2, linha.getLng());
                novoStmt.setFloat(3, linha.getLat());

                // executa
                novoStmt.execute();
                novoStmt.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void createTableLinha(Linha linha) {

        String sql = "create table IF NOT EXISTS " + linha.getNome() + " (\n"
                + "id integer primary key NOT NULL AUTO_INCREMENT,\n"
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

    public void closeConection() throws SQLException {
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
