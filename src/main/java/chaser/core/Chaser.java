package chaser.core;

import chaser.core.listener.Listener;
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

	private Chaser(Watcher watcher) {
		this.watcher = watcher;
	}

	public void chase() {
		watcher.startWatching();
	}

	public static ChaserBuilder builder() {
		return new ChaserBuilder();
	}

	private static class ChaserBuilder {
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

			Watcher watcher = WatcherFactory.create(watcherType, target);
			/**
			 * TODO tail worker 처리
			 * TODO listener 처리
			 * tail worker안에 listener가 들어가야됨
			 */
			return new Chaser(watcher);
		}

	}

}
