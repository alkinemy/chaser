package chaser.standalone;

import chaser.standalone.domain.core.file.ChaserFile;
import chaser.standalone.domain.core.watcher.Watcher;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.nio.file.Paths;

public class ChaserStandalone {

	@Autowired
	private Watcher watcher;

	private ChaserFile chaserFile;

	public ChaserStandalone(String path) {
		this.chaserFile = ChaserFile.of(Paths.get(path));
	}

	@PostConstruct
	public void initialize() {
		watcher.startWatching();
	}

}
