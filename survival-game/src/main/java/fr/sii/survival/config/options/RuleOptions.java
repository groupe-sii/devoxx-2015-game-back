package fr.sii.survival.config.options;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix="game.rule")
public class RuleOptions {
	
	/**
	 * Patterns used for excluding some rule implementations
	 */
	private List<Pattern> excludes = new ArrayList<>(); 

	public List<Pattern> getExcludes() {
		return excludes;
	}

	public void setExcludes(List<Pattern> excludes) {
		this.excludes = excludes;
	}
}
