package io.yaochi.intellij.plugin;

import com.intellij.lang.ASTNode;
import com.intellij.lang.ParserDefinition;
import com.intellij.lang.PsiParser;
import com.intellij.lexer.Lexer;
import com.intellij.openapi.project.Project;
import com.intellij.psi.FileViewProvider;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.tree.IFileElementType;
import com.intellij.psi.tree.TokenSet;
import io.yaochi.intellij.plugin.parser.TEALParser;
import io.yaochi.intellij.plugin.psi.TEALFile;
import io.yaochi.intellij.plugin.psi.TEALTokenType;
import org.jetbrains.annotations.NotNull;

import static io.yaochi.intellij.plugin.psi.TEALTypes.*;

public class TEALParserDefinition implements ParserDefinition {
	public static final IElementType LINE_COMMENT = new TEALTokenType("TEAL_LINE_COMMENT");

	public static final IElementType WS = new TEALTokenType("TEAL_WHITESPACE");
	public static final IElementType NLS = new TEALTokenType("TEAL_WS_NEW_LINES");

	public static final TokenSet WHITESPACES = TokenSet.create(WS, NLS);
	public static final TokenSet COMMENTS = TokenSet.create(LINE_COMMENT);
	public static final TokenSet STRING_LITERALS = TokenSet.create(STRING);
	public static final TokenSet NUMBERS = TokenSet.create(RAW_HEX, RAW_INT, RAW_OCT);
	public static final TokenSet KEYWORDS = TokenSet.create(
			INT, BYTE, ARG, TXN, GLOBAL, GTXN, LOAD, STORE, ERR, BNZ, POP, DUP);
	public static final TokenSet OPERATORS = TokenSet.create(
			PLUS, MINUS, DIV, MUL, LESS, GREATER, LESS_OR_EQUAL, GREATER_OR_EQUAL,
			COND_AND, COND_OR, EQ, NOT_EQ, NOT, BIT_OR, BIT_AND, BIT_XOR, BIT_NOT);

	@NotNull
	@Override
	public Lexer createLexer(Project project) {
		return new TEALLexer();
	}

	@NotNull
	public TokenSet getWhitespaceTokens() {
		return WHITESPACES;
	}

	@NotNull
	@Override
	public TokenSet getCommentTokens() {
		return COMMENTS;
	}

	@NotNull
	@Override
	public TokenSet getStringLiteralElements() {
		return STRING_LITERALS;
	}

	@Override
	public PsiParser createParser(Project project) {
		return new TEALParser();
	}

	@Override
	public IFileElementType getFileNodeType() {
		return TEALFileElementType.INSTANCE;
	}

	@Override
	public PsiFile createFile(FileViewProvider fileViewProvider) {
		return new TEALFile(fileViewProvider);
	}

	@Override
	public SpaceRequirements spaceExistenceTypeBetweenTokens(ASTNode left, ASTNode right) {
		return SpaceRequirements.MAY;
	}

	@NotNull
	@Override
	public PsiElement createElement(ASTNode node) {
		return Factory.createElement(node);
	}

}
