package io.yaochi.intellij.plugin.highlighting;

import com.intellij.openapi.editor.DefaultLanguageHighlighterColors;
import com.intellij.openapi.editor.colors.TextAttributesKey;

import static com.intellij.openapi.editor.colors.TextAttributesKey.createTextAttributesKey;


public class TEALSyntaxHighlightingColors {
	public static final TextAttributesKey LINE_COMMENT = createTextAttributesKey("TEAL_LINE_COMMENT", DefaultLanguageHighlighterColors.LINE_COMMENT);

	private TEALSyntaxHighlightingColors() {
	}
}
