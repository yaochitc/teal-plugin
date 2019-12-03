package io.yaochi.intellij.plugin.parser;

import com.intellij.lexer.FlexLexer;
import com.intellij.psi.tree.IElementType;
import static io.yaochi.intellij.plugin.psi.TEALTypes.*;
import static com.intellij.psi.TokenType.BAD_CHARACTER;
import static io.yaochi.intellij.plugin.TEALParserDefinition.*;

%%

%{
  public _TEALLexer() {
    this((java.io.Reader)null);
  }
%}

%class _TEALLexer
%implements FlexLexer
%unicode
%public

%function advance
%type IElementType

NL = \R
WS = [ \t\f]

LINE_COMMENT = "//" [^\r\n]*

LETTER = [:letter:] | "_"
DIGIT =  [:digit:]

HEX_DIGIT = [0-9A-Fa-f]
INT_DIGIT = [0-9]
OCT_DIGIT = [0-7]

NUM_INT = "0" | ([1-9] {INT_DIGIT}*)
NUM_HEX = ("0x" | "0X") {HEX_DIGIT}+
NUM_OCT = "0" {OCT_DIGIT}+

IDENT = {LETTER} ({LETTER} | {DIGIT} )*

%state MAYBE_SEMICOLON

%%
<YYINITIAL> {
  {WS}                 { return WS; }
  {NL}+                { return NLS; }

  {LINE_COMMENT}       { return LINE_COMMENT; }

  "err"                { return ERR; }
  "sha256"             { return SHA256; }
  "keccak256"          { return KECCAK256; }
  "sha512_256"         { return SHA512_256; }
  "ed25519verify"      { return ED25519VERIFY; }
  "+"                  { return PLUS; }
  "-"                  { return MINUS; }
  "/"                  { return DIV; }
  "*"                  { return MUL; }
  "<"                  { return LESS; }
  ">"                  { return GREATER; }
  "<="                 { return LESS_OR_EQUAL; }
  ">="                 { return GREATER_OR_EQUAL; }
  "&&"                 { return COND_AND; }
  "||"                 { return COND_OR; }
  "=="                 { return EQ; }
  "!="                 { return NOT_EQ; }
  "!"                  { return NOT; }
  "len"                { return LEN; }
  "itob"               { return ITOB; }
  "btoi"               { return BTOI; }
  "%"                  { return MODULO; }
  "|"                  { return BIT_OR; }
  "&"                  { return BIT_AND; }
  "^"                  { return BIT_XOR; }
  "~"                  { return BIT_NOT; }
  "mulw"               { return MULW; }
  "intcblock"          { return INTCBLOCK; }
  "int"                { return INT; }
  "intc"               { return INTC; }
  "intc_0"             { return INTC_0; }
  "intc_1"             { return INTC_1; }
  "intc_2"             { return INTC_2; }
  "intc_3"             { return INTC_3; }
  "bytecblock"         { return BYTECBLOCK; }
  "byte"               { return BYTE; }
  "bytec"              { return BYTEC; }
  "bytec_0"            { return BYTEC_0; }
  "bytec_1"            { return BYTEC_1; }
  "bytec_2"            { return BYTEC_2; }
  "bytec_3"            { return BYTEC_3; }
  "arg"                { return ARG; }
  "arg_0"              { return ARG_0; }
  "arg_1"              { return ARG_1; }
  "arg_2"              { return ARG_2; }
  "arg_3"              { return ARG_3; }
  "txn"                { return TXN; }
  "global"             { return GLOBAL; }
  "gtxn"               { return GTXN; }
  "load"               { return LOAD; }
  "store"              { return STORE; }
  "bnz"                { return BNZ; }
  "pop"                { return POP; }
  "dup"                { return DUP; }
  "byte"               { return BYTE; }

  {IDENT}              { yybegin(MAYBE_SEMICOLON); return IDENTIFIER; }

  {NUM_OCT}            { yybegin(MAYBE_SEMICOLON); return RAW_OCT; }
  {NUM_HEX}            { yybegin(MAYBE_SEMICOLON); return RAW_HEX; }
  {NUM_INT}            { yybegin(MAYBE_SEMICOLON); return RAW_INT; }

  .                    { return BAD_CHARACTER; }
}

<MAYBE_SEMICOLON> {
  {WS}                 { return WS; }
  {NL}                 { yybegin(YYINITIAL); yypushback(yytext().length()); return SEMICOLON_SYNTHETIC; }
  {LINE_COMMENT}       { return LINE_COMMENT; }
  .                    { yybegin(YYINITIAL); yypushback(yytext().length()); }
}
