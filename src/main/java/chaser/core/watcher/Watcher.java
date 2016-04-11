package chaser.core.watcher;

import chaser.core.Chaser;

public interface Watcher {

	void startWatching();

	void stopWatching();

	void setChaser(Chaser chaser);

}
