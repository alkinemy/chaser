package chaser.core.listener.builtin;

import chaser.core.listener.Listener;
import chaser.core.listener.Pipeline;
import chaser.core.listener.builtin.base.CharsetListener;

import java.nio.charset.Charset;
import java.util.Objects;
import java.util.regex.Pattern;

public class DelimiterReadingLogListener extends Pipeline {

	public DelimiterReadingLogListener(String logPrefixPattern, String joiner) {
		super(
			CharsetListener.of(Charset.forName("UTF-8")),
			LogAppendListener.of(logPrefixPattern, joiner)
		);
	}


	private static class LogAppendListener implements Listener<String, String> {

		private static final String START_REGEX = "^";

		private String regex;

		private String joiner;

		private StringBuilder log;

		private Pattern logStartRegex;

		//TODO 하나의 thread에서만 실행되도록 처리해야함(순차처리 중요)

		private LogAppendListener(String regex, String joiner) {
			Objects.requireNonNull(regex, "Expression should not be null");
			Objects.requireNonNull(joiner, "Joiner should not be null");

			if (regex.startsWith(START_REGEX)) {
				this.regex = regex;
			} else {
				this.regex = START_REGEX + regex;
			}

			this.joiner = joiner;
			this.logStartRegex = Pattern.compile(regex);
		}

		private static LogAppendListener of(String regex, String joiner) {
			return new LogAppendListener(regex, joiner);
		}

		@Override
		public String process(String from) {
			String result = null;
			if (logStartRegex.matcher(from).find()) {
				if (log != null) {
					result = log.toString();
				}
				log = new StringBuilder();
				log.append(from);
			} else {
				log.append(joiner)
					.append(from);
			}
			return result;
		}

	}
}
