package io.yaochi.intellij.plugin;

import com.intellij.lexer.FlexAdapter;

public class TEALLexerAdapter extends FlexAdapter {
	public TEALLexerAdapter() {
		super(new TEALLexer());
	}
}
