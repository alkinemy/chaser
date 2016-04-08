package chaser.example;

import chaser.core.Chaser;
import chaser.core.listener.builtin.PrintListener;
import chaser.core.watcher.WatcherType;

import java.nio.charset.Charset;

public class Main {

	public static void main(String[] args) {
		Chaser chaser = Chaser.builder()
			.target("")
			.watcher(WatcherType.FILE_SYSTEM)
			.listener(new PrintListener(Charset.forName("UTF-8")))
			.build();

		chaser.chase();
	}

}
