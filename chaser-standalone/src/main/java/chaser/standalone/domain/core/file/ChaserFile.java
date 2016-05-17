package chaser.standalone.domain.core.file;

import java.nio.charset.Charset;
import java.nio.file.Path;

public class ChaserFile {

	private Path path;
	private long position;
	private Charset charset;

	private ChaserFile(Path path, Charset charset) {
		this.path = path;
		this.charset = charset;
		this.position = 0;
	}

	public static ChaserFile of(Path path) {
		return new ChaserFile(path, Charset.defaultCharset());
	}

	public static ChaserFile of(Path path, Charset charset) {
		return new ChaserFile(path, charset);
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
