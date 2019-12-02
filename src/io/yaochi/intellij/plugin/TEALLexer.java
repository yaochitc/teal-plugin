package io.yaochi.intellij.plugin;

import com.intellij.lexer.FlexAdapter;
import com.intellij.lexer.MergingLexerAdapter;
import com.intellij.psi.tree.TokenSet;
import io.yaochi.intellij.plugin.parser._TEALLexer;

public class TEALLexer extends MergingLexerAdapter {
	public TEALLexer() {
		super(new FlexAdapter(new _TEALLexer()), TokenSet.orSet(TEALParserDefinition.COMMENTS, TEALParserDefinition.WHITESPACES));
	}
}

