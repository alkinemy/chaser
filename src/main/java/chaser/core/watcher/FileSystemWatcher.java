package chaser.core.watcher;

import chaser.core.Chaser;

import java.io.IOException;
import java.nio.file.*;

import static com.sun.nio.file.SensitivityWatchEventModifier.HIGH;
import static java.nio.file.StandardWatchEventKinds.ENTRY_MODIFY;

public class FileSystemWatcher implements Watcher {

	private Path target;

	private boolean watchingEnabled = true; //TODO 좀 더 잘 처리...

	private Chaser chaser;

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
		Path targetFile = target.getFileName();
		try (WatchService watchService = parentDirectory.getFileSystem().newWatchService()) {
			//TODO create/delete event도 처리해야하나?
			parentDirectory.register(watchService, new WatchEvent.Kind[] { ENTRY_MODIFY }, HIGH);

			WatchKey key;
			while (watchingEnabled) {
				while ((key = watchService.take()) != null) {
					key.pollEvents().stream()
						.filter(event -> ((Path) event.context()).endsWith(targetFile))
						.forEach(event -> chaser.read());
					key.reset();
				}
			}
		} catch (InterruptedException | IOException e) {
			//TODO log
			e.printStackTrace();
		}
	}

	@Override
	public void stopWatching() {
		this.watchingEnabled = false;
		//TODO 뭔가 더 처리가 필요할듯
	}

	@Override
	public void setChaser(Chaser chaser) {
		this.chaser = chaser;
	}

}
