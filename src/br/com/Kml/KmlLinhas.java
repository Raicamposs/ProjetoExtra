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

/**
 *
 * @author Raiane Campos
 */
public class KmlLinhas {

    private String nome;
    private String linha, caminho, codigosSql = "";
    private float lng, lat, alt;

    ConexaoBD conexaoAuxiliar = new ConexaoBD();

    void conversorArquivo(String EndereçoEntrada) throws SQLException {

        try (FileReader arquivo = new FileReader(EndereçoEntrada)) {

            BufferedReader lerArquivo = new BufferedReader(arquivo);
            linha = lerArquivo.readLine();

            int i = 0;

            while (linha != null) {

                linha = lerArquivo.readLine();
                
  
                if (linha.endsWith("<Placemark>")) {
                    i++;
                    while (!linha.endsWith("</Placemark>")) {
                       
                        linha = lerArquivo.readLine();

                        if (linha.endsWith("</name>") == true && linha.length() > 12) {
                            separaName(linha); System.out.println(nome);
                        } else {
                            if (linha.endsWith("<coordinates>") && linha.length() > 12 && !linha.endsWith("</coordinates>")) {
                                linha = lerArquivo.readLine();
                                separaCordenadas(linha.trim());
                            }
                        }

                    }

                }
                if (linha.startsWith("</kml>")) {
                    break;
                } 
            }
            
            System.out.println("Numero de linhas = " + i);

        } catch (IOException endereco) {
            JOptionPane.showMessageDialog(null, "Erro na abertura do arquivo! ");
            System.exit(0);
        }
    }
    
     

    private void separaName(String texto) throws SQLException {

        if (texto.endsWith("</name>") == true && texto.length() > 12) {
            texto = texto.replaceAll("</name>", "");
            texto = texto.replaceAll("<name>", "");
            this.nome = texto.trim();
          
        }
    }

    private void separaCordenadas(String texto) throws IOException {

        String[] grupo;
        grupo = texto.split(" ");

        for (int j = 0; j < grupo.length; j++) {

            String coordenadas = grupo[j].replace(",", " ");
            String[] cord = coordenadas.split(" ");

            this.lng = Float.parseFloat(cord[0]);
            this.lat = Float.parseFloat(cord[1]);
            this.alt = Float.parseFloat(cord[2]);

            gerarComandosSql();

        }

        imprimirDados("C:\\Projeto kml\\sqls");
        codigosSql = "";
    }

    private void gerarComandosSql() {
        String sql = ("insert into " + nome + "(lat,lng,alt values (" + lat + "," + lng + "," + alt + ");");
        codigosSql += " \n " + sql;
        System.out.println(codigosSql);

    }

    private String enderecoSaida(String Caminho, String nome) {
        this.caminho = Caminho + "\\" + nome + ".txt";
        return this.caminho;
    }

    private void imprimirDados(String Caminho)
            throws FileNotFoundException, IOException {

        OutputStream os = new FileOutputStream(enderecoSaida(Caminho, this.nome));
        OutputStreamWriter osw = new OutputStreamWriter(os);
        BufferedWriter bw = new BufferedWriter(osw);
        bw.write(codigosSql);
        bw.newLine();
        bw.close();

    }

}
