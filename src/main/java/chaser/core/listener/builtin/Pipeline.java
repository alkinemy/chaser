package chaser.core.listener.builtin;

import chaser.core.listener.Listener;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

public class Pipeline implements Listener<Byte[], Object> {

	private List<Listener> listeners;

	private Pipeline(Listener... listeners) {
		Objects.requireNonNull(listeners, "Listener should not be null");

		this.listeners = Arrays.asList(listeners);
	}

	public static Pipeline of(Listener... listeners) {
		return new Pipeline(listeners);
	}

	@Override
	public Object process(Byte[] bytes) {
		Object data = bytes;
		Listener listener;
		Iterator<Listener> iterator = listeners.iterator();
		do {
			listener = iterator.next();
			data = listener.process(data);
		} while (iterator.hasNext() && listener.triggerNext());

		return null;
	}

	@Override
	public boolean triggerNext() {
		return false;
	}

}
