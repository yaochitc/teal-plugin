package io.yaochi.intellij.plugin.subs;

import com.intellij.psi.stubs.PsiFileStubImpl;
import io.yaochi.intellij.plugin.psi.TEALFile;

public class TEALFileStub extends PsiFileStubImpl<TEALFile> {
	public TEALFileStub(TEALFile file) {
		super(file);
	}
}
