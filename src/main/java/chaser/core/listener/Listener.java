package chaser.core.listener;

public interface Listener<F, T> {

	T process(F from);

	boolean triggerNext();

}
