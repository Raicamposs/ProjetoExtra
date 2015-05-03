/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.Objetos;

/**
 *
 * @author Raiane
 */
public class Ponto {

    private String linha, nome;
    private float lng, lat, alt;

    public Ponto(float lng, float lat, float alt, String nome) {
        this.nome = nome;
        this.alt = alt;
        this.lat = lat;
        this.lng = lng;
    }

    public String getNome() {
        return nome;
    }

    public float getAlt() {
        return alt;
    }

    public float getLng() {
        return lng;
    }

    public float getLat() {
        return lat;
    }

}
