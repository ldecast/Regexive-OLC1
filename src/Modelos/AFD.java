/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelos;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Stack;

/**
 *
 * @author luisd
 */
public class AFD {

    public Stack<NodoAFD> nodos = new Stack<>();
    public Stack<String> cerraduras = new Stack<>();
    public String nombre;

    public AFD(String nombre) {
        this.nombre = nombre;
    }

    public String getNodes() {
        String node = "";
        for (int i = 0; i < nodos.size(); i++) {
//            System.out.println(i+"");
            if (i < nodos.size() - 1) {
                node += nodos.elementAt(i).estado + "[label=\"" + nodos.elementAt(i).estado + "\"];\n";
            } else {
                node += nodos.elementAt(i).estado + "[label=\"" + nodos.elementAt(i).estado + "\" shape=\"doublecircle\"];\n";
            }
        }
        return node;
    }

    public String getTransition() {
        String transition = "";
        for (int i = 0; i < nodos.size() - 1; i++) {
            for (int j = 0; j < nodos.elementAt(i).transiciones.size(); j++) {
//                System.out.println(nodos.elementAt(i).estado);
                transition += nodos.elementAt(i).estado + "->" + nodos.elementAt(i + 1).estado;
                transition += "[label=<<font color=\"Crimson\">" + nodos.elementAt(i).transiciones.get(j) + "</font>> "
                        + "fontname=\"Century Gothic\" fontsize=\"12\"];\n";
            }
        }
        return transition;
    }

    public String getCerradura() {
        Stack<String> aux = new Stack<>();
        String cerradura = "";
//        System.out.println(cerraduras.toString() + "MMMM");
        for (int i = 0; i < cerraduras.size(); i++) {
            if (!aux.contains(cerraduras.elementAt(i))) {
                cerradura += cerraduras.elementAt(i);
                aux.add(cerraduras.elementAt(i));
            }
        }
        return cerradura;
    }

    public void getDot() throws IOException {
        String contenido = "";
        contenido += "digraph AFD {\n"
                + "    graph[bgcolor=\"#141D26\"]\n"
                + "    rankdir=\"LR\";\n"
                + "    node[shape=\"circle\" style=filled fontname=\"Century Gothic\" fontsize=\"14\" color=\"#48D1CC\"];\n"
                + "    edge[arrowhead=vee color=\"#F5F5F5\" penwidth=\"1.5\"];\n";
        contenido += getNodes();
        contenido += getTransition();
        contenido += getCerradura();
        contenido += "\n}";

        File archivo = new File("src\\REPORTES\\AFD_201902238\\" + nombre + "_" + GUI.Interfaz.fname + ".dot");
        try (FileWriter escritor = new FileWriter(archivo)) {
            escritor.write(contenido);
            escritor.close();
        }
        GUI.Interfaz.addToTree(nombre, 4);
        generateSVG();
        generatePNG();
    }

    private void generateSVG() throws IOException {
        String dotPath = "C:\\Program Files (x86)\\Graphviz2.38\\bin\\dot.exe";

        String fileInputPath = "src\\REPORTES\\AFD_201902238\\" + nombre + "_" + GUI.Interfaz.fname + ".dot";

        String fileOutputPath = "src\\REPORTES\\AFD_201902238\\" + nombre + "_" + GUI.Interfaz.fname + ".svg";

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

    private void generatePNG() throws IOException {
        String dotPath = "C:\\Program Files (x86)\\Graphviz2.38\\bin\\dot.exe";

        String fileInputPath = "src\\REPORTES\\AFD_201902238\\" + nombre + "_" + GUI.Interfaz.fname + ".dot";

        String fileOutputPath = "src\\GUI\\IMAGENES\\AFD\\" + nombre + "_" + GUI.Interfaz.fname + ".png";

        String tParam = "-Tpng";

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

class NodoAFD {

    String estado;//, labelTransicion;
    ArrayList<String> transiciones;

    public NodoAFD(String estado, ArrayList<String> transiciones) {
        this.estado = estado;
        this.transiciones = transiciones;
    }
}
