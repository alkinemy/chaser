package chaser.example.full;

import chaser.core.chaser.Chaser;
import chaser.core.chaser.ChaserBuilder;
import chaser.core.listener.Pipeline;
import chaser.core.listener.builtin.base.CharsetListener;
import chaser.core.listener.builtin.base.PrintListener;
import chaser.core.watcher.WatcherType;

import java.nio.charset.Charset;

public class FullChaserPipelineExample {

	public static void main(String[] args) {
		Chaser chaser = new ChaserBuilder()
			.readChangesFullyThenProcess()
			.target("/Users/joke/test/test.out")
			.watcher(WatcherType.FILE_SYSTEM)
			.listener(Pipeline.of(CharsetListener.of(Charset.forName("UTF-8")), new PrintListener()))
			.build();

		chaser.chase();
	}

}
