package io.yaochi.intellij.plugin.parser;

import com.intellij.lexer.FlexLexer;
import com.intellij.psi.tree.IElementType;

import static com.intellij.psi.TokenType.BAD_CHARACTER;
import static com.intellij.psi.TokenType.WHITE_SPACE;
import static io.yaochi.intellij.plugin.psi.TEALTypes.*;

%%

%{
  public _TEALLexer() {
    this((java.io.Reader)null);
  }
%}

%public
%class _TEALLexer
%implements FlexLexer
%function advance
%type IElementType
%unicode

NL = \R
WS = [ \t\f]

LINE_COMMENT = "//" [^\r\n]*
MULTILINE_COMMENT = "/*" ( ([^"*"]|[\r\n])* ("*"+ [^"*""/"] )? )* ("*" | "*"+"/")?

HEX_DIGIT = [0-9A-Fa-f]
INT_DIGIT = [0-9]
OCT_DIGIT = [0-7]

NUM_INT = "0" | ([1-9] {INT_DIGIT}*)
NUM_HEX = ("0x" | "0X") {HEX_DIGIT}+
NUM_OCT = "0" {OCT_DIGIT}+

STR =      "\""
STRING = {STR} ( [^\"\\\n\r] | "\\" ("\\" | {STR} | {ESCAPES} | [0-8xuU] ) )* {STR}?
ESCAPES = [abfnrtv]

%%
<YYINITIAL> {
  {WS}                 { return WS; }
  {NL}+                { return NLS; }

  {LINE_COMMENT}       { return LINE_COMMENT; }
  {MULTILINE_COMMENT}+ { return MULTILINE_COMMENT; }

  {STRING}             { yybegin(MAYBE_SEMICOLON); return STRING; }

  "err"                { return ERROR; }
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
  "base64"             { return BASE64; }

  {NUM_OCT}            { yybegin(MAYBE_SEMICOLON); return RAW_OCT; }
  {NUM_HEX}            { yybegin(MAYBE_SEMICOLON); return RAW_HEX; }
  {NUM_INT}            { yybegin(MAYBE_SEMICOLON); return RAW_INT; }
}

[^] { return BAD_CHARACTER; }
