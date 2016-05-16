package chaser.core.chaser;

import chaser.core.listener.Listener;
import chaser.core.tail.FullReadingTail;
import chaser.core.tail.Tail;
import chaser.core.file.ChaseFile;
import chaser.core.watcher.Watcher;
import chaser.util.IOUtils;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FullChaser implements Chaser {

	private Watcher watcher;
	private List<Listener> listeners;
	private Tail tail;

	private ExecutorService tailExecutorService;
	private ExecutorService listenerExecutorService;

	private ChaseFile target;

	public FullChaser(Watcher watcher, Path target, List<Listener> listeners) {
		this.watcher = watcher;
		this.target = ChaseFile.of(target);
		this.listeners = listeners;
		this.tail = new FullReadingTail();

		watcher.setChaser(this);

		tailExecutorService = Executors.newFixedThreadPool(1);
		listenerExecutorService = Executors.newFixedThreadPool(10);
	}

	@Override
	public void chase() {
		watcher.startWatching();
	}

	@Override
	public void read() {
		tailExecutorService.execute(() -> {
			Byte[] bytes = tail.read(target);
			process(bytes);
		});
	}

	@Override
	public void process(Byte[] bytes) {
		listeners.parallelStream()
			.forEach(listener ->
				listenerExecutorService.execute(() -> listener.process(bytes)));
	}

	@Override
	public void close() throws IOException {
		IOUtils.shutdownExecutorService(tailExecutorService);
		IOUtils.shutdownExecutorService(listenerExecutorService);
	}

}
