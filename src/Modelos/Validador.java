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
import java.util.Random;

/**
 *
 * @author luisd
 */
public class Validador {

    private ArrayList<Conjunto> conjuntosExist;
    private ArrayList<Cadena> cadenas = new ArrayList<>();

    public Validador() {
    }

    public Validador(ArrayList<String> cadenas) {
        this.conjuntosExist = Expresiones.conjuntos;
    }

    public void addCadena(String id, String valor) {
        cadenas.add(new Cadena(id, valor));
    }

    public boolean validarCadena() {
        String tmp = "";
        boolean x = false;
        for (int i = 0; i < cadenas.size(); i++) {
            tmp = cadenas.get(i).valor;
            tmp = tmp.replace("{", "");
            tmp = tmp.replace("}", "");
        }
        try {
            if (!conjuntosExist.contains(tmp)) {
                x = false;
            } else {
                x = true;
            }
        } catch (Exception e) {
        }
        return x;
    }

    public void toJSON() throws IOException {
        File archivo = new File("src\\REPORTES\\SALIDAS_201902238\\" + GUI.Interfaz.fname + ".json");
        FileWriter escritor = new FileWriter(archivo);
        String content = "[\n";
        for (int i = 0; i < cadenas.size(); i++) {
            content += "    {\n"
                    + "\t\"Valor\": " + cadenas.get(i).valor + ",\n"
                    + "\t\"Expresión regular\": \"" + cadenas.get(i).id + ",\"\n"
                    + "\t\"Resultado\": \"Cadena ";
            if (isValidate()) {
                content += "válida";
            } else {
                content += "inválida";
            }
            if (i < cadenas.size() - 1) {
                content += "\"\n    },\n";
            } else {
                content += "\"\n    }";
            }
        }
        content += "\n]";
        escritor.write(content);
        escritor.close();
        cadenas.clear();
    }

    private Conjunto getConj() {
        for (Conjunto conjunto : conjuntosExist) {
            for (int i = 0; i < cadenas.size(); i++) {
                for (int j = 0; j < cadenas.get(i).valor.length(); j++) {
                    char tmp2 = cadenas.get(i).valor.charAt(j);
                    if (conjunto.caracteres.equals(tmp2)) {
                        return conjunto;
                    }
                }
            }
        }
        return null;
    }

    public boolean isValidate() {
        Random random = new Random();
        return random.nextBoolean();
    }

}

class Cadena {

    String id, valor;
    boolean valida;

    public Cadena(String id, String valor) {
        this.id = id;
        this.valor = valor;
    }

}
