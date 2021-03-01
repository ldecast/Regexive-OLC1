/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelos;

import Analizadores.parser;
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

    public NodoTree(int identificador, String valor, NodoTree izq, NodoTree der){
        this.identificador = identificador;
        this.valor = valor.trim();
        this.izq = izq;
        this.der = der;
        this.anulable = isVoidable();
        first();
        last();
//        System.out.println(this.valor);
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
    
    private void first(){
        if (izq == null && der == null) {
            if (valor=="#")
                primeros.add(parser.aux);
            else
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
        //System.out.println(primeros.toString()+"primeros"+this.valor);
    }
    
    private void last(){
        if (izq == null && der == null) {
            if (valor=="#")
                ultimos.add(parser.aux);
            else
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
        //System.out.println(ultimos.toString()+"ultimos"+this.valor);
    }
 
    
    public String getDot(int numeral){
        String content = "";
        String n_a, id, first, last;
        if(anulable) n_a = "A"; else n_a = "N";
        if(valor=="#"){
            id = String.valueOf(numeral); first=last="["+String.valueOf(numeral)+"]";
        } 
        else{
            id = String.valueOf(identificador); first=primeros.toString(); last=ultimos.toString();
        }
        try {
            if (der.valor == "#") {
                last = "["+String.valueOf(numeral)+"]";
            }
        } catch (Exception e) {
        }
        
        if (izq == null && der == null) {
            content = "nodo" + identificador + " [ label =<\n"
                    + "<TABLE BGCOLOR=\"#48D1CC\" BORDER=\"2\" STYLE=\"ROUNDED\" COLOR=\"BLACK\" CELLBORDER=\"1\">\n"
                    + "<TR>\n<TD BORDER=\"0\" COLSPAN=\"2\" STYLE=\"ROUNDED\" HEIGHT=\"25\">"+valor+"</TD>\n"
                    + "</TR>\n<TR>\n"
                    + "<TD BORDER=\"0\" COLSPAN=\"1\" STYLE=\"ROUNDED\" WIDTH=\"75\" HEIGHT=\"25\">"+first+"</TD>\n"
                    + "<TD BORDER=\"0\" COLSPAN=\"1\" STYLE=\"ROUNDED\" WIDTH=\"75\" HEIGHT=\"25\">"+last+"</TD>\n"
                    + "</TR>\n<TR>\n"
                    + "<TD BORDER=\"0\" COLSPAN=\"2\" STYLE=\"ROUNDED\" HEIGHT=\"25\"><B>"+id+"</B></TD>\n</TR>"
                    + "</TABLE>> xlabel=<<font color=\"Crimson\">"+n_a+"</font>>];\n";
        } else {
            content = "nodo" + identificador + " [ label =<\n"
                    + "<TABLE BGCOLOR=\"#48D1CC\" BORDER=\"2\" STYLE=\"ROUNDED\" COLOR=\"BLACK\" CELLBORDER=\"1\">\n"
                    + "<TR>\n<TD BORDER=\"0\" COLSPAN=\"2\" STYLE=\"ROUNDED\" HEIGHT=\"25\">"+valor+"</TD>\n"
                    + "</TR>\n<TR>\n"
                    + "<TD BORDER=\"0\" COLSPAN=\"1\" STYLE=\"ROUNDED\" WIDTH=\"75\" HEIGHT=\"25\">"+first+"</TD>\n"
                    + "<TD BORDER=\"0\" COLSPAN=\"1\" STYLE=\"ROUNDED\" WIDTH=\"75\" HEIGHT=\"25\">"+last+"</TD>\n"
                    + "</TR>\n"
                    + "</TABLE>> xlabel=<<font color=\"Crimson\">"+n_a+"</font>>];\n";
        }
        if (izq != null) {
            content = content + izq.getDot(numeral)
                    + "nodo" + identificador + "->nodo" + izq.identificador + "\n";
        }
        if (der != null) {
            content = content + der.getDot(numeral)
                    + "nodo" + identificador + "->nodo" + der.identificador + "\n";
        }
        return content;
    }
    
}
