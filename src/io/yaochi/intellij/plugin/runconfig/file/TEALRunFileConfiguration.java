package io.yaochi.intellij.plugin.runconfig.file;

import com.intellij.execution.ExecutionException;
import com.intellij.execution.Executor;
import com.intellij.execution.configurations.ConfigurationFactory;
import com.intellij.execution.configurations.ConfigurationType;
import com.intellij.execution.configurations.RunConfiguration;
import com.intellij.execution.configurations.RunProfileState;
import com.intellij.execution.runners.ExecutionEnvironment;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.options.SettingsEditor;
import com.intellij.openapi.project.Project;
import io.yaochi.intellij.plugin.runconfig.TEALModuleBasedConfiguration;
import io.yaochi.intellij.plugin.runconfig.TEALRunConfigurationBase;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;

public class TEALRunFileConfiguration extends TEALRunConfigurationBase<TEALRunFileRunningState> {
	public TEALRunFileConfiguration(Project project, String name, @NotNull ConfigurationType configurationType) {
		super(name, new TEALModuleBasedConfiguration(project), configurationType.getConfigurationFactories()[0]);
	}

	@Override
	public Collection<Module> getValidModules() {
		return null;
	}

	@NotNull
	@Override
	public SettingsEditor<? extends RunConfiguration> getConfigurationEditor() {
		return null;
	}

	@Nullable
	@Override
	public RunProfileState getState(@NotNull Executor executor, @NotNull ExecutionEnvironment executionEnvironment) throws ExecutionException {
		return null;
	}
}
