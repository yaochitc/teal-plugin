package io.yaochi.intellij.plugin.runconfig;

import com.intellij.execution.configurations.ConfigurationFactory;
import com.intellij.execution.configurations.ModuleBasedConfiguration;
import com.intellij.execution.configurations.RunConfigurationWithSuppressedDefaultDebugAction;
import com.intellij.execution.runners.RunConfigurationWithSuppressedDefaultRunAction;
import com.intellij.openapi.util.InvalidDataException;
import com.intellij.openapi.util.WriteExternalException;
import org.jdom.Element;

public abstract class TEALRunConfigurationBase<RunningState extends TEALRunningState>
		extends ModuleBasedConfiguration<TEALModuleBasedConfiguration, RunningState> implements RunConfigurationWithSuppressedDefaultRunAction,
		RunConfigurationWithSuppressedDefaultDebugAction {
	public TEALRunConfigurationBase(String name, TEALModuleBasedConfiguration configurationModule, ConfigurationFactory factory) {
		super(name, configurationModule, factory);
	}

	@Override
	public void writeExternal(Element element) throws WriteExternalException {
		super.writeExternal(element);
	}

	@Override
	public void readExternal(Element element) throws InvalidDataException {
		super.readExternal(element);
	}
}
