package io.yaochi.intellij.plugin.psi;

import com.intellij.extapi.psi.PsiFileBase;
import com.intellij.openapi.fileTypes.FileType;
import com.intellij.psi.FileViewProvider;
import io.yaochi.intellij.plugin.TEALFileType;
import io.yaochi.intellij.plugin.TEALLanguage;
import org.jetbrains.annotations.NotNull;

public class TEALFile extends PsiFileBase {
	public TEALFile(@NotNull FileViewProvider viewProvider) {
		super(viewProvider, TEALLanguage.INSTANCE);
	}

	@NotNull
	@Override
	public FileType getFileType() {
		return TEALFileType.INSTANCE;
	}
}
