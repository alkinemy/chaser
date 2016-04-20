package chaser.core.chaser;

import chaser.core.listener.Listener;
import chaser.core.watcher.WatcherType;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ChaserBuilder {

	private Path target;
	private WatcherType watcherType;
	private List<Listener> listeners = new ArrayList<>();

	public ChaserBuilder target(String targetPathString) {
		this.target = Paths.get(targetPathString);
		return this;
	}

	public ChaserBuilder watcher(WatcherType watcherType) {
		this.watcherType = watcherType;
		return this;
	}

	public ChaserBuilder listener(Listener listener) {
		this.listeners.add(listener);
		return this;
	}

	public Chaser build() {
		Objects.requireNonNull(watcherType, "Watcher type should not be null");

		//TODO chaser 구현
		return null;
//		return new Chaser(WatcherFactory.create(watcherType, target), target, listeners);
	}

}
