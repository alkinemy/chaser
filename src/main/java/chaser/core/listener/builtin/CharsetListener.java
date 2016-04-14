package chaser.core.listener.builtin;

import chaser.core.listener.Listener;
import chaser.util.ByteUtils;

import java.nio.charset.Charset;
import java.util.Objects;

public class CharsetListener implements Listener<Byte[], String> {

	private final Charset charset;

	private CharsetListener(Charset charset) {
		Objects.requireNonNull(charset, "Character encoding should not be null");

		this.charset = charset;
	}

	public static CharsetListener of(Charset charset) {
		return new CharsetListener(charset);
	}

	@Override
	public String process(Byte[] bytes) {
		return new String(ByteUtils.toByteArray(bytes), charset);
	}

	@Override
	public boolean triggerNext() {
		return true;
	}

}
