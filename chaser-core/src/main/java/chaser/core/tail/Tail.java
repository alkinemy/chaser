package chaser.core.tail;

import chaser.core.file.ChaseFile;

public interface Tail {

	Byte[] read(ChaseFile target);

}
