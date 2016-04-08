package chaser.core.listener.builtin;

import chaser.core.listener.Listener;

import java.nio.charset.Charset;

public class PrintListener implements Listener {

	private Charset charset;

	public PrintListener(Charset charset) {
		this.charset = charset;
	}

	@Override
	public void process(byte[] bytes) {
		System.out.println(new String(bytes, charset));
	}

}
