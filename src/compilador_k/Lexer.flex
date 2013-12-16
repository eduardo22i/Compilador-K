package compilador_k;
import java_cup.runtime.Symbol;
%%

%class Lexer
%int
%unicode
%state COMMENTL, COMMENT
%line
%column
%cup
%caseless

   
	letter = [a-zA-Z]
       	digit = [0-9]
        void =  "void"
       	space = " " | "\t"       
        int = "int"
        float = "float"
        String = "String"
        char = "char"
	line_break = "\n" | "\r"
	semicolon = ";"
	dot= "."
	comma = ","
	underline = "_"
	par_left = "("
	par_right = ")"
	brack_left = "{"
	brack_right = "}"
       
	minus = "-"
	plus = "+"
        plus_one= "++"
        minus_one="--"
        incrementadores = {plus_one}|{minus_one}
	mult = "*"
	div = "/"
        operadores = {less}|{greater}|{less_equal}|{greater_equal}|{equal_comp}|{diff}
	less = "<"
	greater = ">"
	less_equal = "<="
	greater_equal = ">="
        add_more = "+="
        sub_less = "-="
        assignations = {add_more}|{sub_less}|{equal_asig}
	equal_asig = "="
	equal_comp = "=="
	diff = "!="
	comment_line = "//"
	comment_begin = "/*"
	comment_end = "*/"
	if = "if"
        elseif = "else if"
	else = "else"
	for = "for"
	while = "while"
	printf = "printf"
	scanf = "scanf"
        ampersand = "&"
        or = "||"
        and = "&&"
	id = {letter}({underline}?({digit}|{letter}))*
	float_literal = {digit}{dot}{digit}+
	int_literal = [0-9]{digit}* | "0"
	float_literal = {digit}+{dot}{digit}+
	char_literal = \'[^\n\r]\'
	string_literal = \"((\\[ntbrf\\\'\"]) | ([^\"\\\n\r]))*\"
	main = "main"
	void = "void"
	return = "return"
%%
<YYINITIAL>{
{int}                           {return new Symbol(sym.INT, yyline, yycolumn, yytext());}
{float}                         {return new Symbol(sym.FLOAT, yyline, yycolumn, yytext());}
{String}                        {return new Symbol(sym.STRING, yyline, yycolumn, yytext());}
{char}                          {return new Symbol(sym.CHAR, yyline, yycolumn, yytext());}
{int_literal}                   {return new Symbol(sym.INT_LITERAL, yyline, yycolumn, yytext());}
{float_literal}                 {return new Symbol(sym.FLOAT_LITERAL, yyline, yycolumn, yytext());}
{char_literal}                  {return new Symbol(sym.CHAR_LITERAL, yyline, yycolumn, yytext());}
{space}				{}
{line_break}                    {}
{or}                            {return new Symbol(sym.OR, yyline, yycolumn, yytext());}
{and}                           {return new Symbol(sym.AND, yyline, yycolumn, yytext());}
{ampersand}                     {return new Symbol(sym.AMPERSAND, yyline, yycolumn, yytext());}
{string_literal}                {return new Symbol(sym.STRING_LITERAL, yyline, yycolumn, yytext());}
{incrementadores}               {return new Symbol(sym.INCREMENTADORES, yyline, yycolumn, yytext());}
{semicolon}			{return new Symbol(sym.SEMICOLON, yyline, yycolumn, yytext());}
{comma}				{return new Symbol(sym.COMMA, yyline, yycolumn, yytext());}
{par_left}			{return new Symbol(sym.PAR_LEFT, yyline, yycolumn, yytext());}
{par_right}			{return new Symbol(sym.PAR_RIGHT, yyline, yycolumn, yytext());}
{brack_left}                    {return new Symbol(sym.BRACK_LEFT, yyline, yycolumn, yytext());}
{brack_right}                   {return new Symbol(sym.BRACK_RIGHT, yyline, yycolumn, yytext());}
{assignations}                  {return new Symbol(sym.ASSIGNATIONS, yyline, yycolumn, yytext());}
{plus}                          {return new Symbol(sym.PLUS, yyline, yycolumn, yytext());}
{minus}                         {return new Symbol(sym.MINUS, yyline, yycolumn, yytext());}
{div}                           {return new Symbol(sym.DIV, yyline, yycolumn, yytext());}
{mult}                          {return new Symbol(sym.MULT, yyline, yycolumn, yytext());}

{operadores}                    {return new Symbol(sym.OPERADORES, yyline, yycolumn, yytext());}
{comment_begin}             	{yybegin(COMMENTL);}
{comment_line}                  {yybegin(COMMENT);}
{elseif}                        {return new Symbol(sym.ELSEIF, yyline, yycolumn, yytext());}
{if}				{return new Symbol(sym.IF, yyline, yycolumn, yytext());}
{else}				{return new Symbol(sym.ELSE, yyline, yycolumn, yytext());}
{for}				{return new Symbol(sym.FOR, yyline, yycolumn, yytext());}
{while}				{return new Symbol(sym.WHILE, yyline, yycolumn, yytext());}
{printf}			{return new Symbol(sym.PRINTF, yyline, yycolumn, yytext());}
{scanf}				{return new Symbol(sym.SCANF, yyline, yycolumn, yytext());}

{main}				{return new Symbol(sym.MAIN, yyline, yycolumn, yytext());}
{void}				{return new Symbol(sym.VOID, yyline, yycolumn, yytext());}
{return}			{return new Symbol(sym.RETURN, yyline, yycolumn, yytext());}
{id}				{return new Symbol(sym.ID, yyline, yycolumn, yytext());}

.				{Interfaz.salida.setText(Interfaz.salida.getText() + "Error lexico, Token " + yytext()+ " no reconocido en la linea: "+ (yyline+1) + " columna: "+ (yycolumn+1)+ "\n");}
}

<COMMENTL>{
	{space}	{}
	{line_break}	{}
	{comment_end} {yybegin(YYINITIAL);}
	. {}
}
<COMMENT>{
	{line_break} {yybegin(YYINITIAL);}
	. {}
}

	
	