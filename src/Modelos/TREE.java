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
        transition_table(this.raiz);
//        toDot("ARBOLES");
//        toDot("SIGUIENTES");
        toDot("TRANSICIONES");
//        toDot("AFD");
        //generate_AFD();
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
    
    private void checkTransition(){
        Stack<Integer> aux;
        for (int i = 0; i < estados.size(); i++) {
            aux = estados.elementAt(i).estadosSiguientes;
            Set<Integer> set = new HashSet<>(aux);
            aux.clear();
            aux.addAll(set);
            if (!tmpTransiciones.contains(aux)) {
                tmpTransiciones.add(aux);
            }
            else{
                tmpCerraduras.add(aux);
            }
        }
//        Set<Integer> set2 = new HashSet<>(sigs);
//        sigs.clear();
//        sigs.addAll(set2);
//        for (int i = 0; i < temp.size(); i++) {
//            Set<Integer> set1 = new HashSet<>(temp.elementAt(i));
//            temp.elementAt(i).clear();
//            temp.elementAt(i).addAll(set1);
//        }
//        return temp.contains(sigs);
    }
    
    private void transition_table(NodoTree root) {
//        try{
//            if ("#".equals(root.der.valor)) {
////                estados.add(new Estado(root.primeros));
////                count++;
//            }
//        } catch(Exception e){}
        //else {
//            Stack <Stack<Integer>> temp = new Stack<>();
//            Estado tmp;
//            Siguiente aux;
//            for (int i = 0; i < siguientes.size(); i++) {
//                aux = siguientes.elementAt(i);
//                tmp = new Estado(aux.siguientes);
//                if (checkTransition(temp, aux.siguientes)) {
////                    System.out.println(aux.siguientes.toString()+"ya estaba"+i);
//                    cerraduras.add(tmp);
//                }
//                else{
//                    System.out.println(aux.siguientes.toString()+"no estaba"+i);
//                    estados.add(tmp);
//                    temp.add(aux.siguientes);
//                }
//            }
            
            Stack<Integer> aux;
        for (int i = 0; i < siguientes.size(); i++) {
            aux = siguientes.elementAt(i).siguientes;
            Set<Integer> set = new HashSet<>(aux);
            aux.clear();
            aux.addAll(set);
            if (!tmpTransiciones.contains(aux)) {
                tmpTransiciones.add(aux);
            }
            else{
                tmpCerraduras.add(aux);
            }
        }
            
//            if (root.izq != null)
//                transition_table(root.izq);
//            if (root.der != null)
//                transition_table(root.der);
        //}
    }
    
    private void generate_AFD(){
        AFD automata = new AFD();
        String estado, transicion, cerradura;
//        for (int i = 0; i < estados.size(); i++) {
//            estado = "S" + estados.elementAt(i).estadoActual;
//            transicion = findValue(raiz, estados.elementAt(i).estadoActual);
//            automata.nodos.add(new NodoAFD(estado, transicion));
//        }
//        for (int i = 0; i < cerraduras.size(); i++) {
//            cerradura = findValue(raiz, cerraduras.elementAt(i).estadoActual);
//            automata.cerraduras.add(cerradura);
//        }
        automata.getDot();
        //System.out.println(siguientes.elementAt(0).siguientes.toString()+"sig");
        //System.out.println(estados.elementAt(0).toString()+"estados");
        //System.out.println(cerraduras.elementAt(0).estadosSiguientes.toString());
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
        "</TR>" +
        "<TR>\n" +
        "<TD BORDER=\"1\" COLSPAN=\"1\" HEIGHT=\"10\">S0</TD>\n";
        for (int i = 0; i < raiz.primeros.size(); i++) {
            findValue(raiz, raiz.primeros.elementAt(i));
            content += "<TD BORDER=\"1\" COLSPAN=\"1\" WIDTH=\"30\" HEIGHT=\"10\">{"+ raiz.primeros.elementAt(i) +"}: "+tmpValores.pop()+"</TD>\n";
        }
        content += "</TR>\n";
        tmpValores.clear();
        for (int i = 0; i < tmpTransiciones.size(); i++) {
//            Estado tmp = estados.elementAt(i);
            Estado tmp = new Estado(tmpTransiciones.elementAt(i));
//            findValue(raiz, tmp.identificador);
//            content += tmp.getNextTableDot(tmpValores.pop());
            content += tmp.getTransitionDot(raiz, i+1);
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

//    public int estadoActual;
    public Stack<Integer> estadosSiguientes = new Stack<>();

    public Estado(Stack<Integer> estadosSiguientes) {
//        this.estadoActual = estadoActual;
        Set<Integer> set = new HashSet<>(estadosSiguientes);
        this.estadosSiguientes.addAll(set);
    }
    
    public String getTransitionDot(NodoTree root, int count) {//</TR> al final de todo el for
        String content = "<TR>\n";
        content += "<TD BORDER=\"1\" COLSPAN=\"1\" HEIGHT=\"10\">S" + count + "</TD>\n";
        for (int i = 0; i < estadosSiguientes.size(); i++) {
            if (estadosSiguientes.elementAt(i) == TREE.num) {
                content += "<TD BORDER=\"1\" COLSPAN=\"1\" WIDTH=\"30\" HEIGHT=\"10\">{" + estadosSiguientes.elementAt(i) + "}: #</TD>\n";
            } else {
                TREE.findValue(root, estadosSiguientes.elementAt(i));
                content += "<TD BORDER=\"1\" COLSPAN=\"1\" WIDTH=\"30\" HEIGHT=\"10\">{" + estadosSiguientes.elementAt(i) + "}: " + TREE.tmpValores.pop() + "</TD>\n";
            }
        }
        content += "</TR>\n";
        return content;
    }

}

class Siguiente {
    
    public int identificador;
    public Stack<Integer> siguientes = new Stack<>();
    //public String valor;

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
