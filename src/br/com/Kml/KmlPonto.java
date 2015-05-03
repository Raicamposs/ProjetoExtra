/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.Kml;


import br.com.Objetos.Ponto;
import br.com.ConexaoBD.PontoDao;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class KmlPonto {

    String nome;
    private String linha;
    private float lng, lat, alt;
    private Ponto ponto;
    private PontoDao con = new PontoDao();
    int i = 0;

    public void conversorArquivo(String EndereçoEntrada) throws SQLException {

        try (FileReader arquivo = new FileReader(EndereçoEntrada)) {
            BufferedReader lerArquivo = new BufferedReader(arquivo);
            linha = lerArquivo.readLine();
            
            while (linha != null) {
                linha = lerArquivo.readLine();

                if (linha.startsWith("</kml>")) {
                    break;
                }

                separa(linha);
              
            }
            System.out.println("Numero de pontos: " + i);

        } catch (IOException endereco) {
            JOptionPane.showMessageDialog(null, "Erro na abertura do arquivo! ");
            System.exit(0);
        }
    }

    private void separa(String texto) throws SQLException {

        texto = texto.trim();

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
            ponto = new Ponto(lng, lat, alt, nome);
            con.adicionaPonto(ponto);  i++;
        }

    }

}
