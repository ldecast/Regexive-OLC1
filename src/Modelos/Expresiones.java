/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelos;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Stack;

/**
 *
 * @author luisd
 */
public class Expresiones {
    
    //public static ArrayList<Expresiones> lista_expresiones = new ArrayList<Expresiones>();
    public static Stack<String> stack = new Stack<>();
    // private ArrayList<Conjunto> conjuntos = new ArrayList<Conjunto>(); //agregar por busqueda del nombre de la global de conjuntos

    public Expresiones() {
    }
    
    public static void setName(String x){
        stack.add(0, x);
    }
    public static void add(String x){
        stack.add(x);
        
    }
    
    public static void loadExp() throws IOException{
        String name = stack.remove(0);
//        AFND th = new AFND(name, stack);
        //AE tree = mew AE(stack.elementAt(0), stack);
        stack.clear();
    }
    
}


class Conjunto{
    public String nombre, rango, tipo, notacion;
    public char caracteres[];
    public static ArrayList<Conjunto> lista_conjuntos = new ArrayList<Conjunto>();

    public Conjunto(String nombre, String rango, String tipo, String notacion) {
        this.nombre = nombre.trim();
        this.rango = rango.trim();
        this.tipo = tipo;
        this.notacion = notacion;
        estandarizar();
    }

    private void estandarizar() {
        if ("intervalo".equals(notacion)) {
            int inicial = (int) rango.charAt(0);
            int fin = (int) rango.charAt(rango.length() - 1);
            if ("letra".equals(tipo) || "digito".equals(tipo)) {
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
            String[] aux = rango.split(",");
            caracteres = new char[aux.length];
            for (int i = 0; i < aux.length; i++) {
                caracteres[i] = aux[i].charAt(0);
            }
        }
    }
    
}
