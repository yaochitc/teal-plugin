package io.yaochi.intellij.plugin.util;

import com.intellij.openapi.module.Module;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class TEALExecutor {
	@NotNull
	private final Project myProject;
	@Nullable
	private final Module myModule;

	private TEALExecutor(@NotNull Project project, @Nullable Module module) {
		myProject = project;
		myModule = module;
	}

	public static TEALExecutor in(@Nullable Module module) {
		Project project = module.getProject();
		return new TEALExecutor(project, module);
	}
}
