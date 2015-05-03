/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.Kml;

import br.com.Objetos.Linha;
import br.com.ConexaoBD.LinhaDao;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Raiane Campos
 */
public class KmlLinhas {

    private String nome;
    private String linha;
    private float lng, lat, alt;
    private Linha novaLinha;
    private final LinhaDao con = new LinhaDao();

    public void conversorArquivo(String EndereçoEntrada) throws SQLException {

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
                            separaName(linha);
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

            System.out.println("Numero de linhas Cadastradas = " + i);

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
        novaLinha = new Linha(nome);
        con.createTableLinha(novaLinha);
    }

    private void separaCordenadas(String texto) throws IOException {

        String[] grupo;
        grupo = texto.split(" ");

        for (String grupo1 : grupo) {
            String coordenadas = grupo1.replace(",", " ");
            String[] cord = coordenadas.split(" ");
            this.lng = Float.parseFloat(cord[0]);
            this.lat = Float.parseFloat(cord[1]);
            this.alt = Float.parseFloat(cord[2]);
            novaLinha = new Linha(lng, lat, alt, nome);
            con.adicionaLinha(novaLinha);
        }

    }

}
