package chaser.standalone.domain.core.file;

import lombok.Getter;
import lombok.Setter;

import java.nio.charset.Charset;
import java.nio.file.Path;

@Getter
public class ChaserFile {

	@Setter
	private Path path;
	@Setter
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

}
