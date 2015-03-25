package fr.sii.survival.config.options;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class RuleOptions {
	
	/**
	 * Patterns used for excluding some rule implementations
	 */
	private final List<Pattern> excludes; 

	@Autowired
	public RuleOptions(@Value("${game.rule.exclude}") String[] excludes) {
		super();
		this.excludes = Arrays.asList(excludes).stream().map(e -> Pattern.compile(e)).collect(Collectors.toList());
	}

	public List<Pattern> getExcludes() {
		return excludes;
	}
}
