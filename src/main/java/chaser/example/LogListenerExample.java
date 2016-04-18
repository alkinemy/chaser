package chaser.example;

import chaser.core.Chaser;
import chaser.core.listener.Pipeline;
import chaser.core.listener.builtin.LogListener;
import chaser.core.listener.builtin.base.PrintListener;
import chaser.core.watcher.WatcherType;

public class LogListenerExample {

	public static void main(String[] args) {
		Chaser chaser = Chaser.builder()
			.target("/Users/joke/test/test.out")
			.watcher(WatcherType.FILE_SYSTEM)
			.listener(Pipeline.of(new LogListener("\\[hello\\]"), new PrintListener()))
			.build();

		chaser.chase();
	}

}
