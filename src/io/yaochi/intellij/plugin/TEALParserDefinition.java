package io.yaochi.intellij.plugin;

import com.intellij.lang.ASTNode;
import com.intellij.lang.ParserDefinition;
import com.intellij.lang.PsiParser;
import com.intellij.lexer.Lexer;
import com.intellij.openapi.project.Project;
import com.intellij.psi.FileViewProvider;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.TokenType;
import com.intellij.psi.tree.IFileElementType;
import com.intellij.psi.tree.TokenSet;
import org.jetbrains.annotations.NotNull;

public class TEALParserDefinition implements ParserDefinition {
	private static final TokenSet WHITE_SPACES = TokenSet.create(TokenType.WHITE_SPACE);

	@NotNull
	@Override
	public Lexer createLexer(Project project) {
		return new TEALLexerAdapter();
	}

	@NotNull
	public TokenSet getWhitespaceTokens() {
		return WHITE_SPACES;
	}

	@NotNull
	@Override
	public TokenSet getCommentTokens() {
		return null;
	}

	@NotNull
	@Override
	public TokenSet getStringLiteralElements() {
		return null;
	}

	@Override
	public PsiParser createParser(Project project) {
		return null;
	}

	@Override
	public IFileElementType getFileNodeType() {
		return null;
	}

	@Override
	public PsiFile createFile(FileViewProvider fileViewProvider) {
		return null;
	}

	@Override
	public SpaceRequirements spaceExistenceTypeBetweenTokens(ASTNode left, ASTNode right) {
		return SpaceRequirements.MAY;
	}

	@NotNull
	@Override
	public PsiElement createElement(ASTNode astNode) {
		return null;
	}

}
