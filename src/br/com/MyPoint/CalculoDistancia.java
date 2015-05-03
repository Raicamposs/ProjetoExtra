/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.MyPoint;

/**
 *
 * @author Raiane Campos
 */
public class CalculoDistancia {

//    Distancia = fatorKM *ACOS(COS(PI()*(90-LatB)/180)*COS((90-LatA)*PI()/180)+
//            SEN((90-LatB)*PI()/180)*SEN((90-LatA)*PI()/180)*COS(( LngA - LngB)*PI()/180))
    public static double distancia(double latitudeInicial, double longitudeInicial,
            double latitudeFinal, double longitudeFinal) {
        double d = 0;
        int raioTerra = 6371;
        double PI = Math.PI;
        int valorMetade = 90;
        int valorInteiro = 180;

        double v1 = Math.cos(PI * (valorMetade - latitudeFinal) / valorInteiro);
        double v2 = Math.cos((valorMetade - latitudeInicial) * PI / valorInteiro);
        double v3 = Math.sin((valorMetade - latitudeFinal) * PI / valorInteiro);
        double v4 = Math.sin((valorMetade - latitudeInicial) * PI / valorInteiro);
        double v5 = Math.cos((longitudeInicial - longitudeFinal) * PI / valorInteiro);

        double result = raioTerra * Math.acos((v1 * v2) + (v3 * v4 * v5));

        return d = result;

    }
}
