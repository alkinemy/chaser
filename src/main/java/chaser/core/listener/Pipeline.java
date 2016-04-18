package chaser.core.listener;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

public class Pipeline implements Listener {

	private List<Listener> listeners;

	public Pipeline(Listener... listeners) {
		Objects.requireNonNull(listeners, "Listener should not be null");

		this.listeners = Arrays.asList(listeners);
	}

	public static Pipeline of(Listener... listeners) {
		return new Pipeline(listeners);
	}

	@Override
	public Object process(Object from) {
		Object data = from;
		Listener listener;
		Iterator<Listener> iterator = listeners.iterator();
		do {
			listener = iterator.next();
			data = listener.process(data);
		} while (iterator.hasNext());
		return data;
	}

}
