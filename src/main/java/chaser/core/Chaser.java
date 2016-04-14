package chaser.core;

import chaser.core.listener.Listener;
import chaser.core.tail.Tail;
import chaser.core.target.ChaseFile;
import chaser.core.watcher.Watcher;
import chaser.core.watcher.WatcherFactory;
import chaser.core.watcher.WatcherType;

import java.io.Closeable;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Chaser implements Closeable {

	private Watcher watcher;
	private List<Listener> listeners;
	private Tail tail;

	private ExecutorService tailExecutorService;

	private ChaseFile target;

	private Chaser(Watcher watcher, Path target, List<Listener> listeners) {
		this.watcher = watcher;
		this.target = ChaseFile.of(target, target.toFile().length());
		this.listeners = listeners;
		this.tail = new Tail();

		watcher.setChaser(this);

		tailExecutorService = Executors.newFixedThreadPool(1);
	}

	public void chase() {
		watcher.startWatching();
	}

	public void read() {
		tailExecutorService.execute(() -> {
			Byte[] bytes = tail.read(target);

			//TODO async로 처리
			listeners.parallelStream()
				.forEach(listener -> listener.process(bytes));
		});
	}

	public static ChaserBuilder builder() {
		return new ChaserBuilder();
	}

	@Override
	public void close() throws IOException {
		tailExecutorService.shutdown();
		try {
			if (!tailExecutorService.awaitTermination(5, TimeUnit.SECONDS)) {
				tailExecutorService.shutdownNow();
				if (!tailExecutorService.awaitTermination(5, TimeUnit.SECONDS)) {
					//TODO 로그
				}
			}
		} catch (InterruptedException e) {
			tailExecutorService.shutdownNow();
			Thread.currentThread().interrupt();
		}
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
