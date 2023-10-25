/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.axocode.service.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.axocode.dao.protocol;

/**
 *
 * @author chump
 */
public class protocolService {
    
    
    public static class Resultados {
            public StringBuilder resultado1;
            

            public Resultados(StringBuilder resultado1) {
                this.resultado1 = resultado1;
            }
        }
    
    public static Resultados eco(protocol protocol) {
    String txt = protocol.getTxt(); 

    String reglasLetras = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    String reglasNumeros = "1234567890";

    String sustitucionLetras = "EOPQSRTUVWXYZABCDFGHIJKLMN";
    String sustitucionNumeros = "0497235681";

    
    StringBuilder resultado1 = new StringBuilder();
    for (char c : txt.toCharArray()) {
        if (Character.isLetter(c)) {
            int index = reglasLetras.indexOf(Character.toUpperCase(c));
            if (index != -1) {
                char letraCodificada = sustitucionLetras.charAt(index);
                resultado1.append(Character.isLowerCase(c) ? Character.toLowerCase(letraCodificada) : letraCodificada);
            } else {
                resultado1.append(c); 
            }
        } else if (Character.isDigit(c)) {
            int index = reglasNumeros.indexOf(c);
            if (index != -1) {
                char numeroCodificado = sustitucionNumeros.charAt(index);
                resultado1.append(numeroCodificado);
            } else {
                resultado1.append(c); 
            }
        } else {
            resultado1.append(c); 
        }
    }

    

    return new Resultados(resultado1);
    }
    
    public static Resultados deco(protocol protocol) {
    String txt = protocol.getTxt();

    
    String reglasLetras = "EOPQSRTUVWXYZABCDFGHIJKLMN";
    String reglasNumeros = "0497235681";

    String sustitucionLetras = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    String sustitucionNumeros = "1234567890";

    StringBuilder resultado1 = new StringBuilder();
    for (char c : txt.toCharArray()) {
        if (Character.isLetter(c)) {
            int index = reglasLetras.indexOf(Character.toUpperCase(c));
            if (index != -1) {
                char letraDecodificada = sustitucionLetras.charAt(index);
                resultado1.append(Character.isLowerCase(c) ? Character.toLowerCase(letraDecodificada) : letraDecodificada);
            } else {
                resultado1.append(c); 
            }
        } else if (Character.isDigit(c)) {
            int index = reglasNumeros.indexOf(c);
            if (index != -1) {
                char numeroDecodificado = sustitucionNumeros.charAt(index);
                resultado1.append(numeroDecodificado);
            } else {
                resultado1.append(c); 
            }
        } else {
            resultado1.append(c); 
        }
    }

    return new Resultados(resultado1);
}
}

