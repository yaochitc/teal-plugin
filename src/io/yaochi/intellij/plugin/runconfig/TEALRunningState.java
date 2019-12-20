package io.yaochi.intellij.plugin.runconfig;

import com.intellij.execution.configurations.CommandLineState;
import com.intellij.execution.runners.ExecutionEnvironment;
import org.jetbrains.annotations.NotNull;

public abstract class TEALRunningState<T extends TEALRunConfigurationBase<?>> extends CommandLineState {
	public TEALRunningState(@NotNull ExecutionEnvironment env) {
		super(env);
	}
}
