package chaser.core.listener.builtin.base;

import chaser.core.listener.Listener;
import chaser.util.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class TokenizeListener implements Listener<String, List<String>> {

	private String tokenizer;

	private TokenizeListener(String tokenizer) {
		Objects.requireNonNull(tokenizer, "Tokenize should not be null");
		this.tokenizer = tokenizer;
	}

	public static TokenizeListener of(String tokenizer) {
		return new TokenizeListener(tokenizer);
	}

	@Override
	public List<String> process(String from) {
		if (StringUtils.isBlank(from)) {
			return new ArrayList<>();
		}

		return Arrays.asList(from.split(tokenizer));
	}

	@Override
	public boolean triggerNext() {
		return true;
	}

}
