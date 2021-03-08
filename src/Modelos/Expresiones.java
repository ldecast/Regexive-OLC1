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
    
    public static Stack<String> stack = new Stack<>();
    public static ArrayList<Conjunto> conjuntos = new ArrayList<>(); //agregar por busqueda del nombre de la global de conjuntos
    public static Stack<String> tmp = new Stack<>();

    public Expresiones() {
    }
    
    public static void setName(String x){
        stack.add(0, x);
    }
    public static void add(String x){
        stack.add(x.trim());
    }
    
    public static void addTmp(String x){
        tmp.add(x.trim());
    }
    
    public static Stack<String> getExpr(){
        Stack<String> x = stack;
        stack.clear();
        return x;
    }
    
    public static ArrayList<Conjunto> getConjs(){
        ArrayList<Conjunto> x = conjuntos;
        conjuntos.clear();
        return x;
    }
    
    public static void addConj(Conjunto x){
        conjuntos.add(x);
    }
    
    public static void loadExp() throws IOException{
        String name = stack.remove(0);
        AFND th = new AFND(name, stack, tmp);
        stack.clear();
        tmp.clear();
    }
    
}
