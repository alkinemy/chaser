package chaser.core.listener.builtin.base;

import chaser.core.listener.Listener;

import java.util.List;
import java.util.Objects;

public class AppendListener implements Listener<String, List<String>> {

	private String pattern;

	private AppendListener(String pattern) {
		Objects.requireNonNull(pattern, "Pattern should not be null");

		this.pattern = pattern;
	}

	public static AppendListener of(String pattern) {
		return new AppendListener(pattern);
	}

	@Override
	public List<String> process(String from) {
		return null;
	}

	@Override
	public boolean triggerNext() {
		return true;
	}
}
