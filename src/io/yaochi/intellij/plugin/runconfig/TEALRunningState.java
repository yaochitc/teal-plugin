package io.yaochi.intellij.plugin.runconfig;

import com.intellij.execution.ExecutionException;
import com.intellij.execution.configurations.CommandLineState;
import com.intellij.execution.process.ProcessHandler;
import com.intellij.execution.runners.ExecutionEnvironment;
import com.intellij.openapi.module.Module;
import io.yaochi.intellij.plugin.util.TEALExecutor;
import org.jetbrains.annotations.NotNull;

public abstract class TEALRunningState<T extends TEALRunConfigurationBase<?>> extends CommandLineState {
	@NotNull
	protected final Module myModule;

	@NotNull
	public T getConfiguration() {
		return myConfiguration;
	}

	@NotNull
	protected final T myConfiguration;

	public TEALRunningState(@NotNull ExecutionEnvironment env, @NotNull Module module, @NotNull T configuration) {
		super(env);
		myModule = module;
		myConfiguration = configuration;
	}

	@NotNull
	@Override
	protected ProcessHandler startProcess() throws ExecutionException {
		TEALExecutor executor = patchExecutor(createCommonExecutor());
		return null;
	}

	@NotNull
	public TEALExecutor createCommonExecutor() {
		return TEALExecutor.in(myModule);
	}

	protected TEALExecutor patchExecutor(@NotNull TEALExecutor executor) throws ExecutionException {
		return executor;
	}
}
