/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.Teste;

import br.com.Kml.KmlLinhas;
import br.com.Kml.KmlPonto;
import java.io.IOException;
import java.sql.SQLException;

/**
 *
 * @author Raiane Campos
 */
public class ProjetoExtra {

   public static void main(String[] args) throws SQLException, IOException {
////
       kmlPonto();
//       banco();
//        kmllinha();
//

    }

    public static void kmlPonto() throws SQLException, IOException {
        KmlPonto kml = new KmlPonto();
        kml.conversorArquivo("C:\\Users\\Raiane\\Dropbox\\Projeto Rotas de Onibus e Mapas Colaborativos\\Linhas e Pontos de Onibus (KML)\\2014 01 30 BASE GERAL.kml");
      

    }

    public static void kmllinha() throws SQLException, IOException {
        KmlLinhas kml = new KmlLinhas();
        kml.conversorArquivo("C:\\Users\\Raiane\\Dropbox\\Projeto Rotas de Onibus e Mapas Colaborativos\\Linhas e Pontos de Onibus (KML)\\02_04_2014_redetranscol.kml");

    }
//  public static void banco () throws SQLException {
//  
//     ConexaoBD conexao = new ConexaoBD();
//        conexao.conecta();
////        String sql = "insert into ponto(name,lat,lng,alt)values ('10008',-20.261679,-40.263702,11.42126)";
////       int done=conexao.statement.executeUpdate(sql);
//        conexao.executeSQL("select*from ponto");
//        while (conexao.resultset.next()) {
//            System.out.println("Id = " + conexao.resultset.getString("id"));
//            System.out.println("Ponto = " + conexao.resultset.getString("name"));
//            System.out.println("Longitude = " + conexao.resultset.getString("lng"));
//            System.out.println("Latitude = " + conexao.resultset.getString("lat"));
//            System.out.println("Altura = " + conexao.resultset.getString("alt"));
//        }
//        conexao.desconecta();
//  
//  }
//
    
}
