package chaser.core.listener.builtin;

import chaser.core.listener.Listener;

import java.nio.charset.Charset;
import java.util.Objects;

public class LogListener implements Listener {

	private String logStartPattern;

	private Charset charset;

	public LogListener(String logStartPattern, Charset charset) {
		Objects.requireNonNull(logStartPattern, "Log start pattern should not be null");
		Objects.requireNonNull(charset, "Character encoding should not be null");

		this.logStartPattern = logStartPattern;
		this.charset = charset;
	}

	@Override
	public void process(byte[] bytes) {

	}

}
