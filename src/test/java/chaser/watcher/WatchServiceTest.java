package chaser.watcher;

import org.junit.Test;

import java.io.IOException;
import java.nio.file.*;
import java.util.Date;

import static com.sun.nio.file.SensitivityWatchEventModifier.HIGH;
import static java.nio.file.StandardWatchEventKinds.ENTRY_MODIFY;

public class WatchServiceTest {

	@Test
	public void watchService() throws IOException, InterruptedException {
		String filePath = "/Users/joke/test/test.out";
		Path directory = Paths.get(filePath).getParent();
		Path targetFile = Paths.get(filePath).getFileName();
		try (WatchService watchService = directory.getFileSystem().newWatchService()) {
			directory.register(watchService, new WatchEvent.Kind[]{ ENTRY_MODIFY }, HIGH);

			WatchKey key;
			while (true) {
				while ((key = watchService.take()) != null) {
					for (WatchEvent<?> event : key.pollEvents()) {
						Path changed = (Path) event.context();
						if (changed.endsWith(targetFile)) {
							System.out.println("Target file changed");
						}
						System.out.print(new Date());
						System.out.printf(" Received %s event for file: %s\n", event.kind(), event.context());
					}
					key.reset();
				}
			}
		}
	}

}
