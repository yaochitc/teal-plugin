package io.yaochi.intellij.plugin.runconfig;

import com.intellij.execution.configurations.ConfigurationFactory;
import com.intellij.execution.configurations.ModuleBasedConfiguration;
import com.intellij.execution.configurations.RunConfigurationWithSuppressedDefaultDebugAction;
import com.intellij.execution.runners.RunConfigurationWithSuppressedDefaultRunAction;

public abstract class TEALRunConfigurationBase<RunningState extends TEALRunningState>
		extends ModuleBasedConfiguration<TEALModuleBasedConfiguration, RunningState> implements RunConfigurationWithSuppressedDefaultRunAction,
		RunConfigurationWithSuppressedDefaultDebugAction {
	public TEALRunConfigurationBase(String name, TEALModuleBasedConfiguration configurationModule, ConfigurationFactory factory) {
		super(name, configurationModule, factory);
	}
}
