package io.yaochi.intellij.plugin;

import com.intellij.icons.AllIcons;
import com.intellij.openapi.util.IconLoader;
import com.intellij.ui.LayeredIcon;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

public interface TEALIcons {
	Icon FILE = IconLoader.getIcon("/io/yaochi/intellij/plugin/icons/icon.png");

	Icon APPLICATION_RUN = Helper.createIconWithShift(FILE, AllIcons.Nodes.RunnableMark);

	class Helper {
		private Helper() {
		}

		@NotNull
		public static LayeredIcon createIconWithShift(@NotNull Icon base, Icon mark) {
			LayeredIcon icon = new LayeredIcon(2) {
				@Override
				public int getIconHeight() {
					return base.getIconHeight();
				}
			};
			icon.setIcon(base, 0);
			icon.setIcon(mark, 1, 0, base.getIconWidth() / 2);
			return icon;
		}
	}
}
