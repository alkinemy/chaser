package chaser.example.delimiter;

import chaser.core.chaser.Chaser;
import chaser.core.chaser.ChaserBuilder;
import chaser.core.listener.Pipeline;
import chaser.core.listener.builtin.base.CharsetListener;
import chaser.core.listener.builtin.base.PrintLineListener;
import chaser.core.watcher.WatcherType;

import java.nio.charset.Charset;

public class DelimiterChaserPipelineExample {

	public static void main(String[] args) {
		Chaser chaser = new ChaserBuilder()
			.readChangesByDelimeterThenProcess(System.lineSeparator())
			.target("/Users/joke/test/test.out")
			.watcher(WatcherType.FILE_SYSTEM)
			.listener(Pipeline.of(CharsetListener.of(Charset.forName("UTF-8")), new PrintLineListener()))
			.build();

		chaser.chase();
	}

}
