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
public class NodoTree{
    
    public int identificador;
    public Stack<Integer> primeros = new Stack<>();
    public Stack<Integer> ultimos = new Stack<>();
    public boolean anulable;
    public String valor;
    public NodoTree izq, der;

    public NodoTree(int identificador, String valor, NodoTree izq, NodoTree der) {
        this.identificador = identificador;
        this.valor = valor;
        this.izq = izq;
        this.der = der;
        this.anulable = isVoidable();
//        first();
//        last();
    }

    private boolean isVoidable(){
        if (izq == null && der == null) {
            return false;
        }
        if ("*".equals(valor) || "?".equals(valor)) {
            return true;
        }
        if ("+".equals(valor)) {
            return izq.anulable;
        }
        if ("|".equals(valor)) {
            return izq.anulable || der.anulable;
        }
        if (".".equals(valor)){
            return izq.anulable && der.anulable;
        }
        return false;
    }
    
    public void first(){
        if (izq == null && der == null) {
            primeros.add(identificador);
        }
        else if ("*".equals(valor) || "+".equals(valor) || "?".equals(valor)) {
            for (int i = 0; i < izq.primeros.size(); i++) {
                primeros.add(izq.primeros.elementAt(i));
            }
        }
        else if ("|".equals(valor)) {
            for (int i = 0; i < izq.primeros.size(); i++) {
                primeros.add(izq.primeros.elementAt(i));
            }
            for (int i = 0; i < der.primeros.size(); i++) {
                primeros.add(der.primeros.elementAt(i));
            }
        }
        else if (".".equals(valor)) {
            for (int i = 0; i < izq.primeros.size(); i++) {
                primeros.add(izq.primeros.elementAt(i));
            }
            if (izq.anulable) {
                for (int i = 0; i < der.primeros.size(); i++) {
                    primeros.add(der.primeros.elementAt(i));
                }
            }
        }
    }
    
    public void last(){
        if (izq == null && der == null) {
            ultimos.add(identificador);
        }
        else if ("*".equals(valor) || "+".equals(valor) || "?".equals(valor)) {
            for (int i = 0; i < izq.ultimos.size(); i++) {
                ultimos.add(izq.ultimos.elementAt(i));
            }
        }
        else if ("|".equals(valor)) {
            for (int i = 0; i < izq.ultimos.size(); i++) {
                ultimos.add(izq.ultimos.elementAt(i));
            }
            for (int i = 0; i < der.ultimos.size(); i++) {
                ultimos.add(der.ultimos.elementAt(i));
            }
        }
        else if (".".equals(valor)) {
            if (der.anulable) {
                for (int i = 0; i < izq.ultimos.size(); i++) {
                    ultimos.add(izq.ultimos.elementAt(i));
                }
            }
            for (int i = 0; i < der.ultimos.size(); i++) {
                ultimos.add(der.ultimos.elementAt(i));
            }
        }
    }
 
    
    public String getDot(){
        String content = "";
//        if (izq == null && der == null) {
//            content = "nodo" + id + " [ label =\"" + valor+"\"];\n";
//        } else {
//            content = "nodo" + id + " [ label =\"" + valor + "\"];\n";
//        }
//        if (izq != null) {
//            content = content + izq.getCodigoInterno()
//                    + "nodo" + id + "->nodo" + izq.id + "\n";
//        }
//        if (der != null) {
//            content = content + der.getCodigoInterno()
//                    + "nodo" + id + "->nodo" + der.id + "\n";
//        }

        
        return content;
    }
}
