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
public class AFND { //leer de derecha a izquierda e ir agrupando por segmentos el AFND

    private final String nombre;
    private final Stack<String> stack;
    private final Stack<String> tmp;
    private final Stack<String> pila;

    public AFND(String nombre, Stack<String> stack, Stack<String> tmp) throws IOException {
        this.nombre = nombre;
        this.stack = stack;
        this.tmp = tmp;
        this.pila = new Stack<>();
        if (checkStack()) {
            thompson();
        }
    }

    private boolean isOperator(String x) {
        switch (x.trim()) {
            case "|":
            case ".":
                return true;
        }
        return false;
    }

    private boolean isMultiplicative(String x) {
        switch (x.trim()) {
            case "+":
            case "?":
            case "*":
                return true;
        }
        return false;
    }

    private boolean checkStack() {
        for (int i = 0; i < stack.size(); i++) {
            if (tmp.contains(stack.get(i))) {
                pila.add(stack.get(i));
            }
        }
        return true;
    }

    private void thompson() throws IOException {

        boolean m = false;
        String temp = "0[label=\"\" shape=\"doublecircle\" fontsize=\"12\"];\n";
        temp += "final[label=\"S0\" fontsize=\"12\"];\n";
        int count = 1;
        Stack<String> trans = new Stack<>();
        Stack<Integer> states = new Stack<>();
        Stack<Integer> aux = new Stack<>();

        String op1 = "";
        String op2 = "";
        int sti = 0;
        int stf = 0;
        for (int i = pila.size() - 1; i >= 0; i--) {
            String c = pila.remove(i);
            if (isOperator(c)) {

                if ("|".equals(c.trim())) {
                    if (aux.isEmpty()) {
                        op1 = trans.pop();
                        op2 = trans.pop();
                        for (int j = count; j < count + 4; j++) {
                            temp += String.valueOf(j) + "[label=\"\"];\n";
                        }
                        temp += String.valueOf(count) + "->" + String.valueOf(count + 1) + "[label=<<font color=\"Crimson\">" + op1 + "</font>> fontname=\"Century Gothic\" fontsize=\"12\"];\n";
                        temp += String.valueOf(count) + "->" + String.valueOf(count + 2) + "[label=<<font color=\"Crimson\">" + op2 + "</font>> fontname=\"Century Gothic\" fontsize=\"12\"];\n";
                        temp += String.valueOf(count + 1) + "," + String.valueOf(count + 2) + "->" + String.valueOf(count + 3) + "[label=<<font color=\"Crimson\">ε</font>> fontname=\"Century Gothic\" fontsize=\"12\"];\n";
                        aux.add(count);
                        states.add(count);
                        states.add(count + 3);
                        aux.add(count + 3);
                        count += 4;
                    } else {
                        sti = aux.remove(0);
                        stf = aux.remove(0);
                        op1 = trans.pop();
                        for (int j = count; j < count + 3; j++) {
                            temp += String.valueOf(j) + "[label=\"\"];\n";
                        }
                        temp += String.valueOf(sti) + "->" + String.valueOf(count) + "[label=<<font color=\"Crimson\">ε</font>> fontname=\"Century Gothic\" fontsize=\"12\"];\n";
                        temp += String.valueOf(count) + "->" + String.valueOf(count + 1) + "[label=<<font color=\"Crimson\">" + op1 + "</font>> fontname=\"Century Gothic\" fontsize=\"12\"];\n";
                        temp += String.valueOf(count + 1) + "->" + String.valueOf(stf) + "->" + String.valueOf(count + 2) + "[label=<<font color=\"Crimson\">ε</font>> fontname=\"Century Gothic\" fontsize=\"12\"];\n";
                        aux.add(count);
                        states.add(count);
                        states.add(count + 2);
                        aux.add(count + 2);
                        count += 3;
                    }
                } else {
                    if (aux.isEmpty()) {
                        op1 = trans.pop();
                        op2 = trans.pop();
                        for (int j = count; j < count + 3; j++) {
                            temp += String.valueOf(j) + "[label=\"\"];\n";
                        }
                        temp += String.valueOf(count) + "->" + String.valueOf(count + 1) + "[label=<<font color=\"Crimson\">" + op1 + "</font>> fontname=\"Century Gothic\" fontsize=\"12\"];\n";
                        temp += String.valueOf(count + 1) + "->" + String.valueOf(count + 2) + "[label=<<font color=\"Crimson\">" + op2 + "</font>> fontname=\"Century Gothic\" fontsize=\"12\"];\n";
                        aux.add(count);
                        states.add(count);
                        states.add(count + 2);
                        aux.add(count + 2);
                        count += 3;
                    } else {
                        sti = aux.pop();
                        op1 = trans.pop();
                        temp += String.valueOf(count) + "[label=\"\"];\n";
                        temp += String.valueOf(sti) + "->" + String.valueOf(count) + "[label=<<font color=\"Crimson\">" + op1 + "</font>> fontname=\"Century Gothic\" fontsize=\"12\"];\n";
                        aux.add(sti); //...
                        states.add(sti);
                        states.add(count);
                        aux.add(count);
                        count++;
                    }
                }
            } else if (isMultiplicative(c)) {
                if (states.size() >= 2) {
                    sti = states.remove(0);
                    stf = states.remove(0); //(mejor estetico, pero fallo en 1)
                    switch (c.trim()) {
                        case "?":
                            temp += String.valueOf(sti) + "->" + String.valueOf(stf) + "[label=<<font color=\"Crimson\">ε</font>> fontname=\"Century Gothic\" fontsize=\"12\"];\n";
                            break;
                        case "+":
                            temp += String.valueOf(stf) + "->" + String.valueOf(sti) + "[label=<<font color=\"Crimson\">ε</font>> fontname=\"Century Gothic\" fontsize=\"12\"];\n";
                            break;
                        case "*":
                            temp += String.valueOf(sti) + "->" + String.valueOf(stf) + "->" + String.valueOf(sti) + "[label=<<font color=\"Crimson\">ε</font>> fontname=\"Century Gothic\" fontsize=\"12\"];\n";
                            break;
                    }
                    states.add(sti);
                    states.add(count);
                } else if (states.isEmpty()) {
                    sti = 0;
                    op1 = trans.pop();
                    switch (c.trim()) {
                        case "?":
                            for (int j = count; j < count + 3; j++) {
                                temp += String.valueOf(j) + "[label=\"\"];\n";
                            }
                            temp += String.valueOf(sti) + "->" + String.valueOf(count) + "," + String.valueOf(count + 2) + "[label=<<font color=\"Crimson\">ε</font>> fontname=\"Century Gothic\" fontsize=\"12\"];\n";
                            temp += String.valueOf(count) + "->" + String.valueOf(count + 1) + "[label=<<font color=\"Crimson\">" + op1 + "</font>> fontname=\"Century Gothic\" fontsize=\"12\"];\n";
                            temp += String.valueOf(count + 1) + "->" + String.valueOf(count + 2) + "[label=<<font color=\"Crimson\">ε</font>> fontname=\"Century Gothic\" fontsize=\"12\"];\n";
                            aux.add(sti);
                            states.add(sti);
                            states.add(count + 2);
                            aux.add(count + 2);
                            count += 3;
                            break;
                        case "+":
                            for (int j = count; j < count + 3; j++) {
                                temp += String.valueOf(j) + "[label=\"\"];\n";
                            }
                            temp += String.valueOf(sti) + "->" + String.valueOf(count) + "[label=<<font color=\"Crimson\">ε</font>> fontname=\"Century Gothic\" fontsize=\"12\"];\n";
                            temp += String.valueOf(count) + "->" + String.valueOf(count + 1) + "[label=<<font color=\"Crimson\">" + op1 + "</font>> fontname=\"Century Gothic\" fontsize=\"12\"];\n";
                            temp += String.valueOf(count + 1) + "->" + String.valueOf(count + 2) + "," + String.valueOf(count) + "[label=<<font color=\"Crimson\">ε</font>> fontname=\"Century Gothic\" fontsize=\"12\"];\n";
                            aux.add(sti);
                            states.add(sti);
                            states.add(count + 2);
                            aux.add(count + 2);
                            count += 3;
                            break;
                        case "*":
                            for (int j = count; j < count + 3; j++) {
                                temp += String.valueOf(j) + "[label=\"\"];\n";
                            }
                            temp += String.valueOf(sti) + "->" + String.valueOf(count) + "," + String.valueOf(count + 2) + "[label=<<font color=\"Crimson\">ε</font>> fontname=\"Century Gothic\" fontsize=\"12\"];\n";
                            temp += String.valueOf(count) + "->" + String.valueOf(count + 1) + "[label=<<font color=\"Crimson\">" + op1 + "</font>> fontname=\"Century Gothic\" fontsize=\"12\"];\n";
                            temp += String.valueOf(count + 1) + "->" + String.valueOf(count + 2) + "," + String.valueOf(count) + "[label=<<font color=\"Crimson\">ε</font>> fontname=\"Century Gothic\" fontsize=\"12\"];\n";
                            aux.add(sti);
                            states.add(sti);
                            states.add(count + 2);
                            aux.add(count + 2);
                            count += 3;
                            break;
                    }
                    m = true;
                }
            } else {
                trans.push(c + "");
            }
        }
        if (!m) {
            temp += "0->1[label=<<font color=\"Crimson\">ε</font>> fontname=\"Century Gothic\" fontsize=\"12\"];\n";
        }
        temp += String.valueOf(count - 1) + "->final[label=<<font color=\"Crimson\">ε</font>> fontname=\"Century Gothic\" fontsize=\"12\"];";
        stack.clear();
        pila.clear();
        toDot(temp);
    }

    private void toDot(String complemento) throws IOException {
        String contenido = "";
        contenido += "digraph AFND {\n"
                + "    graph[bgcolor=\"#141D26\"]\n"
                + "    rankdir=\"RL\";\n"
                + "    node[shape=\"circle\" style=filled fontname=\"Century Gothic\" fontsize=\"14\" color=\"#48D1CC\"];\n"
                + "    edge[arrowhead=vee color=\"#F5F5F5\" penwidth=\"1.5\" dir=\"back\"];\n";
        contenido += complemento;
        contenido += "\n}";

        File archivo = new File("src\\REPORTES\\AFND_201902238\\" + nombre + "_" + GUI.Interfaz.fname + ".dot");
        try (FileWriter escritor = new FileWriter(archivo)) {
            escritor.write(contenido);
            escritor.close();
        }
        GUI.Interfaz.addToTree(nombre, 3);
        generateSVG();
        generatePNG();
    }

    private void generateSVG() throws IOException {
        String dotPath = "C:\\Program Files (x86)\\Graphviz2.38\\bin\\dot.exe";

        String fileInputPath = "src\\REPORTES\\AFND_201902238\\" + nombre + "_" + GUI.Interfaz.fname + ".dot";

        String fileOutputPath = "src\\REPORTES\\AFND_201902238\\" + nombre + "_" + GUI.Interfaz.fname + ".svg";

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

        String fileInputPath = "src\\REPORTES\\AFND_201902238\\" + nombre + "_" + GUI.Interfaz.fname + ".dot";

        String fileOutputPath = "src\\GUI\\IMAGENES\\AFND\\" + nombre + "_" + GUI.Interfaz.fname + ".png";

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
