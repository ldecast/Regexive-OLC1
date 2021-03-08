/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelos;

import java.util.ArrayList;

/**
 *
 * @author luisd
 */
public class Conjunto {

    public String nombre, expr, tipo;
    public char caracteres[];
    public static ArrayList<Conjunto> lista_conjuntos = new ArrayList<Conjunto>();

    public Conjunto(String nombre, String expr) {
        this.nombre = nombre.trim();
        this.nombre = this.nombre.replace("{", "");
        this.nombre = this.nombre.replace("}", "");
        this.expr = expr.trim().substring(3);
        this.tipo = expr.trim().substring(0, 2);
        estandarizar();
    }

    private void estandarizar() {
        if (expr.contains("~")) {
            int inicial = (int) expr.charAt(0);
            int fin = (int) expr.charAt(expr.length() - 1);
            if ("let".equals(tipo) || "dig".equals(tipo)) {
                caracteres = new char[fin - inicial + 1];
                int aux = 0;
                for (int i = inicial; i <= fin; i++) {
                    caracteres[aux] = (char) i;
                    aux++;
                }
            } else {
                int aux = 0;
                for (int i = inicial; i <= fin; i++) {
                    if (i >= 32 && i <= 47 && i >= 58 && i <= 64 && i >= 91 && i <= 96 && i >= 123 && i <= 125) {
                        aux++;
                    }
                }
                caracteres = new char[aux];
                int aux2 = 0;
                for (int i = inicial; i <= fin; i++) {
                    if (i >= 32 && i <= 47 && i >= 58 && i <= 64 && i >= 91 && i <= 96 && i >= 123 && i <= 125) {
                        caracteres[aux2] = (char) i;
                        aux2++;
                    }
                }
            }
        } else {
            String[] aux = expr.split(",");
            caracteres = new char[aux.length];
            for (int i = 0; i < aux.length; i++) {
                caracteres[i] = aux[i].charAt(0);
            }
        }
    }
}
