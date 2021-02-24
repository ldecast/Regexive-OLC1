/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelos;

import java.util.Stack;

/**
 *
 * @author luisd
 */
public class AFD {
    
    public Stack<NodoAFD> nodos = new Stack<>();
    public Stack<String> cerraduras = new Stack<>();

    public AFD(){
    }
    
    public void getDot(){
        //construir el grafo
    }
    
}

class NodoAFD{
    String estado, transicion, cerradura;

    public NodoAFD(String estado, String transicion) {
        this.estado = estado;
        this.transicion = transicion;
    }
}
