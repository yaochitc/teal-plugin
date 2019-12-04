package io.yaochi.intellij.plugin.highlighting;

import com.intellij.lexer.Lexer;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.fileTypes.SyntaxHighlighterBase;
import com.intellij.psi.tree.IElementType;
import io.yaochi.intellij.plugin.TEALLexer;
import io.yaochi.intellij.plugin.TEALParserDefinition;
import io.yaochi.intellij.plugin.psi.TEALTypes;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

import static io.yaochi.intellij.plugin.highlighting.TEALSyntaxHighlightingColors.*;

public class TEALSyntaxHighlighter extends SyntaxHighlighterBase {
	private static final Map<IElementType, TextAttributesKey> ATTRIBUTES = new HashMap<IElementType, TextAttributesKey>();

	static {
		fillMap(ATTRIBUTES, LINE_COMMENT, TEALParserDefinition.LINE_COMMENT);
		fillMap(ATTRIBUTES, IDENTIFIER, TEALTypes.IDENTIFIER);
		fillMap(ATTRIBUTES, TEALParserDefinition.OPERATORS, OPERATOR);
		fillMap(ATTRIBUTES, TEALParserDefinition.KEYWORDS, KEYWORD);
		fillMap(ATTRIBUTES, TEALParserDefinition.NUMBERS, NUMBER);
	}

	@NotNull
	@Override
	public Lexer getHighlightingLexer() {
		return new TEALLexer();
	}

	@NotNull
	@Override
	public TextAttributesKey[] getTokenHighlights(IElementType tokenType) {
		return pack(ATTRIBUTES.get(tokenType));
	}
}
