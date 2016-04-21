package chaser.core.chaser;

import chaser.core.listener.Listener;
import chaser.core.watcher.Watcher;
import chaser.core.watcher.WatcherFactory;
import chaser.core.watcher.WatcherType;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ChaserBuilder {

	private boolean delimiterChaser;
	private boolean fullChaser;
	private String delimiter;

	public ChaserComponentBuilder readChangesByDelimeterThenProcess(String delimiter) {
		this.delimiterChaser = true;
		this.fullChaser = false;
		this.delimiter = delimiter;
		return new ChaserComponentBuilder();
	}

	public ChaserComponentBuilder readChangesFullyThenProcess() {
		this.delimiterChaser = false;
		this.fullChaser = true;
		return new ChaserComponentBuilder();
	}

	public class ChaserComponentBuilder {

		private Path target;
		private WatcherType watcherType;
		private List<Listener> listeners = new ArrayList<>();

		public ChaserComponentBuilder target(String targetPathString) {
			this.target = Paths.get(targetPathString);
			return this;
		}

		public ChaserComponentBuilder watcher(WatcherType watcherType) {
			this.watcherType = watcherType;
			return this;
		}

		public ChaserComponentBuilder listener(Listener listener) {
			this.listeners.add(listener);
			return this;
		}

		public Chaser build() {
			Objects.requireNonNull(watcherType, "Watcher type should not be null");

			Watcher watcher = WatcherFactory.create(watcherType, target);
			if (delimiterChaser) {
				return new DelimiterChaser(delimiter, watcher, target, listeners);
			} else if (fullChaser) {
				return new FullChaser(watcher, target, listeners);
			}

			throw new IllegalArgumentException("Invalid chaser type");
		}

	}

}
