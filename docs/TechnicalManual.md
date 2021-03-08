# MANUAL TÉCNICO

## Indice
- [Introducción](#introducción)
- [Objetivos](#objetivos)
- [Descripción general](#Descripción-general)
- [Requerimientos funcionales](#Requerimientos-funcionales)
- [Atributos del sistema](#Atributos-del-sistema)
- [Método de trabajo](#método-de-trabajo)

<hr>
<br>

## Introducción
En el presente documento se detallan los elementos técnicos detrás de la funcionalidad de REGEXIVE, realizado para el primer proyecto del curso de Organización de Lenguajes y Compiladores 1. Así mismo 
se justifica las decisiones tomadas a lo largo de la elaboración del proyecto en busca de una implementación completa y funcional para el análisis léxico y sintáctico.

## Objetivos
### General 
Aplicar los conocimientos sobre la fase de análisis léxico y sintáctico de un compilador para construcción de una solución de software que permita generar análisis por medio del método del árbol.
### Específicos
-	Reforzar el concepto del método de Árbol de expresiones regulares en Autómatas Finitos Deterministas (AFD).
-	Reforzar el concepto del método de Thompson de expresiones regulares en Autómatas Finitos No Deterministas (AFND).
-	Identificar y programar el proceso de reconocimiento de lexemas mediante el uso de Autómatas Finitos Determinista.


## Descripción general
El curso de Organización de Lenguajes y Compiladores 1, perteneciente a la Facultad de Ingeniería de la Universidad de San Carlos de Guatemala, requiere que como conocedor en la construcción de analizadores Léxico y Sintáctico, se cree un sistema que sea capaz de realizar el Método del Árbol y el Método de Thompson, para que los estudiantes puedan verificar que las respuestas de las tareas y exámenes del curso son correctas. Este aplicativo, requiere que las expresiones regulares sean ingresadas en notación polaca o prefija.

## Atributos del sistema
Cualquier computadora con sistema operativo Windows (8+) con máquina virtual de Java instalada para correr un .jar y 1 GB de memoria RAM como mínimo.

## Requerimientos funcionales
-	Intérprete de Expresiones Regulares Permitidas: Esta funcionalidad analizará un archivo de expresiones regulares que serán las que se permiten dentro del lenguaje que recibe el lenguaje entendible con la información necesaria para la validación de los lexemas.
-	Se requiere un programa que permita la edición de programas fuente a través de un área de texto. Además, que permita la visualización de los resultados y reportes de análisis.
-	Brindar los parámetros requeridos, para evitar que el proceso sea rechazado.
-	Reconocer, analizar y validar cadenas ingresadas.
-	Graficar toda parte del proceso por medio de una interfaz amigable al usuario.


## Método de trabajo
El proyecto está distribuido en distintas carpetas, para optimizar y ordenar mejor el trabajo, los módulos son los siguientes:
- Modelos
- GUI
- Analizadores
Además de archivos adicionales dentro del mismo directorio como los reportes.

Toda la metodología está ligeramente inspirada en MVC donde se busca mantener separado cada uno de sus componentes para un mejor orden y control de errores. El proyecto es multiparadigma pero predomina el OOP, representado por cada clase e instancia que se maneja a lo largo de la implementación. 

### [Analizadores](https://github.com/ldecast/OLC1-PROYECTO1-201902238/tree/master/src/Analizadores)
Carpeta en donde se encuentran los respectivos archivos .flex y .cup además de sus generados, para poder llevar a cabo ambos análisis y ejecutar cada funcionalidad de acuerdo al flujo de la operación.

### [Modelos](https://github.com/ldecast/OLC1-PROYECTO1-201902238/tree/master/src/Modelos)
Carpeta en donde se encuentran cada uno de los archivos .java para poder realizar cada gráfica, validación y construcción de las estructuras lógicas.

#### [TREE](https://github.com/ldecast/OLC1-PROYECTO1-201902238/tree/master/src/Modelos/TREE.java)
|Nombre de la función |Descripción|
| ------------ | ------------ |
| `next_table(NodoTree raiz)` | Realiza el recorrido del árbol para poder llenar los arreglos de enteros para cada siguiente. |
| `transition_table()` | Construye la tabla de transiciones por medio de los siguientes obtenidos. |
| `toDot()` | Genera los archivos en .dot para su graficación mediante Graphviz. |
| `generate_AFD()` | Instancia un objeto de la clase AFD para poder construir el autómata finito determinista. |
| `generateSVG/PNG(String tipo)` | Crea un gráfico en formato SVG y PNG respectivamente según el tipo de gráfica indicado. |

#### [NodoTree](https://github.com/ldecast/OLC1-PROYECTO1-201902238/tree/master/src/Modelos/NodoTree.java)
|Nombre de la función |Descripción|
| ------------ | ------------ |
| `isVoidable()` | Verifica si el nodo actual es anulable o no con un booleano. |
| `first()` | Calcula los primeros del nodo. |
| `last()` | Calcula los últimos del nodo. |
| `getDot()` | Obtiene el gráfico del árbol desde la raíz.  |


#### [Validador](https://github.com/ldecast/OLC1-PROYECTO1-201902238/tree/master/src/Modelos/Validador.java)
|Nombre de la función |Descripción|
| ------------ | ------------ |
| `addCadena()` | Agrega una cadena nueva a evaluar en un ArrayList de tipo Cadena. |
| `validarCadena()` | Valida cada cadena secuencialmente de acuerdo a la expresión que corresponde y retorna un booleano dependiendo de si es válida o no. |
| `toJSON()` | Genera un archivo .json para reportar cada cadena del archivo y su validez. |

#### [Errores](https://github.com/ldecast/OLC1-PROYECTO1-201902238/tree/master/src/Modelos/Errores.java)
|Nombre de la función |Descripción|
| ------------ | ------------ |
| `reportarErrores()` | Toma todos los errores de una lista de tipo Error y genera un archivo .html que despliega automáticamente en caso de detectar errores. |

<hr><br>
Además se cuenta con una interfaz gráfica para trabajar con las funciones de manera más dinámica e intuitiva y poder visualizar cada reporte.
<br><br>

#### [Interfaz](https://github.com/ldecast/OLC1-PROYECTO1-201902238/tree/master/src/GUI/Interfaz.java)
|Nombre de la función |Descripción|
| ------------ | ------------ |
| `OpenFile()` | Permite seleccionar un archivo .olc para abrirlo desde un FileChooser.|
| `CloseFile()` | Cierra el archivo actual y reestablece la ventana. |
| `SaveAs()` | Abre un FileChooser para seleccionar una ruta y establecer un nombre de guardado. |
| `Save()` | Reescribe el archivo actual. |
| `btnAutomatasActionPerformed(evt)` | Genera el primer análisis para crear todos los autómatas. |
| `btnAnalizarActionPerformed(evt)` | Analiza las entradas y genera el archivo .json como reporte. |
| `jTreeMouseClicked(evt)` | Permite visualizar o bien abrir el archivo de la gráfica con uno o dos clic. |


#### [Main](https://github.com/ldecast/OLC1-PROYECTO1-201902238/tree/master/src/main/Main.java)
Lanza la aplicación y ejecuta los compiladores para los analizadores léxico y sintáctico.

<hr>
<br>

## Contacto:
luis.danniel@hotmail.com
<hr>







