package chaser.core;

import chaser.core.listener.Listener;
import chaser.core.tail.Tail;
import chaser.core.target.ChaseFile;
import chaser.core.watcher.Watcher;
import chaser.core.watcher.WatcherFactory;
import chaser.core.watcher.WatcherType;
import chaser.util.IOUtils;

import java.io.Closeable;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Chaser implements Closeable {

	private Watcher watcher;
	private List<Listener> listeners;
	private Tail tail;

	private ExecutorService tailExecutorService;
	private ExecutorService listenerExecutorService;

	private ChaseFile target;

	private Chaser(Watcher watcher, Path target, List<Listener> listeners) {
		this.watcher = watcher;
		this.target = ChaseFile.of(target);
		this.listeners = listeners;
		this.tail = new Tail();

		watcher.setChaser(this);

		tailExecutorService = Executors.newFixedThreadPool(1);
		listenerExecutorService = Executors.newFixedThreadPool(10);
	}

	public void chase() {
		watcher.startWatching();
	}

	public void read() {
		tailExecutorService.execute(() -> {
			Byte[] bytes = tail.read(target);

			listeners.parallelStream()
				.forEach(listener ->
					listenerExecutorService.execute(() -> listener.process(bytes)));
		});
	}

	public static ChaserBuilder builder() {
		return new ChaserBuilder();
	}

	@Override
	public void close() throws IOException {
		IOUtils.shutdownExecutorService(tailExecutorService);
		IOUtils.shutdownExecutorService(listenerExecutorService);
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
