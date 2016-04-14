package chaser.example;

import chaser.core.Chaser;
import chaser.core.listener.builtin.CharsetListener;
import chaser.core.listener.builtin.Pipeline;
import chaser.core.listener.builtin.PrintListener;
import chaser.core.watcher.WatcherType;

import java.nio.charset.Charset;

public class Main {

	public static void main(String[] args) {
		Chaser chaser = Chaser.builder()
			.target("/Users/joke/test/test.out")
			.watcher(WatcherType.FILE_SYSTEM)
			.listener(Pipeline.of(CharsetListener.of(Charset.forName("UTF-8")), new PrintListener()))
			.build();

		chaser.chase();
	}

}
