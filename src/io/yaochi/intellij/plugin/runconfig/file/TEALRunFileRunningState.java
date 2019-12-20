package io.yaochi.intellij.plugin.runconfig.file;

import com.intellij.execution.runners.ExecutionEnvironment;
import com.intellij.openapi.module.Module;
import io.yaochi.intellij.plugin.runconfig.TEALRunningState;
import org.jetbrains.annotations.NotNull;

public class TEALRunFileRunningState extends TEALRunningState<TEALRunFileConfiguration> {
	public TEALRunFileRunningState(@NotNull ExecutionEnvironment env, @NotNull Module module, TEALRunFileConfiguration configuration) {
		super(env, module, configuration);
	}
}
