package chaser.core.tail;

import chaser.core.target.ChaseFile;

public interface Tail {

	Byte[] read(ChaseFile target);

}
