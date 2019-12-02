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

EOL=\R
WHITE_SPACE=\s+


%%
<YYINITIAL> {
  {WHITE_SPACE}        { return WHITE_SPACE; }

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
  "intc"               { return INTC; }
  "intc_0"             { return INTC_0; }
  "intc_1"             { return INTC_1; }
  "intc_2"             { return INTC_2; }
  "intc_3"             { return INTC_3; }
  "bytecblock"         { return BYTECBLOCK; }
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
  "int"                { return INT; }
  "byte"               { return BYTE; }
  "base64"             { return BASE64; }
  "string"             { return STRING; }
  "bytes"              { return BYTES; }


}

[^] { return BAD_CHARACTER; }
