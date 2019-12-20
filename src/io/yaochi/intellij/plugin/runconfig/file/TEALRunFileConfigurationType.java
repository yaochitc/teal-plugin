package io.yaochi.intellij.plugin.runconfig.file;

import com.intellij.execution.configurations.ConfigurationTypeBase;
import com.intellij.execution.configurations.RunConfiguration;
import com.intellij.openapi.extensions.Extensions;
import com.intellij.openapi.project.Project;
import io.yaochi.intellij.plugin.TEALConstants;
import io.yaochi.intellij.plugin.TEALIcons;
import io.yaochi.intellij.plugin.runconfig.TEALConfigurationFactoryBase;
import org.jetbrains.annotations.NotNull;

public class TEALRunFileConfigurationType extends ConfigurationTypeBase {
	public TEALRunFileConfigurationType() {
		super("TEALRunFileConfiguration", "TEAL File", "TEAL run file configuration", TEALIcons.APPLICATION_RUN);
		addFactory(new TEALConfigurationFactoryBase(this) {
			@NotNull
			@Override
			public RunConfiguration createTemplateConfiguration(@NotNull Project project) {
				return new TEALRunFileConfiguration(project, TEALConstants.TEAL, getInstance());
			}
		});
	}

	@NotNull
	public static TEALRunFileConfigurationType getInstance() {
		return Extensions.findExtension(CONFIGURATION_TYPE_EP, TEALRunFileConfigurationType.class);
	}
}
