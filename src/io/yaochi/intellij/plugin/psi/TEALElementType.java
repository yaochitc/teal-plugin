package io.yaochi.intellij.plugin.psi;

import com.intellij.psi.tree.IElementType;
import io.yaochi.intellij.plugin.TEALLanguage;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

public class TEALElementType extends IElementType {
	public TEALElementType(@NotNull @NonNls String debugName) {
		super(debugName, TEALLanguage.INSTANCE);
	}
}
