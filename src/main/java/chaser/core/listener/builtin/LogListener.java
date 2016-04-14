package chaser.core.listener.builtin;

import chaser.core.listener.Listener;

import java.util.List;
import java.util.Objects;

public class LogListener implements Listener<String, List<String>> {

	private String logStartPattern;

	public LogListener(String logStartPattern) {
		Objects.requireNonNull(logStartPattern, "Log start pattern should not be null");

		this.logStartPattern = logStartPattern;
	}

	@Override
	public List<String> process(String from) {
		return null;
	}

	@Override
	public boolean triggerNext() {
		return false;
	}
}
