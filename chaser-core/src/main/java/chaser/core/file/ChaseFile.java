package chaser.core.file;

import java.nio.file.Path;

public class ChaseFile {

	private Path path;
	private long position;

	private ChaseFile(Path path) {
		this.path = path;
		this.position = 0;
	}

	public static ChaseFile of(Path path) {
		return new ChaseFile(path);
	}

	public Path getPath() {
		return path;
	}

	public long getPosition() {
		return position;
	}

	public void setPosition(long position) {
		this.position = position;
	}

}
