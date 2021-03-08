# MANUAL DE USUARIO

## Introducción
El siguiente manual tiene como fin introducir al usuario al manejo y conocimiento de REGEXIVE para su uso funcional.  

### __TytusDB__
<hr>
REGEXIVE es una aplicación que permite crear un sistema capaz de realizar el Método del Árbol y el Método de Thompson, para que los estudiantes puedan verificar que las respuestas de las tareas y exámenes del curso son correctas, así como validar cadenas. Por medio de una interfaz gráfica para el manejo de operaciones y reportes.

El software desarrollado cuenta con dos distintas ventanas de opciones para que el usuario pueda interactuar y hacer uso de la misma, las cuales se detallan más adelante.
<br><br>

### Requerimientos del sistema
<hr>
Cualquier computadora con sistema operativo Windows (8+) con máquina virtual de Java instalada para correr un .jar y 1 GB de memoria RAM como mínimo.
<br><br>

## Ventana principal
<hr>
<div align="center" alt="Menu">
  <img src="img/Ventana.png" />
</div>
En ella el usuario puede acceder a cada una de las funcionalidades de la aplicación y visualizar los reportes según desee.

<br><br>

<hr>
<div align="center" alt="Entradas">
  <img src="img/Entradas.png" />
</div>
Panel de texto donde el usuario podrá ingresar sus archivos .olc o bien escribir uno desde cero.
<br><br>

<hr>
<div align="center" alt="Salidas">
  <img src="img/Salidas.png" />
</div>
Consola de salida donde se brindará una pequeña retroalimentación de los procesos completados e información de errores.
<br><br>

<hr>
<div align="center" alt="Jtree">
  <img src="img/Jtree.png" />
</div>
Árbol de archivos para que el usuario pueda desplegar sobre el panel las distinas imágenes de reportes.
Con un clic desplegará en el panel, y con doble clic se abrirá un archivo .svg con mayor calidad en su explorador predeterminado.
<br><br>

<hr>
<div align="center" alt="Panel">
  <img src="img/Panel.png" />
</div>
Panel de imágenes para poder visualizar los reportes que el usuario elija del árbol de archivos en formato.png.
<br><br>

<hr>
<div align="center" alt="Opciones">
  <img src="img/Opciones.png" />
</div>
Menú para poder elegir distintas opciones y su respectivo atajo en teclado.
<br><br>


## Funciones
<hr>

### Generar autómatas
Al hacer clic en este botón se realizará un análisis inicial para generar cada uno de los autómatas (AFND y AFD), así como un árbol de expresiones y todos sus componentes como tabla de siguientes y transiciones.
<br><br>


### Analizar entradas
Se realizará un segundo análisis para poder validar entradas ingresadas por el usuario y reportar todas en un sólo archivo .json en el que podrá acceder desde el menú o presionando Ctrl+J.

<hr><br>

### Manejo de errores:

Es frecuente que puedan ocurrir ciertos errores al momento de ingresar datos y manejar las funciones del administrador, para ello se le retornará un archivo html con todos los errores tanto léxicos como sintácticos para informar sobre el error.

<div align="center" alt="Error">
  <img src="img/Errores.png" />
</div>
Cada error detalla en qué linea y columna se presentó, así como si se trata de un error léxico (no pertence al lenguaje) o sintáctico (el orden no fue el correcto).
<hr><br><br>


## Sintaxis esperada:
El archivo para la definición de las expresiones regulares se compone de una sección en la que cada sentencia define el token (identificador) con que el analizador debe reconocer los lexemas ingresados, seguido de la definición de la expresión regulares. Seguido de esto dos símbolos de porcentaje (%%). Por último, se define un identificador y un lexema de entrada, el cual se deberá comparar con la definición que se encuentra en la parte superior.
<br>
Cada sentencia se delimita utilizando punto y coma.
<br><br>

**Expresiones regulares:**
Las expresiones regulares establecen el patrón que se debe cumplir para representar un token, estas se reconocerán en notación polaca o prefija.
<div align="center" alt="Exp">
  <img src="img/Expr.png" />
</div>
<br><br>

**Conjuntos:**
Para la definición de conjuntos se utiliza la palabra reservada “CONJ”. Un conjunto puede utilizarse dentro de una expresión regular, pero no en la definición de otro conjunto.
A continuación, la notación a utilizar para la definición de conjuntos:
<div align="center" alt="Conj">
  <img src="img/Conj.png" />
</div>
*Los conjuntos vistos anteriormente son ejemplos de las diferentes variantes que éstos pueden tomar (Ej: también puede existir a~d o 0~9).
<br><br>

**Caracteres especiales:**
Dentro del lenguaje pueden utilizarse estos caracteres especiales:
<div align="center" alt="Car">
  <img src="img/Carac.png" />
</div>
<br><br>

**Notas:**
- La definición de conjuntos CONJ puede existir en cualquier parte del archivo.
- El uso de conjuntos se verá delimitado por { llaves }

## Ejemplo de archivo de entrada válido:
<div align="center" alt="Ej">
  <img src="img/ejemplo.png" />
</div>
<br><br>


## Soporte técnico
<hr>

luis.danniel@hotmail.com