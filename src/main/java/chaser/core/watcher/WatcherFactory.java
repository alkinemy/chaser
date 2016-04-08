package chaser.core.watcher;

import java.nio.file.Path;

public class WatcherFactory {

	public static Watcher create(WatcherType watcherType, Path target) {
		switch (watcherType) {
			case FILE_SYSTEM:
				return new FileSystemWatcher(target);
			case POLLING:
				return null;
			default:
				throw new IllegalArgumentException("Invalid watcher type: " + watcherType);
		}
	}

}
