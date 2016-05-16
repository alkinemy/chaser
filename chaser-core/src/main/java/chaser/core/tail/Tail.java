package chaser.core.tail;

import chaser.core.file.ChaserFile;

public interface Tail {

	Byte[] read(ChaserFile target);

}
