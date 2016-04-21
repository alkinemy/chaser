package chaser.example.delimiter;

import chaser.core.chaser.Chaser;
import chaser.core.chaser.ChaserBuilder;
import chaser.core.listener.Pipeline;
import chaser.core.listener.builtin.DelimiterReadingLogListener;
import chaser.core.listener.builtin.base.PrintLineListener;
import chaser.core.watcher.WatcherType;

public class DelimiterChaserLogListenerExample {

	public static void main(String[] args) {
		Chaser chaser = new ChaserBuilder()
			.readChangesByDelimeterThenProcess(System.lineSeparator())
			.target("/Users/joke/test/test.out")
			.watcher(WatcherType.FILE_SYSTEM)
			.listener(Pipeline.of(new DelimiterReadingLogListener("\\[hello\\]", System.lineSeparator()), new PrintLineListener()))
			.build();

		chaser.chase();
	}

}
