package io.yaochi.intellij.plugin.runconfig;

import com.intellij.compiler.options.CompileStepBeforeRun;
import com.intellij.execution.BeforeRunTask;
import com.intellij.execution.configurations.ConfigurationFactory;
import com.intellij.execution.configurations.ConfigurationType;
import com.intellij.openapi.util.Key;

public abstract class TEALConfigurationFactoryBase extends ConfigurationFactory {
	protected TEALConfigurationFactoryBase(ConfigurationType type) {
		super(type);
	}

	@Override
	public void configureBeforeRunTaskDefaults(Key<? extends BeforeRunTask> providerID, BeforeRunTask task) {
		super.configureBeforeRunTaskDefaults(providerID, task);
		if (providerID == CompileStepBeforeRun.ID) {
			task.setEnabled(false);
		}
	}
}
