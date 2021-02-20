/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelos;

import java.awt.Desktop;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;

/**
 *
 * @author luisd
 */
public class Errores {
    
    private String valor, tipo;
    private int fila, columna;
    public static ArrayList<Errores> lista_errores = new ArrayList<Errores>();

    public Errores(String tipo, String valor, int fila, int columna) {
        this.valor = valor;
        this.fila = fila;
        this.columna = columna;
        this.tipo = tipo;
    }

    public static void reportarErrores(String filename) throws IOException, URISyntaxException{ //hacerlo estatico y ejecutarlo desde el boton
        if (!lista_errores.isEmpty()) {
            String errores = "";
            for (int i = 0; i < lista_errores.size(); i++) {
                errores += "<tr>\n"+
            "<th scope=\"row\">" + String.valueOf(i+1) + "</th>\n"+
            "<td>" + lista_errores.get(i).tipo + "</td>\n";
                if ("Léxico".equals(lista_errores.get(i).tipo))
                    errores += "<td>El caracter \"" + lista_errores.get(i).valor + "\" no pertenece al lenguaje o no es válido.</td>\n";
                else
                    errores += "<td>El identificador \"" + lista_errores.get(i).valor + "\" reconocido no se esperaba.</td>\n";
	    errores += "<td>" + lista_errores.get(i).fila + "</td>\n"+
	    "<td>" + lista_errores.get(i).columna + "</td>\n"+
	    "</tr>\n";
            }
            
            String contenido = "<!doctype html>\n"+
"<html lang=\"en\">\n"+
  "<head>\n"+
   " <!-- Required meta tags -->\n"+
    "<meta charset=\"utf-8\">\n"+
    "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1, shrink-to-fit=no\">\n"+
    "<!-- Bootstrap CSS -->\n"+
    "<link rel=\"stylesheet\" href=\"https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css\" integrity=\"sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk\" crossorigin=\"anonymous\">\n"+
    "<style> body{ background-color: #7f8c8d; } .foot{ position: absolute; right:40px; bottom:10px; } </style>\n"+
   " <title>REGEXIVE - Errores</title>\n"+
  "</head>\n"+
  "<body>\n"+
  "<div class=\"container-fluid pt-3 pb-1 text-center bg-warning\">\n"+
   " <h1 class=\"display-5\"> Organización de Lenguajes y Compiladores 1</h1><hr>\n"+
   " <h2 class=\"\">Reporte de errores</h2>"+
   " </div>\n"+
   "     <div class=\"row px-5\">\n"+
   "         <div class=\"col-12 pl-2\">\n"+
   "             <h4 class=\"pt-3 pb-2\" >&nbsp;&nbsp;Archivo:&nbsp;&nbsp;------- "+ filename +".olc -------</h4>\n"+
  "              <table class=\"table table-striped table-dark\">\n"+
  "	<thead>\n"+
  "	  <tr>\n"+
  "	    <th scope=\"col\">#</th>\n"+
  "	    <th scope=\"col\">Tipo de error</th>\n"+
  " 	   <th scope=\"col\">Descripción</th>\n"+
 "  	   <th scope=\"col\">Línea</th>\n"+
 "  	   <th scope=\"col\">Columna</th>\n"+
 " 	  </tr>\n"+
 "	 </thead>\n"+
 "	 <tbody>\n"+
            errores+
"	  </tbody>\n"+
"	</table>\n"+
"            </div>\n"+
"        </div>\n"+
    "<!-- Optional JavaScript -->\n"+
    "<!-- jQuery first, then Popper.js, then Bootstrap JS -->\n"+
    "<script src=\"https://code.jquery.com/jquery-3.5.1.slim.min.js\" integrity=\"sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj\" crossorigin=\"anonymous\"></script>\n"+
    "<script src=\"https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js\" integrity=\"sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo\" crossorigin=\"anonymous\"></script>\n"+
    "<script src=\"https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js\" integrity=\"sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI\" crossorigin=\"anonymous\"></script>\n"+
    "</body></html>";
            
            try (FileWriter html = new FileWriter("src/ERRORES_201902238/"+filename+".html")) {
                html.write(contenido);
            }
            File htmlFile = new File("src/ERRORES_201902238/"+filename+".html");
            Desktop.getDesktop().browse(htmlFile.toURI());
        }
    }
    
}
