package chaser.core.watcher;

import java.io.IOException;
import java.nio.file.*;

import static com.sun.nio.file.SensitivityWatchEventModifier.HIGH;
import static java.nio.file.StandardWatchEventKinds.ENTRY_MODIFY;

public class FileSystemWatcher implements Watcher {

	private Path target;

	private boolean watchingEnabled = true; //좀 더 잘 처리...

	public FileSystemWatcher(String targetPathString) {
		this.target = Paths.get(targetPathString);
		//TODO file existence check
		//TODO check if target is file type
	}

	public FileSystemWatcher(Path targetPath) {
		this.target = targetPath;
	}

	@Override
	public void startWatching() {
		Path parentDirectory = target.getParent();
		try (WatchService watchService = parentDirectory.getFileSystem().newWatchService()) {
			parentDirectory.register(watchService, new WatchEvent.Kind[]{ ENTRY_MODIFY }, HIGH);

			WatchKey key;
			while (watchingEnabled) {
				while ((key = watchService.take()) != null) {
					key.pollEvents().stream()
						.filter(event -> ((Path) event.context()).endsWith(target))
						.forEach(System.out::println);
					//TODO foreach부분에서 target file의 tail에게 읽으라고 시켜야함
					key.reset();
				}
			}
		} catch (InterruptedException | IOException e) {
			//TODO log
			e.printStackTrace();
		}
	}

}
