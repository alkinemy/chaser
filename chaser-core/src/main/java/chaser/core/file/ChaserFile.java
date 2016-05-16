package chaser.core.file;

import java.nio.file.Path;

public class ChaserFile {

	private Path path;
	private long position;

	private ChaserFile(Path path) {
		this.path = path;
		this.position = 0;
	}

	public static ChaserFile of(Path path) {
		return new ChaserFile(path);
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
