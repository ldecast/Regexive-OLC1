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

/**
 *
 * @author luisd
 */
public class AFD {
    
    public Stack<NodoAFD> nodos = new Stack<>();
    public Stack<String> cerraduras = new Stack<>();
    public String nombre;

    public AFD(String nombre){
        this.nombre = nombre;
    }
    
    
    public String getNodes(){
        String node = "";
        for (int i = 0; i < nodos.size(); i++) {
//            System.out.println(i+"");
            node += nodos.elementAt(i).estado + "[label=\""+nodos.elementAt(i).estado+"\"];\n";
        }
        return node;
    }
    
    public String getTransition(){
        String transition = "";
        for (int i = 0; i < nodos.size()-1; i++) {
                transition += nodos.elementAt(i).estado + "->" + nodos.elementAt(i+1).estado
                    + "[label=<<font color=\"Crimson\">"+ nodos.elementAt(i).labelTransicion +"</font>> "
                    + "fontname=\"Century Gothic\" fontsize=\"12\"];\n";
            }
        return transition;
    }
    
    public String getCerradura(){
        Stack<String> aux = new Stack<>();
        String cerradura = "";
//        System.out.println(cerraduras.toString()+"MMMM");
        for (int i = 0; i < cerraduras.size(); i++) {
            if (!aux.contains(cerraduras.elementAt(i))) {
                cerradura += cerraduras.elementAt(i);
                aux.add(cerraduras.elementAt(i));
            }
        }
        return cerradura;
    }
    
    public void getDot() throws IOException{
        String contenido = "";
        contenido += "digraph D {\n"
        + "    graph[bgcolor=\"#141D26\"]\n"
        + "    rankdir=\"LR\";\n"
        + "    node[shape=\"circle\" style=filled fontname=\"Century Gothic\" fontsize=\"14\" color=\"#48D1CC\"];\n"
        + "    edge[arrowhead=vee color=\"#F5F5F5\" penwidth=\"1.5\"];\n";
        contenido += getNodes();
        contenido += getTransition();
        contenido += getCerradura();
        contenido += "\n}";
        
        File archivo = new File("src\\AFD_201902238\\"+nombre+"_"+GUI.Interfaz.fname+".dot");
        try (FileWriter escritor = new FileWriter(archivo)) {
            escritor.write(contenido);
        }
        GUI.Interfaz.addToTree(nombre,3);
        generateSVG();
    }
    
    private void generateSVG() throws IOException{
    	String dotPath = "C:\\Program Files (x86)\\Graphviz2.38\\bin\\dot.exe";

    	String fileInputPath = "src\\AFD_201902238\\"+nombre+"_"+GUI.Interfaz.fname+".dot";

    	String fileOutputPath = "src\\AFD_201902238\\"+nombre+"_"+GUI.Interfaz.fname+".svg";

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
    
}

class NodoAFD{
    String estado, labelTransicion;

    public NodoAFD(String estado, String labelTransicion) {
        this.estado = estado;
        this.labelTransicion = labelTransicion;
    }
}
