package io.yaochi.intellij.plugin.runconfig;

import com.intellij.execution.configurations.RunConfigurationModule;
import com.intellij.openapi.project.Project;

public class TEALModuleBasedConfiguration extends RunConfigurationModule {
	public TEALModuleBasedConfiguration(Project project) {
		super(project);
	}
}