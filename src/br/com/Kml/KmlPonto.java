/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.Kml;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class KmlPonto {

    String nome;
    private String linha, caminho, codigosSql = "";
    private float lng, lat, alt;

    ConexaoBD conexaoAuxiliar = new ConexaoBD();

    public void conversorArquivo(String EndereçoEntrada) throws SQLException {

        try (FileReader arquivo = new FileReader(EndereçoEntrada)) {
            BufferedReader lerArquivo = new BufferedReader(arquivo);
            linha = lerArquivo.readLine();
int i=0;
            while (linha != null) {
                linha = lerArquivo.readLine();

                if (linha.startsWith("</kml>")) {
                    break;
                }

                separa(linha);
                gerarComandosSql(linha);
            i++;
            } System.out.println("Numero de pontos: "+i);

        } catch (IOException endereco) {
            JOptionPane.showMessageDialog(null, "Erro na abertura do arquivo! ");
            System.exit(0);
        }
    }

    private void separa(String texto) throws SQLException {

        texto=texto.trim();
        
        if (texto.endsWith("</name>") == true && texto.length() > 12) {

            this.nome = texto.replace("</name>", "");
            this.nome = this.nome.replace("<name>", "");
           
        }

        if (texto.endsWith("</coordinates>") == true && texto.length() > 12) {
            String coordenadas;
            coordenadas = texto.replace(",", " ");
            coordenadas = coordenadas.replace("<", " ");
            coordenadas = coordenadas.replace(">", " ");
            String[] cord = coordenadas.split(" ");
            this.lng = Float.parseFloat(cord[2]);
            this.lat = Float.parseFloat(cord[3]);
            this.alt = Float.parseFloat(cord[4]);
        }

    }

    private void gerarComandosSql(String Texto) {

        if (Texto.endsWith("</coordinates>") == true) {
            String sql = ("insert into ponto(name,lat,lng,alt)values ('" + nome + "'," + lat + "," + lng + "," + alt + ");");
            codigosSql += " \n " + sql;
        }
    }

    private String enderecoSaida(String Caminho) {
        this.caminho = Caminho + "\\ComandosSql.txt";
        return this.caminho;
    }

    public void imprimirDados(String Caminho)
            throws FileNotFoundException, IOException {

        OutputStream os = new FileOutputStream(enderecoSaida(Caminho));
        OutputStreamWriter osw = new OutputStreamWriter(os);
        BufferedWriter bw = new BufferedWriter(osw);
        bw.write(codigosSql);
        bw.newLine();
        bw.close();

    }

}
