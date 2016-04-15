package chaser.core.listener.builtin;

import chaser.core.listener.Listener;
import chaser.core.listener.Pipeline;
import chaser.core.listener.builtin.base.AppendListener;
import chaser.core.listener.builtin.base.CharsetListener;
import chaser.core.listener.builtin.base.TokenizeListener;

import java.nio.charset.Charset;

public class LogListener extends Pipeline {

	public LogListener(String logStartPattern, Listener... listeners) {
		super(
			CharsetListener.of(Charset.forName("UTF-8")),
			TokenizeListener.of(System.lineSeparator()),
			AppendListener.of(logStartPattern)
		);
	}

}
