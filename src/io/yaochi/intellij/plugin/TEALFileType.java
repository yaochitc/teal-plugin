package io.yaochi.intellij.plugin;

import com.intellij.openapi.fileTypes.LanguageFileType;
import org.jetbrains.annotations.*;

import javax.swing.*;

public class TEALFileType extends LanguageFileType {
	public static final TEALFileType INSTANCE = new TEALFileType();

	protected TEALFileType() {
		super(TEALLanguage.INSTANCE);
	}

	@NotNull
	@Override
	public String getName() {
		return "TEAL file";
	}

	@NotNull
	@Override
	public String getDescription() {
		return "TEAL language file";
	}

	@NotNull
	@Override
	public String getDefaultExtension() {
		return "teal";
	}

	@Nullable
	@Override
	public Icon getIcon() {
		return TEALIcons.FILE;
	}
}
