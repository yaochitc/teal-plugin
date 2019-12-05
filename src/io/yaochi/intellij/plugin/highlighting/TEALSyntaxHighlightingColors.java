package io.yaochi.intellij.plugin.highlighting;

import com.intellij.openapi.editor.DefaultLanguageHighlighterColors;
import com.intellij.openapi.editor.colors.TextAttributesKey;

import static com.intellij.openapi.editor.colors.TextAttributesKey.createTextAttributesKey;


public class TEALSyntaxHighlightingColors {
	public static final TextAttributesKey LINE_COMMENT = createTextAttributesKey("TEAL_LINE_COMMENT", DefaultLanguageHighlighterColors.LINE_COMMENT);
	public static final TextAttributesKey KEYWORD = createTextAttributesKey("TEAL_KEYWORD", DefaultLanguageHighlighterColors.KEYWORD);
	public static final TextAttributesKey STRING = createTextAttributesKey("TEAL_STRING", DefaultLanguageHighlighterColors.STRING);
	public static final TextAttributesKey NUMBER = createTextAttributesKey("TEAL_NUMBER", DefaultLanguageHighlighterColors.NUMBER);
	public static final TextAttributesKey OPERATOR = createTextAttributesKey("TEAL_OPERATOR", DefaultLanguageHighlighterColors.OPERATION_SIGN);
	public static final TextAttributesKey IDENTIFIER = createTextAttributesKey("TEAL_IDENTIFIER", DefaultLanguageHighlighterColors.IDENTIFIER);
	public static final TextAttributesKey CONSTANT = createTextAttributesKey("GO_CONSTANT", DefaultLanguageHighlighterColors.CONSTANT);

	private TEALSyntaxHighlightingColors() {
	}
}
