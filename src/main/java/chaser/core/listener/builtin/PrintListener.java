package chaser.core.listener.builtin;

import chaser.core.listener.Listener;

public class PrintListener implements Listener<String, Object> {

	@Override
	public Object process(String str) {
		System.out.print(str);
		return null;
	}

	@Override
	public boolean triggerNext() {
		return false;
	}

}
