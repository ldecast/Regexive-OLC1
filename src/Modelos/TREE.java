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
public class TREE {
    
    public Stack<Siguiente> siguientes = new Stack<>();
    public Stack<Estado> estados = new Stack<>();
    public Stack<Estado> cerraduras = new Stack<>();
    public int count = 0;
    NodoTree raiz;

    public TREE(NodoTree raiz) {
        this.raiz = raiz;
        this.raiz.first();
        this.raiz.last();
        next_table(this.raiz);
        transition_table(this.raiz);
        generate_AFD();
    }
    
    private void next_table(NodoTree root) {
        if (root.izq == null && root.der == null) {
            return;
        } else {
            if (".".equals(root.valor)) {
                for (int i = 0; i < root.izq.ultimos.size(); i++) {
                    for (int j = 0; j < root.der.primeros.size(); j++) {
                        siguientes.add(new Siguiente(root.izq.ultimos.elementAt(i), root.der.primeros.elementAt(j)));
                    }
                }
            }
            if ("*".equals(root.valor) || "+".equals(root.valor)) {
                for (int i = 0; i < root.izq.ultimos.size(); i++) {
                    for (int j = 0; j < root.izq.primeros.size(); j++) {
                        siguientes.add(new Siguiente(root.izq.ultimos.elementAt(i), root.izq.primeros.elementAt(j)));
                    }
                }
            }
            if (root.izq != null)
                next_table(root.izq);
            if (root.der != null)
                next_table(root.der);
        }
    }
    
    private void transition_table(NodoTree root) {
        if ("#".equals(root.der.valor)) {
            estados.add(new Estado(count, root.primeros));
            count++;
        } else {
            Estado tmp;
            Siguiente aux;
            for (int i = 0; i < siguientes.size(); i++) {
                aux = siguientes.elementAt(i);
                tmp = new Estado(count, aux.siguientes);
                if (!estados.contains(tmp)) {
                    estados.add(tmp);
                    count++;
                }
                else{
                    cerraduras.add(tmp);
                }
            }
            if (root.izq != null)
                transition_table(root.izq);
            if (root.der != null)
                transition_table(root.der);
        }
    }
    
    private void generate_AFD(){
        AFD automata = new AFD();
        String estado, transicion, cerradura;
        for (int i = 0; i < estados.size(); i++) {
            estado = "S" + estados.elementAt(i).estadoActual;
            transicion = findValue(raiz, estados.elementAt(i).estadoActual);
            automata.nodos.add(new NodoAFD(estado, transicion));
        }
        for (int i = 0; i < cerraduras.size(); i++) {
            cerradura = findValue(raiz, cerraduras.elementAt(i).estadoActual);
            automata.cerraduras.add(cerradura);
        }
        automata.getDot();
        System.out.println(siguientes.toString());
        System.out.println(estados.toString());
        System.out.println(cerraduras.toString());
    }
    
    private String findValue(NodoTree root, int id){
        if (root.identificador == id) {
            return root.valor;
        }
        else{
            if (root.izq != null)
                findValue(root.izq, id);
            if (root.der != null)
                findValue(root.der, id);
        }
        return "";
    }
    
}

class Estado {

    public int estadoActual;
    public Stack<Integer> estadosSiguientes = new Stack<>();

    public Estado(int estadoActual, Stack<Integer> estadosSiguientes) {
        this.estadoActual = estadoActual;
        this.estadosSiguientes = estadosSiguientes;
    }

}

class Siguiente {
    
    public int identificador, siguiente;
    public Stack<Integer> siguientes = new Stack<>();
    public String valor;

    public Siguiente(int identificador, int siguiente) {
        this.identificador = identificador;
        this.siguiente = siguiente;
        insertNext(siguiente);
    }
    
    private void insertNext(int next){
        siguientes.add(next);
    }
    
}
