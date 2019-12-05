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

STRING = [^\"\\\n\r]*

%state MAYBE_SEMICOLON
%state MAYBE_STRING


%%
<YYINITIAL> {
  {WS}                 { return WS; }
  {NL}+                { return NLS; }

  {LINE_COMMENT}       { return LINE_COMMENT; }

  "b32"                { yybegin(MAYBE_STRING); return B32; }
  "b64"                { yybegin(MAYBE_STRING); return B64; }
  "base32"             { yybegin(MAYBE_STRING); return BASE32; }
  "base64"             { yybegin(MAYBE_STRING); return BASE64; }
  "sha256"             { return SHA256; }
  "keccak256"          { return KECCAK256; }
  "sha512_256"         { return SHA512_256; }
  "ed25519verify"      { return ED25519VERIFY; }
  "+"                  { yybegin(MAYBE_SEMICOLON); return PLUS; }
  "-"                  { yybegin(MAYBE_SEMICOLON); return MINUS; }
  "/"                  { yybegin(MAYBE_SEMICOLON); return DIV; }
  "*"                  { yybegin(MAYBE_SEMICOLON); return MUL; }
  "<"                  { yybegin(MAYBE_SEMICOLON); return LESS; }
  ">"                  { yybegin(MAYBE_SEMICOLON); return GREATER; }
  "<="                 { yybegin(MAYBE_SEMICOLON); return LESS_OR_EQUAL; }
  ">="                 { yybegin(MAYBE_SEMICOLON); return GREATER_OR_EQUAL; }
  "&&"                 { yybegin(MAYBE_SEMICOLON); return COND_AND; }
  "||"                 { yybegin(MAYBE_SEMICOLON); return COND_OR; }
  "=="                 { yybegin(MAYBE_SEMICOLON); return EQ; }
  "!="                 { yybegin(MAYBE_SEMICOLON); return NOT_EQ; }
  "!"                  { yybegin(MAYBE_SEMICOLON); return NOT; }
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
  "err"                { yybegin(MAYBE_SEMICOLON); return ERR; }
  "bnz"                { yybegin(MAYBE_SEMICOLON); return BNZ; }
  "pop"                { yybegin(MAYBE_SEMICOLON); return POP; }
  "dup"                { yybegin(MAYBE_SEMICOLON); return DUP; }
  "Sender"             { yybegin(MAYBE_SEMICOLON); return SENDER; }
  "Fee"                { yybegin(MAYBE_SEMICOLON); return FEE; }
  "FirstValid"         { yybegin(MAYBE_SEMICOLON); return FIRSTVALID; }
  "FirstValidTime"     { yybegin(MAYBE_SEMICOLON); return FIRSTVALIDTIME; }
  "LastValid"          { yybegin(MAYBE_SEMICOLON); return LASTVALID; }
  "Note"               { yybegin(MAYBE_SEMICOLON); return NOTE; }
  "Lease"              { yybegin(MAYBE_SEMICOLON); return LEASE; }
  "Receiver"           { yybegin(MAYBE_SEMICOLON); return RECEIVER; }
  "Amount"             { yybegin(MAYBE_SEMICOLON); return AMOUNT; }
  "CloseRemainderTo"   { yybegin(MAYBE_SEMICOLON); return CLOSEREMAINDERTO; }
  "VotePK"             { yybegin(MAYBE_SEMICOLON); return VOTEPK; }
  "SelectionPK"        { yybegin(MAYBE_SEMICOLON); return SELECTIONPK; }
  "VoteFirst"          { yybegin(MAYBE_SEMICOLON); return VOTEFIRST; }
  "VoteLast"           { yybegin(MAYBE_SEMICOLON); return VOTELAST; }
  "VoteKeyDilution"    { yybegin(MAYBE_SEMICOLON); return VOTEKEYDILUTION; }
  "Type"               { yybegin(MAYBE_SEMICOLON); return TYPE; }
  "TypeEnum"           { yybegin(MAYBE_SEMICOLON); return TYPEENUM; }
  "XferAsset"          { yybegin(MAYBE_SEMICOLON); return XFERASSET; }
  "AssetAmount"        { yybegin(MAYBE_SEMICOLON); return ASSETAMOUNT; }
  "AssetSender"        { yybegin(MAYBE_SEMICOLON); return ASSETSENDER; }
  "AssetReceiver"      { yybegin(MAYBE_SEMICOLON); return ASSETRECEIVER; }
  "AssetCloseTo"       { yybegin(MAYBE_SEMICOLON); return ASSETCLOSETO; }
  "GroupIndex"         { yybegin(MAYBE_SEMICOLON); return GROUPINDEX; }
  "TxID"               { yybegin(MAYBE_SEMICOLON); return TXID; }
  "MinTxnFee"          { yybegin(MAYBE_SEMICOLON); return MINTXNFEE; }
  "MinBalance"         { yybegin(MAYBE_SEMICOLON); return MINBALANCE; }
  "MaxTxnLife"         { yybegin(MAYBE_SEMICOLON); return MAXTXNLIFE; }
  "ZeroAddress"        { yybegin(MAYBE_SEMICOLON); return ZEROADDRESS; }
  "GroupSize"          { yybegin(MAYBE_SEMICOLON); return GROUPSIZE; }

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

<MAYBE_STRING> {
  {STRING}             { return STRING; }
  {WS}                 { return WS; }
  {NL}                 { yybegin(YYINITIAL); yypushback(yytext().length()); return SEMICOLON_SYNTHETIC; }
  {LINE_COMMENT}       { return LINE_COMMENT; }
  .                    { yybegin(YYINITIAL); yypushback(yytext().length()); }
}
