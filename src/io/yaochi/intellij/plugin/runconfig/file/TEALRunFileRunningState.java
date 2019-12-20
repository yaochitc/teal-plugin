package io.yaochi.intellij.plugin.runconfig.file;

import com.intellij.execution.ExecutionException;
import com.intellij.execution.process.ProcessHandler;
import com.intellij.execution.runners.ExecutionEnvironment;
import io.yaochi.intellij.plugin.runconfig.TEALRunningState;
import org.jetbrains.annotations.NotNull;

public class TEALRunFileRunningState extends TEALRunningState<TEALRunFileConfiguration> {
	public TEALRunFileRunningState(@NotNull ExecutionEnvironment env) {
		super(env);
	}

	@NotNull
	@Override
	protected ProcessHandler startProcess() throws ExecutionException {
		return null;
	}


}
