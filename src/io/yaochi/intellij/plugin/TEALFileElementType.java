package io.yaochi.intellij.plugin;

import com.intellij.psi.tree.IStubFileElementType;
import io.yaochi.intellij.plugin.subs.TEALFileStub;

public class TEALFileElementType extends IStubFileElementType<TEALFileStub> {
	public static final IStubFileElementType INSTANCE = new TEALFileElementType();

	private TEALFileElementType() {
		super("TEAL_FILE", TEALLanguage.INSTANCE);
	}
}
