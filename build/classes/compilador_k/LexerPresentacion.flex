package compilador_k;

%%

%class LexerPresentacion
%int
%unicode
%state COMMENTML, COMMENT
%line
%column

	letter = [a-zA-Z]
	digit = [0-9]
        void = "void"
	var_type = "int"|"char"|"float"|"string"
	space = " " | "\t"
	line_break = "\n" | "\r"
	semicolon = ";"
	colon = ":"
	dot= "."
	comma = ","
	underline = "_"
	par_left = "("
	par_right = ")"
	brack_left = "{"
	brack_right = "}"
	minus = "-"
	plus = "+"
	mult = "*"
	div = "/"
	less = "<"
	greater = ">"
	less_equal = "<="
	greater_equal = ">="
	equal_asig = "="
	equal_comp = "=="
	diff = "!="
	comment_line = "//"
	comment_begin = "/*"
	comment_end = "*/"
	if = "if"
	else = "else"
	for = "for"
	while = "while"
	printf = "printf"
	scanf = "scanf"	
	float_literal = {digit}{dot}{digit}+
	int_literal = [1-9]{digit}* | "0"
	float_literal = {digit}+{dot}{digit}+
	char_literal = \'[^\n\r]\'
	string_literal = \"((\\[ntbrf\\\'\"]) | ([^\"\\\n\r]))*\"
	writeln = "writeln"
	write = "write"
	main = "main"
	end_main = "end main"
	function = "function"
	false = "False"
	true = "True"
	return = "return"
	id = {letter}({underline}?({digit}|{letter}))*
%%
<YYINITIAL>{


{space}			{}
{line_break}            {}
{main}			{System.out.println("Tok "+ yytext() + " reconocido");}
{void}			{System.out.println("Tok "+ yytext() + " reconocido");}
{end_main}		{System.out.println("Tok "+ yytext() + " reconocido");}
{var_type}		{System.out.println("Tok "+ yytext() + " reconocido");}
{semicolon}		{System.out.println("Tok semicolon: "+ yytext() + " reconocido");}
{colon}			{System.out.println("Tok colon: "+ yytext() + " reconocido");}
{dot}			{System.out.println("Tok dot: "+ yytext() + " reconocido");}
{comma}			{System.out.println("Tok comma: "+ yytext() + " reconocido");}
{underline}		{System.out.println("Tok underline: "+ yytext() + " reconocido");}
{par_left}		{System.out.println("Tok par_left: "+ yytext() + " reconocido");}
{par_right}		{System.out.println("Tok par_right: "+ yytext() + " reconocido");}
{brack_left}           {System.out.println("Tok brack_left: "+ yytext() + " reconocido");}
{brack_right}          {System.out.println("Tok brack_right: "+ yytext() + " reconocido");}
{minus}			{System.out.println("Tok minus: "+ yytext() + " reconocido");}
{plus}			{System.out.println("Tok plus: "+ yytext() + " reconocido");}
{mult}			{System.out.println("Tok mult: "+ yytext() + " reconocido");}
{div}			{System.out.println("Tok div: "+ yytext() + " reconocido");}
{less}			{System.out.println("Tok less: "+ yytext() + " reconocido");}
{greater}		{System.out.println("Tok greater: "+ yytext() + " reconocido");}
{less_equal}	{System.out.println("Tok less_equal: "+ yytext() + " reconocido");}
{greater_equal}	{System.out.println("Tok greater_equal: "+ yytext() + " reconocido");}
{equal_asig}	{System.out.println("Tok equal_asig: "+ yytext() + " reconocido");}
{equal_comp}	{System.out.println("Tok equal_comp: "+ yytext() + " reconocido");}
{diff}			{System.out.println("Tok diff: "+ yytext() + " reconocido");}
{comment_begin}	{yybegin (COMMENTML);}
{comment_line}	{yybegin (COMMENT);}
{if}			{System.out.println("Tok "+ yytext() + " reconocido");}
{else}			{System.out.println("Tok "+ yytext() + " reconocido");}
{while}			{System.out.println("Tok "+ yytext() + " reconocido");}
{for}			{System.out.println("Tok "+ yytext() + " reconocido");}
{printf}		{System.out.println("Tok "+ yytext() + " reconocido");}
{scanf}			{System.out.println("Tok "+ yytext() + " reconocido");}
{int_literal}	{System.out.println("Tok int_literal: "+ yytext() + " reconocido");}
{string_literal} {System.out.println("Tok string_literal: "+ yytext() + " reconocido");}
{float_literal}	{System.out.println("Tok float_literal: "+ yytext() + " reconocido");}
{char_literal}	{System.out.println("Tok char_literal: "+ yytext() + " reconocido");}
{writeln}		{System.out.println("Tok "+ yytext() + " reconocido");}
{write}			{System.out.println("Tok "+ yytext() + " reconocido");}
{true}			{System.out.println("Tok "+ yytext() + " reconocido");}
{false}			{System.out.println("Tok "+ yytext() + " reconocido");}
{function}		{System.out.println("Tok "+ yytext() + " reconocido");}
{return}		{System.out.println("Tok "+ yytext() + " reconocido");}
{id}			{System.out.println("Tok id: "+ yytext() + " reconocido");}

.				{System.out.println("Tok " + yytext()+ " no reconocido en la linea: "+ yyline+1 + " columna: "+ yycolumn+1);}
	

}
<COMMENTML>{
	{space}	{}
	{line_break}	{}
	{comment_end} {yybegin(YYINITIAL);}
	. {}
}
<COMMENT>{
	{line_break} {yybegin(YYINITIAL);}
	. {}
}
	
	