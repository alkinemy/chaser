package chaser.standalone.domain.core.tail;

import chaser.standalone.domain.core.file.ChaserFile;

public interface Tail {

	Byte[] read(ChaserFile target);

}
