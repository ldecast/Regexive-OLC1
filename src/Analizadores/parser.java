
//----------------------------------------------------
// The following code was generated by CUP v0.11b 20160615 (GIT 4ac7450)
//----------------------------------------------------

package Analizadores;

import java_cup.runtime.*;
import GUI.Interfaz;
import Modelos.*;
import java_cup.runtime.XMLElement;

/** CUP v0.11b 20160615 (GIT 4ac7450) generated parser.
  */
@SuppressWarnings({"rawtypes"})
public class parser extends java_cup.runtime.lr_parser {

 public final Class getSymbolContainer() {
    return sym.class;
}

  /** Default constructor. */
  @Deprecated
  public parser() {super();}

  /** Constructor which sets the default scanner. */
  @Deprecated
  public parser(java_cup.runtime.Scanner s) {super(s);}

  /** Constructor which sets the default scanner. */
  public parser(java_cup.runtime.Scanner s, java_cup.runtime.SymbolFactory sf) {super(s,sf);}

  /** Production table. */
  protected static final short _production_table[][] = 
    unpackFromStrings(new String[] {
    "\000\025\000\002\002\004\000\002\002\003\000\002\003" +
    "\007\000\002\004\005\000\002\004\005\000\002\004\004" +
    "\000\002\004\004\000\002\007\007\000\002\006\003\000" +
    "\002\006\003\000\002\006\003\000\002\010\005\000\002" +
    "\011\004\000\002\011\003\000\002\012\004\000\002\012" +
    "\005\000\002\012\003\000\002\012\003\000\002\012\003" +
    "\000\002\005\007\000\002\005\006" });

  /** Access to production table. */
  public short[][] production_table() {return _production_table;}

  /** Parse-action table. */
  protected static final short[][] _action_table = 
    unpackFromStrings(new String[] {
    "\000\056\000\004\007\004\001\002\000\006\004\010\012" +
    "\012\001\002\000\004\002\000\001\002\000\004\002\007" +
    "\001\002\000\004\002\001\001\002\000\004\006\052\001" +
    "\002\000\010\004\010\011\033\012\012\001\002\000\004" +
    "\017\017\001\002\000\004\005\016\001\002\000\004\005" +
    "\015\001\002\000\010\004\ufffc\011\ufffc\012\ufffc\001\002" +
    "\000\010\004\ufffb\011\ufffb\012\ufffb\001\002\000\014\013" +
    "\026\020\020\021\021\022\023\023\025\001\002\000\014" +
    "\013\026\020\020\021\021\022\023\023\025\001\002\000" +
    "\014\013\026\020\020\021\021\022\023\023\025\001\002" +
    "\000\016\005\ufff6\013\026\020\020\021\021\022\023\023" +
    "\025\001\002\000\016\005\ufff0\013\ufff0\020\ufff0\021\ufff0" +
    "\022\ufff0\023\ufff0\001\002\000\016\005\ufff4\013\ufff4\020" +
    "\ufff4\021\ufff4\022\ufff4\023\ufff4\001\002\000\016\005\uffef" +
    "\013\uffef\020\uffef\021\uffef\022\uffef\023\uffef\001\002\000" +
    "\016\005\ufff1\013\ufff1\020\ufff1\021\ufff1\022\ufff1\023\ufff1" +
    "\001\002\000\016\005\ufff5\013\ufff5\020\ufff5\021\ufff5\022" +
    "\ufff5\023\ufff5\001\002\000\016\005\ufff3\013\ufff3\020\ufff3" +
    "\021\ufff3\022\ufff3\023\ufff3\001\002\000\014\013\026\020" +
    "\020\021\021\022\023\023\025\001\002\000\016\005\ufff2" +
    "\013\ufff2\020\ufff2\021\ufff2\022\ufff2\023\ufff2\001\002\000" +
    "\004\012\041\001\002\000\004\005\037\001\002\000\004" +
    "\005\036\001\002\000\010\004\ufffd\011\ufffd\012\ufffd\001" +
    "\002\000\010\004\ufffe\011\ufffe\012\ufffe\001\002\000\006" +
    "\010\045\012\046\001\002\000\004\006\042\001\002\000" +
    "\004\013\043\001\002\000\004\005\044\001\002\000\006" +
    "\010\uffed\012\uffed\001\002\000\004\002\uffff\001\002\000" +
    "\004\006\047\001\002\000\004\013\050\001\002\000\004" +
    "\005\051\001\002\000\006\010\uffee\012\uffee\001\002\000" +
    "\004\012\053\001\002\000\004\017\054\001\002\000\010" +
    "\014\060\015\056\016\057\001\002\000\004\005\ufffa\001" +
    "\002\000\004\005\ufff7\001\002\000\004\005\ufff8\001\002" +
    "\000\004\005\ufff9\001\002" });

  /** Access to parse-action table. */
  public short[][] action_table() {return _action_table;}

  /** <code>reduce_goto</code> table. */
  protected static final short[][] _reduce_table = 
    unpackFromStrings(new String[] {
    "\000\056\000\006\002\005\003\004\001\001\000\010\004" +
    "\010\007\013\010\012\001\001\000\002\001\001\000\002" +
    "\001\001\000\002\001\001\000\002\001\001\000\006\007" +
    "\033\010\034\001\001\000\002\001\001\000\002\001\001" +
    "\000\002\001\001\000\002\001\001\000\002\001\001\000" +
    "\006\011\021\012\023\001\001\000\004\012\030\001\001" +
    "\000\004\012\027\001\001\000\004\012\026\001\001\000" +
    "\002\001\001\000\002\001\001\000\002\001\001\000\002" +
    "\001\001\000\002\001\001\000\002\001\001\000\004\012" +
    "\031\001\001\000\002\001\001\000\004\005\037\001\001" +
    "\000\002\001\001\000\002\001\001\000\002\001\001\000" +
    "\002\001\001\000\002\001\001\000\002\001\001\000\002" +
    "\001\001\000\002\001\001\000\002\001\001\000\002\001" +
    "\001\000\002\001\001\000\002\001\001\000\002\001\001" +
    "\000\002\001\001\000\002\001\001\000\002\001\001\000" +
    "\004\006\054\001\001\000\002\001\001\000\002\001\001" +
    "\000\002\001\001\000\002\001\001" });

  /** Access to <code>reduce_goto</code> table. */
  public short[][] reduce_table() {return _reduce_table;}

  /** Instance of action encapsulation class. */
  protected CUP$parser$actions action_obj;

  /** Action encapsulation object initializer. */
  protected void init_actions()
    {
      action_obj = new CUP$parser$actions(this);
    }

  /** Invoke a user supplied parse action. */
  public java_cup.runtime.Symbol do_action(
    int                        act_num,
    java_cup.runtime.lr_parser parser,
    java.util.Stack            stack,
    int                        top)
    throws java.lang.Exception
  {
    /* call code in generated class */
    return action_obj.CUP$parser$do_action(act_num, parser, stack, top);
  }

  /** Indicates start state. */
  public int start_state() {return 0;}
  /** Indicates start production. */
  public int start_production() {return 0;}

  /** <code>EOF</code> Symbol index. */
  public int EOF_sym() {return 0;}

  /** <code>error</code> Symbol index. */
  public int error_sym() {return 1;}


  
    public static int id = 100;
    public static int aux = 1;
    public static NodoTree raiz;
    public static Validador val = new Validador();
    //-----------------------------------------para errores sintacticos-------------------------------------------------------------------------------------------
    public void syntax_error(Symbol s)
    {
        System.err.println("Error en la L??nea " + (s.right+1) +" Columna "+(s.left+1)+ ". Identificador "+s.value + " no reconocido. Se ha recuperado del error." );
        if (s.value!=null){
            GUI.Interfaz.texto_consola+="<<<Errores sint??cticos encontrados>>>\n";
            Errores.lista_errores.add(new Errores("Sint??ctico", (String) s.value, (s.right+1), (s.left+1)));
        }
    }
    public void unrecovered_syntax_error(Symbol s) throws java.lang.Exception
    {
        System.err.println("Error en la L??nea " + (s.right+1)+ " Columna "+(s.left+1)+". Identificador " +s.value + " no reconocido.");
    }
    //-------------------------------------------------------------------------------------------------------------------------------------------8


/** Cup generated class to encapsulate user supplied action code.*/
@SuppressWarnings({"rawtypes", "unchecked", "unused"})
class CUP$parser$actions {



  private final parser parser;

  /** Constructor */
  CUP$parser$actions(parser parser) {
    this.parser = parser;
  }

  /** Method 0 with the actual generated action code for actions 0 to 300. */
  public final java_cup.runtime.Symbol CUP$parser$do_action_part00000000(
    int                        CUP$parser$act_num,
    java_cup.runtime.lr_parser CUP$parser$parser,
    java.util.Stack            CUP$parser$stack,
    int                        CUP$parser$top)
    throws java.lang.Exception
    {
      /* Symbol object for return from actions */
      java_cup.runtime.Symbol CUP$parser$result;

      /* select the action based on the action number */
      switch (CUP$parser$act_num)
        {
          /*. . . . . . . . . . . . . . . . . . . .*/
          case 0: // $START ::= INICIO EOF 
            {
              Object RESULT =null;
		int start_valleft = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-1)).left;
		int start_valright = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-1)).right;
		String start_val = (String)((java_cup.runtime.Symbol) CUP$parser$stack.elementAt(CUP$parser$top-1)).value;
		RESULT = start_val;
              CUP$parser$result = parser.getSymbolFactory().newSymbol("$START",0, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-1)), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          /* ACCEPT */
          CUP$parser$parser.done_parsing();
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 1: // INICIO ::= EXP 
            {
              String RESULT =null;
		
              CUP$parser$result = parser.getSymbolFactory().newSymbol("INICIO",0, ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 2: // EXP ::= labre SENTENCIA porcentajes EXPRESION lcierra 
            {
              String RESULT =null;
		int eleft = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-1)).left;
		int eright = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-1)).right;
		String e = (String)((java_cup.runtime.Symbol) CUP$parser$stack.elementAt(CUP$parser$top-1)).value;
		  if (Interfaz.funcion == "ANALIZAR ENTRADAS")
                                                                parser.val.toJSON();
                                                        
              CUP$parser$result = parser.getSymbolFactory().newSymbol("EXP",1, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-4)), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 3: // SENTENCIA ::= SENTENCIA CONJUNTO pcoma 
            {
              String RESULT =null;
		
              CUP$parser$result = parser.getSymbolFactory().newSymbol("SENTENCIA",2, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-2)), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 4: // SENTENCIA ::= SENTENCIA INFIJA pcoma 
            {
              String RESULT =null;
		int rleft = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-1)).left;
		int rright = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-1)).right;
		String r = (String)((java_cup.runtime.Symbol) CUP$parser$stack.elementAt(CUP$parser$top-1)).value;
		
              CUP$parser$result = parser.getSymbolFactory().newSymbol("SENTENCIA",2, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-2)), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 5: // SENTENCIA ::= CONJUNTO pcoma 
            {
              String RESULT =null;
		
              CUP$parser$result = parser.getSymbolFactory().newSymbol("SENTENCIA",2, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-1)), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 6: // SENTENCIA ::= INFIJA pcoma 
            {
              String RESULT =null;
		int rleft = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-1)).left;
		int rright = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-1)).right;
		String r = (String)((java_cup.runtime.Symbol) CUP$parser$stack.elementAt(CUP$parser$top-1)).value;
		
              CUP$parser$result = parser.getSymbolFactory().newSymbol("SENTENCIA",2, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-1)), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 7: // CONJUNTO ::= prconj dospuntos id deriva NOTACION 
            {
              String RESULT =null;
		int nombreleft = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-2)).left;
		int nombreright = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-2)).right;
		String nombre = (String)((java_cup.runtime.Symbol) CUP$parser$stack.elementAt(CUP$parser$top-2)).value;
		int nleft = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).left;
		int nright = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).right;
		String n = (String)((java_cup.runtime.Symbol) CUP$parser$stack.peek()).value;
		  if (Interfaz.funcion == "ANALIZAR ENTRADAS") {
                                                                    Conjunto conj = new Conjunto(nombre, n);
                                                                    Expresiones.addConj(conj);
                                                                }
                                                            
              CUP$parser$result = parser.getSymbolFactory().newSymbol("CONJUNTO",5, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-4)), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 8: // NOTACION ::= notacionL 
            {
              String RESULT =null;
		int lleft = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).left;
		int lright = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).right;
		String l = (String)((java_cup.runtime.Symbol) CUP$parser$stack.peek()).value;
		 RESULT = "let"+l; 
              CUP$parser$result = parser.getSymbolFactory().newSymbol("NOTACION",4, ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 9: // NOTACION ::= notacionA 
            {
              String RESULT =null;
		int aleft = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).left;
		int aright = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).right;
		String a = (String)((java_cup.runtime.Symbol) CUP$parser$stack.peek()).value;
		 RESULT = "asc"+a; 
              CUP$parser$result = parser.getSymbolFactory().newSymbol("NOTACION",4, ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 10: // NOTACION ::= notacionD 
            {
              String RESULT =null;
		int dleft = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).left;
		int dright = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).right;
		String d = (String)((java_cup.runtime.Symbol) CUP$parser$stack.peek()).value;
		 RESULT = "dig"+d; 
              CUP$parser$result = parser.getSymbolFactory().newSymbol("NOTACION",4, ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 11: // INFIJA ::= id deriva DEF 
            {
              String RESULT =null;
		int nameleft = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-2)).left;
		int nameright = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-2)).right;
		String name = (String)((java_cup.runtime.Symbol) CUP$parser$stack.elementAt(CUP$parser$top-2)).value;
		int sleft = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).left;
		int sright = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).right;
		NodoTree s = (NodoTree)((java_cup.runtime.Symbol) CUP$parser$stack.peek()).value;
		  if (Interfaz.funcion == "GENERAR AUTOMATAS") {
                                            NodoTree nodoNum = new NodoTree(parser.id, "#", null, null);
                                            parser.id++;
                                            NodoTree nodoI = new NodoTree(parser.id, ".", s, nodoNum);
                                            parser.id++;
                                            Expresiones.setName(name);
                                            Expresiones.loadExp();
                                            Expresiones.tmp.clear();
                                            parser.raiz = nodoI;
                                            TREE arbol = new TREE(parser.raiz, name);
                                            Interfaz.texto_consola+="Aut??matas generados para la expresi??n: "+name+"\n";
                                        }
                                    
              CUP$parser$result = parser.getSymbolFactory().newSymbol("INFIJA",6, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-2)), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 12: // DEF ::= DEF OPM 
            {
              NodoTree RESULT =null;
		int aleft = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-1)).left;
		int aright = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-1)).right;
		NodoTree a = (NodoTree)((java_cup.runtime.Symbol) CUP$parser$stack.elementAt(CUP$parser$top-1)).value;
		
                        RESULT = a;
                    
              CUP$parser$result = parser.getSymbolFactory().newSymbol("DEF",7, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-1)), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 13: // DEF ::= OPM 
            {
              NodoTree RESULT =null;
		int nleft = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).left;
		int nright = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).right;
		NodoTree n = (NodoTree)((java_cup.runtime.Symbol) CUP$parser$stack.peek()).value;
		 
                        RESULT = n;
                    
              CUP$parser$result = parser.getSymbolFactory().newSymbol("DEF",7, ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 14: // OPM ::= multiplicativo OPM 
            {
              NodoTree RESULT =null;
		int mleft = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-1)).left;
		int mright = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-1)).right;
		String m = (String)((java_cup.runtime.Symbol) CUP$parser$stack.elementAt(CUP$parser$top-1)).value;
		int oleft = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).left;
		int oright = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).right;
		NodoTree o = (NodoTree)((java_cup.runtime.Symbol) CUP$parser$stack.peek()).value;
		  if (Interfaz.funcion == "GENERAR AUTOMATAS") {
                                            Expresiones.addTmp(m);
                                            NodoTree nodo = new NodoTree(parser.id, m, o, null);
                                            parser.id++;
                                            RESULT = nodo;
                                        }
                                    
              CUP$parser$result = parser.getSymbolFactory().newSymbol("OPM",8, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-1)), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 15: // OPM ::= operador OPM OPM 
            {
              NodoTree RESULT =null;
		int oleft = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-2)).left;
		int oright = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-2)).right;
		String o = (String)((java_cup.runtime.Symbol) CUP$parser$stack.elementAt(CUP$parser$top-2)).value;
		int aleft = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-1)).left;
		int aright = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-1)).right;
		NodoTree a = (NodoTree)((java_cup.runtime.Symbol) CUP$parser$stack.elementAt(CUP$parser$top-1)).value;
		int bleft = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).left;
		int bright = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).right;
		NodoTree b = (NodoTree)((java_cup.runtime.Symbol) CUP$parser$stack.peek()).value;
		  if (Interfaz.funcion == "GENERAR AUTOMATAS") {
                                            Expresiones.addTmp(o);
                                            NodoTree nodo = new NodoTree(parser.id, o, a, b);
                                            parser.id++;
                                            RESULT = nodo;
                                        }
                                    
              CUP$parser$result = parser.getSymbolFactory().newSymbol("OPM",8, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-2)), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 16: // OPM ::= lexema 
            {
              NodoTree RESULT =null;
		int bleft = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).left;
		int bright = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).right;
		String b = (String)((java_cup.runtime.Symbol) CUP$parser$stack.peek()).value;
		  if (Interfaz.funcion == "GENERAR AUTOMATAS") {
                                        Expresiones.addTmp(b);
                                        NodoTree nodo = new NodoTree(parser.aux, b, null, null);
                                        parser.aux++;
                                        RESULT = nodo;
                                    }
                                
              CUP$parser$result = parser.getSymbolFactory().newSymbol("OPM",8, ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 17: // OPM ::= conjuntoo 
            {
              NodoTree RESULT =null;
		int aleft = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).left;
		int aright = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).right;
		String a = (String)((java_cup.runtime.Symbol) CUP$parser$stack.peek()).value;
		  if (Interfaz.funcion == "GENERAR AUTOMATAS") {
                                        Expresiones.addTmp(a);
                                        NodoTree nodo = new NodoTree(parser.aux, a, null, null);
                                        parser.aux++;
                                        RESULT = nodo;
                                    }
                                
              CUP$parser$result = parser.getSymbolFactory().newSymbol("OPM",8, ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 18: // OPM ::= especial 
            {
              NodoTree RESULT =null;
		int cleft = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).left;
		int cright = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).right;
		String c = (String)((java_cup.runtime.Symbol) CUP$parser$stack.peek()).value;
		  if (Interfaz.funcion == "GENERAR AUTOMATAS") {
                                        Expresiones.addTmp(c);
                                        NodoTree nodo = new NodoTree(parser.aux, c, null, null);
                                        parser.aux++;
                                        RESULT = nodo;
                                    }
                                
              CUP$parser$result = parser.getSymbolFactory().newSymbol("OPM",8, ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 19: // EXPRESION ::= EXPRESION id dospuntos lexema pcoma 
            {
              String RESULT =null;
		int nomleft = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-3)).left;
		int nomright = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-3)).right;
		String nom = (String)((java_cup.runtime.Symbol) CUP$parser$stack.elementAt(CUP$parser$top-3)).value;
		int aleft = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-1)).left;
		int aright = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-1)).right;
		String a = (String)((java_cup.runtime.Symbol) CUP$parser$stack.elementAt(CUP$parser$top-1)).value;
		  if (Interfaz.funcion == "ANALIZAR ENTRADAS") {
                                                            Interfaz.texto_consola+="Expresi??n reconocida: "+a+"\n";
                                                            //Expresiones.add(a);
                                                            parser.val.addCadena(nom, a);
                                                            Interfaz.texto_consola+="JSON de salida generado\n\n";
                                                            }
                                                        
              CUP$parser$result = parser.getSymbolFactory().newSymbol("EXPRESION",3, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-4)), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 20: // EXPRESION ::= id dospuntos lexema pcoma 
            {
              String RESULT =null;
		int nomleft = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-3)).left;
		int nomright = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-3)).right;
		String nom = (String)((java_cup.runtime.Symbol) CUP$parser$stack.elementAt(CUP$parser$top-3)).value;
		int bleft = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-1)).left;
		int bright = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-1)).right;
		String b = (String)((java_cup.runtime.Symbol) CUP$parser$stack.elementAt(CUP$parser$top-1)).value;
		  if (Interfaz.funcion == "ANALIZAR ENTRADAS") {
                                                    Interfaz.texto_consola+="Expresi??n reconocida: "+b+"\n";
                                                    //Expresiones.add(b);
                                                    parser.val.addCadena(nom, b);
                                                    Interfaz.texto_consola+="JSON de salida generado\n\n";
                                                    }
                                                
              CUP$parser$result = parser.getSymbolFactory().newSymbol("EXPRESION",3, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-3)), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /* . . . . . .*/
          default:
            throw new Exception(
               "Invalid action number "+CUP$parser$act_num+"found in internal parse table");

        }
    } /* end of method */

  /** Method splitting the generated action code into several parts. */
  public final java_cup.runtime.Symbol CUP$parser$do_action(
    int                        CUP$parser$act_num,
    java_cup.runtime.lr_parser CUP$parser$parser,
    java.util.Stack            CUP$parser$stack,
    int                        CUP$parser$top)
    throws java.lang.Exception
    {
              return CUP$parser$do_action_part00000000(
                               CUP$parser$act_num,
                               CUP$parser$parser,
                               CUP$parser$stack,
                               CUP$parser$top);
    }
}

}
