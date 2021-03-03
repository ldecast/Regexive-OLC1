package Analizadores;
import java_cup.runtime.*;
import GUI.Interfaz;
import Modelos.*;
%%

%class Lexico
%cupsym sym
%cup
%public
%unicode
%line
%column
%ignorecase

%{
      StringBuffer string = new StringBuffer();

      private Symbol symbol(int type) {
        return new Symbol(type, yyline, yycolumn);
      }
      private Symbol symbol(int type, Object value) {
        return new Symbol(type, yyline, yycolumn, value);
      }
%}


LineTerminator = \r|\n|\r\n
InputCharacter = [^\r\n]
WhiteSpace     = {LineTerminator} | [ \t\f]
Alternativo = [ \t\r\n]
B = {WhiteSpace}+ | {Alternativo}+

Comment = {TraditionalComment}
          | {EndOfLineComment}

TraditionalComment   = "<!" [^*] ~"!>" | "<!" "!"+ ">"
EndOfLineComment     = "//" {InputCharacter}* {LineTerminator}?

prcon = "CONJ"
guion = "-"
pico = ">"
virgulilla = "~"
coma = ","
porcentaje = {B}*"%"{B}*
labre = "{"
lcierra = "}"

deriva = {guion}{B}*{pico}
operador = "." | "|"
multiplicativo = "*" | "+" | "?"
especiales = "\\n" | "\\’" | "\\”" | "\\'"

letra = [a-zA-Z]
digito = [0-9]

identificador = [:jletter:] [:jletterdigit:]*

lexema = \"[^\"]*\" | \“[^\”]*\”

notacionL = {letra}{virgulilla}{letra} | ({letra}{coma})*{letra}
notacionD = {digito}{virgulilla}{digito} | ({digito}{coma})*{digito}
notacionA = ([\040-\057] | [\072-\100] | [\133-\140] | [\173-\175]){virgulilla}([\040-\057] | [\072-\100] | [\133-\140] | [\173-\175]) | (([\040-\057] | [\072-\100] | [\133-\140] | [\173-\175]){coma})*([\040-\057] | [\072-\100] | [\133-\140] | [\173-\175])


conjuntoo = {labre}{identificador}{lcierra}

%{
    
%}
    
%%
\n {yycolumn=1;}
{B} {/*Se ignoran*/}
{Comment} {/*Se ignoran*/}

{prcon} {return new Symbol(sym.prconj,yycolumn,yyline,yytext());}
";" {return new Symbol(sym.pcoma,yycolumn,yyline,yytext());}
":" {return new Symbol(sym.dospuntos,yycolumn,yyline,yytext());}
{deriva} {return new Symbol(sym.deriva,yycolumn,yyline,yytext());}
{labre} {return new Symbol(sym.labre,yycolumn,yyline,yytext());}
{lcierra} {return new Symbol(sym.lcierra,yycolumn,yyline,yytext());}
{operador} {Expresiones.add(yytext()); return new Symbol(sym.operador,yycolumn,yyline,yytext());}
{multiplicativo} {Expresiones.add(yytext()); return new Symbol(sym.multiplicativo,yycolumn,yyline,yytext());}
{porcentaje}{4} {return new Symbol(sym.porcentajes,yycolumn,yyline,yytext());}

{notacionA} {
    //System.out.println("NOTACION SIMBOLO: "+yytext());
    return new Symbol(sym.notacionA,yycolumn,yyline,yytext());
}

{identificador} {
    //System.out.println("ID: "+yytext());
    return new Symbol(sym.id,yycolumn,yyline,yytext());
}

{notacionL} {
    //System.out.println("NOTACION LETRA: "+yytext());
    return new Symbol(sym.notacionL,yycolumn,yyline,yytext());
}

{notacionD} {
    //System.out.println("NOTACION DIGITO: "+yytext());
    return new Symbol(sym.notacionD,yycolumn,yyline,yytext());
}

{lexema} {
    Expresiones.add(yytext());
    //System.out.println("LEXEMA: "+yytext());
    return new Symbol(sym.lexema,yycolumn,yyline,yytext());
}

{conjuntoo} {
    Expresiones.add(yytext());
    //System.out.println("CONJUNTO: "+yytext());
    return new Symbol(sym.conjuntoo,yycolumn,yyline,yytext());
}

{especiales} {
    Expresiones.add(yytext());
    System.out.println("ESPECIAL: "+yytext());
    return new Symbol(sym.especial,yycolumn,yyline,yytext());
}

//CUALQUIER ERROR:           SIMBOLOS NO DEFINIDOS DENTRO DEL LENGUAJE
.   {
	    System.err.println("Error lexico: "+yytext()+ " Linea:"+(yyline+1)+" Columna:"+(yycolumn+1));
            GUI.Interfaz.texto_consola+="<<<Se encontraron errores léxicos en la entrada>>>\n";
            Modelos.Errores.lista_errores.add(new Errores("Léxico", yytext(), (yyline+1), (yycolumn+1)));
    }
