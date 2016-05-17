package chaser.standalone.domain.core.listener;

public interface Listener<F, T> {

	T process(F from);

}
