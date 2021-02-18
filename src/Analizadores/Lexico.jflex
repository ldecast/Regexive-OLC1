package Analizadores;
import java_cup.runtime.*;

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
blancos = [ \t\r\n]+

Comment = {TraditionalComment}
          | {EndOfLineComment}

TraditionalComment   = "<!" [^*] ~"!>" | "<!" "!"+ ">"
EndOfLineComment     = "//" {InputCharacter}* {LineTerminator}?

prcon = "CONJ"

letra = [a-zA-Z]
digito = [0-9]

identificador = [:jletter:] [:jletterdigit:]*

lexema = \".*\" | \“.*\”

notacionL = ({letra}"~"{letra}) | ({letra}",")*{letra}
notacionD = {digito}"~"{digito} | ({digito}",")*{digito}
notacionA = ([\040-\057] | [\072-\100] | [\133-\140] | [\173-\175])"~"([\040-\057] | [\072-\100] | [\133-\140] | [\173-\175]) | (([\040-\057] | [\072-\100] | [\133-\140] | [\173-\175])",")+([\040-\057] | [\072-\100] | [\133-\140] | [\173-\175])

porcentajes = " "*"%"" "*{LineTerminator}*
//porcentaje2 = "%%\n%%""   " "*"%"" "*"\n"*

operador = [.|*+?]

conjunto = {operador}*" "*"{"" "*{identificador}" "*"}"

var = {operador}?" "*{operador}?" "*{lexema}

%{
    
%}

%%

" "*{prcon}" "* {return new Symbol(sym.prconj,yycolumn,yyline,yytext());}
" "*";"" "* {return new Symbol(sym.pcoma,yycolumn,yyline,yytext());}
" "*":"" "* {return new Symbol(sym.dospuntos,yycolumn,yyline,yytext());}
" "*"->"" "* {return new Symbol(sym.deriva,yycolumn,yyline,yytext());}
" "*"{"" "* {return new Symbol(sym.labre,yycolumn,yyline,yytext());}
" "*"}"" "* {return new Symbol(sym.lcierra,yycolumn,yyline,yytext());}
{porcentajes}{4} {return new Symbol(sym.porcentajes,yycolumn,yyline,yytext());}

\n {yycolumn=1;}
{blancos} {/*Se ignoran*/}
{WhiteSpace} {/*Se ignoran*/}
{Comment} {/*Se ignoran*/}

{notacionA} {
    //System.out.println("NOTACION SIMBOLO: "+yytext());
    return new Symbol(sym.notacionA,yycolumn,yyline,yytext());
}

{identificador} {
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
    //System.out.println("LEXEMA: "+yytext());
    return new Symbol(sym.lexema,yycolumn,yyline,yytext());
}

{operador} {
    //System.out.println("OPERADOR: "+yytext());
    return new Symbol(sym.operador,yycolumn,yyline,yytext());
}   

{conjunto} {
    //System.out.println("ENTRE LLAVES: "+yytext());
    return new Symbol(sym.conjunto,yycolumn,yyline,yytext());
}

{var} {
    //System.out.println("VAR: "+yytext());
    return new Symbol(sym.var,yycolumn,yyline,yytext());
}   

//CUALQUIER ERROR:           SIMBOLOS NO DEFINIDOS DENTRO DEL LENGUAJE
.   {
	    System.err.println("Error lexico: "+yytext()+ " Linea:"+(yyline+1)+" Columna:"+(yycolumn+1));
            //clase error
    }

