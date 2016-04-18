package chaser.core.listener.builtin;

import chaser.core.listener.Listener;
import chaser.core.listener.Pipeline;
import chaser.core.listener.builtin.base.CharsetListener;
import chaser.core.listener.builtin.base.TokenizeListener;
import chaser.util.StringUtils;
import org.fest.util.Collections;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;

public class LogListener extends Pipeline {

	//TODO customizing

	public LogListener(String logPrefixPattern) {
		super(
			CharsetListener.of(Charset.forName("UTF-8")),
			TokenizeListener.of(System.lineSeparator()),
			LogAppendListener.of(logPrefixPattern, System.lineSeparator())
		);
	}


	private static class LogAppendListener implements Listener<List<String>, List<String>> {

		private static final String START_REGEX = "^";

		private String regex;

		private String joiner;

		private LogAppendListener(String regex, String joiner) {
			Objects.requireNonNull(regex, "Expression should not be null");
			Objects.requireNonNull(joiner, "Joiner should not be null");

			if (regex.startsWith(START_REGEX)) {
				this.regex = regex;
			} else {
				this.regex = START_REGEX + regex;
			}

			this.joiner = joiner;
		}

		private static LogAppendListener of(String regex, String joiner) {
			return new LogAppendListener(regex, joiner);
		}

		@Override
		public List<String> process(List<String> from) {
			List<String> result = new ArrayList<>();

			List<String> intermediate = null;
			Pattern pattern = Pattern.compile(regex);
			Iterator<String> iterator = from.iterator();
			while(iterator.hasNext()) {
				String str = iterator.next();
				if (pattern.matcher(str).find()) {
					if (intermediate != null) {
						result.add(StringUtils.join(intermediate, joiner));
					}
					intermediate = new ArrayList<>();
					intermediate.add(str);
				} else {
					if (intermediate == null) {
						intermediate = new ArrayList<>();
					}
					intermediate.add(str);
				}
			}
			if (!Collections.isNullOrEmpty(intermediate)) {
				result.add(StringUtils.join(intermediate, joiner));
			}

			return result;
		}
	}
}
