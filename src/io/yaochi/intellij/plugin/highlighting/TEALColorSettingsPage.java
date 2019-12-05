package io.yaochi.intellij.plugin.highlighting;

import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.fileTypes.SyntaxHighlighter;
import com.intellij.openapi.options.colors.AttributesDescriptor;
import com.intellij.openapi.options.colors.ColorDescriptor;
import com.intellij.openapi.options.colors.ColorSettingsPage;
import io.yaochi.intellij.plugin.TEALFileType;
import io.yaochi.intellij.plugin.TEALIcons;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.util.Map;

import static io.yaochi.intellij.plugin.highlighting.TEALSyntaxHighlightingColors.*;

public class TEALColorSettingsPage implements ColorSettingsPage {
	private static final AttributesDescriptor[] DESCRIPTORS = new AttributesDescriptor[]{
			new AttributesDescriptor("Line comment", LINE_COMMENT),
			new AttributesDescriptor("Keyword", KEYWORD),
			new AttributesDescriptor("Identifier", IDENTIFIER),
			new AttributesDescriptor("String", STRING),
			new AttributesDescriptor("Number", NUMBER),
			new AttributesDescriptor("Operator", OPERATOR),
			new AttributesDescriptor("Constant", CONSTANT),
	};

	@Nullable
	@Override
	public Icon getIcon() {
		return TEALIcons.FILE;
	}

	@NotNull
	@Override
	public SyntaxHighlighter getHighlighter() {
		return new TEALSyntaxHighlighter();
	}

	@NotNull
	@Override
	public String getDemoText() {
		return null;
	}

	@Nullable
	@Override
	public Map<String, TextAttributesKey> getAdditionalHighlightingTagToDescriptorMap() {
		return null;
	}

	@NotNull
	@Override
	public AttributesDescriptor[] getAttributeDescriptors() {
		return DESCRIPTORS;
	}

	@NotNull
	@Override
	public ColorDescriptor[] getColorDescriptors() {
		return ColorDescriptor.EMPTY_ARRAY;
	}

	@NotNull
	@Override
	public String getDisplayName() {
		return TEALFileType.INSTANCE.getName();
	}
}
