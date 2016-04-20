package chaser.core.chaser;

import java.io.Closeable;

public interface Chaser extends Closeable {

	void chase();
	void read();
	void process(Byte[] bytes);

}
