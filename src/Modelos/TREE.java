/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelos;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Stack;
import Analizadores.parser;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
/**
 *
 * @author luisd
 */
public class TREE {
    
    public Stack<Siguiente> siguientes = new Stack<>();
    public Stack<Estado> estados = new Stack<>();
    public Stack<Estado> cerraduras = new Stack<>();
    public static Stack<String> tmpValores = new Stack<>();
    public static Stack<Stack<Integer>> tmpTransiciones = new Stack<>();
    public static Stack<Stack<Integer>> tmpCerraduras = new Stack<>();
    public int count = 0;
    public static int num;
    NodoTree raiz;
    String nombre;

    public TREE(NodoTree raiz, String nombre) throws IOException {
        parser.id = 1;
        num = parser.aux;
        parser.aux  = 1;
        this.raiz = raiz;
        this.nombre = nombre;
        next_table(this.raiz);
        transition_table();
//        toDot("ARBOLES");
//        toDot("SIGUIENTES");
//        toDot("TRANSICIONES");
//        toDot("AFD");
        generate_AFD();
    }
    
    private void next_table(NodoTree root) {
        if (root.izq == null && root.der == null) {
            return;
        } else {
            if (".".equals(root.valor)) {
                if (!checkNext(root.izq.ultimos, root.der.primeros)) {
                    for (int i = 0; i < root.izq.ultimos.size(); i++) {
                        if ("#".equals(root.der.valor)) {
                            Stack<Integer> tempNum = new Stack<>();
                            tempNum.add(num);
                            siguientes.add(new Siguiente(root.izq.ultimos.elementAt(i), tempNum));
                            tempNum = null;
                        }
                        else
                            siguientes.add(new Siguiente(root.izq.ultimos.elementAt(i), root.der.primeros));
                    }
                }
            }
            if ("*".equals(root.valor) || "+".equals(root.valor)) {
                if (!checkNext(root.izq.ultimos, root.izq.primeros)) {
                    for (int i = 0; i < root.izq.ultimos.size(); i++) {
                        siguientes.add(new Siguiente(root.izq.ultimos.elementAt(i), root.izq.primeros));
                    }
                }
            }
            if (root.izq != null)
                next_table(root.izq);
            if (root.der != null)
                next_table(root.der);
        }
    }
    
    private boolean checkNext(Stack<Integer> id, Stack<Integer> sigs){
        boolean ban = false;
        for (int i = 0; i < id.size(); i++) {
            Siguiente tmp = searchSig(id.elementAt(i));
            if (tmp!=null) {
                for (int j = 0; j < sigs.size(); j++) {
                    tmp.siguientes.add(sigs.elementAt(j));
                }
                ban = true;
            }
        }
        return ban;
    }
    
    private Siguiente searchSig(int id){
        for (int i = 0; i < siguientes.size(); i++) {
            if (siguientes.elementAt(i).identificador == id) {
                return siguientes.elementAt(i);
            }
        }
        return null;
    }
    
    public static ArrayList<Integer> sigsTmp = new ArrayList<Integer>();
    int c = 0;
    private void transition_table() {
        Estado estadoInicial = new Estado(c, new Siguiente(num, raiz.primeros));
        estados.add(estadoInicial);
        sigsTmp.addAll(estadoInicial.siguiente.siguientes);
        c++;

        while (!sigsTmp.isEmpty()) {
            siguienteDe();
//            System.out.println("enciclado");
        }
        
        recurTemp.clear();
    }
    
    public static Stack<Stack<Integer>> recurTemp = new Stack<Stack<Integer>>();
    private void siguienteDe(){
        Siguiente aux;
        for (int i = 0; i < sigsTmp.size(); i++) {
            int id = sigsTmp.remove(i); //el id al que le sacare los siguientes
            aux = searchSig(id); //los siguientes de ese id
            try {
                if (recurTemp.contains(aux.siguientes)) {
                    System.out.println("CERRADURA NUEVA"+aux.identificador+aux.siguientes.toString());
                    cerraduras.add(new Estado(c-1, aux));
//                c++;
            } else {
                Estado e = new Estado(c, aux);
                estados.add(e);
                recurTemp.add(aux.siguientes);
                    for (int j = 0; j < aux.siguientes.size(); j++) {
                        sigsTmp.add(aux.siguientes.elementAt(j));
                    }
                c++;
            }
            } catch (Exception e) {
            }
            
        }
    }
    
    private void generate_AFD() throws IOException{
        AFD automata = new AFD(nombre);
        String estado, transicion, cerradura;
        for (int i = 0; i < estados.size(); i++) {
            for (int j = 0; j < estados.elementAt(i).siguiente.siguientes.size(); j++) {
                findValue(raiz, estados.elementAt(i).siguiente.siguientes.elementAt(j));
            }
            estado = "S" + estados.elementAt(i).estadoActual;
            transicion = tmpValores.remove(0);
            automata.nodos.add(new NodoAFD(estado, transicion));
        }
//        for (int i = 0; i < tmpValores.size(); i++) {
//            estado = "S" + i;
//            transicion = tmpValores.elementAt(i);
//            automata.nodos.add(new NodoAFD(estado, transicion));
//        }
//        System.out.println(tmpValores.toString()+"AUN HABIAN");
        tmpValores.clear();
        
        for (int i = 0; i < cerraduras.size(); i++) {
//            for (int j = 0; j < cerraduras.elementAt(i).siguiente.siguientes.size(); j++) {
                findValue(raiz, cerraduras.elementAt(i).siguiente.identificador);
//            }
            estado = "S" + cerraduras.elementAt(i).estadoActual;
            transicion = tmpValores.lastElement();
            tmpValores.remove(transicion);
            automata.cerraduras.add(estado+"->"+estado+"[label=<<font color=\"Crimson\">"+ transicion +"</font>> "
                    + "fontname=\"Century Gothic\" fontsize=\"12\"];\n");
        }
//        for (int i = 0; i < tmpValores.size(); i++) {
//            estado = "S" + estados.elementAt(i).estadoActual;
//            transicion = tmpValores.pop();
//            automata.cerraduras.add(estado+"->"+estado+"[label=<<font color=\"Crimson\">"+ transicion +"</font>> "
//                    + "fontname=\"Century Gothic\" fontsize=\"12\"];\n");
//        }
        System.out.println(tmpValores.toString()+"AUN HABIAN");
        tmpValores.clear();
//        
        
//        for (int i = 0; i < cerraduras.size(); i++) {
//            for (int j = 0; j < cerraduras.elementAt(i).estadosSiguientes.size(); j++) {
//                findValue(raiz, cerraduras.elementAt(i).estadosSiguientes.elementAt(j));
//            }
//        }
//        for (int i = 0; i < tmpValores.size(); i++) {
//            estado = "S" + i;
//            cerradura = tmpValores.elementAt(i);
//            automata.nodos.add(new NodoAFD(estado, transicion));
//        }
//        for (int i = 0; i < cerraduras.size(); i++) {
//            cerradura = findValue(raiz, cerraduras.elementAt(i).estadoActual);
//            automata.cerraduras.add(cerradura);
//        }
        automata.getDot();
    }
    
    public static void findValue(NodoTree root, int id) {
        if (root.identificador == id) {
            tmpValores.add(root.valor);
        } else {
            if (root.izq != null) {
                findValue(root.izq, id);
            }
            if (root.der != null) {
                findValue(root.der, id);
            }
        }
    }
    
    private int findId(NodoTree root, String val){
        if (root.valor == val) {
            return root.identificador;
        }
        else{
            if (root.izq != null)
                findId(root.izq, val);
            if (root.der != null)
                findId(root.der, val);
        }
        return 0;
    }
    
    private void toDot(String tipo) throws IOException {
        String contenido = "";
        contenido += "digraph D {\n"
        + "    graph[bgcolor=\"#141D26\"]\n"
        + "    rankdir=\"TB\";\n"
        + "    node [shape=plaintext margin=\"0\" fontname= \"Century Gothic\" fontsize=\"14\"];\n"
        + "    edge[color=\"#F5FFFA\" penwidth=\"1.5\"];\n";
        switch (tipo){
            case "ARBOLES":
                contenido += raiz.getDot(num);
                break;
            case "SIGUIENTES":
                contenido += dotNextTable();
                break;
            case "TRANSICIONES":
                contenido += dotTransition();
        }
        
        contenido += "\n}";
        
        File archivo = new File("src\\"+tipo+"_201902238\\"+nombre+"_"+GUI.Interfaz.fname+".dot");
        try (FileWriter escritor = new FileWriter(archivo)) {
            escritor.write(contenido);
        }
        GUI.Interfaz.addToTree(nombre,3);
        generateSVG(tipo);
    }
    
    private void generateSVG(String tipo) throws IOException {
    	String dotPath = "C:\\Program Files (x86)\\Graphviz2.38\\bin\\dot.exe";

    	String fileInputPath = "src\\"+tipo+"_201902238\\"+nombre+"_"+GUI.Interfaz.fname+".dot";
 
    	String fileOutputPath = "src\\"+tipo+"_201902238\\"+nombre+"_"+GUI.Interfaz.fname+".svg";

    	String tParam = "-Tsvg";

    	String tOParam = "-o";

    	String[] cmd = new String[5];
    	cmd[0] = dotPath;
    	cmd[1] = tParam;
    	cmd[2] = fileInputPath;
    	cmd[3] = tOParam;
    	cmd[4] = fileOutputPath;

    	Runtime rt = Runtime.getRuntime();

    	rt.exec(cmd);
    }
    
    private String dotNextTable(){
        String content = "tabla [ label =<\n" +
        "<TABLE BGCOLOR=\"#48D1CC\" WIDTH=\"170\" BORDER=\"3\" STYLE=\"ROUNDED\" COLOR=\"BLACK\" CELLBORDER=\"1\">\n" +
        "<TR>\n" +
        "<TD BORDER=\"0\" COLSPAN=\"2\" STYLE=\"ROUNDED\" HEIGHT=\"25\" WIDTH=\"0\"><B>Hoja</B></TD>\n" +
        "<TD BORDER=\"0\" COLSPAN=\"2\" STYLE=\"ROUNDED\" HEIGHT=\"5\" WIDTH=\"0\"><B>Siguientes</B></TD>\n" +
        "</TR>\n\n";
        
        for (int i = 0; i < siguientes.size(); i++) {
            Siguiente tmp = siguientes.elementAt(i);
            findValue(raiz, tmp.identificador);
            content += tmp.getNextTableDot(tmpValores.pop());
        }
        tmpValores.clear();
        content += "</TABLE>>];";
        return content;
    }
    
    private String dotTransition(){
        String content = "tabla [ label =<\n" +
        "<TABLE BGCOLOR=\"#48D1CC\" WIDTH=\"170\" BORDER=\"3\" STYLE=\"ROUNDED\" COLOR=\"BLACK\" CELLBORDER=\"1\">\n" +
        "<TR>\n" +
        "<TD BORDER=\"0\" COLSPAN=\"1\" HEIGHT=\"25\" WIDTH=\"80\"><B>Estado</B></TD>\n" +
        "<TD BORDER=\"0\" COLSPAN=\"20\" HEIGHT=\"25\" WIDTH=\"0\"><B>Transiciones</B></TD>\n" +
        "</TR>\n";
//        "<TR>\n" +
//        "<TD BORDER=\"1\" COLSPAN=\"1\" HEIGHT=\"10\">S0</TD>\n";
//        for (int i = 0; i < raiz.primeros.size(); i++) {
//            findValue(raiz, raiz.primeros.elementAt(i));
//            content += "<TD BORDER=\"1\" COLSPAN=\"1\" WIDTH=\"30\" HEIGHT=\"10\">{"+ raiz.primeros.elementAt(i) +"}: "+tmpValores.pop()+"</TD>\n";
//        }
//        content += "</TR>\n";
//        tmpValores.clear();
        Estado tmp;
        for (int i = 0; i < estados.size(); i++) {
            tmp = estados.elementAt(i);
            content += tmp.getTransitionDot(raiz, i);
//            System.out.println("estado actual: S"+tmp.estadoActual+"estados siguientes: "+tmp.estadosSiguientes.toString());
        }
        tmpValores.clear();
        content += "<TR>\n" +
        "<TD BORDER=\"0\" COLSPAN=\"2\" STYLE=\"ROUNDED\" HEIGHT=\"5\"></TD>\n" +
        "</TR>\n" +
        "</TABLE>>];";
        return content;
    }
    
}

class Estado {

    public int estadoActual;
    public Siguiente siguiente;

    public Estado(int estadoActual, Siguiente siguiente) {
        this.estadoActual = estadoActual;
        this.siguiente = siguiente;
    }
    
    public String getTransitionDot(NodoTree root, int count) {//</TR> al final de todo el for
        String content = "<TR>\n";
        content += "<TD BORDER=\"1\" COLSPAN=\"1\" HEIGHT=\"10\">S" + count + "</TD>\n";
        for (int i = 0; i < siguiente.siguientes.size(); i++) {
            if (siguiente.siguientes.elementAt(i) == TREE.num) {
                content += "<TD BORDER=\"1\" COLSPAN=\"1\" WIDTH=\"30\" HEIGHT=\"10\">{" + siguiente.siguientes.elementAt(i) + "}: #</TD>\n";
            } else {
                TREE.findValue(root, siguiente.siguientes.elementAt(i));
                content += "<TD BORDER=\"1\" COLSPAN=\"1\" WIDTH=\"30\" HEIGHT=\"10\">{" + siguiente.siguientes.elementAt(i) + "}: " + TREE.tmpValores.pop() + "</TD>\n";
            }
        }
        content += "</TR>\n";
        return content;
    }

}

class Cerradura {
    public int estadoActual;
    
}

class Siguiente {
    
    public int identificador;
    public Stack<Integer> siguientes = new Stack<>();

    public Siguiente(int identificador, Stack<Integer> siguiente) {
        this.identificador = identificador;
        Set<Integer> set = new HashSet<>(siguiente);//sort?
        this.siguientes.addAll(set);
    }
    
    public Stack<Integer> sortstack(Stack<Integer> input) {
        Stack<Integer> tmpStack = new Stack<Integer>();
        while (!input.isEmpty()) {
            int tmp = input.pop();
            while (!tmpStack.isEmpty() && tmpStack.peek() > tmp) {
                input.push(tmpStack.pop());
            }
            tmpStack.push(tmp);
        }
        return tmpStack;
    }

    public String getNextTableDot(String val){
        Set<Integer> set = new HashSet<>(siguientes);
        siguientes.clear();
        siguientes.addAll(set);
        String content = "<TR>\n" +
        "<TD BORDER=\"1\" COLSPAN=\"1\" WIDTH=\"25\" HEIGHT=\"10\">"+ val +"</TD>\n" +
        "<TD BORDER=\"1\" COLSPAN=\"1\"  WIDTH=\"25\" HEIGHT=\"10\">"+ identificador +"</TD>\n" +
        "<TD BORDER=\"0\" COLSPAN=\"1\"  WIDTH=\"10\"></TD>\n" +
        "<TD BORDER=\"1\" COLSPAN=\"1\" WIDTH=\"90\" HEIGHT=\"10\">"+ sortstack(siguientes).toString() +"</TD>\n" +
        "</TR>\n" +
        "<TR>\n" +
        "<TD BORDER=\"0\" COLSPAN=\"2\" STYLE=\"ROUNDED\" HEIGHT=\"5\"></TD>\n" +
        "</TR>\n";
        
        return content;
    }
    
}
