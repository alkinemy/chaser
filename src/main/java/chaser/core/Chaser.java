package chaser.core;

import chaser.core.listener.Listener;
import chaser.core.tail.TailWorker;
import chaser.core.watcher.Watcher;
import chaser.core.watcher.WatcherFactory;
import chaser.core.watcher.WatcherType;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Chaser {

	private Watcher watcher;
	private List<Listener> listeners;
	private TailWorker tailWorker;

	private Path target;

	private Chaser(Watcher watcher, Path target, List<Listener> listeners) {
		this.watcher = watcher;
		this.target = target;
		this.listeners = listeners;
		this.tailWorker = new TailWorker();

		watcher.setChaser(this);
	}

	public void chase() {
		watcher.startWatching();
	}

	public void read() {
		//TODO 0을 고쳐야됨
		byte[] bytes = tailWorker.read(target, 0);
		listeners.parallelStream()
			.forEach(listener -> listener.process(bytes));
	}

	public static ChaserBuilder builder() {
		return new ChaserBuilder();
	}

	public static class ChaserBuilder {

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

			return new Chaser(WatcherFactory.create(watcherType, target), target, listeners);
		}

	}

}
