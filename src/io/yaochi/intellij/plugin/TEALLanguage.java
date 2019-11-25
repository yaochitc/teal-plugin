package io.yaochi.intellij.plugin;

import com.intellij.lang.Language;

public class TEALLanguage extends Language {
	public static final TEALLanguage INSTANCE = new TEALLanguage();

	private TEALLanguage() {
		super("TEAL");
	}
}
