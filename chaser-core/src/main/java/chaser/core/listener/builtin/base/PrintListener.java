package chaser.core.listener.builtin.base;

import chaser.core.listener.Listener;

public class PrintListener implements Listener<Object, Object> {

	@Override
	public Object process(Object obj) {
		if (obj != null) {
			System.out.print(obj);
		}
		return obj;
	}

}
